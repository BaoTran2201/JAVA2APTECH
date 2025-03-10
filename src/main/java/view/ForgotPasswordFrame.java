package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.util.Random;

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
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
public class ForgotPasswordFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textEmail;
    private JTextField textOTP;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton btnSendOTP;
    private JButton btnResetPassword;
    private JButton btnBack;

	// Biến cho OTP và thông tin email
	private String generatedOTP;
	private final String senderEmail = "lebao545@gmail.com";
	private final String senderPassword = "qfdc kpnj kyzc qfab";
	private JButton btncheck;
	private JLabel lblEnterUser;
	private JTextField textUser;
	private boolean isPasswordVisible = false;
	private JButton btnShowPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                var frame = new ForgotPasswordFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ForgotPasswordFrame() {
        setTitle("Forgot Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(0, 128, 128)); // Màu nền xanh giống trang rules
        setContentPane(contentPane);

        var lblTitle = new JLabel("Reset Your Password");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(400, 30, 500, 30);
        contentPane.add(lblTitle);

        var lblEmail = new JLabel("Enter your email:");
        lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(300, 148, 200, 25);
        contentPane.add(lblEmail);

        textEmail = new JTextField();
		textEmail.setBounds(500, 148, 250, 25);
        contentPane.add(textEmail);

        btnSendOTP = new JButton("Send OTP");
		btnSendOTP.addActionListener(this::generateOTPAction);
		btnSendOTP.setBounds(770, 148, 120, 30);
        contentPane.add(btnSendOTP);

        var lblOTP = new JLabel("Enter OTP:");
        lblOTP.setForeground(Color.WHITE);
		lblOTP.setBounds(300, 198, 200, 25);
        contentPane.add(lblOTP);

        textOTP = new JTextField();
		textOTP.setBounds(500, 198, 250, 25);
		textOTP.setEnabled(false);
        contentPane.add(textOTP);

        var lblNewPassword = new JLabel("New Password:");
        lblNewPassword.setForeground(Color.WHITE);
		lblNewPassword.setBounds(300, 296, 200, 25);
        contentPane.add(lblNewPassword);

        passwordField = new JPasswordField();
		passwordField.setBounds(500, 296, 250, 25);
		passwordField.setEnabled(false);
        contentPane.add(passwordField);
		btnShowPassword = new JButton(
				new ImageIcon("C:\\Users\\PC\\JAVA2APTECH\\src\\main\\resources\\image\\eye_closed.png"));
		btnShowPassword.setBounds(768, 296, 30, 25);
        btnShowPassword.setBorder(null);
        btnShowPassword.setContentAreaFilled(false);
        btnShowPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPane.add(btnShowPassword);

		var lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setBounds(300, 346, 200, 25);
		contentPane.add(lblConfirmPassword);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(500, 346, 250, 25);
		confirmPasswordField.setEnabled(false);
		contentPane.add(confirmPasswordField);

        btnShowPassword.addActionListener(e -> {
		    isPasswordVisible = !isPasswordVisible;
		    if (isPasswordVisible) {
				confirmPasswordField.setEchoChar((char) 0);
		        passwordField.setEchoChar((char) 0);
				btnShowPassword
						.setIcon(new ImageIcon("C:\\Users\\PC\\JAVA2APTECH\\src\\main\\resources\\image\\eye_open.png"));
		    } else {
				confirmPasswordField.setEchoChar('*');
		        passwordField.setEchoChar('*');
				btnShowPassword.setIcon(
						new ImageIcon("C:\\Users\\PC\\JAVA2APTECH\\src\\main\\resources\\image\\eye_closed.png"));
		    }
		});

        btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(this::resetPasswordAction);
		btnResetPassword.setBounds(500, 406, 150, 30);
		btnResetPassword.setEnabled(false);
        contentPane.add(btnResetPassword);

        btnBack = new JButton("◄ Back");
        btnBack.setBounds(20, 810, 150, 40);
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(64, 128, 128));
        btnBack.setBorder(null);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> {
            new LoginFrame().setVisible(true);
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
        contentPane.add(btnBack);

		btncheck = new JButton("Check");
		btncheck.addActionListener(this::btncheckActionPerformed);
		btncheck.setBounds(561, 244, 120, 30);
		btncheck.setEnabled(false);
		contentPane.add(btncheck);

		lblEnterUser = new JLabel("Enter User");
		lblEnterUser.setForeground(Color.WHITE);
		lblEnterUser.setBounds(300, 92, 200, 25);
		contentPane.add(lblEnterUser);

		textUser = new JTextField();
		textUser.setBounds(500, 92, 250, 25);
		contentPane.add(textUser);
    }

	protected void generateOTPAction(ActionEvent event) {
		var username = textUser.getText().trim();
		var email = textEmail.getText().trim();
		var dao = new LoginDAO();

		if (dao.validateUser(username, email)) {
			generatedOTP = String.valueOf(1000 + new Random().nextInt(9000));
			sendOTPEmail(email, generatedOTP);
		} else {
			JOptionPane.showMessageDialog(this, "Username not found or email mismatch! Please check again ",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sendOTPEmail(String recipientEmail, String otp) {
		var props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		var session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Your OTP Code");
			message.setText("Your OTP is: " + otp);
			Transport.send(message);
			JOptionPane.showMessageDialog(this, "OTP sent to your email!", "Success", JOptionPane.INFORMATION_MESSAGE);
			textOTP.setEnabled(true);
			btncheck.setEnabled(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to send OTP!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void resetPasswordAction(ActionEvent event) {
		var dao = new LoginDAO();

		var otp = textOTP.getText().trim();
		var newPassword = new String(passwordField.getPassword()).trim();
		var confirmPassword = new String(confirmPasswordField.getPassword()).trim();
		var username = textUser.getText().trim();

		if (!otp.equals(generatedOTP)) {
			JOptionPane.showMessageDialog(this, "Invalid OTP!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (newPassword.length() < 4) {
			JOptionPane.showMessageDialog(this, "Password must be at least 4 characters long!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!newPassword.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		dao.updatePassword(username, newPassword);

		JOptionPane.showMessageDialog(this, "Password successfully reset!", "Success", JOptionPane.INFORMATION_MESSAGE);
		new LoginFrame().setVisible(true);
		dispose();
	}



	protected void btncheckActionPerformed(ActionEvent e) {
		if (textOTP.getText().trim().equals(generatedOTP)) {
			passwordField.setEnabled(true);
			confirmPasswordField.setEnabled(true);
			btnResetPassword.setEnabled(true);
			JOptionPane.showMessageDialog(this, " You can reset your password now.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Invalid OTP!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
