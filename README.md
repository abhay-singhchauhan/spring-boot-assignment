# 🎓 Student Management System - Backend API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%20%2F%204.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15%2B-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)

A robust, enterprise-grade **RESTful Backend API** for a **Student Management System**, designed to handle school admissions, course catalog curation, student profiling, and enrollments. Built from scratch with Spring Boot, JPA/Hibernate, PostgreSQL, and JWT-secured Role-Based Access Control (RBAC).

---

> [!IMPORTANT]
> ### 🖥️ Companion Frontend Available!
> A fully interactive, responsive **Frontend Web Application** has been built in parallel to demonstrate, visualize, and thoroughly test every single feature of this API interactively. You can log in as an Admin, register/validate as a Student, assign courses, update profiles, and manage enrollments through a polished, modern interface. 
> 
> *Please refer to the companion frontend repository (`[school-management-frontend](https://github.com/abhay-singhchauhan/spring-boot-assignment-frontend/tree/master)`) to run the client app side-by-side with this API.*

---

## 🌟 Key Features Implemented

### 🔑 Authentication & Role-Based Security
*   **Admin Login:** Secure authentication using configured credentials (`admin` / `admin123`) returning a stateless JSON Web Token (JWT).
*   **Student Validation:** Students can sign in and retrieve their JWT session by validating their **Unique Student Code** and **Date of Birth** (as required by the assignment guidelines).
*   **Stateless Security Filter Chain:** Fully secured endpoints utilizing custom JWT filters, preventing unauthorized access across admin and student boundaries.

### 🏛️ Admin Operations
1.  **Student Admission:** Add new students with details including Name, DOB, Gender, and a Unique Student Code.
    *   *Supports multi-address mapping* (Permanent, Correspondence, and Current addresses) utilizing a **One-to-Many bidirectional relationship**.
2.  **Course Management:** Create and catalog courses with Name, Description, Course Type (e.g., Full-Time/Part-Time), Duration, and Topics.
3.  **Course Assignment:** Assign one or more courses to students, managed via a clean, optimized **Many-to-Many relationship**.
4.  **Advanced Search System:**
    *   Retrieve students by Name (supports case-insensitive fuzzy matches).
    *   Retrieve all students currently assigned to a specific Course ID.

### 🎒 Student Operations
1.  **Profile Management:** Authed students can retrieve and update their personal details, including Email, Mobile Number, Father's Name, Mother's Name, and Address lists.
2.  **Course Search & Enrollment:** 
    *   Search and list courses/topics assigned to them.
    *   Search/filter their course lists by specific keywords/topics (e.g., "Java").
    *   Voluntarily leave/unregister from a specific assigned course.

---

## 🛠️ Technology Stack & Architecture

*   **Core Framework:** Spring Boot 4.0.6
*   **Security:** Spring Security, JWT (io.jsonwebtoken version 0.12.6)
*   **Database & ORM:** PostgreSQL Driver, Spring Data JPA (Hibernate)
*   **Data Models & Mapping:** Lombok (boilerplate reduction), clean Data Transfer Objects (DTO) to separate persistence layers from API presentation models.
*   **Validation:** Jakarta Validation (`@NotNull`, `@NotBlank`, `@Size`, `@Email`, etc.) for strict incoming data integrity.
*   **Global Exception Handling:** Specialized Controller Advice returning standardized, user-friendly JSON error payloads.

---

## 📁 Project Architecture & Clean Layers

The project is structured strictly using standard enterprise layered architectures to maximize scalability, testing, and clean separation of concerns:

```text
src/main/java/com/school/
├── SchoolManagementApplication.java  # Main Bootstrapping Entry Point
├── config/                           # Application & CORS configuration
├── controller/                       # REST Controllers (exposing API endpoints)
│   ├── AuthController.java
│   ├── AdminStudentController.java
│   ├── AdminCourseController.java
│   └── StudentProfileController.java
├── dto/                              # Request/Response Data Transfer Objects (DTOs)
├── entity/                           # JPA Database Entities (Student, Course, Address)
├── enums/                            # Shared Enums (Gender, AddressType)
├── exception/                        # Global Exception Handler and custom exceptions
├── repository/                       # Spring Data JPA Repository Layers
├── security/                         # JWT filters, custom entry points, & user contexts
└── service/                          # Business Logic Services & Implementations
```

---

## 🚀 Local Setup & Installation

Follow these steps to set up and run the application locally on your machine.

### Prerequisites
*   **Java Development Kit (JDK) 17** or higher installed.
*   **Apache Maven 3.6+** installed (or use the provided Maven wrapper `./mvnw`).
*   **PostgreSQL 14+** running locally (or running in Docker).

---

### Step 1: Set up the Database

