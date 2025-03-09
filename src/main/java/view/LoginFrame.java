package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Dao.LoginDAO;
import Dao.StaffLoginDAO;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogin;
	private JLabel lblUsername;
	private JTextField textUser;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel lblForgot;
	private JButton btnLoginButton;
	private JButton btnBack;
	private JLabel lblImage;
	private JLabel lblRegister;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new LoginFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public LoginFrame() {
		setTitle("Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Nút Back
//        btnBack = new JButton("◄ Back");
//        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
//        btnBack.setForeground(Color.WHITE);
//        btnBack.setBackground(new Color(64, 128, 128));
//        btnBack.setBorder(null);
//        btnBack.setFocusPainted(false);
//        btnBack.setContentAreaFilled(false);
//        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btnBack.setBounds(20, 20, 100, 30);
//        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//			public void mouseEntered(java.awt.event.MouseEvent evt) {
//                btnBack.setForeground(new Color(200, 200, 200));
//            }
//            @Override
//			public void mouseExited(java.awt.event.MouseEvent evt) {
//                btnBack.setForeground(Color.WHITE);
//            }
//        });
//        contentPane.add(btnBack);

		lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(new Color(64, 128, 128));
		lblLogin.setFont(new Font("Arial", Font.BOLD, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(518, 232, 209, 58);
		contentPane.add(lblLogin);

		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(64, 128, 128));
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsername.setBounds(443, 301, 115, 26);
		contentPane.add(lblUsername);

		textUser = new JTextField();
		textUser.setBounds(613, 301, 209, 26);
		contentPane.add(textUser);
		textUser.setColumns(10);

		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(64, 128, 128));
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(443, 370, 115, 26);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(613, 370, 209, 26);
		contentPane.add(passwordField);

		lblForgot = new JLabel("Forgot Password?");
		lblForgot.setForeground(new Color(0, 0, 255));
		lblForgot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblForgot.setBounds(622, 407, 105, 14);
		lblForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblForgot.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				new ForgotPasswordFrame().setVisible(true);
				dispose();
			}
		});
		contentPane.add(lblForgot);
		lblRegister = new JLabel("Register?");
		lblRegister.setForeground(new Color(0, 0, 255));
		lblRegister.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblRegister.setBounds(766, 407, 56, 14);
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				new RegisterFrame().setVisible(true);
				dispose();
			}
		});
		contentPane.add(lblRegister);
		btnLoginButton = new JButton("LOGIN");
		btnLoginButton.addActionListener(this::btnLoginButtonActionPerformed);
		btnLoginButton.setForeground(new Color(0, 0, 0));
		btnLoginButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnLoginButton.setBackground(new Color(64, 128, 128));
		btnLoginButton.setBounds(546, 496, 179, 36);
		contentPane.add(btnLoginButton);

		// Ảnh nền
		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("D:\\java\\code\\Apartment\\src\\main\\resources\\image\\apartment1.jpg"));
		lblImage.setBounds(0, 0, 1286, 850);
		contentPane.add(lblImage);
	}

	protected void btnLoginButtonActionPerformed(ActionEvent e) {
		var username = textUser.getText().trim();
		var password = new String(passwordField.getPassword()).trim();

		if (username.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please input again username password !", "ERROR_MESSAGE",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		var loginDao = new LoginDAO();
		var userAccount = loginDao.selectByUser(username);

		if (userAccount != null) {
			if (!userAccount.isLoginStatus()) {
				JOptionPane.showMessageDialog(this, "Acount is locked!", "WARNING_MESSAGE",
						JOptionPane.WARNING_MESSAGE);
			} else if (!userAccount.getPass().equals(password)) {
				JOptionPane.showMessageDialog(this, "Password fail!", "ERROR_MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Login Success!", "INFORMATION_MESSAGE",
						JOptionPane.INFORMATION_MESSAGE);
				var userID = userAccount.getMemberID();

				switch (userAccount.getJobRole()) {
				case "admin" -> new QuanLyChungCuGUI().setVisible(true);
				case "user" -> new User(userID).setVisible(true);
				}
				this.dispose();
			}
			return;
		}

		var staffDao = new StaffLoginDAO();
		var staffAccount = staffDao.getStaffByUsername(username);

		if (staffAccount != null) {
			if (!staffAccount.isLoginStatus()) {
				JOptionPane.showMessageDialog(this, "Acount is locked!", "WARNING_MESSAGE",
						JOptionPane.WARNING_MESSAGE);
			} else if (!staffAccount.getPassword().equals(password)) {
				JOptionPane.showMessageDialog(this, "Password fail!", "ERROR_MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Login Success!", "INFORMATION_MESSAGE",
						JOptionPane.INFORMATION_MESSAGE);
				new Staff_Only(staffAccount.getStaffID()).setVisible(true);
				this.dispose();
			}
			return;
		}

		JOptionPane.showMessageDialog(this, "Account does not have in the system!", "ERROR_MESSAGE",
				JOptionPane.ERROR_MESSAGE);
	}

}
