FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} web.jar
ENTRYPOINT ["java","-jar","/web.jar"]