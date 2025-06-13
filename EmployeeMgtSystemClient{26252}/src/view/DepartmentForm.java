//package view;
//
//import controller.DepartmentClientController;
//import java.awt.Color;
//import java.awt.Font;
//import model.Department;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.event.*;
//import java.util.List;
//
//public class DepartmentForm extends JFrame {
//    private JTextField codeField, nameField, headField, totalField, yearField;
//    private JTextField searchField; // New
//    private JButton saveButton, updateButton, deleteButton;
//    private JButton employeeButton, salaryButton, logoutButton;
//    private JButton exportButton;
//    private JButton searchButton; // New
//
//    private JTable deptTable;
//    private DefaultTableModel tableModel;
//    private DepartmentClientController controller;
//
//    public DepartmentForm() {
//        controller = new DepartmentClientController();
//        initComponents();
//        loadDepartments();
//    }
//
//
//    private void initComponents() {
//    setTitle("Department Management");
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setLayout(null);
//    setSize(720, 530);
//    setLocationRelativeTo(null);
//
//    // Background color - very light gray, neutral and calm
//    getContentPane().setBackground(new java.awt.Color(245, 245, 245));
//
//    // Labels
//    JLabel lblCode = new JLabel("Code:");
//    JLabel lblName = new JLabel("Name:");
//    JLabel lblHead = new JLabel("Head:");
//    JLabel lblTotal = new JLabel("Total Employees:");
//    JLabel lblYear = new JLabel("Established Year:");
//
//    // Text fields
//    codeField = new JTextField();
//    nameField = new JTextField();
//    headField = new JTextField();
//    totalField = new JTextField();
//    yearField = new JTextField();
//    searchField = new JTextField();
//
//    // Define main blue color for accent (buttons, selection, highlights)
//    Color mainBlue = new Color(10, 90, 180);  // A strong, elegant blue
//    Color lightBlue = new Color(220, 230, 250); // Light subtle blue for backgrounds
//
//    // Buttons with uniform style and main blue accent color
//    saveButton = new JButton("Save");
//    styleButton(saveButton, mainBlue);
//
//    updateButton = new JButton("Update");
//    styleButton(updateButton, mainBlue);
//
//    deleteButton = new JButton("Delete");
//    styleButton(deleteButton, mainBlue);
//
//    employeeButton = new JButton("Employee");
//    styleButton(employeeButton, mainBlue);
//
//    salaryButton = new JButton("Salary");
//    styleButton(salaryButton, mainBlue);
//
//    logoutButton = new JButton("Logout");
//    styleButton(logoutButton, mainBlue);
//
//    exportButton = new JButton("Export to Excel");
//    styleButton(exportButton, mainBlue);
//
//    searchButton = new JButton("Search");
//    styleButton(searchButton, mainBlue);
//
//    // Table
//    String[] columns = {"Code", "Name", "Head", "Total", "Year"};
//    tableModel = new DefaultTableModel(columns, 0);
//    deptTable = new JTable(tableModel);
//    deptTable.setRowHeight(25);
//    deptTable.setGridColor(Color.GRAY);
//    deptTable.setBackground(Color.WHITE);
//    deptTable.setSelectionBackground(mainBlue);
//    deptTable.setSelectionForeground(Color.WHITE);
//    JScrollPane scrollPane = new JScrollPane(deptTable);
//
//    // Position components
//    employeeButton.setBounds(20, 10, 100, 25);
//    salaryButton.setBounds(130, 10, 100, 25);
//    logoutButton.setBounds(240, 10, 100, 25);
//
//    lblCode.setBounds(20, 50, 120, 25);      codeField.setBounds(150, 50, 150, 25);
//    lblName.setBounds(20, 90, 120, 25);      nameField.setBounds(150, 90, 150, 25);
//    lblHead.setBounds(20, 130, 120, 25);     headField.setBounds(150, 130, 150, 25);
//    lblTotal.setBounds(20, 170, 120, 25);    totalField.setBounds(150, 170, 150, 25);
//    lblYear.setBounds(20, 210, 120, 25);     yearField.setBounds(150, 210, 150, 25);
//
//    saveButton.setBounds(330, 180, 100, 25);
//    updateButton.setBounds(440, 180, 100, 25);
//    deleteButton.setBounds(550, 180, 100, 25);
//
//    scrollPane.setBounds(20, 250, 640, 200);
//
//    searchField.setBounds(20, 460, 200, 25);
//    searchButton.setBounds(230, 460, 100, 25);
//    exportButton.setBounds(550, 460, 150, 25);
//
//    // Add components
//    add(employeeButton);
//    add(salaryButton);
//    add(logoutButton);
//
//    add(lblCode);
//    add(codeField);
//    add(lblName);
//    add(nameField);
//    add(lblHead);
//    add(headField);
//    add(lblTotal);
//    add(totalField);
//    add(lblYear);
//    add(yearField);
//
//    add(saveButton);
//    add(updateButton);
//    add(deleteButton);
//
//    add(scrollPane);
//    add(searchField);
//    add(searchButton);
//    add(exportButton);
//
//    // Button actions
//    saveButton.addActionListener(e -> saveDepartment());
//    updateButton.addActionListener(e -> updateDepartment());
//    deleteButton.addActionListener(e -> deleteDepartment());
//
//    employeeButton.addActionListener(e -> openEmployeeForm());
//    salaryButton.addActionListener(e -> openSalaryForm());
//    logoutButton.addActionListener(e -> logout());
//    exportButton.addActionListener(e -> exportToExcel());
//    searchButton.addActionListener(e -> searchDepartments(searchField.getText()));
//
//    // Table row click
//    deptTable.addMouseListener(new MouseAdapter() {
//        public void mouseClicked(MouseEvent e) {
//            int row = deptTable.getSelectedRow();
//            if (row >= 0) {
//                codeField.setText(tableModel.getValueAt(row, 0).toString());
//                nameField.setText(tableModel.getValueAt(row, 1).toString());
//                headField.setText(tableModel.getValueAt(row, 2).toString());
//                totalField.setText(tableModel.getValueAt(row, 3).toString());
//                yearField.setText(tableModel.getValueAt(row, 4).toString());
//            }
//        }
//    });
//}
//
//// Helper method for uniform button styling
//private void styleButton(JButton button, Color bgColor) {
//    button.setBackground(bgColor);
//    button.setForeground(Color.WHITE);
//    button.setFocusPainted(false);
//    button.setBorder(BorderFactory.createLineBorder(new Color(5, 70, 140)));
//    button.setFont(new Font("Segoe UI", Font.BOLD, 12));
//}
//
//
//
//   private void saveDepartment() {
//    try {
//        if (!validateFields()) return;
//
//        Department dept = getDepartmentFromFields();
//        String message = controller.registerDepartment(dept);
//        JOptionPane.showMessageDialog(this, message);
//        clearFields();
//        loadDepartments();
//    } catch (Exception ex) {
//        showError(ex);
//    }
//}
//
//private void updateDepartment() {
//    try {
//        if (!validateFields()) return;
//
//        Department dept = getDepartmentFromFields();
//        String message = controller.updateDepartment(dept);
//        JOptionPane.showMessageDialog(this, message);
//        clearFields();
//        loadDepartments();
//    } catch (Exception ex) {
//        showError(ex);
//    }
//}
//
//
//    private void deleteDepartment() {
//        try {
//            String code = codeField.getText();
//            if (code.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Select a department to delete.");
//                return;
//            }
//            Department dept = controller.getDepartmentByCode(code);
//            String message = controller.deleteDepartment(dept);
//            JOptionPane.showMessageDialog(this, message);
//            clearFields();
//            loadDepartments();
//        } catch (Exception ex) {
//            showError(ex);
//        }
//    }
//
//    private void loadDepartments() {
//        try {
//            tableModel.setRowCount(0);
//            List<Department> list = controller.getAllDepartments();
//            for (Department d : list) {
//                Object[] row = {
//                    d.getDeptCode(),
//                    d.getDeptName(),
//                    d.getHeadOfDept(),
//                    d.getTotalEmployees(),
//                    d.getEstablishedYear()
//                };
//                tableModel.addRow(row);
//            }
//        } catch (Exception ex) {
//            showError(ex);
//        }
//    }
//
//    // ✅ New method for searching
//    private void searchDepartments(String keyword) {
//        try {
//            tableModel.setRowCount(0);
//            List<Department> list = controller.getAllDepartments();
//            for (Department d : list) {
//                if (d.getDeptCode().toLowerCase().contains(keyword.toLowerCase()) ||
//                    d.getDeptName().toLowerCase().contains(keyword.toLowerCase()) ||
//                    d.getHeadOfDept().toLowerCase().contains(keyword.toLowerCase())) {
//
//                    Object[] row = {
//                        d.getDeptCode(),
//                        d.getDeptName(),
//                        d.getHeadOfDept(),
//                        d.getTotalEmployees(),
//                        d.getEstablishedYear()
//                    };
//                    tableModel.addRow(row);
//                }
//            }
//        } catch (Exception ex) {
//            showError(ex);
//        }
//    }
//
//    private Department getDepartmentFromFields() {
//        String code = codeField.getText();
//        String name = nameField.getText();
//        String head = headField.getText();
//        int total = Integer.parseInt(totalField.getText());
//        int year = Integer.parseInt(yearField.getText());
//
//        return new Department(code, name, head, total, year);
//    }
//
//    private void clearFields() {
//        codeField.setText("");
//        nameField.setText("");
//        headField.setText("");
//        totalField.setText("");
//        yearField.setText("");
//    }
//
//    private void exportToExcel() {
//        try {
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setDialogTitle("Save Excel File");
//            int userSelection = fileChooser.showSaveDialog(this);
//
//            if (userSelection == JFileChooser.APPROVE_OPTION) {
//                java.io.File fileToSave = fileChooser.getSelectedFile();
//
//                java.io.FileWriter fw = new java.io.FileWriter(fileToSave + ".xls");
//
//                // Write column headers
//                for (int i = 0; i < tableModel.getColumnCount(); i++) {
//                    fw.write(tableModel.getColumnName(i) + "\t");
//                }
//                fw.write("\n");
//
//                // Write table rows
//                for (int i = 0; i < tableModel.getRowCount(); i++) {
//                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
//                        fw.write(tableModel.getValueAt(i, j).toString() + "\t");
//                    }
//                    fw.write("\n");
//                }
//
//                fw.close();
//                JOptionPane.showMessageDialog(this, "Exported successfully!");
//            }
//        } catch (Exception ex) {
//            showError(ex);
//        }
//    }
//
//    private void showError(Exception ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//    }
//    
//    private boolean validateFields() {
//    String code = codeField.getText().trim();
//    String name = nameField.getText().trim();
//    String head = headField.getText().trim();
//    String totalStr = totalField.getText().trim();
//    String yearStr = yearField.getText().trim();
//
//    if (code.isEmpty() || name.isEmpty() || head.isEmpty() || totalStr.isEmpty() || yearStr.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "All fields are required.");
//        return false;
//    }
//
//    if (!name.matches("[a-zA-Z\\s]+")) {
//        JOptionPane.showMessageDialog(this, "Name can only contain letters and spaces.");
//        return false;
//    }
//
//    if (!head.matches("[a-zA-Z\\s]+")) {
//        JOptionPane.showMessageDialog(this, "Head of department can only contain letters and spaces.");
//        return false;
//    }
//
//    try {
//        int total = Integer.parseInt(totalStr);
//        if (total < 0) {
//            JOptionPane.showMessageDialog(this, "Total employees must be a positive number.");
//            return false;
//        }
//    } catch (NumberFormatException e) {
//        JOptionPane.showMessageDialog(this, "Total employees must be a valid number.");
//        return false;
//    }
//
//    try {
//        int year = Integer.parseInt(yearStr);
//        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
//        if (year < 1900 || year > currentYear) {
//            JOptionPane.showMessageDialog(this, "Established year must be between 1900 and " + currentYear + ".");
//            return false;
//        }
//    } catch (NumberFormatException e) {
//        JOptionPane.showMessageDialog(this, "Established year must be a valid number.");
//        return false;
//    }
//
//    return true;
//}
//
//
//    private void openEmployeeForm() {
//            
//           new EmployeeForm().setVisible(true);
//                    
//        this.dispose();
//        // TODO: Add employee form logic or remove this method if not required
//    }
//
//    private void openSalaryForm() {
//        
//           new SalaryForm().setVisible(true);
//        this.dispose();
//        // TODO: Add salary form logic or remove this method if not required
//    }
//
//    private void logout() {
//           new logInnForm().setVisible(true);
//        this.dispose();
//        // TODO: Add logout logic or redirect to login form
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new DepartmentForm().setVisible(true));
//    }
//}
//
//
//
//
//
//
package view;

