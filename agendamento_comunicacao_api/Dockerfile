FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/agendamento_comunicacao_api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]