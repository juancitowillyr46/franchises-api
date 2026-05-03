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

## Módulos implementados

### Módulo Franquicias

Permite la administración básica de franquicias mediante operaciones CRUD.

#### Funcionalidades implementadas

- Crear franquicia
- Consultar todas las franquicias
- Consultar franquicia por ID
- Actualizar franquicia
- Validación de datos de entrada
- Manejo centralizado de errores
- Respuestas limpias mediante DTOs
- Paginación en listados

#### Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/franchises` | Crear franquicia |
| GET | `/api/franchises` | Listar franquicias |
| GET | `/api/franchises/{id}` | Obtener franquicia por ID |
| PUT | `/api/franchises/{id}` | Actualizar franquicia |

> Los listados `GET` aceptan paginación con `page`, `size` y `sort` mediante `Pageable`.

#### Estructura interna del módulo

```text
franchise/
├── controller/
├── dto/
├── entity/
├── repository/
└── service/
```

### Módulo Sucursales

Permite la administración de sucursales asociadas a franquicias mediante operaciones CRUD.

#### Funcionalidades implementadas

- Consultar todas las sucursales
- Consultar sucursal por ID
- Actualizar nombre de sucursal
- Validación de datos de entrada
- Manejo centralizado de errores
- Respuestas limpias mediante DTOs
- Paginación en listados

#### Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/branches` | Crear sucursal |
| GET | `/api/branches` | Listar sucursales |
| GET | `/api/branches/{id}` | Obtener sucursal por ID |
| PUT | `/api/branches/{id}` | Actualizar sucursal |

> Los listados `GET` aceptan paginación con `page`, `size` y `sort` mediante `Pageable`.

#### Estructura interna del módulo

```text
branch/
├── controller/
├── dto/
├── entity/
├── repository/
└── service/
```

### Módulo Productos

Permite la administración de productos asociados a sucursales, incluyendo gestión de stock y operaciones CRUD.

#### Funcionalidades implementadas

- Crear producto asociado a una sucursal
- Consultar todos los productos
- Consultar producto por ID
- Actualizar nombre de producto
- Actualizar stock de producto
- Eliminar producto
- Validación de datos de entrada
- Manejo centralizado de errores
- Respuestas limpias mediante DTOs
- Paginación en listados

#### Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/products` | Crear producto |
| GET | `/api/products` | Listar productos |
| GET | `/api/products/{id}` | Obtener producto por ID |
| PUT | `/api/products/{id}` | Actualizar nombre de producto |
| PATCH | `/api/products/{id}/stock` | Actualizar stock |
| DELETE | `/api/products/{id}` | Eliminar producto |

> Los listados `GET` aceptan paginación con `page`, `size` y `sort` mediante `Pageable`.

#### Estructura interna del módulo

```text
product/
├── controller/
├── dto/
├── entity/
├── repository/
└── service/
```

### Tecnologías utilizadas

- Java 25
- Spring Boot 3.5.14
- Spring Data JPA
- MySQL 8
- Docker Compose
- Maven

### Reglas de negocio de top stock

- Si una sucursal no tiene productos, no aparece en el resultado.
- Si una sucursal tiene productos con stock `0`, el producto con menor `id` entre los de stock máximo se devuelve como top de esa sucursal.
- En caso de empate de stock, se prioriza el producto con menor `id` para mantener un resultado determinista.

## Arquitectura del proyecto

El proyecto sigue una arquitectura por capas:

- **Controller**: expone los endpoints REST
- **Service**: contiene la lógica de negocio
- **Repository**: acceso a datos con Spring Data JPA
- **Entity**: modelo de dominio persistente
- **DTO**: objetos para solicitudes y respuestas entre cliente y API

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

### Configuración por entornos

La aplicación usa perfiles de Spring Boot para separar la configuración local de la configuración de cloud:

- `application.yml`: configuración base común.
- `application-local.yml`: configuración para desarrollo local.
- `application-prod.yml`: configuración para despliegue en cloud.

Por defecto se usa el perfil `local`.

### Variables de entorno para producción

Cuando se active el perfil `prod`, la aplicación espera estas variables:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `SERVER_PORT` opcional, con valor por defecto `8081`

Ejemplo de activación:

```bash
SPRING_PROFILES_ACTIVE=prod
```

## Variables de configuración

La aplicación está configurada para ejecutarse en entorno local por defecto, y puede adaptarse a cloud mediante perfiles y variables de entorno.

### API

- Puerto: `8081`
- URL: `http://localhost:8081`

### Base de datos

- Base de datos: `franchises_db`
- Host: `localhost`
- Puerto: `3306`

## Credenciales de acceso a la base de datos

Importante: Estas credenciales son utilizadas únicamente para entorno de desarrollo local.
En entornos productivos deben ser gestionadas mediante variables de entorno o vaults de secretos.
