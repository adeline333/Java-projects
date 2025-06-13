package view;

import controller.EmployeeClientController;
import model.Department;
import model.Employee;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.List;

public class EmployeeForm extends JFrame {
    private JTextField empIdField, fullNameField, genderField, emailField, phoneField, deptCodeField;
    private JTextField searchField;
    private JButton registerButton, updateButton, departmentButton, salaryButton, logoutButton,
            deleteButton, clearButton, refreshButton, exportButton;
    private JButton searchButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private EmployeeClientController controller = new EmployeeClientController();

    public EmployeeForm() {
        if (!SessionManager.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Please login first.");
            new logInnForm().setVisible(true);
            this.dispose();
            return;
        }

        System.out.println("Welcome, " + SessionManager.getCurrentUser());
        System.out.println("Is Logged In? " + SessionManager.isLoggedIn());
        System.out.println("Current User: " + SessionManager.getCurrentUser());
        System.out.println("Currently on the Employee form ");

        initComponents();
        loadTableData();
    }

    private void initComponents() {
        setTitle("Employee Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(950, 650);
        setLocationRelativeTo(null);

        // Custom panel for gradient background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(178, 232, 232), 0, getHeight(), new Color(245, 247, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 950, 650);
        setContentPane(backgroundPanel);

        // Title
        JLabel titleLabel = new JLabel("Employee Management");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 163, 163));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 20, 950, 50);
        backgroundPanel.add(titleLabel);

        // Input panel with rounded corners
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(30, 120, 380, 270);
        inputPanel.setBackground(new Color(232, 236, 239));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 5)
        ));

        // Labels
        JLabel lblEmpId = new JLabel("Employee ID:");
        lblEmpId.setFont(new Font("Roboto", Font.BOLD, 15));
        lblEmpId.setForeground(new Color(74, 74, 74));
        lblEmpId.setBounds(20, 20, 120, 30);
        JLabel lblFullName = new JLabel("Full Name:");
        lblFullName.setFont(new Font("Roboto", Font.BOLD, 15));
        lblFullName.setForeground(new Color(74, 74, 74));
        lblFullName.setBounds(20, 60, 120, 30);
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Roboto", Font.BOLD, 15));
        lblGender.setForeground(new Color(74, 74, 74));
        lblGender.setBounds(20, 100, 120, 30);
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Roboto", Font.BOLD, 15));
        lblEmail.setForeground(new Color(74, 74, 74));
        lblEmail.setBounds(20, 140, 120, 30);
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(new Font("Roboto", Font.BOLD, 15));
        lblPhone.setForeground(new Color(74, 74, 74));
        lblPhone.setBounds(20, 180, 120, 30);
        JLabel lblDeptCode = new JLabel("Dept Code:");
        lblDeptCode.setFont(new Font("Roboto", Font.BOLD, 15));
        lblDeptCode.setForeground(new Color(74, 74, 74));
        lblDeptCode.setBounds(20, 220, 120, 30);

        // Text fields with rounded corners
        empIdField = new JTextField();
        empIdField.setBackground(Color.WHITE);
        empIdField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        empIdField.setBounds(150, 20, 200, 35);

        fullNameField = new JTextField();
        fullNameField.setBackground(Color.WHITE);
        fullNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        fullNameField.setBounds(150, 60, 200, 35);

        genderField = new JTextField();
        genderField.setBackground(Color.WHITE);
        genderField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        genderField.setBounds(150, 100, 200, 35);

        emailField = new JTextField();
        emailField.setBackground(Color.WHITE);
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        emailField.setBounds(150, 140, 200, 35);

        phoneField = new JTextField();
        phoneField.setBackground(Color.WHITE);
        phoneField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        phoneField.setBounds(150, 180, 200, 35);

        deptCodeField = new JTextField();
        deptCodeField.setBackground(Color.WHITE);
        deptCodeField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        deptCodeField.setBounds(150, 220, 200, 35);

        searchField = new JTextField();
        searchField.setBackground(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setBounds(440, 180, 200, 35);

        // Define colors
        Color mainTeal = new Color(0, 163, 163);
        Color hoverTeal = new Color(0, 190, 190);
        Color pressedTeal = new Color(0, 140, 140);

        // Buttons with rounded corners
        registerButton = new JButton("Register");
        styleButton(registerButton, mainTeal, hoverTeal, pressedTeal);
        registerButton.setBounds(440, 220, 120, 35);

        updateButton = new JButton("Update");
        styleButton(updateButton, mainTeal, hoverTeal, pressedTeal);
        updateButton.setBounds(570, 220, 120, 35);

        deleteButton = new JButton("Delete");
        styleButton(deleteButton, mainTeal, hoverTeal, pressedTeal);
        deleteButton.setBounds(700, 220, 120, 35);

        clearButton = new JButton("Clear");
        styleButton(clearButton, mainTeal, hoverTeal, pressedTeal);
        clearButton.setBounds(440, 260, 120, 35);

        refreshButton = new JButton("Refresh Table");
        styleButton(refreshButton, mainTeal, hoverTeal, pressedTeal);
        refreshButton.setBounds(570, 260, 120, 35);

        exportButton = new JButton("GET Excel");
        styleButton(exportButton, mainTeal, hoverTeal, pressedTeal);
        exportButton.setBounds(700, 260, 120, 35);

        searchButton = new JButton("Search");
        styleButton(searchButton, mainTeal, hoverTeal, pressedTeal);
        searchButton.setBounds(650, 180, 120, 35);

        departmentButton = new JButton("Department");
        styleButton(departmentButton, mainTeal, hoverTeal, pressedTeal);
        departmentButton.setBounds(30, 80, 120, 35);

        salaryButton = new JButton("Salary");
        styleButton(salaryButton, mainTeal, hoverTeal, pressedTeal);
        salaryButton.setBounds(160, 80, 120, 35);

        logoutButton = new JButton("Logout");
        styleButton(logoutButton, mainTeal, hoverTeal, pressedTeal);
        logoutButton.setBounds(290, 80, 120, 35);

        // Table with rounded scroll pane
        String[] columns = {"Emp ID", "Full Name", "Gender", "Email", "Phone", "Dept Code"};
        tableModel = new DefaultTableModel(columns, 0);
        employeeTable = new JTable(tableModel);
        employeeTable.setRowHeight(30);
        employeeTable.setGridColor(new Color(200, 200, 200));
        employeeTable.setBackground(Color.WHITE);
        employeeTable.setSelectionBackground(mainTeal);
        employeeTable.setSelectionForeground(Color.WHITE);
        employeeTable.setFont(new Font("Roboto", Font.PLAIN, 13));
        employeeTable.setShowGrid(true);
        employeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        employeeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 247, 249));
                }
                return c;
            }
        });
        JTableHeader header = employeeTable.getTableHeader();
        header.setBackground(mainTeal);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.setBounds(30, 400, 890, 200);

        // Add components to input panel
        inputPanel.add(lblEmpId);
        inputPanel.add(empIdField);
        inputPanel.add(lblFullName);
        inputPanel.add(fullNameField);
        inputPanel.add(lblGender);
        inputPanel.add(genderField);
        inputPanel.add(lblEmail);
        inputPanel.add(emailField);
        inputPanel.add(lblPhone);
        inputPanel.add(phoneField);
        inputPanel.add(lblDeptCode);
        inputPanel.add(deptCodeField);

        // Add components to background panel
        backgroundPanel.add(inputPanel);
        backgroundPanel.add(searchField);
        backgroundPanel.add(searchButton);
        backgroundPanel.add(registerButton);
        backgroundPanel.add(updateButton);
        backgroundPanel.add(deleteButton);
        backgroundPanel.add(clearButton);
        backgroundPanel.add(refreshButton);
        backgroundPanel.add(exportButton);
        backgroundPanel.add(departmentButton);
        backgroundPanel.add(salaryButton);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(scrollPane);

        // Button actions
        registerButton.addActionListener(e -> registerEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadTableData());
        exportButton.addActionListener(e -> exportToExcel());
        searchButton.addActionListener(e -> searchEmployees());

        departmentButton.addActionListener(e -> {
            new DepartmentForm().setVisible(true);
            this.dispose();
        });

        salaryButton.addActionListener(e -> {
            new SalaryForm().setVisible(true);
            dispose();
        });

        logoutButton.addActionListener(e -> {
            SessionManager.logout();
            new logInnForm().setVisible(true);
            dispose();
        });

        // Table row click
        employeeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = employeeTable.getSelectedRow();
                empIdField.setText(tableModel.getValueAt(row, 0).toString());
                fullNameField.setText(tableModel.getValueAt(row, 1).toString());
                genderField.setText(tableModel.getValueAt(row, 2).toString());
                emailField.setText(tableModel.getValueAt(row, 3).toString());
                phoneField.setText(tableModel.getValueAt(row, 4).toString());
                deptCodeField.setText(tableModel.getValueAt(row, 5).toString());
            }
        });
    }

    private void styleButton(JButton button, Color bgColor, Color hoverColor, Color pressedColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Roboto", Font.BOLD, 14));
        button.setBorder(new RoundedBorder(15, new Color(0, 140, 140)));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(pressedColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(hoverColor);
            }
        });
    }

    private static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color borderColor;

        public RoundedBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(borderColor);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 15, 5, 15);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = 15;
            insets.top = 5;
            insets.right = 15;
            insets.bottom = 5;
            return insets;
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    private void registerEmployee() {
        if (!validateFields()) {
            return;
        }
        try {
            Employee emp = getEmployeeFromFields();
            String result = controller.registerEmployee(emp);
            JOptionPane.showMessageDialog(this, result);
            loadTableData();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Registration failed: " + ex.getMessage());
        }
    }

    private void updateEmployee() {
        if (!validateFields()) {
            return;
        }
        try {
            Employee emp = getEmployeeFromFields();
            String result = controller.updateEmployee(emp);
            JOptionPane.showMessageDialog(this, result);
            loadTableData();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }

    private void deleteEmployee() {
        try {
            Employee emp = new Employee();
            emp.setEmpId(empIdField.getText());
            String result = controller.deleteEmployee(emp);
            JOptionPane.showMessageDialog(this, result);
            loadTableData();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + ex.getMessage());
        }
    }

    private void loadTableData() {
        try {
            tableModel.setRowCount(0);
            List<Employee> list = controller.getAllEmployees();
            for (Employee e : list) {
                tableModel.addRow(new Object[]{
                    e.getEmpId(),
                    e.getFullName(),
                    e.getGender(),
                    e.getEmail(),
                    e.getPhone(),
                    e.getDepartment() != null ? e.getDepartment().getDeptCode() : ""
                });
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error loading employees: " + e.getMessage());
        }
    }

    private void clearFields() {
        empIdField.setText("");
        fullNameField.setText("");
        genderField.setText("");
        emailField.setText("");
        phoneField.setText("");
        deptCodeField.setText("");
    }

    private Employee getEmployeeFromFields() {
        Employee emp = new Employee();
        emp.setEmpId(empIdField.getText());
        emp.setFullName(fullNameField.getText());
        emp.setGender(genderField.getText());
        emp.setEmail(emailField.getText());
        emp.setPhone(phoneField.getText());

        Department dept = new Department();
        dept.setDeptCode(deptCodeField.getText());
        emp.setDepartment(dept);

        return emp;
    }

    private void exportToExcel() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("employees.csv"));
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.print(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    writer.print(",");
                }
            }
            writer.println();

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.print(tableModel.getValueAt(i, j));
                    if (j < tableModel.getColumnCount() - 1) {
                        writer.print(",");
                    }
                }
                writer.println();
            }

            writer.close();
            JOptionPane.showMessageDialog(this, "Exported successfully to employees.csv");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Export failed: " + e.getMessage());
        }
    }

    private void searchEmployees() {
        String keyword = searchField.getText().trim().toLowerCase();
        try {
            tableModel.setRowCount(0);
            List<Employee> list = controller.getAllEmployees();
            for (Employee e : list) {
                String[] values = {
                    e.getEmpId(),
                    e.getFullName(),
                    e.getGender(),
                    e.getEmail(),
                    e.getPhone(),
                    e.getDepartment() != null ? e.getDepartment().getDeptCode() : ""
                };

                boolean matches = false;
                for (String val : values) {
                    if (val != null && val.toLowerCase().contains(keyword)) {
                        matches = true;
                        break;
                    }
                }

                if (matches) {
                    tableModel.addRow(values);
                }
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Search failed: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        String empId = empIdField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String gender = genderField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String deptCode = deptCodeField.getText().trim();

        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Employee ID is required.");
            empIdField.requestFocus();
            return false;
        }

        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Full Name is required.");
            fullNameField.requestFocus();
            return false;
        }
        if (!fullName.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Full Name can only contain letters and spaces.");
            fullNameField.requestFocus();
            return false;
        }

        if (gender.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Gender is required.");
            genderField.requestFocus();
            return false;
        }
        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female") && !gender.equalsIgnoreCase("Other")) {
            JOptionPane.showMessageDialog(this, "Gender must be Male, Female, or Other.");
            genderField.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email is required.");
            emailField.requestFocus();
            return false;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            emailField.requestFocus();
            return false;
        }

        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone is required.");
            phoneField.requestFocus();
            return false;
        }
        if (!phone.matches("\\d{7,15}")) {
            JOptionPane.showMessageDialog(this, "Phone must be digits only (7-15 digits).");
            phoneField.requestFocus();
            return false;
        }

        if (deptCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Department Code is required.");
            deptCodeField.requestFocus();
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeForm().setVisible(true));
    }
}


