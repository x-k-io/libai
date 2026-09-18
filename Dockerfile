# ---------- 阶段1：构建 builder 阶段 ----------
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /workspace

# 优先拷贝gradle脚本，利用docker缓存：依赖不变就不用重新下载
COPY gradlew settings.gradle build.gradle gradle.properties ./
COPY gradle gradle
RUN chmod +x gradlew
RUN ./gradlew --no-daemon resolveDependencies

# 拷贝业务源码
COPY src src

# 打包，跳过单元测试（CI流水线如果需要跑测试，删掉 -x test）
RUN ./gradlew --no-daemon bootJar -x test

# SpringBoot layertools：拆分胖Jar为4层
# dependencies(第三方依赖) / spring-boot-loader / snapshot-dependencies / application(你的业务代码)
RUN cp "$(find build/libs -name '*.jar' ! -name '*-plain.jar' | head -n 1)" app.jar \
    && java -Djarmode=layertools -jar app.jar extract --destination layers

# ---------- 阶段2：运行 runtime 阶段 ----------
FROM eclipse-temurin:17-jre-jammy

# 设置时区 Asia/Shanghai，日志、数据库时间统一
RUN apt-get update && apt-get install -y --no-install-recommends tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# 创建非root安全用户，uid=1001 行业常用
RUN groupadd --system --gid 1001 spring \
    && useradd --system --uid 1001 --gid spring --create-home spring

WORKDIR /app

# 分层拷贝，按依赖稳定程度顺序拷贝（缓存核心）
COPY --from=builder --chown=spring:spring /workspace/layers/dependencies/ ./
COPY --from=builder --chown=spring:spring /workspace/layers/spring-boot-loader/ ./
COPY --from=builder --chown=spring:spring /workspace/layers/snapshot-dependencies/ ./
COPY --from=builder --chown=spring:spring /workspace/layers/application/ ./

# 切换非root用户运行
USER spring

# 端口声明，你的项目端口11010
EXPOSE 11010

# 微服务健康探针，复用actuator端点 /actuator/health
HEALTHCHECK --interval=30s --timeout=5s --retries=3 --start-period=60s \
  CMD wget -qO- http://127.0.0.1:11010/actuator/health || exit 1

# JVM环境变量，生产参数
ENV JAVA_OPTS="\
-XX:+UseContainerSupport \
-XX:MaxRAMPercentage=70.0 \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/home/spring/logs \
-Duser.timezone=Asia/Shanghai \
-Dnetworkaddress.cache.ttl=60 \
-Dnetworkaddress.cache.negative.ttl=10 \
"

# exec 模式：sh exec 把java提升为PID1，保证SIGTERM信号能传给JVM，优雅停机
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS org.springframework.boot.loader.JarLauncher"]