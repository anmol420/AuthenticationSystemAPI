# Spring Boot Authentication System

This is a secure and minimal authentication system built using **Spring Boot**, **Spring Security**, **Spring Data JPA**, **PostgreSQL**, and **Docker Compose**. It includes basic routes for user registration, login, logout, and access to a protected dashboard. It uses **cookie-based authentication** to maintain session security.

---

## Features

-  Secure login and registration
-  Cookie-based session authentication
-  JWT token handling (with custom error handling)
-  PostgreSQL database integration
-  Docker Compose setup for easy database deployment

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose

---

## Getting Started

### Prerequisites

- Java 21
- Maven
- Docker & Docker Compose

### Clone the Repository

```bash
  git clone https://github.com/your-username/spring-auth-system.git
  cd spring-auth-system
```

### Setup PostgreSQL with Docker

Use the provided `docker-compose.yml` to spin up a PostgreSQL container:

```bash
  docker-compose up -d
```

Default credentials (defined in `application.properties`):

- **Username:** `postgres`
- **Password:** `changemeinprod!`
- **DB Name:** `auth_system`

Make sure your Spring Boot app connects to this database.

### Run the Application

```bash
  ./mvnw spring-boot:run
```

---

## API Endpoints

### 1. Register

```http
POST /auth/register
```

**Request Body:**

```json
{
  "username": "your_username",
  "password": "your_password"
}
```

### 2. Login

```http
POST /auth/login
```

**Request Body:**

```json
{
  "username": "your_username",
  "password": "your_password"
}
```

**Response:** Sets a JWT cookie if successful.

### 3. Logout

```http
POST /auth/logout
```

Logs the user out and clears the cookie.

### 4. Dashboard (Protected)

```http
GET /dashboard
```

Requires authentication via cookie.

---

## Configuration

All environment settings (like DB URL, JWT expiration, etc.) can be configured in `application.properties`.

---

## Troubleshooting

- **403 Forbidden:** Check if the JWT cookie is present and valid.
- **Password Error:** Ensure password is Bcrypt encoded.
- **CORS issues:** Make sure the frontend origin is allowed in your CORS configuration.
