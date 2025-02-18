package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Dao.ApartmentDAO;
import Dao.MemberDAO;
import model.Apartment;

public class QuanLyChungCuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel menuPanel;
	private JLabel lblNewLabel;
	private JSeparator separator;
	private JButton btnHome;
	private JButton btnQuanlyphong;
	private JButton btnDichvu;
	private JButton btnThongke;
	private JButton btnKhachhang;
	private JButton btnThanhToan;
	private JButton btnCaidat;
	private JButton btnDangxuat;
	private JSeparator separator_1;
	private JLabel lblQunL;
	private JSeparator separator_2;
	private JButton btnNotification;
	private JButton btnRules;
	private JButton btnThngRac_2;
	private JPanel panel;
	private JButton btnTrong;
	private JButton btnDaChoThue;
	private JButton btnChoKy;
	private JButton btnBaoTri;
	private JButton btnDonDep;
	private JPanel Floor;
	private JLabel lblFloor1;
	private JPanel r101;
	private JPanel r102;
	private JPanel r103;
	private JPanel r104;
	private JPanel r105;
	private JPanel r109;
	private JPanel r106;
	private JPanel r110;
	private JPanel r107;
	private JPanel r111;
	private JPanel r108;
	private JPanel r112;
	private JLabel lblFloor2;
	private JPanel r201;
	private JPanel r202;
	private JPanel r203;
	private JPanel r204;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new QuanLyChungCuGUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuanLyChungCuGUI() {
		setBackground(new Color(255, 255, 255));
		setTitle("Apartment Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 889);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(64, 128, 128));
		menuPanel.setBounds(10, 11, 290, 811);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		lblNewLabel = new JLabel("QUẢN LÝ CHUNG CƯ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 23, 270, 24);
		menuPanel.add(lblNewLabel);

		separator = new JSeparator();
		separator.setBounds(32, 58, 233, 2);
		menuPanel.add(separator);

		btnHome = new JButton("Home");
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setFont(new Font("Arial", Font.BOLD, 17));
		btnHome.setFocusPainted(false);
		btnHome.setContentAreaFilled(false);
		btnHome.setBorderPainted(false);
		btnHome.setBounds(10, 82, 270, 42);
		menuPanel.add(btnHome);
		addHoverEffect(btnHome); // Thêm hiệu ứng hover
		// Xử lý sự kiện click nút Home
		btnHome.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new HomeScreen().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnQuanlyphong = new JButton("Quản Lý Phòng");
		btnQuanlyphong.setForeground(new Color(255, 255, 255));
		btnQuanlyphong.setFont(new Font("Arial", Font.BOLD, 17));
		btnQuanlyphong.setFocusPainted(false);
		btnQuanlyphong.setBorderPainted(false);
		btnQuanlyphong.setContentAreaFilled(false);
		btnQuanlyphong.setBounds(10, 135, 270, 42);
		menuPanel.add(btnQuanlyphong);
		addHoverEffect(btnQuanlyphong);
		// Xử lý sự kiện click nút Home
		btnQuanlyphong.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new RoomManagementPage().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnDichvu = new JButton("Dịch Vụ");
		btnDichvu.setForeground(new Color(255, 255, 255));
		btnDichvu.setFont(new Font("Arial", Font.BOLD, 17));
		btnDichvu.setFocusPainted(false);
		btnDichvu.setContentAreaFilled(false);
		btnDichvu.setBorderPainted(false);
		btnDichvu.setBounds(10, 188, 270, 42);
		menuPanel.add(btnDichvu);
		addHoverEffect(btnDichvu); // Thêm hiệu ứng hover
		// Xử lý sự kiện click nút Home
		btnDichvu.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new ServiceManagement().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnThongke = new JButton("Thống Kê");
		btnThongke.setForeground(new Color(255, 255, 255));
		btnThongke.setFont(new Font("Arial", Font.BOLD, 17));
		btnThongke.setFocusPainted(false);
		btnThongke.setBorderPainted(false);
		btnThongke.setContentAreaFilled(false);
		btnThongke.setBounds(10, 241, 270, 42);
		menuPanel.add(btnThongke);
		addHoverEffect(btnThongke); // Thêm hiệu ứng hover
		// Xử lý sự kiện click nút Home
		btnThongke.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new StatisticsFrame().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnKhachhang = new JButton("Khách Hàng");
		btnKhachhang.setForeground(new Color(255, 255, 255));
		btnKhachhang.setFont(new Font("Arial", Font.BOLD, 17));
		btnKhachhang.setFocusPainted(false);
		btnKhachhang.setContentAreaFilled(false);
		btnKhachhang.setBorderPainted(false);
		btnKhachhang.setBounds(10, 294, 270, 42);
		menuPanel.add(btnKhachhang);
		addHoverEffect(btnKhachhang); // Thêm hiệu ứng hover
		// Xử lý sự kiện click nút Home
		btnKhachhang.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new CustomerFrame().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font("Arial", Font.BOLD, 17));
		btnThanhToan.setFocusPainted(false);
		btnThanhToan.setContentAreaFilled(false);
		btnThanhToan.setBorderPainted(false);
		btnThanhToan.setBounds(10, 347, 270, 42);
		menuPanel.add(btnThanhToan);
		addHoverEffect(btnThanhToan); // Thêm hiệu ứng hover
		// Xử lý sự kiện click nút Home
		btnThanhToan.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new PaymentManagement().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnCaidat = new JButton("Cài Đặt");
		btnCaidat.setForeground(Color.WHITE);
		btnCaidat.setFont(new Font("Arial", Font.BOLD, 17));
		btnCaidat.setFocusPainted(false);
		btnCaidat.setContentAreaFilled(false);
		btnCaidat.setBorderPainted(false);
		btnCaidat.setBounds(10, 400, 270, 42);
		menuPanel.add(btnCaidat);
		addHoverEffect(btnCaidat); // Thêm hiệu ứng hover

		btnDangxuat = new JButton("Đăng Xuất");
		btnDangxuat.setForeground(Color.WHITE);
		btnDangxuat.setFont(new Font("Arial", Font.BOLD, 17));
		btnDangxuat.setFocusPainted(false);
		btnDangxuat.setContentAreaFilled(false);
		btnDangxuat.setBorderPainted(false);
		btnDangxuat.setBounds(10, 453, 270, 42);
		menuPanel.add(btnDangxuat);
		addHoverEffect(btnDangxuat);
		// Xử lý sự kiện click nút Logout
		btnDangxuat.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new LoginFrame().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		separator_1 = new JSeparator();
		separator_1.setBounds(10, 506, 270, 2);
		menuPanel.add(separator_1);

		lblQunL = new JLabel("NULL");
		lblQunL.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunL.setForeground(Color.WHITE);
		lblQunL.setFont(new Font("Arial", Font.BOLD, 20));
		lblQunL.setBounds(10, 519, 270, 24);
		menuPanel.add(lblQunL);

		separator_2 = new JSeparator();
		separator_2.setBounds(32, 554, 233, 2);
		menuPanel.add(separator_2);

		btnNotification = new JButton("Admin Notification");
		btnNotification.setForeground(Color.WHITE);
		btnNotification.setFont(new Font("Arial", Font.BOLD, 17));
		btnNotification.setFocusPainted(false);
		btnNotification.setContentAreaFilled(false);
		btnNotification.setBorderPainted(false);
		btnNotification.setBounds(10, 567, 270, 42);
		menuPanel.add(btnNotification);
		addHoverEffect(btnNotification);
		// Xử lý sự kiện click nút Logout
		btnNotification.addActionListener(e -> {
			// Tạo và hiển thị trang HomeScreen
			new AdminNotificationForm().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnRules = new JButton("Rules");
		btnRules.setForeground(Color.WHITE);
		btnRules.setFont(new Font("Arial", Font.BOLD, 17));
		btnRules.setFocusPainted(false);
		btnRules.setContentAreaFilled(false);
		btnRules.setBorderPainted(false);
		btnRules.setBounds(10, 651, 270, 42);
		menuPanel.add(btnRules);
		addHoverEffect(btnRules);
		// Xử lý sự kiện click nút Rules
		btnRules.addActionListener(e -> {
			// Tạo và hiển thị trang ApartmentRules
			new ApartmentRules().setVisible(true); // Trang chủ HomeScreen sẽ được mở
			dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
		});

		btnThngRac_2 = new JButton("Thùng Rác");
		btnThngRac_2.setForeground(Color.WHITE);
		btnThngRac_2.setFont(new Font("Arial", Font.BOLD, 17));
		btnThngRac_2.setFocusPainted(false);
		btnThngRac_2.setContentAreaFilled(false);
		btnThngRac_2.setBorderPainted(false);
		btnThngRac_2.setBounds(10, 733, 270, 42);
		menuPanel.add(btnThngRac_2);
		addHoverEffect(btnThngRac_2);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(128, 128, 0), 2));
		panel.setToolTipText("Ghi chú trạng thái");
		panel.setBounds(310, 11, 956, 60);
		contentPane.add(panel);
		panel.setLayout(null);

		btnTrong = new JButton("Căn Hộ Trống");
		btnTrong.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTrong.setBounds(28, 11, 147, 38);
		btnTrong.setBackground(new Color(144, 202, 249));
		btnTrong.setForeground(Color.WHITE);
		// Màu gốc và màu khi hover
		var defaultColor = new Color(144, 202, 249); // Màu gốc của nút
		var hoverColor = new Color(30, 144, 255); // Màu khi hover
		panel.add(btnTrong);

		btnTrong.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnTrong.setBackground(hoverColor); // Đổi màu khi hover
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnTrong.setBackground(defaultColor); // Trả lại màu gốc
			}
		});

		btnDaChoThue = new JButton("Đã Cho Thuê");
		btnDaChoThue.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDaChoThue.setBounds(220, 11, 147, 38);
		btnDaChoThue.setBackground(new Color(165, 214, 167)); // Xanh lá nhạt
		btnDaChoThue.setForeground(Color.BLACK);
		// Màu gốc và màu khi hover
		var defaultColor1 = new Color(165, 214, 167);
		var hoverColor1 = new Color(0, 205, 102);
		panel.add(btnDaChoThue);

		btnDaChoThue.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnDaChoThue.setBackground(hoverColor1); // Đổi màu khi hover
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnDaChoThue.setBackground(defaultColor1); // Trả lại màu gốc
			}
		});

		btnChoKy = new JButton("Chờ Ký Hợp Đồng");
		btnChoKy.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChoKy.setBounds(415, 11, 147, 38);
		btnChoKy.setBackground(new Color(255, 204, 128));
		btnChoKy.setForeground(Color.BLACK);
		var defaultColor2 = new Color(255, 204, 128);
		var hoverColor2 = new Color(244, 164, 96);
		panel.add(btnChoKy);

		btnChoKy.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnChoKy.setBackground(hoverColor2); // Đổi màu khi hover
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnChoKy.setBackground(defaultColor2); // Trả lại màu gốc
			}
		});

		btnBaoTri = new JButton("Đang Bảo Trì");
		btnBaoTri.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBaoTri.setBounds(605, 11, 147, 38);
		btnBaoTri.setBackground(new Color(224, 224, 224)); // Xám nhạt
		btnBaoTri.setForeground(Color.BLACK); // Chữ đen
		var defaultColor3 = new Color(224, 224, 224);
		var hoverColor3 = new Color(139, 137, 137);
		panel.add(btnBaoTri);

		btnBaoTri.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBaoTri.setBackground(hoverColor3); // Đổi màu khi hover
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBaoTri.setBackground(defaultColor3); // Trả lại màu gốc
			}
		});

		btnDonDep = new JButton("Chờ Dọn Dẹp");
		btnDonDep.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDonDep.setBounds(787, 11, 147, 38);
		btnDonDep.setBackground(new Color(255, 245, 157)); // Vàng nhạt
		btnDonDep.setForeground(Color.BLACK); // Chữ đen
		var defaultColor4 = new Color(255, 245, 157);
		var hoverColor4 = new Color(255, 250, 55);
		panel.add(btnDonDep);

		// Tạo JScrollPane
		var scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 82, 956, 740); // Kích thước giống Floor
		contentPane.add(scrollPane);

		// Đặt JPanel Floor vào JScrollPane
		Floor = new JPanel();
		Floor.setBorder(new LineBorder(new Color(128, 128, 0)));
		Floor.setLayout(null);
		Floor.setPreferredSize(new Dimension(956, 1200)); // Kích thước đủ lớn để có thể cuộn
		scrollPane.setViewportView(Floor); // Thêm Floor vào JScrollPane

		// Floor Label
		lblFloor1 = new JLabel("Floor 1");
		lblFloor1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFloor1.setFont(new Font("Arial", Font.BOLD, 12));
		lblFloor1.setBounds(25, 11, 75, 21);
		Floor.add(lblFloor1);

		btnDonDep.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnDonDep.setBackground(hoverColor4); // Đổi màu khi hover
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnDonDep.setBackground(defaultColor4); // Trả lại màu gốc
			}
		});
		loadApartmentsToGUI();
	}

	// Phương thức thêm hiệu ứng hover
	private void addHoverEffect(JButton button) {
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(96, 144, 144)); // Màu khi hover
				button.setOpaque(true);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(64, 128, 128)); // Quay về màu nền gốc
				button.setOpaque(false);
			}
		});

	}

	private Color getStatusColor(int status) {
		return switch (status) {
		case 1 -> new Color(144, 202, 249);
		case 2 -> new Color(165, 214, 167);
		case 3 -> new Color(255, 204, 128);
		case 4 -> new Color(224, 224, 224);
		case 5 -> new Color(255, 245, 157);
		default -> Color.WHITE;
		};
	}

	private void loadApartmentsToGUI() {
		var dao = new ApartmentDAO();
		var apartments = dao.getApartmentsByFloor();

		Floor.removeAll();
		Floor.revalidate();
		Floor.repaint();

		var yPos = 10;
		var floorNumber = -1;
		JPanel floorPanel = null;

		for (Apartment apt : apartments) {
			if (apt.getFloorID() != floorNumber) {
				floorNumber = apt.getFloorID();
				var floorLabel = new JLabel("Floor " + floorNumber);
				floorLabel.setFont(new Font("Arial", Font.BOLD, 14));
				floorLabel.setBounds(10, yPos, 200, 20);
				Floor.add(floorLabel);
				yPos += 30;

				floorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
				floorPanel.setBounds(10, yPos, 900, 120);
				Floor.add(floorPanel);
				yPos += 130;
			}

			var roomPanel = new JPanel();
			roomPanel.setBorder(new TitledBorder(new EtchedBorder(), apt.getApartmentNumber()));
			roomPanel.setBackground(getStatusColor(apt.getApartmentsStatus()));
			roomPanel.setPreferredSize(new Dimension(150, 100));

			// Thêm menu chuột phải
			var popupMenu = new JPopupMenu();
			var itemSetStatus = new JMenuItem("Cập nhật trạng thái");
			popupMenu.add(itemSetStatus);

			itemSetStatus.addActionListener(e -> showStatusDialog(apt));

			roomPanel.setComponentPopupMenu(popupMenu);

			// Thêm sự kiện nhấn chuột trái để mở thông tin chủ sở hữu
			roomPanel.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mousePressed(java.awt.event.MouseEvent evt) {
					if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
						showOwnerInfo(apt.getApartmentID());
					}
				}
			});

			floorPanel.add(roomPanel);
		}
	}

	private void showStatusDialog(Apartment apartment) {
		String[] options = { "Căn hộ trống", "Đã cho thuê", "Chờ ký hợp đồng", "Đang bảo trì", "Chờ dọn dẹp" };
		var choice = JOptionPane.showOptionDialog(this,
				"Chọn trạng thái mới cho căn hộ " + apartment.getApartmentNumber() + ":", "Cập nhật trạng thái",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[apartment.getApartmentsStatus() - 1]);

		if (choice >= 0) {
			var dao = new ApartmentDAO();
			var updated = dao.updateApartmentStatus(apartment.getApartmentID(), choice + 1);
			if (updated) {
				JOptionPane.showMessageDialog(this, "Cập nhật trạng thái thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadApartmentsToGUI(); // Reload giao diện
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void showOwnerInfo(int apartmentID) {
		var memberDAO = new MemberDAO();
		var owner = memberDAO.getOwnerByApartmentID(apartmentID);

		if (owner != null) {
			new ApartmentOwnerInfoFrame(owner);
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy chủ sở hữu!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
