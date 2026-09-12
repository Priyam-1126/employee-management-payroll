package com.employeepayroll.model;
import java.time.LocalDate;
public record Attendance(int employeeId, LocalDate date, String status) {}
