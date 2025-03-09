package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ContactPage extends JFrame {
	public ContactPage(int userID) {
        setTitle("Contact Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        setLocationRelativeTo(null);

        // Panel nền
        var backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(new Color(0, 128, 128));
        getContentPane().add(backgroundPanel);

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Tiêu đề
        var lblTitle = new JLabel("Contact Information", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        backgroundPanel.add(lblTitle, gbc);

        // Panel chứa thông tin liên hệ
        var contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setBackground(Color.WHITE);
        contactPanel.setPreferredSize(new Dimension(600, 200));
        contactPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm thông tin liên hệ
        contactPanel.add(createContactLabel("Phone: 123-456-789"));
        contactPanel.add(createContactLabel("Email: support@rental.com"));
        contactPanel.add(createContactLabel("Social: @rental_official"));

        gbc.gridy = 1;
        backgroundPanel.add(contactPanel, gbc);

        // Nút quay lại
        var btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(0, 100, 100));
        btnBack.setPreferredSize(new Dimension(150, 40));
        btnBack.setFocusPainted(false);

        // Hover effect
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBack.setBackground(new Color(0, 150, 150));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBack.setBackground(new Color(0, 100, 100));
            }
        });

		btnBack.addActionListener(e -> {
			new User(userID).setVisible(true); // Mở lại trang User
			dispose(); // Đóng trang hiện tại
		});

        gbc.gridy = 2;
        backgroundPanel.add(btnBack, gbc);
    }

    private JLabel createContactLabel(String text) {
        var label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(new Color(0, 128, 128));
        return label;
    }

}
