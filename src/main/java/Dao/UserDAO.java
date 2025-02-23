package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import service.ConnectDB;

public class UserDAO {

	// 🔹 Lấy danh sách tất cả user (Chỉ lấy user còn hoạt động)
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		var sql = "SELECT * FROM members WHERE memberStatus = 1";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {
			while (rs.next()) {
				users.add(mapResultSetToUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	// 🔹 Lấy user theo ID
	public User getUserById(int id) {
		User user = null;
		var sql = "SELECT * FROM members WHERE memberID = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					user = mapResultSetToUser(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// 🔹 Lấy user theo ID căn hộ
	public User getUserByApartmentID(int apartmentID) {
		User user = null;
		var sql = "SELECT * FROM members WHERE apartmentID = ? AND memberStatus = 1";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, apartmentID);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					user = mapResultSetToUser(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// 🔹 Thêm user mới
	// 🔹 Thêm user mới
	public boolean addUser(User user) {
		var apartmentID = user.getApartmentID();

		// Nếu apartmentID = 0, tìm theo số phòng
		if (apartmentID == 0 && user.getApartmentNumber() != null) {
			apartmentID = getApartmentIDFromNumber(user.getApartmentNumber());
			if (apartmentID == -1) {
				System.out.println("⚠ LỖI: Không tìm thấy ApartmentID cho số phòng " + user.getApartmentNumber());
				return false;
			}
		}

		var sql = """
				INSERT INTO members (memberName, identityImage, country, Phone, Email, apartmentID, memberStatus)
				VALUES (?, ?, ?, ?, ?, ?, ?)
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getMemberName());
			stmt.setString(2, user.getIdentityImage());
			stmt.setString(3, user.getCountry());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, apartmentID);
			stmt.setBoolean(7, user.isMemberStatus());

			var inserted = stmt.executeUpdate() > 0;

			// Nếu thêm thành công, cập nhật trạng thái căn hộ thành "Đã cho thuê"
			if (inserted) {
				updateApartmentStatus(apartmentID, 2); // 2 = đã cho thuê
			}
			return inserted;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 🔹 Cập nhật trạng thái căn hộ (1: Trống, 2: Đã cho thuê, 3: Chờ ký HĐ, 4: Bảo
	// trì, 5: Dọn dẹp)
	public boolean updateApartmentStatus(int apartmentID, int status) {
		var sql = "UPDATE Apartments SET Apartments_Status = ? WHERE ApartmentID = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, status);
			stmt.setInt(2, apartmentID);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 🔹 Cập nhật thông tin user
	public boolean updateUser(User user) {
		var sql = "UPDATE members SET memberName=?, identityImage=?, country=?, Phone=?, Email=?, apartmentID=?, memberStatus=? WHERE memberID=?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getMemberName());
			stmt.setString(2, user.getIdentityImage());
			stmt.setString(3, user.getCountry());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getApartmentID());
			stmt.setBoolean(7, user.isMemberStatus());
			stmt.setInt(8, user.getMemberID());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 🔹 Xóa user (Chuyển trạng thái thành 0)
	public boolean deleteUser(int id) {
		var sql = "UPDATE members SET memberStatus = 0 WHERE memberID = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 🔹 Chuyển ResultSet → User Object
	private User mapResultSetToUser(ResultSet rs) throws SQLException {
		var apartmentID = rs.getInt("apartmentID");
		var apartmentNumber = getApartmentNumberFromID(apartmentID); // 📌 Lấy số phòng từ ID

		return new User(rs.getInt("memberID"), rs.getString("memberName"), rs.getString("identityImage"),
				rs.getString("country"), rs.getDate("dob"), // ⚠ Đã sửa: Lấy ngày sinh từ ResultSet
				rs.getDate("startDate"), // ⚠ Đã sửa: Lấy ngày bắt đầu từ ResultSet
				rs.getDate("endDate"), // ⚠ Đã sửa: Lấy ngày kết thúc từ ResultSet
				rs.getInt("quantity"), // ⚠ Đã sửa: Số lượng thành viên
				rs.getString("Phone"), rs.getString("Email"), rs.getInt("verifyCode"), // ⚠ Đã sửa: Mã xác nhận
				rs.getBoolean("gender"), // ⚠ Đã sửa: Giới tính
				apartmentID, rs.getBoolean("memberStatus"), // ⚠ Đã sửa: Trạng thái thành viên
				apartmentNumber);
	}

	// 🔍 Tìm ApartmentID từ số phòng (ApartmentNumber)
	public int getApartmentIDFromNumber(String apartmentNumber) {
		var sql = "SELECT ApartmentID FROM Apartments WHERE ApartmentNumber = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, apartmentNumber);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("ApartmentID");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 🚨 Nếu không tìm thấy, trả về -1
	}

	// 🔍 Tìm ApartmentNumber từ ApartmentID
	private String getApartmentNumberFromID(int apartmentID) {
		if (apartmentID == 0) {
			return null; // Nếu không có căn hộ, trả về null
		}

		var sql = "SELECT ApartmentNumber FROM Apartments WHERE ApartmentID = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, apartmentID);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("ApartmentNumber");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Nếu không tìm thấy, trả về null
	}
}
