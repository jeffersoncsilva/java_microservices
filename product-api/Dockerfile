FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/Product-API-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} product-api.jar
ENTRYPOINT ["java","-jar","/product-api.jar"]