# Tienda de Videojuegos API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

API RESTful para la gestión integral de una tienda de videojuegos. Desarrollada en **Java con Spring Boot**, el proyecto implementa principios de **Arquitectura Hexagonal (Puertos y Adaptadores)** y **Domain-Driven Design (DDD)** para separar claramente la lógica de negocio de la infraestructura y las interfaces de red.

### Arquitectura de la solución

El código está organizado de manera modular para garantizar escalabilidad, bajo acoplamiento y facilidad de mantenimiento:

```text
TiendaVideojuegos/src/main/java/mx/edu/tecdesoftware/Venus/TiendaVideojuegos
├── domain/              Lógica core: Modelos (Client, Console, Videogame, Purchase), DTOs y puertos de Servicios/Repositorios.
├── persistence/         Infraestructura de datos: Entidades JPA, repositorios CRUD y Mappers de conversión.
├── security/            Capa de seguridad: Configuración de Spring Security, filtros JWT y utilidades de autenticación.
└── web/controller/      Capa de presentación: Controladores REST (Endpoints) que exponen la API hacia el exterior.
```

## Manejo y persistencia de datos

El proyecto utiliza **Spring Data JPA** para gestionar la persistencia de información en la base de datos y el patrón **Data Mapper**, implementado mediante la carpeta `mapper/`, para realizar una separación entre las entidades de persistencia y los modelos del dominio.

Esta estructura permite mantener las reglas de negocio independientes de la estructura específica utilizada por la base de datos.

| Dominio (Negocio) | Entidad JPA (Datos) | Repositorio CRUD           | Mapper            |
| ----------------- | ------------------- | -------------------------- | ----------------- |
| `Client`          | `Cliente`           | `ClienteCrudRepository`    | `ClientMapper`    |
| `Console`         | `Consola`           | `ConsolaCrudRepository`    | `ConsoleMapper`   |
| `Videogame`       | `Videojuego`        | `VideojuegoCrudRepository` | `VideogameMapper` |
| `Purchase`        | `Compra`            | `CompraCrudRepository`     | `PurchaseMapper`  |

## Seguridad y autenticación

La API está protegida mediante **JSON Web Tokens (JWT)**, permitiendo implementar un esquema de autenticación **stateless**.

### Componentes principales

* **`AuthController`**: Gestiona las solicitudes de inicio de sesión mediante `LoginRequest` y genera una respuesta con un token válido a través de `AuthResponse`.

* **`JwtFilter`**: Intercepta las solicitudes HTTP entrantes para comprobar la existencia y validez del token JWT antes de permitir el acceso a los recursos protegidos.

* **`JwtUtil`**: Se encarga de las operaciones relacionadas con los tokens, incluyendo su validación y la extracción de información del usuario.

* **`CustomUserDetails`**: Proporciona los detalles del usuario autenticado que serán utilizados por Spring Security.

Los mecanismos de autenticación protegen los diferentes controladores de la API, incluyendo:

* `ClientController`
* `PurchaseController`
* `ConsoleController`
* `VideogameController`
* Otros endpoints que requieran autenticación.

## Cómo ejecutar el proyecto en local

El proyecto utiliza **Gradle Wrapper**, por lo que no es necesario instalar Gradle manualmente en el equipo.

### Requisitos

Antes de ejecutar el proyecto, es necesario contar con:

* **Java 17** o una versión superior.
* Una **base de datos relacional** configurada de acuerdo con los parámetros definidos en `application.properties` o `application.yml`.
* Las credenciales y datos de conexión correspondientes a la base de datos configurados correctamente.

### Ejecución desde la terminal en Linux/macOS

1. Abre una terminal y navega hasta la carpeta raíz del proyecto.

2. Si es la primera ejecución, proporciona permisos de ejecución al Gradle Wrapper:

   ```bash
   chmod +x gradlew
   ```

3. Compila y ejecuta la aplicación:

   ```bash
   ./gradlew bootRun
   ```

### Ejecución desde la terminal en Windows

En Windows, utiliza el archivo `gradlew.bat`:

```bat
gradlew.bat bootRun
```

Una vez iniciada correctamente la aplicación, la API estará disponible localmente. Por defecto, Spring Boot utiliza el puerto `8080`:

```text
http://localhost:8080
```

## Pruebas

El proyecto cuenta con un entorno de pruebas ubicado en:

```text
src/test/
```

Para ejecutar las pruebas automatizadas mediante Gradle, utiliza los siguientes comandos.

### Linux/macOS

```bash
./gradlew test
```

### Windows

```bat
gradlew.bat test
```

Este comando ejecuta las pruebas configuradas en el proyecto, incluyendo las pruebas **unitarias y de integración** disponibles.

## Registro de características principales

### Arquitectura limpia

Se mantiene una separación entre las principales capas de la aplicación:

```text
web
│
├── Presentación y controladores
│
domain
│
├── Reglas y modelos de negocio
│
persistence
│
└── Acceso y persistencia de datos
```

Esta organización facilita el mantenimiento del código y reduce el acoplamiento entre las diferentes partes de la aplicación.

### Seguridad avanzada

Se implementó **Spring Security** utilizando un modelo de autenticación **stateless** basado en JWT.

Cada solicitud a un recurso protegido debe incluir un token válido para poder acceder al endpoint correspondiente.

### DTOs y Mappers

Se utilizan **DTOs (Data Transfer Objects)** para evitar exponer directamente la estructura interna de las entidades de la base de datos.

Los **Mappers** se encargan de convertir la información entre las entidades de persistencia y los modelos utilizados por el dominio o la capa de presentación.

### Controladores modulares

La API cuenta con controladores independientes para organizar las diferentes funcionalidades del sistema:

* **Autenticación**
* **Clientes**
* **Consolas**
* **Videojuegos**
* **Compras**

Esta separación permite mantener los endpoints organizados y facilita la incorporación de nuevas funcionalidades al proyecto.
