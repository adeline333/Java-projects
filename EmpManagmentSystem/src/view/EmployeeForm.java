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
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import dao.EmployeeDAO;
import dao.DepartmentDAO;
import dao.DBConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;

public class EmployeeForm {
    private JFrame frame;
    private JTextField txtEmpId, txtFullName, txtGender, txtEmail, txtPhone;
    private JComboBox<String> cmbDept;
    private JTable table;
    private DefaultTableModel model;
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    public EmployeeForm() {
        try {
            employeeDAO = new EmployeeDAO(DBConnection.getConnection());
            departmentDAO = new DepartmentDAO(DBConnection.getConnection());
            initialize();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initialize() {
        frame = new JFrame("👨‍💼 Employee Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(232, 248, 255);
                Color color2 = new Color(207, 233, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 30, 15, 30));

        // Navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        navPanel.setOpaque(false);

        JButton btnHome = createNavButton("🏠 Home");
        JButton btnEmployees = createNavButton("👨‍💼 Employees");
        JButton btnSalaries = createNavButton("💰 Salaries");
        JButton btnDepartments = createNavButton("🏬 Departments");
        JButton btnLogout = createNavButton("🚪 Logout");

        btnHome.addActionListener(e -> {
            frame.dispose();
            new HomePage();
        });

        btnEmployees.addActionListener(e -> {
            frame.dispose();
            new EmployeeForm(); // current form
        });

        btnSalaries.addActionListener(e -> {
            frame.dispose();
            new SalaryForm();
        });

        btnDepartments.addActionListener(e -> {
            frame.dispose();
            new DepartmentForm();
        });

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                new LoginForm();
            }
        });

        navPanel.add(btnHome);
        navPanel.add(btnEmployees);
        navPanel.add(btnSalaries);
        navPanel.add(btnDepartments);
        navPanel.add(btnLogout);
        mainPanel.add(navPanel);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 12, 12));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 215), 1, true),
            "Employee Details", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16), Color.DARK_GRAY
        ));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        formPanel.add(createLabel("Employee ID:", labelFont));
        txtEmpId = createTextField();
        formPanel.add(txtEmpId);

        formPanel.add(createLabel("Full Name:", labelFont));
        txtFullName = createTextField();
        formPanel.add(txtFullName);

        formPanel.add(createLabel("Gender:", labelFont));
        txtGender = createTextField();
        formPanel.add(txtGender);

        formPanel.add(createLabel("Email:", labelFont));
        txtEmail = createTextField();
        formPanel.add(txtEmail);

        formPanel.add(createLabel("Phone:", labelFont));
        txtPhone = createTextField();
        formPanel.add(txtPhone);

        formPanel.add(createLabel("Department:", labelFont));
        cmbDept = new JComboBox<>();
        cmbDept.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        populateDepartments();
        formPanel.add(cmbDept);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(formPanel);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnPanel.setOpaque(false);

        JButton btnAdd = createActionButton("➕ Add");
        JButton btnUpdate = createActionButton("✏️ Update");
        JButton btnDelete = createActionButton("🗑️ Delete");
        JButton btnClear = createActionButton("🧹 Clear");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        mainPanel.add(btnPanel);

        // Table
        model = new DefaultTableModel(new Object[]{"Emp ID", "Name", "Gender", "Email", "Phone", "Dept"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(22);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "📚 Employees List", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), Color.DARK_GRAY
        ));

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(scrollPane);

        // Button actions
        btnAdd.addActionListener(e -> {
            Employee emp = getEmpFromForm();
            if (emp != null && employeeDAO.addEmployee(emp)) {
                JOptionPane.showMessageDialog(frame, "✅ Employee added successfully!");
                refreshTable();
                clearForm();
            }
        });

        btnUpdate.addActionListener(e -> {
            Employee emp = getEmpFromForm();
            if (emp != null && employeeDAO.updateEmployee(emp)) {
                JOptionPane.showMessageDialog(frame, "✅ Employee updated successfully!");
                refreshTable();
                clearForm();
            }
        });

        btnDelete.addActionListener(e -> {
            String empId = txtEmpId.getText().trim();
            if (empId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ Enter employee ID to delete.");
            } else if (employeeDAO.deleteEmployee(empId)) {
                JOptionPane.showMessageDialog(frame, "🗑️ Employee deleted successfully!");
                refreshTable();
                clearForm();
            }
        });

        btnClear.addActionListener(e -> clearForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtEmpId.setText(model.getValueAt(row, 0).toString());
                txtFullName.setText(model.getValueAt(row, 1).toString());
                txtGender.setText(model.getValueAt(row, 2).toString());
                txtEmail.setText(model.getValueAt(row, 3).toString());
                txtPhone.setText(model.getValueAt(row, 4).toString());
                cmbDept.setSelectedItem(model.getValueAt(row, 5).toString());
            }
        });

        refreshTable();
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void populateDepartments() {
        cmbDept.removeAllItems();
        List<String> departments = departmentDAO.getAllDepartmentNames(); // Assuming this method exists
        for (String dept : departments) {
            cmbDept.addItem(dept);
        }
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return textField;
    }

    private JButton createActionButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 153, 204));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setPreferredSize(new Dimension(130, 40));
        return button;
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 123, 167));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(8, 18, 8, 18));
        return button;
    }

    private Employee getEmpFromForm() {
        String empId = txtEmpId.getText().trim();
        String name = txtFullName.getText().trim();
        String gender = txtGender.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String dept = cmbDept.getSelectedItem() != null ? cmbDept.getSelectedItem().toString() : "";

        if (empId.isEmpty() || name.isEmpty() || gender.isEmpty() || email.isEmpty() || phone.isEmpty() || dept.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "❌ Please fill in all fields.");
            return null;
        }

        if (!empId.matches("^E\\d*")) {
            JOptionPane.showMessageDialog(frame, "❌ Employee ID must start with 'E' followed by digits.");
            return null;
        }

        if (!name.matches("^[A-Za-z ]+$")) {
            JOptionPane.showMessageDialog(frame, "❌ Full Name should contain letters only.");
            return null;
        }

        if (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
            JOptionPane.showMessageDialog(frame, "❌ Gender must be 'Male' or 'Female'.");
            return null;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            JOptionPane.showMessageDialog(frame, "❌ Invalid email format.");
            return null;
        }

        if (!phone.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(frame, "❌ Phone number must be 10 digits.");
            return null;
        }

        return new Employee(empId, name, gender, email, phone, dept);
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee emp : employees) {
            model.addRow(new Object[]{emp.getEmpId(), emp.getFullName(), emp.getGender(), emp.getEmail(), emp.getPhone(), emp.getDepartmentName()});
        }
    }

    private void clearForm() {
        txtEmpId.setText("");
        txtFullName.setText("");
        txtGender.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        cmbDept.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new EmployeeForm();
    }
}
