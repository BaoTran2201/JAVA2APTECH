package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Students_Inform extends JInternalFrame {

	public Students_Inform() {
		// Set up the JInternalFrame
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 749, 559);
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Main panel
		var mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

		// Title label
		var titleLabel = new JLabel("Thông Tin Sinh Viên", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// Add fields panel
		var fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new GridLayout(11, 2, 5, 5)); // Adjust spacing between rows and columns

		// Add labels and non-editable text fields with background color
		var labelFullName = new JLabel("Họ và Tên:");
		labelFullName.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelFullName.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelFullName);
		var fullNameField = new JLabel();
		fullNameField.setOpaque(true);
		fullNameField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(fullNameField);

		var labelId = new JLabel("ID:");
		labelId.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelId.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelId);
		var idField = new JLabel();
		idField.setOpaque(true);
		idField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(idField);

		var labelDob = new JLabel("DoB:");
		labelDob.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDob.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelDob);
		var dobField = new JLabel();
		dobField.setOpaque(true);
		dobField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(dobField);

		var labelBirthplace = new JLabel("Birthplace:");
		labelBirthplace.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelBirthplace.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelBirthplace);
		var birthplaceField = new JLabel();
		birthplaceField.setOpaque(true);
		birthplaceField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(birthplaceField);

		var labelGender = new JLabel("Gender:");
		labelGender.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelGender.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelGender);
		var genderField = new JLabel();
		genderField.setOpaque(true);
		genderField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(genderField);

		var labelMajor = new JLabel("Major:");
		labelMajor.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMajor.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelMajor);
		var majorField = new JLabel();
		majorField.setOpaque(true);
		majorField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(majorField);

		var labelSubject1 = new JLabel("Subject 1:");
		labelSubject1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSubject1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelSubject1);
		var subject1Field = new JLabel();
		subject1Field.setOpaque(true);
		subject1Field.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(subject1Field);

		var labelSubject2 = new JLabel("Subject 2:");
		labelSubject2.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSubject2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelSubject2);
		var subject2Field = new JLabel();
		subject2Field.setOpaque(true);
		subject2Field.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(subject2Field);

		var labelSubject3 = new JLabel("Subject 3:");
		labelSubject3.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSubject3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelSubject3);
		var subject3Field = new JLabel();
		subject3Field.setOpaque(true);
		subject3Field.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(subject3Field);

		var labelAvg = new JLabel("AVG:");
		labelAvg.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelAvg.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelAvg);
		var avgField = new JLabel();
		avgField.setOpaque(true);
		avgField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(avgField);

		var labelStatus = new JLabel("Status:");
		labelStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelStatus.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fieldsPanel.add(labelStatus);
		var statusField = new JLabel();
		statusField.setOpaque(true);
		statusField.setBackground(Color.LIGHT_GRAY);
		fieldsPanel.add(statusField);

		// Add fields panel to main panel
		mainPanel.add(fieldsPanel, BorderLayout.CENTER);

		// Add bottom button panel
		var buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add panels to frame
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		// Test the JInternalFrame in a JFrame
		var frame = new JFrame("Test Students Inform");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 800);

		var desktopPane = new JDesktopPane();
		var studentFrame = new Students_Inform();
		desktopPane.add(studentFrame);

		frame.getContentPane().add(desktopPane);
		frame.setVisible(true);
	}
}
