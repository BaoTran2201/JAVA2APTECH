package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.User;

public class OwnerDetailPage extends JFrame {
	private static final long serialVersionUID = 1L;

	public OwnerDetailPage(User owner) {
		setTitle("Thông Tin Chủ Sở Hữu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		var panelMain = new JPanel(new GridLayout(10, 2, 10, 10));
		panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		panelMain.add(new JLabel("Họ và Tên:"));
		panelMain.add(new JLabel(owner.getMemberName()));

		panelMain.add(new JLabel("Số Điện Thoại:"));
		panelMain.add(new JLabel(owner.getPhone()));

		panelMain.add(new JLabel("Email:"));
		panelMain.add(new JLabel(owner.getEmail()));

		panelMain.add(new JLabel("Quốc Gia:"));
		panelMain.add(new JLabel(owner.getCountry()));

		panelMain.add(new JLabel("Căn Hộ:"));
		panelMain.add(new JLabel(owner.getApartmentNumber() + ""));

		panelMain.add(new JLabel("Ngày Sinh:"));
		panelMain.add(new JLabel(owner.getDob().toString()));

		panelMain.add(new JLabel("Ngày Bắt Đầu:"));
		panelMain.add(new JLabel(owner.getStartDate().toString()));

		panelMain.add(new JLabel("Ngày Kết Thúc:"));
		panelMain.add(new JLabel(owner.getEndDate().toString()));

		panelMain.add(new JLabel("Số Lượng Thành Viên:"));
		panelMain.add(new JLabel(owner.getQuantity() + ""));

		panelMain.add(new JLabel("Mã Xác Nhận:"));
		panelMain.add(new JLabel(owner.getVerifyCode() + ""));

		// Hiển thị ảnh
		var panelImage = new JPanel();
		panelImage.setLayout(new BorderLayout());
		var lblImage = new JLabel();
		lblImage.setHorizontalAlignment(JLabel.CENTER);

		if (owner.getIdentityImage() != null && !owner.getIdentityImage().isEmpty()) {
			var icon = new ImageIcon(owner.getIdentityImage());
			var img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			lblImage.setIcon(new ImageIcon(img));
		} else {
			lblImage.setText("Không có ảnh");
		}
		panelImage.add(lblImage, BorderLayout.CENTER);

		add(panelMain, BorderLayout.CENTER);
		add(panelImage, BorderLayout.SOUTH);

		setVisible(true);
	}
}
