FROM openjdk:21-slim
WORKDIR /app
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]