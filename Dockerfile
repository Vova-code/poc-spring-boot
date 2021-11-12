#
#   Build state
#
FROM maven:3.8.1-adoptopenjdk-16 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean package

#
#   Package state
#
FROM openjdk:17.0.1
MAINTAINER vova-code
WORKDIR /opt/app
ARG JAR_FILE=mongodb-demo-0.0.1-SNAPSHOT.jar
COPY --from=maven /usr/src/app/target/$JAR_FILE /opt/app/
EXPOSE 8888:8080
ENTRYPOINT ["java","-jar","mongodb-demo-0.0.1-SNAPSHOT.jar"]