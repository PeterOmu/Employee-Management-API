# Employee Management ERP

**Author:** Peter Omu

This is a **Spring Boot-based Employee Management ERP system** built with PostgreSQL, Docker, and standard enterprise best practices. It allows CRUD operations for **Departments** and **Employees** with pagination, filtering, sorting, and search capabilities.

---

## **Table of Contents**

1. [Technology Stack](#technology-stack)
2. [Dependencies](#dependencies)
3. [Database Setup](#database-setup)
4. [Docker Setup](#docker-setup)
5. [Running the Application](#running-the-application)
6. [API Endpoints](#api-endpoints)
7. [Postman Collection](#postman-collection)
8. [Author](#author)

---

## **Technology Stack**

* **Backend:** Spring Boot 3.x
* **Language:** Java 17+
* **ORM:** Hibernate / JPA
* **Database:** PostgreSQL 17 (Dockerized)
* **API:** RESTful JSON endpoints
* **Validation:** Jakarta Bean Validation (`@Valid`)
* **Build Tool:** Maven
* **Logging:** SLF4J + Spring Boot logging

---

## **Dependencies**

Key Maven dependencies included:

* `spring-boot-starter-web` → REST APIs
* `spring-boot-starter-data-jpa` → JPA/Hibernate
* `spring-boot-starter-validation` → DTO validation
* `postgresql` → JDBC driver
* `lombok` → Boilerplate reduction (`@Getter`, `@Builder`, etc.)
* `spring-boot-devtools` → Development hot reload (optional)

Example `pom.xml` snippet:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

---

## **Database Setup**

The application uses **PostgreSQL 17** running via Docker.

* **DB Name:** `employee_db`
* **Username:** `postgres`
* **Password:** `postgres123`
* **Port:** `5432`

### Initial SQL Data

* `src/main/resources/data.sql` contains **sample departments and employees**.
* The script is **idempotent** (`ON CONFLICT DO NOTHING`) and runs automatically on Spring Boot startup.

---

## **Docker Setup**

A `docker-compose.yml` is included with two services:

```yaml
services:
  postgres:
    image: postgres:17-alpine
    container_name: erp-postgres
    restart: always
    environment:
      POSTGRES_DB: employee_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres123
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  pgadmin:
    image: dpage/pgadmin4:latest
    container_name: erp-pgadmin
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@erp.com
      PGADMIN_DEFAULT_PASSWORD: admin123
    ports:
      - "5050:80"
    depends_on:
      - postgres

volumes:
  postgres_data:
```

* **pgAdmin URL:** [http://localhost:5050](http://localhost:5050)
* **pgAdmin login:** [admin@erp.com](mailto:admin@erp.com) / admin123

---

## **Running the Application**

1. **Start Docker containers**:

```bash
docker-compose up -d
```

2. **Build and run Spring Boot app**:

```bash
mvn clean install
mvn spring-boot:run
```

3. **Application will start** on: `http://localhost:8080`

4. **Data population**: Spring Boot automatically runs `data.sql` on startup to insert sample departments and employees.

---

## **API Endpoints**

| Method   | Endpoint              | Description                              |
| -------- | --------------------- | ---------------------------------------- |
| `POST`   | `/api/employees`      | Create Employee                          |
| `PUT`    | `/api/employees/{id}` | Update Employee                          |
| `GET`    | `/api/employees/{id}` | Get Employee by ID                       |
| `DELETE` | `/api/employees/{id}` | Delete Employee                          |
| `GET`    | `/api/employees`      | Paginated list with search, sort, filter |

**Query parameters for `/api/employees`**:

* `page` (default: 1)
* `size` (default: 10)
* `sortBy` (default: id)
* `direction` (asc/desc, default: asc)
* `departmentId` (filter by department)
* `search` (filter by firstName or lastName)

**Example:**

```
GET /api/employees?page=1&size=5&sortBy=salary&direction=desc&search=john
```

---

## **Postman Collection**

A **Postman collection** is included in the repository for easy API testing and integration.

* Import it into Postman.
* All CRUD endpoints and query filters are pre-configured.

---

## **Author**

**Peter Omu**

* Email: [peter.omu@interswitchng.com]
* LinkedIn: [Blardomu]
