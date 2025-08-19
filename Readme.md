# Customer Service Section11

A Spring Boot microservice to manage customer data securely,  
following a clean layered architecture and including features like password hashing,  
enum-based status, and timestamp management.

---

##  Features
* Create, Read, Update, Patch, and Soft Delete customer records.
* Stores customer status (`ACTIVE` / `INACTIVE`) as an Enum.
* Automatically sets `createdDate` and `updatedDate`.
* Prevents duplicate customer creation using mobile number or email.
* Returns consistent API responses using `ApiResponse` DTO.
* Exception handling for both "Customer Not Found" and "Customer Already Exists".
* Uses `Lombok` to reduce boilerplate code.
* Uses `LocalDateTime` for timestamps.

---

##  Tech Stack
* Spring Boot
* Spring Web
* Spring Data JPA
* Lombok
* MySQL
* Java 17+

---

##  API Endpoints
* **POST**: 'http://localhost:8080/api/customer/v1/create'
* **GET**: 'http://localhost:8080/api/customer/v1/getAllData'
* **GET**: 'http://localhost:8080/api/customer/v1/getByMobile/{mobileNumber}'
* **GET**: 'http://localhost:8080/api/customer/v1/getByUserName/{userName}'
* **GET**: 'http://localhost:8080/api/customer/v1/getByEmailAddress/{emailAddress}'
* **PUT**: 'http://localhost:8080/api/customer/v1/update'
* **DELETE**: 'http://localhost:8080/api/customer/v1/delete/{mobileNumber}'
* **PATCH**: 'http://localhost:8080/api/customer/v1/updateMobileNumber/{userName}/{mobileNumber}'

---

### 1. **Create Customer**

* **Method**: `POST`
* **URL**: `http://localhost:8080/api/customer/v1/create`
* **Request Body**:

```json
{
   "userName": "Aiyan",
   "customerAge": 2,
   "customerMobileNumber": "0987654321",
   "customerEmailAddress": "Aiyan@gamil.com",
   "customerAddress": "chinnamandem"
}

```

* **Response Body**:
```json
{
   "code": 201,
   "message": "Customer created Successfully",
   "data": {
      "customerId": 5,
      "userName": "Aiyan",
      "customerAge": 2,
      "customerEmailAddress": "Aiyan@gamil.com",
      "customerMobileNumber": "0987654321",
      "customerAddress": "chinnamandem",
      "userStatus": "ACTIVE",
      "createdDate": "2025-08-13T17:55:12.534845",
      "updatedDate": "2025-08-13T17:55:12.534845"
   }
}
```

---

### 2. **Get All Customers**

* **Method**: `GET`
* **URL**: `http://localhost:8080/api/customer/v1/getAllData`
* **Response Body**:
```json
{
   "code": 200,
   "message": "OK",
   "data": [
      {
         "customerId": 1,
         "userName": "HayathullaUpdate",
         "customerAge": 24,
         "customerEmailAddress": "Hayath@gamil.com",
         "customerMobileNumber": "6304474604",
         "customerAddress": " Main Street",
         "userStatus": "INACTIVE",
         "createdDate": "2025-08-13T17:52:25.380681",
         "updatedDate": "2025-08-13T18:05:42.395161"
      },
      {
         "customerId": 2,
         "userName": "sufiyan",
         "customerAge": 16,
         "customerEmailAddress": "sufiyan@gmail.com",
         "customerMobileNumber": "8985415771",
         "customerAddress": "chinnamandem",
         "userStatus": "ACTIVE",
         "createdDate": "2025-08-13T17:53:16.224597",
         "updatedDate": "2025-08-13T17:53:16.224597"
      },
      {
         "customerId": 3,
         "userName": "Arzoo",
         "customerAge": 18,
         "customerEmailAddress": "Arzoo@gmail.com",
         "customerMobileNumber": "951541910",
         "customerAddress": "chinnamandem",
         "userStatus": "ACTIVE",
         "createdDate": "2025-08-13T17:53:59.024449",
         "updatedDate": "2025-08-13T17:53:59.024449"
      },
      {
         "customerId": 4,
         "userName": "Aakif",
         "customerAge": 3,
         "customerEmailAddress": "Aakif@gmail.com",
         "customerMobileNumber": "0000000001",
         "customerAddress": "chinnamandem",
         "userStatus": "ACTIVE",
         "createdDate": "2025-08-13T17:54:44.671822",
         "updatedDate": "2025-08-13T18:18:09.396696"
      },
      {
         "customerId": 5,
         "userName": "Aiyan",
         "customerAge": 2,
         "customerEmailAddress": "Aiyan@gamil.com",
         "customerMobileNumber": "0987654321",
         "customerAddress": "chinnamandem",
         "userStatus": "ACTIVE",
         "createdDate": "2025-08-13T17:55:12.534845",
         "updatedDate": "2025-08-13T17:55:12.534845"
      }
   ]
}
```

