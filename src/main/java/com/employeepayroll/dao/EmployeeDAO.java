package com.employeepayroll.dao;

import com.employeepayroll.exception.DatabaseException;
import com.employeepayroll.model.Employee;
import com.employeepayroll.util.DBConnection;
import java.sql.*;
import java.util.*;

public class EmployeeDAO {
    public int create(Employee e){
        String sql="INSERT INTO employees(name,email,department_id,employee_type,base_salary,allowance,deduction,joining_date) VALUES(?,?,?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            p.setString(1,e.getName());p.setString(2,e.getEmail());p.setInt(3,e.getDepartmentId());
            p.setString(4,e.getEmployeeType());p.setDouble(5,e.getBaseSalary());p.setDouble(6,e.getAllowance());
            p.setDouble(7,e.getDeduction());p.setDate(8,java.sql.Date.valueOf(e.getJoiningDate()));p.executeUpdate();
            try(ResultSet r=p.getGeneratedKeys()){return r.next()?r.getInt(1):-1;}
        }catch(SQLException x){throw new DatabaseException("Unable to create employee.",x);}
    }
    public List<Employee> findAll(){
        List<Employee> out=new ArrayList<>();
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM employees ORDER BY id");ResultSet r=p.executeQuery()){
            while(r.next())out.add(map(r));return out;
        }catch(SQLException x){throw new DatabaseException("Unable to fetch employees.",x);}
    }
    public Employee findById(int id){
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM employees WHERE id=?")){
            p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}
        }catch(SQLException x){throw new DatabaseException("Unable to find employee.",x);}
    }
    public boolean updateSalary(int id,double base,double allowance,double deduction){
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("UPDATE employees SET base_salary=?,allowance=?,deduction=? WHERE id=?")){
            p.setDouble(1,base);p.setDouble(2,allowance);p.setDouble(3,deduction);p.setInt(4,id);return p.executeUpdate()>0;
        }catch(SQLException x){throw new DatabaseException("Unable to update salary.",x);}
    }
    public boolean delete(int id){
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM employees WHERE id=?")){
            p.setInt(1,id);return p.executeUpdate()>0;
        }catch(SQLException x){throw new DatabaseException("Unable to delete employee.",x);}
    }
    private Employee map(ResultSet r)throws SQLException{
        return new Employee(r.getInt("id"),r.getString("name"),r.getString("email"),r.getInt("department_id"),
          r.getString("employee_type"),r.getDouble("base_salary"),r.getDouble("allowance"),r.getDouble("deduction"),
          r.getDate("joining_date").toLocalDate());
    }
}
