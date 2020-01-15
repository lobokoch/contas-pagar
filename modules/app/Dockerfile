FROM adoptopenjdk/openjdk8-openj9:latest
VOLUME /tmp
COPY target/*.jar financeiro-contaspagar-app.jar
ENTRYPOINT ["java", "-Xshareclasses:name=kerubin", "-jar", "/financeiro-contaspagar-app.jar"]