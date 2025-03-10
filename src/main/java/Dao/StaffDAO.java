package Dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Staff;
import model.StaffService;
import model.UserServices;
import service.ConnectDB;

public class StaffDAO {
	public boolean addStaff(Staff staff) {
		var sql = "INSERT INTO staff (StaffName, Phone, staffStatus) VALUES (?, ?, ?)";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, staff.getStaffName());
			stmt.setString(2, staff.getPhone());
			stmt.setBoolean(3, staff.isStaffStatus());

			var affectedRows = stmt.executeUpdate();
			if (affectedRows > 0) {
				try (var generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						var staffID = generatedKeys.getInt(1);
						return createLoginForStaff(staffID);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean createLoginForStaff(int staffID) {
		var sql = "INSERT INTO Login (username, pass, jobRole) VALUES (?, ?, 'staff')";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, String.valueOf(staffID)); // Username là StaffID
			stmt.setString(2, "1"); // Mật khẩu mặc định là "1"

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 🟢 Lấy danh sách nhân viên
	public List<Staff> getAllStaff() {
		List<Staff> staffList = new ArrayList<>();
		var sql = "SELECT * FROM staff WHERE staffStatus = 1";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {
			while (rs.next()) {
				staffList.add(new Staff(rs.getInt("StaffID"), rs.getString("StaffName"), rs.getString("Phone"),
						rs.getBoolean("staffStatus")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffList;
	}

	// 🟢 Cập nhật thông tin nhân viên
	public boolean updateStaff(Staff staff) {
		var sql = "UPDATE staff SET StaffName=?, Phone=? WHERE StaffID=?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, staff.getStaffName());
			stmt.setString(2, staff.getPhone());
			stmt.setInt(3, staff.getStaffID());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 🟢 Xóa nhân viên (Đổi trạng thái `staffStatus` thành 0)
	public boolean deleteStaff(int staffID) {
		var sql = "UPDATE staff SET staffStatus = 0 WHERE StaffID = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, staffID);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Staff getStaffByID(int staffID) {
		var sql = "SELECT * FROM staff WHERE StaffID = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, staffID);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Staff(rs.getInt("StaffID"), rs.getString("StaffName"), rs.getString("Phone"),
							rs.getBoolean("staffStatus"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Không tìm thấy staff
	}

	// ----------------------------------------------------------
	public List<Staff> AllStaff() {
		List<Staff> staffList = new ArrayList<>();
		var sql = "SELECT StaffID, StaffName, Phone, staffStatus FROM staff where staffStatus = 1 ";

		try (var con = ConnectDB.getCon();
				var ps = con.prepareStatement(sql);
				var rs = ps.executeQuery()) {

			while (rs.next()) {
				var id = rs.getInt("StaffID");
				var name = rs.getString("StaffName");
				var phone = rs.getString("Phone");
				var status = rs.getBoolean("staffStatus");

				var staff = new Staff(id, name, phone, status);
				staffList.add(staff);
			}

		} catch (Exception e) {
			System.err.println("Error fetching staff data: " + e.getMessage());
			e.printStackTrace();
		}
		return staffList;
	}

	public List<UserServices> loadUserServices() {
		List<UserServices> userServicesList = new ArrayList<>();

		var sql = """
				SELECT us.UserServiceID, us.memberID, us.ServiceID, s.ServiceName,
				us.Daystart, us.Dayend, us.StatusSer
				FROM UserServices us
				JOIN Services s ON us.ServiceID = s.ServiceID
				WHERE us.StatusSer = 1 AND s.DurationDays = 1""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {

			while (rs.next()) {
				var userServiceID = rs.getInt("UserServiceID");
				var memberID = rs.getInt("memberID");
				var serviceID = rs.getInt("ServiceID");
				var serviceName = rs.getString("ServiceName");
				Date dayStart = rs.getDate("Daystart");
				Date dayEnd = rs.getDate("Dayend");
				var status = rs.getBoolean("StatusSer");

				// Tạo đối tượng UserServices và thêm vào danh sách
				var userService = new UserServices(userServiceID, memberID, serviceID, serviceName, dayStart, dayEnd,
						status);
				userServicesList.add(userService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userServicesList;
	}

	public boolean insertStaffService(int staffID, int userServiceID) {
		var sql = "INSERT INTO StaffServices (StaffID, UserServiceID) VALUES (?, ?)";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, staffID);
			ps.setInt(2, userServiceID);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<StaffService> getAllStaffServices() {
		List<StaffService> staffServiceList = new ArrayList<>();
		var sql = "SELECT * FROM StaffServices";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {
			while (rs.next()) {
				var staffService = new StaffService(rs.getInt("StaffServiceID"), rs.getInt("StaffID"),
						rs.getInt("UserServiceID"), rs.getDate("AssignmentDate"), rs.getBoolean("StatusDone"));
				staffServiceList.add(staffService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffServiceList;
	}

	public boolean updateStaffService(int staffServiceID, int staffID, int userServiceID) {
		var sql = "UPDATE StaffServices SET StaffID = ?, UserServiceID = ? WHERE StaffServiceID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, staffID);
			ps.setInt(2, userServiceID);
			ps.setInt(3, staffServiceID);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//check đã làm chưa
	public boolean isNotCompleteByStaffServiceID(int staffServiceID) {
		var isNotComplete = false;
		var sql = "SELECT StatusDone FROM StaffServices WHERE staffServiceID = ?";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, staffServiceID);
			var rs = ps.executeQuery();
			if (rs.next()) {
				isNotComplete = rs.getInt("StatusDone") == 1; // Nếu StatusDone = 1 → chưa hoàn thành
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isNotComplete;
	}

	public boolean deleteStaffService(int staffServiceID) {
		var sql = "DELETE FROM StaffServices WHERE StaffServiceID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, staffServiceID);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	///// check staff có lamf xong chưa
	public boolean isStatusComplete(int staffID) {
		var isComplete = false;
		var sql = "SELECT StatusDone FROM StaffServices WHERE staffID = ?";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, staffID);
			var rs = ps.executeQuery();
			if (rs.next()) {
				isComplete = rs.getInt("StatusDone") == 1; // Lấy giá trị BIT (0 = false, 1 = true)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isComplete;
	}

}
