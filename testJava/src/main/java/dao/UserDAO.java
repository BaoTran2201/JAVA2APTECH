package dao;

import service.ConnectDB;

public class UserDAO {

	// Phương thức kiểm tra đăng nhập và lấy thông tin người dùng
	public static boolean validateUser(String username, String password) {
		var isValid = false;
		var sql = "SELECT JobRole FROM login WHERE Username = ? AND Pass = ?";
		try (var con = ConnectDB.getCon(); var stmt = con.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, password);

			var rs = stmt.executeQuery();
			if (rs.next()) {
				isValid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isValid;
	}

	// Phương thức lấy vai trò (role) của người dùng
	public static String getUserRole(String username, String password) {
		var role = "";
		var sql = "SELECT JobRole FROM login WHERE Username = ? AND Pass = ?";

		try (var con = ConnectDB.getCon(); var stmt = con.prepareStatement(sql)) {

			stmt.setString(1, username);
			stmt.setString(2, password);

			var rs = stmt.executeQuery();

			if (rs.next()) {
				role = rs.getString("JobRole");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return role;
	}
}