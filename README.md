# Franchises API

## Descripción

Este proyecto consiste en el desarrollo de una API REST para la gestión de franquicias, sucursales y productos, permitiendo operaciones CRUD y consultas de negocio relacionadas con el stock.

El sistema está diseñado como un monolito modular utilizando Spring Boot, con persistencia en MySQL y ejecución mediante Docker.

## Características principales

- Crear franquicias
- Crear sucursales dentro de una franquicia
- Crear productos dentro de una sucursal
- Eliminar productos
- Actualizar stock de productos
- Consultar el producto con mayor stock por sucursal dentro de una franquicia

## Tecnologías utilizadas

- Java 25
- Spring Boot 3.5.14
- Spring Data JPA
- MySQL 8
- Docker Compose
- Maven

## Arquitectura del proyecto

El proyecto sigue una arquitectura por capas:

- **Controller**: expone los endpoints REST
- **Service**: contiene la lógica de negocio
- **Repository**: acceso a datos con Spring Data JPA
- **Entity**: modelo de dominio persistente

## Modelo de datos

El sistema está compuesto por las siguientes entidades:

- Franchise (Franquicia)
- Branch (Sucursal)
- Product (Producto)

Relaciones:
- Una franquicia tiene muchas sucursales
- Una sucursal tiene muchos productos

## Cómo ejecutar en local

### Requisitos

- Java 25
- Docker Desktop

### Pasos

1. docker compose up -d
2. ./mvnw spring-boot:run

## Variables de configuración

La aplicación está configurada para ejecutarse en entorno local.

### API

- Puerto: `8081`
- URL: `http://localhost:8081`

### Base de datos

- Base de datos: `franchises_db`
- Host: `localhost`
- Puerto: `3306`

## Credenciales de acceso a la base de datos

- Usuario: `franchise_user`
- Contraseña: `jCR7D53n&3]z`

Importante: Estas credenciales son utilizadas únicamente para entorno de desarrollo local.
En entornos productivos deben ser gestionadas mediante variables de entorno o vaults de secretos.
