/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author adeli
 */

import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO {

    private Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

   public EmployeeDAO() {
        try {
            this.conn = DBConnection.getConnection(); // Get connection from DBConnection class
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


//    public EmployeeDAO() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    // CREATE
    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (emp_id, full_name, gender, email, phone, department_name) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getEmpId());
            stmt.setString(2, emp.getFullName());
            stmt.setString(3, emp.getGender());
            stmt.setString(4, emp.getEmail());
            stmt.setString(5, emp.getPhone());
            stmt.setString(6, emp.getDepartmentName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public  List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getString("emp_id"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("department_name")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET full_name=?, gender=?, email=?, phone=?, department_name=? WHERE emp_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getFullName());
            stmt.setString(2, emp.getGender());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getPhone());
            stmt.setString(5, emp.getDepartmentName());
            stmt.setString(6, emp.getEmpId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteEmployee(String empId) {
        String sql = "DELETE FROM employees WHERE emp_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
   
 

}
