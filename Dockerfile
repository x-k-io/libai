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
ARG JAR_FILE=libai-webapp/build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 4. 解压分层并复制到目标目录（layers 由 extract 在容器内生成，需用 cp 而非 COPY）
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher --destination layers \
    && cp -r layers/dependencies/. ./ \
    && cp -r layers/spring-boot-loader/. ./ \
    && cp -r layers/snapshot-dependencies/. ./ \
    && cp -r layers/application/. ./ \
    && chown -R spring:spring /app \
    && rm -rf layers app.jar

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