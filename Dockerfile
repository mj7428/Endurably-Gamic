# Stage 1: Use an official Maven image to build the application's JAR file
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Use a lightweight Java image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
