package view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.StudentDao;

public class StudentsSearch extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblStudent; // JTable
	private DefaultTableModel model;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public StudentsSearch() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 749, 559);
		getContentPane().setLayout(null);

		var lblId = new JLabel("ID");
		lblId.setBounds(150, 20, 100, 25);
		getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setBounds(278, 20, 272, 25); // Đặt vị trí và kích thước cho txtId
		getContentPane().add(txtId); // Thêm txtId vào frame

		var btnEnter = new JButton("Enter");
		btnEnter.setBounds(450, 140, 100, 25);
		getContentPane().add(btnEnter);

		var lblName = new JLabel("Name");
		lblName.setBounds(150, 60, 100, 25);
		getContentPane().add(lblName);

		var txtName = new JTextField();
		txtName.setBounds(278, 60, 272, 25);
		getContentPane().add(txtName);

		var lblClass = new JLabel("Class");
		lblClass.setBounds(150, 100, 100, 25);
		getContentPane().add(lblClass);

		var cboClass = new JComboBox<>(new String[] {});
		cboClass.setBounds(278, 100, 272, 25);
		getContentPane().add(cboClass);

		var btnSearch = new JButton("Search");
		btnSearch.addActionListener(this::btnTimKiemActionPerformed);
		btnSearch.setBounds(300, 140, 100, 25);
		getContentPane().add(btnSearch);

		var btnCancel = new JButton("Cancel");
		btnCancel.setBounds(150, 140, 100, 25);
		getContentPane().add(btnCancel);

		var lblTotal = new JLabel("Total of students");
		lblTotal.setBounds(150, 206, 200, 25);
		getContentPane().add(lblTotal);

		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Class");

		// Khởi tạo JTable và set model cho nó
		tblStudent = new JTable(model);

		// Tạo JScrollPane và thêm JTable vào đó
		var scrollPane = new JScrollPane(tblStudent);
		scrollPane.setBounds(109, 233, 540, 120); // Vị trí và kích thước của JScrollPane
		getContentPane().add(scrollPane); // Thêm JScrollPane vào frame
	}

	public static void main(String[] args) {
		var frame = new JFrame("Chương trình quản lý điểm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.getContentPane().setLayout(null);
		var desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane);
		var studentSearch = new StudentsSearch();
		desktopPane.add(studentSearch);
		studentSearch.setVisible(true);
		frame.setVisible(true);
	}

	protected void btnTimKiemActionPerformed(ActionEvent e) {
		var id = txtId.getText();
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter an ID to search.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		var model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Class");
		var dao = StudentDao.getInstance();
		var students = dao.selectid(id);
		if (students == null) {
			JOptionPane.showMessageDialog(null, "Can not find ID.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			//model.addRow(new Object[] { student.getId(), student.getName(), student.getIdclass() });
			students.forEach(p -> model.addRow(new Object[] { p.getId(), p.getName(), p.getIdclass() }));
		tblStudent.setModel(model);
	}
}


}
