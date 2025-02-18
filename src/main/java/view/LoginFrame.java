package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblImage;
	private JLabel lblLogin;
	private JLabel lblUsername;
	private JTextField textUser;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JCheckBox chckbxremember;
	private JLabel lblForgot;
	private JButton btnLoginButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Login_Form\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(new Color(64, 128, 128));
		lblLogin.setFont(new Font("Arial", Font.BOLD, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(518, 232, 209, 58);
		contentPane.add(lblLogin);
		
		lblUsername = new JLabel("Username\r\n:");
		lblUsername.setForeground(new Color(64, 128, 128));
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsername.setBounds(443, 301, 115, 26);
		contentPane.add(lblUsername);
		
		textUser = new JTextField();
		textUser.setBackground(new Color(255, 255, 255));
		textUser.setForeground(new Color(0, 0, 0));
		textUser.setBounds(613, 301, 209, 26);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		lblPassword = new JLabel("Password: ");
		lblPassword.setForeground(new Color(64, 128, 128));
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(443, 370, 115, 26);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(613, 370, 209, 26);
		contentPane.add(passwordField);
		
		chckbxremember = new JCheckBox("Remember Password");
		chckbxremember.setForeground(new Color(255, 255, 255));
		chckbxremember.setFont(new Font("Arial", Font.BOLD, 12));
		chckbxremember.setBounds(457, 428, 161, 23);
		contentPane.add(chckbxremember);
		chckbxremember.setOpaque(false);
		chckbxremember.setBackground(new Color(0, 0, 0, 0)); // Trong suốt

		
		lblForgot = new JLabel("Forgot Password?");
		lblForgot.setForeground(new Color(0, 0, 255));
		lblForgot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblForgot.setBounds(717, 407, 105, 14);
		contentPane.add(lblForgot);
		
		btnLoginButton = new JButton("LOGIN");
		btnLoginButton.setForeground(new Color(255, 255, 255));
		btnLoginButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnLoginButton.setBackground(new Color(64, 128, 128));
		btnLoginButton.setBounds(546, 496, 179, 36);
		contentPane.add(btnLoginButton);
		
		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\apartment1.jpg"));
		lblImage.setBounds(0, 0, 1276, 850);
		contentPane.add(lblImage);
	}
}
