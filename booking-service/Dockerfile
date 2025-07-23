FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/service-*.jar ./booking-service.jar

ENV SPRING_PROFILES_ACTIVE=prod
ENV DB_URL=jdbc:postgresql://booking-db:5432/booking_db
ENV DB_USERNAME=booking_user
ENV DB_PASSWORD=booking_pass
ENV PAYMENT_SERVICE_URL=http://payment-service:8080
ENV CATALOG_SERVICE_URL=http://catalog-service:8080

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "booking-service.jar"]