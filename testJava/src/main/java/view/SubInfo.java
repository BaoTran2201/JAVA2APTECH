package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class SubInfo extends JInternalFrame {
	private JTextField txtClassID;
	private JTextField txtClassName;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblFaculty;
	private JTextField txtFacultyID;

	public SubInfo() {
		setBounds(100, 100, 881, 662);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setLayout(null);

		// Panel cho các trường thông tin
		var fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		fieldPanel.setBorder(BorderFactory.createTitledBorder("Class Information"));
		fieldPanel.setBounds(153, 9, 560, 145);
		getContentPane().add(fieldPanel);

		// Label "ID"
		var lblID = new JLabel("ID");
		lblID.setBounds(20, 30, 80, 25);
		fieldPanel.add(lblID);

		// TextField "ID"
		txtClassID = new JTextField();
		txtClassID.setBounds(100, 30, 150, 25);
		fieldPanel.add(txtClassID);

		// Label "Name"
		var lblName = new JLabel("Name");
		lblName.setBounds(20, 70, 80, 25);
		fieldPanel.add(lblName);

		// TextField "Name"
		txtClassName = new JTextField();
		txtClassName.setBounds(100, 70, 150, 25);
		fieldPanel.add(txtClassName);

		var btnLoad = new JButton("Load");
		btnLoad.setBounds(323, 40, 80, 25);
		fieldPanel.add(btnLoad);

		var btnCancel = new JButton("Cancel");
		btnCancel.setBounds(323, 88, 80, 25);
		fieldPanel.add(btnCancel);

		lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(20, 106, 80, 25);
		fieldPanel.add(lblFaculty);

		txtFacultyID = new JTextField();
		txtFacultyID.setBounds(100, 106, 150, 25);
		fieldPanel.add(txtFacultyID);

		// Panel cho bảng
		var tablePanel = new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBorder(BorderFactory.createTitledBorder("Class List"));
		tablePanel.setBounds(10, 165, 845, 292);
		getContentPane().add(tablePanel);

		// Table
		tableModel = new DefaultTableModel(new Object[] { "ID", "Name" }, 0);
		table = new JTable(tableModel);
		var tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(10, 20, 814, 247);
		tablePanel.add(tableScrollPane);

		// Panel cho các nút
		var buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBorder(new TitledBorder(null, "Buttons", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonPanel.setBounds(153, 468, 560, 80);
		getContentPane().add(buttonPanel);

		// Các nút
		var btnAdd = new JButton("Add");
		btnAdd.setBounds(54, 30, 80, 25);
		buttonPanel.add(btnAdd);

		var btnEdit = new JButton("Edit");
		btnEdit.setBounds(188, 30, 80, 25);
		buttonPanel.add(btnEdit);

		var btnDelete = new JButton("Delete");
		btnDelete.setBounds(318, 30, 80, 25);
		buttonPanel.add(btnDelete);

		var btnSearch = new JButton("Search");
		btnSearch.setBounds(443, 30, 80, 25);
		buttonPanel.add(btnSearch);
	}

}