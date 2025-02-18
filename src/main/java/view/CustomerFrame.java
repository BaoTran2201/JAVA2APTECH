package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Dao.ApartmentDAO;
import Dao.MemberDAO;
import model.Member;

public class CustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtCMND, txtName, txtCountry, txtDOB, txtEmail, txtQuantity, txtPhone, txtStartDate, txtEndDate,
			txtAvatar;
	private JComboBox<String> cbGender, cbApartments;
	private MemberDAO memberDAO;
	private ApartmentDAO apartmentDAO;

	public CustomerFrame() {
		setTitle("Thêm chủ sở hữu căn hộ");
		setBounds(100, 100, 1292, 889);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		memberDAO = new MemberDAO();
		apartmentDAO = new ApartmentDAO();

		var titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(new Color(64, 128, 128));

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
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBack.setForeground(new Color(200, 200, 200));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBack.setForeground(Color.WHITE);
			}
		});

		// Tiêu đề
		var lblTitle = new JLabel("Thêm chủ sở hữu căn hộ", JLabel.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

		titlePanel.add(btnBack, BorderLayout.WEST);
		titlePanel.add(lblTitle, BorderLayout.CENTER);
		add(titlePanel, BorderLayout.NORTH);

		var formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
		formPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
		formPanel.setBackground(Color.WHITE);

		// Ô nhập liệu
		txtCMND = createTextField();
		txtName = createTextField();
		txtCountry = createTextField();
		txtDOB = createTextField();
		txtQuantity = createTextField();
		cbGender = new JComboBox<>(new String[] { "Nam", "Nữ" });
		txtPhone = createTextField();
		txtEmail = createTextField();
		txtStartDate = createTextField();
		txtEndDate = createTextField();
		txtAvatar = createTextField();
		cbApartments = new JComboBox<>();
		loadApartments();

		addFormRow(formPanel, "CMND:", txtCMND);
		addFormRow(formPanel, "Tên:", txtName);
		addFormRow(formPanel, "Quốc tịch:", txtCountry);
		addFormRow(formPanel, "Ngày sinh:", txtDOB);
		addFormRow(formPanel, "Số lượng thành viên:", txtQuantity);
		addFormRow(formPanel, "Giới tính:", cbGender);
		addFormRow(formPanel, "Số điện thoại:", txtPhone);
		addFormRow(formPanel, "Email:", txtEmail);
		addFormRow(formPanel, "Ngày bắt đầu thuê:", txtStartDate);
		addFormRow(formPanel, "Ngày hết hợp đồng:", txtEndDate);
		addFormRow(formPanel, "Ảnh đại diện (URL):", txtAvatar);
		addFormRow(formPanel, "Chọn căn hộ:", cbApartments);

		add(formPanel, BorderLayout.CENTER);

		var buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		buttonPanel.setBackground(new Color(64, 128, 128));

		var btnSave = new JButton("Lưu");
		var btnCancel = new JButton("Hủy");

		styleButton(btnSave, new Color(0, 153, 76), new Color(0, 102, 51));
		styleButton(btnCancel, new Color(255, 102, 102), new Color(204, 0, 0));

		btnSave.addActionListener(e -> saveOwner());
		btnCancel.addActionListener(e -> dispose());

		buttonPanel.add(btnSave);
		buttonPanel.add(btnCancel);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void loadApartments() {
		var apartments = apartmentDAO.getAvailableApartments();
		for (String apt : apartments) {
			cbApartments.addItem(apt);
		}
	}

	private JTextField createTextField() {
		var textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		return textField;
	}

	private void addFormRow(JPanel panel, String label, JComponent field) {
		var lbl = new JLabel(label);
		lbl.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(lbl);
		panel.add(field);
	}

	private void styleButton(JButton button, Color bgColor, Color borderColor) {
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBackground(bgColor);
		button.setForeground(Color.WHITE);
		button.setBorder(new LineBorder(borderColor, 2, true));
		button.setFocusPainted(false);
	}

	private void saveOwner() {
		try {
			var cmnd = Integer.parseInt(txtCMND.getText().trim());
			var name = txtName.getText().trim();
			var country = txtCountry.getText().trim();
			var dob = Date.valueOf(txtDOB.getText().trim());
			var quantity = Integer.parseInt(txtQuantity.getText().trim());
			var gender = cbGender.getSelectedIndex() == 0;
			var phone = txtPhone.getText().trim();
			var email = txtEmail.getText().trim();
			var startDate = Date.valueOf(txtStartDate.getText().trim());
			var endDate = txtEndDate.getText().trim().isEmpty() ? null : Date.valueOf(txtEndDate.getText().trim());
			var avatar = txtAvatar.getText().trim();
			var apartmentID = Integer.parseInt(cbApartments.getSelectedItem().toString().split("-")[0]);

			var newMember = new Member(cmnd, name, avatar, country, dob, startDate, endDate, quantity, phone, email, 0,
					gender, apartmentID, true);
			var success = memberDAO.addMember(newMember);

			if (success) {
				JOptionPane.showMessageDialog(this, "Thêm chủ sở hữu thành công!", "Thành công",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Thêm thất bại, vui lòng kiểm tra lại!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng!", "Lỗi nhập liệu",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
