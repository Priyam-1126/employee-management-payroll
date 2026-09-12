package com.employeepayroll.model;
import java.time.LocalDate;
public record LeaveRequest(int employeeId, LocalDate startDate, LocalDate endDate,
                           String reason, String status) {}
