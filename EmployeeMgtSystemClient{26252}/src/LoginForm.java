
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.Query;
import model.Employee;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class LoginForm extends JFrame {
    private JTextField emailField;
    private JPasswordField empIdField;
    private JButton loginButton;

    private SessionFactory factory;

    public LoginForm() {
        setTitle("Employee Login");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password (Emp ID):"));
        empIdField = new JPasswordField();
        add(empIdField);

        add(new JLabel()); // Empty
        loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(e -> performLogin());

        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        setVisible(true);
    }

    private void performLogin() {
        String email = emailField.getText().trim();
        String empId = new String(empIdField.getPassword()).trim();

        Session session = factory.openSession();
        // No generics here, use raw Query
Query query = session.createQuery("FROM Employee WHERE email = :email AND empId = :empId");
query.setParameter("email", email);
query.setParameter("empId", empId);

// Cast result manually
Employee emp = (Employee) query.uniqueResult();



            if (emp != null) {
                if (!"111".equals(emp.getDepartment())) {
                    JOptionPane.showMessageDialog(this,
                            "You aren't in the admin's department therefore can't login.");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Welcome " + emp.getFullName());
                    // You can redirect to dashboard here
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid email or emp ID.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }
}
