package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.StaffServiceByID;
import service.ConnectDB;

public class StaffServiceByidDAO {
	public List<StaffServiceByID> getStaffServicesByStaffID(int staffID) {
		List<StaffServiceByID> staffServices = new ArrayList<>();
		var sql = "SELECT " + "ss.StaffServiceID," + "a.ApartmentNumber, " + "m.memberName, " + "m.Phone, "
				+ "s.ServiceName, "
				+ "b.BuildingName, " + "ss.StatusDone " + // Lấy trạng thái dịch vụ
				"FROM StaffServices ss " + "JOIN UserServices us ON ss.UserServiceID = us.UserServiceID "
				+ "JOIN Services s ON us.ServiceID = s.ServiceID " + "JOIN members m ON us.memberID = m.memberID "
				+ "JOIN Apartments a ON m.apartmentID = a.ApartmentID "
				+ "JOIN Building b ON a.BuildingID = b.BuildingID " + "WHERE ss.StaffID = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, staffID);
			var rs = stmt.executeQuery();

			while (rs.next()) {
				var staffService = new StaffServiceByID();
				staffService.setStaffServiceID(rs.getInt("StaffServiceID"));
				staffService.setApartmentNumber(rs.getString("ApartmentNumber"));
				staffService.setMemberName(rs.getString("memberName"));
				staffService.setPhone(rs.getString("Phone"));
				staffService.setServiceName(rs.getString("ServiceName"));
				staffService.setBuildingName(rs.getString("BuildingName"));
				staffService.setStatus(rs.getBoolean("StatusDone")); // Set trạng thái từ dữ liệu
				staffServices.add(staffService);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffServices;
	}

	public boolean updateStatus(int staffServiceID, int status) {
		var sql = "UPDATE StaffServices SET StatusDone = ? WHERE StaffServiceID = ?";

		try (var conn = ConnectDB.getCon(); var ps = conn.prepareStatement(sql)) {
			ps.setInt(1, status);
			ps.setInt(2, staffServiceID);

			var rowsAffected = ps.executeUpdate();
			return rowsAffected > 0; // Return true if update was successful
		} catch (Exception e) {
			e.printStackTrace(); // Handle exceptions properly
			return false; // Return false if there was an error
		}
	}

	public boolean updateServiceStatus(int staffServiceID, int status) {
		var sql = "UPDATE StaffServices SET StatusDone = ? WHERE StaffServiceID = ?";
		try (var conn = ConnectDB.getCon(); var ps = conn.prepareStatement(sql)) {
			ps.setInt(1, status); // Set the status value (1 or 0)
			ps.setInt(2, staffServiceID); // Set the StaffServiceID

			var rowsUpdated = ps.executeUpdate();

			return rowsUpdated > 0;
		} catch (Exception e) {

			System.out.println("Error updating service status: " + e.getMessage());
			return false;
		}
	}

}
