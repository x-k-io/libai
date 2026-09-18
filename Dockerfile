# ---------- build stage ----------
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /workspace

# Copy gradle build scripts first, cache dependencies
COPY gradlew settings.gradle build.gradle gradle.properties ./
COPY gradle ./gradle
RUN chmod +x gradlew
RUN ./gradlew --no-daemon resolveDependencies

# Copy all remaining source code, no hardcode submodule names
COPY . .

# Build bootJar, skip test
RUN ./gradlew --no-daemon bootJar -x test

# Recursively find bootJar and split by layertools
RUN cp "$(find . -path '*/build/libs/*.jar' ! -name '*-plain.jar' | head -n 1)" app.jar \
    && java -Djarmode=layertools -jar app.jar extract --destination layers

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-jammy

# Set timezone
RUN apt-get update && apt-get install -y --no-install-recommends tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Non-root user
RUN groupadd --system --gid 1001 spring \
    && useradd --system --uid 1001 --gid spring --create-home spring

WORKDIR /app

COPY --from=builder --chown=spring:spring /workspace/layers/dependencies/ ./
COPY --from=builder --chown=spring:spring /workspace/layers/spring-boot-loader/ ./
COPY --from=builder --chown=spring:spring /workspace/layers/snapshot-dependencies/ ./
COPY --from=builder --chown=spring:spring /workspace/layers/application/ ./

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