#### Option A: Using Docker (Highly Recommended & Easiest)
If you have Docker installed, you can spin up a fully configured PostgreSQL container instantly with a single command:
```bash
docker run --name school-postgres \
  -e POSTGRES_USER=root \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_DB=school_management \
  -p 5432:5432 \
  -d postgres:15-alpine
```

#### Option B: Manual Local PostgreSQL Setup
If you are running PostgreSQL directly on your host machine:
1. Log in to your PostgreSQL CLI or GUI (PgAdmin/DBeaver).
2. Create a database named `school_management`:
   ```sql
   CREATE DATABASE school_management;
   ```
3. Ensure your PostgreSQL instance has a user matching the credentials in `application.properties` (or modify `application.properties` to match your local setup).

---

### Step 2: Configure Environment Variables or Database Settings

Open `src/main/resources/application.properties` and verify or edit the database credentials:
```properties
# Database URL, Username, and Password
spring.datasource.url=jdbc:postgresql://localhost:5432/school_management
spring.datasource.username=root
spring.datasource.password=secret

# Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

> [!NOTE]
> For safety, the repository default is set to `spring.jpa.hibernate.ddl-auto=update` to prevent schema loss during application restarts while testing locally.

---

### Step 3: Build and Run the Backend API

Navigate to the project's root folder in your terminal and execute:

**On Linux / macOS:**
```bash
./mvnw clean spring-boot:run
```

**On Windows (Command Prompt / PowerShell):**
```powershell
.\mvnw.cmd clean spring-boot:run
```

The application will start, build database schemas, and begin listening for API requests on **`http://localhost:8080`**.

---

## 🧪 How to Test the APIs

We have provided two highly accessible methods to test the REST endpoints without having to build requests manually:

### 1. Interactive Frontend Web Application (Best Experience)
Run the companion frontend repository alongside this backend. The frontend provides a stunning user interface to interactively test admissions, course allocations, profile updates, and authentication states seamlessly.

### 2. Live HTTP Requests File (`requests.http`)
We have included a fully configured **`requests.http`** file in the root of this project. 
*   If you use **VS Code**, install the **REST Client** extension.
*   If you use **IntelliJ IDEA Ultimate**, you can run these HTTP requests natively.
*   This file lists every single API request, headers, sample request bodies, and lets you log in, capture the JWT Token, and execute protected Admin and Student endpoints with a single click.

---

## 📑 API Endpoints Reference

### 🔐 Authentication

| HTTP Method | Endpoint | Auth | Description | Request Body Payload |
|:---|:---|:---|:---|:---|
| `POST` | `/api/auth/admin/login` | Public | Admin Login (Retrieve JWT) | `{"username": "admin", "password": "admin123"}` |
| `POST` | `/api/auth/student/validate` | Public | Student Login (Retrieve JWT) | `{"studentCode": "STU001", "dateOfBirth": "YYYY-MM-DD"}` |

### 🛠️ Admin Dashboard (Requires Admin Bearer Token)

| HTTP Method | Endpoint | Description | Sample Request / Query Parameters |
|:---|:---|:---|:---|
| `POST` | `/api/admin/student` | Admits a new Student with multiple addresses | Nested addresses JSON (`PERMANENT`, `CURRENT`, etc.) |
| `POST` | `/api/admin/course` | Uploads and registers a new Course | `{"courseName": "BCA", "topics": "Java, Spring Boot"}` |
| `GET` | `/api/admin/courses` | Lists all active courses | None |
| `POST` | `/api/admin/student/{studentId}/course/{courseId}` | Assigns a course to a student | Dynamic path variables |
| `GET` | `/api/admin/students/search` | Search students by Name (fuzzy search) | `?name=Abhay` |
| `GET` | `/api/admin/students/by-course/{courseId}` | Fetch students assigned to a specific course | Dynamic path variable |

### 🎒 Student Portal (Requires Student Bearer Token)

| HTTP Method | Endpoint | Description | Sample Request / Query Parameters |
|:---|:---|:---|:---|
| `GET` | `/api/student/profile` | Fetches authenticated student's profile details | None |
| `PUT` | `/api/student/profile` | Updates student email, mobile, parent names, addresses | Profile JSON |
| `GET` | `/api/student/courses` | Lists assigned courses (supports filtering by topic) | `?topic=Java` (Optional) |
| `DELETE` | `/api/student/courses/{courseId}` | Voluntarily leaves/unregisters from a course | Dynamic path variable |

---

## 🔒 Originality & Plagiarism Statement

This system has been built in full compliance with the Recruitment standards. Every layer, authentication filter, relationship mapping, and service method has been crafted specifically for this evaluation from the ground up, ensuring original, high-performance, and plagiarism-free source code.

For any deployment queries or questions regarding this backend, please feel free to reach out to the contacts listed in the assignment sheet! 🎓
