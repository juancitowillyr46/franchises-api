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

## Metodología de trabajo

El desarrollo de esta prueba técnica fue abordado bajo un enfoque ágil, descomponiendo el alcance funcional en una épica principal y múltiples historias de usuario priorizadas.

### Épica

**Gestión de franquicias, sucursales y productos**

Permitir la administración de franquicias, sus sucursales y productos asociados, incluyendo operaciones de inventario y consultas de negocio.

### Historias de Usuario

#### HU-01 Crear Franquicia
Como administrador, quiero registrar una franquicia para gestionar sus sucursales.

**Criterios de aceptación:**
- Registrar nombre de franquicia.
- Guardar información correctamente.
- Retornar confirmación de creación.

#### HU-02 Crear Sucursal
Como administrador, quiero crear una sucursal asociada a una franquicia para organizar operaciones.

**Criterios de aceptación:**
- Registrar nombre de sucursal.
- Asociar a una franquicia existente.
- Confirmar creación.

#### HU-03 Crear Producto
Como administrador, quiero registrar productos en una sucursal para controlar stock.

**Criterios de aceptación:**
- Registrar nombre del producto.
- Registrar stock inicial.
- Asociar a una sucursal existente.

#### HU-04 Actualizar Stock
Como administrador, quiero actualizar el stock de un producto para reflejar inventario real.

**Criterios de aceptación:**
- Permitir modificación de stock.
- Persistir nuevo valor.
- No permitir valores negativos.

#### HU-05 Eliminar Producto
Como administrador, quiero eliminar productos para depurar inventario.

**Criterios de aceptación:**
- Eliminar producto existente.
- Retornar respuesta exitosa.
- Controlar producto inexistente.

#### HU-06 Consultar Producto con Mayor Stock
Como administrador, quiero consultar el producto con mayor stock por sucursal de una franquicia para apoyar decisiones operativas.

**Criterios de aceptación:**
- Consultar por franquicia.
- Mostrar sucursales asociadas.
- Mostrar producto con mayor stock por cada sucursal.

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
