FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app

# Copia el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descarga las dependencias
RUN mvn dependency:go-offline -B

# Copia el código fuente
COPY src ./src

# Compila y empaqueta el proyecto con Maven
RUN mvn package -DskipTests

# Configura el contenedor final con la imagen de Java y el artefacto compilado
FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

# Copia el artefacto compilado desde el build stage anterior
COPY --from=build /app/target/proyectoRegistro-0.0.1-SNAPSHOT.war app.jar

# Define el comando de inicio para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
