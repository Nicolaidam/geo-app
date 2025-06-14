# Stage 1: Build the application using a Gradle image with JDK 21.
FROM gradle:8.14.0-jdk21-corretto AS builder
WORKDIR /home/gradle/project

# Copy Gradle wrapper and build configuration
COPY gradlew gradlew.bat ./
COPY gradle gradle
COPY settings.gradle.kts ./
COPY build.gradle.kts ./
COPY shared/build.gradle.kts shared/
COPY server/build.gradle.kts server/

# Make gradlew executable
RUN chmod +x gradlew

# Download project dependencies
RUN ./gradlew --no-daemon :server:dependencies

# Copy the remaining source code
COPY shared/src shared/src
COPY server/src server/src

# Build the server distribution
RUN ./gradlew --no-daemon :server:installDist

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built distribution from the builder stage
COPY --from=builder /home/gradle/project/server/build/install/server/ /app

# Start the application
CMD ["./bin/server"]
