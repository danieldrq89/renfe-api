# Renfe Stations API - Backend Infrastructure

Solución integral para la gestión y consulta de estaciones ferroviarias. El proyecto implementa una arquitectura de microservicio que integra una API REST desarrollada en Spring Boot con una base de datos relacional MySQL, orquestada mediante contenedores Docker.

## Especificaciones Técnicas

* **Framework:** Spring Boot 3.x (Java 17)
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** MySQL 8.0
* **Infraestructura:** Docker & Docker Compose
* **Gestión de Dependencias:** Maven

## Arquitectura de Red y Conectividad

La solución utiliza una red aislada de Docker (`bridge`) para permitir la resolución de nombres DNS entre servicios. La API se comunica con el nodo de base de datos utilizando el hostname `db-renfe`, garantizando el desacoplamiento de direcciones IP estáticas.

## Implementación de Búsqueda Parcial

Se ha implementado una lógica de consulta optimizada en la capa de persistencia (Repository) para permitir búsquedas flexibles. La integración de operadores `LIKE` junto con la normalización de cadenas asegura resultados precisos independientemente del "case sensitivity" de la entrada.

## Instrucciones de Despliegue

### Requisitos previos
* Docker Engine 20.10+
* Docker Compose V2

### Ejecución del entorno
Para inicializar la infraestructura completa (compilación, creación de red y despliegue de contenedores), ejecute el siguiente comando en la raíz del proyecto:

```powershell
docker-compose up -d
```
## Roadmap y Mejoras Futuras
Para garantizar la escalabilidad y el mantenimiento del proyecto, se plantean las siguientes implementaciones técnicas:

### 1. CI/CD Pipeline (Jenkins)
Automatización del ciclo de vida del software mediante un `Jenkinsfile` que realice las siguientes etapas:
* **Build:** Compilación automatizada de la solución con Maven.
* **Test:** Ejecución de pruebas unitarias y de integración.
* **Dockerize:** Generación automática de imágenes tras cada `push` a la rama principal.
* **Push:** Actualización del repositorio en Docker Hub con tags versionados.

### 2. Seguridad y Optimización
* **Spring Security:** Implementación de autenticación JWT para proteger los endpoints de consulta.
* **Caching:** Integración de Redis para cachear las consultas a estaciones más frecuentes y reducir la carga en MySQL.
* **Healthchecks avanzados:** Mejora de la resiliencia en el orquestador para gestionar reintentos de conexión ante caídas del servicio de base de datos.

