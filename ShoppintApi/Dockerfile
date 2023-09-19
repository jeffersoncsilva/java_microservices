FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/ShoppintApi-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} user-api.jar
ENTRYPOINT ["java","-jar","/user-api.jar"]