package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import model.StudentManager;
import model.Tinh;

public class StudentManagementView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	StudentManager model;
	private JPanel panelList;
	private JPanel panelView;
	private JLabel lblStudentList;
	private JPanel panelShow;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblId;
	private JTextField textField_nameView;
	private JLabel lblName;
	private JTextField textField_idView;
	private JLabel lblDateOfBirth;
	private JLabel lblBirthPlaceView;
	private JLabel lblGender;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnFemale;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblSubject;
	private JTextField textField_Sub1;
	private JLabel lblSubject_2;
	private JTextField textField_Sub2;
	private JLabel lblSubject_3;
	private JTextField textField_Sub3;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnSave;
	private JButton btnCancel;
	private JComboBox comboBox_tinhView;
	private JDateChooser dateChooser;
	private JLabel lblMajor;
	private JTextField textField_Major;

	private void clearInputFields() {
		textField_idView.setText("");
		textField_nameView.setText("");
		comboBox_tinhView.setSelectedIndex(-1);
		textField_Sub1.setText("");
		textField_Sub2.setText("");
		textField_Sub3.setText("");
		textField_Major.setText("");
		dateChooser.setDate(null);
		buttonGroup.clearSelection();
		rdbtnNewRadioButton.setSelected(true);
	}

	private void updateRowNumbers() {
		var model = (DefaultTableModel) table.getModel();
		for (var i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(i + 1, i, 0);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new StudentManagementView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentManagementView() {

		setTitle("Student Manager");
		this.model = new StudentManager();
		setBounds(100, 100, 758, 726);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelList = new JPanel();
		panelList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelList.setBounds(10, 25, 722, 289);
		contentPane.add(panelList);
		panelList.setLayout(new BorderLayout());

		lblStudentList = new JLabel("Students List");
		lblStudentList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStudentList.setHorizontalAlignment(SwingConstants.CENTER);
		panelList.add(lblStudentList, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelList.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "No.", "ID", "Name", "DoB", "Birthplace",
				"Gender", "Major", "Subject 1", "Subject 2", "Subject 3	", "AVG", "Status" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(table);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				var selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					var model = (DefaultTableModel) table.getModel();

					textField_idView.setText(model.getValueAt(selectedRow, 1).toString());
					textField_nameView.setText(model.getValueAt(selectedRow, 2).toString());

					var dobValue = model.getValueAt(selectedRow, 3);
					if (dobValue != null && !dobValue.toString().isEmpty()) {
						dateChooser.setDate(java.sql.Date.valueOf(dobValue.toString()));
					} else {
						dateChooser.setDate(null);
					}

					comboBox_tinhView.setSelectedItem(model.getValueAt(selectedRow, 4).toString());

					var gender = model.getValueAt(selectedRow, 5).toString();
					if (gender.equals("Male")) {
						rdbtnNewRadioButton.setSelected(true);
					} else if (gender.equals("Female")) {
						rdbtnFemale.setSelected(true);
					}

					textField_Major.setText(model.getValueAt(selectedRow, 6).toString());

					var sub1Value = model.getValueAt(selectedRow, 7);
					textField_Sub1.setText(sub1Value != null ? sub1Value.toString() : "");

					var sub2Value = model.getValueAt(selectedRow, 8);
					textField_Sub2.setText(sub2Value != null ? sub2Value.toString() : "");

					var sub3Value = model.getValueAt(selectedRow, 9);
					textField_Sub3.setText(sub3Value != null ? sub3Value.toString() : "");
				}
			}
		});

		panelView = new JPanel();
		panelView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelView.setBounds(10, 339, 722, 318);
		contentPane.add(panelView);
		panelView.setLayout(null);

		lblNewLabel_1 = new JLabel("Student Information");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(0, 0, 722, 25);
		panelView.add(lblNewLabel_1);

		var lblX = 30;
		var txtX = 150;
		var yGap = 50;
		var currentY = 40;

		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblId.setBounds(10, 32, 100, 25);
		panelView.add(lblId);

		textField_nameView = new JTextField();
		textField_nameView.setBounds(110, 73, 150, 25);
		panelView.add(textField_nameView);

		currentY += yGap;

		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblName.setBounds(10, 69, 58, 25);
		panelView.add(lblName);

		textField_idView = new JTextField();
		textField_idView.setBounds(110, 36, 150, 25);
		panelView.add(textField_idView);

		currentY += yGap;

		lblDateOfBirth = new JLabel("DoB");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblDateOfBirth.setBounds(10, 109, 58, 25);
		panelView.add(lblDateOfBirth);

		currentY += yGap;

		lblBirthPlaceView = new JLabel("Birthplace");
		lblBirthPlaceView.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBirthPlaceView.setBounds(10, 149, 100, 25);
		panelView.add(lblBirthPlaceView);

		var lblX2 = 400;
		var txtX2 = 520;
		currentY = 40;

		lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblGender.setBounds(10, 185, 100, 25);
		panelView.add(lblGender);

		rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(110, 189, 70, 25);
		panelView.add(rdbtnNewRadioButton);

		rdbtnFemale = new JRadioButton("Female");
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(182, 189, 80, 25);
		panelView.add(rdbtnFemale);

		currentY += yGap;

		lblSubject = new JLabel("Subject 1");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSubject.setBounds(410, 73, 100, 25);
		panelView.add(lblSubject);

		textField_Sub1 = new JTextField();
		textField_Sub1.setBounds(520, 153, 150, 25);
		panelView.add(textField_Sub1);

		currentY += yGap;

		lblSubject_2 = new JLabel("Subject 2");
		lblSubject_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSubject_2.setBounds(410, 109, 100, 25);
		panelView.add(lblSubject_2);

		textField_Sub2 = new JTextField();
		textField_Sub2.setBounds(520, 113, 150, 25);
		panelView.add(textField_Sub2);

		currentY += yGap;

		lblSubject_3 = new JLabel("Subject 3");
		lblSubject_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSubject_3.setBounds(410, 149, 100, 25);
		panelView.add(lblSubject_3);

		textField_Sub3 = new JTextField();
		textField_Sub3.setBounds(520, 77, 150, 25);
		panelView.add(textField_Sub3);

		btnAdd = new JButton("Add");
		var iconAdd = new ImageIcon(this.getClass().getResource("/add1.png")).getImage();
		btnAdd.setIcon(new ImageIcon(iconAdd));
		btnAdd.addActionListener(this::btnAddActionPerformed);
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdd.setBounds(10, 238, 110, 40);
		panelView.add(btnAdd);

		btnDelete = new JButton("Delete");
		var iconDel = new ImageIcon(this.getClass().getResource("/delete2.png")).getImage();
		btnDelete.setIcon(new ImageIcon(iconDel));
		btnDelete.addActionListener(this::btnDeleteActionPerformed);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(158, 238, 110, 40);
		panelView.add(btnDelete);

		btnUpdate = new JButton("Update");
		var iconUpdate = new ImageIcon(this.getClass().getResource("/update3.png")).getImage();
		btnUpdate.setIcon(new ImageIcon(iconUpdate));
		btnUpdate.addActionListener(this::btnUpdateActionPerformed);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUpdate.setBounds(308, 238, 110, 40);
		panelView.add(btnUpdate);

		btnSave = new JButton("Save");
		var iconSave = new ImageIcon(this.getClass().getResource("/save1.png")).getImage();
		btnSave.setIcon(new ImageIcon(iconSave));
		btnSave.addActionListener(this::btnSaveActionPerformed);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSave.setBounds(454, 238, 110, 40);
		panelView.add(btnSave);

		btnCancel = new JButton("Cancel");
		var iconCancel = new ImageIcon(this.getClass().getResource("/cancel2.png")).getImage();
		btnCancel.setIcon(new ImageIcon(iconCancel));
		btnCancel.addActionListener(this::btnCancelActionPerformed);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(602, 238, 110, 40);
		panelView.add(btnCancel);

		comboBox_tinhView = new JComboBox<>();
		var listTinh = Tinh.getDSTinh();
		comboBox_tinhView.addItem("Select province/city");
		for (Tinh tinh : listTinh) {
			comboBox_tinhView.addItem(tinh.getTenTinh());
		}
		comboBox_tinhView.setBounds(110, 150, 150, 25);
		panelView.add(comboBox_tinhView);

		dateChooser = new JDateChooser();
		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.getCalendarButton();
		dateChooser.setBounds(110, 113, 150, 25);
		panelView.add(dateChooser);

		lblMajor = new JLabel("Major");
		lblMajor.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMajor.setBounds(410, 36, 100, 25);
		panelView.add(lblMajor);

		textField_Major = new JTextField();
		textField_Major.setBounds(520, 36, 150, 25);
		panelView.add(textField_Major);

		this.setVisible(true);
	}

	protected void btnAddActionPerformed(ActionEvent e) {
		var id = textField_idView.getText().trim();
		var name = textField_nameView.getText().trim();
		var dob = "";
		var birthPlace = (String) comboBox_tinhView.getSelectedItem();
		var gender = rdbtnNewRadioButton.isSelected() ? "Male" : "Female";
		var sub1 = textField_Sub1.getText().trim();
		var sub2 = textField_Sub2.getText().trim();
		var sub3 = textField_Sub3.getText().trim();
		var major = textField_Major.getText().trim();
		var sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (dateChooser.getDate() != null) {
			dob = sdf.format(dateChooser.getDate());
		}

		if (id.isEmpty() || name.isEmpty() || dob.isEmpty() || birthPlace.equals("Select province/city")
				|| sub1.isEmpty() || sub2.isEmpty() || sub3.isEmpty() || major.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			var score1 = Double.parseDouble(sub1);
			var score2 = Double.parseDouble(sub2);
			var score3 = Double.parseDouble(sub3);
			if (score1 > 10.0 || score2 > 10.0 || score3 > 10.0) {
				JOptionPane.showMessageDialog(this, "Subjects' scores must not exceed 10.0.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			var avg = (Double.parseDouble(sub1) + Double.parseDouble(sub2) + Double.parseDouble(sub3)) / 3;
			var status = trangThai(avg);

			var model = (DefaultTableModel) table.getModel();
			model.insertRow(0, new Object[] { model.getRowCount() + 1, id, name, dob, birthPlace, gender, major, sub1,
					sub2, sub3, String.format("%.2f", avg), status });
			updateRowNumbers();
			clearInputFields();
			JOptionPane.showMessageDialog(this, "Student added successfully!");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Subjects must be numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private String trangThai(double avg) {
		if (avg >= 5) {
			return "Pass";
		}
		return "Fail";
	}

	protected void btnUpdateActionPerformed(ActionEvent e) {
		var selectedRow = table.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			var data = new Object[] { selectedRow + 1, textField_idView.getText().trim(),
					textField_nameView.getText().trim(),
					dateChooser.getDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate())
							: "",
					comboBox_tinhView.getSelectedItem(), rdbtnNewRadioButton.isSelected() ? "Male" : "Female",
					textField_Major.getText().trim(), validateScore(textField_Sub1.getText().trim()),
					validateScore(textField_Sub2.getText().trim()), validateScore(textField_Sub3.getText().trim()) };

			var avg = (Double.parseDouble(data[7].toString()) + Double.parseDouble(data[8].toString())
					+ Double.parseDouble(data[9].toString())) / 3;
			data = appendToArray(data, String.format("%.2f", avg));
			data = appendToArray(data, avg >= 5 ? "Pass" : "Fail");

			var model = (DefaultTableModel) table.getModel();
			for (var i = 1; i < data.length; i++) {
				model.setValueAt(data[i], selectedRow, i);
			}

			JOptionPane.showMessageDialog(this, "Student updated successfully!");
			clearInputFields();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Subjects' scores must be numeric values between 0 and 10.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private String validateScore(String score) throws NumberFormatException {
		var value = Double.parseDouble(score);
		if (value > 10.0 || value < 0.0) {
			throw new NumberFormatException("Invalid score range");
		}
		return String.format("%.2f", value);
	}

	private Object[] appendToArray(Object[] original, Object newItem) {
		var newArray = new Object[original.length + 1];
		System.arraycopy(original, 0, newArray, 0, original.length);
		newArray[original.length] = newItem;
		return newArray;
	}

	protected void btnSaveActionPerformed(ActionEvent e) {
		/*
		 * var connection = connectToDatabase(); if (connection == null) return;
		 *
		 * try { var model = (DefaultTableModel) table.getModel(); String query =
		 * "INSERT INTO students (id, name, dob, birthplace, gender, major, subject1, subject2, subject3, avg, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
		 * ; var preparedStatement = connection.prepareStatement(query);
		 *
		 * for (int i = 0; i < model.getRowCount(); i++) {
		 * preparedStatement.setString(1, model.getValueAt(i, 1).toString());
		 * preparedStatement.setString(2, model.getValueAt(i, 2).toString());
		 * preparedStatement.setString(3, model.getValueAt(i, 3).toString());
		 * preparedStatement.setString(4, model.getValueAt(i, 4).toString());
		 * preparedStatement.setString(5, model.getValueAt(i, 5).toString());
		 * preparedStatement.setString(6, model.getValueAt(i, 6).toString());
		 * preparedStatement.setDouble(7, Double.parseDouble(model.getValueAt(i,
		 * 7).toString())); preparedStatement.setDouble(8,
		 * Double.parseDouble(model.getValueAt(i, 8).toString()));
		 * preparedStatement.setDouble(9, Double.parseDouble(model.getValueAt(i,
		 * 9).toString())); preparedStatement.setDouble(10,
		 * Double.parseDouble(model.getValueAt(i, 10).toString()));
		 * preparedStatement.setString(11, model.getValueAt(i, 11).toString());
		 * preparedStatement.addBatch(); }
		 *
		 * preparedStatement.executeBatch(); connection.close();
		 *
		 * JOptionPane.showMessageDialog(this,
		 * "Data saved successfully to the database", "Save Successful",
		 * JOptionPane.INFORMATION_MESSAGE); } catch (Exception ex) {
		 *
		 * JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(),
		 * "Save Failed", JOptionPane.ERROR_MESSAGE); }
		 */
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		clearInputFields();

		JOptionPane.showMessageDialog(this, "All input fields have been cleared.", "Cancel",
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected void btnDeleteActionPerformed(ActionEvent e) {
		var selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			var model = (DefaultTableModel) table.getModel();

			var confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this row?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {

				model.removeRow(selectedRow);

				updateRowNumbers();

				clearInputFields();

				JOptionPane.showMessageDialog(this, "The data has been successfully deleted!");

			}
		} else {
			JOptionPane.showMessageDialog(this, "Please select a row to delete!", "Notification",
					JOptionPane.WARNING_MESSAGE);
		}
	}

}