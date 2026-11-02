package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Signup extends JFrame implements ActionListener {
    private JTextField tfUsername;
    private JPasswordField tfPassword, tfConfirmPassword;
    private JButton btnRegister, btnBack;

    public Signup() {
        setTitle("Airline Management System - New User Registration");
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);

        JLabel lblHeader = new JLabel("FIRST TIME USER REGISTRATION", JLabel.CENTER);
        lblHeader.setBounds(50, 20, 500, 30);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setForeground(new Color(40, 167, 69));
        add(lblHeader);

        JLabel lblSub = new JLabel("Create an admin username and password", JLabel.CENTER);
        lblSub.setBounds(50, 50, 500, 20);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(Color.GRAY);
        add(lblSub);

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(60, 80, 480, 220);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1));
        add(card);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(40, 25, 120, 25);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 13));
        card.add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(170, 25, 260, 28);
        tfUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card.add(tfUsername);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(40, 65, 120, 25);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        card.add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(170, 65, 260, 28);
        card.add(tfPassword);

        // Confirm Password
        JLabel lblConfirm = new JLabel("Confirm Password:");
        lblConfirm.setBounds(40, 105, 130, 25);
        lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
        card.add(lblConfirm);

        tfConfirmPassword = new JPasswordField();
        tfConfirmPassword.setBounds(170, 105, 260, 28);
        card.add(tfConfirmPassword);

        // Buttons
        btnRegister = new JButton("Create Account");
        btnRegister.setBounds(170, 160, 140, 32);
        btnRegister.setBackground(new Color(40, 167, 69));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRegister.setFocusPainted(false);
        btnRegister.addActionListener(this);
        card.add(btnRegister);

        btnBack = new JButton("Back to Login");
        btnBack.setBounds(320, 160, 110, 32);
        btnBack.setBackground(new Color(108, 117, 125));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(this);
        card.add(btnBack);

        getRootPane().setDefaultButton(btnRegister);

        setSize(620, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRegister) {
            String username = tfUsername.getText().trim();
            String password = new String(tfPassword.getPassword()).trim();
            String confirm = new String(tfConfirmPassword.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Conn conn = new Conn();
                if (conn.c == null) {
                    JOptionPane.showMessageDialog(this, "Could not connect to Database!\nPlease verify MySQL is running.", "Database Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Automatically ensure table exists
                Statement stmt = conn.c.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS login (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50) NOT NULL)");

                // Insert new user
                String insertQuery = "INSERT INTO login (username, password) VALUES (?, ?)";
                PreparedStatement ps = conn.c.prepareStatement(insertQuery);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Account created successfully! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                conn.close();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Username already exists or database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == btnBack) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Signup());
    }
}
