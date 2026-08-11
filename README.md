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

