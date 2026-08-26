# Spring Boot Authentication Demo

A small Spring Boot application that demonstrates user registration, role persistence, and HTTP Basic authentication backed by MySQL.

## Technology Stack

- Java 21
- Spring Boot 3.5.16
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Lombok
- Maven Wrapper

## Prerequisites

- JDK 21
- MySQL Server
- A MySQL database named `-----`

Create the database before starting the application:

```sql
CREATE DATABASE ----;
```

Update `src/main/resources/application.properties` with your local database username and password. Do not commit real credentials to source control.

The application is configured to use port `7777`.

## Running the Application

From the project directory:

### Windows PowerShell

```powershell
.\mvnw.cmd spring-boot:run
```

### macOS/Linux

```bash
./mvnw spring-boot:run
```

The application is available at:

```text
http://localhost:7777
```

To build and test the project:

```bash
./mvnw clean test
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## API Endpoints

### Public endpoints

`GET /home/home-page`

Returns a home page message.

`POST /home/register-user`

Registers a user with the `USER` role. Example request body:

```json
{
  "fullname": "Jane User",
  "email": "jane@example.com",
  "password": "change-me"
}
```

`POST /home/register-admin`

Registers a user with the `ADMIN` role. Example request body:

```json
{
  "fullname": "Alex Admin",
  "email": "admin@example.com",
  "password": "change-me"
}
```

Passwords are encoded with BCrypt before they are stored.

### Authenticated endpoints

Use HTTP Basic authentication with the registered email as the username and the original password.

`GET /user/user-home`

Returns the user page message.

`GET /admin/admin-home`

Returns the admin page message.

Example with cURL:

```bash
curl -u jane@example.com:change-me http://localhost:7777/user/user-home
```

## Security Note

CSRF protection is disabled and HTTP Basic authentication is enabled for this demo. Do not expose this configuration directly to the public internet without adding HTTPS, CSRF protection where appropriate, stronger account controls, and secure secret management.

The controller paths are `/user/**` and `/admin/**`, while the role-specific matchers in `SecurityConfig` currently target `/api/v1/user/**` and `/api/v1/admin/**`. As a result, the role-specific authorization rules do not currently match these controller endpoints; requests fall through to the general authenticated rule. Align the matcher paths with the controller paths if role-based access enforcement is required.

## Project Structure

```text
src/main/java/SpringBoot_Auth/demo/
├── Config/       Security configuration
├── Controller/   HTTP endpoints
├── Entity/       User and role JPA models
├── Repository/   Spring Data repositories
└── Service/      Registration and user lookup logic
```

The default test verifies that the Spring application context loads successfully.
