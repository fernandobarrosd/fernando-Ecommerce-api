FROM maven:3.8.8-eclipse-temurin-21 as build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-slim

COPY --from=build target/fernando-Ecommerce-api-0.0.1-SNAPSHOT.jar fernando-ecommerce-api-0.0.1-SNAPSHOT.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "fernando-ecommerce-api-0.0.1-SNAPSHOT.jar"]