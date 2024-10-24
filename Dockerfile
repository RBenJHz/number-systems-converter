# Usar una imagen base de Java
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y el directorio de Maven
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Descargar dependencias (Maven)
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación
RUN ./mvnw package -DskipTests

# Copiar el archivo JAR generado al contenedor
COPY target/numeric-converter-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
