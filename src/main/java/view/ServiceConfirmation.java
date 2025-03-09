package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Dao.UserServiceDAO;

public class ServiceConfirmation extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel tableModel;
	private int userID;

	public ServiceConfirmation(int userID) {
		this.userID = userID;
		setTitle("Registered Services");
		setBounds(200, 100, 800, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		var lblTitle = new JLabel("Registered Services", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0, 128, 128));
		lblTitle.setPreferredSize(new Dimension(100, 50));
		contentPane.add(lblTitle, BorderLayout.NORTH);

		String[] columnNames = { "ID", "Service Name", "Start Date", "End Date", "Status" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		var scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		loadRegisteredServices();
		addRightClickMenu();
	}

	private void loadRegisteredServices() {
		var dao = new UserServiceDAO();
		var services = dao.getRegisteredServices(userID);
		tableModel.setRowCount(0);

		services.forEach(service -> tableModel.addRow(new Object[] { service.getUserServiceID(),
				service.getServiceName(),
				service.getDayStart() != null ? service.getDayStart().toString() : "Not set",
				service.getDayEnd() != null ? service.getDayEnd().toString() : "Not set",
				service.isStatusSer() ? "Activated" : "Not Activated" }));
	}

	private void addRightClickMenu() {
		var popupMenu = new JPopupMenu();
		var deleteItem = new JMenuItem("Delete Service");

		deleteItem.addActionListener(e -> deleteSelectedService());

		popupMenu.add(deleteItem);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showPopup(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showPopup(e);
				}
			}

			private void showPopup(MouseEvent e) {
				var row = table.rowAtPoint(e.getPoint());
				if (row >= 0) {
					table.setRowSelectionInterval(row, row);
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});
	}

	private void deleteSelectedService() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a service to delete.");
			return;
		}

		var userServiceID = (int) tableModel.getValueAt(selectedRow, 0); // Cột chứa UserServiceID
		var serviceName = (String) tableModel.getValueAt(selectedRow, 1);
		var statusSer = String.valueOf(tableModel.getValueAt(selectedRow, 4)); // Đảm bảo lấy dữ liệu đúng

		// Kiểm tra nếu dịch vụ đã kích hoạt
		if ("Activated".equalsIgnoreCase(statusSer) || "1".equals(statusSer)) {
			JOptionPane.showMessageDialog(this, "Cannot delete this service because it is activated.");
			return;
		}

		var confirm = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to delete the service: " + serviceName + "?", "Delete Confirmation",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			var dao = new UserServiceDAO();
			var success = dao.deleteUserService(userServiceID);

			if (success) {
				JOptionPane.showMessageDialog(this, "Service deleted successfully!");
				loadRegisteredServices();
			} else {
				JOptionPane.showMessageDialog(this, "Failed to delete the service. Please try again.");
			}
		}
	}

}