---

### 3. **Get Customer By Mobile Number**

* **Method**: `GET`
* **URL**: `http://localhost:8080/api/customer/v1/getByMobile/0987654321`
* **Response**:
```json
{
   "code": 200,
   "message": "OK",
   "data": {
      "customerId": 5,
      "userName": "Aiyan",
      "customerAge": 2,
      "customerEmailAddress": "Aiyan@gamil.com",
      "customerMobileNumber": "0987654321",
      "customerAddress": "chinnamandem",
      "userStatus": "ACTIVE",
      "createdDate": "2025-08-13T17:55:12.534845",
      "updatedDate": "2025-08-13T17:55:12.534845"
   }
}
```

---

### 4. **Get Customer By Username**

* **Method**: `GET`
* **URL**: `http://localhost:8080/api/customer/v1/getByUserName/arzoo`
```json
{
   "code": 200,
   "message": "OK",
   "data": {
      "customerId": 3,
      "userName": "Arzoo",
      "customerAge": 18,
      "customerEmailAddress": "Arzoo@gmail.com",
      "customerMobileNumber": "951541910",
      "customerAddress": "chinnamandem",
      "userStatus": "ACTIVE",
      "createdDate": "2025-08-13T17:53:59.024449",
      "updatedDate": "2025-08-13T17:53:59.024449"
   }
}
```

---

### 5. **Get Customer By Email Address**

* **Method**: `GET`
* **URL**: `http://localhost:8080/api/customer/v1/getByEmailAddress/sufiyan@gmail.com`
* **Response Body**:
```json
{
   "code": 200,
   "message": "OK",
   "data": {
      "customerId": 2,
      "userName": "sufiyan",
      "customerAge": 16,
      "customerEmailAddress": "sufiyan@gmail.com",
      "customerMobileNumber": "8985415771",
      "customerAddress": "chinnamandem",
      "userStatus": "ACTIVE",
      "createdDate": "2025-08-13T17:53:16.224597",
      "updatedDate": "2025-08-13T17:53:16.224597"
   }
}
```

---

### 6. **Update Customer Details**

* **Method**: `PUT`
* **URL**: `http://localhost:8080/api/customer/v1/update`
* **Request Body**:

```json
{
   "userName": "HayathullaUpdate",
   "customerAge": 24,
   "customerMobileNumber": "6304474604",
   "customerEmailAddress": "Hayath@gamil.com",
   "customerAddress": " Main Street",
   "password": "01021401021"
}

```

* **Response Body**:
```json
{
   "code": 200,
   "message": "Customer updated Successfully",
   "data": {
      "customerId": 1,
      "userName": "HayathullaUpdate",
      "customerAge": 24,
      "customerEmailAddress": "Hayath@gamil.com",
      "customerMobileNumber": "6304474604",
      "customerAddress": " Main Street",
      "userStatus": "ACTIVE",
      "createdDate": "2025-08-13T17:52:25.380681",
      "updatedDate": "2025-08-13T18:04:21.858613"
   }
}
```

---

### 7. **Delete Customer (Soft Delete)**

* **Method**: `DELETE`
* **URL**: `http://localhost:8080/api/customer/v1/delete/6304474604`
* **Response Body**: `
```json
{
   "code": 200,
   "message": "Customer deleted Successfully",
   "data": {
      "customerId": 1,
      "userName": "HayathullaUpdate",
      "customerAge": 24,
      "customerEmailAddress": "Hayath@gamil.com",
      "customerMobileNumber": "6304474604",
      "customerAddress": " Main Street",
      "userStatus": "INACTIVE",
      "createdDate": "2025-08-13T17:52:25.380681",
      "updatedDate": "2025-08-13T18:05:42.395161"
   }
}
```

---

### 8. **Patch Mobile Number**

* **Method**: `PATCH`
* **URL**: `http://localhost:8080/api/customer/v1/updateMobileNumber/Aakif/0000000001`
* **Response Body**:
```json
{
   "code": 201,
   "message": "OK",
   "data": {
      "customerId": 4,
      "userName": "Aakif",
      "customerAge": 3,
      "customerEmailAddress": "Aakif@gmail.com",
      "customerMobileNumber": "0000000001",
      "customerAddress": "chinnamandem",
      "userStatus": "ACTIVE",
      "createdDate": "2025-08-13T17:54:44.671822",
      "updatedDate": "2025-08-13T18:18:09.396696"
   }
}
```

