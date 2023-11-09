FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD kaddem/target/kaddem-4.0.jar kaddem-4.0.jar
ENTRYPOINT ["java","-jar", "kaddem-4.0.jar"]