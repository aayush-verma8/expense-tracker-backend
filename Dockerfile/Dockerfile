FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY . .

# Build using Maven (NOT mvnw)
RUN mvn clean package -DskipTests

# Run app
CMD ["java", "-jar", "target/*.jar"]