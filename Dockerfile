# ====================== 阶段1：构建阶段，使用temurin17 jdk，不依赖gradle镜像 ======================
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /opt/app

# 拷贝gradle wrapper（gradlew + gradle目录，里面就是你的gradle-8.5-all）
COPY gradlew ./
COPY gradle ./gradle
# 赋予执行权限
RUN chmod +x gradlew

# 优先拷贝构建脚本，docker缓存层
COPY build.gradle settings.gradle ./

# 预下载依赖，缓存
RUN ./gradlew dependencies --no-daemon

# 拷贝源码
COPY src ./src

# 打包，跳过单元测试；CI环境去掉 -x test
RUN ./gradlew bootJar --no-daemon -x test

# ====================== 阶段2：运行阶段 生产镜像 eclipse-temurin:17-jre-alpine ======================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app

# 设置上海时区
RUN apk add --no-cache tzdata && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo Asia/Shanghai > /etc/timezone && apk del tzdata

# 创建普通用户，禁止root运行（生产安全）
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# 拷贝jar包
COPY --from=builder /opt/app/build/libs/*.jar app.jar

# 授权
RUN chown -R appuser:appgroup /opt/app

# 切换非root用户
USER appuser

EXPOSE 11010

# 微服务健康检查 actuator
HEALTHCHECK --interval=30s --timeout=5s --retries=3 --start-period=60s \
  CMD wget -qO- http://127.0.0.1:11010/actuator/health || exit 1

# JVM生产参数
ENTRYPOINT ["java", \
"-XX:+UseContainerSupport", \
"-XX:MaxRAMPercentage=70.0", \
"-XX:+HeapDumpOnOutOfMemoryError", \
"-XX:HeapDumpPath=/opt/app/logs", \
"-Duser.timezone=Asia/Shanghai", \
"-Dnetworkaddress.cache.ttl=60", \
"-Dnetworkaddress.cache.negative.ttl=10", \
"-jar", "app.jar"]