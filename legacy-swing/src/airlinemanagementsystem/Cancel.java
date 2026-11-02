package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class Cancel extends JFrame implements ActionListener {
    private JTextField tfPnr, tfName, tfCancelNo, tfFlightCode, tfDate;
    private JButton btnFetch, btnCancel, btnBack;

    public Cancel() {
        setTitle("Ticket Cancellation");
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);

        JLabel lblTitle = new JLabel("CANCELLATION OF TICKET", JLabel.CENTER);
        lblTitle.setBounds(50, 15, 600, 30);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(220, 53, 69));
        add(lblTitle);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50, 65, 600, 370);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1));
        add(panel);

        int lblX = 50, fldX = 200, w = 320, h = 30, y = 30, gap = 50;

        // PNR + Fetch
        JLabel lblPnr = new JLabel("Enter PNR No:");
        lblPnr.setBounds(lblX, y, 140, h);
        lblPnr.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblPnr);

        tfPnr = new JTextField();
        tfPnr.setBounds(fldX, y, 190, h);
        panel.add(tfPnr);

        btnFetch = new JButton("Fetch Ticket");
        btnFetch.setBounds(fldX + 200, y, 120, h);
        btnFetch.setBackground(new Color(24, 76, 120));
        btnFetch.setForeground(Color.WHITE);
        btnFetch.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnFetch.addActionListener(this);
        panel.add(btnFetch);

        // Name
        y += gap;
        JLabel lblName = new JLabel("Passenger Name:");
        lblName.setBounds(lblX, y, 140, h);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(fldX, y, w, h);
        tfName.setEditable(false);
        tfName.setBackground(new Color(245, 245, 245));
        panel.add(tfName);

        // Cancellation No
        y += gap;
        JLabel lblCancel = new JLabel("Cancellation No:");
        lblCancel.setBounds(lblX, y, 140, h);
        lblCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblCancel);

        Random r = new Random();
        tfCancelNo = new JTextField("CAN-" + (10000 + r.nextInt(90000)));
        tfCancelNo.setBounds(fldX, y, w, h);
        tfCancelNo.setEditable(false);
        tfCancelNo.setBackground(new Color(245, 245, 245));
        panel.add(tfCancelNo);

        // Flight Code
        y += gap;
        JLabel lblCode = new JLabel("Flight Code:");
        lblCode.setBounds(lblX, y, 140, h);
        lblCode.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblCode);

        tfFlightCode = new JTextField();
        tfFlightCode.setBounds(fldX, y, w, h);
        tfFlightCode.setEditable(false);
        tfFlightCode.setBackground(new Color(245, 245, 245));
        panel.add(tfFlightCode);

        // Date of Travel
        y += gap;
        JLabel lblDate = new JLabel("Date of Travel:");
        lblDate.setBounds(lblX, y, 140, h);
        lblDate.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblDate);

        tfDate = new JTextField();
        tfDate.setBounds(fldX, y, w, h);
        tfDate.setEditable(false);
        tfDate.setBackground(new Color(245, 245, 245));
        panel.add(tfDate);

        // Buttons
        y += 65;
        btnCancel = new JButton("Confirm Cancellation");
        btnCancel.setBounds(fldX, y, 180, 36);
        btnCancel.setBackground(new Color(220, 53, 69));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        panel.add(btnCancel);

        btnBack = new JButton("Close");
        btnBack.setBounds(fldX + 195, y, 100, 36);
        btnBack.setBackground(new Color(108, 117, 125));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(this);
        panel.add(btnBack);

        setSize(720, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnFetch) {
            String pnr = tfPnr.getText().trim();
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter PNR number!", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM reservation WHERE pnr = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, pnr);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    tfName.setText(rs.getString("name"));
                    tfFlightCode.setText(rs.getString("flight_code"));
                    tfDate.setText(rs.getString("travel_date"));
                } else {
                    JOptionPane.showMessageDialog(this, "No active reservation found for PNR: " + pnr, "Not Found", JOptionPane.WARNING_MESSAGE);
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnCancel) {
            String pnr = tfPnr.getText().trim();
            String name = tfName.getText().trim();
            String cancelNo = tfCancelNo.getText().trim();
            String flightCode = tfFlightCode.getText().trim();
            String travelDate = tfDate.getText().trim();

            if (pnr.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fetch a valid reservation first!", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel ticket for " + name + " (PNR: " + pnr + ")?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                Conn conn = new Conn();
                // Insert into cancel table
                String insertCancel = "INSERT INTO cancel (pnr, name, cancel_no, flight_code, travel_date) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement psInsert = conn.c.prepareStatement(insertCancel);
                psInsert.setString(1, pnr);
                psInsert.setString(2, name);
                psInsert.setString(3, cancelNo);
                psInsert.setString(4, flightCode);
                psInsert.setString(5, travelDate);
                psInsert.executeUpdate();

                // Remove from reservation table
                String deleteReservation = "DELETE FROM reservation WHERE pnr = ?";
                PreparedStatement psDelete = conn.c.prepareStatement(deleteReservation);
                psDelete.setString(1, pnr);
                psDelete.executeUpdate();

                JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully!\nCancellation Reference: " + cancelNo, "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                conn.close();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Cancellation failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == btnBack) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cancel());
    }
}
