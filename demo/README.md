# Spring Boot Authentication Demo

A small Spring Boot application for learning user registration, role-based authorization, and database-backed form authentication. Users are stored in MySQL, and passwords are encoded with BCrypt before persistence.

## Stack

- Java 21
- Spring Boot 3.5.16
- Spring MVC and Thymeleaf
- Spring Security
- Spring Data JPA
- MySQL
<<<<<<< HEAD
- Lombok
- Maven Wrapper

## Requirements
=======
>>>>>>> branch/2

## Requirements
- JDK 21
- MySQL Server
<<<<<<< HEAD

Create the database configured in `src/main/resources/application.properties`:

```sql
CREATE DATABASE testing;
=======
Create the database configured in `src/main/resources/application.properties`:
Initialize your own application.properties file useing eg-application.properties file and rename them to eg-apptlication.properties -> application.properties 

```sql
CREATE DATABASE NAME OF YOUER DATABASE ;
>>>>>>> branch/2
```

Before starting the app, update the datasource URL, username, and password for your local MySQL installation. Keep real credentials out of source control; environment variables or a local, ignored properties file are safer choices.

The default server port is `7777`.
<<<<<<< HEAD

## Run Locally

From the directory containing `pom.xml`:
=======
Open [http://localhost:7777/login] after the application starts.

## Authentication Flow

1. Open `/register-user` and create a user account.
2. Sign in at `/login` with the registered email and password.
3. After a successful login, Spring Security redirects to `/home/home-page`.
4. Log out with `POST /logout`.
>>>>>>> branch/2

The login form uses `email` and `password` fields. Authentication is session-based form login, not HTTP Basic authentication.

## Routes and Roles

| Method | Route | Access | Purpose |
| --- | --- | --- | --- |
| `GET` | `/login` | Public | Display the login page |
| `GET` | `/register-user` | Public | Display the registration page |
| `POST` | `/register-user` | Public | Create a `USER` account |
| `GET` | `/home/home-page` | Public | Display the home response |
| `POST` | `/home/register-admin` | Public | Create an `ADMIN` account from a JSON request |
| `GET` | `/user/user-home` | `USER`, `ADMIN` | Display the user response |
| `GET` | `/admin/admin-home` | `ADMIN` | Display the admin response |
| `POST` | `/logout` | Authenticated | End the current session |

Create an admin account with "Postman" a request such as:

```bash
curl -X POST http://localhost:7777/home/register-admin \
  -H "Content-Type: application/json" \
  -d '{"fullname":"Alex Admin","email":"admin@example.com","password":"change-me"}'
```

<<<<<<< HEAD
Open [http://localhost:7777/login](http://localhost:7777/login) after the application starts.

Run the test suite with:

```powershell
.\mvnw.cmd clean test
```

On macOS/Linux, use `./mvnw clean test`.

## Authentication Flow

1. Open `/register-user` and create a user account.
2. Sign in at `/login` with the registered email and password.
3. After a successful login, Spring Security redirects to `/home/home-page`.
4. Log out with `POST /logout`.

The login form uses `email` and `password` fields. Authentication is session-based form login, not HTTP Basic authentication.

## Routes and Roles

| Method | Route | Access | Purpose |
| --- | --- | --- | --- |
| `GET` | `/login` | Public | Display the login page |
| `GET` | `/register-user` | Public | Display the registration page |
| `POST` | `/register-user` | Public | Create a `USER` account |
| `GET` | `/home/home-page` | Public | Display the home response |
| `POST` | `/home/register-admin` | Public | Create an `ADMIN` account from a JSON request |
| `GET` | `/user/user-home` | `USER`, `ADMIN` | Display the user response |
| `GET` | `/admin/admin-home` | `ADMIN` | Display the admin response |
| `POST` | `/logout` | Authenticated | End the current session |

Create an admin account with a request such as:

```bash
curl -X POST http://localhost:7777/home/register-admin \
  -H "Content-Type: application/json" \
  -d '{"fullname":"Alex Admin","email":"admin@example.com","password":"change-me"}'
```

## Security Notes

This is a learning project, not a production-ready authentication service. CSRF protection is disabled in the current configuration, and the admin registration endpoint is publicly accessible. Before deploying, enable CSRF protection where appropriate, restrict admin creation, use HTTPS, validate inputs, and externalize secrets.

=======
## Security Notes

This is a learning project, not a production-ready authentication service. CSRF protection is disabled in the current configuration, and the admin registration endpoint is publicly accessible. Before deploying, enable CSRF protection where appropriate, restrict admin creation, use HTTPS, validate inputs, and externalize secrets.

>>>>>>> branch/2
## Project Layout

```text
src/main/java/SpringBoot_Auth/demo/
├── Config/       Spring Security configuration
├── Controller/   Web and REST endpoints
├── Entity/       User and role JPA entities
├── Repository/   Spring Data repositories
└── Service/      Registration and user lookup services
```

The default test checks that the Spring application context loads successfully.
