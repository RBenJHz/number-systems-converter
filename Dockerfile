# Usar una imagen base de Java
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y el directorio de código fuente
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn ./.mvn

# Dar permisos de ejecución al script mvnw
RUN chmod +x mvnw

# Descargar dependencias (Maven)
RUN ./mvnw dependency:go-offline -B

# Compilar la aplicación
RUN ./mvnw package -DskipTests

# Copiar el archivo JAR generado al contenedor
COPY target/numeric-converter-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]