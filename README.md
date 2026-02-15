# 🎉 Event Management System (Spring Boot + JWT + Role-Based Security)

A secure Event Management & Booking REST API built using Spring Boot with JWT Authentication and Role-Based Authorization.

---

## 🚀 Features

- 🔐 JWT-based authentication
- 👤 Role-based access control (ADMIN / CUSTOMER)
- 📝 User registration & login
- 🎟 Booking creation
- 🛡 Stateless security configuration
- 🧪 Unit & Integration testing with MockMvc
- 🗂 Clean layered architecture (Controller → Service → Repository)

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- H2 / PostgreSQL
- Maven
- JUnit & MockMvc

---


---

## 🔐 Authentication Flow

1. User registers
2. User logs in with username & password
3. Server generates JWT token
4. Client sends JWT in header:
5. JWT filter validates token
6. Role-based access is enforced

---
Register and Login doesnot require any headers.

## 👥 Roles

| Role | Access |
|------|--------|
| ADMIN | Manage system |
| CUSTOMER | Create bookings |

---

## 🧪 Running Tests

```bash
mvn test

How To Run
git clone https://github.com/your-username/event-management.git
switch feature-booking
cd event-management

Run application
mvn spring-boot:run

Application runs on:
http://localhost:8080

For detailed API documentation refer
http://localhost:8080/swagger-ui.html
