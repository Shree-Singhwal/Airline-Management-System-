package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JButton btnSubmit, btnReset, btnClose, btnRegister;

    public Login() {
        setTitle("Airline Management System - Login");
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);

        // Header Title
        JLabel lblHeader = new JLabel("AIRLINE MANAGEMENT SYSTEM", JLabel.CENTER);
        lblHeader.setBounds(50, 20, 500, 30);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeader.setForeground(new Color(24, 76, 120));
        add(lblHeader);

        JLabel lblSub = new JLabel("Please enter your admin credentials to login", JLabel.CENTER);
        lblSub.setBounds(50, 55, 500, 20);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(Color.GRAY);
        add(lblSub);

        // Card Panel
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(60, 90, 480, 250);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1));
        add(card);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(40, 30, 100, 25);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        card.add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(150, 30, 280, 30);
        tfUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        card.add(tfUsername);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(40, 75, 100, 25);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        card.add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(150, 75, 280, 30);
        tfPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        card.add(tfPassword);

        // Buttons Row 1: Login, Reset, Close
        btnSubmit = new JButton("Login");
        btnSubmit.setBounds(150, 125, 85, 32);
        btnSubmit.setBackground(new Color(24, 76, 120));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSubmit.setFocusPainted(false);
        btnSubmit.addActionListener(this);
        card.add(btnSubmit);

        btnReset = new JButton("Reset");
        btnReset.setBounds(245, 125, 85, 32);
        btnReset.setBackground(new Color(108, 117, 125));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(this);
        card.add(btnReset);

        btnClose = new JButton("Close");
        btnClose.setBounds(340, 125, 90, 32);
        btnClose.setBackground(new Color(220, 53, 69));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(this);
        card.add(btnClose);

        // Buttons Row 2: First Time User Registration Button
        JSeparator sep = new JSeparator();
        sep.setBounds(40, 175, 400, 10);
        card.add(sep);

        btnRegister = new JButton("✨ First Time User? Register / Sign Up");
        btnRegister.setBounds(100, 195, 280, 34);
        btnRegister.setBackground(new Color(40, 167, 69));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.addActionListener(this);
        card.add(btnRegister);

        // Enter key triggers login
        getRootPane().setDefaultButton(btnSubmit);

        setSize(620, 410);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSubmit) {
            String username = tfUsername.getText().trim();
            String password = new String(tfPassword.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Username and Password!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn conn = new Conn();
                if (conn.c == null) {
                    JOptionPane.showMessageDialog(this, "Could not connect to Database!\nPlease verify MySQL is running and password in Conn.java is correct.", "Database Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful! Welcome " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    dispose();
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password!\nClick 'First Time User? Register / Sign Up' to create an account.", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == btnReset) {
            tfUsername.setText("");
            tfPassword.setText("");
            tfUsername.requestFocus();
        } else if (ae.getSource() == btnClose) {
            System.exit(0);
        } else if (ae.getSource() == btnRegister) {
            new Signup();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
