package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class CustomerPage extends JFrame {
    private JSeparator separator;
    private JButton btnContact, btnNotification, btnFeedback, btnRules, btnLogout;
    private JLabel lblServices;
    private JSeparator separator_1;
    private JButton btnServices, btnPayment, btnRTS;

	public CustomerPage(int userID) {
		System.out.println(userID);
        setTitle("Customer Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        setLocationRelativeTo(null);

        var mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // Sidebar
        var sidebar = new JPanel();
        sidebar.setBackground(new Color(0, 128, 128));
        sidebar.setBounds(10, 11, 290, 811);
        sidebar.setLayout(null);

        var btnProfile = new JButton("Profile");
        setupButtonHoverEffect(btnProfile);
        btnProfile.setBounds(10, 108, 270, 42);
        sidebar.add(btnProfile);
		btnProfile.addActionListener(e -> new Owner_Information(userID).setVisible(true));
        mainPanel.add(sidebar);

        var lblCustomer = new JLabel("CUSTOMER DASHBOARD");
        lblCustomer.setForeground(Color.WHITE);
        lblCustomer.setHorizontalAlignment(SwingConstants.CENTER);
        lblCustomer.setBounds(0, 33, 290, 40);
        sidebar.add(lblCustomer);
        lblCustomer.setFont(new Font("Arial", Font.BOLD, 20));

        separator = new JSeparator();
        separator.setBounds(28, 80, 234, 2);
        sidebar.add(separator);

        btnContact = new JButton("Contact");
        btnContact.setForeground(new Color(255, 255, 255));
        btnContact.setFont(new Font("Arial", Font.BOLD, 17));
        setupButtonHoverEffect(btnContact);
        btnContact.setBounds(10, 170, 270, 42);
        btnContact.addActionListener(e -> new ContactPage().setVisible(true));

        sidebar.add(btnContact);

        btnNotification = new JButton("Notification");
        btnNotification.setFont(new Font("Arial", Font.BOLD, 17));
        btnNotification.setForeground(new Color(255, 255, 255));
        setupButtonHoverEffect(btnNotification);
        btnNotification.setBounds(10, 237, 270, 42);
        sidebar.add(btnNotification);
        btnNotification.addActionListener(e -> new NotificationPage().setVisible(true));


        btnFeedback = new JButton("Feedback");
        btnFeedback.setForeground(new Color(255, 255, 255));
        btnFeedback.setFont(new Font("Arial", Font.BOLD, 17));
        setupButtonHoverEffect(btnFeedback);
        btnFeedback.setBounds(10, 304, 270, 42);
        sidebar.add(btnFeedback);
        btnFeedback.addActionListener(e -> {
            new FeedbackPage().setVisible(true);
            dispose();
        });

        btnRules = new JButton("Rules");
        btnRules.setFont(new Font("Arial", Font.BOLD, 17));
        btnRules.setForeground(new Color(255, 255, 255));
        setupButtonHoverEffect(btnRules);
        btnRules.setBounds(10, 371, 270, 42);
        sidebar.add(btnRules);
        btnRules.addActionListener(e -> {
            new RulesPage().setVisible(true);
            dispose();
        });


        btnLogout = new JButton("Logout");
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.setFont(new Font("Arial", Font.BOLD, 17));
        setupButtonHoverEffect(btnLogout);
        btnLogout.setBounds(10, 448, 270, 42);
        btnLogout.addActionListener(e -> {
		    dispose(); // Đóng CustomerPage
		    new LoginFrame().setVisible(true); // Mở LoginFrame
		});

        sidebar.add(btnLogout);

        lblServices = new JLabel("SERVICES");
        lblServices.setHorizontalAlignment(SwingConstants.CENTER);
        lblServices.setForeground(Color.WHITE);
        lblServices.setFont(new Font("Arial", Font.BOLD, 20));
        lblServices.setBounds(0, 501, 290, 40);
        sidebar.add(lblServices);

        separator_1 = new JSeparator();
        separator_1.setBounds(28, 552, 234, 2);
        sidebar.add(separator_1);

        btnServices = new JButton("Services");
        btnServices.setForeground(new Color(255, 255, 255));
        btnServices.setFont(new Font("Arial", Font.BOLD, 17));
        setupButtonHoverEffect(btnServices);
        btnServices.setBounds(10, 597, 270, 42);
        sidebar.add(btnServices);

        btnPayment = new JButton("Payment");
        btnPayment.setFont(new Font("Arial", Font.BOLD, 17));
        btnPayment.setForeground(new Color(255, 255, 255));
        setupButtonHoverEffect(btnPayment);
        btnPayment.setBounds(10, 668, 270, 42);
        sidebar.add(btnPayment);

        btnRTS = new JButton("Register Temporary Stay");
        btnRTS.setForeground(new Color(255, 255, 255));
        btnRTS.setFont(new Font("Arial", Font.BOLD, 17));
        setupButtonHoverEffect(btnRTS);
        btnRTS.setBounds(10, 739, 270, 42);
        sidebar.add(btnRTS);

        getContentPane().add(mainPanel);
    }

    private void setupButtonHoverEffect(JButton button) {
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setBackground(new Color(0, 128, 128));
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        // Màu khi hover
        var hoverColor = new Color(96, 144, 144);
        // Màu gốc
        var defaultColor = button.getBackground();

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultColor);
            }
        });
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new CustomerPage().setVisible(true);
//        });
//    }

}
