package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BookFlight extends JFrame implements ActionListener {
    private JTextField tfAadhar, tfName, tfNationality, tfAddress, tfGender, tfDate;
    private JComboBox<String> cbSource, cbDestination, cbFlight;
    private JLabel lblTicketIdVal, lblPnrVal;
    private JButton btnFetchUser, btnSearchFlights, btnBook, btnCancel;

    public BookFlight() {
        setTitle("Book Flight Ticket");
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);

        JLabel lblTitle = new JLabel("FLIGHT RESERVATION SYSTEM", JLabel.CENTER);
        lblTitle.setBounds(50, 15, 750, 30);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(24, 76, 120));
        add(lblTitle);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(40, 60, 780, 520);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1));
        add(panel);

        int lblX = 40, fldX = 170, w = 240, h = 28, y = 25, gap = 45;

        // Aadhar + Fetch User
        JLabel lblAadhar = new JLabel("Aadhar No:");
        lblAadhar.setBounds(lblX, y, 120, h);
        lblAadhar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(fldX, y, 170, h);
        panel.add(tfAadhar);

        btnFetchUser = new JButton("Fetch User");
        btnFetchUser.setBounds(fldX + 180, y, 105, h);
        btnFetchUser.setBackground(new Color(24, 76, 120));
        btnFetchUser.setForeground(Color.WHITE);
        btnFetchUser.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnFetchUser.addActionListener(this);
        panel.add(btnFetchUser);

        // Name
        y += gap;
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(lblX, y, 120, h);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(fldX, y, w, h);
        tfName.setEditable(false);
        tfName.setBackground(new Color(245, 245, 245));
        panel.add(tfName);

        // Nationality
        y += gap;
        JLabel lblNat = new JLabel("Nationality:");
        lblNat.setBounds(lblX, y, 120, h);
        lblNat.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblNat);

        tfNationality = new JTextField();
        tfNationality.setBounds(fldX, y, w, h);
        tfNationality.setEditable(false);
        tfNationality.setBackground(new Color(245, 245, 245));
        panel.add(tfNationality);

        // Address
        y += gap;
        JLabel lblAddr = new JLabel("Address:");
        lblAddr.setBounds(lblX, y, 120, h);
        lblAddr.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblAddr);

        tfAddress = new JTextField();
        tfAddress.setBounds(fldX, y, w, h);
        tfAddress.setEditable(false);
        tfAddress.setBackground(new Color(245, 245, 245));
        panel.add(tfAddress);

        // Gender
        y += gap;
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(lblX, y, 120, h);
        lblGender.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblGender);

        tfGender = new JTextField();
        tfGender.setBounds(fldX, y, w, h);
        tfGender.setEditable(false);
        tfGender.setBackground(new Color(245, 245, 245));
        panel.add(tfGender);

        // Column 2 - Flight Routing
        int col2LblX = 430, col2FldX = 550, col2W = 190;
        int y2 = 25;

        // Source
        JLabel lblSource = new JLabel("Source City:");
        lblSource.setBounds(col2LblX, y2, 110, h);
        lblSource.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblSource);

        String[] cities = {"Delhi", "Mumbai", "Bangalore", "Kolkata", "Chennai", "Goa", "Dubai"};
        cbSource = new JComboBox<>(cities);
        cbSource.setBounds(col2FldX, y2, col2W, h);
        panel.add(cbSource);

        // Destination
        y2 += gap;
        JLabel lblDest = new JLabel("Destination:");
        lblDest.setBounds(col2LblX, y2, 110, h);
        lblDest.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblDest);

        cbDestination = new JComboBox<>(cities);
        cbDestination.setSelectedIndex(1);
        cbDestination.setBounds(col2FldX, y2, col2W, h);
        panel.add(cbDestination);

        // Search Flights button
        y2 += gap;
        btnSearchFlights = new JButton("Search Available Flights");
        btnSearchFlights.setBounds(col2FldX, y2, col2W, h);
        btnSearchFlights.setBackground(new Color(0, 123, 255));
        btnSearchFlights.setForeground(Color.WHITE);
        btnSearchFlights.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSearchFlights.addActionListener(this);
        panel.add(btnSearchFlights);

        // Available Flights
        y2 += gap;
        JLabel lblFlight = new JLabel("Select Flight:");
        lblFlight.setBounds(col2LblX, y2, 110, h);
        lblFlight.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblFlight);

        cbFlight = new JComboBox<>();
        cbFlight.setBounds(col2FldX, y2, col2W, h);
        panel.add(cbFlight);

        // Travel Date
        y2 += gap;
        JLabel lblDate = new JLabel("Travel Date:");
        lblDate.setBounds(col2LblX, y2, 110, h);
        lblDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(lblDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tfDate = new JTextField(sdf.format(new Date()));
        tfDate.setBounds(col2FldX, y2, col2W, h);
        panel.add(tfDate);

        // Auto IDs
        int y3 = 290;
        JPanel infoBox = new JPanel(new GridLayout(2, 2, 10, 10));
        infoBox.setBounds(lblX, y3, 700, 70);
        infoBox.setBackground(new Color(245, 250, 255));
        infoBox.setBorder(BorderFactory.createLineBorder(new Color(180, 210, 240), 1));

        Random random = new Random();
        String generatedPNR = "PNR-" + (100000 + random.nextInt(900000));
        String generatedTicket = "TIC-" + (1000 + random.nextInt(9000));

        JLabel lblPnr = new JLabel("  Generated PNR: ", JLabel.LEFT);
        lblPnrVal = new JLabel(generatedPNR, JLabel.LEFT);
        lblPnrVal.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblPnrVal.setForeground(new Color(24, 76, 120));

        JLabel lblTicketId = new JLabel("  Generated Ticket ID: ", JLabel.LEFT);
        lblTicketIdVal = new JLabel(generatedTicket, JLabel.LEFT);
        lblTicketIdVal.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTicketIdVal.setForeground(new Color(24, 76, 120));

        infoBox.add(lblPnr);
        infoBox.add(lblPnrVal);
        infoBox.add(lblTicketId);
        infoBox.add(lblTicketIdVal);
        panel.add(infoBox);

        // Action Buttons
        btnBook = new JButton("Confirm Booking");
        btnBook.setBounds(240, 420, 160, 40);
        btnBook.setBackground(new Color(40, 167, 69));
        btnBook.setForeground(Color.WHITE);
        btnBook.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBook.setFocusPainted(false);
        btnBook.addActionListener(this);
        panel.add(btnBook);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(420, 420, 120, 40);
        btnCancel.setBackground(new Color(108, 117, 125));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        panel.add(btnCancel);

        setSize(870, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Preload flights
        searchFlights();
    }

    private void searchFlights() {
        cbFlight.removeAllItems();
        String src = (String) cbSource.getSelectedItem();
        String dest = (String) cbDestination.getSelectedItem();
        if (src.equals(dest)) {
            JOptionPane.showMessageDialog(this, "Source and Destination cannot be the same!", "Route Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Conn conn = new Conn();
            String q = "SELECT flight_code, flight_name FROM flight WHERE source = ? AND destination = ?";
            PreparedStatement ps = conn.c.prepareStatement(q);
            ps.setString(1, src);
            ps.setString(2, dest);
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                cbFlight.addItem(rs.getString("flight_code") + " - " + rs.getString("flight_name"));
            }
            if (!found) {
                // If direct flight not found, list all flights
                ResultSet rsAll = conn.s.executeQuery("SELECT flight_code, flight_name FROM flight");
                while (rsAll.next()) {
                    cbFlight.addItem(rsAll.getString("flight_code") + " - " + rsAll.getString("flight_name"));
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnFetchUser) {
            String aadhar = tfAadhar.getText().trim();
            if (aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Aadhar Number!", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM passenger WHERE aadhar = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, aadhar);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    tfName.setText(rs.getString("name"));
                    tfNationality.setText(rs.getString("nationality"));
                    tfAddress.setText(rs.getString("address"));
                    tfGender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(this, "Passenger not found with Aadhar: " + aadhar + "\nPlease add customer details first.", "Not Found", JOptionPane.WARNING_MESSAGE);
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnSearchFlights) {
            searchFlights();
        } else if (ae.getSource() == btnBook) {
            String aadhar = tfAadhar.getText().trim();
            String name = tfName.getText().trim();
            String nationality = tfNationality.getText().trim();
            String date = tfDate.getText().trim();
            String selectedFlight = (String) cbFlight.getSelectedItem();
            String src = (String) cbSource.getSelectedItem();
            String dest = (String) cbDestination.getSelectedItem();
            String pnr = lblPnrVal.getText();
            String ticketId = lblTicketIdVal.getText();

            if (name.isEmpty() || selectedFlight == null || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fetch passenger and ensure all details are filled!", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] flightParts = selectedFlight.split(" - ");
            String flightCode = flightParts[0];
            String flightName = flightParts.length > 1 ? flightParts[1] : "";

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO reservation (pnr, ticket_id, aadhar, name, nationality, flight_name, flight_code, source, destination, travel_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, pnr);
                ps.setString(2, ticketId);
                ps.setString(3, aadhar);
                ps.setString(4, name);
                ps.setString(5, nationality);
                ps.setString(6, flightName);
                ps.setString(7, flightCode);
                ps.setString(8, src);
                ps.setString(9, dest);
                ps.setString(10, date);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Ticket Booked Successfully!\nPNR: " + pnr + "\nTicket ID: " + ticketId, "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
                conn.close();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Booking Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == btnCancel) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookFlight());
    }
}
