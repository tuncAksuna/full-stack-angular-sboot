FROM openjdk:11
ADD target/springboot-backend-0.0.1-SNAPSHOT.jar app.jar
VOLUME /main-app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
