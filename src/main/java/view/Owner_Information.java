package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import Dao.MemberDAO;
import model.Member;
public class Owner_Information extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
	private JLabel lblInfor;
	private JPanel Owner;
	private JLabel lblOwnerInformation;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblName;
	private JTextField textName;
	private JLabel lblGender;
	private JLabel lblDoB;
	private JTextField textPhoneNumber;
	private JLabel lblPhoneNumber;
	private JLabel lblNationality;
	private JLabel lblIDNumber;
	private JTextField textIDNumber;
	private JLabel lblIDPhoto;
	private JLabel lblpicture;
	private JPanel ContractInformation;
	private JLabel lblContractInformation;
	private JSeparator separator_3;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JLabel lblMembers;
	private JTextField textStartDate;
	private JTextField textEndDate;
	private JTextField textMembers;
	private JButton btnBack;
	private int userID;
	private JLabel lblCccd;
	private JTextField textcccd;
	private JTextField textCountry;
	private JDateChooser dateChooser;
	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	private JButton btnChooseImage;
	String filename = null; // tên file ảnh chọn từ dialog
	String fileold = null; // tên file ảnh cũ
	String dirlocal = null; // đường dẫn chọn hình ảnh dưới máy các bạn
	String dirserver = null; // đường dẫn up hình ảnh trên server

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new Owner_Information(2);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Owner_Information(int userID) {
		this.userID = userID;
		setTitle("Owner_Infor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

		lblInfor = new JLabel("INFORMATION");
        lblInfor.setForeground(new Color(0, 128, 128));
        lblInfor.setFont(new Font("Arial", Font.BOLD, 25));
        lblInfor.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfor.setBounds(0, 11, 1276, 70);
        contentPane.add(lblInfor);

		Owner = new JPanel();
        Owner.setBackground(new Color(0, 128, 128));
		Owner.setBounds(140, 108, 966, 361);
        contentPane.add(Owner);
        Owner.setLayout(null);

		lblOwnerInformation = new JLabel("Owner Information");
        lblOwnerInformation.setForeground(Color.WHITE);
        lblOwnerInformation.setFont(new Font("Arial", Font.BOLD, 20));
        lblOwnerInformation.setHorizontalAlignment(SwingConstants.CENTER);
        lblOwnerInformation.setBounds(10, 11, 946, 38);
        Owner.add(lblOwnerInformation);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(482, 60, 9, 272);
		Owner.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(40, 47, 880, 2);
		Owner.add(separator_1);

		lblName = new JLabel("Full Name");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Arial", Font.BOLD, 17));
		lblName.setBounds(70, 60, 77, 31);
		Owner.add(lblName);

		textName = new JTextField();
		textName.setFont(new Font("Arial", Font.PLAIN, 17));
		textName.setBounds(205, 60, 253, 31);
		Owner.add(textName);
		textName.setColumns(10);

		lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Arial", Font.BOLD, 17));
		lblGender.setBounds(70, 144, 77, 31);
		Owner.add(lblGender);

		radioMale = new JRadioButton("Male");
		radioMale.setFont(new Font("Arial", Font.PLAIN, 17));
		radioMale.setBounds(205, 144, 80, 31);
		Owner.add(radioMale);

		radioFemale = new JRadioButton("Female");
		radioFemale.setFont(new Font("Arial", Font.PLAIN, 17));
		radioFemale.setBounds(295, 144, 100, 31);
		Owner.add(radioFemale);

		var genderGroup = new ButtonGroup();
		genderGroup.add(radioMale);
		genderGroup.add(radioFemale);

		lblDoB = new JLabel("Date of Birth");
		lblDoB.setForeground(Color.WHITE);
		lblDoB.setFont(new Font("Arial", Font.BOLD, 17));
		lblDoB.setBounds(70, 234, 107, 31);
		Owner.add(lblDoB);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.getCalendarButton();
		dateChooser.setBounds(205, 234, 253, 31);
		Owner.add(dateChooser);

		textPhoneNumber = new JTextField();
		textPhoneNumber.setFont(new Font("Arial", Font.PLAIN, 17));
		textPhoneNumber.setColumns(10);
		textPhoneNumber.setBounds(205, 281, 253, 31);
		Owner.add(textPhoneNumber);

		lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 17));
		lblPhoneNumber.setBounds(70, 281, 125, 31);
		Owner.add(lblPhoneNumber);

		lblNationality = new JLabel("Country");
		lblNationality.setFont(new Font("Arial", Font.BOLD, 17));
		lblNationality.setForeground(new Color(255, 255, 255));
		lblNationality.setBounds(70, 186, 86, 31);
		Owner.add(lblNationality);

		lblIDNumber = new JLabel("National ID Number");
		lblIDNumber.setForeground(Color.WHITE);
		lblIDNumber.setFont(new Font("Arial", Font.BOLD, 17));
		lblIDNumber.setBounds(501, 72, 161, 31);
		Owner.add(lblIDNumber);

		textIDNumber = new JTextField();
		textIDNumber.setFont(new Font("Arial", Font.PLAIN, 17));
		textIDNumber.setColumns(10);
		textIDNumber.setBounds(685, 72, 235, 31);
		Owner.add(textIDNumber);

		lblIDPhoto = new JLabel("National ID Photo");
		lblIDPhoto.setForeground(Color.WHITE);
		lblIDPhoto.setFont(new Font("Arial", Font.BOLD, 17));
		lblIDPhoto.setBounds(501, 127, 161, 31);
		Owner.add(lblIDPhoto);

		lblpicture = new JLabel("\r\n");
		lblpicture.setOpaque(true);
		lblpicture.setBackground(new Color(255, 255, 255));
		lblpicture.setBounds(652, 127, 268, 185);
		Owner.add(lblpicture);

		lblCccd = new JLabel("CCCD");
		lblCccd.setForeground(Color.WHITE);
		lblCccd.setFont(new Font("Arial", Font.BOLD, 17));
		lblCccd.setBounds(70, 102, 77, 31);
		Owner.add(lblCccd);

		textcccd = new JTextField();
		textcccd.setFont(new Font("Arial", Font.PLAIN, 17));
		textcccd.setColumns(10);
		textcccd.setBounds(205, 102, 253, 31);
		Owner.add(textcccd);

		textCountry = new JTextField();
		textCountry.setFont(new Font("Arial", Font.PLAIN, 17));
		textCountry.setColumns(10);
		textCountry.setBounds(205, 188, 253, 31);
		Owner.add(textCountry);

		btnChooseImage = new JButton("Choose Image");
		btnChooseImage.addActionListener(this::btnChooseImageActionPerformed);
		btnChooseImage.setForeground(new Color(0, 128, 128));
		btnChooseImage.setFont(new Font("Arial", Font.BOLD, 15));
		btnChooseImage.setBackground(Color.WHITE);
		btnChooseImage.setBounds(652, 323, 268, 30);
		Owner.add(btnChooseImage);

		ContractInformation = new JPanel();
		ContractInformation.setBackground(new Color(0, 128, 128));
		ContractInformation.setBounds(140, 526, 973, 214);
		contentPane.add(ContractInformation);
		ContractInformation.setLayout(null);

		lblContractInformation = new JLabel("Contract Information");
		lblContractInformation.setFont(new Font("Arial", Font.BOLD, 20));
		lblContractInformation.setForeground(new Color(255, 255, 255));
		lblContractInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblContractInformation.setBounds(10, 11, 953, 38);
		ContractInformation.add(lblContractInformation);

		separator_3 = new JSeparator();
		separator_3.setBounds(51, 47, 880, 2);
		ContractInformation.add(separator_3);

		lblStartDate = new JLabel("Contract Renewal Start Date");
		lblStartDate.setFont(new Font("Arial", Font.BOLD, 17));
		lblStartDate.setForeground(new Color(255, 255, 255));
		lblStartDate.setBounds(167, 75, 231, 31);
		ContractInformation.add(lblStartDate);

		lblEndDate = new JLabel("Contract Expiry Date");
		lblEndDate.setForeground(Color.WHITE);
		lblEndDate.setFont(new Font("Arial", Font.BOLD, 17));
		lblEndDate.setBounds(167, 117, 231, 31);
		ContractInformation.add(lblEndDate);

		lblMembers = new JLabel("Number of Members");
		lblMembers.setForeground(Color.WHITE);
		lblMembers.setFont(new Font("Arial", Font.BOLD, 17));
		lblMembers.setBounds(167, 159, 231, 31);
		ContractInformation.add(lblMembers);

		textStartDate = new JTextField();
		textStartDate.setBounds(492, 75, 281, 31);
		ContractInformation.add(textStartDate);
		textStartDate.setColumns(10);

		textEndDate = new JTextField();
		textEndDate.setColumns(10);
		textEndDate.setBounds(492, 117, 281, 31);
		ContractInformation.add(textEndDate);

		textMembers = new JTextField();
		textMembers.setColumns(10);
		textMembers.setBounds(492, 159, 281, 31);
		ContractInformation.add(textMembers);

		btnBack = new JButton("◄ Back");
		btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setForeground(new Color(0, 128, 128));
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setBounds(50, 750, 100, 30);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
			dispose();
			var mainFrame = new CustomerPage(userID);
			mainFrame.setVisible(true);
		});
		var btnSave = new JButton("Save");
		btnSave.addActionListener(this::btnSaveActionPerformed);
		btnSave.setBackground(Color.WHITE);
		btnSave.setForeground(new Color(0, 128, 128));
		btnSave.setFont(new Font("Arial", Font.BOLD, 15));
		btnSave.setBounds(200, 750, 100, 30);
		contentPane.add(btnSave);
		loadMemberData(userID);
		textMembers.setEditable(false);
		textStartDate.setEditable(false);
		textEndDate.setEditable(false);
    }

	public void loadMemberData(int userID) {
		try {
			var memberDAO = new MemberDAO();
			var member = memberDAO.getMemberByID(userID);
			System.out.println(member);
			if (member != null) {
				textName.setText(member.getMemberName());
				textPhoneNumber.setText(member.getPhone());
				textcccd.setText(member.getCccd());
				textCountry.setText(member.getCountry());
				if (member.isGender()) {
					radioMale.setSelected(true);
				} else {
					radioFemale.setSelected(true);
				}
				dateChooser.setDate(java.sql.Date.valueOf(member.getDob()));
				textStartDate.setText(member.getStartDate().toString());
				textEndDate.setText(member.getEndDate().toString());
				textMembers.setText(String.valueOf(member.getQuantity()));

				// Hiển thị avatar nếu có
				if (member.getAvatar() != null && !member.getAvatar().isEmpty()) {
					var avatarIcon = new ImageIcon(member.getAvatar());
					lblpicture.setIcon(avatarIcon); // Đặt vào label tương ứng
				} else {
					lblpicture.setIcon(null);
				}

				// Hiển thị trạng thái thành viên
//				var statusText = member.isMemberStatus() ? "Active" : "Inactive";
//				JOptionPane.showMessageDialog(null, "Member Status: " + statusText, "Info",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy thành viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu thành viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void btnSaveActionPerformed(ActionEvent e) {
		try {
			if (textName.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (textPhoneNumber.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (textcccd.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập CCCD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (textCountry.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập quốc gia!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (dateChooser.getDate() == null) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			var memberDAO = new MemberDAO();
			var member = new Member();

			member.setMemberID(userID);

			member.setMemberName(textName.getText().trim());
			member.setPhone(textPhoneNumber.getText().trim());
			member.setCccd(textcccd.getText().trim());
			member.setCountry(textCountry.getText().trim());
			member.setGender(radioMale.isSelected());

			// Lấy ngày sinh từ dateChooser và chuyển về LocalDate (yyyy-MM-dd)
			var utilDate = dateChooser.getDate();
			var sdf = new SimpleDateFormat("yyyy-MM-dd");
			var dobString = sdf.format(utilDate); // Chuyển thành chuỗi "yyyy-MM-dd"
			var dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			member.setDob(dob);
			if (filename != null) {
				var dirserver = System.getProperty("user.dir") + "\\images";
				var pathlocal = Paths.get(dirlocal);
				var pathserver = Paths.get(dirserver);
				try {
					Files.copy(pathlocal, pathserver.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
					member.setAvatar("images/" + filename);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			} else {
				member.setAvatar(fileold);
			}
			// Gọi DAO để cập nhật theo ID
			var success = memberDAO.updateMemberByID(member);
			System.out.println("ID: " + member.getMemberID());
			System.out.println("Name: " + member.getMemberName());
			System.out.println("Phone: " + member.getPhone());
			System.out.println("CCCD: " + member.getCccd());
			System.out.println("Country: " + member.getCountry());
			System.out.println("Gender: " + member.isGender());
			System.out.println("DOB: " + member.getDob());
			System.out.println("Avatar Path: " + member.getAvatar());
			if (success) {
				JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật thành công!", "Thành công",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		filename = null;
		fileold = null;
		dirlocal = null;
		dirserver = null;
	}

	protected void btnChooseImageActionPerformed(ActionEvent e) {
		var fc = new JFileChooser("C:\\Users\\lebao");
		fc.setDialogTitle("Choose an image file");

		fc.setFileFilter(
				new FileNameExtensionFilter("Image files(jpg, png, gif, bmp)", "jpg", "jpeg", "png", "gif", "bmp"));

		fc.setAcceptAllFileFilterUsed(false);
		var result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			var f = fc.getSelectedFile();
			// design pattern: guard clause => null check
			if (f.length() > 5 * 1024 * 1024) {
				JOptionPane.showMessageDialog(this, "File <= 5mb");
				return;
			}
			filename = f.getName(); // tên file mới
			dirlocal = f.getAbsolutePath(); // đường dẫn hình trên máy khách
			lblpicture.setIcon(new ImageIcon(
					new ImageIcon(f.getAbsolutePath()).getImage().getScaledInstance(135, 113, Image.SCALE_SMOOTH)));
		}
	}
}
