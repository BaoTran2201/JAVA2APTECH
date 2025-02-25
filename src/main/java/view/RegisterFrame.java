package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Dao.LoginDAO;
import model.Login;

public class RegisterFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
	private JTextField textEmail;
    private JTextField textUsername;
    private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
    private JButton btnRegister;
    private JButton btnBack;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
				var frame = new RegisterFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	public void resetForm() {
		textEmail.setText("");
		textUsername.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
	}
    public RegisterFrame() {
        setTitle("Register Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

		var lblRegister = new JLabel("REGISTER");
        lblRegister.setForeground(new Color(64, 128, 128));
        lblRegister.setFont(new Font("Arial", Font.BOLD, 24));
        lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegister.setBounds(150, 20, 200, 30);
        contentPane.add(lblRegister);

		// Email
		var lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
		lblEmail.setBounds(50, 70, 100, 20);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(180, 70, 250, 25);
		contentPane.add(textEmail);

		// Username
		var lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
		lblUsername.setBounds(50, 110, 100, 20);
        contentPane.add(lblUsername);

        textUsername = new JTextField();
		textUsername.setBounds(180, 110, 250, 25);
        contentPane.add(textUsername);
        textUsername.setColumns(10);

		// Password
		var lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblPassword.setBounds(50, 150, 100, 20);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
		passwordField.setBounds(180, 150, 250, 25);
        contentPane.add(passwordField);

		// Confirm Password
		var lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblConfirmPassword.setBounds(50, 190, 150, 20);
		contentPane.add(lblConfirmPassword);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(180, 190, 250, 25);
		contentPane.add(confirmPasswordField);

		// Register Button
        btnRegister = new JButton("Register");
		btnRegister.addActionListener(this::btnRegisterActionPerformed);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(new Color(64, 128, 128));
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegister.setBounds(180, 240, 120, 30);
		btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnRegister);

		// Back Button
        btnBack = new JButton("◄ Back");
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.DARK_GRAY);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setBounds(320, 240, 110, 30);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        contentPane.add(btnBack);
    }

	protected void btnRegisterActionPerformed(ActionEvent e) {
		var dao = new LoginDAO();
		var email = textEmail.getText().trim();
		var username = textUsername.getText().trim();
		var password = new String(passwordField.getPassword()).trim();
		var confirmPassword = new String(confirmPasswordField.getPassword()).trim();

		// Kiểm tra rỗng
		if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Kiểm tra email hợp lệ
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			JOptionPane.showMessageDialog(this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Kiểm tra mật khẩu xác nhận
		if (!password.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Kiểm tra xem tài khoản đã tồn tại chưa
		if (dao.selectByUser(username) != null) {
			JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Tạo user mới và lưu vào database
		var newUser = new Login(username, password, email, "user", true);
		dao.insert(newUser);

		// JOptionPane.showMessageDialog(this, "Registration successful!", "Success",
		// JOptionPane.INFORMATION_MESSAGE);

		// Reset form sau khi đăng ký thành công
		resetForm();
	}


}
