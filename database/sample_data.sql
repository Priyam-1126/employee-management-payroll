USE employee_payroll;

INSERT IGNORE INTO departments (id,name) VALUES
(1,'Engineering'),(2,'Human Resources'),(3,'Finance');

INSERT IGNORE INTO employees
(id,name,email,department_id,employee_type,base_salary,allowance,deduction,joining_date)
VALUES
(1,'Aarav Sharma','aarav@example.com',1,'FULL_TIME',60000,8000,3000,'2025-01-15'),
(2,'Neha Singh','neha@example.com',2,'FULL_TIME',52000,6000,2500,'2025-03-10'),
(3,'Rohan Das','rohan@example.com',1,'CONTRACT',45000,4000,1500,'2025-06-01');
