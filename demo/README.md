# 🔐 Spring Boot Authentication & Authorization System

A complete **Authentication and Authorization project** built using **Spring Boot**, **Spring Security**, **Thymeleaf**, **Spring Data JPA**, and **MySQL**.

This project implements user registration, secure login, role-based authorization, admin user management, password reset using token-based verification, and custom error pages.

### Before starting project implement your application.properties file has name in project is eg-application.properties rename that into application properties and implement that .

---

## 🚀 Features

### 🔑 Authentication

* User Registration
* User Login
* Logout
* Email and Password based authentication
* BCrypt password encryption
* Custom login page
* Login failure handling

### 👥 Role-Based Authorization

The application supports two roles:

* `USER`
* `ADMIN`

Access is controlled using **Spring Security**.

| Role  | Access                            |
| ----- | --------------------------------- |
| USER  | User Dashboard                    |
| ADMIN | Admin Dashboard + User Management |

---

### 👨‍💼 Admin Features

Admin users can:

* View Admin Dashboard
* View all registered users
* Add new users
* Activate / Deactivate users
* Delete users
* Assign `USER` role to newly created users

---

### 👤 User Features

Normal users can:

* Register an account
* Login securely
* Access User Dashboard
* Logout

---

### 🔄 Password Reset

The project includes a basic password reset workflow:

1. User enters their registered email.
2. System generates a unique reset token.
3. Token is stored in the database.
4. Token expires after **10 minutes**.
5. User enters the token and new password.
6. Password is encrypted using BCrypt.
7. Reset token is removed after successful password change.

> **Note:** The current implementation displays the generated reset token on the page for development/testing purposes. In a production application, the token should be sent through a secure email service.

---

## 🛠️ Technologies Used

* **Java 21**
* **Spring Boot 3.5.16**
* **Spring Security 6**
* **Spring Data JPA**
* **Thymeleaf**
* **Thymeleaf Spring Security Extras**
* **MySQL**
* **Lombok**
* **Maven**
* **HTML5**
* **CSS3**

---

## 📂 Project Structure

```text
src
└── main
    ├── java
    │   └── SpringBoot_Auth
    │       └── demo
    │           ├── Config
    │           │   └── SecurityConfig.java
    │           │
    │           ├── Controller
    │           │   ├── AdminController.java
    │           │   ├── AuthController.java
    │           │   ├── HomeController.java
    │           │   └── UserController.java
    │           │
    │           ├── Entity
    │           │   ├── Role.java
    │           │   └── User.java
    │           │
    │           ├── Repository
    │           │   └── UserRepo.java
    │           │
    │           ├── Service
    │           │   ├── CustomSuccessHandler.java
    │           │   ├── CustomUserDetailsService.java
    │           │   └── UserService.java
    │           │
    │           └── DemoApplication.java
    │
    └── resources
        ├── static
        │   └── css
        │       ├── style.css
        │       └── styles.css
        │
        └── templates
            ├── admin
            │   ├── add-user.html
            │   └── users.html
            │
            ├── error
            │   ├── 403.html
            │   └── 404.html
            │
            ├── adminpage.html
            ├── forgot-password.html
            ├── home.html
            ├── login.html
            ├── register.html
            ├── reset-password.html
            └── userpage.html
```

---

## 🔐 Spring Security Configuration

The project uses Spring Security to protect application endpoints.

### Public Endpoints

```text
/home/**
/login
/css/**
/js/**
/error
/register-user
```

### User Endpoints

```text
/user/**
```

Accessible by:

```text
USER
ADMIN
```

### Admin Endpoints

```text
/admin/**
```

Accessible only by:

```text
ADMIN
```

All other endpoints require authentication.

---

## 🔀 Login Flow

After successful login, users are redirected according to their role.

```text
                    Login
                      │
                      ▼
              Spring Security
                      │
                      ▼
              Check User Role
                 /        \
                /          \
             ADMIN         USER
               │             │
               ▼             ▼
        Admin Dashboard   User Dashboard
```

This redirection is handled by:

```text
CustomSuccessHandler.java
```

---

## 🔒 Password Security

Passwords are **not stored as plain text**.

The project uses:

```java
BCryptPasswordEncoder
```

Example:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

During registration:

```text
Plain Password
      ↓
BCrypt Encoder
      ↓
Encrypted Password
      ↓
MySQL Database
```

---

## 🗄️ Database Configuration

Create a MySQL database before running the application.

Example:

```sql
CREATE DATABASE springboot_auth;
```

Then configure your database credentials in:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.application.name=SpringBoot-Auth

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/springboot_auth
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Never upload your real database password or other secrets to GitHub.

---

