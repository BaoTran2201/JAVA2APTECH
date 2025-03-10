package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.StaffDAO;
import model.Staff;

public class StaffManagement extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;

	public StaffManagement() {
		setTitle("Quản Lý Nhân Viên");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		// Tiêu đề
		var lblTitle = new JLabel("UserManagement", JLabel.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(64, 128, 128));
		lblTitle.setForeground(Color.WHITE);
		add(lblTitle, BorderLayout.NORTH);

		// Bảng nhân viên
		tableModel = new DefaultTableModel(new String[] { "ID", "Name", "Phone", "Status" }, 0);
		table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);

		// Nút chức năng
		var panelButtons = new JPanel(new FlowLayout());
		var btnThem = new JButton("ADD");
		var btnSua = new JButton("Update");
		var btnXoa = new JButton("Delete");

		btnThem.addActionListener(e -> themNhanVien());
		btnSua.addActionListener(e -> suaNhanVien());
		btnXoa.addActionListener(e -> xoaNhanVien());

		panelButtons.add(btnThem);
		panelButtons.add(btnSua);
		panelButtons.add(btnXoa);
		add(panelButtons, BorderLayout.SOUTH);

		loadData();
	}

	private void loadData() {
		tableModel.setRowCount(0);
		var staffs = new StaffDAO().getAllStaff();
		for (var s : staffs) {
			tableModel.addRow(new Object[] { s.getStaffID(), s.getStaffName(), s.getPhone(),
					s.isStaffStatus() ? "Ative" : "Stop" });
		}
	}

	private void themNhanVien() {
		var name = JOptionPane.showInputDialog(this, "Input User Name:");
		var phone = JOptionPane.showInputDialog(this, "Input User Phone:");
		if (name != null && phone != null) {
			var staff = new Staff(0, name, phone, true);
			if (new StaffDAO().addStaff(staff)) {
				JOptionPane.showMessageDialog(this, "Add Success!");
				loadData();
			} else {
				JOptionPane.showMessageDialog(this, "Add fail!");
			}
		}
	}

	private void suaNhanVien() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please choose user you want to update!");
			return;
		}
		var id = (int) tableModel.getValueAt(selectedRow, 0);
		var name = JOptionPane.showInputDialog(this, "Name update :", tableModel.getValueAt(selectedRow, 1));
		var phone = JOptionPane.showInputDialog(this, "Phone update:", tableModel.getValueAt(selectedRow, 2));
		if (name != null && phone != null) {
			var staff = new Staff(id, name, phone, true);
			if (new StaffDAO().updateStaff(staff)) {
				JOptionPane.showMessageDialog(this, "Update Success!");
				loadData();
			} else {
				JOptionPane.showMessageDialog(this, "Update Fail!");
			}
		}
	}

	private void xoaNhanVien() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please choose user you want to delete!");
			return;
		}
		var id = (int) tableModel.getValueAt(selectedRow, 0);
		var confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete ?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			if (new StaffDAO().deleteStaff(id)) {
				JOptionPane.showMessageDialog(this, "Delete Success!");
				loadData();
			} else {
				JOptionPane.showMessageDialog(this, "Delete Fail!");
			}
		}
	}

}
