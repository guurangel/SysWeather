# Build do projeto usando Maven
FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Container final executando a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/sysweather-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
