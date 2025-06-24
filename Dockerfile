# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Install necessary packages
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies with timeout and retry
RUN ./mvnw dependency:go-offline -B -Dmaven.wagon.http.retryHandler.count=3 -Dmaven.wagon.http.retryHandler.retryInterval=1000

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests -B

# Create a new stage for runtime
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/target/hibernate-example-1.0-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Set environment variables
ENV JAVA_OPTS=""

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 