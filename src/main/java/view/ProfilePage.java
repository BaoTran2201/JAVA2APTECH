package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ProfilePage extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int userID;

	public ProfilePage() {
        setTitle("User Profile");
        setSize(1292, 889);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Panel chính
        var mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 128, 128));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(null);

        // Avatar (JLabel hình tròn)
        var avatarLabel = new AvatarLabel();
        avatarLabel.setBounds(516, 0, 186, 186);
        avatarLabel.setPreferredSize(new Dimension(100, 100)); // Kích thước hình vuông
        avatarLabel.setOpaque(true);
        avatarLabel.setBackground(new Color(255, 255, 255)); // Màu nền của avatar
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        var avatarPanel = new JPanel();
        avatarPanel.setBounds(20, 20, 1234, 196);
        avatarPanel.setBackground(new Color(0, 128, 128));
        avatarPanel.setPreferredSize(new Dimension(100, 100)); // Đảm bảo panel có kích thước phù hợp
        avatarPanel.setLayout(null);
        avatarPanel.add(avatarLabel);
        mainPanel.add(avatarPanel);

        // Panel thông tin cá nhân
        var infoPanel = new JPanel();
        infoPanel.setBounds(20, 255, 1234, 290);
        infoPanel.setBackground(new Color(0, 128, 128));
        infoPanel.setLayout(null);

        // Thêm các thành phần vào infoPanel
        var lblFullName = new JLabel("Full Name");
        lblFullName.setBounds(0, 0, 612, 40);
        lblFullName.setHorizontalAlignment(SwingConstants.CENTER);
        lblFullName.setForeground(new Color(255, 255, 255));
        lblFullName.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(lblFullName);
        var textFullname = new JTextField("");
        textFullname.setBounds(622, 0, 612, 40);
        textFullname.setFont(new Font("Arial", Font.PLAIN, 11));
        infoPanel.add(textFullname);

        var llblGender = new JLabel("Gender");
        llblGender.setBackground(new Color(0, 128, 128));
        llblGender.setBounds(0, 50, 612, 40);
        llblGender.setHorizontalAlignment(SwingConstants.CENTER);
        llblGender.setFont(new Font("Arial", Font.BOLD, 20));
        llblGender.setForeground(new Color(255, 255, 255));
        infoPanel.add(llblGender);
        var textGender = new JTextField("");
        textGender.setBounds(622, 50, 612, 40);
        textGender.setFont(new Font("Arial", Font.PLAIN, 11));
        infoPanel.add(textGender);

        var lblDate = new JLabel("Date of Birth");
        lblDate.setBounds(0, 100, 612, 40);
        lblDate.setForeground(new Color(255, 255, 255));
        lblDate.setFont(new Font("Arial", Font.BOLD, 20));
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(lblDate);
        var textDOB = new JTextField("");
        textDOB.setBounds(622, 100, 612, 40);
        textDOB.setFont(new Font("Arial", Font.PLAIN, 11));
        infoPanel.add(textDOB);

        var lblCountry = new JLabel("Country");
        lblCountry.setBounds(0, 150, 612, 40);
        lblCountry.setFont(new Font("Arial", Font.BOLD, 20));
        lblCountry.setForeground(new Color(255, 255, 255));
        lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(lblCountry);
        var textCountry = new JTextField("");
        textCountry.setBounds(622, 150, 612, 40);
        textCountry.setFont(new Font("Arial", Font.PLAIN, 11));
        infoPanel.add(textCountry);

        var lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setBounds(0, 200, 612, 40);
        lblPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhoneNumber.setForeground(new Color(255, 255, 255));
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(lblPhoneNumber);
        var textPhone = new JTextField("");
        textPhone.setBounds(622, 200, 612, 40);
        textPhone.setFont(new Font("Arial", Font.PLAIN, 11));
        infoPanel.add(textPhone);

        var lblEmail = new JLabel("Email");
        lblEmail.setBounds(0, 250, 612, 40);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
        lblEmail.setForeground(new Color(255, 255, 255));
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(lblEmail);
        var textEmail = new JTextField("");
        textEmail.setBounds(622, 250, 612, 40);
        textEmail.setFont(new Font("Arial", Font.PLAIN, 11));
        infoPanel.add(textEmail);

        mainPanel.add(infoPanel);

        // Panel thông tin căn hộ
        var apartmentPanel = new JPanel();
        apartmentPanel.setBounds(20, 556, 1234, 222);
        apartmentPanel.setBackground(new Color(0, 128, 128));
        apartmentPanel.setLayout(null);

        // Thêm các thành phần vào apartmentPanel
        var lblAID = new JLabel("Apartment ID");
        lblAID.setBounds(0, 11, 612, 48);
        lblAID.setHorizontalAlignment(SwingConstants.CENTER);
        lblAID.setFont(new Font("Arial", Font.BOLD, 20));
        lblAID.setHorizontalAlignment(SwingConstants.CENTER);
        lblAID.setForeground(new Color(255, 255, 255));
        apartmentPanel.add(lblAID);
        var textAID = new JTextField("");
        textAID.setBounds(622, 0, 612, 48);
        textAID.setFont(new Font("Arial", Font.PLAIN, 11));
        apartmentPanel.add(textAID);

        var lblApartmentS = new JLabel("Apartment Status");
        lblApartmentS.setBounds(0, 58, 612, 48);
        lblApartmentS.setForeground(new Color(255, 255, 255));
        lblApartmentS.setFont(new Font("Arial", Font.BOLD, 20));
        lblApartmentS.setHorizontalAlignment(SwingConstants.CENTER);
        apartmentPanel.add(lblApartmentS);
        var textAS = new JTextField("");
        textAS.setBounds(622, 58, 612, 48);
        textAS.setFont(new Font("Arial", Font.PLAIN, 11));
        apartmentPanel.add(textAS);

        var lblStart = new JLabel("Start Date");
        lblStart.setBounds(0, 116, 612, 48);
        lblStart.setFont(new Font("Arial", Font.BOLD, 20));
        lblStart.setForeground(new Color(255, 255, 255));
        lblStart.setHorizontalAlignment(SwingConstants.CENTER);
        apartmentPanel.add(lblStart);
        var textSD = new JTextField("");
        textSD.setBounds(622, 116, 612, 48);
        textSD.setFont(new Font("Arial", Font.PLAIN, 11));
        apartmentPanel.add(textSD);

        var lblMember = new JLabel("Member Status");
        lblMember.setBounds(0, 174, 612, 48);
        lblMember.setFont(new Font("Arial", Font.BOLD, 20));
        lblMember.setForeground(new Color(255, 255, 255));
        lblMember.setHorizontalAlignment(SwingConstants.CENTER);
        apartmentPanel.add(lblMember);
        var textMS = new JTextField("");
        textMS.setBounds(622, 174, 612, 48);
        textMS.setFont(new Font("Arial", Font.PLAIN, 11));
        apartmentPanel.add(textMS);

        mainPanel.add(apartmentPanel);

        // Panel nút bấm
        var buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 789, 1234, 59);
        buttonPanel.setBackground(new Color(0, 128, 128));
        var btnEdit = new JButton("Edit");
        btnEdit.setBackground(new Color(255, 255, 255));
        btnEdit.setFont(new Font("Arial", Font.PLAIN, 12));
        btnEdit.setBounds(239, 5, 139, 43);
        var btnSave = new JButton("Save");
        btnSave.setFont(new Font("Arial", Font.PLAIN, 12));
        btnSave.setBackground(new Color(255, 255, 255));
        btnSave.setBounds(533, 5, 139, 43);
        var btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Arial", Font.PLAIN, 12));
        btnExit.setBackground(new Color(255, 255, 255));
        btnExit.setBounds(809, 5, 139, 43);

        // Sự kiện cho nút Exit
        btnExit.addActionListener(e -> {
		    dispose(); // Đóng ProfilePage
			new CustomerPage(userID).setVisible(true); // Mở CustomerPage
		});

        buttonPanel.setLayout(null);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnExit);

        mainPanel.add(buttonPanel);

        // Thêm panel chính vào cửa sổ
        var scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0, 0, 1276, 850);
        getContentPane().add(scrollPane);

        setVisible(true);
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(ProfilePage::new);
	}

    // Lớp con AvatarLabel để vẽ hình tròn
    static class AvatarLabel extends JLabel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Đảm bảo chỉ vẽ trong hình tròn
            var size = Math.min(getWidth(), getHeight());
            var g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getBackground());
            g2d.fillOval(0, 0, size, size);  // Vẽ hình tròn trong vùng vuông
        }
    }
}
