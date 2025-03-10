package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Dao.ApartmentDAO;
import Dao.MemberDAO;

public class UserManagementPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private int editingMemberID = -1; // Lưu ID của user đang chỉnh sửa
	private AbstractButton txtMemberID;
	private JDialog apartmentDialog;
	public UserManagementPage() {
		initUI();
		loadDataToTable();
	}

	private void initUI() {
		setTitle("User Management Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Panel tiêu đề
		var panelTitle = new JPanel(new BorderLayout());
		panelTitle.setBackground(new Color(64, 128, 128));
		var lblTitle = new JLabel("User Management", JLabel.CENTER);
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
			new QuanLyChungCuGUI().setVisible(true);
			dispose();
		});

		panelTitle.add(btnBack, BorderLayout.WEST);
		panelTitle.add(lblTitle, BorderLayout.CENTER);
		contentPane.add(panelTitle, BorderLayout.NORTH);

		// Bảng dữ liệu
		String[] columnNames = { "ID", "FULLNAME", "GENDER", "BIRTH", "PHONE", "CCCD", "COUNTRY", "NUM OF MEMBERS",
				"APARTMENT ID", "START DATE", "END DATE", "PHOTO" };

		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Ngăn không cho chỉnh sửa bất kỳ ô nào
			}
			@Override
			public Class<?> getColumnClass(int column) {
				return (column == 11) ? ImageIcon.class : Object.class; // Cột ảnh nằm ở vị trí 11
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(80); // Đảm bảo đủ chỗ hiển thị ảnh

		// Set Renderer cho cột ảnh
		table.getColumnModel().getColumn(11).setCellRenderer(new ImageRenderer());

		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

		// Panel nút chức năng
		var panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelBottom.setBackground(new Color(64, 128, 128));
		var btnSua = new JButton("Update");
		btnSua.addActionListener(e -> suaUser());
		var btnXoa = new JButton("Delete");
		btnXoa.addActionListener(e -> xoaUser());
		panelBottom.add(btnSua);
		panelBottom.add(btnXoa);
		contentPane.add(panelBottom, BorderLayout.SOUTH);

	}

	private void loadDataToTable() {
		var dao = new MemberDAO();
		var userList = dao.getAllMembers();

		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		tableModel.setRowCount(0);
		userList.forEach(user -> tableModel.addRow(new Object[] { user.getMemberID(), user.getMemberName(),
				user.isGender() ? "MALE" : "FEMALE", user.getDob().format(formatter), // Chuyển LocalDate thành String
				user.getPhone(), user.getCccd(), user.getCountry(), user.getQuantity(), user.getApartmentID(),
				user.getStartDate().format(formatter), // Chuyển LocalDate thành String
				user.getEndDate().format(formatter), // Chuyển LocalDate thành String
				loadImageIcon(user.getAvatar(), 80, 80) }));
	}

	private ImageIcon loadImageIcon(String imagePath, int width, int height) {
		try {
			var imgFile = new File(imagePath);
			Image img = ImageIO.read(imgFile);
			var scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImg);
		} catch (Exception e) {
			e.printStackTrace();
			return new ImageIcon();
		}
	}

	// Renderer để hiển thị hình ảnh trong JTable
	private class ImageRenderer extends DefaultTableCellRenderer {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void setValue(Object value) {
			if (value instanceof ImageIcon) {
				setIcon((ImageIcon) value);
				setText("");
			} else {
				super.setValue(value);
			}
		}
	}

