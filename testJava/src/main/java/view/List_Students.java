package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.StudentDao;

public class List_Students extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel listPanel;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnLast;
	private JButton btnNext;
	private JComboBox comboBox;
	private JTextField textPage;
	private JLabel lblpage;
	private JButton btnLoad;

	private int pageNumber = 1;
	private int rowsPerPage = 10;
	private int totalRows = 0;
	private int totalPages = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				var frame = new List_Students();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public List_Students() {
		setResizable(true);

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 881, 662);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 11, 733, 412);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "No.", "ID", "Name", "DoB", "Birthplace",
				"Gender", "Major", "Subject 1", "Subject 2", "Subject 3", "AVG", "Status" }));
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(168, 438, 507, 122);
		getContentPane().add(panel);

		btnFirst = new JButton("First");
		btnFirst.addActionListener(this::btnFirstActionPerformed);
		btnFirst.setBounds(10, 48, 89, 23);
		panel.add(btnFirst);

		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(115, 48, 89, 23);
		panel.add(btnPrevious);

		btnLast = new JButton("Last");
		btnLast.setBounds(408, 48, 89, 23);
		panel.add(btnLast);

		btnNext = new JButton("Next");
		btnNext.setBounds(301, 48, 89, 23);
		panel.add(btnNext);

		comboBox = new JComboBox();
		comboBox.setBounds(227, 48, 55, 23);
		panel.add(comboBox);

		textPage = new JTextField();
		textPage.setText("1");
		textPage.setHorizontalAlignment(SwingConstants.CENTER);
		textPage.setColumns(10);
		textPage.setBounds(227, 91, 55, 20);
		panel.add(textPage);

		lblpage = new JLabel("Page 1 of");
		lblpage.setBounds(34, 94, 81, 14);
		panel.add(lblpage);

		btnLoad = new JButton("Load");
		btnLoad.addActionListener(this::btnLoadActionPerformed);
		btnLoad.setBounds(211, 11, 89, 23);
		panel.add(btnLoad);
	}

	public void resetTable() {
	}

	protected void btnFirstActionPerformed(ActionEvent e) {

	}

	protected void btnLoadActionPerformed(ActionEvent e) {
		var model = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int column) {
				return switch (column) {
				case 0 -> Integer.class; // cột id là int
				case 1 -> String.class; // cột fullname là string
				case 2 -> Boolean.class; // cột gender là boolean
				case 3 -> LocalDate.class; // cột dob là LocalDate
				case 4 -> ImageIcon.class; // cột picture là ảnh
				case 5 -> String.class; // cột picturepath là đường dẫn hình
				default -> String.class;
				};
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return switch (column) {
				case 4 -> false; // cột picture ko duoc phép click vào
				default -> true;
				};
			}
		};

		model.addColumn("id");
		model.addColumn("fullname");
		model.addColumn("gender");
		model.addColumn("dob");
		model.addColumn("address");
		model.addColumn("idclass");

		var dao = new StudentDao();

		rowsPerPage = Integer.parseInt(comboBox.getSelectedItem().toString());
		totalRows = dao.count();
		totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
		lblpage.setText("Page " + pageNumber + " of " + totalPages);
		var students = dao.select(pageNumber, rowsPerPage);

		var list = dao.select(pageNumber, rowsPerPage);
		list.stream().forEach(stu -> model.addRow(new Object[] { stu.getId(), stu.getName(),
				stu.isGender() ? "Male" : "Female", stu.getDob(), stu.getDiaChi(), stu.getClass()

		}));
		table.setModel(model);
		table.setRowHeight(60);
	}
}