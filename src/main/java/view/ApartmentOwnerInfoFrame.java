package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Member;

public class ApartmentOwnerInfoFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ApartmentOwnerInfoFrame(Member owner) {
		setTitle("Thông tin chủ sở hữu căn hộ");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Panel tiêu đề
		var titlePanel = new JPanel();
		titlePanel.setBackground(new Color(64, 128, 128));
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		var lblTitle = new JLabel("Thông tin chủ sở hữu", SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.add(lblTitle);
		add(titlePanel, BorderLayout.NORTH);

		// Panel nội dung chính
		var contentPanel = new JPanel(new GridLayout(10, 2, 10, 10));
		contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		contentPanel.setBackground(Color.WHITE);

		// Cấu trúc hiển thị thông tin chủ sở hữu
		addRow(contentPanel, "Tên chủ sở hữu:", owner.getMemberName());
		addRow(contentPanel, "Chứng minh nhân dân:", String.valueOf(owner.getMemberID()));
		addRow(contentPanel, "Quốc tịch:", owner.getCountry());
		addRow(contentPanel, "Ngày sinh:", owner.getDob() != null ? owner.getDob().toString() : "Không có dữ liệu");
		addRow(contentPanel, "Ngày bắt đầu thuê:",
				owner.getStartDate() != null ? owner.getStartDate().toString() : "Không có dữ liệu");
		addRow(contentPanel, "Ngày hết hợp đồng:",
				owner.getEndDate() != null ? owner.getEndDate().toString() : "Chưa có");
		addRow(contentPanel, "Số lượng thành viên:", String.valueOf(owner.getQuantity()));
		addRow(contentPanel, "Email:", owner.getEmail());
		addRow(contentPanel, "Giới tính:", owner.isGender() ? "Nam" : "Nữ");

		add(contentPanel, BorderLayout.CENTER);

		// Panel hiển thị ảnh đại diện
		if (owner.getAvatar() != null && !owner.getAvatar().isEmpty()) {
			var avatarPanel = new JPanel();
			avatarPanel.setBackground(Color.WHITE);
			var lblAvatar = new JLabel();
			lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
			var icon = new ImageIcon(owner.getAvatar());
			lblAvatar.setIcon(new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			avatarPanel.add(lblAvatar);
			add(avatarPanel, BorderLayout.WEST);
		}

		// Panel chứa nút quay lại
		var buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		var btnClose = new JButton("Đóng");
		btnClose.setFont(new Font("Arial", Font.BOLD, 14));
		btnClose.setBackground(new Color(255, 102, 102)); // Màu đỏ nhạt
		btnClose.setForeground(Color.WHITE);
		btnClose.setFocusPainted(false);
		btnClose.setBorder(new LineBorder(new Color(255, 77, 77), 1, true));

		btnClose.addActionListener(e -> dispose());
		buttonPanel.add(btnClose);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	/**
	 * Thêm một dòng hiển thị thông tin.
	 */
	private void addRow(JPanel panel, String label, String value) {
		var lblKey = new JLabel(label);
		lblKey.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(lblKey);

		var lblValue = new JLabel(value);
		lblValue.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblValue);
	}
}
