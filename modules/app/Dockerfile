FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar financeiro-contaspagar-app.jar
ENTRYPOINT ["java","-jar","/financeiro-contaspagar-app.jar"]