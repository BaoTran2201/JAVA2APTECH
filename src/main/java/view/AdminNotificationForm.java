package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Dao.AdminNoticeDAO;

public class AdminNotificationForm extends JFrame {

	private JTextField txtTitle;
	private JTextArea txtContent;
	private JComboBox<String> cbDay, cbMonth, cbYear;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var window = new AdminNotificationForm();
				window.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public AdminNotificationForm() {
		setTitle("Admin Notification Form");
		getContentPane().setBackground(new Color(64, 128, 128));
		setBounds(100, 100, 1292, 889);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		var btnBack = new JButton("◄ Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setBorder(null);
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.setBounds(10, 10, 100, 30);
		btnBack.addActionListener(e -> {
			new QuanLyChungCuGUI().setVisible(true);
			dispose();
		});
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
		getContentPane().add(btnBack);

		var lblHeader = new JLabel("Admin Notification Form", SwingConstants.CENTER);
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 25));
		lblHeader.setBounds(346, 11, 548, 30);
		getContentPane().add(lblHeader);

		var lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(327, 79, 80, 26);
		getContentPane().add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBackground(Color.WHITE);
		txtTitle.setBounds(564, 80, 412, 25);
		getContentPane().add(txtTitle);
		txtTitle.setColumns(10);

		var lblDate = new JLabel("Created Date:");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDate.setBounds(328, 142, 141, 20);
		getContentPane().add(lblDate);

		cbDay = new JComboBox<>();
		cbDay.setBackground(Color.WHITE);
		cbDay.setBounds(564, 137, 60, 25);
		getContentPane().add(cbDay);

		cbMonth = new JComboBox<>();
		cbMonth.setBackground(Color.WHITE);
		for (var i = 1; i <= 12; i++) {
			cbMonth.addItem(String.valueOf(i));
		}
		cbMonth.setBounds(673, 137, 60, 25);
		getContentPane().add(cbMonth);

		cbYear = new JComboBox<>();
		cbYear.setBackground(Color.WHITE);
		for (var i = 2025; i <= 2030 && i > 2024; i++) {
			cbYear.addItem(String.valueOf(i));
		}
		cbYear.setBounds(789, 137, 80, 25);
		getContentPane().add(cbYear);

		updateDays();

		ItemListener dateUpdater = e -> updateDays();
		cbMonth.addItemListener(dateUpdater);
		cbYear.addItemListener(dateUpdater);

		var lblContent = new JLabel("Content:");
		lblContent.setForeground(Color.WHITE);
		lblContent.setFont(new Font("Arial", Font.PLAIN, 20));
		lblContent.setBounds(327, 203, 107, 20);
		getContentPane().add(lblContent);

		var btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.addActionListener(e -> {
			var title = txtTitle.getText();
			var content = txtContent.getText();
			var day = (String) cbDay.getSelectedItem();
			var month = (String) cbMonth.getSelectedItem();
			var year = (String) cbYear.getSelectedItem();

			var date = year + "-" + month + "-" + day;

			if (title.isEmpty() || content.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please input full your information!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Information to:\n" + title + "\nDateo: " + date + "\n" + content,
						"Succees", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSubmit.setBounds(714, 445, 100, 30);
		getContentPane().add(btnSubmit);
		btnSubmit.addActionListener(e -> {
			var title = txtTitle.getText();
			var content = txtContent.getText();

			if (title.isEmpty() || content.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please input full your information!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				var dao = new AdminNoticeDAO();
				dao.addNotification(title, content);
				JOptionPane.showMessageDialog(this, "Save Success!", "Save Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		txtContent = new JTextArea();
		txtContent.setBackground(Color.WHITE);
		txtContent.setBounds(564, 204, 412, 214);
		getContentPane().add(txtContent);
	}

	private void updateDays() {
		var selectedMonth = cbMonth.getSelectedIndex() + 1;
		var selectedYear = Integer.parseInt((String) cbYear.getSelectedItem());

		var daysInMonth = switch (selectedMonth) {
		case 4, 6, 9, 11 -> 30;
		case 2 -> (isLeapYear(selectedYear) ? 29 : 28);
		default -> 31;
		};

		cbDay.removeAllItems();
		for (var i = 1; i <= daysInMonth; i++) {
			cbDay.addItem(String.valueOf(i));
		}
	}

	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
}