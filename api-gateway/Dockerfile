FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gateway-api.jar
ENTRYPOINT ["java","-jar","/gateway-api.jar"]