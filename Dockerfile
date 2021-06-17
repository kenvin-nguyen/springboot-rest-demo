FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD

MAINTAINER Khanh Nguyen

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package



FROM openjdk:11

#ADD ./target/rest-demo-0.0.1-SNAPSHOT.jar /usr/src/rest-demo-0.0.1-SNAPSHOT.jar
COPY --from=MAVEN_BUILD ./build/target/rest-demo-0.0.1-SNAPSHOT.jar /usr/src
WORKDIR usr/src
EXPOSE 8080
ENTRYPOINT ["java","-jar","rest-demo-0.0.1-SNAPSHOT.jar"]