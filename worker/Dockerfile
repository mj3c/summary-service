FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} worker.jar
ENTRYPOINT ["java","-jar","/worker.jar"]