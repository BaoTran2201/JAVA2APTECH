package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dao.UserDAO;
import model.User;

public class UserManagementPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String selectedImagePath = null;

	// 🟢 Constructor mặc định: Hiển thị toàn bộ danh sách user
	public UserManagementPage() {
		initUI();
		loadDataToTable(null);
	}

	// 🟢 Constructor: Hiển thị thông tin chủ sở hữu căn hộ
	public UserManagementPage(User owner) {
		initUI();
		loadDataToTable(owner);
	}

	private void initUI() {
		setTitle("User Management Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 🏷️ Panel tiêu đề
		var panelTitle = new JPanel(new BorderLayout());
		panelTitle.setBackground(new Color(64, 128, 128));
		var lblTitle = new JLabel("Quản Lý Người Dùng", JLabel.CENTER);
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

		// 🏷️ Bảng dữ liệu
		String[] columnNames = { "ID", "Họ Tên", "Số Điện Thoại", "Email", "Quốc Gia", "Căn Hộ", "Ảnh Giấy Tờ" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				return (column == 6) ? ImageIcon.class : Object.class; // Cột ảnh sử dụng ImageIcon
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(80); // 🔥 Tăng chiều cao hàng để hiển thị ảnh rõ hơn
		table.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer()); // Sử dụng ImageRenderer

		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		// 🏷️ Panel nút chức năng
		var panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelBottom.setBackground(new Color(64, 128, 128));

		var btnThem = new JButton("Thêm");
		btnThem.addActionListener(e -> themUser());

		var btnSua = new JButton("Sửa");
		btnSua.addActionListener(e -> suaUser());

		var btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(e -> xoaUser());

		panelBottom.add(btnThem);
		panelBottom.add(btnSua);
		panelBottom.add(btnXoa);
		contentPane.add(panelBottom, BorderLayout.SOUTH);
	}

	private void loadDataToTable(User owner) {
		tableModel.setRowCount(0);
		var dao = new UserDAO();
		var users = (owner == null) ? dao.getAllUsers() : List.of(owner);

		for (User u : users) {
			var imageIcon = loadImageIcon(u.getIdentityImage(), 80, 80); // Load ảnh với kích thước 80x80
			tableModel.addRow(new Object[] { u.getMemberID(), u.getMemberName(), u.getPhone(), u.getEmail(),
					u.getCountry(), u.getApartmentID(), imageIcon });
		}
	}

	// 🔹 Hàm tải ảnh từ đường dẫn và chuyển thành ImageIcon
	private ImageIcon loadImageIcon(String imagePath, int width, int height) {
		if (imagePath == null || imagePath.isEmpty()) {
			return new ImageIcon(); // Tránh lỗi nếu không có ảnh
		}

		try {
			var imgFile = new File(imagePath);
			if (!imgFile.exists()) {
				return new ImageIcon(); // Tránh lỗi file không tồn tại
			}

			var img = ImageIO.read(imgFile);
			var scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImg);
		} catch (IOException e) {
			e.printStackTrace();
			return new ImageIcon();
		}
	}

	// 🔹 Renderer để hiển thị hình ảnh trong JTable
	private class ImageRenderer extends DefaultTableCellRenderer {
		@Override
		protected void setValue(Object value) {
			if (value instanceof ImageIcon) {
				setIcon((ImageIcon) value);
				setText(""); // Xóa nội dung text
			} else {
				super.setValue(value);
			}
		}
	}

	// 🔹 Thêm người dùng mới
	private void themUser() {
		var frame = new JFrame("Thêm Người Dùng");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);

		// 🌟 Tiêu đề
		var panelTitle = new JPanel(new BorderLayout());
		panelTitle.setBackground(new Color(64, 128, 128));

		var lblTitle = new JLabel("Thêm Người Dùng", JLabel.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

		var btnClose = new JButton("✖");
		btnClose.setFont(new Font("Arial", Font.BOLD, 18));
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(new Color(64, 128, 128));
		btnClose.setBorder(null);
		btnClose.setFocusPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnClose.addActionListener(e -> frame.dispose());

		panelTitle.add(lblTitle, BorderLayout.CENTER);
		panelTitle.add(btnClose, BorderLayout.EAST);
		frame.add(panelTitle, BorderLayout.NORTH);

		// 🌟 Nội dung form
		var panelForm = new JPanel(new GridLayout(13, 2, 15, 15));
		panelForm.setBorder(new EmptyBorder(20, 30, 20, 30));

		var txtFullName = new JTextField();
		var txtPhone = new JTextField();
		var txtEmail = new JTextField();
		var txtCountry = new JTextField();
		var txtApartmentNumber = new JTextField();
		var txtDob = new JTextField("yyyy-MM-dd");
		var txtStartDate = new JTextField("yyyy-MM-dd");
		var txtEndDate = new JTextField("yyyy-MM-dd");
		var txtQuantity = new JTextField();
		var txtVerifyCode = new JTextField();
		var lblImagePreview = new JLabel("Chưa chọn ảnh");

		// 🔹 Chọn giới tính bằng RadioButton
		var maleRadio = new JRadioButton("Nam");
		var femaleRadio = new JRadioButton("Nữ");
		var genderGroup = new ButtonGroup();
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);

		var genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		genderPanel.add(maleRadio);
		genderPanel.add(femaleRadio);

		// 🔹 Chọn ảnh giấy tờ tùy thân
		var btnUpload = new JButton("Chọn ảnh");
		btnUpload.setFont(new Font("Arial", Font.BOLD, 14));
		btnUpload.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUpload.addActionListener(e -> {
			var fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));
			var result = fileChooser.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION) {
				var selectedFile = fileChooser.getSelectedFile();
				selectedImagePath = selectedFile.getAbsolutePath();
				lblImagePreview.setText(selectedFile.getName());
			}
		});

		// 🔹 Thêm các thành phần vào form
		panelForm.add(new JLabel("Họ và tên:"));
		panelForm.add(txtFullName);
		panelForm.add(new JLabel("Số điện thoại:"));
		panelForm.add(txtPhone);
		panelForm.add(new JLabel("Email:"));
		panelForm.add(txtEmail);
		panelForm.add(new JLabel("Quốc gia:"));
		panelForm.add(txtCountry);
		panelForm.add(new JLabel("Ngày sinh (yyyy-MM-dd):"));
		panelForm.add(txtDob);
		panelForm.add(new JLabel("Ngày bắt đầu (yyyy-MM-dd):"));
		panelForm.add(txtStartDate);
		panelForm.add(new JLabel("Ngày kết thúc (yyyy-MM-dd):"));
		panelForm.add(txtEndDate);
		panelForm.add(new JLabel("Số lượng thành viên:"));
		panelForm.add(txtQuantity);
		panelForm.add(new JLabel("Mã xác nhận:"));
		panelForm.add(txtVerifyCode);
		panelForm.add(new JLabel("Căn hộ:"));
		panelForm.add(txtApartmentNumber);
		panelForm.add(new JLabel("Giới tính:"));
		panelForm.add(genderPanel);
		panelForm.add(btnUpload);
		panelForm.add(lblImagePreview);

		frame.add(panelForm, BorderLayout.CENTER);

		// 🌟 Panel nút chức năng
		var panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelBottom.setBackground(new Color(64, 128, 128));

		var btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Arial", Font.BOLD, 16));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(34, 177, 76));
		btnThem.setBorder(new LineBorder(new Color(0, 102, 51), 2, true));
		btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));

		var btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(new Color(192, 57, 43));
		btnHuy.setBorder(new LineBorder(new Color(139, 0, 0), 2, true));
		btnHuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnHuy.addActionListener(e -> frame.dispose());

		btnThem.addActionListener(e -> {
			try {
				var dao = new UserDAO();
				var imagePath = selectedImagePath != null ? selectedImagePath : "default.png";
				var apartmentID = dao.getApartmentIDFromNumber(txtApartmentNumber.getText());

				if (apartmentID == -1) {
					JOptionPane.showMessageDialog(frame, "Căn hộ không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 🔥 Xử lý lỗi định dạng ngày
				Date dob, startDate, endDate;
				try {
					dob = Date.valueOf(txtDob.getText().trim());
					startDate = Date.valueOf(txtStartDate.getText().trim());
					endDate = Date.valueOf(txtEndDate.getText().trim());
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(frame, "Vui lòng nhập đúng định dạng ngày!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 🔥 Kiểm tra nhập số tránh lỗi `NumberFormatException`
				int quantity, verifyCode;
				try {
					quantity = Integer.parseInt(txtQuantity.getText().trim());
					verifyCode = Integer.parseInt(txtVerifyCode.getText().trim());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Số lượng và mã xác nhận phải là số!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				var gender = maleRadio.isSelected();

				var newUser = new User(0, txtFullName.getText().trim(), imagePath, txtCountry.getText().trim(), dob,
						startDate, endDate, quantity, txtPhone.getText().trim(), txtEmail.getText().trim(), verifyCode,
						gender, apartmentID, true, txtApartmentNumber.getText().trim());

				if (dao.addUser(newUser)) {
					JOptionPane.showMessageDialog(frame, "Thêm người dùng thành công!", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					loadDataToTable(null);
					new QuanLyChungCuGUI().loadApartmentsToGUI(); // 🔥 Cập nhật trạng thái căn hộ
				} else {
					JOptionPane.showMessageDialog(frame, "Lỗi khi thêm người dùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		panelBottom.add(btnThem);
		panelBottom.add(btnHuy);
		frame.add(panelBottom, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	// 🔹 Chỉnh sửa người dùng
	private void suaUser() {

	}

	// 🔹 Xóa người dùng
	private void xoaUser() {
		var selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			var userId = (int) tableModel.getValueAt(selectedRow, 0);
			new UserDAO().deleteUser(userId);
			loadDataToTable(null);
		}
	}
}
