package com.employeepayroll.dao;

import com.employeepayroll.exception.DatabaseException;
import com.employeepayroll.util.DBConnection;
import java.sql.*;

public class PayrollDAO {
    public void save(int id, String month, double base, double allowance, double deduction, double net) {
        String s = "INSERT INTO payroll(employee_id,payroll_month,base_salary,allowance,deduction,net_salary) VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE base_salary=VALUES(base_salary),allowance=VALUES(allowance),deduction=VALUES(deduction),net_salary=VALUES(net_salary)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(s)) {
            p.setInt(1, id);
            p.setString(2, month);
            p.setDouble(3, base);
            p.setDouble(4, allowance);
            p.setDouble(5, deduction);
            p.setDouble(6, net);
            p.executeUpdate();
        } catch (SQLException x) {
            throw new DatabaseException("Unable to save payroll.", x);
        }
    }
}
