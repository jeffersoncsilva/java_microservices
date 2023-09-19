FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/user-api-0.0.1.jar
COPY ${JAR_FILE} user-api.jar
ENTRYPOINT ["java","-jar","/user-api.jar"]