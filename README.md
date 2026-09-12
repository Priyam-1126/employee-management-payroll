# Employee Management & Payroll System

This is a college project I built using Core Java, JDBC, MySQL and Docker. The main goal of the project is to manage employees and handle basic attendance, leave and payroll operations.

## Technologies Used

- Java 17
- Core Java / OOP
- JDBC
- MySQL 8.4
- Maven
- Docker & Docker Compose

## What the Project Can Do

- Add, view, update and delete employees
- Store department information
- Mark employee attendance
- Submit leave requests
- Generate basic payroll
- Adjust payroll based on attendance
- Use inheritance, interfaces and polymorphism
- Use `HashMap` for a simple employee cache
- Handle errors using custom exceptions
- Save basic application logs to a file
- Run SQL queries for reports

## Project Structure

```text
employee-management-payroll/
├── src/main/java/com/employeepayroll/
│   ├── Main.java
│   ├── model/
│   ├── dao/
│   ├── service/
│   ├── util/
│   └── exception/
├── database/
│   ├── schema.sql
│   ├── sample_data.sql
│   └── reports.sql
├── docker/
│   └── Dockerfile
├── pom.xml
├── docker-compose.yml
├── README.md
└── .gitignore
```

## How to Run

I used Docker so that Java and MySQL can run together without setting up the database manually.

Open PowerShell in the project folder and run:

```powershell
docker compose down -v
docker compose up --build
```

After the containers start, the Java program shows a menu like:

```text
1.List  2.Add  3.View  4.Update Salary  5.Delete
6.Attendance  7.Leave  8.Payroll  9.Demo  0.Exit
```

For testing the OOP and payroll calculation without using the menu:

```powershell
docker compose run --rm app --demo
```

## Database Details

MySQL runs inside Docker.

From the Windows machine:

```text
Host: localhost
Port: 3307
Database: employee_payroll
Username: root
Password: root
```

Inside the application container, the database connection is:

```text
jdbc:mysql://mysql:3306/employee_payroll
```

## SQL Practice

The `database/reports.sql` file contains examples of:

- JOIN
- Subquery
- GROUP BY
- HAVING
- CTE
- Aggregate functions
- Window function (`RANK`)

## What I Learned

While making this project, I practiced connecting Java with MySQL using JDBC, organizing Java classes using OOP concepts, writing SQL queries, handling exceptions and using Docker to run the application and database together.

## Future Improvements

- Add a proper GUI or web interface
- Add employee login and admin login
- Add better payroll rules and salary slips
- Add more detailed attendance and leave reports
- Add unit tests
