# HealthHub Management System

A Spring Boot application for healthcare operations, built with JWT, paging, sorting, validation,  testing with JUnit , Swagger, Lombok, and MySQL.

## Table of Contents

- Features
- Tech Stack
- Prerequisites
- Getting Started
- API Endpoints
- Project Structure
- Testing

## Features

- Authentication: JWT-based login system with secure token management
- User Management: Full CRUD operations for system users
- Doctor & Nurse Management: Complete healthcare staff data handling
- Patient Records: Patient information and medical history tracking
- Medicine Inventory: Stock management and prescription handling
- Medical Records: Electronic health records system
- Billing: Healthcare billing and payment tracking
- API Documentation: Swagger UI for easy API exploration
- Testing: Unit and integration tests for reliability

## Tech Stack

- Java 21 - Using the latest LTS for better performance
- Spring Boot 3.4.0-M2 - For rapid development and microservices
- Spring Security - Handles authentication and authorization
- Spring Data JPA - Database operations and entity management
- MySQL - Reliable database for healthcare data
- JWT - Stateless authentication tokens
- Lombok - Reduces boilerplate code
- Maven - Build and dependency management
- Swagger/OpenAPI - API documentation

## Prerequisites

You'll need these installed on your machine:

- Java 21 or newer
- Maven 3.6+
- MySQL 8.0+
- Git

## Getting Started

1. Clone and Setup
```bash
git clone <repository-url>
cd healthhub
```

2. Database Setup
First, create the database:
```sql
CREATE DATABASE healthhub;
```

Then update the database config in `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.url=jdbc:mysql://localhost:3306/healthhub
```

3. Build and Run
```bash
# Build the project
mvn clean install

# Run in development mode
mvn spring-boot:run
```

The app will be running at http://localhost:8008

## API Endpoints

Once running, check out the API docs at:
- Swagger UI: http://localhost:8008/swagger-ui.html

### Example: Register

**Request (Valid):**
```json
POST /api/auth/register
{
  "name": "Amisha",
  "email": "Amisha@email.com",
  "password": "amisha123"
}
```

**Request (Invalid):**
```json
POST /api/auth/register
{
  "name": "am",
  "email": "Amisha",
  "password": "123"
}
```

**Response (Validation Error):**
```json
{
  "timestamp": "2024-05-01T12:34:56.789+00:00",
  "status": 400,
  "errors": [
    "Name must be between 3 and 50 characters",
    "Email should be valid",
    "Password must be at least 6 characters"
  ],
  "path": "/api/auth/register"
}
```

### Example: Login

**Request (Valid):**
```json
POST /api/auth/login
{
  "email": "Amisha@email.com",
  "password": "Amisha123"
}
```

**Request (Invalid):**
```json
POST /api/auth/login
{
  "email": "Amisha",
  "password": "123"
}
```

**Response (Validation Error):**
```json
{
  "timestamp": "2024-05-01T12:35:10.123+00:00",
  "status": 400,
  "errors": [
    "Email should be valid",
    "Password must be at least 6 characters"
  ],
  "path": "/api/auth/login"
}
```

Main Endpoints

Authentication:
- POST /api/auth/register - Create new user account
- POST /api/auth/login - Login and get JWT token

Users:
- GET /api/users - List all users
- GET /api/users/{id} - Get specific user
- POST /api/users - Create new user
- PUT /api/users/{id} - Update user
- DELETE /api/users/{id} - Remove user

Doctors:
- GET /api/doctors - List all doctors
- GET /api/doctors/{id} - Get doctor details
- POST /api/doctors - Add new doctor
- PUT /api/doctors/{id} - Update doctor info
- DELETE /api/doctors/{id} - Remove doctor

Patients:
- GET /api/patients - List all patients
- GET /api/patients/{id} - Get patient details
- POST /api/patients - Register new patient
- PUT /api/patients/{id} - Update patient info
- DELETE /api/patients/{id} - Remove patient

Medical Records:
- GET /api/medical-records - List all records
- GET /api/medical-records/{id} - Get specific record
- POST /api/medical-records - Create new record
- PUT /api/medical-records/{id} - Update record
- DELETE /api/medical-records/{id} - Remove record

Medicines:
- GET /api/medicines - List all medicines
- GET /api/medicines/{id} - Get medicine details
- POST /api/medicines - Add new medicine
- PUT /api/medicines/{id} - Update medicine
- DELETE /api/medicines/{id} - Remove medicine

Billing:
- GET /api/billings - List all billing records
- GET /api/billings/{id} - Get billing details
- POST /api/billings - Create new billing
- PUT /api/billings/{id} - Update billing
- DELETE /api/billings/{id} - Remove billing

## 

```
src/main/java/com/example/healthhub
├── HealthHubApplication.java
├── config/
│   ├── ConfigSwagger.java
│   └── GlobalExceptionHandler.java
├── controller/
│   ├── AuthController.java
│   ├── BillingController.java
│   ├── DoctorController.java
│   ├── HealthHubController.java
│   ├── MedicalRecordController.java
│   ├── MedicineController.java
│   ├── NurseController.java
│   ├── PatientController.java
│   └── UserController.java
├── dto/
│   ├── LoginUserDTO.java
│   ├── RegisterUserDTO.java
│   ├── request/
│   │   ├── BillingDTO.java
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   └── response/
│       └── LoginResponse.java
├── entity/
│   ├── Billing.java
│   ├── Doctor.java
│   ├── HealthHub.java
│   ├── MedicalRecord.java
│   ├── Medicine.java
│   ├── Nurse.java
│   ├── Patient.java
│   └── User.java
├── repository/
│   ├── BillingRepository.java
│   ├── DoctorRepository.java
│   ├── HealthHubRepository.java
│   ├── MedicalRecordRepository.java
│   ├── MedicineRepository.java
│   ├── NurseRepository.java
│   ├── PatientRepository.java
│   └── UserRepository.java
├── security/
│   ├── JwtAuthenticationToken.java
│   ├── JwtFilter.java
│   ├── JwtUtil.java
│   └── SecurityConfig.java
└── service/
    ├── AuthService.java
    ├── BillingService.java
    ├── DoctorService.java
    ├── HealthHubService.java
    ├── MedicalRecordService.java
    ├── MedicineService.java
    ├── NurseService.java
    ├── PatientService.java
    ├── UserService.java
    └── impl/
        ├── AuthServiceImpl.java
        ├── BillingServiceImpl.java
        ├── DoctorServiceImpl.java
        ├── HealthHubServiceImpl.java
        ├── MedicalRecordServiceImpl.java
        ├── MedicineServiceImpl.java
        ├── NurseServiceImpl.java
        ├── PatientServiceImpl.java
        └── UserServiceImpl.java
```

## Testing

Run the tests with:
```bash
mvn test
```

I've included tests for:
- Service layer unit tests
- Authentication integration tests
- Repository layer testing
- Application context tests

## Contributing

Feel free to contribute! Here's how:
1. Fork the repo
2. Create a feature branch
3. Make your changes
4. Add tests if needed
5. Submit a pull request

