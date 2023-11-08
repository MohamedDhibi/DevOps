# Use an OpenJDK Runtime as a parent image
FROM openjdk:11
# Copy the executable into the container at /app
ADD target/*.jar app.jar
# Make port 8089 available to the world outside this container
EXPOSE 8089
# Run app.jar when the container launches
CMD ["java", "-jar", "app.jar"] 
#CMD mvn spring-boot:run


