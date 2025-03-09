package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Dao.ServiesDAO;
import Dao.UserServiceDAO;
import model.Service;

public class ServicesUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel listServicesPanel;
	private int userID;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new ServicesUI(2);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ServicesUI(int userID) {
		this.userID = userID;
		setTitle("Services");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1292, 600);
		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		var topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(0, 128, 128));

		var lblTitle = new JLabel("List of Services", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0, 128, 128));
		lblTitle.setPreferredSize(new Dimension(100, 50));

		var btnBack = createBackButton();
		topPanel.add(btnBack, BorderLayout.WEST);
		topPanel.add(lblTitle, BorderLayout.CENTER);

		listServicesPanel = new JPanel(new GridBagLayout());
		listServicesPanel.setBackground(new Color(230, 230, 250));

		var scrollPane = new JScrollPane(listServicesPanel);
		scrollPane.setBorder(null);

		var btnViewRegisteredServices = new JButton("View Registered Services");
		btnViewRegisteredServices.setFont(new Font("Arial", Font.BOLD, 16));
		btnViewRegisteredServices.setBackground(new Color(0, 128, 128));
		btnViewRegisteredServices.setForeground(Color.WHITE);
		btnViewRegisteredServices.setFocusPainted(false);
		btnViewRegisteredServices.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		btnViewRegisteredServices.addActionListener(e -> {
			new ServiceConfirmation(userID).setVisible(true);
		});

		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(btnViewRegisteredServices, BorderLayout.SOUTH);

		loadServices();
	}

	private JButton createBackButton() {
		var btnBack = new JButton("◄ Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setBorder(null);
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnBack.addActionListener(e -> {
			new User(userID).setVisible(true);
			dispose();
		});

		return btnBack;
	}

	private void loadServices() {
		var dao = new ServiesDAO();
		var services = dao.getActiveServices();

		listServicesPanel.removeAll();
		var gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		final int[] col = { 0 }, row = { 0 };

		services.forEach(service -> {
			gbc.gridx = col[0];
			gbc.gridy = row[0];

			listServicesPanel.add(createServicePanel(service), gbc);

			if (++col[0] == 3) {
				col[0] = 0;
				row[0]++;
			}
		});

		listServicesPanel.revalidate();
		listServicesPanel.repaint();
	}

	private JPanel createServicePanel(Service service) {
		var panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(250, 220));
		panel.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 128), 2));
		panel.setBackground(Color.WHITE);

		var lblTitle = new JLabel(service.getServiceName(), SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0, 128, 128));
		panel.add(lblTitle, BorderLayout.NORTH);

		var lblDescription = new JLabel("<html><center>" + service.getDescription() + "</center></html>");
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(lblDescription, BorderLayout.CENTER);

		var lblPrice = new JLabel("Price: $" + service.getPrice(), SwingConstants.CENTER);
		lblPrice.setFont(new Font("Arial", Font.BOLD, 14));
		lblPrice.setForeground(new Color(220, 20, 60));
		panel.add(lblPrice, BorderLayout.SOUTH);

		var btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(0, 128, 128));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegister.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));
		btnRegister.setFocusPainted(false);
		btnRegister.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		btnRegister.addActionListener(e -> {
			var dao = new UserServiceDAO();
			var success = dao.registerService(userID, service.getServiceID());

			if (success) {
				JOptionPane.showMessageDialog(null,
						"You have successfully registered for the service!\n" + "Service: " + service.getServiceName()
								+ "\n" + "Price: $" + service.getPrice(),
						"Registration Successful", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Service registration failed. Please try again.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});


		panel.add(btnRegister, BorderLayout.SOUTH);

		var popupMenu = new JPopupMenu();
		var menuItemInfo = new JMenuItem("View Information");
		menuItemInfo.addActionListener(e -> JOptionPane.showMessageDialog(panel,
				"Service Name: " + service.getServiceName() + "\n" + "Description: " + service.getDescription() + "\n"
						+ "Price: $" + service.getPrice() + "\n" + "Duration: " + service.getDurationDays() + " days",
				"Service Information", JOptionPane.INFORMATION_MESSAGE));

		popupMenu.add(menuItemInfo);
		panel.setComponentPopupMenu(popupMenu);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(new Color(224, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		return panel;
	}
}
