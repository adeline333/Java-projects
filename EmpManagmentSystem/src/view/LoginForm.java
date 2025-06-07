package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;

public class LoginForm {
    private JFrame frame;
    private JTextField txtUserId;
    private JPasswordField txtPassword;

    public LoginForm() {
        frame = new JFrame("Admin Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 300);
        frame.setLocationRelativeTo(null);

        // Main Panel with gradient blue background
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1 = new Color(70, 130, 180); // SteelBlue
                Color c2 = new Color(100, 149, 237); // CornflowerBlue
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Title Label
        JLabel lblTitle = new JLabel("Admin Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(Color.WHITE);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form Panel for User ID and Password
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        formPanel.setOpaque(false);

        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUserId.setForeground(Color.WHITE);
        txtUserId = new JTextField();
        txtUserId.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUserId.setBorder(new LineBorder(new Color(70, 130, 180), 2, true)); // blue border

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPassword.setBorder(new LineBorder(new Color(70, 130, 180), 2, true)); // blue border

        formPanel.add(lblUserId);
        formPanel.add(txtUserId);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Login Button with blue color theme
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(new Color(30, 144, 255)); // DodgerBlue
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(new RoundedBorder(10)); // Rounded border for the button
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(btnLogin);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Login action when button clicked
        btnLogin.addActionListener(e -> {
            String userId = txtUserId.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (authenticateUser(userId, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();
                new HomePage(); // Replace with your actual home page class
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials or not an admin.");
            }
        });
    }

    // Authenticate the user from the database
    private boolean authenticateUser(String userId, String password) {
        String url = "jdbc:mysql://localhost:3306/employee_management_db";
        String dbUser = "root";
        String dbPass = "waterAndMilk1#";

        String query = "SELECT * FROM users WHERE user_id = ? AND password = ? AND role = 'admin'";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userId);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if admin found

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database error occurred.");
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }

    // Custom Rounded Border for buttons and text fields
    class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(30, 144, 255)); // DodgerBlue color for the border
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
