package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    private JMenuItem mFlightDetails, mCustomerDetails, mBookFlight, mJourneyDetails, mCancelTicket, mBoardingPass, mLogout;
    private JButton btnFlight, btnCustomer, btnBook, btnJourney, btnCancel, btnBoarding;

    public Home() {
        setTitle("Airline Management System - Dashboard");
        setLayout(new BorderLayout());

        // Menu Bar Setup
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(24, 76, 120));
        menuBar.setPreferredSize(new Dimension(1000, 40));

        JMenu menuDetails = new JMenu("Management");
        menuDetails.setForeground(Color.WHITE);
        menuDetails.setFont(new Font("Segoe UI", Font.BOLD, 14));

        mFlightDetails = new JMenuItem("Flight Details");
        mCustomerDetails = new JMenuItem("Add Customer Details");
        mBookFlight = new JMenuItem("Book Flight");
        mJourneyDetails = new JMenuItem("Journey Details");
        mCancelTicket = new JMenuItem("Cancel Ticket");

        mFlightDetails.addActionListener(this);
        mCustomerDetails.addActionListener(this);
        mBookFlight.addActionListener(this);
        mJourneyDetails.addActionListener(this);
        mCancelTicket.addActionListener(this);

        menuDetails.add(mFlightDetails);
        menuDetails.add(mCustomerDetails);
        menuDetails.add(mBookFlight);
        menuDetails.add(mJourneyDetails);
        menuDetails.add(mCancelTicket);

        JMenu menuTicket = new JMenu("Ticket");
        menuTicket.setForeground(Color.WHITE);
        menuTicket.setFont(new Font("Segoe UI", Font.BOLD, 14));

        mBoardingPass = new JMenuItem("Generate Boarding Pass");
        mBoardingPass.addActionListener(this);
        menuTicket.add(mBoardingPass);

        JMenu menuAccount = new JMenu("Account");
        menuAccount.setForeground(Color.WHITE);
        menuAccount.setFont(new Font("Segoe UI", Font.BOLD, 14));

        mLogout = new JMenuItem("Logout");
        mLogout.addActionListener(this);
        menuAccount.add(mLogout);

        menuBar.add(menuDetails);
        menuBar.add(menuTicket);
        menuBar.add(menuAccount);
        setJMenuBar(menuBar);

        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 244, 248));

        // Top Banner
        JPanel banner = new JPanel();
        banner.setLayout(new BoxLayout(banner, BoxLayout.Y_AXIS));
        banner.setBackground(new Color(30, 90, 145));
        banner.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel lblTitle = new JLabel("AIR INDIA & DOMESTIC AIRLINE MANAGEMENT");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Fast, Reliable Flight Booking, Passenger Tracking & Boarding System");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSub.setForeground(new Color(210, 230, 250));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        banner.add(lblTitle);
        banner.add(Box.createVerticalStrut(10));
        banner.add(lblSub);
        mainPanel.add(banner, BorderLayout.NORTH);

        // Center Quick Action Grid
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 3, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        centerPanel.setBackground(new Color(240, 244, 248));

        btnFlight = createCardButton("✈ Flight Information", "View schedule & routes", new Color(0, 123, 255));
        btnCustomer = createCardButton("👤 Add Customer", "Register new passenger", new Color(40, 167, 69));
        btnBook = createCardButton("🎟 Book Ticket", "Reserve seat & generate PNR", new Color(255, 136, 0));
        btnJourney = createCardButton("📋 Journey Details", "Check reservation status", new Color(23, 162, 184));
        btnCancel = createCardButton("❌ Cancel Ticket", "Cancel booking with PNR", new Color(220, 53, 69));
        btnBoarding = createCardButton("🎫 Boarding Pass", "Print passenger boarding card", new Color(111, 66, 193));

        centerPanel.add(btnFlight);
        centerPanel.add(btnCustomer);
        centerPanel.add(btnBook);
        centerPanel.add(btnJourney);
        centerPanel.add(btnCancel);
        centerPanel.add(btnBoarding);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.setPreferredSize(new Dimension(1000, 35));
        JLabel lblFooter = new JLabel("Status: Connected to Database | Ready");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFooter.setForeground(Color.DARK_GRAY);
        footer.add(lblFooter);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createCardButton(String title, String subtitle, Color accentColor) {
        JButton btn = new JButton("<html><center><b style='font-size:14px;'>" + title + "</b><br/><span style='font-size:10px; color:#555555;'>" + subtitle + "</span></center></html>");
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(accentColor);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();
        if (src == mFlightDetails || src == btnFlight) {
            new FlightInfo();
        } else if (src == mCustomerDetails || src == btnCustomer) {
            new AddCustomer();
        } else if (src == mBookFlight || src == btnBook) {
            new BookFlight();
        } else if (src == mJourneyDetails || src == btnJourney) {
            new JourneyDetails();
        } else if (src == mCancelTicket || src == btnCancel) {
            new Cancel();
        } else if (src == mBoardingPass || src == btnBoarding) {
            new BoardingPass();
        } else if (src == mLogout) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                setVisible(false);
                dispose();
                new Login();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home());
    }
}
