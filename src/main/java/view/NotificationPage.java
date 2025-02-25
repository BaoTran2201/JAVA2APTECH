package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class NotificationPage extends JFrame {
    public NotificationPage() {
        setTitle("Notifications");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        JLabel lblTitle = new JLabel("Notifications", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(0, 128, 128));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Panel containing the list of notifications
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        notificationPanel.setBackground(Color.WHITE);

        // Sample notification list
        List<String> notifications = Arrays.asList(
            "Your lease agreement is about to expire.",
            "You have an outstanding bill for February.",
            "Warning: Rent payment is overdue!",
            "Upcoming event: Management service discount.",
            "Elevator maintenance scheduled for 20/02/2025."
        );

        for (String text : notifications) {
            notificationPanel.add(createNotificationItem(text));
        }

        JScrollPane scrollPane = new JScrollPane(notificationPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(0, 100, 100));
        btnBack.setPreferredSize(new Dimension(150, 40));
        btnBack.setFocusPainted(false);

        // Hover effect for back button
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack.setBackground(new Color(0, 150, 150));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack.setBackground(new Color(0, 100, 100));
            }
        });

        btnBack.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBack);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private JPanel createNotificationItem(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(label, BorderLayout.CENTER);

        // Hover effect
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(new Color(220, 245, 255)); // Light blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(Color.WHITE); // Revert to white when mouse exits
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotificationPage().setVisible(true));
    }
}
