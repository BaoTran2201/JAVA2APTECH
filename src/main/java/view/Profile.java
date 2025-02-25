package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import javax.swing.JTextField;

public class Profile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblFullName;
	private JLabel lblAVT;
	private JTextField textFullName;
	private JLabel lblFullName_1;
	private JLabel lblFullName_2;
	private JLabel lblFullName_3;
	private JLabel lblFullName_4;
	private JLabel lblFullName_5;
	private JLabel lblFullName_6;
	private JLabel lblFullName_7;
	private JLabel lblFullName_8;
	private JLabel lblFullName_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
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
	public Profile() {
		setTitle("Profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("PROFILE");
		lblNewLabel.setBounds(5, 5, 1276, 65);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		lblFullName = new JLabel("Full Name");
		lblFullName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName.setBounds(373, 341, 138, 30);
		lblFullName.setForeground(new Color(255, 255, 255));
		lblFullName.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblFullName);
		
		lblAVT = new JLabel("");
		lblAVT.setBackground(new Color(255, 255, 255));
		lblAVT.setBounds(556, 90, 189, 189);
		contentPane.add(lblAVT);
		
		textFullName = new JTextField();
		textFullName.setBounds(668, 341, 288, 30);
		contentPane.add(textFullName);
		textFullName.setColumns(10);
		
		lblFullName_1 = new JLabel("Full Name");
		lblFullName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_1.setForeground(Color.WHITE);
		lblFullName_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_1.setBounds(373, 396, 138, 30);
		contentPane.add(lblFullName_1);
		
		lblFullName_2 = new JLabel("Full Name");
		lblFullName_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_2.setForeground(Color.WHITE);
		lblFullName_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_2.setBounds(373, 452, 138, 30);
		contentPane.add(lblFullName_2);
		
		lblFullName_3 = new JLabel("Full Name");
		lblFullName_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_3.setForeground(Color.WHITE);
		lblFullName_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_3.setBounds(373, 503, 138, 30);
		contentPane.add(lblFullName_3);
		
		lblFullName_4 = new JLabel("Full Name");
		lblFullName_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_4.setForeground(Color.WHITE);
		lblFullName_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_4.setBounds(373, 557, 138, 30);
		contentPane.add(lblFullName_4);
		
		lblFullName_5 = new JLabel("Full Name");
		lblFullName_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_5.setForeground(Color.WHITE);
		lblFullName_5.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_5.setBounds(373, 608, 138, 30);
		contentPane.add(lblFullName_5);
		
		lblFullName_6 = new JLabel("Full Name");
		lblFullName_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_6.setForeground(Color.WHITE);
		lblFullName_6.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_6.setBounds(373, 659, 138, 30);
		contentPane.add(lblFullName_6);
		
		lblFullName_7 = new JLabel("Full Name");
		lblFullName_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_7.setForeground(Color.WHITE);
		lblFullName_7.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_7.setBounds(373, 714, 138, 30);
		contentPane.add(lblFullName_7);
		
		lblFullName_8 = new JLabel("Full Name");
		lblFullName_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_8.setForeground(Color.WHITE);
		lblFullName_8.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_8.setBounds(373, 755, 138, 30);
		contentPane.add(lblFullName_8);
		
		lblFullName_9 = new JLabel("Full Name");
		lblFullName_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullName_9.setForeground(Color.WHITE);
		lblFullName_9.setFont(new Font("Arial", Font.BOLD, 20));
		lblFullName_9.setBounds(373, 809, 138, 30);
		contentPane.add(lblFullName_9);
	}
}
