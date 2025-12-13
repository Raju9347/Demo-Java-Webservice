# ====== Build stage ======
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /src


# Copy pom.xml first to leverage caching
COPY pom.xml .
RUN mvn -q -B -e -DskipTests dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -q -B -e package -DskipTests

# ====== Runtime stage (non-root) ======
FROM eclipse-temurin:21-jre-alpine
ENV APP_HOME=/app
WORKDIR ${APP_HOME}
##Vulnerability Remediation - Install specific version of libpng
RUN apk add --no-cache libpng=1.6.53-r0
# Create non-root user/group
RUN addgroup -S app && adduser -S app -G app
USER app

# Copy built artifact (pick the fat JAR created by Spring Boot plugin)
COPY --chown=app:app --from=build /src/target/java-webservice-1.0.0.jar app.jar
# Or if you use release versioning:
# Copy initial JSON for items (from build context)
# Build stage contains source at /src/src/... because we used WORKDIR /src and COPY src ./src
COPY --chown=app:app --from=build /src/src/main/resources/data/items.json /data/items.json

EXPOSE 8080

# Healthcheck using Spring Boot actuator
HEALTHCHECK --interval=10s --timeout=3s --retries=5 \
  CMD wget -qO- http://localhost:8080/actuator/health | grep '"status":"UP"' || exit 1

ENTRYPOINT ["java","-jar","/app/app.jar"]
