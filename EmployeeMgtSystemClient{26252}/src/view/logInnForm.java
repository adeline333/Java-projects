

package view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class logInnForm extends JFrame {
    private JTextField emailField;
    private JPasswordField empIdField;
    private JButton loginButton;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_magment_system_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "waterAndMilk1#";

    public logInnForm() {
        setTitle("Employee Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(450, 350);
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
        backgroundPanel.setBounds(0, 0, 450, 350);
        setContentPane(backgroundPanel);

        // Title
        JLabel titleLabel = new JLabel("Employee Login");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 163, 163));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 20, 450, 40);
        backgroundPanel.add(titleLabel);

        // Input panel with rounded corners
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(50, 80, 350, 180);
        inputPanel.setBackground(new Color(232, 236, 239));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Labels
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Roboto", Font.BOLD, 15));
        lblEmail.setForeground(new Color(74, 74, 74));
        lblEmail.setBounds(20, 20, 100, 30);

        JLabel lblEmpId = new JLabel("Password:");
        lblEmpId.setFont(new Font("Roboto", Font.BOLD, 15));
        lblEmpId.setForeground(new Color(74, 74, 74));
        lblEmpId.setBounds(20, 70, 100, 30);

        // Text fields with rounded corners
        emailField = new JTextField();
        emailField.setBackground(Color.WHITE);
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        emailField.setBounds(120, 20, 200, 35);

        empIdField = new JPasswordField();
        empIdField.setBackground(Color.WHITE);
        empIdField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        empIdField.setBounds(120, 70, 200, 35);

        // Button styling
        Color mainTeal = new Color(0, 163, 163);
        Color hoverTeal = new Color(0, 190, 190);
        Color pressedTeal = new Color(0, 140, 140);

        loginButton = new JButton("Login");
        styleButton(loginButton, mainTeal, hoverTeal, pressedTeal);
        loginButton.setBounds(150, 270, 120, 35);
        loginButton.addActionListener(e -> authenticateUser());

        // Add components to input panel
        inputPanel.add(lblEmail);
        inputPanel.add(emailField);
        inputPanel.add(lblEmpId);
        inputPanel.add(empIdField);

        // Add components to background panel
        backgroundPanel.add(inputPanel);
        backgroundPanel.add(loginButton);

        setVisible(true);
    }

    private void styleButton(JButton button, Color mainColor, Color hoverColor, Color pressedColor) {
        button.setFont(new Font("Roboto", Font.BOLD, 14));
        button.setBackground(mainColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(mainColor);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(pressedColor);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
        });
    }

    private void authenticateUser() {
        String email = emailField.getText().trim();
        String empId = new String(empIdField.getPassword()).trim();

        if (email.isEmpty() || empId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and employee ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT dept_code FROM employees WHERE email = ? AND emp_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, empId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String deptCode = rs.getString("dept_code");

                if (!"111".equals(deptCode)) {
                    JOptionPane.showMessageDialog(this, "You are not in the admin department and cannot login.");
                    return;
                }

                // Generate OTP and send email
                String otp = OTPService.generateOTP();
                OTPService.sendOTPEmail(email, otp);
                JOptionPane.showMessageDialog(this, "An OTP has been sent to your email.");

                // Prompt for OTP
                String userOtp = JOptionPane.showInputDialog(this, "Enter the OTP sent to your email:");

                if (userOtp != null && OTPService.verifyOTP(email, userOtp.trim())) {
                    JOptionPane.showMessageDialog(this, "OTP verified successfully.");
                    SessionManager.login(emailField.getText());
                    new DepartmentForm().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid or expired OTP. Login failed.", "OTP Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or employee ID.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to send OTP: " + ex.getMessage(), "Email Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(() -> new logInnForm());
    }
}