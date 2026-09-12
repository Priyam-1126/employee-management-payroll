package com.employeepayroll.dao;
import com.employeepayroll.exception.DatabaseException;
import com.employeepayroll.model.Attendance;
import com.employeepayroll.util.DBConnection;
import java.sql.*;
public class AttendanceDAO {
    public void mark(Attendance a){
        String s="INSERT INTO attendance(employee_id,attendance_date,status) VALUES(?,?,?) ON DUPLICATE KEY UPDATE status=VALUES(status)";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(s)){
            p.setInt(1,a.employeeId());p.setDate(2,Date.valueOf(a.date()));p.setString(3,a.status());p.executeUpdate();
        }catch(SQLException x){throw new DatabaseException("Unable to save attendance.",x);}
    }
}
