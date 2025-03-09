package Dao;

import java.util.HashMap;
import java.util.Map;

import service.ConnectDB;

public class StatisticsDAO {
	public Map<String, Integer> getServiceRegistrationData() {
		Map<String, Integer> data = new HashMap<>();
		var sql = """
				SELECT s.ServiceName, COUNT(us.ServiceID) AS Total
				FROM Services s
				LEFT JOIN UserServices us ON s.ServiceID = us.ServiceID
				GROUP BY s.ServiceName""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {

			while (rs.next()) {
				var serviceName = rs.getString("ServiceName");
				var totalRegistrations = rs.getInt("Total");
				data.put(serviceName, totalRegistrations);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public int getTotalCustomers() {
		var sql = "SELECT COUNT(*) AS Total FROM Login where jobRole='user'and loginStatus =1";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt("Total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getTotalFeedback() {
		var sql = "SELECT COUNT(*)AS TOTAL FROM FEEDBACK WHERE Statusfb =0  ";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt("Total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getRevenue() {
		var sql = "SELECT SUM(TotalAmount) AS Total FROM Invoices";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt("Total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
