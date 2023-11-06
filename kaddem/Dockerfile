FROM openjdk:8-jdk-alpine
EXPOSE 8080

ADD target/kaddem-2.0.jar kaddem-2.0.jar

ENTRYPOINT ["java","-jar","/kaddem-2.0.jar"]