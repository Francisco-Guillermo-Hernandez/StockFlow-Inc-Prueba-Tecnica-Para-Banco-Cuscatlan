# Stage 1: Build stage
FROM amazoncorretto:25 AS build
WORKDIR /app

# Copy all necessary files first
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Make sure mvnw is executable
RUN chmod +x mvnw

# Download dependencies with better error handling
#RUN ./mvnw dependency:go-offline -B -Dmaven.repo.local=/tmp/.m2/repository

# Download all the required project dependencies.
RUN ./mvnw dependency:resolve

# Copy source code and build
COPY src ./src
RUN ./mvnw package -DskipTests
#RUN ./mvnw clean install -DskipTests

# Stage 2: Run stage
FROM amazoncorretto:25-alpine
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 3030

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
