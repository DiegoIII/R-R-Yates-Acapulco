FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/service-0.0.1-SNAPSHOT.jar ./user-service.jar

ENV SPRING_PROFILES_ACTIVE=prod
ENV DB_URL=jdbc:postgresql://user-db:5432/user_db
ENV DB_USERNAME=user_user
ENV DB_PASSWORD=user_pass
ENV JWT_SECRET=your_jwt_secret_key_here
ENV JWT_EXPIRATION_MS=86400000

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-service.jar"]