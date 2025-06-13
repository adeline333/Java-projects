package view;

import controller.SalaryClientController;
import model.Salary;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.util.List;

public class SalaryForm extends JFrame {
    private JTextField codeField, basicField, bonusField, deductionField, monthField;
    private JTextField searchField;
    private JButton saveButton, updateButton, deleteButton;
    private JButton departmentButton, employeeButton, logoutButton, exportButton;
    private JButton searchButton;
    private JLabel searchLabel;
    private JTable salaryTable;
    private DefaultTableModel tableModel;
    private SalaryClientController controller;

    public SalaryForm() {
               if (!SessionManager.isLoggedIn()) {
    JOptionPane.showMessageDialog(this, "Please login first.");
    new logInnForm().setVisible(true); // Go back to login
    this.dispose();
    
    return;
            }

           System.out.println("Welcome, " + SessionManager.getCurrentUser());
           System.out.println("Is Logged In? " + SessionManager.isLoggedIn());
System.out.println("Current User: " + SessionManager.getCurrentUser());
System.out.println("Currently on the salary form " );

        
        controller = new SalaryClientController();
        initComponents();
        loadSalaries();
    }

    private void initComponents() {
        setTitle("Salary Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(850, 650);
        setLocationRelativeTo(null);

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

        JLabel titleLabel = new JLabel("Salary Management");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 163, 163));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 20, 850, 50);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(30, 120, 380, 260);
        inputPanel.setBackground(new Color(232, 236, 239));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblCode = new JLabel("Code:");
        lblCode.setFont(new Font("Roboto", Font.BOLD, 15));
        lblCode.setForeground(new Color(74, 74, 74));
        lblCode.setBounds(20, 20, 120, 30);
        JLabel lblBasic = new JLabel("Basic Salary:");
        lblBasic.setFont(new Font("Roboto", Font.BOLD, 15));
        lblBasic.setForeground(new Color(74, 74, 74));
        lblBasic.setBounds(20, 60, 120, 30);
        JLabel lblBonus = new JLabel("Bonus:");
        lblBonus.setFont(new Font("Roboto", Font.BOLD, 15));
        lblBonus.setForeground(new Color(74, 74, 74));
        lblBonus.setBounds(20, 100, 120, 30);
        JLabel lblDeduction = new JLabel("Deductions:");
        lblDeduction.setFont(new Font("Roboto", Font.BOLD, 15));
        lblDeduction.setForeground(new Color(74, 74, 74));
        lblDeduction.setBounds(20, 140, 120, 30);
        JLabel lblMonth = new JLabel("Pay Month:");
        lblMonth.setFont(new Font("Roboto", Font.BOLD, 15));
        lblMonth.setForeground(new Color(74, 74, 74));
        lblMonth.setBounds(20, 180, 120, 30);

        codeField = new JTextField();
        codeField.setBackground(Color.WHITE);
        codeField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        codeField.setBounds(150, 20, 200, 35);

        basicField = new JTextField();
        basicField.setBackground(Color.WHITE);
        basicField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        basicField.setBounds(150, 60, 200, 35);

        bonusField = new JTextField();
        bonusField.setBackground(Color.WHITE);
        bonusField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        bonusField.setBounds(150, 100, 200, 35);

        deductionField = new JTextField();
        deductionField.setBackground(Color.WHITE);
        deductionField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        deductionField.setBounds(150, 140, 200, 35);

        monthField = new JTextField();
        monthField.setBackground(Color.WHITE);
        monthField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        monthField.setBounds(150, 180, 200, 35);

        searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        searchLabel.setForeground(new Color(74, 74, 74));
        searchLabel.setBounds(440, 180, 120, 30);

        searchField = new JTextField();
        searchField.setBackground(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setBounds(510, 180, 200, 35);

        Color mainTeal = new Color(0, 163, 163);
        Color hoverTeal = new Color(0, 190, 190);
        Color pressedTeal = new Color(0, 140, 140);

        saveButton = new JButton("Save");
        styleButton(saveButton, mainTeal, hoverTeal, pressedTeal);
        saveButton.setBounds(440, 220, 120, 35);

        updateButton = new JButton("Update");
        styleButton(updateButton, mainTeal, hoverTeal, pressedTeal);
        updateButton.setBounds(570, 220, 120, 35);

        deleteButton = new JButton("Delete");
        styleButton(deleteButton, mainTeal, hoverTeal, pressedTeal);
        deleteButton.setBounds(700, 220, 120, 35);

        departmentButton = new JButton("Department");
        styleButton(departmentButton, mainTeal, hoverTeal, pressedTeal);
        departmentButton.setBounds(30, 80, 120, 35);

        employeeButton = new JButton("Employee");
        styleButton(employeeButton, mainTeal, hoverTeal, pressedTeal);
        employeeButton.setBounds(160, 80, 120, 35);

        logoutButton = new JButton("Logout");
        styleButton(logoutButton, mainTeal, hoverTeal, pressedTeal);
        logoutButton.setBounds(290, 80, 120, 35);

        exportButton = new JButton("GET Excel");
        styleButton(exportButton, mainTeal, hoverTeal, pressedTeal);
        exportButton.setBounds(700, 260, 120, 35);

        searchButton = new JButton("Search");
        styleButton(searchButton, mainTeal, hoverTeal, pressedTeal);
        searchButton.setBounds(720, 180, 100, 35);

        String[] columns = {"Code", "Basic", "Bonus", "Deduction", "Month"};
        tableModel = new DefaultTableModel(columns, 0);
        salaryTable = new JTable(tableModel);
        salaryTable.setRowHeight(30);
        salaryTable.setGridColor(new Color(200, 200, 200));
        salaryTable.setBackground(Color.WHITE);
        salaryTable.setSelectionBackground(mainTeal);
        salaryTable.setSelectionForeground(Color.WHITE);
        salaryTable.setFont(new Font("Roboto", Font.PLAIN, 13));
        salaryTable.setShowGrid(true);
        salaryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        salaryTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 247, 249));
                }
                return c;
            }
        });
        JTableHeader header = salaryTable.getTableHeader();
        header.setBackground(mainTeal);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(salaryTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.setBounds(30, 400, 790, 200);

        inputPanel.add(lblCode);
        inputPanel.add(codeField);
        inputPanel.add(lblBasic);
        inputPanel.add(basicField);
        inputPanel.add(lblBonus);
        inputPanel.add(bonusField);
        inputPanel.add(lblDeduction);
        inputPanel.add(deductionField);
        inputPanel.add(lblMonth);
        inputPanel.add(monthField);

        backgroundPanel.add(titleLabel);
        backgroundPanel.add(inputPanel);
        backgroundPanel.add(searchLabel);
        backgroundPanel.add(searchField);
        backgroundPanel.add(searchButton);
        backgroundPanel.add(saveButton);
        backgroundPanel.add(updateButton);
        backgroundPanel.add(deleteButton);
        backgroundPanel.add(departmentButton);
        backgroundPanel.add(employeeButton);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(exportButton);
        backgroundPanel.add(scrollPane);

        saveButton.addActionListener(e -> saveSalary());
        updateButton.addActionListener(e -> updateSalary());
        deleteButton.addActionListener(e -> deleteSalary());

        departmentButton.addActionListener(e -> {
            new DepartmentForm().setVisible(true);
            this.dispose();
        });

        employeeButton.addActionListener(e -> {
            new EmployeeForm().setVisible(true);
            this.dispose();
        });

                
 logoutButton.addActionListener(e -> {
    SessionManager.logout(); // ✅ Clear session
    new logInnForm().setVisible(true); // Show login screen
    dispose(); // Close current form
});


        exportButton.addActionListener(e -> exportToExcel());
        searchButton.addActionListener(e -> searchSalaries());

        salaryTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = salaryTable.getSelectedRow();
                if (row >= 0) {
                    codeField.setText(tableModel.getValueAt(row, 0).toString());
                    basicField.setText(tableModel.getValueAt(row, 1).toString());
                    bonusField.setText(tableModel.getValueAt(row, 2).toString());
                    deductionField.setText(tableModel.getValueAt(row, 3).toString());
                    monthField.setText(tableModel.getValueAt(row, 4).toString());
                }
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

    private void saveSalary() {
        try {
            if (!validateInputs()) return;
            Salary s = getSalaryFromFields();
            String message = controller.registerSalary(s);
            JOptionPane.showMessageDialog(this, message);
            clearFields();
            loadSalaries();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void updateSalary() {
        try {
            if (!validateInputs()) return;
            Salary s = getSalaryFromFields();
            String message = controller.updateSalary(s);
            JOptionPane.showMessageDialog(this, message);
            clearFields();
            loadSalaries();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void deleteSalary() {
        try {
            String code = codeField.getText();
            if (code.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select a salary to delete.");
                return;
            }
            Salary s = controller.retrieveSalaryById(code);
            String message = controller.deleteSalary(s);
            JOptionPane.showMessageDialog(this, message);
            clearFields();
            loadSalaries();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void loadSalaries() {
        try {
            tableModel.setRowCount(0);
            List<Salary> list = controller.retrieveAllSalaries();
            for (Salary s : list) {
                Object[] row = {
                    s.getSalaryCode(),
                    s.getBasicSalary(),
                    s.getBonus(),
                    s.getDeductions(),
                    s.getPayMonth()
                };
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void searchSalaries() {
        String keyword = searchField.getText().toLowerCase().trim();
        if (keyword.isEmpty()) {
            loadSalaries();
            return;
        }

        try {
            tableModel.setRowCount(0);
            List<Salary> list = controller.retrieveAllSalaries();
            for (Salary s : list) {
                if (s.getSalaryCode().toLowerCase().contains(keyword) ||
                    s.getPayMonth().toLowerCase().contains(keyword)) {
                    Object[] row = {
                        s.getSalaryCode(),
                        s.getBasicSalary(),
                        s.getBonus(),
                        s.getDeductions(),
                        s.getPayMonth()
                    };
                    tableModel.addRow(row);
                }
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private Salary getSalaryFromFields() {
        String code = codeField.getText();
        double basic = Double.parseDouble(basicField.getText());
        double bonus = Double.parseDouble(bonusField.getText());
        double deduction = Double.parseDouble(deductionField.getText());
        String month = monthField.getText();
        return new Salary(code, basic, bonus, deduction, month);
    }

    private void clearFields() {
        codeField.setText("");
        basicField.setText("");
        bonusField.setText("");
        deductionField.setText("");
        monthField.setText("");
    }

    private void exportToExcel() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Excel File");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();

                FileWriter fw = new FileWriter(fileToSave + ".xls");

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

    private boolean validateInputs() {
        String code = codeField.getText().trim();
        String basic = basicField.getText().trim();
        String bonus = bonusField.getText().trim();
        String deduction = deductionField.getText().trim();
        String month = monthField.getText().trim();

        if (code.isEmpty() || !code.matches("^SAL\\d{3}$")) {
            JOptionPane.showMessageDialog(this, "Enter a valid code like SAL001.");
            return false;
        }

        try {
            double basicValue = Double.parseDouble(basic);
            if (basicValue < 0) {
                JOptionPane.showMessageDialog(this, "Basic salary must be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Basic salary must be a valid number.");
            return false;
        }

        if (!bonus.isEmpty()) {
            try {
                double bonusValue = Double.parseDouble(bonus);
                if (bonusValue < 0) {
                    JOptionPane.showMessageDialog(this, "Bonus must be a non-negative number.");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Bonus must be a number.");
                return false;
            }
        }

        if (!deduction.isEmpty()) {
            try {
                double deductionValue = Double.parseDouble(deduction);
                if (deductionValue < 0) {
                    JOptionPane.showMessageDialog(this, "Deduction must be a non-negative number.");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Deduction must be a number.");
                return false;
            }
        }

        if (month.isEmpty() || !month.matches("^(January|February|March|April|May|June|July|August|September|October|November|December)$")) {
            JOptionPane.showMessageDialog(this, "Enter a valid month name (e.g., January).");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SalaryForm().setVisible(true));
    }
}

