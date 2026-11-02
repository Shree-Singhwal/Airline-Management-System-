package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class AddCustomer extends JFrame implements ActionListener {
    private JTextField tfName, tfNationality, tfAadhar, tfAddress, tfPhone;
    private JRadioButton rbMale, rbFemale, rbOther;
    private JButton btnSave, btnCancel;

    public AddCustomer() {
        setTitle("New Customer Registration");
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);

        JLabel lblTitle = new JLabel("REGISTER NEW PASSENGER", JLabel.CENTER);
        lblTitle.setBounds(50, 20, 600, 30);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(24, 76, 120));
        add(lblTitle);

        JPanel formCard = new JPanel();
        formCard.setLayout(null);
        formCard.setBounds(50, 70, 600, 420);
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1));
        add(formCard);

        int labelX = 50, fieldX = 200, width = 340, height = 30, y = 30, gap = 50;

        // Name
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(labelX, y, 140, height);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(fieldX, y, width, height);
        tfName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formCard.add(tfName);

        // Nationality
        y += gap;
        JLabel lblNat = new JLabel("Nationality:");
        lblNat.setBounds(labelX, y, 140, height);
        lblNat.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(lblNat);

        tfNationality = new JTextField("Indian");
        tfNationality.setBounds(fieldX, y, width, height);
        tfNationality.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formCard.add(tfNationality);

        // Aadhar Number
        y += gap;
        JLabel lblAadhar = new JLabel("Aadhar / ID No:");
        lblAadhar.setBounds(labelX, y, 140, height);
        lblAadhar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(fieldX, y, width, height);
        tfAadhar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formCard.add(tfAadhar);

        // Address
        y += gap;
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(labelX, y, 140, height);
        lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(fieldX, y, width, height);
        tfAddress.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formCard.add(tfAddress);

        // Gender
        y += gap;
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(labelX, y, 140, height);
        lblGender.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(lblGender);

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(fieldX, y, 70, height);
        rbMale.setBackground(Color.WHITE);
        rbMale.setSelected(true);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(fieldX + 80, y, 80, height);
        rbFemale.setBackground(Color.WHITE);

        rbOther = new JRadioButton("Other");
        rbOther.setBounds(fieldX + 170, y, 80, height);
        rbOther.setBackground(Color.WHITE);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);
        bg.add(rbOther);

        formCard.add(rbMale);
        formCard.add(rbFemale);
        formCard.add(rbOther);

        // Phone
        y += gap;
        JLabel lblPhone = new JLabel("Phone Number:");
        lblPhone.setBounds(labelX, y, 140, height);
        lblPhone.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formCard.add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(fieldX, y, width, height);
        tfPhone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formCard.add(tfPhone);

        // Buttons
        y += 60;
        btnSave = new JButton("Save Customer");
        btnSave.setBounds(fieldX, y, 150, 35);
        btnSave.setBackground(new Color(40, 167, 69));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(this);
        formCard.add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(fieldX + 170, y, 120, 35);
        btnCancel.setBackground(new Color(108, 117, 125));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        formCard.add(btnCancel);

        setSize(720, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSave) {
            String name = tfName.getText().trim();
            String nationality = tfNationality.getText().trim();
            String aadhar = tfAadhar.getText().trim();
            String address = tfAddress.getText().trim();
            String phone = tfPhone.getText().trim();
            String gender = rbMale.isSelected() ? "Male" : (rbFemale.isSelected() ? "Female" : "Other");

            if (name.isEmpty() || aadhar.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields!", "Validation Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO passenger (aadhar, name, nationality, phone, address, gender) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, aadhar);
                ps.setString(2, name);
                ps.setString(3, nationality);
                ps.setString(4, phone);
                ps.setString(5, address);
                ps.setString(6, gender);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Passenger Details Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                conn.close();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error inserting passenger (Aadhar might already exist): " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == btnCancel) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddCustomer());
    }
}
