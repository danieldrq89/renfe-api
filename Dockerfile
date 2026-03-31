# ETAPA 1: Compilación (Build)
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copiamos el pom.xml y descargamos dependencias (esto ahorra tiempo en futuros builds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos el JAR saltando los tests
COPY src ./src
RUN mvn clean package -DskipTests

# ETAPA 2: Imagen de ejecución (Runtime)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de Spring
EXPOSE 8080

# Arrancamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]