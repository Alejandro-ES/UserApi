# API de Registro de Usuarios

Este proyecto es una API RESTful desarrollada con **Spring Boot** que permite registrar usuarios con validaciones, generación de token JWT, encriptación de contraseña y persistencia en una base de datos en memoria (H2).

## 📮 Características

- Registro de nuevos usuarios con validaciones estrictas.
- Encriptación de contraseña con `BCryptPasswordEncoder`.
- Generación automática de token JWT al momento de registro.
- Almacenamiento de usuarios y sus teléfonos asociados.
- Base de datos en memoria (H2) para pruebas.
- Validaciones con mensajes personalizados.
- Swagger/OpenAPI para documentación interactiva.
- Tests unitarios y de validación (cobertura cercana al 100%).

---

## 🚀 Tecnologías utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- MapStruct
- JWT (JJWT)
- Swagger/OpenAPI
- JUnit & Mockito

---

## 📦 Requisitos previos

- Java 17 o superior
- Maven 3.x

---

## ▶️ Cómo correr la app

1. **Clonar el repositorio**
```bash
git clone https://github.com/Alejandro-ES/UserApi.git
```

2. **Configurar variables necesarias**

Para configurar la clave secreta JWT:
- Renombrá el archivo `.env-example` a `.env`.
- Dentro del archivo `.env` agregá la clave secreta:


```properties
JWT_SECRET=tu_clave_secreta_generada
```

> 💡 Podés generar usar este secret de ejemplo:
> `JWT_SECRET=Z3Z2aEZydDQ0NTMwY2FkcXZpaUpKNzYxYnZkNDc=`

3. **Correr la aplicación**
```bash
mvn clean install
mvn spring-boot:run
```

o si utilizas Intellij ejecutar la clase `Application`

4. **Acceder al Swagger**
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📮 Probar el endpoint de registro

### Endpoint

```
POST /users
```

### Request Body

```json
{
  "name": "Fulanito",
  "email": "fulanito@example.com",
  "password": "Password1@",
  "phones": [
    {
      "number": "123456789",
      "citycode": "11",
      "contrycode": "54"
    }
  ]
}
```

### Respuesta esperada

```json
{
  "id": "95cac40e-31bc-4c62-a57b-9e0ff2b4c6ad",
  "created": "2025-04-23 21:24:35",
  "modified": "2025-04-23 21:24:35",
  "last_login": "2025-04-23 21:24:35",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmdWxh9...",
  "isactive": true
}
```

---

## 🔒 Seguridad

Este proyecto utiliza JWT para la autenticación, sin embargo, la validación de token aún no está implementada (solo se genera al registrar).

---

## 🧠 Autor

Alejandro Ezequiel Nuñez  
[LinkedIn](https://www.linkedin.com/in/alejandro-ezequiel-nuñez)

---

## 📝 Diseño

El diseño se encuentra en el archivo diseño.png