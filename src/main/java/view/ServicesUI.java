package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServicesUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitle;
	private JPanel ListServices;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JPanel Service1;
	private JLabel lblRepair;
	private JSeparator separator_3;
	private JLabel lblNameService1;
	private JButton btnRegister1;
	private JPanel service1;
	private JLabel lblCleaningServices;
	private JSeparator separator_4;
	private JLabel lblApartmentCleaningServices;
	private JButton btnRegister1_1;
	private JPanel service1_1;
	private JLabel lblParkingServices;
	private JSeparator separator_5;
	private JLabel lblRegisterParkingSlot;
	private JButton btnRegister1_2;
	private JPanel service1_2;
	private JLabel lblSecurityServices;
	private JSeparator separator_6;
	private JLabel lblContactApartmentSecurity;
	private JButton btnRegister1_3;
	private JPanel service1_3;
	private JLabel lblLaundryServices;
	private JSeparator separator_7;
	private JLabel lblProfessionalLaundryAnd;
	private JButton btnRegister1_4;
	private JPanel service1_4;
	private JLabel lblMaintenanceServices;
	private JSeparator separator_8;
	private JLabel lblRoutineMaintenanceFor;
	private JButton btnRegister1_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServicesUI frame = new ServicesUI();
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
	public ServicesUI() {
		setTitle("Services");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("List of Services");
		lblTitle.setForeground(new Color(0, 128, 128));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitle.setBounds(10, 11, 1256, 56);
		contentPane.add(lblTitle);
		
		ListServices = new JPanel();
		ListServices.setBackground(new Color(0, 128, 128));
		ListServices.setBounds(10, 95, 1256, 683);
		contentPane.add(ListServices);
		ListServices.setLayout(null);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(833, 11, 5, 661);
		ListServices.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(410, 11, 5, 661);
		ListServices.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(10, 333, 1236, 2);
		ListServices.add(separator_2);
		
		Service1 = new JPanel();
		Service1.setBounds(10, 26, 390, 296);
		ListServices.add(Service1);
		Service1.setLayout(null);
		
		lblRepair = new JLabel("Repair Services");
		lblRepair.setBackground(new Color(255, 255, 255));
		lblRepair.setFont(new Font("Arial", Font.BOLD, 18));
		lblRepair.setForeground(new Color(0, 128, 128));
		lblRepair.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepair.setBounds(91, 26, 209, 29);
		Service1.add(lblRepair);
		
		separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(10, 66, 370, 2);
		Service1.add(separator_3);
		
		lblNameService1 = new JLabel("Technical device repair support");
		lblNameService1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNameService1.setForeground(new Color(0, 128, 128));
		lblNameService1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameService1.setBounds(68, 163, 258, 29);
		Service1.add(lblNameService1);
		
		btnRegister1 = new JButton("Register");
		btnRegister1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegister1ActionPerformed(e);
			}
		});
		btnRegister1.setBackground(new Color(192, 192, 192));
		btnRegister1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister1.setForeground(new Color(0, 128, 128));
		btnRegister1.setBounds(0, 267, 390, 29);
		Service1.add(btnRegister1);
		
		service1 = new JPanel();
		service1.setLayout(null);
		service1.setBounds(425, 26, 390, 296);
		ListServices.add(service1);
		
		lblCleaningServices = new JLabel("Cleaning Services");
		lblCleaningServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblCleaningServices.setForeground(new Color(0, 128, 128));
		lblCleaningServices.setFont(new Font("Arial", Font.BOLD, 18));
		lblCleaningServices.setBackground(Color.WHITE);
		lblCleaningServices.setBounds(91, 26, 209, 29);
		service1.add(lblCleaningServices);
		
		separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(10, 66, 370, 2);
		service1.add(separator_4);
		
		lblApartmentCleaningServices = new JLabel("Apartment cleaning services");
		lblApartmentCleaningServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblApartmentCleaningServices.setForeground(new Color(0, 128, 128));
		lblApartmentCleaningServices.setFont(new Font("Arial", Font.BOLD, 15));
		lblApartmentCleaningServices.setBounds(68, 163, 258, 29);
		service1.add(lblApartmentCleaningServices);
		
		btnRegister1_1 = new JButton("Register");
		btnRegister1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegister1_1ActionPerformed(e);
			}
		});
		btnRegister1_1.setForeground(new Color(0, 128, 128));
		btnRegister1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister1_1.setBackground(Color.LIGHT_GRAY);
		btnRegister1_1.setBounds(0, 267, 390, 29);
		service1.add(btnRegister1_1);
		
		service1_1 = new JPanel();
		service1_1.setLayout(null);
		service1_1.setBounds(848, 26, 390, 296);
		ListServices.add(service1_1);
		
		lblParkingServices = new JLabel("Parking Services");
		lblParkingServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblParkingServices.setForeground(new Color(0, 128, 128));
		lblParkingServices.setFont(new Font("Arial", Font.BOLD, 18));
		lblParkingServices.setBackground(Color.WHITE);
		lblParkingServices.setBounds(91, 26, 209, 29);
		service1_1.add(lblParkingServices);
		
		separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(10, 66, 370, 2);
		service1_1.add(separator_5);
		
		lblRegisterParkingSlot = new JLabel("Register parking slot");
		lblRegisterParkingSlot.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterParkingSlot.setForeground(new Color(0, 128, 128));
		lblRegisterParkingSlot.setFont(new Font("Arial", Font.BOLD, 15));
		lblRegisterParkingSlot.setBounds(68, 163, 258, 29);
		service1_1.add(lblRegisterParkingSlot);
		
		btnRegister1_2 = new JButton("Register");
		btnRegister1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegister1_2ActionPerformed(e);
			}
		});
		btnRegister1_2.setForeground(new Color(0, 128, 128));
		btnRegister1_2.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister1_2.setBackground(Color.LIGHT_GRAY);
		btnRegister1_2.setBounds(0, 267, 390, 29);
		service1_1.add(btnRegister1_2);
		
		service1_2 = new JPanel();
		service1_2.setLayout(null);
		service1_2.setBounds(10, 363, 390, 296);
		ListServices.add(service1_2);
		
		lblSecurityServices = new JLabel("Security Services");
		lblSecurityServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecurityServices.setForeground(new Color(0, 128, 128));
		lblSecurityServices.setFont(new Font("Arial", Font.BOLD, 18));
		lblSecurityServices.setBackground(Color.WHITE);
		lblSecurityServices.setBounds(91, 26, 209, 29);
		service1_2.add(lblSecurityServices);
		
		separator_6 = new JSeparator();
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(10, 66, 370, 2);
		service1_2.add(separator_6);
		
		lblContactApartmentSecurity = new JLabel("Contact apartment security");
		lblContactApartmentSecurity.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactApartmentSecurity.setForeground(new Color(0, 128, 128));
		lblContactApartmentSecurity.setFont(new Font("Arial", Font.BOLD, 15));
		lblContactApartmentSecurity.setBounds(68, 163, 258, 29);
		service1_2.add(lblContactApartmentSecurity);
		
		btnRegister1_3 = new JButton("Register");
		btnRegister1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegister1_3ActionPerformed(e);
			}
		});
		btnRegister1_3.setForeground(new Color(0, 128, 128));
		btnRegister1_3.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister1_3.setBackground(Color.LIGHT_GRAY);
		btnRegister1_3.setBounds(0, 267, 390, 29);
		service1_2.add(btnRegister1_3);
		
		service1_3 = new JPanel();
		service1_3.setLayout(null);
		service1_3.setBounds(425, 363, 390, 296);
		ListServices.add(service1_3);
		
		lblLaundryServices = new JLabel("Laundry Services");
		lblLaundryServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaundryServices.setForeground(new Color(0, 128, 128));
		lblLaundryServices.setFont(new Font("Arial", Font.BOLD, 18));
		lblLaundryServices.setBackground(Color.WHITE);
		lblLaundryServices.setBounds(91, 26, 209, 29);
		service1_3.add(lblLaundryServices);
		
		separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setBounds(10, 66, 370, 2);
		service1_3.add(separator_7);
		
		lblProfessionalLaundryAnd = new JLabel("Professional laundry and dry cleaning");
		lblProfessionalLaundryAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessionalLaundryAnd.setForeground(new Color(0, 128, 128));
		lblProfessionalLaundryAnd.setFont(new Font("Arial", Font.BOLD, 15));
		lblProfessionalLaundryAnd.setBounds(61, 163, 271, 29);
		service1_3.add(lblProfessionalLaundryAnd);
		
		btnRegister1_4 = new JButton("Register");
		btnRegister1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegister1_4ActionPerformed(e);
			}
		});
		btnRegister1_4.setForeground(new Color(0, 128, 128));
		btnRegister1_4.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister1_4.setBackground(Color.LIGHT_GRAY);
		btnRegister1_4.setBounds(0, 267, 390, 29);
		service1_3.add(btnRegister1_4);
		
		service1_4 = new JPanel();
		service1_4.setLayout(null);
		service1_4.setBounds(848, 363, 390, 296);
		ListServices.add(service1_4);
		
		lblMaintenanceServices = new JLabel("Maintenance Services");
		lblMaintenanceServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaintenanceServices.setForeground(new Color(0, 128, 128));
		lblMaintenanceServices.setFont(new Font("Arial", Font.BOLD, 18));
		lblMaintenanceServices.setBackground(Color.WHITE);
		lblMaintenanceServices.setBounds(91, 26, 209, 29);
		service1_4.add(lblMaintenanceServices);
		
		separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(10, 66, 370, 2);
		service1_4.add(separator_8);
		
		lblRoutineMaintenanceFor = new JLabel("Routine maintenance for apartment facilities");
		lblRoutineMaintenanceFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoutineMaintenanceFor.setForeground(new Color(0, 128, 128));
		lblRoutineMaintenanceFor.setFont(new Font("Arial", Font.BOLD, 15));
		lblRoutineMaintenanceFor.setBounds(37, 163, 318, 29);
		service1_4.add(lblRoutineMaintenanceFor);
		
		btnRegister1_5 = new JButton("Register");
		btnRegister1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegister1_5ActionPerformed(e);
			}
		});
		btnRegister1_5.setForeground(new Color(0, 128, 128));
		btnRegister1_5.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister1_5.setBackground(Color.LIGHT_GRAY);
		btnRegister1_5.setBounds(0, 267, 390, 29);
		service1_4.add(btnRegister1_5);
	}
	protected void btnRegister1ActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "You have selected: Repair Services");

	}
	
	protected void btnRegister1_1ActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "You have selected: Cleaning Services");
	}
	
	protected void btnRegister1_2ActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "You have selected: Parking Services");
	}
	
	protected void btnRegister1_3ActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "You have selected: Security Services");
	}
	
	protected void btnRegister1_4ActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "You have selected: Laundry Services");
	}
	
	protected void btnRegister1_5ActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "You have selected: Maintenance Services");

	}
}
