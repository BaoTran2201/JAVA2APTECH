package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

public class AdminNotificationForm extends JFrame {

	private JTextField txtTitle;
	private JTextArea txtContent;
	private JLabel lblCreatedby;
	private JCalendar calendar;
	private JTextField txtCreatedBy;

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
		setBounds(100, 100, 800, 600);
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
		getContentPane().add(btnBack);

		var lblHeader = new JLabel("Admin Notification Form", SwingConstants.CENTER);
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 25));
		lblHeader.setBounds(200, 10, 400, 30);
		getContentPane().add(lblHeader);

		var lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(50, 60, 100, 26);
		getContentPane().add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(180, 60, 500, 30);
		getContentPane().add(txtTitle);
		txtTitle.setColumns(10);

		var lblCreatedby = new JLabel("Created By");
		lblCreatedby.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCreatedby.setForeground(Color.WHITE);
		lblCreatedby.setBounds(50, 110, 100, 26);
		getContentPane().add(lblCreatedby);

		txtCreatedBy = new JTextField();
		txtCreatedBy.setColumns(10);
		txtCreatedBy.setBounds(180, 110, 500, 30);
		getContentPane().add(txtCreatedBy);

		var lblDate = new JLabel("Created Date");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(50, 160, 120, 26);
		getContentPane().add(lblDate);

		calendar = new JCalendar();
		calendar.setBounds(180, 160, 200, 150);
		getContentPane().add(calendar);

		var lblContent = new JLabel("Content");
		lblContent.setFont(new Font("Arial", Font.PLAIN, 18));
		lblContent.setForeground(Color.WHITE);
		lblContent.setBounds(50, 330, 100, 26);
		getContentPane().add(lblContent);

		txtContent = new JTextArea();
		var scrollPane = new JScrollPane(txtContent);
		scrollPane.setBounds(180, 330, 500, 150);
		getContentPane().add(scrollPane);

		var btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setBounds(350, 500, 100, 30);
		btnSubmit.addActionListener(e -> {
			var title = txtTitle.getText().trim();
			var content = txtContent.getText().trim();
			var createdBy = txtCreatedBy.getText().trim();
			var selectedDate = calendar.getDate();
			var sdf = new SimpleDateFormat("yyyy-MM-dd");
			var date = sdf.format(selectedDate);

			if (title.isEmpty() || content.isEmpty() || createdBy.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"Thông báo đã gửi:\n" + "Tiêu đề: " + title + "\n" + "Người tạo: " + createdBy + "\n"
								+ "Ngày tạo: " + date + "\n" + "Nội dung: " + content,
						"Thành công", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		getContentPane().add(btnSubmit);
	}
}
