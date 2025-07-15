FROM eclipse-temurin:21-jdk as builder

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw pom.xml ./
COPY .mvn/ .mvn/

# Copy source code
COPY src/ src/

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a smaller JRE image for the final container
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/puskesmas-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8008

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 