# Usa una imagen base de OpenJDK para Java 17 en Alpine Linux
FROM openjdk:17-alpine as builder

# Establece el directorio de trabajo
WORKDIR /app

# Copia el Maven Wrapper (mvnw) y los archivos de compilación necesarios (pom.xml y src)
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn

# Hace ejecutable el Maven Wrapper
RUN chmod +x mvnw

# Construye la aplicación sin empaquetarla
RUN ./mvnw clean package -DskipTests

# Usa una nueva imagen base para el entorno de ejecución
FROM openjdk:17-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia solo el archivo JAR construido desde la imagen de compilación
COPY --from=builder /app/target/facturas-1.0.0.jar app.jar

# Expone el puerto en el que se ejecuta tu aplicación Spring Boot
EXPOSE 8585

# Comando para ejecutar la aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]


#docker-compose up -d --build myapp