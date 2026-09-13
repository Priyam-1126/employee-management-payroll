package com.employeepayroll.model;

import java.time.LocalDate;

public class Employee implements Payable {
    private int id;
    private String name, email, employeeType;
    private int departmentId;
    private double baseSalary, allowance, deduction;
    private LocalDate joiningDate;

    public Employee() {
    }

    public Employee(int id, String name, String email, int departmentId, String employeeType,
            double baseSalary, double allowance, double deduction, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.employeeType = employeeType;
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.deduction = deduction;
        this.joiningDate = joiningDate;
    }

    public double calculateNetSalary() {
        return baseSalary + allowance - deduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int v) {
        id = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String v) {
        name = v;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String v) {
        email = v;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int v) {
        departmentId = v;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String v) {
        employeeType = v;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double v) {
        baseSalary = v;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double v) {
        allowance = v;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double v) {
        deduction = v;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate v) {
        joiningDate = v;
    }

    public String toString() {
        return String.format("Employee{id=%d, name='%s', email='%s', type='%s', netSalary=%.2f}",
                id, name, email, employeeType, calculateNetSalary());
    }
}
