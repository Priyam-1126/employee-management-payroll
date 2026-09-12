package com.employeepayroll;

import com.employeepayroll.dao.*;
import com.employeepayroll.exception.*;
import com.employeepayroll.model.*;
import com.employeepayroll.service.EmployeeService;
import com.employeepayroll.util.PayrollCalculator;
import java.time.LocalDate;
import java.util.*;

public class Main {
    static final EmployeeService employees=new EmployeeService();
    static final AttendanceDAO attendance=new AttendanceDAO();
    static final LeaveDAO leaves=new LeaveDAO();
    static final PayrollDAO payroll=new PayrollDAO();

    public static void main(String[] args){
        System.out.println("======================================");
        System.out.println(" Employee Management & Payroll System");
        System.out.println("======================================");
        if(args.length>0 && args[0].equalsIgnoreCase("--demo")){demo();return;}

        try(Scanner sc=new Scanner(System.in)){
            while(true){
                System.out.println("\n1.List  2.Add  3.View  4.Update Salary  5.Delete  6.Attendance  7.Leave  8.Payroll  9.Demo  0.Exit");
                System.out.print("Choose: ");
                String c=sc.nextLine().trim();
                try{
                    switch(c){
                        case "1"->list();
                        case "2"->add(sc);
                        case "3"->view(sc);
                        case "4"->update(sc);
                        case "5"->delete(sc);
                        case "6"->markAttendance(sc);
                        case "7"->applyLeave(sc);
                        case "8"->generatePayroll(sc);
                        case "9"->demo();
                        case "0"->{System.out.println("Goodbye!");return;}
                        default->System.out.println("Invalid option.");
                    }
                }catch(EmployeeNotFoundException|DatabaseException|IllegalArgumentException e){System.out.println("Error: "+e.getMessage());}
            }
        }
    }

    static void list(){employees.getAll().forEach(System.out::println);}
    static void add(Scanner s){
        System.out.print("Name: ");String n=s.nextLine();
        System.out.print("Email: ");String em=s.nextLine();
        System.out.print("Department ID: ");int d=Integer.parseInt(s.nextLine());
        System.out.print("Type (FULL_TIME/CONTRACT): ");String t=s.nextLine().toUpperCase();
        System.out.print("Base salary: ");double b=Double.parseDouble(s.nextLine());
        System.out.print("Allowance: ");double a=Double.parseDouble(s.nextLine());
        System.out.print("Deduction: ");double de=Double.parseDouble(s.nextLine());
        Employee e=new Employee(0,n,em,d,t,b,a,de,LocalDate.now());
        System.out.println("Created employee ID: "+employees.add(e));
    }
    static void view(Scanner s){System.out.print("Employee ID: ");System.out.println(employees.get(Integer.parseInt(s.nextLine())));}
    static void update(Scanner s){
        System.out.print("Employee ID: ");int id=Integer.parseInt(s.nextLine());
        System.out.print("Base salary: ");double b=Double.parseDouble(s.nextLine());
        System.out.print("Allowance: ");double a=Double.parseDouble(s.nextLine());
        System.out.print("Deduction: ");double d=Double.parseDouble(s.nextLine());
        employees.updateSalary(id,b,a,d);System.out.println("Salary updated.");
    }
    static void delete(Scanner s){System.out.print("Employee ID: ");employees.delete(Integer.parseInt(s.nextLine()));System.out.println("Deleted.");}
    static void markAttendance(Scanner s){
        System.out.print("Employee ID: ");int id=Integer.parseInt(s.nextLine());
        System.out.print("Date (YYYY-MM-DD): ");LocalDate date=LocalDate.parse(s.nextLine());
        System.out.print("Status (PRESENT/ABSENT/LEAVE): ");String st=s.nextLine().toUpperCase();
        attendance.mark(new Attendance(id,date,st));System.out.println("Attendance saved.");
    }
    static void applyLeave(Scanner s){
        System.out.print("Employee ID: ");int id=Integer.parseInt(s.nextLine());
        System.out.print("Start date: ");LocalDate a=LocalDate.parse(s.nextLine());
        System.out.print("End date: ");LocalDate b=LocalDate.parse(s.nextLine());
        System.out.print("Reason: ");String r=s.nextLine();
        leaves.apply(new LeaveRequest(id,a,b,r,"PENDING"));System.out.println("Leave submitted.");
    }
    static void generatePayroll(Scanner s){
        System.out.print("Employee ID: ");int id=Integer.parseInt(s.nextLine());
        Employee e=employees.get(id);
        System.out.print("Payroll month (YYYY-MM): ");String m=s.nextLine();
        System.out.print("Present days: ");int p=Integer.parseInt(s.nextLine());
        System.out.print("Working days: ");int w=Integer.parseInt(s.nextLine());
        double net=PayrollCalculator.calculateAttendanceAdjusted(e,p,w);
        payroll.save(id,m,e.getBaseSalary(),e.getAllowance(),e.getDeduction(),net);
        System.out.printf("Payroll generated. Net salary = %.2f%n",net);
    }
    static void demo(){
        Employee e=new Employee(100,"Demo","demo@example.com",1,"FULL_TIME",50000,5000,2000,LocalDate.now());
        Payable f=new FullTimeEmployee(e,3000);
        Payable c=new ContractEmployee(e,2500);
        System.out.printf("Full-time polymorphic salary: %.2f%n",PayrollCalculator.calculate(f));
        System.out.printf("Contract polymorphic salary: %.2f%n",PayrollCalculator.calculate(c));
        System.out.printf("Attendance-adjusted salary: %.2f%n",PayrollCalculator.calculateAttendanceAdjusted(e,22,24));
    }
}
