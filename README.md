# 🧾 Microservicio de Gestión de Tareas (Spring Boot + JWT + MySQL)

Este proyecto es un microservicio RESTful desarrollado con **Spring Boot** que permite realizar operaciones CRUD sobre tareas. Cada tarea incluye un título, descripción y estado (pendiente, en progreso, completada). El servicio está protegido mediante **autenticación JWT** y utiliza una base de datos relacional **MySQL** para el almacenamiento de datos.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Tokens)
- MySQL (WampServer)
- JUnit 5
- Mockito
- Maven
- Git

---

## 🧠 Características del proyecto

### ✅ Funcionalidades principales
- Crear, obtener, actualizar y eliminar tareas.
- Validación de existencia de tareas.
- Seguridad con JWT (Login y acceso con token).
- Gestión de excepciones con respuestas HTTP claras.
- Pruebas unitarias con Mockito.
- Pruebas de integración de endpoints.

### 📦 Estructura del proyecto
```
com.programandoenjava.jwt
├── auth              # Registro y autenticación de usuarios
├── config            # Configuración de seguridad y JWT
├── task              # Entidad, repositorio, servicio y controlador de tareas
├── security          # Filtros y utilidades para autenticación
├── user              # Entidad, repositorio, servicio y controlador de usuarios
└── Application.java  # Clase principal
```

---

## 🔐 Seguridad

- **Login:** `/api/auth/login` (POST)  
- **Registro:** `/api/auth/register` (POST)  
- Al autenticarse, el sistema retorna un **token JWT**.
- Todos los endpoints de tareas están protegidos y requieren un token válido.
- Rol predeterminado: `ROLE_USER`

---

## 📋 Endpoints CRUD de tareas

| Método | Endpoint            | Descripción                     |
|--------|---------------------|---------------------------------|
| GET    | `/tareas`           | Obtener todas las tareas        |
| GET    | `/tareas/{id}`      | Obtener tarea por ID            |
| POST   | `/tareas`           | Crear nueva tarea               |
| PATCH  | `/tareas`           | Actualizar una tarea existente  |
| DELETE | `/tareas/{id}`      | Eliminar tarea por ID           |

---

## 🧪 Pruebas

- **JUnit 5 + Mockito:** Para pruebas unitarias de servicios.
- **Pruebas de integración:** Verifican endpoints completos y su comportamiento.
- **Cobertura:** Se validan casos exitosos y casos de error (tarea no encontrada, etc).

---

## 🛠️ Configuración del entorno

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/pruebaTecnicaInicial
cd pruebaTecnicaInicial
```

2. Configurar la base de datos en `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mi_basededatos
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
```

3. Ejecutar el proyecto:
```bash
mvn spring-boot:run
```

4. Importar en Postman para probar autenticación y endpoints CRUD.

---

## 🧾 Ejemplo de JSON para crear una tarea

```json
{
  "title": "Estudiar Spring Boot",
  "description": "Repasar seguridad con JWT",
  "state": "pendiente"
}
```

---

## 🧠 Autor

Desarrollado por **Benyi Uriel Cordero Sanchez**  
Proyecto académico - 2025

---

## ✅ Estado del proyecto

✔️ **Finalizado y funcional**

---
