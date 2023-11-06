FROM openjdk:8-jdk-alpine
WORKDIR /app
EXPOSE 8087
COPY target/kaddem-2.0.jar kaddem-2.0.jar
ENTRYPOINT ["java", "-jar", "kaddem-2.0.jar"]
