USE employee_payroll;

SELECT e.id,e.name,e.email,d.name AS department,e.employee_type
FROM employees e LEFT JOIN departments d ON e.department_id=d.id ORDER BY e.id;

SELECT d.name AS department,COUNT(e.id) AS employee_count,
ROUND(AVG(e.base_salary),2) AS avg_base_salary,
ROUND(SUM(e.base_salary+e.allowance-e.deduction),2) AS total_net_salary
FROM departments d LEFT JOIN employees e ON d.id=e.department_id
GROUP BY d.id,d.name HAVING COUNT(e.id)>0 ORDER BY total_net_salary DESC;

SELECT id,name,base_salary FROM employees
WHERE base_salary>(SELECT AVG(base_salary) FROM employees)
ORDER BY base_salary DESC;

WITH salary_data AS (
 SELECT e.id,e.name,d.name AS department,
 (e.base_salary+e.allowance-e.deduction) AS net_salary
 FROM employees e LEFT JOIN departments d ON e.department_id=d.id
)
SELECT id,name,department,net_salary,
RANK() OVER(PARTITION BY department ORDER BY net_salary DESC) AS dept_rank
FROM salary_data ORDER BY department,dept_rank;
