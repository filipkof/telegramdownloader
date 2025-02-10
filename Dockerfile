# Сборка приложения
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Создание образа
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/TelegramDownloader-1.0.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]