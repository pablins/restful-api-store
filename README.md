# Restful API Store

[![Java Version](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.4.2-green)](https://spring.io/projects/spring-boot)

Este proyecto es una **API RESTful** desarrollada con **Spring Boot 3.4.2** que permite gestionar tiendas (stores) en una base de datos. Proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una entidad `Store`.

## Tabla de Contenidos

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Características Principales](#características-principales)
3. [Estructura del Proyecto](#estructura-del-proyecto)
4. [Configuración del Proyecto](#configuración-del-proyecto)
5. [Cómo Ejecutar el Proyecto](#cómo-ejecutar-el-proyecto)
6. [Endpoints Disponibles](#endpoints-disponibles)
7. [Validaciones](#validaciones)
8. [Errores Personalizados](#errores-personalizados)
9. [Dependencias](#dependencias)
10. [Contribuciones](#contribuciones)
11. [Licencia](#licencia)

---

## Descripción del Proyecto

Este proyecto implementa una API RESTful para gestionar tiendas (`Store`). La aplicación utiliza **Spring Boot** como framework principal, **JPA** para la persistencia de datos y **PostgreSQL** o **MySQL** como bases de datos según el perfil activo.

La API permite realizar operaciones CRUD sobre la entidad `Store`, incluyendo búsquedas por nombre, ID y otras funcionalidades adicionales.

---

## Características Principales

- **CRUD Completo**: Crear, leer, actualizar y eliminar tiendas.
- **Búsquedas Avanzadas**: Búsqueda por nombre, búsqueda insensible a mayúsculas/minúsculas y consultas personalizadas con JPQL.
- **Validaciones**: Validaciones básicas utilizando anotaciones de **Hibernate Validator**.
- **Manejo de Errores**: Manejo centralizado usando `@ControllerAdvice` de errores con mensajes personalizados.
- **Perfiles de Configuración**: Soporte para múltiples entornos (`dev`, `qa`, `prd`, `prd-mysql`) mediante archivos YAML.

---

## Estructura del Proyecto

El proyecto sigue una arquitectura basada en capas:

```bash
src/
├── main/
│ ├── java/com/pablins/restful_api/
│ │ ├── controller/                       # Controladores REST
│ │ ├── entity/                           # Entidades JPA
│ │ ├── error/                            # Clases de manejo de errores
│ │ ├── service/                          # Capa de servicios
│ │ └── RestfullApiApplication.java       # Clase principal de Spring Boot
│ └── resources/
│ ├── application.yml                     # Configuración principal
└── test/                                 # Pruebas unitarias
```

---

## Configuración del Proyecto

### Archivo `application.yml`

El archivo `application.yml` contiene la configuración para diferentes perfiles (`dev`, `qa`, `prd`, `prd-mysql`). Asegúrate de establecer el perfil activo correctamente antes de ejecutar la aplicación.

```yaml
spring:
  profiles:
    active: prd       # Cambia esto según el entorno deseado
```

---

### Dependencias Necesarias

Asegúrate de tener las siguientes dependencias instaladas:

- **Java 21**
- **Maven**
- **PostgreSQL** o **MySQL** (según el perfil activo)

## Cómo Ejecutar el Proyecto

**1. Clonar el Repositorio:**

  ```bash
  git clone https://github.com/pablins/restful-api-store.git
  cd restful-api-store
  ```

**2. Configurar el Perfil Activo:**

  Edita el archivo `application.yml` y establece el perfil activo (`dev`, `qa`, `prd`, `prd-mysql`).

**3. Construir el Proyecto:**
   
  ```bash
  mvn clean install
  ```

**4. Ejecutar la Aplicación:**
   
  ```bash
  mvn spring-boot:run
  ```

  En caso de querer usar un perfil diferente al establecido en el `application.yml` podrias realizar el siguiente script:

  ```bash
  java -jar restful-api-1.0.0.jar --spring.profiles.active=prd
  ```

**5. Acceder a la API:**
   
  La API estará disponible en `http://localhost:8080/api/store`

## Endpoints Disponibles


|MÉTODO HTTP | ENDPOINT | DESCRIPCIÓN |
|:---: |:--- |:--- |
| `GET` | `/api/store/findAll` | Lista todas las tiendas |
| `GET` | `/api/store/findByName/{name}` | Busca una tienda por nombre |
| `GET` | `/api/store/findByNameIgnoreCase/{name}` | Busca una tienda por nombre (insensible a mayúsculas/minúsculas) |
| `GET` | `/api/store/findById/{id}` | Busca una tienda por ID |
| `POST` | `/api/store/save` | Crea una nueva tienda |
| `PUT` | `/api/store/update/{id}` | Actualiza una tienda existente |
| `DELETE` | `/api/store/delete/{id}` | Elimina una tienda |

---

## Validaciones

La entidad `Store` incluye validaciones para garantizar la integridad de los datos:

- **Nombre (`name`)** : No puede estar vacío.
- **Código (`code`)** : Debe tener entre 4 y 10 caracteres.

---

## Errores Personalizados

La clase `RestResponseEntityExceptionHandler` maneja errores globales, como cuando no se encuentra una tienda (`StoreNotFoundException`), devolviendo respuestas JSON claras y consistentes.

## Dependencias

Las principales dependencias del proyecto son:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Hibernate Validator
- PostgreSQL Driver / MySQL Driver
- H2
- Lombok

## Contribuciones
Si deseas contribuir al proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m "Añadir nueva funcionalidad"`).
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia
Este proyecto está bajo la licencia **MIT** . Consulta el archivo `LICENSE` para más detalles.
