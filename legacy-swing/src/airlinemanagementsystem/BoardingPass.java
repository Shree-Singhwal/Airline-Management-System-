package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardingPass extends JFrame implements ActionListener {
    private JTextField tfPnr;
    private JLabel lblNameVal, lblNatVal, lblSrcVal, lblDestVal, lblFlightNameVal, lblFlightCodeVal, lblDateVal, lblSeatVal, lblGateVal;
    private JButton btnFetch, btnPrint, btnClose;
    private JPanel passCard;

    public BoardingPass() {
        setTitle("Passenger Boarding Pass");
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);

        // PNR Input Section at Top
        JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        pnlTop.setBounds(40, 15, 780, 50);
        pnlTop.setBackground(Color.WHITE);
        pnlTop.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1));

        JLabel lblPnr = new JLabel("Enter Booking PNR:");
        lblPnr.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPnr.setForeground(new Color(24, 76, 120));
        pnlTop.add(lblPnr);

        tfPnr = new JTextField(12);
        tfPnr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnlTop.add(tfPnr);

        btnFetch = new JButton("Generate Boarding Pass");
        btnFetch.setBackground(new Color(24, 76, 120));
        btnFetch.setForeground(Color.WHITE);
        btnFetch.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnFetch.addActionListener(this);
        pnlTop.add(btnFetch);
        add(pnlTop);

        // Visual Boarding Pass Card
        passCard = new JPanel();
        passCard.setLayout(null);
        passCard.setBounds(40, 80, 780, 360);
        passCard.setBackground(Color.WHITE);
        passCard.setBorder(BorderFactory.createLineBorder(new Color(24, 76, 120), 2));
        add(passCard);

        // Pass Header
        JPanel passHeader = new JPanel();
        passHeader.setLayout(new BorderLayout());
        passHeader.setBounds(0, 0, 780, 60);
        passHeader.setBackground(new Color(24, 76, 120));

        JLabel lblAirline = new JLabel("  ✈ AIRLINE MANAGEMENT SYSTEM  |  OFFICIAL BOARDING PASS", JLabel.LEFT);
        lblAirline.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblAirline.setForeground(Color.WHITE);
        passHeader.add(lblAirline, BorderLayout.WEST);

        JLabel lblClass = new JLabel("ECONOMY CLASS  ", JLabel.RIGHT);
        lblClass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblClass.setForeground(new Color(255, 193, 7));
        passHeader.add(lblClass, BorderLayout.EAST);
        passCard.add(passHeader);

        // Pass Body Details
        int x1 = 40, x2 = 180, x3 = 420, x4 = 550, h = 25, y = 80, gap = 40;

        // Row 1: Name & Flight Name
        JLabel lblName = new JLabel("Passenger Name:");
        lblName.setBounds(x1, y, 130, h);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblName);

        lblNameVal = new JLabel("-------------------------");
        lblNameVal.setBounds(x2, y, 200, h);
        lblNameVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passCard.add(lblNameVal);

        JLabel lblFlightName = new JLabel("Airline / Carrier:");
        lblFlightName.setBounds(x3, y, 130, h);
        lblFlightName.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblFlightName);

        lblFlightNameVal = new JLabel("-------------------------");
        lblFlightNameVal.setBounds(x4, y, 200, h);
        lblFlightNameVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passCard.add(lblFlightNameVal);

        // Row 2: Nationality & Flight Code
        y += gap;
        JLabel lblNat = new JLabel("Nationality:");
        lblNat.setBounds(x1, y, 130, h);
        lblNat.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblNat);

        lblNatVal = new JLabel("-------------------------");
        lblNatVal.setBounds(x2, y, 200, h);
        lblNatVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passCard.add(lblNatVal);

        JLabel lblFlightCode = new JLabel("Flight Code:");
        lblFlightCode.setBounds(x3, y, 130, h);
        lblFlightCode.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblFlightCode);

        lblFlightCodeVal = new JLabel("-------------------------");
        lblFlightCodeVal.setBounds(x4, y, 200, h);
        lblFlightCodeVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passCard.add(lblFlightCodeVal);

        // Row 3: Origin & Destination
        y += gap;
        JLabel lblSrc = new JLabel("Departure City:");
        lblSrc.setBounds(x1, y, 130, h);
        lblSrc.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblSrc);

        lblSrcVal = new JLabel("-------------------------");
        lblSrcVal.setBounds(x2, y, 200, h);
        lblSrcVal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSrcVal.setForeground(new Color(0, 102, 204));
        passCard.add(lblSrcVal);

        JLabel lblDest = new JLabel("Arrival City:");
        lblDest.setBounds(x3, y, 130, h);
        lblDest.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblDest);

        lblDestVal = new JLabel("-------------------------");
        lblDestVal.setBounds(x4, y, 200, h);
        lblDestVal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDestVal.setForeground(new Color(0, 102, 204));
        passCard.add(lblDestVal);

        // Row 4: Travel Date & Boarding Info
        y += gap;
        JLabel lblDate = new JLabel("Flight Date:");
        lblDate.setBounds(x1, y, 130, h);
        lblDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblDate);

        lblDateVal = new JLabel("-------------------------");
        lblDateVal.setBounds(x2, y, 200, h);
        lblDateVal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passCard.add(lblDateVal);

        JLabel lblGate = new JLabel("Gate / Terminal:");
        lblGate.setBounds(x3, y, 130, h);
        lblGate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblGate);

        lblGateVal = new JLabel("T3 - Gate B12");
        lblGateVal.setBounds(x4, y, 200, h);
        lblGateVal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblGateVal.setForeground(new Color(40, 167, 69));
        passCard.add(lblGateVal);

        // Seat Pill
        y += gap;
        JLabel lblSeat = new JLabel("Assigned Seat:");
        lblSeat.setBounds(x1, y, 130, h);
        lblSeat.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passCard.add(lblSeat);

        lblSeatVal = new JLabel("14-A (Window)");
        lblSeatVal.setBounds(x2, y, 200, h);
        lblSeatVal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSeatVal.setForeground(new Color(220, 53, 69));
        passCard.add(lblSeatVal);

        // Barcode Simulation Label
        JLabel lblBarcode = new JLabel("||||||| | ||||| |||| |||||||| ||||| ||| ||||||| |||||| |||", JLabel.CENTER);
        lblBarcode.setBounds(40, y + 45, 700, 30);
        lblBarcode.setFont(new Font("Courier New", Font.BOLD, 18));
        lblBarcode.setForeground(Color.DARK_GRAY);
        passCard.add(lblBarcode);

        // Bottom Controls
        btnPrint = new JButton("Print / Save Boarding Pass");
        btnPrint.setBounds(280, 460, 200, 36);
        btnPrint.setBackground(new Color(40, 167, 69));
        btnPrint.setForeground(Color.WHITE);
        btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPrint.addActionListener(this);
        add(btnPrint);

        btnClose = new JButton("Close");
        btnClose.setBounds(500, 460, 100, 36);
        btnClose.setBackground(new Color(108, 117, 125));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnClose.addActionListener(e -> dispose());
        add(btnClose);

        setSize(870, 550);
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
                    lblNameVal.setText(rs.getString("name"));
                    lblNatVal.setText(rs.getString("nationality"));
                    lblFlightNameVal.setText(rs.getString("flight_name"));
                    lblFlightCodeVal.setText(rs.getString("flight_code"));
                    lblSrcVal.setText(rs.getString("source"));
                    lblDestVal.setText(rs.getString("destination"));
                    lblDateVal.setText(rs.getString("travel_date"));
                } else {
                    JOptionPane.showMessageDialog(this, "No booking found for PNR: " + pnr, "Not Found", JOptionPane.WARNING_MESSAGE);
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnPrint) {
            if (lblNameVal.getText().contains("---")) {
                JOptionPane.showMessageDialog(this, "Please generate a boarding pass with valid PNR first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "Boarding Pass ready! Press Ctrl+P or save as PDF.", "Print", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BoardingPass());
    }
}
