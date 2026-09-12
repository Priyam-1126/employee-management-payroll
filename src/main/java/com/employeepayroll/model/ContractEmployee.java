package com.employeepayroll.model;

public class ContractEmployee extends Employee {
    private double contractFee;
    public ContractEmployee(Employee e,double fee){
        super(e.getId(),e.getName(),e.getEmail(),e.getDepartmentId(),e.getEmployeeType(),
              e.getBaseSalary(),e.getAllowance(),e.getDeduction(),e.getJoiningDate());
        this.contractFee=fee;
    }
    @Override public double calculateNetSalary(){return getBaseSalary()+contractFee-getDeduction();}
}
