# Employee Management & Payroll System

Runnable Core Java + JDBC + MySQL employee management and payroll application with Docker.

## Stack
Java 17, Core Java/OOP, JDBC, MySQL 8.4, Maven, Docker, Docker Compose.

## Features
- Employee CRUD
- Department relationship
- Attendance and leave tracking
- Payroll generation with attendance adjustment
- Interfaces, inheritance and polymorphism
- HashMap employee cache
- Custom exceptions and file logging
- SQL reporting with JOIN, subquery, GROUP BY, HAVING, CTE and window functions
- Dockerized Java application and MySQL

## Standard Maven Structure
`src/main/java/com/employeepayroll/Main.java`

## Run
From the project root:

```powershell
docker compose down -v
docker compose up --build
```

For the OOP/payroll demo:

```powershell
docker compose run --rm app --demo
```

MySQL is exposed to Windows on `localhost:3307`.
Inside Docker the application uses `jdbc:mysql://mysql:3306/employee_payroll`.

## Verification
After startup, use the console menu. Test listing employees, adding/updating/deleting, attendance, leave and payroll.

Do not commit `target/` or `*.log`.
