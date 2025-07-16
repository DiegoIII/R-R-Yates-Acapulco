FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/user-service-*.jar ./user-service.jar

ENV SPRING_PROFILES_ACTIVE=prod
ENV DB_URL=jdbc:postgresql://user-db:5432/user_db
ENV DB_USERNAME=user_user
ENV DB_PASSWORD=user_pass
ENV JWT_SECRET=your_jwt_secret_key_here
ENV JWT_EXPIRATION_MS=86400000

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-service.jar"]