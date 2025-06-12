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
import dao.DepartmentDAO;
import dao.DBConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;

public class DepartmentForm {
    private JFrame frame;
    private JTextField txtCode, txtName, txtHead, txtTotal, txtYear;
    private JTable table;
    private DefaultTableModel model;
    private DepartmentDAO departmentDAO;

    public DepartmentForm() {
        try {
            departmentDAO = new DepartmentDAO(DBConnection.getConnection());
            initialize();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initialize() {
        frame = new JFrame("🏬 Department Management");
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
            new EmployeeForm();
        });
        btnSalaries.addActionListener(e -> {
            frame.dispose();
            new SalaryForm();
        });
        btnDepartments.addActionListener(e -> {
            frame.dispose();
            new DepartmentForm(); // current form
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
        mainPanel.add(navPanel);
         navPanel.add(btnLogout);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 12, 12));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 215), 1, true),
            "Department Details", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16), Color.DARK_GRAY
        ));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        formPanel.add(createLabel("Department Code:", labelFont));
        txtCode = createTextField();
        formPanel.add(txtCode);

        formPanel.add(createLabel("Department Name:", labelFont));
        txtName = createTextField();
        formPanel.add(txtName);

        formPanel.add(createLabel("Head of Department:", labelFont));
        txtHead = createTextField();
        formPanel.add(txtHead);

        formPanel.add(createLabel("Total Employees:", labelFont));
        txtTotal = createTextField();
        formPanel.add(txtTotal);

        formPanel.add(createLabel("Established Year:", labelFont));
        txtYear = createTextField();
        formPanel.add(txtYear);

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
        model = new DefaultTableModel(new Object[]{"Code", "Name", "Head", "Total", "Year"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(22);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "📚 Departments List", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), Color.DARK_GRAY
        ));

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(scrollPane);

        // Actions
        btnAdd.addActionListener(e -> {
            Department dept = getDeptFromForm();
            if (dept != null && departmentDAO.addDepartment(dept)) {
                JOptionPane.showMessageDialog(frame, "✅ Department added successfully!");
                refreshTable();
                clearForm();
            }
        });

        btnUpdate.addActionListener(e -> {
            Department dept = getDeptFromForm();
            if (dept != null && departmentDAO.updateDepartment(dept)) {
                JOptionPane.showMessageDialog(frame, "✅ Department updated successfully!");
                refreshTable();
                clearForm();
            }
        });

        btnDelete.addActionListener(e -> {
            String code = txtCode.getText().trim();
            if (code.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ Enter department code to delete.");
            } else if (departmentDAO.deleteDepartment(code)) {
                JOptionPane.showMessageDialog(frame, "🗑️ Department deleted successfully!");
                refreshTable();
                clearForm();
            }
        });

        btnClear.addActionListener(e -> clearForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtCode.setText(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtHead.setText(model.getValueAt(row, 2).toString());
                txtTotal.setText(model.getValueAt(row, 3).toString());
                txtYear.setText(model.getValueAt(row, 4).toString());
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

    private Department getDeptFromForm() {
    String code = txtCode.getText().trim();
    String name = txtName.getText().trim();
    String head = txtHead.getText().trim();
    String totalStr = txtTotal.getText().trim();
    String yearStr = txtYear.getText().trim();

    if (code.isEmpty() || !code.startsWith("D")) {
        JOptionPane.showMessageDialog(frame, "❌ Department Code must start with 'D'.");
        return null;
    }

    if (name.isEmpty() || head.isEmpty() || totalStr.isEmpty() || yearStr.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "❌ Please fill in all fields.");
        return null;
    }

    // ✅ Validate Head of Department (only letters and spaces)
    if (!head.matches("[a-zA-Z\\s]+")) {
        JOptionPane.showMessageDialog(frame, "❌ Head of Department must contain only letters and spaces.");
        return null;
    }

    int total, year;
    try {
        total = Integer.parseInt(totalStr);
        if (total <= 0) throw new NumberFormatException();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "❌ Total Employees must be a positive number.");
        return null;
    }

    try {
        year = Integer.parseInt(yearStr);
        if (year < 1900 || year > 2100) throw new NumberFormatException();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "❌ Year must be between 1900 and 2100.");
        return null;
    }

    return new Department(code, name, head, total, year);
}


    private void clearForm() {
        txtCode.setText("");
        txtName.setText("");
        txtHead.setText("");
        txtTotal.setText("");
        txtYear.setText("");
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Department> list = departmentDAO.getAllDepartments();
        for (Department dept : list) {
            model.addRow(new Object[]{
                dept.getDeptCode(),
                dept.getDeptName(),
                dept.getHeadOfDept(),
                dept.getTotalEmployees(),
                dept.getEstablishedYear()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DepartmentForm::new);
    }
}
