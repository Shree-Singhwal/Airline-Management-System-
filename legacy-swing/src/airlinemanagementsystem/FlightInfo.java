package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class FlightInfo extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public FlightInfo() {
        setTitle("Flight Information");
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header Panel
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlHeader.setBackground(new Color(24, 76, 120));
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JLabel lblTitle = new JLabel("AVAILABLE FLIGHT SCHEDULE");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(230, 238, 248));
        table.getTableHeader().setForeground(new Color(24, 76, 120));
        table.setSelectionBackground(new Color(180, 210, 245));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Load Flights from Database
        loadFlightData();

        // Footer with refresh button
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(24, 76, 120));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRefresh.addActionListener(e -> loadFlightData());

        JButton btnClose = new JButton("Close");
        btnClose.setBackground(new Color(108, 117, 125));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnClose.addActionListener(e -> dispose());

        pnlBottom.add(btnRefresh);
        pnlBottom.add(btnClose);
        add(pnlBottom, BorderLayout.SOUTH);

        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadFlightData() {
        try {
            Conn conn = new Conn();
            if (conn.c == null) {
                JOptionPane.showMessageDialog(this, "Database connection not available!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT flight_code AS 'Flight Code', flight_name AS 'Airline / Flight Name', source AS 'Source', destination AS 'Destination' FROM flight";
            ResultSet rs = conn.s.executeQuery(query);
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(meta.getColumnLabel(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            tableModel.setDataVector(data, columnNames);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching flight records: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlightInfo());
    }
}
