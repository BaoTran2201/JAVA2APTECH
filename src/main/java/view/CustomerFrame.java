package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CustomerFrame extends JFrame {
	public CustomerFrame() {
		setTitle("Quản Lý Khách Hàng");
		setBounds(100, 100, 1292, 889);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// ====== Tiêu đề có nút Back ======
		JPanel panelTitle = new JPanel(new BorderLayout()); // Tạo panel chứa tiêu đề và nút back
		panelTitle.setBackground(new Color(64, 128, 128)); // Đặt màu nền giống tiêu đề

		JButton btnBack = new JButton("◄ Back"); // Tạo nút Back
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE); // Màu chữ trắng
		btnBack.setBackground(new Color(64, 128, 128)); // Đặt màu nền giống thanh tiêu đề
		btnBack.setBorder(null); // Xóa viền
		btnBack.setFocusPainted(false); // Tắt viền khi chọn
		btnBack.setContentAreaFilled(false); // Xóa nền nút
		btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Đổi con trỏ chuột
		btnBack.addActionListener(e -> dispose()); // Đóng cửa sổ khi nhấn
		// Xử lý sự kiện click nút Home
				btnBack.addActionListener(e -> {
				    // Tạo và hiển thị trang HomeScreen
				    new QuanLyChungCuGUI().setVisible(true); // Trang chủ HomeScreen sẽ được mở
				    dispose(); // Đóng cửa sổ hiện tại (quản lý chung cư)
				});
		// ====== Hiệu ứng hover ======
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnBack.setForeground(new Color(200, 200, 200)); // Đổi màu chữ khi hover
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnBack.setForeground(Color.WHITE); // Trả về màu chữ ban đầu
		    }
		});
		
		JLabel lblTitle = new JLabel("Quản Lý Khách Hàng", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(64, 128, 128));
		lblTitle.setForeground(Color.WHITE);

		panelTitle.add(btnBack, BorderLayout.WEST); // Nút Back bên trái
		panelTitle.add(lblTitle, BorderLayout.CENTER); // Tiêu đề ở giữa

		getContentPane().add(panelTitle, BorderLayout.NORTH); // Thêm vào cửa sổ chính


		// ====== Bảng khách hàng ======
		String[] columnNames = { "Mã KH", "Tên", "SĐT", "Căn hộ thuê" };
		Object[][] data = { { "KH001", "Nguyễn Văn A", "0123456789", "A101" },
				{ "KH002", "Trần Thị B", "0987654321", "B202" }, { "KH003", "Lê Văn C", "0367891234", "C303" } };
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// ====== Form nhập dữ liệu ======
		JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 hàng, 2 cột, khoảng cách 10px
		panelForm.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		panelForm.add(new JLabel("Mã KH:"));
		JTextField txtMaKH = new JTextField();
		panelForm.add(txtMaKH);

		panelForm.add(new JLabel("Tên:"));
		JTextField txtTenKH = new JTextField();
		panelForm.add(txtTenKH);

		panelForm.add(new JLabel("SĐT:"));
		JTextField txtSDT = new JTextField();
		panelForm.add(txtSDT);

		panelForm.add(new JLabel("Căn hộ thuê:"));
		JTextField txtCanHo = new JTextField();
		panelForm.add(txtCanHo);

		getContentPane().add(panelForm, BorderLayout.WEST);

		// ====== Nút hành động ======
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(64, 128, 128));
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setBackground(new Color(255, 255, 255));
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setBackground(new Color(255, 255, 255));
		JButton btnDelete = new JButton("Xóa");
		btnDelete.setBackground(new Color(255, 255, 255));
		panelButtons.add(btnAdd);
		panelButtons.add(btnEdit);
		panelButtons.add(btnDelete);
		getContentPane().add(panelButtons, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void main(String[] args) {
		new CustomerFrame();
	}
}
