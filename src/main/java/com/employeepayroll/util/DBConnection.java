package com.employeepayroll.util;
import java.sql.*;
public final class DBConnection {
    private DBConnection(){}
    public static Connection getConnection() throws SQLException {
        String url=System.getenv().getOrDefault("DB_URL","jdbc:mysql://localhost:3307/employee_payroll");
        String user=System.getenv().getOrDefault("DB_USER","root");
        String pass=System.getenv().getOrDefault("DB_PASSWORD","root");
        return DriverManager.getConnection(url,user,pass);
    }
}
