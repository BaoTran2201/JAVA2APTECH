package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


class RoundedPanel extends JPanel {
    private int cornerRadius = 20; // Độ cong góc

    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2 = (Graphics2D) g.create();

        // Bật khử răng cưa
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Đổ bóng (màu đen mờ)
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, cornerRadius, cornerRadius);

        // Vẽ nền panel
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, cornerRadius, cornerRadius);

        g2.dispose();
    }
}

public class User extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RoundedPanel Profile, Contact, Rules, Feedback;
    private JLabel lblBanner_Customer;
    private JSeparator separator_5;
    private JLabel lblSlogan;
    private JLabel lblSlogan2;
    private JLabel lblBanner_Services;
    private JSeparator separator_6;
    private JLabel lblUnlockThePower;
    private view.RoundedPanel Notification;
    private JLabel lblNotification_Img_1;
    private JLabel lblNotification;
    private JSeparator separator_7;
    private view.RoundedPanel Logout;
    private JLabel lblLogout_Img;
    private JLabel lblLogout;
    private JSeparator separator_8;
    private JSeparator separator_9;
    private JLabel lblUser_Page;
    private view.RoundedPanel Services;
    private JLabel lblServices_Img;
    private JLabel lblServices;
    private JSeparator separator_10;
    private view.RoundedPanel Payment;
    private JLabel lblPayment_Img;
    private JLabel lblPayment;
    private JSeparator separator_11;
    private view.RoundedPanel Register_Temporary_Stay;
    private JLabel lblRegister_Temporary_Stay_Img;
    private JLabel lblRegister;
    private JSeparator separator_12;

	public User(int userID) {
        setTitle("Staff");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1292, 3000);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Header giữ nguyên
        var Header = new JPanel();
        Header.setBackground(Color.WHITE);
        Header.setBounds(132, 38, 1043, 270);
        contentPane.add(Header);
        Header.setLayout(null);

        var separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBackground(new Color(64, 128, 128));
        separator.setBounds(523, 63, 2, 196);
        Header.add(separator);

        lblBanner_Customer = new JLabel("CUSTOMER DASHBOARD");
        lblBanner_Customer.setFont(new Font("Arial", Font.BOLD, 30));
        lblBanner_Customer.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanner_Customer.setForeground(new Color(64, 128, 128));
        lblBanner_Customer.setBounds(10, 44, 503, 185);
        Header.add(lblBanner_Customer);

        separator_5 = new JSeparator();
        separator_5.setBackground(new Color(64, 128, 128));
        separator_5.setBounds(129, 156, 248, 14);
        Header.add(separator_5);

        lblSlogan = new JLabel("Your dashboard, your control. Let's make things happen!");
        lblSlogan.setBackground(new Color(255, 255, 255));
        lblSlogan.setForeground(new Color(64, 128, 128));
        lblSlogan.setFont(new Font("Arial", Font.BOLD, 12));
        lblSlogan.setBounds(100, 175, 330, 14);
        Header.add(lblSlogan);

        lblSlogan2 = new JLabel("Welcome back! Your personalized insights are ready");
        lblSlogan2.setForeground(new Color(64, 128, 128));
        lblSlogan2.setFont(new Font("Arial", Font.BOLD, 12));
        lblSlogan2.setBackground(Color.WHITE);
        lblSlogan2.setBounds(110, 200, 304, 14);
        Header.add(lblSlogan2);

        lblBanner_Services = new JLabel("SERVICES");
        lblBanner_Services.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanner_Services.setForeground(new Color(64, 128, 128));
        lblBanner_Services.setFont(new Font("Arial", Font.BOLD, 30));
        lblBanner_Services.setBounds(530, 44, 503, 185);
        Header.add(lblBanner_Services);

        separator_6 = new JSeparator();
        separator_6.setBackground(new Color(64, 128, 128));
        separator_6.setBounds(690, 156, 193, 14);
        Header.add(separator_6);

        lblUnlockThePower = new JLabel("Unlock the power of our services. Designed for you.");
        lblUnlockThePower.setForeground(new Color(64, 128, 128));
        lblUnlockThePower.setFont(new Font("Arial", Font.BOLD, 12));
        lblUnlockThePower.setBackground(Color.WHITE);
        lblUnlockThePower.setBounds(647, 175, 291, 14);
        Header.add(lblUnlockThePower);

        var lblTailoredSolutionsFor = new JLabel("Tailored solutions for your needs.");
        lblTailoredSolutionsFor.setForeground(new Color(64, 128, 128));
        lblTailoredSolutionsFor.setFont(new Font("Arial", Font.BOLD, 12));
        lblTailoredSolutionsFor.setBackground(Color.WHITE);
        lblTailoredSolutionsFor.setBounds(701, 200, 193, 14);
        Header.add(lblTailoredSolutionsFor);

        separator_9 = new JSeparator();
        separator_9.setBackground(new Color(64, 128, 128));
        separator_9.setBounds(78, 44, 860, 2);
        Header.add(separator_9);

        lblUser_Page = new JLabel("USER");
        lblUser_Page.setForeground(new Color(64, 128, 128));
        lblUser_Page.setFont(new Font("Arial", Font.BOLD, 34));
        lblUser_Page.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser_Page.setBounds(454, 0, 134, 44);
        Header.add(lblUser_Page);

        // Profile Panel
        Profile = new RoundedPanel();
        Profile.setBackground(Color.WHITE);
        Profile.setBounds(132, 336, 240, 220);
        Profile.setLayout(null);
        contentPane.add(Profile);

        var lblProfile_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Profile.png"));
        lblProfile_Img.setBounds(50, 31, 128, 130);
        Profile.add(lblProfile_Img);

        var lblPR = new JLabel("Profile", SwingConstants.CENTER);
        lblPR.setFont(new Font("Arial", Font.BOLD, 13));
        lblPR.setForeground(new Color(64, 128, 128));
        lblPR.setBounds(60, 160, 107, 38);
		Profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Đã click vào Profile Panel!");
				var ownerFrame = new Owner_Information(userID);
				ownerFrame.setVisible(true);
			}
		});


		Profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Profile.add(lblPR);

        var separator_1 = new JSeparator();
        separator_1.setBackground(new Color(64, 128, 128));
        separator_1.setBounds(10, 195, 207, 14);
        Profile.add(separator_1);

        Profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Profile.setBackground(new Color(200, 230, 230)); // Màu sáng hơn
                Profile.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3)); // Viền đậm
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Profile.setBackground(Color.WHITE); // Quay lại màu trắng
                Profile.setBorder(null); // Bỏ viền
            }
        });


        // Tasks Panel
        Contact = new RoundedPanel();
        Contact.setBackground(Color.WHITE);
        Contact.setBounds(541, 336, 240, 220);
        Contact.setLayout(null);
        contentPane.add(Contact);

        var lblContact_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Contact.png"));
        lblContact_Img.setBounds(46, 25, 128, 130);
        Contact.add(lblContact_Img);

        var lblContact = new JLabel("Contact", SwingConstants.CENTER);
        lblContact.setFont(new Font("Arial", Font.BOLD, 13));
        lblContact.setForeground(new Color(64, 128, 128));
        lblContact.setBounds(66, 155, 107, 38);
        Contact.add(lblContact);
		Contact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Đã click vào lblContact!");

                // Mở trang ContactPage
				var contactFrame = new ContactPage(userID);
                contactFrame.setVisible(true);
            }
        });
		Contact.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Contact.add(lblContact);
        var separator_2 = new JSeparator();
        separator_2.setBackground(new Color(64, 128, 128));
        separator_2.setBounds(10, 195, 207, 14);
        Contact.add(separator_2);

        Contact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Contact.setBackground(new Color(200, 230, 230));
                Contact.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Contact.setBackground(Color.WHITE);
                Contact.setBorder(null);
            }
        });

        // Attendance Panel
        Rules = new RoundedPanel();
        Rules.setBackground(Color.WHITE);
        Rules.setBounds(541, 567, 240, 220);
        Rules.setLayout(null);
        contentPane.add(Rules);

        var lblAttendance_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Rules.png"));
        lblAttendance_Img.setBounds(49, 32, 128, 130);
        Rules.add(lblAttendance_Img);

        var lblAttendance = new JLabel("Rules", SwingConstants.CENTER);
        lblAttendance.setFont(new Font("Arial", Font.BOLD, 13));
        lblAttendance.setForeground(new Color(64, 128, 128));
        lblAttendance.setBounds(62, 156, 107, 38);
        Rules.add(lblAttendance);

		Rules.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Đã click vào lblAttendance!");
                // Mở trang RulesPage
				var rulesFrame = new RulesPage(userID);
                rulesFrame.setVisible(true);
            }
        });
		Rules.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Rules.add(lblAttendance);


        var separator_3 = new JSeparator();
        separator_3.setBackground(new Color(64, 128, 128));
        separator_3.setBounds(10, 195, 207, 14);
        Rules.add(separator_3);

        Rules.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Rules.setBackground(new Color(200, 230, 230));
                Rules.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Rules.setBackground(Color.WHITE);
                Rules.setBorder(null);
            }
        });

        // Feedback Panel
        Feedback = new RoundedPanel();
        Feedback.setBackground(Color.WHITE);
        Feedback.setBounds(132, 567, 240, 220);
        Feedback.setLayout(null);
        contentPane.add(Feedback);

        var lblFeedback_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Feedback2.png"));
        lblFeedback_Img.setBounds(51, 31, 128, 130);
        Feedback.add(lblFeedback_Img);

        var lblFeedback = new JLabel("Feedback", SwingConstants.CENTER);
        lblFeedback.setFont(new Font("Arial", Font.BOLD, 13));
        lblFeedback.setForeground(new Color(64, 128, 128));
        lblFeedback.setBounds(61, 158, 107, 38);
        Feedback.add(lblFeedback);

		Feedback.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Đã click vào lblFeedback!");

                // Mở trang FeedbackPage
				var feedbackFrame = new FeedbackPage(userID);
                feedbackFrame.setVisible(true);
            }
        });
		Feedback.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Feedback.add(lblFeedback);

        var separator_4 = new JSeparator();
        separator_4.setBackground(new Color(64, 128, 128));
        separator_4.setBounds(10, 195, 207, 14);
        Feedback.add(separator_4);

        Notification = new view.RoundedPanel();
        Notification.setLayout(null);
        Notification.setBackground(Color.WHITE);
        Notification.setBounds(132, 798, 240, 220);
        contentPane.add(Notification);

        lblNotification_Img_1 = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Notification.png"));
        lblNotification_Img_1.setBounds(58, 27, 128, 130);
        Notification.add(lblNotification_Img_1);

        lblNotification = new JLabel("Notification", SwingConstants.CENTER);
        lblNotification.setForeground(new Color(64, 128, 128));
        lblNotification.setFont(new Font("Arial", Font.BOLD, 13));
        lblNotification.setBounds(57, 157, 107, 38);
        Notification.add(lblNotification);

		Notification.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Đã click vào lblNotification!");


                // Mở trang NotificationPage
                var notificationFrame = new NotificationPage();
                notificationFrame.setVisible(true);
            }
        });
		Notification.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Notification.add(lblNotification);

        separator_7 = new JSeparator();
        separator_7.setBackground(new Color(64, 128, 128));
        separator_7.setBounds(10, 195, 207, 14);
        Notification.add(separator_7);

        Logout = new view.RoundedPanel();
        Logout.setLayout(null);
        Logout.setBackground(Color.WHITE);
        Logout.setBounds(541, 798, 240, 220);
        contentPane.add(Logout);

        lblLogout_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Logout.png"));
        lblLogout_Img.setBounds(56, 31, 128, 130);
        Logout.add(lblLogout_Img);

        lblLogout = new JLabel("Logout", SwingConstants.CENTER);
        lblLogout.setForeground(new Color(64, 128, 128));
        lblLogout.setFont(new Font("Arial", Font.BOLD, 13));
        lblLogout.setBounds(56, 156, 107, 38);
        Logout.add(lblLogout);

		Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Đã click vào lblLogout!");
				var currentFrame = (JFrame) SwingUtilities.getWindowAncestor(lblServices);
				if (currentFrame != null) {
					currentFrame.dispose();
				}
                var loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
		Logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Logout.add(lblLogout);

        separator_8 = new JSeparator();
        separator_8.setBackground(new Color(64, 128, 128));
        separator_8.setBounds(10, 195, 207, 14);
        Logout.add(separator_8);

        Services = new view.RoundedPanel();
        Services.setLayout(null);
        Services.setBackground(Color.WHITE);
        Services.setBounds(935, 336, 240, 220);
        contentPane.add(Services);

        lblServices_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Services.png"));
        lblServices_Img.setBounds(50, 31, 128, 130);
        Services.add(lblServices_Img);

        lblServices = new JLabel("Services", SwingConstants.CENTER);
        lblServices.setForeground(new Color(64, 128, 128));
        lblServices.setFont(new Font("Arial", Font.BOLD, 13));
        lblServices.setBounds(60, 157, 107, 38);
        Services.add(lblServices);

		Services.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Đã click vào lblServices!");

                // Mở trang ServicesUI
				var servicesFrame = new ServicesUI(userID);
                servicesFrame.setVisible(true);
            }
        });
		Services.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Services.add(lblServices);

        separator_10 = new JSeparator();
        separator_10.setBackground(new Color(64, 128, 128));
        separator_10.setBounds(10, 195, 207, 14);
        Services.add(separator_10);

        Payment = new view.RoundedPanel();
        Payment.setLayout(null);
        Payment.setBackground(Color.WHITE);
        Payment.setBounds(935, 567, 240, 220);
        contentPane.add(Payment);

        lblPayment_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Payment.png"));
        lblPayment_Img.setBounds(56, 31, 128, 130);
        Payment.add(lblPayment_Img);

        lblPayment = new JLabel("Payment", SwingConstants.CENTER);
        lblPayment.setForeground(new Color(64, 128, 128));
        lblPayment.setFont(new Font("Arial", Font.BOLD, 13));
        lblPayment.setBounds(66, 158, 107, 38);
        Payment.add(lblPayment);
		Payment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Mở trang PaymentPage (giả sử bạn đã có class PaymentPage)
				var paymentPage = new PaymentFrame(userID);
				paymentPage.setVisible(true);
			}
		});
		Payment.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Payment.add(lblPayment);

        separator_11 = new JSeparator();
        separator_11.setBackground(new Color(64, 128, 128));
        separator_11.setBounds(10, 195, 207, 14);
        Payment.add(separator_11);

        Register_Temporary_Stay = new view.RoundedPanel();
        Register_Temporary_Stay.setLayout(null);
        Register_Temporary_Stay.setBackground(Color.WHITE);
        Register_Temporary_Stay.setBounds(935, 798, 240, 220);
        contentPane.add(Register_Temporary_Stay);

        lblRegister_Temporary_Stay_Img = new JLabel(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\Register Temporary Stay.png"));
        lblRegister_Temporary_Stay_Img.setBounds(56, 31, 128, 130);
        Register_Temporary_Stay.add(lblRegister_Temporary_Stay_Img);

        lblRegister = new JLabel("Register Temporary Stay", SwingConstants.CENTER);
        lblRegister.setForeground(new Color(64, 128, 128));
        lblRegister.setFont(new Font("Arial", Font.BOLD, 13));
        lblRegister.setBounds(35, 156, 165, 38);
        Register_Temporary_Stay.add(lblRegister);

        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Đã click vào lblRegister!");

             // Đóng trang hiện tại
                var currentFrame = (JFrame) SwingUtilities.getWindowAncestor(lblServices);
                if (currentFrame != null) {
                    currentFrame.dispose();
                }

                // Mở trang RegisterTemporaryStay
				// RegisterTemporaryStay registerFrame = new RegisterTemporaryStay(userID);
				// registerFrame.setVisible(true);
            }
        });

        lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Register_Temporary_Stay.add(lblRegister);

        lblServices.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Services.add(lblServices);
        separator_12 = new JSeparator();
        separator_12.setBackground(new Color(64, 128, 128));
        separator_12.setBounds(10, 195, 207, 14);
        Register_Temporary_Stay.add(separator_12);

        Feedback.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Feedback.setBackground(new Color(200, 230, 230));
                Feedback.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Feedback.setBackground(Color.WHITE);
                Feedback.setBorder(null);
            }
        });

        Notification.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Notification.setBackground(new Color(200, 230, 230));
                Notification.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Notification.setBackground(Color.WHITE);
                Notification.setBorder(null);
            }
        });

        Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Logout.setBackground(new Color(200, 230, 230));
                Logout.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Logout.setBackground(Color.WHITE);
                Logout.setBorder(null);
            }
        });

        lblBanner_Customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblBanner_Customer.setForeground(new Color(30, 90, 90)); // Màu tối hơn khi hover
                lblBanner_Customer.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Biểu tượng bàn tay
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblBanner_Customer.setForeground(new Color(64, 128, 128)); // Quay lại màu cũ
            }

        });

        lblBanner_Services.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblBanner_Services.setForeground(new Color(30, 90, 90)); // Màu tối hơn khi hover
                lblBanner_Services.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Biểu tượng bàn tay
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblBanner_Services.setForeground(new Color(64, 128, 128)); // Quay lại màu cũ
            }

        });

        Services.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	Services.setBackground(new Color(200, 230, 230));
            	Services.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	Services.setBackground(Color.WHITE);
            	Services.setBorder(null);
            }
        });

        Payment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	Payment.setBackground(new Color(200, 230, 230));
            	Payment.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	Payment.setBackground(Color.WHITE);
            	Payment.setBorder(null);
            }
        });

        Register_Temporary_Stay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	Register_Temporary_Stay.setBackground(new Color(200, 230, 230));
            	Register_Temporary_Stay.setBorder(BorderFactory.createLineBorder(new Color(64, 128, 128), 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	Register_Temporary_Stay.setBackground(Color.WHITE);
            	Register_Temporary_Stay.setBorder(null);
            }
        });

    }

}