---

### 9. **Patch ACTIVE/IN-ACTIVE**

* **Method**: `PATCH`
* **URL**: `http://localhost:8080/api/customer/v1/status/6304474604/ACTIVE`
* **Response Body**:
```json
{
   "code": 200,
   "message": "Customer status updated Successfully",
   "data": {
      "customerId": 1,
      "userName": "HayathullaUpdate",
      "customerAge": 24,
      "customerEmailAddress": "Hayath@gamil.com",
      "customerMobileNumber": "6304474604",
      "customerAddress": " Main Street",
      "userStatus": "ACTIVE",
      "createdDate": "2025-08-13T17:52:25.380681",
      "updatedDate": "2025-08-13T18:19:50.512704"
   }
}
```

##  Database Schema

**Table**: `customer_details_section9`

| Field Name     | Type      | Description                  |
|----------------|-----------|------------------------------|
| id             | bigint    | Primary Key (Auto-generated) |
| user\_name     | varchar   | Customer name                |
| mobile\_number | varchar   | Customer mobile number       |
| email\_address | varchar   | Customer email               |
| customer\_age  | int       | Customer age                 |
| password       | varchar   | Encrypted password           |
| user\_status   | varchar   | Enum: `ACTIVE`, `INACTIVE`   |
| created\_date  | timestamp | Record creation timestamp    |
| updated\_date  | timestamp | Last update timestamp        |

---

##  Password Hashing

* Passwords are auto-generated and hashed using **SHA-256** before saving.
* Format: 10 characters with at least 1 uppercase, lowercase, digit, and special character.

---

##  Project Structure

```
customer-service-section9
|── constant               # Constants like password characters, length
|── controller             # REST controllers for endpoints
|── enums                  # Enum for CustomerStatus
|── entity/model           # JPA Entity - CustomerModel
|── exceptions             # Custom exceptions & global exception handler
|── mapper                 # Mapper classes for converting between Entity and DTOs
|── repository             # Spring Data JPA repository
|── request                # Request DTOs
|── response               # Response DTOs
|── service                # Interfaces and implementations
|── service/impl           # Service implementation classes containing business logic
|── util                   # Utility class for password hashing
── CustomerServiceSection10Application.java
```

---

##  Enum—CustomerStatus

```java
public enum CustomerStatus {
    ACTIVE,
    INACTIVE
}
```

---

##  Important Classes & Purpose

| Class                             | Purpose                                                              |
|-----------------------------------|----------------------------------------------------------------------|
| `CustomerController`              | Handles all REST API requests                                        |
| `CustomerService`                 | Service interface for business logic                                 |
| `CustomerServiceImpl`             | Implementation of business logic                                     |
| `CustomerModel`                   | Entity class for JPA table mapping                                   |
| `CustomerRequest`                 | DTO for input requests                                               |
| `CustomerResponse`                | DTO for output responses                                             |
| `CustomerUtil`                    | Utility for password generation & hashing                            |
| `Constant`                        | Constant values used across application                              |
| `CustomerRepository`              | Extends `JpaRepository` for DB operations                            |
| `CustomerAlreadyExistsException`  | Custom exception thrown when creating a duplicate customer           |
| `CustomerNotExistsException`      | Custom exception thrown when requested customer is not found         |
| `GlobalExceptionHandler`          | Handles exceptions globally and returns standardized error responses |



---

##  How to Run

1. Create database:

   ```sql
   CREATE DATABASE customer_db1;
   ```
2. Update `application.properties` with MySQL credentials.
3. Run Spring Boot Application.
4. Test using **Postman** or any REST client.

---

##  Future Enhancements
* Add input validation annotations (`@NotNull`, `@Email`, etc.)
* Centralized global exception handling
* Add JWT-based authentication
* Full Swagger/OpenAPI documentation
* Implement pagination for `getAllData`

---