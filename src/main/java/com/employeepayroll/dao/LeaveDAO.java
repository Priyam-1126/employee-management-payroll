package com.employeepayroll.dao;
import com.employeepayroll.exception.DatabaseException;
import com.employeepayroll.model.LeaveRequest;
import com.employeepayroll.util.DBConnection;
import java.sql.*;
public class LeaveDAO {
    public void apply(LeaveRequest l){
        String s="INSERT INTO leaves(employee_id,start_date,end_date,reason,status) VALUES(?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(s)){
            p.setInt(1,l.employeeId());p.setDate(2,Date.valueOf(l.startDate()));p.setDate(3,Date.valueOf(l.endDate()));
            p.setString(4,l.reason());p.setString(5,l.status());p.executeUpdate();
        }catch(SQLException x){throw new DatabaseException("Unable to apply leave.",x);}
    }
}