## ▶️ How to Run the Project

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### 2. Open the Project

Open the project in:

* IntelliJ IDEA
* Eclipse
* VS Code
* Spring Tool Suite

### 3. Configure MySQL

Create the database and update your database credentials.

### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run:

```text
DemoApplication.java
```

from your IDE.

---

## 🌐 Application URLs

After starting the application:

### Home

```text
http://localhost:8080/home/home-page
```

### Login

```text
http://localhost:8080/login
```

### Register

```text
http://localhost:8080/register-user
```

### Forgot Password

```text
http://localhost:8080/home/forgot-password
```

### Reset Password

```text
http://localhost:8080/home/reset-password
```

### User Dashboard

```text
http://localhost:8080/user/user-home
```

### Admin Dashboard

```text
http://localhost:8080/admin/admin-home
```

### Admin User Management

```text
http://localhost:8080/admin/users
```

---

## 📡 Important Endpoints

| Method | Endpoint                          | Description          |
| ------ | --------------------------------- | -------------------- |
| GET    | `/login`                          | Login page           |
| GET    | `/register-user`                  | Registration page    |
| POST   | `/register-user`                  | Register user        |
| GET    | `/user/user-home`                 | User dashboard       |
| GET    | `/admin/admin-home`               | Admin dashboard      |
| GET    | `/admin/users`                    | List users           |
| GET    | `/admin/users/add`                | Add user page        |
| POST   | `/admin/users/add`                | Add user             |
| POST   | `/admin/users/{id}/toggle-status` | Enable/disable user  |
| POST   | `/admin/users/{id}/delete`        | Delete user          |
| GET    | `/home/forgot-password`           | Forgot password page |
| POST   | `/home/forgot-password`           | Generate reset token |
| GET    | `/home/reset-password`            | Reset password page  |
| POST   | `/home/reset-password`            | Change password      |
| POST   | `/logout`                         | Logout               |

---

## 🧩 Main Components

### `SecurityConfig`

Responsible for:

* Spring Security configuration
* URL authorization
* Login configuration
* Logout configuration
* Password encoder
* Role-based access control

---

### `CustomUserDetailsService`

Loads users from the database using their email.

```text
Email
 ↓
UserRepo
 ↓
MySQL
 ↓
User Details
 ↓
Spring Security
```

---

### `CustomSuccessHandler`

Redirects users after successful login:

```text
ADMIN → /admin/admin-home

USER → /user/user-home
```

---

### `UserService`

Handles user creation and password encryption.

It supports:

```text
registerNewUser()
registerNewAdmin()
```

---

### `UserRepo`

Spring Data JPA repository responsible for database operations.

Important methods include:

```java
findByEmail()
findByToken()
existsByEmail()
```

---

## 🗃️ User Entity

The `User` entity contains fields such as:

```text
id
fullname
email
password
role
enabled
token
resetTokenExpiry
```

Roles are represented using:

```java
public enum Role {
    USER,
    ADMIN
}
```

---

## 🖥️ Frontend

The frontend uses:

* HTML
* CSS
* Thymeleaf

Thymeleaf templates are located inside:

```text
src/main/resources/templates
```

CSS files are located inside:

```text
src/main/resources/static/css
```

---

## ⚠️ Development Notes

This project is intended primarily for **learning and development**.

Before using it in production, consider adding:

* Email-based password reset
* Stronger password validation
* Confirm-password validation
* CSRF protection
* Input validation
* Global exception handling
* Secure environment variables
* HTTPS
* Rate limiting
* Account lockout
* Better token management
* Audit logging

---

## 🔮 Future Improvements

Possible improvements for this project:

* [ ] Email verification
* [ ] Real email-based password reset
* [ ] JWT authentication
* [ ] Refresh tokens
* [ ] User profile management
* [ ] Admin role management
* [ ] Pagination for users
* [ ] Search and filter users
* [ ] Password strength validation
* [ ] REST API integration
* [ ] Docker support
* [ ] Deployment using AWS
* [ ] PostgreSQL support

---

## 📸 Project Screens

The project contains separate Thymeleaf pages for:

* Login
* Registration
* User Dashboard
* Admin Dashboard
* User Management
* Add User
* Forgot Password
* Reset Password
* 403 Unauthorized
* 404 Not Found

---

## 👨‍💻 Author

**Keshav**

Built as a Spring Boot backend/authentication project for learning and practicing:

```text
Java
Spring Boot
Spring Security
Spring Data JPA
MySQL
Thymeleaf
MVC Architecture
Authentication & Authorization
```

---

## ⭐ Support

If you find this project useful, consider giving the repository a ⭐ on GitHub.

---

## 📄 License

This project is available for educational and personal learning purposes.