import controller.DepartmentClientController;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Component;
import javax.swing.border.AbstractBorder;
import model.Department;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.util.List;

public class DepartmentForm extends JFrame {
    private JTextField codeField, nameField, headField, totalField, yearField;
    private JTextField searchField;
    private JButton saveButton, updateButton, deleteButton;
    private JButton employeeButton, salaryButton, logoutButton;
    private JButton exportButton;
    private JButton searchButton;

    private JTable deptTable;
    private DefaultTableModel tableModel;
    private DepartmentClientController controller;

    public DepartmentForm() {
        
        if (!SessionManager.isLoggedIn()) {
    JOptionPane.showMessageDialog(this, "Please login first.");
    new logInnForm().setVisible(true); // Go back to login
    this.dispose();
    
    return;
            }

           System.out.println("Welcome, " + SessionManager.getCurrentUser());
           System.out.println("Is Logged In? " + SessionManager.isLoggedIn());
System.out.println("Current User: " + SessionManager.getCurrentUser());
System.out.println("Currently on the department form " );

        controller = new DepartmentClientController();
        initComponents();
        loadDepartments();
    }

    private void initComponents() {
        setTitle("Department Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(850, 650);
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
        backgroundPanel.setBounds(0, 0, 850, 650);
        setContentPane(backgroundPanel);

        // Title
        JLabel titleLabel = new JLabel("Department");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 163, 163));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 20, 850, 50);

        // Input panel with rounded corners
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(30, 120, 380, 260);
        inputPanel.setBackground(new Color(232, 236, 239));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Labels
        JLabel lblCode = new JLabel("Code:");
        lblCode.setFont(new Font("Roboto", Font.BOLD, 15));
        lblCode.setForeground(new Color(74, 74, 74));
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Roboto", Font.BOLD, 15));
        lblName.setForeground(new Color(74, 74, 74));
        JLabel lblHead = new JLabel("Head:");
        lblHead.setFont(new Font("Roboto", Font.BOLD, 15));
        lblHead.setForeground(new Color(74, 74, 74));
        JLabel lblTotal = new JLabel("Total Employees:");
        lblTotal.setFont(new Font("Roboto", Font.BOLD, 15));
        lblTotal.setForeground(new Color(74, 74, 74));
        JLabel lblYear = new JLabel("Established Year:");
        lblYear.setFont(new Font("Roboto", Font.BOLD, 15));
        lblYear.setForeground(new Color(74, 74, 74));

        // Text fields with rounded corners
        codeField = new JTextField();
        codeField.setBackground(Color.WHITE);
        codeField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        nameField = new JTextField();
        nameField.setBackground(Color.WHITE);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        headField = new JTextField();
        headField.setBackground(Color.WHITE);
        headField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        totalField = new JTextField();
        totalField.setBackground(Color.WHITE);
        totalField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        yearField = new JTextField();
        yearField.setBackground(Color.WHITE);
        yearField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField = new JTextField();
        searchField.setBackground(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Define colors
        Color mainTeal = new Color(0, 163, 163);
        Color hoverTeal = new Color(0, 190, 190);
        Color pressedTeal = new Color(0, 140, 140);

        // Buttons with rounded corners
        saveButton = new JButton("Save");
        styleButton(saveButton, mainTeal, hoverTeal, pressedTeal);

        updateButton = new JButton("Update");
        styleButton(updateButton, mainTeal, hoverTeal, pressedTeal);

        deleteButton = new JButton("Delete");
        styleButton(deleteButton, mainTeal, hoverTeal, pressedTeal);

        employeeButton = new JButton("Employee");
        styleButton(employeeButton, mainTeal, hoverTeal, pressedTeal);

        salaryButton = new JButton("Salary");
        styleButton(salaryButton, mainTeal, hoverTeal, pressedTeal);

        logoutButton = new JButton("Logout");
        styleButton(logoutButton, mainTeal, hoverTeal, pressedTeal);

        exportButton = new JButton("GET Excel");
        styleButton(exportButton, mainTeal, hoverTeal, pressedTeal);

        searchButton = new JButton("Search");
        styleButton(searchButton, mainTeal, hoverTeal, pressedTeal);

        // Table with rounded scroll pane
        String[] columns = {"Code", "Name", "Head", "Total", "Year"};
        tableModel = new DefaultTableModel(columns, 0);
        deptTable = new JTable(tableModel);
        deptTable.setRowHeight(30);
        deptTable.setGridColor(new Color(200, 200, 200));
        deptTable.setBackground(Color.WHITE);
        deptTable.setSelectionBackground(mainTeal);
        deptTable.setSelectionForeground(Color.WHITE);
        deptTable.setFont(new Font("Roboto", Font.PLAIN, 13));
        deptTable.setShowGrid(true);
        deptTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        deptTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 247, 249));
                }
                return c;
            }
        });
        JTableHeader header = deptTable.getTableHeader();
        header.setBackground(mainTeal);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(deptTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Position components
        backgroundPanel.add(titleLabel);
        employeeButton.setBounds(30, 80, 120, 35);
        salaryButton.setBounds(160, 80, 120, 35);
        logoutButton.setBounds(290, 80, 120, 35);

        lblCode.setBounds(20, 20, 120, 30);
        codeField.setBounds(150, 20, 200, 35);
        lblName.setBounds(20, 60, 120, 30);
        nameField.setBounds(150, 60, 200, 35);
        lblHead.setBounds(20, 100, 120, 30);
        headField.setBounds(150, 100, 200, 35);
        lblTotal.setBounds(20, 140, 120, 30);
        totalField.setBounds(150, 140, 200, 35);
        lblYear.setBounds(20, 180, 120, 30);
        yearField.setBounds(150, 180, 200, 35);

        saveButton.setBounds(440, 180, 120, 35);
        updateButton.setBounds(570, 180, 120, 35);
        deleteButton.setBounds(700, 180, 120, 35);

        scrollPane.setBounds(30, 400, 790, 250);

        searchField.setBounds(30, 660, 220, 35);
        searchButton.setBounds(260, 660, 120, 35);
        exportButton.setBounds(700, 660, 120, 35);

        // Add components to the background panel
        backgroundPanel.add(employeeButton);
        backgroundPanel.add(salaryButton);
        backgroundPanel.add(logoutButton);

        inputPanel.add(lblCode);
        inputPanel.add(codeField);
        inputPanel.add(lblName);
        inputPanel.add(nameField);
        inputPanel.add(lblHead);
        inputPanel.add(headField);
        inputPanel.add(lblTotal);
        inputPanel.add(totalField);
        inputPanel.add(lblYear);
        inputPanel.add(yearField);

        backgroundPanel.add(inputPanel);
        backgroundPanel.add(saveButton);
        backgroundPanel.add(updateButton);
        backgroundPanel.add(deleteButton);

        backgroundPanel.add(scrollPane);
        backgroundPanel.add(searchField);
        backgroundPanel.add(searchButton);
        backgroundPanel.add(exportButton);

        // Button actions
        saveButton.addActionListener(e -> saveDepartment());
        updateButton.addActionListener(e -> updateDepartment());
        deleteButton.addActionListener(e -> deleteDepartment());

        employeeButton.addActionListener(e -> openEmployeeForm());
        salaryButton.addActionListener(e -> openSalaryForm());
        
        
 logoutButton.addActionListener(e -> {
    SessionManager.logout(); // ✅ Clear session
    new logInnForm().setVisible(true); // Show login screen
    dispose(); // Close current form
});

       
        //logoutButton.addActionListener(e -> logout());
        exportButton.addActionListener(e -> exportToExcel());
        searchButton.addActionListener(e -> searchDepartments(searchField.getText()));

        // Table row click
        deptTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = deptTable.getSelectedRow();
                if (row >= 0) {
                    codeField.setText(tableModel.getValueAt(row, 0).toString());
                    nameField.setText(tableModel.getValueAt(row, 1).toString());
                    headField.setText(tableModel.getValueAt(row, 2).toString());
                    totalField.setText(tableModel.getValueAt(row, 3).toString());
                    yearField.setText(tableModel.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private void styleButton(JButton button, Color bgColor, Color hoverColor, Color pressedColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Roboto", Font.BOLD, 14));

        // Custom rounded border
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

    // Custom border class for rounded corners
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

    private void saveDepartment() {
        try {
            if (!validateFields()) return;

            Department dept = getDepartmentFromFields();
            String message = controller.registerDepartment(dept);
            JOptionPane.showMessageDialog(this, message);
            clearFields();
            loadDepartments();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void updateDepartment() {
        try {
            if (!validateFields()) return;

            Department dept = getDepartmentFromFields();
            String message = controller.updateDepartment(dept);
            JOptionPane.showMessageDialog(this, message);
            clearFields();
            loadDepartments();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void deleteDepartment() {
        try {
            String code = codeField.getText();
            if (code.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select a department to delete.");
                return;
            }
            Department dept = controller.getDepartmentByCode(code);
            String message = controller.deleteDepartment(dept);
            JOptionPane.showMessageDialog(this, message);
            clearFields();
            loadDepartments();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void loadDepartments() {
        try {
            tableModel.setRowCount(0);
            List<Department> list = controller.getAllDepartments();
            for (Department d : list) {
                Object[] row = {
                    d.getDeptCode(),
                    d.getDeptName(),
                    d.getHeadOfDept(),
                    d.getTotalEmployees(),
                    d.getEstablishedYear()
                };
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void searchDepartments(String keyword) {
        try {
            tableModel.setRowCount(0);
            List<Department> list = controller.getAllDepartments();
            for (Department d : list) {
                if (d.getDeptCode().toLowerCase().contains(keyword.toLowerCase()) ||
                    d.getDeptName().toLowerCase().contains(keyword.toLowerCase()) ||
                    d.getHeadOfDept().toLowerCase().contains(keyword.toLowerCase())) {

                    Object[] row = {
                        d.getDeptCode(),
                        d.getDeptName(),
                        d.getHeadOfDept(),
                        d.getTotalEmployees(),
                        d.getEstablishedYear()
                    };
                    tableModel.addRow(row);
                }
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private Department getDepartmentFromFields() {
        String code = codeField.getText();
        String name = nameField.getText();
        String head = headField.getText();
        int total = Integer.parseInt(totalField.getText());
        int year = Integer.parseInt(yearField.getText());

        return new Department(code, name, head, total, year);
    }

    private void clearFields() {
        codeField.setText("");
        nameField.setText("");
        headField.setText("");
        totalField.setText("");
        yearField.setText("");
    }

    private void exportToExcel() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Excel File");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();

                java.io.FileWriter fw = new java.io.FileWriter(fileToSave + ".xls");

                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    fw.write(tableModel.getColumnName(i) + "\t");
                }
                fw.write("\n");

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        fw.write(tableModel.getValueAt(i, j).toString() + "\t");
                    }
                    fw.write("\n");
                }

                fw.close();
                JOptionPane.showMessageDialog(this, "Exported successfully!");
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void showError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }

    private boolean validateFields() {
        String code = codeField.getText().trim();
        String name = nameField.getText().trim();
        String head = headField.getText().trim();
        String totalStr = totalField.getText().trim();
        String yearStr = yearField.getText().trim();

        if (code.isEmpty() || name.isEmpty() || head.isEmpty() || totalStr.isEmpty() || yearStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return false;
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(this, "Name can only contain letters and spaces.");
            return false;
        }

        if (!head.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(this, "Head of department can only contain letters and spaces.");
            return false;
        }

        try {
            int total = Integer.parseInt(totalStr);
            if (total < 0) {
                JOptionPane.showMessageDialog(this, "Total employees must be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Total employees must be a valid number.");
            return false;
        }

        try {
            int year = Integer.parseInt(yearStr);
            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            if (year < 1900 || year > currentYear) {
                JOptionPane.showMessageDialog(this, "Established year must be between 1900 and " + currentYear + ".");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Established year must be a valid number.");
            return false;
        }

        return true;
    }

    private void openEmployeeForm() {
        new EmployeeForm().setVisible(true);
        this.dispose();
    }

    private void openSalaryForm() {
        new SalaryForm().setVisible(true);
        this.dispose();
    }

    private void logout() {
        new logInnForm().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DepartmentForm().setVisible(true));
    }
}