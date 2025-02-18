package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class HomeScreen extends JFrame {
	public HomeScreen() {
		setTitle("Quản lý thuê chung cư");
		setBounds(100, 100, 1292, 889);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main Panel với layout BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout()) {
			private Image backgroundImage;

			{
				try {
					// Load the image từ URL
					URL imgURL = getClass().getResource("/image/ql.jpg");
					if (imgURL != null) {
						backgroundImage = new ImageIcon(imgURL).getImage();
					} else {
						System.out.println("Image not found");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backgroundImage != null) {
					// Scale và vẽ ảnh nền
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		// Welcome Text
		JLabel welcomeLabel = new JLabel("<html><h1>Chào mừng đến với hệ thống quản lý!</h1></html>", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
		welcomeLabel.setForeground(new Color(219, 206, 199));
		// Thêm khoảng cách padding cho dòng chữ
		welcomeLabel.setBorder(new EmptyBorder(100, 0, 0, 0)); // 50px cách từ trên xuống

		// Footer Panel
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(new Color(26, 45, 96));
		JLabel label = new JLabel("© 2025 Quản lý thuê chung cư. All rights reserved.");
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		footerPanel.add(label);

		// Add components
		mainPanel.add(welcomeLabel, BorderLayout.NORTH); // Đặt dòng chữ "Chào mừng..." lên trên với khoảng cách
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER); // Ảnh nền ở giữa
		getContentPane().add(footerPanel, BorderLayout.SOUTH); // Footer ở dưới cùng
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new HomeScreen().setVisible(true);
		});
	}
}