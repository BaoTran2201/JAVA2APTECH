package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class List_Students extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel listPanel;
	private JScrollPane scrollPane;
	private JTable table;

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
		setBounds(100, 100, 749, 559);
		getContentPane().setLayout(null);

		listPanel = new JPanel();
		listPanel.setBounds(10, 11, 713, 509);
		getContentPane().add(listPanel);
		listPanel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		listPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "No.", "ID", "Name", "DoB", "Birthplace",
				"Gender", "Major", "Subject 1", "Subject 2", "Subject 3", "AVG", "Status" }));
		scrollPane.setViewportView(table);

	}
}