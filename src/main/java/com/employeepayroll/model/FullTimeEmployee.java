package com.employeepayroll.model;

public class FullTimeEmployee extends Employee {
    private double bonus;

    public FullTimeEmployee(Employee e, double bonus) {
        super(e.getId(), e.getName(), e.getEmail(), e.getDepartmentId(), e.getEmployeeType(),
                e.getBaseSalary(), e.getAllowance(), e.getDeduction(), e.getJoiningDate());
        this.bonus = bonus;
    }

    @Override
    public double calculateNetSalary() {
        return getBaseSalary() + getAllowance() + bonus - getDeduction();
    }
}
