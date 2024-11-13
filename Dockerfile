# Start with an official Maven image with OpenJDK 17 to build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire project source code into the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK 17 image for the runtime
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/com.bjcms-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
