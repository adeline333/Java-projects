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
import java.awt.*;
import java.awt.event.*;

public class HomePage {
    private JFrame frame;

    public HomePage() {
        frame = new JFrame("🏢 Employee Management System");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(204, 229, 255); // Light blue
                Color color2 = new Color(153, 204, 255); // Slightly deeper blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon("c:/Users/adeli/OneDrive/Desktop/logo.png")); // Your logo path
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);

        // Title
        JLabel title = new JLabel("Employee Management System");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(new Color(0, 51, 102));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        mainPanel.add(title);

        // Buttons
        JButton btnEmployees = createStyledButton("Employees");
        JButton btnDepartments = createStyledButton("Departments");
        JButton btnSalaries = createStyledButton("Salaries");

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

        mainPanel.add(btnEmployees);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(btnDepartments);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(btnSalaries);

        // Footer
        JLabel footer = new JLabel("© 2025 Designed by Adeline | EMS Project");
        footer.setFont(new Font("SansSerif", Font.ITALIC, 14));
        footer.setForeground(new Color(0, 76, 153));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(footer);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, 45));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 76, 153)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 76, 153));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePage::new);
    }
}
