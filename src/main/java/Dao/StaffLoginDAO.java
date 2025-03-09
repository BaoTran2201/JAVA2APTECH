package Dao;

import model.StaffLogin;
import service.ConnectDB;

public class StaffLoginDAO {

	public StaffLogin getStaffByUsername(String username) {
		var sql = "SELECT * FROM staff_login WHERE username = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return new StaffLogin(rs.getInt("staffID"), rs.getString("username"), rs.getString("password"),
							rs.getBoolean("loginStatus"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
