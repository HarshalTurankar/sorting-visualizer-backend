# ---------- Build stage (Maven + JDK 21) ----------
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom first to download dependencies
COPY sorting-visualizer/pom.xml .
RUN mvn dependency:go-offline -B

# Copy project source and build
COPY sorting-visualizer/src ./src
RUN mvn clean package -DskipTests -B

# ---------- Run stage (smaller runtime image) ----------
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render will map it)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
