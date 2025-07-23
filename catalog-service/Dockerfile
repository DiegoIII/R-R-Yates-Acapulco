FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/catalog-*.jar ./catalog-service.jar

ENV SPRING_PROFILES_ACTIVE=prod
ENV DB_URL=jdbc:postgresql://catalog-db:5432/catalog_db
ENV DB_USERNAME=catalog_user
ENV DB_PASSWORD=catalog_pass

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "catalog-service.jar"]