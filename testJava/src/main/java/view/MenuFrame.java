package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel leftPanel;
	private JButton btnHome;
	private JButton btnManagenment;
	private JButton btnStatistics;
	private JButton btnLogout_3;
	private JLabel lblNewLabel;

	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new MenuFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 729);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 128, 128));
		leftPanel.setBounds(35, 18, 215, 636);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);

		btnHome = new JButton("Home\r\n");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHome.setBounds(10, 135, 193, 53);
		leftPanel.add(btnHome);

		btnManagenment = new JButton("Management");
		btnManagenment.addActionListener(this::btnManagenmentActionPerformed);
		btnManagenment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnManagenment.setBounds(10, 340, 193, 54);
		leftPanel.add(btnManagenment);

		btnStatistics = new JButton("Statistics");
		btnStatistics.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStatistics.setBounds(10, 450, 193, 55);
		leftPanel.add(btnStatistics);

		btnLogout_3 = new JButton("Logout");
		btnLogout_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogout_3.setBounds(10, 544, 193, 55);
		btnLogout_3.addActionListener(this::btnLogoutActionPerformed); // Gắn sự kiện Logout
		leftPanel.add(btnLogout_3);

		lblNewLabel = new JLabel("<html><div style='text-align: center;'>STUDENT<br/> MANAGEMENT</div></html>");
		lblNewLabel.setForeground(new Color(128, 128, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(19, 28, 202, 72);
		leftPanel.add(lblNewLabel);

		btnList = new JButton("List");
		btnList.addActionListener(this::btnListActionPerformed);
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnList.setBounds(10, 235, 193, 53);
		leftPanel.add(btnList);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 128, 128));
		desktopPane.setBounds(314, 18, 916, 661);
		contentPane.add(desktopPane);
	}

	private StudentManagementView managementView;
	private JButton btnList;

	protected void btnManagenmentActionPerformed(ActionEvent e) {
		if (managementView == null || !managementView.isDisplayable()) {
			managementView = new StudentManagementView();
			managementView.setVisible(true);
		} else {
			managementView.toFront();
		}
	}

	protected void btnLogoutActionPerformed(ActionEvent e) {

		if (managementView != null && managementView.isDisplayable()) {
			managementView.dispose();
		}
		var loginFrame = new LoginFrame();
		loginFrame.setVisible(true);

		this.dispose();
	}

	protected void btnListActionPerformed(ActionEvent e) {
		for (var component : desktopPane.getComponents()) {
			if (component instanceof List_Students listStudentsFrame && listStudentsFrame.isVisible()) {
				listStudentsFrame.toFront();
				return;
			}
		}

		for (var component : desktopPane.getComponents()) {
			if (component instanceof JLabel && "title".equals(component.getName())) {
				desktopPane.remove(component);
			}
		}

		var titleLabel = new JLabel("LIST STUDENT", JLabel.CENTER);
		titleLabel.setName("title");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(0, 0, desktopPane.getWidth(), 50);
		desktopPane.add(titleLabel);

		var listStudent = new List_Students();
		desktopPane.add(listStudent);
		listStudent.setVisible(true);

		desktopPane.repaint();

	}

}
