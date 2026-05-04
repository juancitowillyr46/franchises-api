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
Como administrador, quiero registrar productos en el catálogo para luego ofertarlos en sucursales.

**Criterios de aceptación:**
- Registrar nombre del producto.
- Guardar el producto en el catálogo.
- Retornar confirmación de creación.

#### HU-04 Actualizar Stock
Como administrador, quiero actualizar el stock de un producto ofertado en una sucursal para reflejar inventario real.

**Criterios de aceptación:**
- Permitir modificación de stock.
- Persistir nuevo valor.
- No permitir valores negativos.

#### HU-05 Eliminar Producto
Como administrador, quiero eliminar productos del catálogo o retirarlos de una sucursal para depurar inventario.

**Criterios de aceptación:**
- Eliminar producto existente del catálogo o de la sucursal según el contexto.
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
- Crear sucursal dentro de una franquicia
- Consultar todas las franquicias
- Consultar franquicia por ID
- Actualizar franquicia
- Validación de datos de entrada
- Manejo centralizado de errores
- Respuestas limpias mediante DTOs

#### Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/franchises` | Crear franquicia |
| POST | `/api/franchises/{id}/branches` | Crear sucursal dentro de una franquicia |
| GET | `/api/franchises` | Listar franquicias |
| GET | `/api/franchises/{id}` | Obtener franquicia por ID |
| PUT | `/api/franchises/{id}` | Actualizar franquicia |

> Las respuestas exitosas se entregan con el wrapper estándar `StandardResponse`, con los campos `success`, `message` y `data`.

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

- Crear producto dentro de una sucursal
- Consultar todas las sucursales
- Consultar sucursal por ID
- Actualizar nombre de sucursal
- Validación de datos de entrada
- Manejo centralizado de errores
- Respuestas limpias mediante DTOs

#### Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/branches` | Listar sucursales |
| GET | `/api/branches/{id}` | Obtener sucursal por ID |
| PUT | `/api/branches/{id}` | Actualizar sucursal |
| POST | `/api/branches/{id}/products` | Crear producto dentro de una sucursal |

> Las respuestas exitosas se entregan con el wrapper estándar `StandardResponse`, con los campos `success`, `message` y `data`.

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

Permite la administración del catálogo de productos.

#### Funcionalidades implementadas

- Consultar todos los productos del catálogo
- Crear producto en el catálogo
- Consultar producto por ID
- Actualizar nombre de producto
- Eliminar producto
- Validación de datos de entrada
- Manejo centralizado de errores
- Respuestas limpias mediante DTOs

#### Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/products` | Crear producto en el catálogo |
| GET | `/api/products` | Listar productos |
| GET | `/api/products/{id}` | Obtener producto por ID |
| PUT | `/api/products/{id}` | Actualizar nombre de producto |
| DELETE | `/api/products/{id}` | Eliminar producto |

> Las respuestas exitosas se entregan con el wrapper estándar `StandardResponse`, con los campos `success`, `message` y `data`.

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
- La consulta se resuelve en repositorio con JPQL directo para evitar navegación en memoria y reducir riesgo de N+1.
- La tabla `products` representa el catálogo de productos.
- La tabla `branch_products` representa el inventario por sucursal y guarda la relación `branch_id` + `product_id` con el stock de esa sucursal.

### Contratos de API

- `POST /api/franchises` crea una franquicia con `{ "name": "..." }`.
- `POST /api/franchises/{id}/branches` crea una sucursal con `{ "name": "..." }`.
- `POST /api/products` crea un producto en el catálogo con `{ "name": "..." }`.
- `POST /api/branches/{id}/products` registra un producto existente en la sucursal con `{ "productId": 1, "stock": 0 }`.
- `PUT /api/franchises/{id}`, `PUT /api/branches/{id}` y `PUT /api/products/{id}` actualizan el nombre.
- `PATCH /api/branches/{branchId}/products/{productId}/stock` actualiza el stock de la relación sucursal-producto con `{ "stock": 0 }`.
- `DELETE /api/products/{id}` elimina un producto del catálogo y limpia sus relaciones con sucursales.
- `DELETE /api/branches/{branchId}/products/{productId}` elimina la relación sucursal-producto.
- `GET /api/franchises`, `GET /api/branches` y `GET /api/products` devuelven listas completas dentro de `StandardResponse`.
- Swagger UI queda disponible en `/swagger-ui.html` cuando la aplicación está corriendo.

### Ejemplos de intercambio

#### Crear franquicia

Request:
```json
{
  "name": "Franchise 1"
}
```

Response:
```json
{
  "success": true,
  "message": "Franchise created successfully",
  "data": {
    "id": 1,
    "name": "Franchise 1"
  }
}
```

#### Crear sucursal en una franquicia

Request:
```json
{
  "name": "Branch 1"
}
```

Response:
```json
{
  "success": true,
  "message": "Branch created successfully",
  "data": {
    "id": 1,
    "name": "Branch 1",
    "franchiseId": 1
  }
}
```

#### Crear producto en catálogo

Request:
```json
{
  "name": "Jabon A"
}
```

