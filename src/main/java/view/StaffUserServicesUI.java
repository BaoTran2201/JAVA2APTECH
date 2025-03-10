package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.StaffDAO;

public class StaffUserServicesUI extends JFrame {
	private DefaultTableModel staffTableModel, userServiceTableModel;
	private JTable staffTable, userServiceTable;
	private JTextField txtStaffID; // Hiển thị ID nhân viên được chọn
	private JTextField txtUserServiceID;
	private DefaultTableModel staffServiceTableModel;
	private JTable staffServiceTable;
	private int selectedStaffServiceID;

	public StaffUserServicesUI() {
		setTitle("Staff & User Services Management");
		setBounds(100, 100, 1500, 800);
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
			new QuanLyChungCuGUI().setVisible(true);
			dispose();
		});

		var lblTitle = new JLabel("Staff & User Services Management", JLabel.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

		panelTitle.add(btnBack, BorderLayout.WEST);
		panelTitle.add(lblTitle, BorderLayout.CENTER);
		panelMain.add(panelTitle, BorderLayout.NORTH);

		// Staff Panel
		var panelStaff = new JPanel(new BorderLayout());
		panelStaff.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Staff List",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
		panelStaff.setBackground(new Color(64, 128, 128));

		String[] staffColumns = { "ID", "Staff Name", "Phone Number" };
		staffTableModel = new DefaultTableModel(null, staffColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		staffTable = new JTable(staffTableModel);
		panelStaff.add(new JScrollPane(staffTable), BorderLayout.CENTER);

		staffTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var selectedRow = staffTable.getSelectedRow();
				if (selectedRow != -1) {
					var staffID = staffTable.getValueAt(selectedRow, 0).toString();
					txtStaffID.setText(staffID);
				}
			}
		});

		// User Services Panel
		var panelUserServices = new JPanel(new BorderLayout());
		panelUserServices.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),
				"User Services", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
		panelUserServices.setBackground(new Color(64, 128, 128));

		String[] userServiceColumns = { "UserService ID", "Member ID", "Service ID", "Service Name ", "Start Date",
				"End Date",
				"Status" };
		userServiceTableModel = new DefaultTableModel(null, userServiceColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		userServiceTable = new JTable(userServiceTableModel);
		panelUserServices.add(new JScrollPane(userServiceTable), BorderLayout.CENTER);

		userServiceTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var selectedRow = userServiceTable.getSelectedRow();
				if (selectedRow != -1) {
					var userServiceID = userServiceTable.getValueAt(selectedRow, 0).toString();
					txtUserServiceID.setText(userServiceID);
				}
			}
		});

		// Staff Services
		var panelStaffServices = new JPanel(new BorderLayout());
		panelStaffServices.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),
				"Staff Services", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
		panelStaffServices.setBackground(new Color(64, 128, 128));

		String[] staffServiceColumns = { "StaffService ID", "Staff ID", "UserService ID", "Date", "status" };
		staffServiceTableModel = new DefaultTableModel(null, staffServiceColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		staffServiceTable = new JTable(staffServiceTableModel);
		staffServiceTable.getSelectionModel().addListSelectionListener(e -> {
			var selectedRow = staffServiceTable.getSelectedRow();
			if (selectedRow != -1) { // Kiểm tra có hàng nào được chọn không
				selectedStaffServiceID = Integer.parseInt(staffServiceTable.getValueAt(selectedRow, 0).toString()); // Lưu
																													// ID
				var staffID = staffServiceTable.getValueAt(selectedRow, 1).toString();
				var userServiceID = staffServiceTable.getValueAt(selectedRow, 2).toString();

				// Truyền giá trị xuống JTextField
				txtStaffID.setText(staffID);
				txtUserServiceID.setText(userServiceID);
			} else {
				selectedStaffServiceID = -1; // Không có hàng nào được chọn
			}
		});

		panelStaffServices.add(new JScrollPane(staffServiceTable), BorderLayout.CENTER);

		// Split Panes để chia tỉ lệ
		var splitPaneLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelStaff, panelUserServices);
		splitPaneLeft.setResizeWeight(0.2);

		var splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneLeft, panelStaffServices);
		splitPaneMain.setResizeWeight(0.8); // 80% cho User Services + Staff Services

		panelMain.add(splitPaneMain, BorderLayout.CENTER);

		// ====== Selected Info Panel ======
		var panelInfo = new JPanel();
		panelInfo.add(new JLabel("Selected Staff ID:"));
		txtStaffID = new JTextField(10);
		panelInfo.add(txtStaffID);

		panelInfo.add(new JLabel("Selected UserService ID:"));
		txtUserServiceID = new JTextField(10);
		panelInfo.add(txtUserServiceID);
		var btnAdd = new JButton("Add");
		btnAdd.addActionListener(this::btnAddActionPerformed);
		var btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this::btnUpdateActionPerformed);
		var btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this::btnDeleteActionPerformed);

		panelInfo.add(btnAdd);
		panelInfo.add(btnUpdate);
		panelInfo.add(btnDelete);
		panelMain.add(panelInfo, BorderLayout.SOUTH);

		// Load data
		loadStaffData();
		loadServicesForCustomer();
		loadStaffServiceData();
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		staffServiceTable.getSelectionModel().addListSelectionListener(e -> {
			var isSelected = staffServiceTable.getSelectedRow() != -1;
			btnUpdate.setEnabled(isSelected);
			btnDelete.setEnabled(isSelected);
			btnAdd.setEnabled(false);
			staffTable.clearSelection();
			userServiceTable.clearSelection();
			txtUserServiceID.setEditable(false);
		});
		staffTable.getSelectionModel().addListSelectionListener(e -> {
			staffServiceTable.clearSelection();
			btnAdd.setEnabled(true);
			txtUserServiceID.setEditable(true);
		});

		userServiceTable.getSelectionModel().addListSelectionListener(e -> {
			staffServiceTable.clearSelection();
			btnAdd.setEnabled(true);
			txtUserServiceID.setEditable(true);
		});

		getContentPane().add(panelMain);
		setVisible(true);
	}

	private void loadStaffData() {
		var staffDAO = new StaffDAO();
		var staffList = staffDAO.AllStaff();
		staffTableModel.setRowCount(0);
		staffList.forEach(staff -> {
			staffTableModel.addRow(new Object[] { staff.getStaffID(), staff.getStaffName(), staff.getPhone() });
		});
	}

	private void loadServicesForCustomer() {
		var dao = new StaffDAO();
		var userServicesList = dao.loadUserServices();

		userServiceTableModel.setRowCount(0);

		userServicesList.forEach(userService -> {
			userServiceTableModel.addRow(new Object[] { userService.getUserServiceID(), userService.getMemberID(),
					userService.getServiceID(), userService.getServiceName(), userService.getDayStart(),
					userService.getDayEnd(), userService.isStatusSer() ? "Paid" : "UnPaid" });
		});
	}


	protected void btnAddActionPerformed(ActionEvent e) {
		var staffID = txtStaffID.getText();
		var userServiceID = txtUserServiceID.getText();

		if (staffID.isEmpty() || userServiceID.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please select both Staff and User Service!", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		var dao = new StaffDAO();
		var success = dao.insertStaffService(Integer.parseInt(staffID), Integer.parseInt(userServiceID));

		if (success) {
			JOptionPane.showMessageDialog(this, "Added successfully!");
			loadStaffServiceData();
		} else {
			JOptionPane.showMessageDialog(this, "Failed to add staff service.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void btnUpdateActionPerformed(ActionEvent e) {
		var selectedRow = staffServiceTable.getSelectedRow();
		if (selectedRow != -1) {
			var staffServiceID = Integer.parseInt(staffServiceTable.getValueAt(selectedRow, 0).toString());
			var staffID = Integer.parseInt(txtStaffID.getText());
			var userServiceID = Integer.parseInt(txtUserServiceID.getText());

			var dao = new StaffDAO();
			var isComplete = dao.isStatusComplete(staffID); // Kiểm tra trạng thái
			System.out.println(isComplete);
			if (!isComplete) { // Nếu StatusDone = 0
				JOptionPane.showMessageDialog(null, "This service is already completed and cannot be updated!",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (dao.updateStaffService(staffServiceID, staffID, userServiceID)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				loadStaffServiceData(); // Reload lại bảng dữ liệu
			} else {
				JOptionPane.showMessageDialog(null, "Update Fail!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please choose 1 row to update!");
		}
	}

	private void loadStaffServiceData() {
		var dao = new StaffDAO();
		var staffServiceList = dao.getAllStaffServices();
		staffServiceTableModel.setRowCount(0);

		staffServiceList.forEach(staffService -> staffServiceTableModel.addRow(new Object[] {
				staffService.getStaffServiceID(), staffService.getStaffID(), staffService.getUserServiceID(),
				staffService.getAssignmentDate(), staffService.isStatusDone() ? "Delivered" : "Complete" }));
	}

	protected void btnDeleteActionPerformed(ActionEvent e) {
		if (selectedStaffServiceID != -1) {
			var staffServiceDao = new StaffDAO();

			// Kiểm tra trạng thái StatusDone của staffServiceID
			var isNotComplete = staffServiceDao.isNotCompleteByStaffServiceID(selectedStaffServiceID);

			if (!isNotComplete) { // Nếu StatusDone = 0 (đã hoàn thành) thì không cho xóa
				JOptionPane.showMessageDialog(null, "This service is already completed and cannot be deleted!",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}

			var confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?",
					"Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				if (staffServiceDao.deleteStaffService(selectedStaffServiceID)) {
					JOptionPane.showMessageDialog(null, "Successfully deleted!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					loadStaffServiceData();
					selectedStaffServiceID = -1;
				} else {
					JOptionPane.showMessageDialog(null, "Deletion failed! Please try again.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please select a row to delete!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
	}


	public static void main(String[] args) {
		new StaffUserServicesUI();
	}
}
