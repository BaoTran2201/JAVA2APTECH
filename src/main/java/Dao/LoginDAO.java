package Dao;

import java.util.ArrayList;
import java.util.List;

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
			try (var con = ConnectDB.getCon();
					var ps = con.prepareStatement(sql)) {

				ps.setString(1, t.getUsername());
				ps.setString(2, t.getPass());
				ps.setString(3, t.getEmail());

				var rowsAffected = ps.executeUpdate(); // Thực hiện chèn dữ liệu

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
			} catch (Exception e) {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public List<Login> getUsersByRole() {
			List<Login> list = new ArrayList<>();
			var sql = "SELECT * FROM Login WHERE jobRole = 'user'";

			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {
				while (rs.next()) {
					var user = new Login();
					user.setMemberID(rs.getInt("memberID"));
					user.setUsername(rs.getString("username"));
					user.setPass(rs.getString("pass")); // Lấy mật khẩu
					user.setEmail(rs.getString("email"));
					user.setJobRole(rs.getString("jobRole"));
					user.setLoginStatus(rs.getBoolean("loginStatus"));
					list.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		public void updateLoginStatusByID(int memberID, boolean status) {
			var sql = "UPDATE Login SET loginStatus = ? WHERE memberID = ?";
			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setBoolean(1, status);
				ps.setInt(2, memberID);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public List<Login> searchUsers(String keyword) {
			List<Login> users = new ArrayList<>();
			var sql = "SELECT memberID, username, pass, email, jobRole, loginStatus "
					+ "FROM Login WHERE username LIKE ? OR email LIKE ?";

			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

				ps.setString(1, "%" + keyword + "%");
				ps.setString(2, "%" + keyword + "%");

				try (var rs = ps.executeQuery()) {
					while (rs.next()) {
						users.add(new Login(rs.getInt("memberID"), rs.getString("username"), rs.getString("pass"),
								rs.getString("email"), rs.getString("jobRole"), rs.getBoolean("loginStatus")));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return users;
		}

		public void updateUsernameByID(int memberID, String newUsername) {
			var sql = "UPDATE Login SET username = ? WHERE memberID = ?";
			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setString(1, newUsername);
				ps.setInt(2, memberID);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void updatePasswordByID(int memberID, String newPassword) {
			var sql = "UPDATE Login SET pass = ? WHERE memberID = ?";
			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setString(1, newPassword);
				ps.setInt(2, memberID);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void updateEmailByID(int memberID, String newEmail) {
			var sql = "UPDATE Login SET email = ? WHERE memberID = ?";
			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setString(1, newEmail);
				ps.setInt(2, memberID);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public boolean isUsername(String username) {
			var sql = "SELECT COUNT(*) FROM Login WHERE username = ?";
			try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
				ps.setString(1, username);
				try (var rs = ps.executeQuery()) {
					if (rs.next()) {
						return rs.getInt(1) > 0;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

}