//sửa
	private void suaUser() {
	    var selectedRow = table.getSelectedRow();
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Please choose user!", "Information!",
	                JOptionPane.WARNING_MESSAGE);
	        return;
	    }

		editingMemberID = (int) tableModel.getValueAt(selectedRow, 0);
	    var fullName = (String) tableModel.getValueAt(selectedRow, 1);
	    var gender = (String) tableModel.getValueAt(selectedRow, 2);
	    var birthStr = (String) tableModel.getValueAt(selectedRow, 3);
	    var phone = (String) tableModel.getValueAt(selectedRow, 4);
	    var cccd = (String) tableModel.getValueAt(selectedRow, 5);
	    var country = (String) tableModel.getValueAt(selectedRow, 6);
	    var numOfMembers = (int) tableModel.getValueAt(selectedRow, 7);
	    var apartmentID = (int) tableModel.getValueAt(selectedRow, 8);
	    var startDateStr = (String) tableModel.getValueAt(selectedRow, 9);
	    var endDateStr = (String) tableModel.getValueAt(selectedRow, 10);
	    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    var birthDate = LocalDate.parse(birthStr, formatter);
	    var startDate = LocalDate.parse(startDateStr, formatter);
	    var endDate = LocalDate.parse(endDateStr, formatter);

	    var editDialog = new JDialog(this, "Edit User", true);
	    editDialog.setSize(450, 500);
	    editDialog.setLocationRelativeTo(this);
	    editDialog.setLayout(new BorderLayout());

	    var formPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // GridLayout với khoảng cách
	    formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	    formPanel.setBackground(new Color(240, 248, 255)); // Màu nền nhẹ

	    var lblFullName = new JLabel("Full Name:");
	    lblFullName.setFont(new Font("Arial", Font.BOLD, 13));
	    var txtFullName = new JTextField(fullName);

	    var lblGender = new JLabel("Gender:");
	    lblGender.setFont(new Font("Arial", Font.BOLD, 13));
	    var cmbGender = new JComboBox<>(new String[]{"MALE", "FEMALE"});
	    cmbGender.setSelectedItem(gender);

	    var lblBirthDate = new JLabel("Birth Date:");
	    lblBirthDate.setFont(new Font("Arial", Font.BOLD, 13));
	    var birthChooser = new JDateChooser();
	    birthChooser.setDate(Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    birthChooser.setDateFormatString("yyyy-MM-dd");

	    var lblPhone = new JLabel("Phone:");
	    lblPhone.setFont(new Font("Arial", Font.BOLD, 13));
	    var txtPhone = new JTextField(phone);

	    var lblCCCD = new JLabel("CCCD:");
	    lblCCCD.setFont(new Font("Arial", Font.BOLD, 13));
	    var txtCCCD = new JTextField(cccd);

	    var lblCountry = new JLabel("Country:");
	    lblCountry.setFont(new Font("Arial", Font.BOLD, 13));
	    var txtCountry = new JTextField(country);

	    var lblNumMembers = new JLabel("Num of Members:");
	    lblNumMembers.setFont(new Font("Arial", Font.BOLD, 13));
	    var txtNumOfMembers = new JTextField(String.valueOf(numOfMembers));

	    var lblApartmentID = new JLabel("Apartment ID:");
	    lblApartmentID.setFont(new Font("Arial", Font.BOLD, 13));
	    var txtApartmentID = new JTextField(String.valueOf(apartmentID));

	    var lblStartDate = new JLabel("Start Date:");
	    lblStartDate.setFont(new Font("Arial", Font.BOLD, 13));
	    var startDateChooser = new JDateChooser();
	    startDateChooser.setDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    startDateChooser.setDateFormatString("yyyy-MM-dd");

	    var lblEndDate = new JLabel("End Date:");
	    lblEndDate.setFont(new Font("Arial", Font.BOLD, 13));
	    var endDateChooser = new JDateChooser();
	    endDateChooser.setDate(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    endDateChooser.setDateFormatString("yyyy-MM-dd");
		startDateChooser.getDateEditor().getUiComponent().setEnabled(false);
		startDateChooser.getDateEditor().getUiComponent().setFocusable(false);
		endDateChooser.getDateEditor().getUiComponent().setEnabled(false);
		endDateChooser.getDateEditor().getUiComponent().setFocusable(false);
		// Thêm nút "View Apartments"
		var btnViewApartments = new JButton("View Apartments");
		btnViewApartments.addActionListener(e -> showApartmentList());

	    // Thêm vào Panel
	    formPanel.add(lblFullName);
	    formPanel.add(txtFullName);
	    formPanel.add(lblGender);
	    formPanel.add(cmbGender);
	    formPanel.add(lblBirthDate);
	    formPanel.add(birthChooser);
	    formPanel.add(lblPhone);
	    formPanel.add(txtPhone);
	    formPanel.add(lblCCCD);
	    formPanel.add(txtCCCD);
	    formPanel.add(lblCountry);
	    formPanel.add(txtCountry);
	    formPanel.add(lblNumMembers);
	    formPanel.add(txtNumOfMembers);
	    formPanel.add(lblApartmentID);
	    formPanel.add(txtApartmentID);
	    formPanel.add(lblStartDate);
	    formPanel.add(startDateChooser);
	    formPanel.add(lblEndDate);
	    formPanel.add(endDateChooser);

	    // Button Panel
	    var buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonPanel.add(btnViewApartments);
		var btnSave = new JButton("Save");
	    btnSave.setBackground(new Color(34, 139, 34));
	    btnSave.setForeground(Color.WHITE);
	    btnSave.setFont(new Font("Arial", Font.BOLD, 13));
	    btnSave.setPreferredSize(new Dimension(100, 35));
		btnSave.addActionListener(e -> {
			try {
				// Lấy dữ liệu từ form và kiểm tra null
				var fullName1 = txtFullName.getText().trim();
				if (fullName1.isEmpty()) {
					fullName1 = "";
				}

				var gender1 = cmbGender.getSelectedItem().equals("MALE");

				// Xử lý ngày tháng (cho phép null nếu không chọn)
				var birthDate1 = (birthChooser.getDate() != null)
						? birthChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						: null;

				var phone1 = txtPhone.getText();
				phone1 = (phone1 != null && !phone1.isBlank()) ? phone1.trim() : "";

				var cccd1 = txtCCCD.getText();
				cccd1 = (cccd1 != null && !cccd1.isBlank()) ? cccd1.trim() : "";

				var country1 = txtCountry.getText().trim();
				if (country1.isEmpty()) {
					country1 = "";
				}

				var numOfMembers1 = 0;
				if (!txtNumOfMembers.getText().trim().isEmpty()) {
					numOfMembers1 = Integer.parseInt(txtNumOfMembers.getText().trim());
				}
				// integer cho nhập null
				Integer apartmentID1 = null;
				var IDcheck = new ApartmentDAO();
				// Kiểm tra nếu ô nhập không rỗng
				if (!txtApartmentID.getText().trim().isEmpty()) {
					var isValid = false;
					while (!isValid) {
						try {
							var value = Integer.parseInt(txtApartmentID.getText().trim());
							if (value != 0) {
								if (IDcheck.isApartmentOccupied(value)) {
									txtApartmentID.setText("");
									txtApartmentID.requestFocus(); // Đưa con trỏ về ô nhập
									JOptionPane.showMessageDialog(null, "Apartment have Owner !");
									return;
								}
								apartmentID1 = value;
								isValid = true;
							}
						} catch (NumberFormatException ex) {
							txtApartmentID.setText(""); // Xóa nội dung ô nhập
							txtApartmentID.requestFocus(); // Đưa con trỏ về ô nhập
							JOptionPane.showMessageDialog(null, "Input correct number!");
							return; // Dừng xử lý để yêu cầu nhập lại
						}
					}
				}

				// Xử lý ngày hợp đồng (cho phép null)
				var startDate1 = startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				var endDate1 = endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				if (startDate1.isAfter(endDate1)) {
					throw new IllegalArgumentException("Date start after date end");
				}

				// Cập nhật vào CSDL
				var dao = new MemberDAO();
				var dao1 = new ApartmentDAO();
				var success = dao.updateMember(editingMemberID, fullName1, gender1, birthDate1, phone1, cccd1, country1,
						numOfMembers1, apartmentID1, startDate1, endDate1);

				if (!success) {
					throw new Exception("Fail");
				}
//				if (apartmentID1 != null) {
//					dao1.increaseApartmentStatus(apartmentID1);
//				}
				// Cập nhật thành công
				JOptionPane.showMessageDialog(editDialog, "Update Success", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				// Làm mới bảng và đóng dialog
				loadDataToTable();
				editDialog.dispose();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(editDialog, "Not format number: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(editDialog, ex.getMessage(), "data fail",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(editDialog, "Error: " + ex.getMessage(), "Eroor",
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});

		var btnCancel = new JButton("Exit");
	    btnCancel.setBackground(new Color(178, 34, 34));
	    btnCancel.setForeground(Color.WHITE);
	    btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
	    btnCancel.setPreferredSize(new Dimension(100, 35));
	    btnCancel.addActionListener(e -> editDialog.dispose());

	    buttonPanel.add(btnSave);
	    buttonPanel.add(btnCancel);

	    editDialog.add(formPanel, BorderLayout.CENTER);
	    editDialog.add(buttonPanel, BorderLayout.SOUTH);
	    editDialog.setVisible(true);
	}



	private void showApartmentList() {
		var apartmentDialog = new JDialog(this, "Apartment List", true);
		apartmentDialog.setSize(400, 400);
		apartmentDialog.setLocationRelativeTo(this);

		String[] columnNames = { "Apartment ID", "Apartment Number", "Status" };
		var tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Ngăn không cho chỉnh sửa dữ liệu
	        }
		};
		var table = new JTable(tableModel);
		var dao = new ApartmentDAO();
		var apartments = dao.getAllApartments();

		for (var ap : apartments) {
			tableModel.addRow(new Object[] { ap.getApartmentID(), ap.getApartmentNumber(), ap.getApartmentsStatus() });
		}

		var btnClose = new JButton("Close");
		btnClose.addActionListener(e -> apartmentDialog.dispose());

		var panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		panel.add(btnClose, BorderLayout.SOUTH);

		apartmentDialog.add(panel);
		apartmentDialog.setVisible(true);
	}





	// Xóa người dùng
	private void xoaUser() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please choose user to delete!", "WARNING_MESSAGE",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		var dao = new MemberDAO();
		var userID = (int) table.getValueAt(selectedRow, 0); // Lấy ID người dùng từ bảng

		var confirm = JOptionPane.showConfirmDialog(this, "Are you sure to lock this user?",
				"Accept", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			if (dao.updateLoginStatus(userID, false)) { // false = vô hiệu hóa tài khoản
				JOptionPane.showMessageDialog(this, "Locked succes!", "INFORMATION_MESSAGE",
						JOptionPane.INFORMATION_MESSAGE);
				loadDataToTable(); // Cập nhật lại dữ liệu trên bảng
			} else {
				JOptionPane.showMessageDialog(this, "Fail!", "ERROR_MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
