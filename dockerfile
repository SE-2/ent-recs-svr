Dockerfile

Copy

# Use the official OpenJDK image as the base
FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml into the working directory
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execute permissions to the mvnw file
RUN chmod +x mvnw

# Install dos2unix
RUN apk update && apk add dos2unix

# Convert the line endings of the mvnw file to Unix-style (LF)
RUN dos2unix mvnw

# Install dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the application code
COPY src src

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["./mvnw", "spring-boot:run"]
