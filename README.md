# 📚 Library Management System — Backend

A RESTful API built with Spring Boot and PostgreSQL for managing a library's books, authors, students, and borrowing system.

## 🛠 Tech Stack
- Java 21
- Spring Boot 3.2
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/login | Admin login |
| GET | /books | Get all books |
| POST | /books | Add new book |
| PUT | /books/{id} | Update book |
| DELETE | /books/{id} | Delete book |
| PUT | /books/{bookId}/borrow/{userId} | Issue book |
| PUT | /books/{bookId}/return | Return book |
| GET | /authors | Get all authors |
| POST | /authors | Add author |
| GET | /categories | Get all categories |
| POST | /categories | Add category |
| GET | /users | Get all students |
| POST | /users | Add student |
| PUT | /users/{id} | Update student |
| DELETE | /users/{id} | Delete student |

## 🗃️ JPA Relationships
- One-to-One → User ↔ Profile
- One-to-Many → Author → Books
- Many-to-One → Book → Author
- Many-to-Many → Book ↔ Category

## ⚙️ Setup
1. Create PostgreSQL database: `librarydb`
2. Update `application.properties` with your DB credentials
3. Run: `./mvnw spring-boot:run`
