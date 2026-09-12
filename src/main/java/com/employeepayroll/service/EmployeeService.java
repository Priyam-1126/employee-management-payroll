package com.employeepayroll.service;
import com.employeepayroll.dao.EmployeeDAO;
import com.employeepayroll.exception.EmployeeNotFoundException;
import com.employeepayroll.model.Employee;
import com.employeepayroll.util.*;
import java.util.List;
public class EmployeeService {
    private final EmployeeDAO dao=new EmployeeDAO();
    private final EmployeeCache cache=new EmployeeCache();
    public int add(Employee e){int id=dao.create(e);e.setId(id);cache.put(e);FileUtil.log("Created employee: "+id);return id;}
    public List<Employee> getAll(){List<Employee> x=dao.findAll();x.forEach(cache::put);return x;}
    public Employee get(int id){Employee e=cache.get(id);if(e!=null)return e;e=dao.findById(id);if(e==null)throw new EmployeeNotFoundException("Employee not found: "+id);cache.put(e);return e;}
    public void updateSalary(int id,double b,double a,double d){if(!dao.updateSalary(id,b,a,d))throw new EmployeeNotFoundException("Employee not found: "+id);cache.remove(id);FileUtil.log("Updated salary: "+id);}
    public void delete(int id){if(!dao.delete(id))throw new EmployeeNotFoundException("Employee not found: "+id);cache.remove(id);FileUtil.log("Deleted employee: "+id);}
}
