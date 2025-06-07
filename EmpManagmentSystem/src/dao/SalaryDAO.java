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
import model.Salary;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class SalaryDAO {
    private Connection conn;

    public SalaryDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addSalary(Salary salary) {
        String sql = "INSERT INTO salaries (salary_code, emp_id, basic_salary, bonus, deductions, pay_month) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salary.getSalaryCode());
            stmt.setString(2, salary.getEmpId());
            stmt.setBigDecimal(3, salary.getBasicSalary());
            stmt.setBigDecimal(4, salary.getBonus());
            stmt.setBigDecimal(5, salary.getDeductions());
            stmt.setString(6, salary.getPayMonth());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSalary(Salary salary) {
        String sql = "UPDATE salaries SET emp_id = ?, basic_salary = ?, bonus = ?, deductions = ?, pay_month = ? WHERE salary_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salary.getEmpId());
            stmt.setBigDecimal(2, salary.getBasicSalary());
            stmt.setBigDecimal(3, salary.getBonus());
            stmt.setBigDecimal(4, salary.getDeductions());
            stmt.setString(5, salary.getPayMonth());
            stmt.setString(6, salary.getSalaryCode());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSalary(String salaryCode) {
        String sql = "DELETE FROM salaries WHERE salary_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salaryCode);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Salary> getAllSalaries() {
        List<Salary> list = new ArrayList<>();
        String sql = "SELECT * FROM salaries";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Salary salary = new Salary(
                        rs.getString("salary_code"),
                        rs.getString("emp_id"),
                        rs.getBigDecimal("basic_salary"),
                        rs.getBigDecimal("bonus"),
                        rs.getBigDecimal("deductions"),
                        rs.getString("pay_month")
                );
                list.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public boolean employeeExists(String empId) {
    String query = "SELECT COUNT(*) FROM employees WHERE emp_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, empId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    


}
