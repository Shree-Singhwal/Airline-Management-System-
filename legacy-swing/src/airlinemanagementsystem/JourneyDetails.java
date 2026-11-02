package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class JourneyDetails extends JFrame implements ActionListener {
    private JTextField tfPnr;
    private JButton btnSearch, btnShowAll, btnClose;
    private JTable table;
    private DefaultTableModel model;

    public JourneyDetails() {
        setTitle("Journey Details");
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Top Filter Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        topPanel.setBackground(new Color(24, 76, 120));

        JLabel lblPnr = new JLabel("Enter PNR Number:");
        lblPnr.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPnr.setForeground(Color.WHITE);
        topPanel.add(lblPnr);

        tfPnr = new JTextField(12);
        tfPnr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(tfPnr);

        btnSearch = new JButton("Search Journey");
        btnSearch.setBackground(new Color(40, 167, 69));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSearch.addActionListener(this);
        topPanel.add(btnSearch);

        btnShowAll = new JButton("Show All Bookings");
        btnShowAll.setBackground(new Color(0, 123, 255));
        btnShowAll.setForeground(Color.WHITE);
        btnShowAll.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnShowAll.addActionListener(this);
        topPanel.add(btnShowAll);

        add(topPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(230, 238, 248));
        table.getTableHeader().setForeground(new Color(24, 76, 120));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel btmPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnClose = new JButton("Close");
        btnClose.setBackground(new Color(108, 117, 125));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnClose.addActionListener(e -> dispose());
        btmPanel.add(btnClose);
        add(btmPanel, BorderLayout.SOUTH);

        setSize(950, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        loadData(null);
    }

    private void loadData(String pnr) {
        try {
            Conn conn = new Conn();
            String query;
            PreparedStatement ps;

            if (pnr != null && !pnr.trim().isEmpty()) {
                query = "SELECT pnr AS 'PNR', ticket_id AS 'Ticket ID', aadhar AS 'Aadhar', name AS 'Passenger Name', flight_code AS 'Flight Code', flight_name AS 'Airline', source AS 'Source', destination AS 'Destination', travel_date AS 'Travel Date' FROM reservation WHERE pnr = ?";
                ps = conn.c.prepareStatement(query);
                ps.setString(1, pnr.trim());
            } else {
                query = "SELECT pnr AS 'PNR', ticket_id AS 'Ticket ID', aadhar AS 'Aadhar', name AS 'Passenger Name', flight_code AS 'Flight Code', flight_name AS 'Airline', source AS 'Source', destination AS 'Destination', travel_date AS 'Travel Date' FROM reservation";
                ps = conn.c.prepareStatement(query);
            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            Vector<String> colNames = new Vector<>();
            for (int i = 1; i <= colCount; i++) {
                colNames.add(meta.getColumnLabel(i));
            }

            Vector<Vector<Object>> rows = new Vector<>();
            boolean found = false;
            while (rs.next()) {
                found = true;
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= colCount; i++) {
                    row.add(rs.getObject(i));
                }
                rows.add(row);
            }

            if (!found && pnr != null) {
                JOptionPane.showMessageDialog(this, "No journey found with PNR: " + pnr, "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }

            model.setDataVector(rows, colNames);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching journey records: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSearch) {
            String pnr = tfPnr.getText().trim();
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid PNR number!", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            loadData(pnr);
        } else if (ae.getSource() == btnShowAll) {
            tfPnr.setText("");
            loadData(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JourneyDetails());
    }
}
