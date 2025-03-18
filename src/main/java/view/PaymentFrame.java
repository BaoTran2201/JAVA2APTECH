package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import Dao.PaymentDAO;
import model.Invoice;
import model.InvoiceDetail;

public class PaymentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JTable serviceTable, otherTable;
	private DefaultTableModel serviceTableModel, otherTableModel;
	private JLabel totalLabel;
	private int userID;

	public PaymentFrame(int userID) {
		this.userID = userID;
		setTitle("Payment Page");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

		var servicePanel = createServicePanel();
		tabbedPane.addTab("Service Payment", servicePanel);

		var otherPanel = createOtherPanel();
		tabbedPane.addTab("Payments Details", otherPanel);

		getContentPane().add(tabbedPane);

		loadData(userID);
	}

	private JPanel createServicePanel() {
		var panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(240, 248, 255));

		var lblTitle = new JLabel("Registered Services", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0, 128, 128));
		lblTitle.setPreferredSize(new Dimension(100, 40));
		panel.add(lblTitle, BorderLayout.NORTH);

		String[] columnNames = { "Service Name", "Price" };
		serviceTableModel = new DefaultTableModel(columnNames, 0);
		serviceTable = new JTable(serviceTableModel);
		panel.add(new JScrollPane(serviceTable), BorderLayout.CENTER);

		var bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(new Color(240, 248, 255));

		var paymentButton = new JButton("Pay");
		paymentButton.setFont(new Font("Arial", Font.BOLD, 14));
		paymentButton.setBackground(new Color(0, 128, 128));
		paymentButton.setForeground(Color.WHITE);
		paymentButton.setPreferredSize(new Dimension(120, 40));
		paymentButton.setFocusPainted(false);
		paymentButton.addActionListener(e -> {
			try {
				// Tạo dữ liệu thanh toán với userID
				var paymentData = generatePaymentData(userID);
				// Hiển thị mã QR
				showQRCode(paymentData);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Fail QR!");
			}
		});
		var printButton = new JButton("Print Bill");
		printButton.setFont(new Font("Arial", Font.BOLD, 14));
		printButton.setBackground(new Color(0, 128, 128));
		printButton.setForeground(Color.WHITE);
		printButton.setPreferredSize(new Dimension(120, 40));
		printButton.setFocusPainted(false);
		printButton.addActionListener(e -> printInvoice());

		bottomPanel.add(printButton, BorderLayout.WEST);
		totalLabel = new JLabel("Total Amount: 0 VND", SwingConstants.CENTER);
		totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
		totalLabel.setForeground(Color.RED);

		bottomPanel.add(totalLabel, BorderLayout.CENTER);
		bottomPanel.add(paymentButton, BorderLayout.EAST);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		panel.add(bottomPanel, BorderLayout.SOUTH);

		return panel;
	}

	private void printInvoice() {
		var print = new StringBuilder();
		var space = "       ";
		var nf = NumberFormat.getInstance(Locale.US);
		var currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());

		// Map để gộp các dịch vụ trùng nhau
		Map<String, InvoiceDetail> serviceMap = new HashMap<>();
		var totalAmount = 0D;

		// Lấy dữ liệu từ bảng dịch vụ
		for (var i = 0; i < serviceTableModel.getRowCount(); i++) {
			var serviceName = serviceTableModel.getValueAt(i, 0).toString();
			var price = Double.parseDouble(serviceTableModel.getValueAt(i, 1).toString());

			if (serviceMap.containsKey(serviceName)) {
				// Nếu dịch vụ đã có, tăng số lượng
				var detail = serviceMap.get(serviceName);
				detail.setQuantity(detail.getQuantity() + 1);
			} else {
				// Nếu chưa có, thêm mới
				serviceMap.put(serviceName, new InvoiceDetail(i + 1, serviceName, price, 1));
			}
			totalAmount += price;
		}
		if (totalAmount == 0) {
			JOptionPane.showMessageDialog(this, "No services are registered.!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Chuyển map thành danh sách
		List<InvoiceDetail> details = new ArrayList<>(serviceMap.values());

		// Lưu hóa đơn vào database
		var invoiceDAO = new PaymentDAO();
		var invoice = new Invoice(userID, totalAmount);
		if (invoiceDAO.saveInvoice(invoice, details)) {
			JOptionPane.showMessageDialog(this, "Please give the invoice to the staff for payment. !");
		} else {
			JOptionPane.showMessageDialog(this, "Fail", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// Xây dựng nội dung hóa đơn
		print.append(space).append("=====================================\n").append(space)
				.append("           BILL RECEIPT              \n").append(space)
				.append("=====================================\n").append(space)
				.append(String.format("Date: %s\n", currentDate)).append(space)
				.append(String.format("UserID: %d\n", userID)).append(space)
				.append("-------------------------------------\n").append(space)
				.append(String.format("%-3s %-22s %8s %6s\n", "No", "SERVICE", "PRICE", "QTY")).append(space)
				.append("-------------------------------------\n");

		var index = 1;
		for (InvoiceDetail detail : details) {
			print.append(space).append(String.format("%-3d %-22s %8s %6d\n", index++, detail.getServiceName(),
					nf.format(detail.getPrice()), detail.getQuantity()));
		}

		print.append(space).append("-------------------------------------\n").append(space)
				.append(String.format("%-27s %10s VND\n", "TOTAL:", nf.format(totalAmount))).append(space)
				.append("=====================================\n").append(space)
				.append("     THANK YOU FOR YOUR PAYMENT!     \n").append(space)
				.append("=====================================\n");

		// Hiển thị hóa đơn trong cửa sổ
		var invoiceFrame = new JFrame("BILL");
		invoiceFrame.setSize(420, 450);
		invoiceFrame.setLocationRelativeTo(this);
		invoiceFrame.setResizable(false);

		var invoiceText = new JTextArea(print.toString());
		invoiceText.setFont(new Font("Monospaced", Font.BOLD, 14));
		invoiceText.setEditable(false);
		invoiceText.setBackground(Color.WHITE);

		var scrollPane = new JScrollPane(invoiceText);
		var printButton = new JButton("PRINT");

		printButton.addActionListener(e -> {
			try {
				invoiceText.print();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(invoiceFrame, "Fail!");
			}
		});

		var panel = new JPanel(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(printButton, BorderLayout.SOUTH);

		invoiceFrame.getContentPane().add(panel);
		invoiceFrame.setVisible(true);

	}

	private String generatePaymentData(int userID) {
		var dao = new PaymentDAO();
		var services = dao.getUserServices(userID);
		var totalAmount = dao.getTotalAmount(userID);

		if (services == null || services.isEmpty()) {
			return String.format("Pay for UserID %d: %.2f VND, Service: None", userID, totalAmount);
		}

		var serviceList = services.stream().map(arr -> String.join(", ", arr)).collect(Collectors.joining("; "));
		return String.format("Pay for UserID %d: %.2f VND, Service: %s", userID, totalAmount, serviceList);
	}

	private void showQRCode(String data) throws WriterException, IOException {
		var size = 300;
		var matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
		var image = MatrixToImageWriter.toBufferedImage(matrix);

		var qrFrame = new JFrame("Mã QR Thanh Toán");
		qrFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		qrFrame.getContentPane().add(new JLabel(new ImageIcon(image)));
		qrFrame.pack();
		qrFrame.setLocationRelativeTo(this);
		qrFrame.setVisible(true);
	}

	private JPanel createOtherPanel() {
		var panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(245, 245, 245));
		var lblTitle = new JLabel("Payment Details", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0, 128, 128));
		lblTitle.setPreferredSize(new Dimension(100, 40));
		panel.add(lblTitle, BorderLayout.NORTH);
		String[] columnNames = { "ID Payment", "MemberID", "Date", "Total", "Status" };
		otherTableModel = new DefaultTableModel(columnNames, 0);
		otherTable = new JTable(otherTableModel);
		panel.add(new JScrollPane(otherTable), BorderLayout.CENTER);
		otherTable.setDefaultEditor(Object.class, null);

		// Ẩn cột `MemberID
		var columnModel = otherTable.getColumnModel();
		columnModel.getColumn(1).setMinWidth(0);
		columnModel.getColumn(1).setMaxWidth(0);
		columnModel.getColumn(1).setWidth(0);
		columnModel.getColumn(1).setResizable(false); // Không cho phép thay đổi kích thước
		loadInvoices(userID);
		var popupMenu = new JPopupMenu();
		var viewDetailItem = new JMenuItem("Bill Detail");

		viewDetailItem.addActionListener(e -> {
			var selectedRow = otherTable.getSelectedRow();
			if (selectedRow != -1) {
				var invoiceID = (int) otherTableModel.getValueAt(selectedRow, 0); // Lấy InvoiceID từ cột đầu tiên
				showInvoiceDetails(invoiceID);
			}
		});
		popupMenu.add(viewDetailItem);
		otherTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) { // Kiểm tra nếu là chuột phải
					var row = otherTable.rowAtPoint(e.getPoint());
					if (row != -1) {
						otherTable.setRowSelectionInterval(row, row); // Chọn hàng được nhấn
						popupMenu.show(otherTable, e.getX(), e.getY()); // Hiển thị menu chuột phải
					}
				}
			}
		});
		return panel;
	}

	private void loadInvoices(int userID) {
		var dao = new PaymentDAO();
		otherTableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng

		dao.getPaidInvoicesByUserId(userID)
				.forEach(t -> otherTableModel.addRow(new Object[] { t.getInvoiceID(), t.getMemberID(), // Ẩn cột này
						t.getInvoiceDate(), t.getTotalAmount(), t.isPaymentStatus() ? "Paid" : " " }));

		var totalAmount = dao.getTotalAmount(userID);
		var nf = NumberFormat.getInstance(Locale.US);
		totalLabel.setText("Total Amount: " + nf.format(totalAmount) + " VND");
	}

	private void loadData(int userID) {
		var dao = new PaymentDAO();
		serviceTableModel.setRowCount(0);

		dao.getUserServices(userID).forEach(serviceTableModel::addRow);

		var totalAmount = dao.getTotalAmount(userID);
		var nf = NumberFormat.getInstance(Locale.US);

		totalLabel.setText("Total Amount: " + nf.format(totalAmount) + " VND");
	}

	private void showInvoiceDetails(int invoiceID) {
		var dao = new PaymentDAO();
		var details = dao.getInvoiceDetails(invoiceID);

		// Tạo bảng hiển thị chi tiết
		String[] columnNames = { "service", "Price (VND)", "Quantity", "Total (VND)" };
		var detailTableModel = new DefaultTableModel(columnNames, 0);

		var totalAmount = 0D;
		for (InvoiceDetail detail : details) {
			var total = detail.getPrice() * detail.getQuantity();
			detailTableModel
					.addRow(new Object[] { detail.getServiceName(), detail.getPrice(), detail.getQuantity(), total });
			totalAmount += total;
		}

		var detailTable = new JTable(detailTableModel);
		detailTable.setEnabled(false); // Không cho chỉnh sửa bảng

		var header = detailTable.getTableHeader();
		header.setBackground(new Color(0, 128, 128)); // Màu teal
		header.setForeground(Color.WHITE); // Chữ trắng
		header.setFont(new Font("Arial", Font.BOLD, 14));
		header.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

		detailTable.setBackground(Color.WHITE);
		detailTable.setFont(new Font("Arial", Font.PLAIN, 13));
		detailTable.setRowHeight(30);
		detailTable.setGridColor(Color.LIGHT_GRAY);

		var totalLabel = new JLabel("Total: " + NumberFormat.getInstance(Locale.US).format(totalAmount) + " VND");
		totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
		totalLabel.setForeground(new Color(0, 128, 128));
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		var scrollPane = new JScrollPane(detailTable);
		var detailDialog = new JDialog();
		detailDialog.setTitle("Chi tiết hóa đơn - ID: " + invoiceID);
		detailDialog.setSize(500, 350);
		detailDialog.setLocationRelativeTo(null);

		var panel = new JPanel(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(totalLabel, BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		detailDialog.add(panel);
		detailDialog.setVisible(true);
	}

}
