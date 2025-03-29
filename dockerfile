# Use Amazon Corretto 21 base image (Amazon's distribution of OpenJDK)
FROM amazoncorretto:21

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/transaction-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
