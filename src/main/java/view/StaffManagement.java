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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Tiêu đề
		var lblTitle = new JLabel("Quản Lý Nhân Viên", JLabel.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(64, 128, 128));
		lblTitle.setForeground(Color.WHITE);
		add(lblTitle, BorderLayout.NORTH);

		// Bảng nhân viên
		tableModel = new DefaultTableModel(new String[] { "ID", "Tên", "Số điện thoại", "Trạng thái" }, 0);
		table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);

		// Nút chức năng
		var panelButtons = new JPanel(new FlowLayout());
		var btnThem = new JButton("Thêm");
		var btnSua = new JButton("Sửa");
		var btnXoa = new JButton("Xóa");

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
					s.isStaffStatus() ? "Hoạt động" : "Ngừng" });
		}
	}

	private void themNhanVien() {
		var name = JOptionPane.showInputDialog(this, "Nhập tên nhân viên:");
		var phone = JOptionPane.showInputDialog(this, "Nhập số điện thoại:");
		if (name != null && phone != null) {
			var staff = new Staff(0, name, phone, true);
			if (new StaffDAO().addStaff(staff)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
				loadData();
			} else {
				JOptionPane.showMessageDialog(this, "Thêm thất bại!");
			}
		}
	}

	private void suaNhanVien() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa!");
			return;
		}
		var id = (int) tableModel.getValueAt(selectedRow, 0);
		var name = JOptionPane.showInputDialog(this, "Sửa tên:", tableModel.getValueAt(selectedRow, 1));
		var phone = JOptionPane.showInputDialog(this, "Sửa số điện thoại:", tableModel.getValueAt(selectedRow, 2));
		if (name != null && phone != null) {
			var staff = new Staff(id, name, phone, true);
			if (new StaffDAO().updateStaff(staff)) {
				JOptionPane.showMessageDialog(this, "Sửa thành công!");
				loadData();
			} else {
				JOptionPane.showMessageDialog(this, "Sửa thất bại!");
			}
		}
	}

	private void xoaNhanVien() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!");
			return;
		}
		var id = (int) tableModel.getValueAt(selectedRow, 0);
		var confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa nhân viên?", "Xóa", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			if (new StaffDAO().deleteStaff(id)) {
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				loadData();
			} else {
				JOptionPane.showMessageDialog(this, "Xóa thất bại!");
			}
		}
	}

	public static void main(String[] args) {
		new StaffManagement().setVisible(true);
		;
	}
}
