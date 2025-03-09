package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Staff_Only extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblWelcome;
	private JSeparator separator_1;
	private JButton btnFunction;
	private JButton btnFunction_1;
	private JButton btnFunction_2;
	public Staff_Only(int staffID) {
		setTitle("Staff Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		var menuPanel = new JPanel();
		menuPanel.setBackground(new Color(64, 128, 128));
		menuPanel.setBounds(10, 11, 290, 811);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

				// Nút xem công việc
				var btnTasks = new JButton("Tasks");
				btnTasks.setFocusPainted(false);
				btnTasks.setFont(new Font("Arial", Font.BOLD, 20));
				btnTasks.setForeground(Color.WHITE);
				btnTasks.setBackground(new Color(64, 128, 128));
				btnTasks.setBorder(null);
				btnTasks.setBounds(10, 110, 270, 117);
				btnTasks.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnTasks.addActionListener(e -> {
					var tasksFrame = new Staff_Tasks(staffID);
					tasksFrame.setVisible(true);
				});


				// Hover effect cho nút Tasks
				btnTasks.addMouseListener(new java.awt.event.MouseAdapter() {
				    @Override
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				        btnTasks.setBackground(new Color(84, 148, 148)); // Màu sáng hơn khi hover
				    }

				    @Override
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				        btnTasks.setBackground(new Color(64, 128, 128)); // Quay về màu gốc
				    }
				});

						separator_1 = new JSeparator();
						separator_1.setBounds(20, 735, 250, 12);
						menuPanel.add(separator_1);

						menuPanel.add(btnTasks);

		var lblTitle = new JLabel("Staff Panel");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
		lblTitle.setBounds(10, 26, 270, 53);
		menuPanel.add(lblTitle);

		var separator = new JSeparator();
		separator.setBounds(20, 90, 250, 12);
		menuPanel.add(separator);

		// Nút Logout
		// Nút Logout
		var icon = new ImageIcon("logout_icon.png"); // Đảm bảo có file ảnh này
		var btnLogout = new JButton("Logout", new ImageIcon("C:\\java\\Apartment (6)\\Apartment\\src\\main\\resources\\image\\logout_icon.png"));
		btnLogout.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(new Color(64, 128, 128)); // Màu xanh chuẩn
		btnLogout.setBorder(null);
		btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogout.setBounds(150, 760, 130, 40); // Góc dưới bên phải

		btnLogout.addActionListener(e -> {
		    new LoginFrame().setVisible(true); // Quay lại trang Login
		    dispose(); // Đóng cửa sổ hiện tại
		});

		// Hiệu ứng hover
		btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnLogout.setBackground(new Color(84, 148, 148)); // Màu sáng hơn khi hover
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnLogout.setBackground(new Color(64, 128, 128)); // Trở lại màu ban đầu
		    }
		});

		menuPanel.add(btnLogout); // Thêm vào menuPanel

		btnFunction = new JButton("Function");
		btnFunction.setForeground(Color.WHITE);
		btnFunction.setFont(new Font("Arial", Font.BOLD, 20));
		btnFunction.setFocusPainted(false);
		btnFunction.setBorder(null);
		btnFunction.setBackground(new Color(64, 128, 128));
		btnFunction.setBounds(10, 263, 270, 117);
		btnFunction.setCursor(new Cursor(Cursor.HAND_CURSOR));



		// Hover effect cho nút Function
		btnFunction.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnFunction.setBackground(new Color(84, 148, 148)); // Màu sáng hơn khi hover
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnFunction.setBackground(new Color(64, 128, 128)); // Quay về màu gốc
		    }
		});
		menuPanel.add(btnFunction);

		btnFunction_1 = new JButton("Function1");
		btnFunction_1.setForeground(Color.WHITE);
		btnFunction_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnFunction_1.setFocusPainted(false);
		btnFunction_1.setBorder(null);
		btnFunction_1.setBackground(new Color(64, 128, 128));
		btnFunction_1.setBounds(10, 418, 270, 117);
		btnFunction_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Hover effect cho nút Function1
		btnFunction_1.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnFunction_1.setBackground(new Color(84, 148, 148)); // Màu sáng hơn khi hover
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnFunction_1.setBackground(new Color(64, 128, 128)); // Quay về màu gốc
		    }
		});
		menuPanel.add(btnFunction_1);

		btnFunction_2 = new JButton("Function2");
		btnFunction_2.setForeground(Color.WHITE);
		btnFunction_2.setFont(new Font("Arial", Font.BOLD, 20));
		btnFunction_2.setFocusPainted(false);
		btnFunction_2.setBorder(null);
		btnFunction_2.setBackground(new Color(64, 128, 128));
		btnFunction_2.setBounds(10, 579, 270, 117);
		btnFunction_2.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Hover effect cho nút Function2
		btnFunction_2.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnFunction_2.setBackground(new Color(84, 148, 148)); // Màu sáng hơn khi hover
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
				btnFunction_2.setBackground(new Color(64, 128, 128)); // Quay về màu gốc
		    }
		});
		menuPanel.add(btnFunction_2);


		// Khu vực hiển thị thông tin chính
		var mainPanel = new JPanel();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setBounds(310, 11, 950, 811);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		lblWelcome = new JLabel("Welcome, Staff ID: " + staffID);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
		lblWelcome.setBounds(50, 50, 400, 30);
		mainPanel.add(lblWelcome);

		var lblNote = new JLabel("Your tasks and notifications will appear here.");
		lblNote.setFont(new Font("Arial", Font.ITALIC, 16));
		lblNote.setBounds(50, 100, 400, 30);
		mainPanel.add(lblNote);
	}

	// Mở trang quản lý công việc của nhân viên
	private void openTaskPage() {
		JOptionPane.showMessageDialog(this, "Opening Task Management (Feature to be added)", "Info",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// Đăng xuất về màn hình đăng nhập
	private void logout() {
		var confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout Confirmation",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			new LoginFrame().setVisible(true);
			dispose();
		}
	}

}
