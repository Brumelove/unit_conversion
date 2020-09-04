FROM openjdk:8-jdk-alpine

RUN apk add maven

WORKDIR /opt/
COPY . .
RUN mvn clean install -Dmaven.test.skip=true
RUN ls -lht target
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","target/converter-0.0.1-SNAPSHOT.jar"]