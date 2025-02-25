package Dao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Login;
import service.ConnectDB;
public class LoginDAO {
	public Login selectByUser(String t) {
		 Login result = null;
	        try {
	        	var con = ConnectDB.getCon();
	            var sql = "SELECT * FROM login WHERE username=?";
				var ps = con.prepareStatement(sql);
				ps.setString(1, t);
				var rs = ps.executeQuery();
	            while(rs.next()){
					var memberID = rs.getInt("memberID");
	                var username = rs.getString("username");
					var pass = rs.getString("pass");
					var email = rs.getString("email");
					var jobRole = rs.getString("jobRole");
					var loginStatus = rs.getBoolean("loginStatus");
					var tk = new Login(memberID, username, pass, email, jobRole, loginStatus);
	                result = tk;
	            }

	        } catch (Exception e) {
	        }
	        return result;
	    }

//		public boolean isID(String id) {
//			try (var con = ConnectDB.getCon();
//					var ps = con.prepareStatement("SELECT COUNT(*) FROM Login WHERE memberID = ?")) {
//				ps.setString(1, id);
//				try (var rs = ps.executeQuery()) {
//					if (rs.next()) {
//						return rs.getInt(1) > 0;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return false;
//		}

		public void insert(Login t) {
			var sql = "INSERT INTO Login (username, pass, email) VALUES (?, ?, ?)";

			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setString(1, t.getUsername()); // Chỉ lấy username
				ps.setString(2, t.getPass()); // Mật khẩu
				ps.setString(3, t.getEmail()); // Email

				var rowsAffected = ps.executeUpdate(); // Kiểm tra xem có dòng nào được chèn không
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, "Registration successful!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Registration Failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		public boolean validateUser(String username, String email) {
			var sql = "SELECT * FROM Login WHERE username = ? AND email = ?";

			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setString(1, username);
				ps.setString(2, email);
				var rs = ps.executeQuery();
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		public void updatePassword(String username, String newPassword) {
			var sql = "UPDATE Login SET pass = ? WHERE username = ?";

			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setString(1, newPassword);
				ps.setString(2, username);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


}
