FROM amazoncorretto:17-alpine-jdk

COPY target/proyectoRegistro-0.0.1-SNAPSHOT.war app.jar

ENTRYPOINT ["java","-jar","/app.jar"]