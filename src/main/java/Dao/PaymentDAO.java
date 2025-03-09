package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Invoice;
import model.InvoiceDetail;
import model.Payment;
import service.ConnectDB;

public class PaymentDAO {
	public List<String[]> getUserServices(int userID) {
		List<String[]> services = new ArrayList<>();
		var sql = """
				SELECT s.ServiceName, s.Price FROM UserServices us \
				JOIN Services s ON us.ServiceID = s.ServiceID \
				WHERE us.memberID = ? AND us.StatusSer = 0""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, userID);
			var rs = ps.executeQuery();

			while (rs.next()) {
				var serviceName = rs.getString("ServiceName");
				var price = rs.getDouble("Price");
				services.add(new String[] { serviceName, String.valueOf(price) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return services;
	}

	public double getTotalAmount(int userID) {
		var sql = """
				SELECT COALESCE(SUM(s.Price), 0) AS Total FROM UserServices us \
				JOIN Services s ON us.ServiceID = s.ServiceID \
				WHERE us.memberID = ? AND us.StatusSer = 0""";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, userID);
			var rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("Total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	public Payment getPaymentInfo(int userID) {
		var sql = "SELECT * FROM Payments WHERE memberID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, userID);
			var rs = ps.executeQuery();
			if (rs.next()) {
				return new Payment(rs.getInt("PaymentID"), rs.getInt("memberID"), rs.getDouble("TotalAmount"),
						rs.getDate("PaymentDate"), rs.getBoolean("PaymentStatus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//lưu hóa đơn
	public boolean saveInvoice(Invoice invoice, List<InvoiceDetail> details) {
		var con = ConnectDB.getCon();
		PreparedStatement psInvoice = null, psDetail = null, psUpdate = null;
		ResultSet rs = null;
		var success = false;

		var insertInvoiceSQL = "INSERT INTO Invoices (memberID, TotalAmount) VALUES (?, ?)";
		var checkDetailSQL = "SELECT Quantity FROM InvoiceDetails WHERE InvoiceID = ? AND ServiceID = ?";
		var updateDetailSQL = "UPDATE InvoiceDetails SET Quantity = Quantity + ? WHERE InvoiceID = ? AND ServiceID = ?";
		var insertDetailSQL = "INSERT INTO InvoiceDetails (InvoiceID, ServiceID, ServiceName, Price, Quantity) VALUES (?, ?, ?, ?, ?)";

		try {
			con.setAutoCommit(false); // Bắt đầu transaction

			// Thêm hóa đơn mới
			psInvoice = con.prepareStatement(insertInvoiceSQL, Statement.RETURN_GENERATED_KEYS);
			psInvoice.setInt(1, invoice.getMemberID());
			psInvoice.setDouble(2, invoice.getTotalAmount());
			psInvoice.executeUpdate();

			rs = psInvoice.getGeneratedKeys();
			var invoiceID = -1;
			if (rs.next()) {
				invoiceID = rs.getInt(1);
			}

			// Kiểm tra chi tiết hóa đơn
			for (InvoiceDetail detail : details) {
				psDetail = con.prepareStatement(checkDetailSQL);
				psDetail.setInt(1, invoiceID);
				psDetail.setInt(2, detail.getServiceID());
				rs = psDetail.executeQuery();

				if (rs.next()) {
					// Nếu đã có dịch vụ, cập nhật quantity
					psUpdate = con.prepareStatement(updateDetailSQL);
					psUpdate.setInt(1, detail.getQuantity());
					psUpdate.setInt(2, invoiceID);
					psUpdate.setInt(3, detail.getServiceID());
					psUpdate.executeUpdate();
				} else {
					// Nếu chưa có, thêm mới
					psDetail = con.prepareStatement(insertDetailSQL);
					psDetail.setInt(1, invoiceID);
					psDetail.setInt(2, detail.getServiceID());
					psDetail.setString(3, detail.getServiceName());
					psDetail.setDouble(4, detail.getPrice());
					psDetail.setInt(5, detail.getQuantity());
					psDetail.executeUpdate();
				}
			}

			con.commit(); // Xác nhận transaction
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback(); // Rollback nếu có lỗi
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return success;
	}

	/// load hóa đơn đã thanh toán
	public List<Invoice> getPaidInvoicesByUserId(int memberID) {
	    List<Invoice> invoices = new ArrayList<>();
	    var sql = "SELECT * FROM Invoices WHERE PaymentStatus = 1 AND memberID = ?";

	    try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

	        ps.setInt(1, memberID);
	        var rs = ps.executeQuery();

	        while (rs.next()) {
	            invoices.add(new Invoice(
	                rs.getInt("InvoiceID"),
	                rs.getInt("memberID"),
	                rs.getTimestamp("InvoiceDate"),
	                rs.getDouble("TotalAmount"),
	                rs.getBoolean("PaymentStatus")
	            ));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return invoices;
	}

	public List<InvoiceDetail> getInvoiceDetails(int invoiceID) {
		List<InvoiceDetail> details = new ArrayList<>();
		var sql = "SELECT ServiceName, Price, Quantity FROM InvoiceDetails WHERE InvoiceID = ?";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			ps.setInt(1, invoiceID);
			var rs = ps.executeQuery();

			while (rs.next()) {
				var serviceName = rs.getString("ServiceName");
				var price = rs.getDouble("Price");
				var quantity = rs.getInt("Quantity");

				details.add(new InvoiceDetail(invoiceID, serviceName, price, quantity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return details;
	}

}
