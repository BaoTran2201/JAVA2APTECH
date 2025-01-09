package view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class MainApplication {
	public static void main(String[] args) {
		// Tạo JFrame chính
		var frame = new JFrame("Student Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 700);
		frame.setLayout(null);

		// Tạo JDesktopPane để chứa các JInternalFrame
		var desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 900, 700);
		frame.add(desktopPane);

		// Tạo StudentScore JInternalFrame
		var studentScoreFrame = new StudentScore();
		desktopPane.add(studentScoreFrame);
		studentScoreFrame.setVisible(true); // Hiển thị JInternalFrame

		// Hiển thị JFrame chính
		frame.setVisible(true);
	}
}