//
//
//package view;
//
//import controller.EmployeeClientController;
//import model.Department;
//import model.Employee;
//
//import javax.swing.*;
//import javax.swing.border.AbstractBorder;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.rmi.RemoteException;
//import java.util.List;
//
//public class EmployeeForm extends JFrame {
//    private JTextField empIdField, fullNameField, genderField, emailField, phoneField, deptCodeField;
//    private JTextField searchField;
//    private JButton registerButton, updateButton, departmentButton, salaryButton, logoutButton,
//            deleteButton, clearButton, refreshButton, exportButton;
//    private JButton searchButton;
//    private JTable employeeTable;
//    private DefaultTableModel tableModel;
//    private EmployeeClientController controller = new EmployeeClientController();
//
//    public EmployeeForm() {
//        setTitle("Employee Management");
//        setSize(950, 650);
//        setLayout(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        // Custom panel for gradient background
//        JPanel backgroundPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                GradientPaint gp = new GradientPaint(0, 0, new Color(178, 232, 232), 0, getHeight(), new Color(245, 247, 250));
//                g2d.setPaint(gp);
//                g2d.fillRect(0, 0, getWidth(), getHeight());
//            }
//        };
//        backgroundPanel.setLayout(null);
//        backgroundPanel.setBounds(0, 0, 950, 650);
//        setContentPane(backgroundPanel);
//
//        // Title
//        JLabel titleLabel = new JLabel("Employee");
//        titleLabel.setFont(new Font("Roboto", Font.BOLD, 36));
//        titleLabel.setForeground(new Color(0, 163, 163));
//        titleLabel.setHorizontalAlignment(JLabel.CENTER);
//        titleLabel.setBounds(0, 20, 950, 50);
//        backgroundPanel.add(titleLabel);
//
//        // Navigation buttons
//        departmentButton = new JButton("Department");
//        styleButton(departmentButton, new Color(0, 163, 163), new Color(0, 190, 190), new Color(0, 140, 140));
//        departmentButton.setBounds(30, 80, 120, 35);
//        backgroundPanel.add(departmentButton);
//
//        salaryButton = new JButton("Salary");
//        styleButton(salaryButton, new Color(0, 163, 163), new Color(0, 190, 190), new Color(0, 140, 140));
//        salaryButton.setBounds(160, 80, 120, 35);
//        backgroundPanel.add(salaryButton);
//
//        logoutButton = new JButton("Logout");
//        styleButton(logoutButton, new Color(0, 163, 163), new Color(0, 190, 190), new Color(0, 140, 140));
//        logoutButton.setBounds(290, 80, 120, 35);
//        backgroundPanel.add(logoutButton);
//
//        // Input panel with rounded corners
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(null);
//        inputPanel.setBounds(30, 120, 380, 260);
//        inputPanel.setBackground(new Color(232, 236, 239));
//        inputPanel.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(10, 10, 10, 10)
//        ));
//        backgroundPanel.add(inputPanel);
//
//        // Labels and Fields
//        JLabel lblEmpId = new JLabel("Employee ID:");
//        lblEmpId.setFont(new Font("Roboto", Font.BOLD, 15));
//        lblEmpId.setForeground(new Color(74, 74, 74));
//        lblEmpId.setBounds(20, 20, 120, 30);
//        inputPanel.add(lblEmpId);
//
//        empIdField = new JTextField();
//        empIdField.setBackground(Color.WHITE);
//        empIdField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 10, 5, 10)
//        ));
//        empIdField.setBounds(150, 20, 200, 35);
//        inputPanel.add(empIdField);
//
//        JLabel lblFullName = new JLabel("Full Name:");
//        lblFullName.setFont(new Font("Roboto", Font.BOLD, 15));
//        lblFullName.setForeground(new Color(74, 74, 74));
//        lblFullName.setBounds(20, 60, 120, 30);
//        inputPanel.add(lblFullName);
//
//        fullNameField = new JTextField();
//        fullNameField.setBackground(Color.WHITE);
//        fullNameField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 10, 5, 10)
//        ));
//        fullNameField.setBounds(150, 60, 200, 35);
//        inputPanel.add(fullNameField);
//
//        JLabel lblGender = new JLabel("Gender:");
//        lblGender.setFont(new Font("Roboto", Font.BOLD, 15));
//        lblGender.setForeground(new Color(74, 74, 74));
//        lblGender.setBounds(20, 100, 120, 30);
//        inputPanel.add(lblGender);
//
//        genderField = new JTextField();
//        genderField.setBackground(Color.WHITE);
//        genderField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 10, 5, 10)
//        ));
//        genderField.setBounds(150, 100, 200, 35);
//        inputPanel.add(genderField);
//
//        JLabel lblEmail = new JLabel("Email:");
//        lblEmail.setFont(new Font("Roboto", Font.BOLD, 15));
//        lblEmail.setForeground(new Color(74, 74, 74));
//        lblEmail.setBounds(20, 140, 120, 30);
//        inputPanel.add(lblEmail);
//
//        emailField = new JTextField();
//        emailField.setBackground(Color.WHITE);
//        emailField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 10, 5, 10)
//        ));
//        emailField.setBounds(150, 140, 200, 35);
//        inputPanel.add(emailField);
//
//        JLabel lblPhone = new JLabel("Phone:");
//        lblPhone.setFont(new Font("Roboto", Font.BOLD, 15));
//        lblPhone.setForeground(new Color(74, 74, 74));
//        lblPhone.setBounds(20, 180, 120, 30);
//        inputPanel.add(lblPhone);
//
//        phoneField = new JTextField();
//        phoneField.setBackground(Color.WHITE);
//        phoneField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 10, 5, 10)
//        ));
//        phoneField.setBounds(150, 180, 200, 35);
//        inputPanel.add(phoneField);
//
//        JLabel lblDeptCode = new JLabel("Dept Code:");
//        lblDeptCode.setFont(new Font("Roboto", Font.BOLD, 15));
//        lblDeptCode.setForeground(new Color(74, 74, 74));
//        lblDeptCode.setBounds(20, 220, 120, 30);
//        inputPanel.add(lblDeptCode);
//
//        deptCodeField = new JTextField();
//        deptCodeField.setBackground(Color.WHITE);
//        deptCodeField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 10, 5, 10)
//        ));
//        deptCodeField.setBounds(150, 220, 200, 35);
//        inputPanel.add(deptCodeField);
//
//        // Action Buttons
//        Color mainTeal = new Color(0, 163, 163);
//        Color hoverTeal = new Color(0, 190, 190);
//        Color pressedTeal = new Color(0, 140, 140);
//
//        registerButton = new JButton("Register");
//        styleButton(registerButton, mainTeal, hoverTeal, pressedTeal);
//        registerButton.setBounds(440, 120, 100, 35);
//        backgroundPanel.add(registerButton);
//
//        updateButton = new JButton("Update");
//        styleButton(updateButton, mainTeal, hoverTeal, pressedTeal);
//        updateButton.setBounds(550, 120, 100, 35);
//        backgroundPanel.add(updateButton);
//
//        deleteButton = new JButton("Delete");
//        styleButton(deleteButton, mainTeal, hoverTeal, pressedTeal);
//        deleteButton.setBounds(660, 120, 100, 35);
//        backgroundPanel.add(deleteButton);
//
//        clearButton = new JButton("Clear");
//        styleButton(clearButton, mainTeal, hoverTeal, pressedTeal);
//        clearButton.setBounds(770, 120, 100, 35);
//        backgroundPanel.add(clearButton);
//
//        refreshButton = new JButton("Refresh Table");
//        styleButton(refreshButton, mainTeal, hoverTeal, pressedTeal);
//        refreshButton.setBounds(440, 170, 150, 35);
//        backgroundPanel.add(refreshButton);
//
//        // Search Field and Button
//        searchField = new JTextField();
//        searchField.setBackground(Color.WHITE);
//        searchField.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 10, 5, 10)
//        ));
//        searchField.setBounds(600, 170, 200, 35);
//        backgroundPanel.add(searchField);
//
//        searchButton = new JButton("Search");
//        styleButton(searchButton, mainTeal, hoverTeal, pressedTeal);
//        searchButton.setBounds(810, 170, 100, 35);
//        backgroundPanel.add(searchButton);
//
//        // Table
//        String[] columns = {"Emp ID", "Full Name", "Gender", "Email", "Phone", "Dept Code"};
//        tableModel = new DefaultTableModel(columns, 0);
//        employeeTable = new JTable(tableModel);
//        employeeTable.setRowHeight(30);
//        employeeTable.setGridColor(new Color(200, 200, 200));
//        employeeTable.setBackground(Color.WHITE);
//        employeeTable.setSelectionBackground(mainTeal);
//        employeeTable.setSelectionForeground(Color.WHITE);
//        employeeTable.setFont(new Font("Roboto", Font.PLAIN, 13));
//        employeeTable.setShowGrid(true);
//        employeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        employeeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if (!isSelected) {
//                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 247, 249));
//                }
//                return c;
//            }
//        });
//        JTableHeader header = employeeTable.getTableHeader();
//        header.setBackground(mainTeal);
//        header.setForeground(Color.WHITE);
//        header.setFont(new Font("Roboto", Font.BOLD, 14));
//        JScrollPane scrollPane = new JScrollPane(employeeTable);
//        scrollPane.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
//            BorderFactory.createEmptyBorder(5, 5, 5, 5)
//        ));
//        scrollPane.setBounds(30, 220, 890, 250);
//        backgroundPanel.add(scrollPane);
//
//        // Export Button
//        exportButton = new JButton("Export to Excel");
//        styleButton(exportButton, mainTeal, hoverTeal, pressedTeal);
//        exportButton.setBounds(770, 480, 150, 35);
//        backgroundPanel.add(exportButton);
//
//        // Load Data Initially
//        loadTableData();
//
//        // Button Actions
//        registerButton.addActionListener(e -> registerEmployee());
//        updateButton.addActionListener(e -> updateEmployee());
//        deleteButton.addActionListener(e -> deleteEmployee());
//        clearButton.addActionListener(e -> clearFields());
//        refreshButton.addActionListener(e -> loadTableData());
//        exportButton.addActionListener(e -> exportToExcel());
//        searchButton.addActionListener(e -> searchEmployees());
//
//        departmentButton.addActionListener(e -> {
//            new DepartmentForm().setVisible(true);
//            this.dispose();
//        });
//
//        salaryButton.addActionListener(e -> {
//            new SalaryForm().setVisible(true);
//            dispose();
//        });
//
//        logoutButton.addActionListener(e -> {
//            new logInnForm().setVisible(true);
//            this.dispose();
//        });
//
//        // Table Row Click
//        employeeTable.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int row = employeeTable.getSelectedRow();
//                empIdField.setText(tableModel.getValueAt(row, 0).toString());
//                fullNameField.setText(tableModel.getValueAt(row, 1).toString());
//                genderField.setText(tableModel.getValueAt(row, 2).toString());
//                emailField.setText(tableModel.getValueAt(row, 3).toString());
//                phoneField.setText(tableModel.getValueAt(row, 4).toString());
//                deptCodeField.setText(tableModel.getValueAt(row, 5).toString());
//            }
//        });
//
//        setVisible(true);
//    }
//
//    private void styleButton(JButton button, Color bgColor, Color hoverColor, Color pressedColor) {
//        button.setBackground(bgColor);
//        button.setForeground(Color.WHITE);
//        button.setFocusPainted(false);
//        button.setFont(new Font("Roboto", Font.BOLD, 14));
//
//        // Custom rounded border
//        button.setBorder(new RoundedBorder(15, new Color(0, 140, 140)));
//
//        button.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                button.setBackground(hoverColor);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                button.setBackground(bgColor);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                button.setBackground(pressedColor);
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                button.setBackground(hoverColor);
//            }
//        });
//    }
//
//    // Custom border class for rounded corners
//    private static class RoundedBorder extends AbstractBorder {
//        private final int radius;
//        private final Color borderColor;
//
//        public RoundedBorder(int radius, Color borderColor) {
//            this.radius = radius;
//            this.borderColor = borderColor;
//        }
//
//        @Override
//        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//            Graphics2D g2d = (Graphics2D) g.create();
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setColor(borderColor);
//            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
//            g2d.dispose();
//        }
//
//        @Override
//        public Insets getBorderInsets(Component c) {
//            return new Insets(5, 15, 5, 15);
//        }
//
//        @Override
//        public Insets getBorderInsets(Component c, Insets insets) {
//            insets.left = 15;
//            insets.top = 5;
//            insets.right = 15;
//            insets.bottom = 5;
//            return insets;
//        }
//
//        @Override
//        public boolean isBorderOpaque() {
//            return false;
//        }
//    }
//
//    private void registerEmployee() {
//        if (!validateFields()) {
//            return;
//        }
//        try {
//            Employee emp = getEmployeeFromFields();
//            String result = controller.registerEmployee(emp);
//            JOptionPane.showMessageDialog(this, result);
//            loadTableData();
//            clearFields();
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Registration failed: " + ex.getMessage());
//        }
//    }
//
//    private void updateEmployee() {
//    if (!validateFields()) {
//        return; // stop if validation fails
//    }
//    try {
//        Employee emp = getEmployeeFromFields();
//        String result = controller.updateEmployee(emp);
//        JOptionPane.showMessageDialog(this, result);
//        loadTableData();
//        clearFields();
//    } catch (Exception ex) {
//        JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
//    }
//}
//
//    private void deleteEmployee() {
//        try {
//            Employee emp = new Employee();
//            emp.setEmpId(empIdField.getText());
//            String result = controller.deleteEmployee(emp);
//            JOptionPane.showMessageDialog(this, result);
//            loadTableData();
//            clearFields();
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Delete failed: " + ex.getMessage());
//        }
//    }
//
//    private void loadTableData() {
//        try {
//            tableModel.setRowCount(0);
//            List<Employee> list = controller.getAllEmployees();
//            for (Employee e : list) {
//                tableModel.addRow(new Object[]{
//                    e.getEmpId(),
//                    e.getFullName(),
//                    e.getGender(),
//                    e.getEmail(),
//                    e.getPhone(),
//                    e.getDepartment() != null ? e.getDepartment().getDeptCode() : ""
//                });
//            }
//        } catch (RemoteException e) {
//            JOptionPane.showMessageDialog(this, "Error loading employees: " + e.getMessage());
//        }
//    }
//
//    private void clearFields() {
//        empIdField.setText("");
//        fullNameField.setText("");
//        genderField.setText("");
//        emailField.setText("");
//        phoneField.setText("");
//        deptCodeField.setText("");
//    }
//
//    private Employee getEmployeeFromFields() {
//        Employee emp = new Employee();
//        emp.setEmpId(empIdField.getText());
//        emp.setFullName(fullNameField.getText());
//        emp.setGender(genderField.getText());
//        emp.setEmail(emailField.getText());
//        emp.setPhone(phoneField.getText());
//
//        Department dept = new Department();
//        dept.setDeptCode(deptCodeField.getText());
//        emp.setDepartment(dept);
//
//        return emp;
//    }
//
//    private void exportToExcel() {
//        try {
//            PrintWriter writer = new PrintWriter(new FileWriter("employees.csv"));
//            for (int i = 0; i < tableModel.getColumnCount(); i++) {
//                writer.print(tableModel.getColumnName(i));
//                if (i < tableModel.getColumnCount() - 1) {
//                    writer.print(",");
//                }
//            }
//            writer.println();
//
//            for (int i = 0; i < tableModel.getRowCount(); i++) {
//                for (int j = 0; j < tableModel.getColumnCount(); j++) {
//                    writer.print(tableModel.getValueAt(i, j));
//                    if (j < tableModel.getColumnCount() - 1) {
//                        writer.print(",");
//                    }
//                }
//                writer.println();
//            }
//
//            writer.close();
//            JOptionPane.showMessageDialog(this, "Exported successfully to employees.csv");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Export failed: " + e.getMessage());
//        }
//    }
//
//    private void searchEmployees() {
//        String keyword = searchField.getText().trim().toLowerCase();
//        try {
//            tableModel.setRowCount(0);
//            List<Employee> list = controller.getAllEmployees();
//            for (Employee e : list) {
//                String[] values = {
//                    e.getEmpId(),
//                    e.getFullName(),
//                    e.getGender(),
//                    e.getEmail(),
//                    e.getPhone(),
//                    e.getDepartment() != null ? e.getDepartment().getDeptCode() : ""
//                };
//
//                boolean matches = false;
//                for (String val : values) {
//                    if (val != null && val.toLowerCase().contains(keyword)) {
//                        matches = true;
//                        break;
//                    }
//                }
//
//                if (matches) {
//                    tableModel.addRow(values);
//                }
//            }
//        } catch (RemoteException e) {
//            JOptionPane.showMessageDialog(this, "Search failed: " + e.getMessage());
//        }
//    }
//
//    private boolean validateFields() {
//        String empId = empIdField.getText().trim();
//        String fullName = fullNameField.getText().trim();
//        String gender = genderField.getText().trim();
//        String email = emailField.getText().trim();
//        String phone = phoneField.getText().trim();
//        String deptCode = deptCodeField.getText().trim();
//
//        if (empId.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Employee ID is required.");
//            empIdField.requestFocus();
//            return false;
//        }
//
//        if (fullName.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Full Name is required.");
//            fullNameField.requestFocus();
//            return false;
//        }
//        if (!fullName.matches("[a-zA-Z ]+")) {
//            JOptionPane.showMessageDialog(this, "Full Name can only contain letters and spaces.");
//            fullNameField.requestFocus();
//            return false;
//        }
//
//        if (gender.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Gender is required.");
//            genderField.requestFocus();
//            return false;
//        }
//        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female") && !gender.equalsIgnoreCase("Other")) {
//            JOptionPane.showMessageDialog(this, "Gender must be Male, Female, or Other.");
//            genderField.requestFocus();
//            return false;
//        }
//
//        if (email.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Email is required.");
//            emailField.requestFocus();
//            return false;
//        }
//        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
//            JOptionPane.showMessageDialog(this, "Invalid email format.");
//            emailField.requestFocus();
//            return false;
//        }
//
//        if (phone.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Phone is required.");
//            phoneField.requestFocus();
//            return false;
//        }
//        if (!phone.matches("\\d{7,15}")) {
//            JOptionPane.showMessageDialog(this, "Phone must be digits only (7-15 digits).");
//            phoneField.requestFocus();
//            return false;
//        }
//
//        if (deptCode.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Department Code is required.");
//            deptCodeField.requestFocus();
//            return false;
//        }
//
//        return true;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new EmployeeForm());
//    }
//}