FROM eclipse-temurin:17-jre-jammy

# 1. 设置时区
RUN apt-get update && apt-get install -y --no-install-recommends tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# 2. 创建非 root 用户
RUN groupadd --system --gid 1001 spring \
    && useradd --system --uid 1001 --gid spring --create-home spring

WORKDIR /app

# 3. 接收外部编译好的 Jar 包（通过构建参数或直接 COPY）
ARG JAR_FILE=libai-launcher/build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 4. 使用 layertools 解压分层
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher --destination layers

# 5. 复制解压后的各层到对应目录
COPY --chown=spring:spring layers/dependencies/ ./
COPY --chown=spring:spring layers/spring-boot-loader/ ./
COPY --chown=spring:spring layers/snapshot-dependencies/ ./
COPY --chown=spring:spring layers/application/ ./

# 清理掉原始的 app.jar，只留解压后的层
RUN rm app.jar

USER spring

EXPOSE 11010

HEALTHCHECK --interval=30s --timeout=5s --retries=3 --start-period=60s \
  CMD wget -qO- http://127.0.0.1:11010/actuator/health || exit 1

ENV JAVA_OPTS="\
-XX:+UseContainerSupport \
-XX:MaxRAMPercentage=70.0 \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/home/spring/logs \
-Duser.timezone=Asia/Shanghai \
-Dnetworkaddress.cache.ttl=60 \
-Dnetworkaddress.cache.negative.ttl=10 \
"

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS org.springframework.boot.loader.JarLauncher"]