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

import model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private Connection conn;

    public DepartmentDAO(Connection conn) {
        this.conn = conn;
    }

    // Add a new department
    public boolean addDepartment(Department dept) {
        String sql = "INSERT INTO departments (dept_code, dept_name, head_of_dept, total_employees, established_year) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dept.getDeptCode());
            stmt.setString(2, dept.getDeptName());
            stmt.setString(3, dept.getHeadOfDept());
            stmt.setInt(4, dept.getTotalEmployees());
            stmt.setInt(5, dept.getEstablishedYear());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update department info
    public boolean updateDepartment(Department dept) {
        String sql = "UPDATE departments SET dept_name = ?, head_of_dept = ?, total_employees = ?, established_year = ? WHERE dept_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dept.getDeptName());
            stmt.setString(2, dept.getHeadOfDept());
            stmt.setInt(3, dept.getTotalEmployees());
            stmt.setInt(4, dept.getEstablishedYear());
            stmt.setString(5, dept.getDeptCode());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete department
    public boolean deleteDepartment(String deptCode) {
        String sql = "DELETE FROM departments WHERE dept_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deptCode);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Department dept = new Department(
                    rs.getString("dept_code"),
                    rs.getString("dept_name"),
                    rs.getString("head_of_dept"),
                    rs.getInt("total_employees"),
                    rs.getInt("established_year")
                );
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    //FOR THE DEPArtment combo box
    public List<String> getAllDepartmentNames() {
    List<String> departments = new ArrayList<>();
    String sql = "SELECT dept_name FROM departments"; // Adjust column/table name as per your DB

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            departments.add(rs.getString("dept_name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return departments;
}

    
    
}
