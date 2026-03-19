FROM openjdk:17

WORKDIR /app

COPY . .

# 🔥 ADD THIS LINE (IMPORTANT)
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Run the jar
CMD ["java", "-jar", "target/*.jar"]