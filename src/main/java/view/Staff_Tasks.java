package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.StaffServiceByidDAO;

public class Staff_Tasks extends JFrame {
	// Other variables and methods...

	private JComboBox<String> cbStatus; // ComboBox for Status
	private DefaultTableModel staffServiceTableModel;
	private JTable staffServiceTable;
	private JTextField txtStaffServiceID;
	private int staffID;

	public Staff_Tasks(int staffID) {
		this.staffID = staffID;
		setTitle("Staff Services Assignment");
		setBounds(100, 100, 1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		var panelMain = new JPanel(new BorderLayout());

		// Title Panel
		var panelTitle = new JPanel(new BorderLayout());
		panelTitle.setBackground(new Color(64, 128, 128));

		var btnBack = new JButton("◄ Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setBorder(null);
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(e -> {
			new Staff_Only(staffID).setVisible(true); // Trở về giao diện trước
			dispose();
		});

		var lblTitle = new JLabel("Staff Services Assignment", JLabel.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

		panelTitle.add(btnBack, BorderLayout.WEST);
		panelTitle.add(lblTitle, BorderLayout.CENTER);
		panelMain.add(panelTitle, BorderLayout.NORTH);

		// Staff Services Table
		var panelStaffServices = new JPanel(new BorderLayout());
		panelStaffServices.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Assigned Services",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
		panelStaffServices.setBackground(new Color(64, 128, 128));

		String[] staffServiceColumns = { "ID", "Apartment Number", "Member Name", "Phone", "Service Name",
				"Building Name", "Status" };
		staffServiceTableModel = new DefaultTableModel(null, staffServiceColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		staffServiceTable = new JTable(staffServiceTableModel);
		panelStaffServices.add(new JScrollPane(staffServiceTable), BorderLayout.CENTER);
		loadData();
		panelMain.add(panelStaffServices, BorderLayout.CENTER);

		// Create text fields for displaying StaffServiceID and Status
		var panelTextFields = new JPanel();
		panelTextFields.setBackground(new Color(64, 128, 128));
		panelTextFields.setLayout(new BorderLayout());

		var panelInput = new JPanel();
		panelTextFields.add(panelInput, BorderLayout.CENTER);

		var lblStaffServiceID = new JLabel("Staff Service ID: ");
		lblStaffServiceID.setForeground(Color.black);
		panelInput.add(lblStaffServiceID);

		txtStaffServiceID = new JTextField(15);
		txtStaffServiceID.setEditable(false);
		panelInput.add(txtStaffServiceID);

		var lblStatus = new JLabel("Status: ");
		lblStatus.setForeground(Color.black);
		panelInput.add(lblStatus);
		cbStatus = new JComboBox<>(new String[] { "Wait", "Complete" });
		panelInput.add(cbStatus);

		// Create "Update" button
		var btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 16));
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(0, 128, 128));
		btnUpdate.setBorder(null);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(true);
		btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelInput.add(btnUpdate);

		btnUpdate.addActionListener(e -> {
			var staffServiceID = txtStaffServiceID.getText();
			var status = (String) cbStatus.getSelectedItem(); // Get selected status from JComboBox

			var statusInt = status.equals("Wait") ? 1 : 0;

			// Validate inputs
			if (staffServiceID.isEmpty() || status == null || status.isEmpty()) {
				System.out.println("Please select a valid service and enter status.");
				return;
			}
			var dao = new StaffServiceByidDAO();
			var success = dao.updateServiceStatus(Integer.parseInt(staffServiceID), statusInt);
			if (success) {
				loadData();
				System.out.println("Status updated successfully.");
			} else {
				System.out.println("Error updating status.");
			}
		});

		panelMain.add(panelTextFields, BorderLayout.SOUTH);

		getContentPane().add(panelMain);
		setVisible(true);

		staffServiceTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var row = staffServiceTable.getSelectedRow();
				if (row != -1) {
					var staffServiceID = staffServiceTable.getValueAt(row, 0);
					var status = staffServiceTable.getValueAt(row, 6);

					txtStaffServiceID.setText(staffServiceID.toString());
					cbStatus.setSelectedItem(status.toString());
				}
			}
		});
	}

	private void loadData() {
		var dao = new StaffServiceByidDAO();
		var staffServices = dao.getStaffServicesByStaffID(staffID);
		staffServiceTableModel.setRowCount(0);
		staffServices.forEach(staffService -> {
			Object[] row = { staffService.getStaffServiceID(), staffService.getApartmentNumber(),
					staffService.getMemberName(), staffService.getPhone(), staffService.getServiceName(),
					staffService.getBuildingName(), staffService.isStatus() ? "Wait" : "Complete" };
			staffServiceTableModel.addRow(row);
		});
	}

	public static void main(String[] args) {
		new Staff_Tasks(1).setVisible(true);
	}
}
