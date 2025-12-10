# ====== Build stage ======
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src
COPY pom.xml .
RUN mvn -q -B -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -B -e package

# ====== Runtime stage (non-root) ======
FROM eclipse-temurin:17-jre-alpine
ENV APP_HOME=/app
WORKDIR ${APP_HOME}

# Create non-root user/group
RUN addgroup -S app && adduser -S app -G app
USER app

# Copy artifact
COPY --chown=app:app --from=build /src/target/java-webservice-0.1.0.jar app.jar
# Config directory for ConfigMap mount at runtime
RUN mkdir -p ${APP_HOME}/config

EXPOSE 8080

# Healthcheck using actuator
HEALTHCHECK --interval=10s --timeout=3s --retries=5 \
  CMD wget -qO- http://localhost:8080/actuator/health | grep '"status":"UP"' || exit 1

ENTRYPOINT ["java","-jar","/app/app.jar"]
