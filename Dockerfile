FROM openjdk:8-jdk-alpine
MAINTAINER saurabh
COPY target/top_score-0.0.1-SNAPSHOT.jar top_score-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/top_score-0.0.1-SNAPSHOT.jar"]