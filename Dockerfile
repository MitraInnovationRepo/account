FROM openjdk:8-jdk-alpine

EXPOSE 8080

RUN mkdir /app
COPY build/libs/*.jar /app/spring-app.jar

ENTRYPOINT ["java","-jar","/app/spring-app.jar","--spring.config.additional-location=/config/application.properties"]