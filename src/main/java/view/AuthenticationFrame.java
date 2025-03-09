package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Dao.FeedbackDAO;
import Dao.LoginDAO;
import Dao.UserServiceDAO;

public class AuthenticationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private LoginDAO loginDAO;
	private JTextField searchField;
	private JTabbedPane tabbedPane;
	private JLabel titleLabel;
	private JTextField feedbackSearchField;
	private DefaultTableModel feedbackModel;
	private JTable feedbackTable;
	private JTextField serviceSearchField;
	private DefaultTableModel serviceModel;
	private JTable serviceTable;
	private JDateChooser fromDateChooser;
	private JDateChooser toDateChooser;
	private JCheckBox chkActiveOnly;
	private JCheckBox chkInactiveOnly;

	private void loadData() {
		var dao = new LoginDAO();
		var userList = dao.getUsersByRole();
		model.setRowCount(0);
		userList.forEach(user -> model.addRow(new Object[] { user.getMemberID(), user.getUsername(), user.getPass(),
				user.getEmail(), user.isLoginStatus() }));
	}

	private void loadFeedbackData() {
		var dao = new FeedbackDAO();
		var feedbackList = dao.getAllFeedbacks();
		feedbackModel.setRowCount(0);

		feedbackList.forEach(feedback -> feedbackModel.addRow(
				new Object[] { feedback.getFeedbackID(), feedback.getMemberID(),
						feedback.getNamefb(), feedback.getFeedbackTittle(), feedback.getNote(),
						feedback.getFeedbackDate(), feedback.isStatusfb() }));
	}

	private void loadServicesForCustomer() {
		var dao = new UserServiceDAO();
		var userServicesList = dao.loadUserServices();

		serviceModel.setRowCount(0);

		userServicesList.forEach(userService -> {

			System.out.println("UserServiceID: " + userService.getUserServiceID() + ", MemberID: "
					+ userService.getMemberID() + ", ServiceName: " + userService.getServiceName() + ", Daystart: "
					+ userService.getDayStart() + ", Dayend: " + userService.getDayEnd() + ", Status: "
					+ userService.isStatusSer());
			// Thêm thông tin vào bảng
			serviceModel.addRow(new Object[] { userService.getUserServiceID(), userService.getMemberID(),
					userService.getServiceID(), userService.getServiceName(), userService.getDayStart(),
					userService.getDayEnd(), userService.isStatusSer()
			});
		});
	}



	public AuthenticationFrame() {
		setTitle("User Authorization");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1500, 900);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(64, 128, 128));

		// Header Panel
		var headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(new Color(64, 128, 128));

		// Title Label
		titleLabel = new JLabel("User Authorization", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setForeground(Color.WHITE);
		headerPanel.add(titleLabel, BorderLayout.CENTER);

		// Back Button
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
		headerPanel.add(btnBack, BorderLayout.WEST);

		add(headerPanel, BorderLayout.NORTH);

		loginDAO = new LoginDAO();
		tabbedPane = new JTabbedPane();
		tabbedPane.addChangeListener(e -> updateTitle());
		add(tabbedPane, BorderLayout.CENTER);

		var panel = new JPanel(new BorderLayout());
		var searchPanel = new JPanel();
		searchField = new JTextField(20);
		var btnSearch = new JButton("Search");
		var btnReset = new JButton("Reset");
		btnSearch.addActionListener(this::searchUser);
		btnReset.addActionListener(this::resetTable);
		searchPanel.add(searchField);
		searchPanel.add(btnSearch);
		searchPanel.add(btnReset);
		panel.add(searchPanel, BorderLayout.SOUTH);

		model = new DefaultTableModel(new String[] { "User ID", "Username", "Password", "Email", "Status" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1 || column == 2 || column == 3 || column == 4;
			}

			@Override
			public void setValueAt(Object value, int row, int column) {
				var memberID = (int) getValueAt(row, 0);
				var fieldName = "";
				var oldValue = getValueAt(row, column).toString();
				var newValue = value.toString();

				if (oldValue.equals(newValue)) {
					return;
				}
				if (column == 4) {
					super.setValueAt(value, row, column);
					return;
				}
				switch (column) {
				case 1:
					fieldName = "Username";

					if (loginDAO.isUsername(newValue)) {
						JOptionPane.showMessageDialog(null,
								"Username already exists! Please choose a different username.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					break;
				case 2:
					fieldName = "Password";
					break;
				case 3:
					fieldName = "Email";
					break;
				default:
					return;
				}

				var confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to change " + fieldName + " from " + oldValue + " to " + newValue + "?",
						"Update Success", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					switch (column) {
					case 1:
						loginDAO.updateUsernameByID(memberID, newValue);
						break;
					case 2:
						loginDAO.updatePasswordByID(memberID, newValue);
						break;
					case 3:
						loginDAO.updateEmailByID(memberID, newValue);
						break;
					}

					super.setValueAt(value, row, column);
					JOptionPane.showMessageDialog(null, fieldName + " updated successfully!", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};

		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.getTableHeader().setBackground(new Color(64, 128, 128));
		table.getTableHeader().setForeground(Color.WHITE);

		// Hide user ID column
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		// Renderer và Editor cho Status
		table.getColumnModel().getColumn(4).setCellRenderer(new StatusRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(new StatusEditor());

		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		loadData();
		tabbedPane.addTab("User Authorization", panel);

		// giao diện feedback
		var feedbackPanel = new JPanel(new BorderLayout());
		var feedbackSearchPanel = new JPanel();
		feedbackSearchField = new JTextField(20);
		var btnFeedbackSearch = new JButton("Search");
		var btnFeedbackReset = new JButton("Reset");
		fromDateChooser = new JDateChooser();
		fromDateChooser.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày
		fromDateChooser.setDate(new Date());
		fromDateChooser.setPreferredSize(new Dimension(150, 30)); // Đặt kích thước rộng hơn

		toDateChooser = new JDateChooser();
		toDateChooser.setDateFormatString("dd/MM/yyyy");
		toDateChooser.setDate(new Date());
		toDateChooser.setPreferredSize(new Dimension(150, 30)); // Đặt kích thước rộng hơn
		btnFeedbackSearch.addActionListener(this::searchFeedback);
		btnFeedbackReset.addActionListener(this::resetFeedbackTable);
		feedbackSearchPanel.add(feedbackSearchField);
		feedbackSearchPanel.add(btnFeedbackSearch);
		feedbackSearchPanel.add(btnFeedbackReset);
		feedbackSearchPanel.add(new JLabel("From:"));
		feedbackSearchPanel.add(fromDateChooser);
		feedbackSearchPanel.add(new JLabel("To:"));
		feedbackSearchPanel.add(toDateChooser);
		feedbackPanel.add(feedbackSearchPanel, BorderLayout.SOUTH);

		feedbackModel = new DefaultTableModel(
				new String[] { "Feedback ID", "MemberID", "User", "Title", "Content", "Date", "Status" }, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 6) {
					return Boolean.class; // Dùng Boolean để hiển thị Checkbox
				}
				return super.getColumnClass(columnIndex);
			}
		};
		feedbackTable = new JTable(feedbackModel);
		feedbackTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		feedbackTable.getTableHeader().setBackground(new Color(64, 128, 128));
		feedbackTable.getTableHeader().setForeground(Color.WHITE);
		feedbackTable.getColumnModel().getColumn(0).setMinWidth(0);
		feedbackTable.getColumnModel().getColumn(0).setMaxWidth(0);
		feedbackTable.getColumnModel().getColumn(0).setWidth(0);
		// Hiển thị Checkbox cho cột "Status"
		feedbackTable.getColumnModel().getColumn(6).setCellRenderer(feedbackTable.getDefaultRenderer(Boolean.class));
		feedbackTable.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		feedbackTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				var row = feedbackTable.getSelectedRow();
				var col = feedbackTable.getSelectedColumn();

				// Nếu không phải cột thứ 6 thì gọi showFeedback(row)
				if (row != -1 && col != 6) {
					showFeedback(row);
				}
			}
		});

		feedbackTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				var row = feedbackTable.getSelectedRow();
				var column = feedbackTable.getSelectedColumn();
				if (column == 6 && row != -1) { // Cột "Status" (chỉ mục 6)
					var oldStatus = (boolean) feedbackModel.getValueAt(row, column);
					System.out.println(oldStatus);
					var feedbackID = (int) feedbackModel.getValueAt(row, 0); // Feedback ID
					var dao = new FeedbackDAO();
					if (oldStatus) {
						var isUpdated = dao.updateFeedbackStatusByID(feedbackID, true);
						if (isUpdated) {
							loadFeedbackData();
							JOptionPane.showMessageDialog(null, "Feedback has been seen.", "Notification",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						var confirm = JOptionPane.showConfirmDialog(null, "Do you want to deactivate this feedback?",
								"Confirm Status Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (confirm == JOptionPane.YES_OPTION) {
							var isUpdated = dao.updateFeedbackStatusByID(feedbackID, false);
							if (isUpdated) {
								loadFeedbackData();
								JOptionPane.showMessageDialog(null, "Feedback deactivated successfully.",
										"Notification", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							feedbackModel.setValueAt(true, row, column);
						}

					}
				}
			}
		});


		feedbackPanel.add(new JScrollPane(feedbackTable), BorderLayout.CENTER);
		loadFeedbackData();
		tabbedPane.addTab("Feedback", feedbackPanel);
		//servies
		var servicePanel = new JPanel(new BorderLayout());
		var serviceSearchPanel = new JPanel();
		serviceSearchField = new JTextField(20);
		var btnServiceSearch = new JButton("Search");
		var btnServiceReset = new JButton("Reset");
		btnServiceSearch.addActionListener(this::searchService); // Placeholder
		btnServiceReset.addActionListener(this::resetServiceTable); // Placeholder
		serviceSearchPanel.add(serviceSearchField);
		serviceSearchPanel.add(btnServiceSearch);
		serviceSearchPanel.add(btnServiceReset);
		chkActiveOnly = new JCheckBox("Show Active Only");
		chkActiveOnly.addActionListener(e -> {
			if (chkActiveOnly.isSelected()) {
				chkInactiveOnly.setSelected(false);
			}
			filterServices();
		});
		serviceSearchPanel.add(chkActiveOnly);
		chkInactiveOnly = new JCheckBox("Show Inactive Only");
		chkInactiveOnly.addActionListener(e -> {
			if (chkInactiveOnly.isSelected()) {
				chkActiveOnly.setSelected(false);
			}
			filterServices();
		});
		serviceSearchPanel.add(chkInactiveOnly);

		servicePanel.add(serviceSearchPanel, BorderLayout.SOUTH);

		// service table model
		serviceModel = new DefaultTableModel(new String[] { "UserServiceID", "MemberID", "ServiceID", "ServiceName",
				"Daystart", "Dayend", "Status" }, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 6) { // Status column is Boolean
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		};
		serviceTable = new JTable(serviceModel);
		serviceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		serviceTable.getTableHeader().setBackground(new Color(64, 128, 128));
		serviceTable.getTableHeader().setForeground(Color.WHITE);
		serviceTable.getColumnModel().getColumn(6).setCellRenderer(serviceTable.getDefaultRenderer(Boolean.class));
		serviceTable.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		serviceTable.getModel().addTableModelListener(e -> {
			var row = e.getFirstRow();
			var column = e.getColumn();

			if (column == 6) { // Chỉ xử lý khi cột "Status" thay đổi
				var userServiceID = (int) serviceModel.getValueAt(row, 0); // Lấy UserServiceID
				var newStatus = (boolean) serviceModel.getValueAt(row, column);

				var dao = new UserServiceDAO();
				var isUpdated = dao.updateUserServiceStatus(userServiceID, newStatus);

				if (isUpdated) {
					JOptionPane.showMessageDialog(null, "Service status updated successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Failed to update service status!", "Error",
							JOptionPane.ERROR_MESSAGE);
					serviceModel.setValueAt(!newStatus, row, column); // Hoàn tác nếu update lỗi
				}
			}
		});
		servicePanel.add(new JScrollPane(serviceTable), BorderLayout.CENTER);

		loadServicesForCustomer();

		tabbedPane.addTab("Services", servicePanel);
	}

	private void resetServiceTable(ActionEvent e) {
		serviceSearchField.setText("");
		chkActiveOnly.setSelected(false);
		chkInactiveOnly.setSelected(false);

		var sorter = (TableRowSorter<DefaultTableModel>) serviceTable.getRowSorter();
		if (sorter != null) {
			sorter.setRowFilter(null);
		}

		loadServicesForCustomer();
	}

	private void filterServices() {
		if (serviceModel == null)
		 {
				return;
		}

		var sorter = new TableRowSorter<>(serviceModel);
		serviceTable.setRowSorter(sorter);

		if (chkActiveOnly.isSelected()) {
			sorter.setRowFilter(RowFilter.regexFilter("true", 6));
		} else if (chkInactiveOnly.isSelected()) {
			sorter.setRowFilter(RowFilter.regexFilter("false", 6));
			sorter.setRowFilter(null);
		}
	}


	private void searchService(ActionEvent e) {
		var dao = new UserServiceDAO();
		var keyword = serviceSearchField.getText().trim();
		var services = dao.searchService(keyword);
		serviceModel.setRowCount(0);
		services.forEach(service -> serviceModel
				.addRow(new Object[] { service.getUserServiceID(), service.getMemberID(), service.getServiceID(),
						service.getServiceName(), service.getDayStart(), service.getDayEnd(), service.isStatusSer() }));
	}

	private void updateTitle() {
		titleLabel.setText(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
	}

	private void showFeedback(int row) {
		var feedbackID = (int) feedbackModel.getValueAt(row, 0);
		var memberID = feedbackModel.getValueAt(row, 1).toString();
		var user = feedbackModel.getValueAt(row, 2).toString();
		var title = feedbackModel.getValueAt(row, 3).toString();
		var content = feedbackModel.getValueAt(row, 4).toString();
		var date = feedbackModel.getValueAt(row, 5).toString();
		var status = (boolean) feedbackModel.getValueAt(row, 6);

		var feedbackDAO = new FeedbackDAO();
		var imagePaths = feedbackDAO.getFeedbackImages(feedbackID);

		var feedbackDialog = new JDialog(this, "Feedback Details", true);
		feedbackDialog.setSize(600, 500);
		feedbackDialog.setLayout(new BorderLayout());

		var infoPanel = new JPanel(new GridLayout(6, 1));
		infoPanel.add(new JLabel("Member ID: " + memberID));
		infoPanel.add(new JLabel("User: " + user));
		infoPanel.add(new JLabel("Title: " + title));
		infoPanel.add(new JLabel("Content: " + content));
		infoPanel.add(new JLabel("Date: " + date));
		infoPanel.add(new JLabel("Status: " + (status ? "Read" : "Wait")));

		feedbackDialog.add(infoPanel, BorderLayout.NORTH);

		var imagePanel = new JPanel(new FlowLayout());
		imagePaths.forEach(imagePath -> {
			var icon = new ImageIcon(imagePath);
			var img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			var imageLabel = new JLabel(new ImageIcon(img));
			imagePanel.add(imageLabel);
		});

		var scrollPane = new JScrollPane(imagePanel);
		feedbackDialog.add(scrollPane, BorderLayout.CENTER);

		var closeButton = new JButton("Close");
		closeButton.addActionListener(e -> feedbackDialog.dispose());
		feedbackDialog.add(closeButton, BorderLayout.SOUTH);

		feedbackDialog.setLocationRelativeTo(this);
		feedbackDialog.setVisible(true);
	}
	// search
	private void searchUser(ActionEvent e) {
		var dao = new LoginDAO();
		var searchText = searchField.getText().trim();
		if (searchText.isEmpty()) {
			loadData();
		} else {
			var userList = dao.searchUsers(searchText);
			model.setRowCount(0);
			userList.forEach(user -> model.addRow(new Object[] { user.getMemberID(), user.getUsername(), user.getPass(),
					user.getEmail(), user.isLoginStatus() }));
		}
	}

	private void searchFeedback(ActionEvent e) {
		var dao = new FeedbackDAO();
		var keyword = feedbackSearchField.getText().trim();

		// Chuyển đổi từ Date sang LocalDate
		var fromDate = fromDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		var toDate = toDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		System.out.println("From: " + fromDate + ", To: " + toDate);

		// Kiểm tra nếu ngày "From" lớn hơn ngày "To"
		if (fromDate.isAfter(toDate)) {
			JOptionPane.showMessageDialog(null, "'From' date must be before 'To' date.");
			return;
		}

		feedbackModel.setRowCount(0);

		var feedbacks = dao.searchFeedbackByKeywordAndDate(keyword, fromDate, toDate);

		feedbacks.forEach(fb -> feedbackModel.addRow(new Object[] { fb.getFeedbackID(), fb.getMemberID(),
				fb.getNamefb(), fb.getFeedbackTittle(), fb.getNote(), fb.getFeedbackDate(), fb.isStatusfb() }));
	}


	// reset
	private void resetTable(ActionEvent e) {
		searchField.setText("");
		loadData();
	}
	private void resetFeedbackTable(ActionEvent e) {
		feedbackSearchField.setText("");
		fromDateChooser.setDate(new Date());
		toDateChooser.setDate(new Date());
		loadFeedbackData();
	}

	// giao diện nút
	public static class StatusRenderer extends JPanel implements TableCellRenderer {
		private final JRadioButton activeButton = new JRadioButton("Active");
		private final JRadioButton lockedButton = new JRadioButton("Locked");
		private final ButtonGroup group = new ButtonGroup();

		public StatusRenderer() {
			setLayout(new FlowLayout(FlowLayout.CENTER));
			setBackground(Color.WHITE);
			group.add(activeButton);
			group.add(lockedButton);
			activeButton.setOpaque(false);// nền trong suốt
			lockedButton.setOpaque(false);
			add(activeButton);
			add(lockedButton);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			var status = Boolean.TRUE.equals(value);
			activeButton.setSelected(status);
			lockedButton.setSelected(!status);
			setBackground(Color.WHITE);
			return this;
		}
	}

	public class StatusEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;
		private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		private final JRadioButton activeButton = new JRadioButton("Active");
		private final JRadioButton lockedButton = new JRadioButton("Locked");
		private final ButtonGroup group = new ButtonGroup();
		private boolean oldStatus; // Lưu trạng thái ban đầu

		public StatusEditor() {
			super(new JCheckBox()); // Không sử dụng JCheckBox
			group.add(activeButton);
			group.add(lockedButton);
			panel.add(activeButton);
			panel.add(lockedButton);

			activeButton.addActionListener(e -> handleUpdate(true));
			lockedButton.addActionListener(e -> handleUpdate(false));
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// Lưu trạng thái ban đầu
			oldStatus = (Boolean) value;
			activeButton.setSelected(oldStatus);
			lockedButton.setSelected(!oldStatus);
			return panel;
		}

		@Override
		public Object getCellEditorValue() {
			return activeButton.isSelected();
		}

		private void handleUpdate(boolean newStatus) {
			var selectedRow = table.getSelectedRow();
			var memberID = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
			var conf = JOptionPane.showConfirmDialog(null,
					newStatus ? "Are you sure you want to activate this account?"
							: "Are you sure you want to lock this account?",
					"Confirm Status Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (conf != JOptionPane.YES_OPTION) {
				// Nếu chọn "No", khôi phục trạng thái cũ ngay lập tức
				activeButton.setSelected(oldStatus);
				lockedButton.setSelected(!oldStatus);
				return;
			}

			// Nếu chọn "Yes"
			loginDAO.updateLoginStatusByID(memberID, newStatus);
			model.setValueAt(newStatus, selectedRow, 4);

			JOptionPane.showMessageDialog(null, newStatus ? "Account has been activated." : "Account has been locked.",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new AuthenticationFrame().setVisible(true));
	}
}
