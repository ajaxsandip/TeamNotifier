FROM openjdk:17-jdk-alpine
EXPOSE 8080
VOLUME [ "/tmp" ]
ARG JAR_File=target/*.jar
COPY ${JAR_File} app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]