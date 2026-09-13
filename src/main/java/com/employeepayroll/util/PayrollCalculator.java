package com.employeepayroll.util;

import com.employeepayroll.model.Employee;
import com.employeepayroll.model.Payable;

public final class PayrollCalculator {
    private PayrollCalculator() {
    }

    public static double calculate(Payable p) {
        return Math.max(0, p.calculateNetSalary());
    }

    public static double calculateAttendanceAdjusted(Employee e, int present, int working) {
        if (working <= 0)
            throw new IllegalArgumentException("Working days must be greater than zero.");
        double ratio = Math.max(0, Math.min(1, (double) present / working));
        return Math.max(0, (e.getBaseSalary() + e.getAllowance()) * ratio - e.getDeduction());
    }
}
