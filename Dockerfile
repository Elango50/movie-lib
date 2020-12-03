FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} movie-library-0.0.1.jar
ENTRYPOINT ["java","-jar","/movie-library-0.0.1.jar"]
