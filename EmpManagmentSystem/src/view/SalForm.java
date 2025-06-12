/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author adeli
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;
import dao.SalaryDAO;
import dao.DBConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Salary;

public class SalForm {
    private JFrame frame;
    private JTextField txtSalaryCode, txtEmpId, txtBasic, txtBonus, txtDeductions, txtPayMonth;
    private JTable table;
    private DefaultTableModel model;
    private SalaryDAO salaryDAO;

    public SalForm() {
        try {
            salaryDAO = new SalaryDAO(DBConnection.getConnection());
            initialize();
        } catch (SQLException ex) {
            Logger.getLogger(SalaryForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initialize() {
        frame = new JFrame("Salary Management");
        frame.setSize(850, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Navigation Panel
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnHome = new JButton("Home");
        JButton btnEmployeeForm = new JButton("Employee");
        JButton btnDepartmentForm = new JButton("Department");
        JButton btnSalaries = new JButton("Salary");
        JButton btnLogout = new JButton("Logout");

        navPanel.add(btnHome);
        navPanel.add(btnEmployeeForm);
        navPanel.add(btnDepartmentForm);
        navPanel.add(btnSalaries);
        navPanel.add(btnLogout);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Salary Code:"));
        txtSalaryCode = new JTextField();
        formPanel.add(txtSalaryCode);

        formPanel.add(new JLabel("Employee ID:"));
        txtEmpId = new JTextField();
        formPanel.add(txtEmpId);

        formPanel.add(new JLabel("Basic Salary:"));
        txtBasic = new JTextField();
        formPanel.add(txtBasic);

        formPanel.add(new JLabel("Bonus:"));
        txtBonus = new JTextField();
        formPanel.add(txtBonus);

        formPanel.add(new JLabel("Deductions:"));
        txtDeductions = new JTextField();
        formPanel.add(txtDeductions);

        formPanel.add(new JLabel("Pay Month:"));
        txtPayMonth = new JTextField();
        formPanel.add(txtPayMonth);

        // Create a new vertical panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Add both navPanel and formPanel inside it
        topPanel.add(navPanel);
        topPanel.add(formPanel);

        // Add topPanel to the top of the frame
        frame.add(topPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new Object[]{"Salary Code", "Emp ID", "Basic", "Bonus", "Deductions", "Pay Month"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        frame.add(btnPanel, BorderLayout.SOUTH);

        // Button Actions
        btnHome.addActionListener(e -> {
            frame.dispose();
            new HomePage(); // Ensure HomePage class exists
        });

        btnEmployeeForm.addActionListener(e -> {
            frame.dispose();
            new EmployeeForm();
        });

        btnDepartmentForm.addActionListener(e -> {
            frame.dispose();
            new DepartmentForm();
        });

        btnSalaries.addActionListener(e -> {
            frame.dispose();
            new SalaryForm();
        });

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                new LoginForm(); // Ensure LoginForm exists
            }
        });

        btnAdd.addActionListener(e -> {
            Salary salary = getSalaryFromForm();
            if (salary != null && salaryDAO.addSalary(salary)) {
                JOptionPane.showMessageDialog(frame, "Salary record added!");
                refreshTable();
                clearForm();
            }
        });

        btnUpdate.addActionListener(e -> {
            Salary salary = getSalaryFromForm();
            if (salary != null && salaryDAO.updateSalary(salary)) {
                JOptionPane.showMessageDialog(frame, "Salary record updated!");
                refreshTable();
                clearForm();
            }
        });

        btnDelete.addActionListener(e -> {
            String code = txtSalaryCode.getText();
            if (salaryDAO.deleteSalary(code)) {
                JOptionPane.showMessageDialog(frame, "Salary record deleted!");
                refreshTable();
                clearForm();
            }
        });

        btnClear.addActionListener(e -> clearForm());

        // Load table data
        refreshTable();
        frame.setVisible(true);
    }

    private Salary getSalaryFromForm() {
        String salaryCode = txtSalaryCode.getText().trim();
        String empId = txtEmpId.getText().trim();
        String basicStr = txtBasic.getText().trim();
        String bonusStr = txtBonus.getText().trim();
        String deductionsStr = txtDeductions.getText().trim();
        String payMonth = txtPayMonth.getText().trim();

        if (salaryCode.isEmpty() || !salaryCode.toUpperCase().startsWith("S")) {
            JOptionPane.showMessageDialog(frame, "❌ Salary Code must not be empty and should start with 'S'.");
            return null;
        }

        if (empId.isEmpty() || !empId.toUpperCase().startsWith("E")) {
            JOptionPane.showMessageDialog(frame, "❌ Employee ID must not be empty and should start with 'E'.");
            return null;
        }

        BigDecimal basicSalary;
        try {
            basicSalary = new BigDecimal(basicStr);
            if (basicSalary.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(frame, "❌ Basic Salary must be greater than zero.");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "❌ Basic Salary must be a valid number.");
            return null;
        }

        BigDecimal bonus;
        try {
            bonus = new BigDecimal(bonusStr);
            if (bonus.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(frame, "❌ Bonus cannot be negative.");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "❌ Bonus must be a valid number.");
            return null;
        }

        BigDecimal deductions;
        try {
            deductions = new BigDecimal(deductionsStr);
            if (deductions.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(frame, "❌ Deductions cannot be negative.");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "❌ Deductions must be a valid number.");
            return null;
        }

        if (payMonth.isEmpty() || !payMonth.matches("^(January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{4}$")) {
            JOptionPane.showMessageDialog(frame, "❌ Pay Month must be in the format 'MonthName Year' (e.g., April 2025).");
            return null;
        }

        return new Salary(salaryCode, empId, basicSalary, bonus, deductions, payMonth);
    }

    private void clearForm() {
        txtSalaryCode.setText("");
        txtEmpId.setText("");
        txtBasic.setText("");
        txtBonus.setText("");
        txtDeductions.setText("");
        txtPayMonth.setText("");
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Salary> list = salaryDAO.getAllSalaries();
        for (Salary s : list) {
            model.addRow(new Object[]{
                    s.getSalaryCode(),
                    s.getEmpId(),
                    s.getBasicSalary(),
                    s.getBonus(),
                    s.getDeductions(),
                    s.getPayMonth()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SalaryForm::new);
    }
}
