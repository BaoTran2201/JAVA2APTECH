package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.ServiesDAO;
import model.Service;

public class ServiceManagement extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtServiceName, txtServicePrice;
	private JTextArea txtDescription;
	private JComboBox<String> cbStatus;
    private JTable table;
    private DefaultTableModel tableModel;
	private JTextField txtExpirationDate;

    public ServiceManagement() {
		setTitle("Service Management");
		setBounds(100, 100, 1292, 889);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		initUI();
		loadServices();
	}

	private void initUI() {
		var panelTitle = new JPanel(new BorderLayout());
        panelTitle.setBackground(new Color(64, 128, 128));

		var btnBack = createBackButton();
		var lblTitle = new JLabel("Service Management", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(64, 128, 128));

		panelTitle.add(btnBack, BorderLayout.WEST);
        panelTitle.add(lblTitle, BorderLayout.CENTER);
		getContentPane().add(panelTitle, BorderLayout.NORTH);

		var panelInput = createInputPanel();
		getContentPane().add(panelInput, BorderLayout.WEST);

		createTable();

		var panelButtons = createButtonPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
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
			new QuanLyChungCuGUI().setVisible(true);
			dispose();
		});
		return btnBack;
	}

	private JPanel createInputPanel() {
		var panelInput = new JPanel(new GridLayout(5, 2, 10, 10));
		panelInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Service Information", TitledBorder.LEFT, TitledBorder.TOP));
		panelInput.setBackground(new Color(230, 230, 230));
		panelInput.setPreferredSize(new Dimension(350, 250));

		panelInput.add(new JLabel("Service Name:"));
		txtServiceName = new JTextField();
		panelInput.add(txtServiceName);

		panelInput.add(new JLabel("Description:"));
		txtDescription = new JTextArea(3, 20);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		var scrollPane = new JScrollPane(txtDescription);
		panelInput.add(scrollPane);

		panelInput.add(new JLabel("Price:"));
		txtServicePrice = new JTextField();
		panelInput.add(txtServicePrice);

		panelInput.add(new JLabel("Expiration Date:"));
		txtExpirationDate = new JTextField();
		panelInput.add(txtExpirationDate);

		panelInput.add(new JLabel("Status:"));
		cbStatus = new JComboBox<>(new String[] { "Active", "Stop" });
		panelInput.add(cbStatus);

		return panelInput;
	}

	private void createTable() {
		String[] columnNames = { "Name", "Description", "Price", "Expiration Date", "Status" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					txtServiceName.setText(tableModel.getValueAt(selectedRow, 0).toString());
					txtDescription.setText(tableModel.getValueAt(selectedRow, 1).toString());
					txtServicePrice.setText(tableModel.getValueAt(selectedRow, 2).toString());
					txtExpirationDate.setText(tableModel.getValueAt(selectedRow, 3).toString());
					cbStatus.setSelectedItem(tableModel.getValueAt(selectedRow, 4).toString());
				}
			}
		});
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
	}

	private JPanel createButtonPanel() {
		var panelButtons = new JPanel(new GridLayout(1, 3, 5, 5));
		var btnAdd = new JButton("ADD");
		btnAdd.addActionListener(this::btnAddActionPerformed);
		var btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(this::btnUpdateActionPerformed);
		var btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(this::btnRefreshActionPerformed);

		panelButtons.add(btnAdd);
		panelButtons.add(btnUpdate);
		panelButtons.add(btnRefresh);

		return panelButtons;
	}

	private void resetFields() {
		txtServiceName.setText("");
		txtDescription.setText("");
		txtServicePrice.setText("");
		txtExpirationDate.setText("");
		cbStatus.setSelectedIndex(0);
		table.clearSelection();
	}

	private void btnAddActionPerformed(ActionEvent e) {
		var dao = new ServiesDAO();
		var name = txtServiceName.getText().trim();
		var description = txtDescription.getText().trim();
		var priceText = txtServicePrice.getText().trim();
		var expirationDateText = txtExpirationDate.getText().trim();
		var status = cbStatus.getSelectedItem().equals("Active");

		if (name.isEmpty() || description.isEmpty() || priceText.isEmpty() || expirationDateText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter all information!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			var price = Double.parseDouble(priceText);
			var expirationDate = Integer.parseInt(expirationDateText);
			if (price < 0 || expirationDate <= 0) {
				throw new NumberFormatException();
			}
			var service = new Service(0, name, description, price, expirationDate, status);
			if (dao.addService(service)) {
				JOptionPane.showMessageDialog(this, "Service added successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				loadServices();
				resetFields();
			} else {
				JOptionPane.showMessageDialog(this, "Failed to add service!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Service price or expiration date is invalid!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadServices() {
		var dao = new ServiesDAO();
		tableModel.setRowCount(0);
		var services = dao.getAllServices();

		services.forEach(service -> tableModel.addRow(new Object[] { service.getServiceName(), service.getDescription(),
				service.getPrice(), service.getDurationDays(), service.isServiceStatus() ? "Active" : "Stop" }));
	}

	private int getSelectedServiceID(int selectedRow) {
		var dao = new ServiesDAO();
		var services = dao.getAllServices();
		return services.get(selectedRow).getServiceID();
	}

	private void btnUpdateActionPerformed(ActionEvent e) {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a service to update!", "Notification",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		var name = txtServiceName.getText().trim();
		var description = txtDescription.getText().trim();
		var priceText = txtServicePrice.getText().trim();
		var expirationDateText = txtExpirationDate.getText().trim();
		var status = "Active".equals(cbStatus.getSelectedItem().toString().trim());

		if (name.isEmpty() || description.isEmpty() || priceText.isEmpty() || expirationDateText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter all information!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		double price;
		int expirationDate;
		try {
			price = Double.parseDouble(priceText);
			expirationDate = Integer.parseInt(expirationDateText);
			if (price < 0 || expirationDate <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Service price or expiration date is invalid!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		var serviceID = getSelectedServiceID(selectedRow);
		var service = new Service(serviceID, name, description, price, expirationDate, status);
		var serviceDAO = new ServiesDAO();
		if (serviceDAO.updateService(service)) {
			JOptionPane.showMessageDialog(this, "Service updated successfully!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			loadServices();
			resetFields();
		} else {
			JOptionPane.showMessageDialog(this, "Failed to update service!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnRefreshActionPerformed(ActionEvent e) {
		resetFields();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ServiceManagement().setVisible(true));
	}
}
