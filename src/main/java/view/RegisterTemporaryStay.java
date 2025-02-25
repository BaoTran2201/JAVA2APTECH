package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class RegisterTemporaryStay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblRegisterTemporaryStay;
	private JPanel panel_1;
	private JLabel lblFullName;
	private JTextField textFullName;
	private JLabel lblIdNumberOr;
	private JLabel lblStayDurationfrom;
	private JLabel lblStayDurationto;
	private JLabel lblReasonForStay;
	private JTextField textIdNumberOr;
	private JTextField textStayDurationfrom;
	private JTextField textStayDurationto;
	private JTextField textReasonForStay;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterTemporaryStay frame = new RegisterTemporaryStay();
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
	public RegisterTemporaryStay() {
		setTitle("RegisterTemporaryStay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 1276, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblRegisterTemporaryStay = new JLabel("Register Temporary Stay");
		lblRegisterTemporaryStay.setForeground(new Color(255, 255, 255));
		lblRegisterTemporaryStay.setFont(new Font("Arial", Font.BOLD, 25));
		lblRegisterTemporaryStay.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterTemporaryStay.setBounds(496, 33, 315, 45);
		panel.add(lblRegisterTemporaryStay);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(234, 134, 841, 560);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblFullName = new JLabel("Full Name of Temporary Resident");
		lblFullName.setForeground(new Color(255, 255, 255));
		lblFullName.setFont(new Font("Arial", Font.BOLD, 18));
		lblFullName.setBounds(49, 72, 303, 36);
		panel_1.add(lblFullName);
		
		textFullName = new JTextField();
		textFullName.setBounds(456, 72, 326, 36);
		panel_1.add(textFullName);
		textFullName.setColumns(10);
		
		lblIdNumberOr = new JLabel("ID Number or Passport");
		lblIdNumberOr.setForeground(Color.WHITE);
		lblIdNumberOr.setFont(new Font("Arial", Font.BOLD, 18));
		lblIdNumberOr.setBounds(49, 157, 303, 36);
		panel_1.add(lblIdNumberOr);
		
		lblStayDurationfrom = new JLabel("Stay Duration (From Date)");
		lblStayDurationfrom.setForeground(Color.WHITE);
		lblStayDurationfrom.setFont(new Font("Arial", Font.BOLD, 18));
		lblStayDurationfrom.setBounds(49, 250, 303, 36);
		panel_1.add(lblStayDurationfrom);
		
		lblStayDurationto = new JLabel("Stay Duration (To Date)");
		lblStayDurationto.setForeground(Color.WHITE);
		lblStayDurationto.setFont(new Font("Arial", Font.BOLD, 18));
		lblStayDurationto.setBounds(49, 340, 303, 36);
		panel_1.add(lblStayDurationto);
		
		lblReasonForStay = new JLabel("Reason for Stay");
		lblReasonForStay.setForeground(Color.WHITE);
		lblReasonForStay.setFont(new Font("Arial", Font.BOLD, 18));
		lblReasonForStay.setBounds(49, 432, 303, 36);
		panel_1.add(lblReasonForStay);
		
		textIdNumberOr = new JTextField();
		textIdNumberOr.setColumns(10);
		textIdNumberOr.setBounds(456, 157, 326, 36);
		panel_1.add(textIdNumberOr);
		
		textStayDurationfrom = new JTextField();
		textStayDurationfrom.setColumns(10);
		textStayDurationfrom.setBounds(456, 250, 326, 36);
		panel_1.add(textStayDurationfrom);
		
		textStayDurationto = new JTextField();
		textStayDurationto.setColumns(10);
		textStayDurationto.setBounds(456, 340, 326, 36);
		panel_1.add(textStayDurationto);
		
		textReasonForStay = new JTextField();
		textReasonForStay.setColumns(10);
		textReasonForStay.setBounds(456, 434, 326, 36);
		panel_1.add(textReasonForStay);
		
		btnNewButton = new JButton("Submit Registration");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 128, 128));
		btnNewButton.setBounds(270, 519, 283, 30);
		panel_1.add(btnNewButton);
	}
}
