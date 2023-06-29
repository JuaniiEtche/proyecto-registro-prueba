FROM amazoncorretto:17-alpine-jdk

COPY target/proyectoRegistro-0.0.1-SNAPSHOT.war app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]