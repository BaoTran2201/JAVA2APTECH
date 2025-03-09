package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dao.ApartmentDAO;
import model.Apartment;

public class RoomManagementPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new RoomManagementPage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public RoomManagementPage() {
		setTitle("Room Management Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Panel tiêu đề
		var panelTitle = new JPanel(new BorderLayout());
		panelTitle.setBackground(new Color(64, 128, 128));
		var lblTitle = new JLabel("Quản Lý Phòng", JLabel.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

		var btnBack = new JButton("◄ Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setBorder(null);
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(e -> {
			new QuanLyChungCuGUI().setVisible(true); // Thay đổi đối tượng ở đây nếu cần
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

		panelTitle.add(btnBack, BorderLayout.WEST);
		panelTitle.add(lblTitle, BorderLayout.CENTER);
		contentPane.add(panelTitle, BorderLayout.NORTH);

		// Bảng dữ liệu
		String[] columnNames = { "Room ID", "Building", "Floor", "Room Number", "Room Type", "Area", "Status" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

		// Panel nút chức năng
		var panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelBottom.setBackground(new Color(64, 128, 128));
		// nút thêm
		var btnThemPhong = new JButton("Add");
		btnThemPhong.addActionListener(e -> themPhong());

		panelBottom.add(btnThemPhong);
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		// nút sửa
		var btnSua = new JButton("Edit");
		btnSua.addActionListener(e -> suaPhong());

		panelBottom.add(btnSua);
		contentPane.add(panelBottom, BorderLayout.SOUTH);

		// nút xóa
		var btnXoa = new JButton("Delete");
		btnXoa.addActionListener(e -> xoaPhong());

		panelBottom.add(btnXoa);
		contentPane.add(panelBottom, BorderLayout.SOUTH);

		// Load dữ liệu
		loadDataToTable();
	}

	private void xoaPhong() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a room to delete!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 🏢 Lấy ApartmentID từ bảng
		var apartmentID = (int) tableModel.getValueAt(selectedRow, 0);

		// 🔴 Xác nhận trước khi chuyển vào thùng rác
		var confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to move this apartment to trash?",
				"Confirmation", JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			var dao = new ApartmentDAO();
			var success = dao.moveApartmentToTrash(apartmentID);

			if (success) {
				JOptionPane.showMessageDialog(this, "Apartment has been moved to trash!", "Notification",
						JOptionPane.INFORMATION_MESSAGE);
				loadDataToTable(); // Refresh table after updating status
			} else {
				JOptionPane.showMessageDialog(this, "Status update failed!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void suaPhong() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a room to edit!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		var apartmentID = (int) tableModel.getValueAt(selectedRow, 0);
		var buildingID = tableModel.getValueAt(selectedRow, 1).toString();
		var floorID = tableModel.getValueAt(selectedRow, 2).toString();
		var apartmentNumber = tableModel.getValueAt(selectedRow, 3).toString();
		var apartmentType = tableModel.getValueAt(selectedRow, 4).toString();
		var area = tableModel.getValueAt(selectedRow, 5).toString();
		var apartmentStatus = (int) tableModel.getValueAt(selectedRow, 6);

		var txtBuildingID = new JTextField(buildingID);
		var txtFloorID = new JTextField(floorID);
		var txtApartmentNumber = new JTextField(apartmentNumber);
		var txtApartmentType = new JTextField(apartmentType);
		var txtArea = new JTextField(area);

		String[] statusOptions = { "1 - Occupied", "2 - Vacant", "3 - Waiting for Contract", "4 - Under Maintenance",
				"5 - Waiting for Cleaning" };
		var cbStatus = new JComboBox<>(statusOptions);
		cbStatus.setSelectedIndex(apartmentStatus - 1);

		var panel = new JPanel(new GridLayout(6, 2, 10, 10));
		panel.add(new JLabel("Building:"));
		panel.add(txtBuildingID);
		panel.add(new JLabel("Floor:"));
		panel.add(txtFloorID);
		panel.add(new JLabel("Room Number:"));
		panel.add(txtApartmentNumber);
		panel.add(new JLabel("Room Type:"));
		panel.add(txtApartmentType);
		panel.add(new JLabel("Area (m²):"));
		panel.add(txtArea);
		panel.add(new JLabel("Status:"));
		panel.add(cbStatus);

		var result = JOptionPane.showConfirmDialog(null, panel, "Edit Room Information", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			try {
				var dao = new ApartmentDAO();

				var newBuildingID = Integer.parseInt(txtBuildingID.getText().trim());
				var newFloorID = Integer.parseInt(txtFloorID.getText().trim());
				var newApartmentNumber = txtApartmentNumber.getText().trim();
				var newApartmentType = txtApartmentType.getText().trim();
				var newArea = Double.parseDouble(txtArea.getText().trim());
				var newStatus = cbStatus.getSelectedIndex() + 1;

				var success = dao.updateApartment(new Apartment(apartmentID, newBuildingID, newFloorID,
						newApartmentNumber, newApartmentType, newArea, newStatus));

				if (success) {
					JOptionPane.showMessageDialog(null, "Room updated successfully!");
					loadDataToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid input data!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void loadDataToTable() {
		tableModel.setRowCount(0); // Xóa dữ liệu cũ
		var dao = new ApartmentDAO();
		for (Apartment a : dao.getAllApartments()) {
			tableModel.addRow(new Object[] { a.getApartmentID(), a.getBuildingID(), a.getFloorID(),
					a.getApartmentNumber(), a.getApartmentType(), a.getArea(), a.getApartmentsStatus() });
		}
	}

	private void themPhong() {
		var txtBuildingName = new JTextField();
		var txtFloorNumber = new JTextField();
		var txtApartmentNumber = new JTextField();
		var txtApartmentType = new JTextField();
		var txtArea = new JTextField();

		var panel = new JPanel(new GridLayout(5, 2)); // ⚠️ Giảm số lượng dòng vì bỏ combobox
		panel.add(new JLabel("Building Name:"));
		panel.add(txtBuildingName);
		panel.add(new JLabel("Floor Number:"));
		panel.add(txtFloorNumber);
		panel.add(new JLabel("Room Number:"));
		panel.add(txtApartmentNumber);
		panel.add(new JLabel("Room Type:"));
		panel.add(txtApartmentType);
		panel.add(new JLabel("Area (m²):"));
		panel.add(txtArea);

		var result = JOptionPane.showConfirmDialog(null, panel, "Add new room", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			try {
				var dao = new ApartmentDAO();

				var buildingID = dao.getBuildingID(txtBuildingName.getText().trim());
				if (buildingID == -1) {
					JOptionPane.showMessageDialog(null, "Not found building", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				var floorNumber = Integer.parseInt(txtFloorNumber.getText().trim());
				var floorID = dao.getFloorID(buildingID, floorNumber);
				if (floorID == -1) {
					floorID = dao.addNewFloor(buildingID, floorNumber);
					if (floorID == -1) {
						JOptionPane.showMessageDialog(null, "Failed to add floor!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				// ⚡ Mặc định trạng thái là 2 (Căn hộ trống)
				var newApartment = new Apartment(buildingID, floorID, txtApartmentNumber.getText().trim(),
						txtApartmentType.getText().trim(), Double.parseDouble(txtArea.getText().trim()), 2);

				if (dao.addApartment(newApartment)) {
					JOptionPane.showMessageDialog(null, "Succesful!");
					loadDataToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid Data!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