Response:
```json
{
  "success": true,
  "message": "Product created successfully",
  "data": {
    "id": 1,
    "name": "Jabon A"
  }
}
```

#### Asignar producto a sucursal

Request:
```json
{
  "productId": 1,
  "stock": 10
}
```

Response:
```json
{
  "success": true,
  "message": "Product assigned successfully",
  "data": {
    "id": 1,
    "branchId": 1,
    "productId": 1,
    "productName": "Jabon A",
    "stock": 10
  }
}
```

#### Actualizar stock de sucursal

Request:
```json
{
  "stock": 10
}
```

Response:
```json
{
  "success": true,
  "message": "Branch product stock updated successfully",
  "data": {
    "id": 1,
    "branchId": 1,
    "productId": 1,
    "productName": "Jabon A",
    "stock": 10
  }
}
```

### Ejemplos de errores

#### 400 Bad Request

```json
{
  "timestamp": "2026-05-03T19:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Franchise name is required",
  "path": "/api/franchises"
}
```

#### 404 Not Found

```json
{
  "timestamp": "2026-05-03T19:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Franchise not found",
  "path": "/api/franchises/999"
}
```

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
- Product (catálogo de producto)

Relaciones:
- Una franquicia tiene muchas sucursales
- Una sucursal tiene muchos productos ofertados
- La relación sucursal-producto se guarda en `branch_products`

### Escenario de ejemplo

Para una franquicia nacional como Éxito:

- `Local 1`
  - Jabón A: stock 10
  - Detergente A: stock 5
- `Local 2`
  - Jabón A: stock 0
  - Detergente A: stock 10

En este escenario:

- cada sucursal maneja su propio stock
- el mismo producto puede existir en varias sucursales
- la combinación sucursal + producto no se repite
- la eliminación de un producto afecta solo su registro en `branch_products`
- el top stock por sucursal devuelve:
  - `Local 1 -> Jabón A (10)`
  - `Local 2 -> Detergente A (10)`

## Cómo ejecutar en local

### Requisitos

- Java 25
- Docker Desktop

### Pasos

1. Levantar MySQL con `docker compose up -d mysql`.
2. Ejecutar la app con `./mvnw spring-boot:run`.
3. Abrir `http://localhost:8081/swagger-ui.html` para validar la API.

### Contenedorización

La solución también puede ejecutarse completamente en Docker:

1. Construir y levantar los contenedores con `docker compose up --build`.
2. Verificar que ambos servicios estén arriba con `docker compose ps`.
3. La base de datos se expone como `mysql`.
4. La API se expone en `http://localhost:8081`.
5. Abrir `http://localhost:8081/swagger-ui.html` para validar la documentación interactiva.
6. El perfil usado dentro del contenedor es `docker`, mientras que el modo local sigue usando `local` y el perfil de nube real sigue siendo `prod`.

Archivos involucrados:

- `Dockerfile`
- `docker-compose.yml`
- `application-docker.yml`
- `.dockerignore`

### Configuración por entornos

La aplicación usa perfiles de Spring Boot para separar la configuración local de la configuración de cloud:

- `application.yml`: configuración base común.
- `application-local.yml`: configuración para desarrollo local.
- `application-docker.yml`: configuración para ejecutar la API con Docker Compose.
- `application-prod.yml`: configuración para despliegue en cloud.

Por defecto se usa el perfil `local`.

### Respuestas estándar

Las respuestas exitosas usan el wrapper `StandardResponse` con esta forma general:

```json
{
  "success": true,
  "message": "Operation completed successfully",
  "data": {}
}
```

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

### Despliegue local

1. Levantar MySQL con `docker compose up -d mysql`.
2. Ejecutar la app con `./mvnw spring-boot:run`.
3. Abrir `http://localhost:8081/swagger-ui.html` para validar la API.

### Despliegue con Docker

1. Levantar la base de datos y la API con `docker compose up --build`.
2. Confirmar el estado de los contenedores con `docker compose ps`.
3. Abrir `http://localhost:8081/swagger-ui.html` para consumir la API desde Swagger.
4. Detener la solución con `docker compose down` cuando termines.

### Despliegue en cloud

1. Compilar la aplicación.
2. Ejecutar con el perfil `prod`.
3. Proveer `DB_URL`, `DB_USERNAME` y `DB_PASSWORD` como variables de entorno del entorno cloud.
4. Apuntar la aplicación a una base de datos MySQL administrada en la nube.

## Variables de configuración

La aplicación está configurada para ejecutarse en entorno local por defecto, y puede adaptarse a cloud mediante perfiles y variables de entorno.

### API

- Puerto: `8081`
- URL: `http://localhost:8081`

### Base de datos

- Base de datos: `franchises_db`
- Host: `localhost`
- Puerto: `3306`
- En cloud, estos valores salen de las variables de entorno del perfil `prod`.

## Credenciales de acceso a la base de datos

Importante: Estas credenciales son utilizadas únicamente para entorno de desarrollo local.
En entornos productivos deben ser gestionadas mediante variables de entorno o vaults de secretos.
