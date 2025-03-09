package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Dao.FeedbackDAO;
import model.Feedback;

public class FeedbackPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtFeedbackTitle;
	private JTextArea txtMessage;
	private DefaultListModel<ImageIcon> imageListModel;
	private JList<ImageIcon> imageList;
	private List<File> selectedImages;

	public FeedbackPage(int userID) {
		setTitle("Feedback Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		setLocationRelativeTo(null);
		setResizable(false);

		var panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setLayout(null);
		getContentPane().add(panel);

		var lblTitle = new JLabel("Customer Feedback", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitle.setForeground(new Color(0, 128, 128));
		lblTitle.setBounds(180, 5, 300, 30);
		panel.add(lblTitle);

		var lblName = new JLabel("Name:");
		lblName.setBounds(70, 70, 100, 25);
		panel.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(180, 70, 350, 25);
		panel.add(txtName);

		var lblFeedbackTitle = new JLabel("Feedback Title:");
		lblFeedbackTitle.setBounds(70, 120, 100, 25);
		panel.add(lblFeedbackTitle);

		txtFeedbackTitle = new JTextField();
		txtFeedbackTitle.setBounds(180, 120, 350, 25);
		panel.add(txtFeedbackTitle);

		var lblMessage = new JLabel("Your Message:");
		lblMessage.setBounds(70, 170, 100, 25);
		panel.add(lblMessage);

		txtMessage = new JTextArea();
		var scrollMessage = new JScrollPane(txtMessage);
		scrollMessage.setBounds(180, 170, 350, 80);
		panel.add(scrollMessage);

		imageListModel = new DefaultListModel<>();
		imageList = new JList<>(imageListModel);
		var scrollImages = new JScrollPane(imageList);
		scrollImages.setBounds(180, 261, 350, 134);
		imageList.setCellRenderer(new CenteredImageRenderer());
		panel.add(scrollImages);

		selectedImages = new ArrayList<>();

		var btnChooseImages = new JButton("Choose Images");
		btnChooseImages.setBounds(180, 406, 350, 25);
		btnChooseImages.addActionListener(e -> chooseImages());
		panel.add(btnChooseImages);

		var btnSubmit = new JButton("Submit Feedback");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(0, 128, 128));
		btnSubmit.setBounds(180, 457, 350, 30);
		btnSubmit.addActionListener(e -> submitFeedback(userID));
		panel.add(btnSubmit);

		var panelBottom = new JPanel();
		panel.add(panelBottom, BorderLayout.SOUTH);

		getContentPane().add(panel);

		var btnBack = new JButton("◄ Back");
		btnBack.setBounds(10, 11, 59, 19);
		panel.add(btnBack);
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(new Color(0, 128, 128));
		btnBack.setBackground(new Color(0, 100, 100));
		btnBack.setFocusPainted(false);
		btnBack.setBorder(null);
		btnBack.setContentAreaFilled(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBack.setForeground(new Color(200, 200, 200));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBack.setForeground(new Color(0, 128, 128));
			}
		});

		btnBack.addActionListener(e -> {
			new User(userID).setVisible(true);
			dispose();
		});

	}

//canh giữa các ảnh
	private class CenteredImageRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			var label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof ImageIcon) {
				label.setIcon((ImageIcon) value);
				label.setText("");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
			}
			return label;
		}
	}

	private void chooseImages() {
		var fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		var result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			var files = fileChooser.getSelectedFiles();
			for (File file : files) {
				selectedImages.add(file);
				var icon = new ImageIcon(file.getAbsolutePath());
				var resizedIcon = resizeImage(icon, 80, 80);
				imageListModel.addElement(resizedIcon);
			}
		}
	}

	private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
		var image = icon.getImage();
		var resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	private void submitFeedback(int userID) {
		var name = txtName.getText().trim();
		var title = txtFeedbackTitle.getText().trim();
		var message = txtMessage.getText().trim();

		if (name.isEmpty() || title.isEmpty() || message.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		var feedback = new Feedback();
		feedback.setMemberID(userID);
		feedback.setNamefb(name);
		feedback.setFeedbackTittle(title);
		feedback.setNote(message);
		feedback.setStatusfb(false);

		var dao = new FeedbackDAO();
		var feedbackID = dao.insertFeedback(feedback, userID);

		if (feedbackID > 0) {
			var savedPaths = saveImages(feedbackID);
			dao.insertFeedbackImages(feedbackID, savedPaths);
			JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			resetFields();
		} else {
			JOptionPane.showMessageDialog(this, "Failed to submit feedback!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetFields() {
		txtName.setText("");
		txtFeedbackTitle.setText("");
		txtMessage.setText("");
		imageListModel.clear();

	}

	private List<String> saveImages(int feedbackID) {
		var dirServer = System.getProperty("user.dir") + "\\feedback"; // Thư mục lưu ảnh feedback
		var serverDir = new File(dirServer);
		// đảm bảo thư mục tồn tại
		if (!serverDir.exists()) {
			serverDir.mkdirs();
		}

		List<String> paths = new ArrayList<>();

		for (File file : selectedImages) {
			var fileName = feedbackID + "_" + file.getName(); // Đổi tên file theo feedbackID
			var pathLocal = file.toPath(); // Đường dẫn file gốc
			var pathServer = Paths.get(dirServer, fileName); // Đường dẫn lưu trên server

			try {
				Files.copy(pathLocal, pathServer, StandardCopyOption.REPLACE_EXISTING);
				paths.add("feedback/" + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return paths;
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new FeedbackPage(2).setVisible(true));
	}
}

