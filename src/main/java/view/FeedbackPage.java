package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FeedbackPage extends JFrame {
    public FeedbackPage() {
        setTitle("Feedback Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        setLocationRelativeTo(null);

        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        var lblTitle = new JLabel("Customer Feedback", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(0, 128, 128));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        var lblName = new JLabel("Name:");
        lblName.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblName, gbc);

        var txtName = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtName, gbc);

        var lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblEmail, gbc);

        var txtEmail = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        var lblType = new JLabel("Feedback Type:");
        lblType.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblType, gbc);

        String[] feedbackTypes = {"Suggestion", "Complaint", "Inquiry"};
        var cbFeedbackType = new JComboBox<String>(feedbackTypes);
        gbc.gridx = 1;
        panel.add(cbFeedbackType, gbc);

        var lblMessage = new JLabel("Your Message:");
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblMessage, gbc);

        var txtMessage = new JTextArea(5, 20);
        var scrollPane = new JScrollPane(txtMessage);
        gbc.gridx = 1;
        panel.add(scrollPane, gbc);

        var btnSubmit = new JButton("Submit Feedback");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(new Color(0, 128, 128));
        btnSubmit.setFocusPainted(false);
        
        // Hover effect
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubmit.setBackground(new Color(0, 150, 150));
            }
            @Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubmit.setBackground(new Color(0, 128, 128));
            }
        });
        
        btnSubmit.addActionListener(e -> JOptionPane.showMessageDialog(null, "Thank you for your feedback!", "Feedback Received", JOptionPane.INFORMATION_MESSAGE));
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(btnSubmit, gbc);
        
        // Back Button
        var btnBack = new JButton("◄ Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(0, 100, 100));
        btnBack.setFocusPainted(false);
        btnBack.setBorder(null);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(new Color(200, 200, 200));
            }
            @Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(Color.WHITE);
            }
        });
        
        btnBack.addActionListener(e -> {
            new CustomerPage().setVisible(true);
            dispose();
        });
        
        gbc.gridy = 6;
        panel.add(btnBack, gbc);
        
        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackPage().setVisible(true));
    }
}
