FROM maven:3.8.8-eclipse-temurin-21-alpine as build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-slim

COPY --from=build target/fernando-Ecommerce-api-0.0.1-SNAPSHOT.jar target/fernando-ecommerce-api-0.0.1-SNAPSHOT.jar


EXPOSE 3000

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom", "target/fernando-ecommerce-api-0.0.1-SNAPSHOT.jar"]