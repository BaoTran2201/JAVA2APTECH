package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Dao.AdminNoticeDAO;
import model.Notification;

public class NotificationPage extends JFrame {
	private JPanel notificationPanel;
	private JScrollPane scrollPane;

	public NotificationPage() {
		setTitle("Notifications");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		setLocationRelativeTo(null);

		var mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(240, 240, 240));

		var lblTitle = new JLabel("Notifications", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
		lblTitle.setForeground(new Color(0, 128, 128));
		mainPanel.add(lblTitle, BorderLayout.NORTH);

		// Panel chứa thông báo
		notificationPanel = new JPanel();
		notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
		notificationPanel.setBackground(Color.WHITE);

		// Thanh cuộn
		scrollPane = new JScrollPane(notificationPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Nút điều khiển
		var btnBack = new JButton("Back");
		var btnRefresh = new JButton("Refresh");

		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(0, 100, 100));
		btnBack.setPreferredSize(new Dimension(150, 40));

		btnRefresh.setFont(new Font("Arial", Font.BOLD, 16));
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setBackground(new Color(0, 128, 128));
		btnRefresh.setPreferredSize(new Dimension(150, 40));

		// Hover effect cho nút
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBack.setBackground(new Color(0, 150, 150));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBack.setBackground(new Color(0, 100, 100));
			}
		});

		btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnRefresh.setBackground(new Color(0, 180, 180));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnRefresh.setBackground(new Color(0, 128, 128));
			}
		});

		btnBack.addActionListener(e -> dispose());
		btnRefresh.addActionListener(e -> loadNotifications());

		var bottomPanel = new JPanel();
		bottomPanel.add(btnRefresh);
		bottomPanel.add(btnBack);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);

		// Load thông báo từ database khi mở
		loadNotifications();
	}

	private void loadNotifications() {
		notificationPanel.removeAll(); // Xóa thông báo cũ
		var dao = new AdminNoticeDAO();
		var notifications = dao.getAllNotifications();

		if (notifications.isEmpty()) {
			notificationPanel.add(new JLabel("No notifications available!", SwingConstants.CENTER));
		} else {
			for (Notification notice : notifications) {
				notificationPanel.add(createNotificationItem(notice.getTitle(), notice.getContent(),
						notice.getSentDate().toString()));
			}
		}

		notificationPanel.revalidate();
		notificationPanel.repaint();
	}

	private JPanel createNotificationItem(String title, String content, String date) {
		var panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(Color.WHITE);

		var lblTitle = new JLabel("<html><b>" + title + "</b></html>");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));

		var lblContent = new JLabel("<html>" + content + "</html>");
		lblContent.setFont(new Font("Arial", Font.PLAIN, 16));

		var lblDate = new JLabel(date != null ? date : "Unknown date");
		lblDate.setFont(new Font("Arial", Font.ITALIC, 14));
		lblDate.setForeground(Color.GRAY);

		var textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(lblTitle);
		textPanel.add(lblContent);
		textPanel.add(lblDate);

		panel.add(textPanel, BorderLayout.CENTER);

		panel.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				panel.setBackground(new Color(220, 245, 255));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				panel.setBackground(Color.WHITE);
			}
		});

		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new NotificationPage().setVisible(true));
	}
}
