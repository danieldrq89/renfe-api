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