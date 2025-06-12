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
import java.math.BigDecimal;
import java.util.List;
import dao.SalaryDAO;
import dao.DBConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Salary;

public class SalaryForm {
    private JFrame frame;
    private JTextField txtSalaryCode, txtEmpId, txtBasic, txtBonus, txtDeductions, txtPayMonth;
    private JTable table;
    private DefaultTableModel model;
    private SalaryDAO salaryDAO;

    public SalaryForm() {
        try {
            salaryDAO = new SalaryDAO(DBConnection.getConnection());
            initialize();
        } catch (SQLException ex) {
            Logger.getLogger(SalaryForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initialize() {
        frame = new JFrame("💰 Salary Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
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
        JButton btnDepartments = createNavButton("🏬 Departments");
        JButton btnSalaries = createNavButton("💰 Salaries");
        JButton btnLogout = createNavButton("🚪 Logout");

        btnHome.addActionListener(e -> {
            frame.dispose();
            new HomePage();
        });
        btnEmployees.addActionListener(e -> {
            frame.dispose();
            new EmployeeForm();
        });
        btnDepartments.addActionListener(e -> {
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
                new LoginForm();
            }
        });

        navPanel.add(btnHome);
        navPanel.add(btnEmployees);
        navPanel.add(btnDepartments);
        navPanel.add(btnSalaries);
        navPanel.add(btnLogout);
        mainPanel.add(navPanel);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 12, 12));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 215), 1, true),
            "Salary Details", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16), Color.DARK_GRAY
        ));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        formPanel.add(createLabel("Salary Code:", labelFont));
        txtSalaryCode = createTextField();
        formPanel.add(txtSalaryCode);

        formPanel.add(createLabel("Employee ID:", labelFont));
        txtEmpId = createTextField();
        formPanel.add(txtEmpId);

        formPanel.add(createLabel("Basic Salary:", labelFont));
        txtBasic = createTextField();
        formPanel.add(txtBasic);

        formPanel.add(createLabel("Bonus:", labelFont));
        txtBonus = createTextField();
        formPanel.add(txtBonus);

        formPanel.add(createLabel("Deductions:", labelFont));
        txtDeductions = createTextField();
        formPanel.add(txtDeductions);

        formPanel.add(createLabel("Pay Month:", labelFont));
        txtPayMonth = createTextField();
        formPanel.add(txtPayMonth);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(formPanel);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnPanel.setOpaque(false);

        JButton btnAdd = createActionButton("➕ Add");
        JButton btnUpdate = createActionButton("✏️ Update");
        JButton btnDelete = createActionButton("🗑️ Delete");
        JButton btnClear = createActionButton("🧹 Clear");

        btnAdd.addActionListener(e -> {
            Salary salary = getSalaryFromForm();
            if (salary != null && salaryDAO.addSalary(salary)) {
                JOptionPane.showMessageDialog(frame, "✅ Salary record added!");
                refreshTable();
                clearForm();
            }
        });

        btnUpdate.addActionListener(e -> {
            Salary salary = getSalaryFromForm();
            if (salary != null && salaryDAO.updateSalary(salary)) {
                JOptionPane.showMessageDialog(frame, "✅ Salary record updated!");
                refreshTable();
                clearForm();
            }
        });

        btnDelete.addActionListener(e -> {
            String code = txtSalaryCode.getText();
            if (salaryDAO.deleteSalary(code)) {
                JOptionPane.showMessageDialog(frame, "🗑️ Salary record deleted!");
                refreshTable();
                clearForm();
            }
        });

        btnClear.addActionListener(e -> clearForm());

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        mainPanel.add(btnPanel);

        // Table
        model = new DefaultTableModel(new Object[]{"Salary Code", "Emp ID", "Basic", "Bonus", "Deductions", "Pay Month"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(22);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "📚 Salaries List", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), Color.DARK_GRAY
        ));

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(scrollPane);

        // Table Selection
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtSalaryCode.setText(model.getValueAt(row, 0).toString());
                txtEmpId.setText(model.getValueAt(row, 1).toString());
                txtBasic.setText(model.getValueAt(row, 2).toString());
                txtBonus.setText(model.getValueAt(row, 3).toString());
                txtDeductions.setText(model.getValueAt(row, 4).toString());
                txtPayMonth.setText(model.getValueAt(row, 5).toString());
            }
        });

        refreshTable();
        frame.add(mainPanel);
        frame.setVisible(true);
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

    // ✅ Check if employee exists
    if (!salaryDAO.employeeExists(empId)) {
        JOptionPane.showMessageDialog(frame, "❌ Employee ID '" + empId + "' does not exist in the database.");
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