package Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.UserServices;
import service.ConnectDB;

public class UserServiceDAO {

	public boolean registerService(int userID, int serviceID) {
		var sql = "INSERT INTO UserServices (memberID, ServiceID, Daystart, Dayend, StatusSer) VALUES (?, ?, NULL, NULL, 0)";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, userID);
			ps.setInt(2, serviceID);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<UserServices> getRegisteredServices(int userID) {
        List<UserServices> services = new ArrayList<>();
        var sql = """
				SELECT us.UserServiceID, us.memberID, us.ServiceID, s.ServiceName,
				us.Daystart, us.Dayend, us.StatusSer
				FROM UserServices us
				JOIN Services s ON us.ServiceID = s.ServiceID
			WHERE us.memberID = ?""";

        try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			ps.setInt(1, userID);
            var rs = ps.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng trước
				var userService = new UserServices(rs.getInt("UserServiceID"), rs.getInt("memberID"),
						rs.getInt("ServiceID"), rs.getString("ServiceName"), rs.getDate("Daystart"),
						rs.getDate("Dayend"), rs.getBoolean("StatusSer"));

                // Thêm vào danh sách
				services.add(userService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

	public boolean deleteUserService(int userServiceID) {
		var sql = "DELETE FROM UserServices WHERE UserServiceID = ? AND StatusSer = 0";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			ps.setInt(1, userServiceID);

			var affectedRows = ps.executeUpdate();
			return affectedRows > 0;

		} catch (Exception e) {
			System.err.println("Lỗi khi xóa dịch vụ: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public List<UserServices> loadUserServices() {
		List<UserServices> userServicesList = new ArrayList<>();

		var sql = """
				SELECT us.UserServiceID, us.memberID, us.ServiceID, s.ServiceName,
				us.Daystart, us.Dayend, us.StatusSer
				FROM UserServices us
				JOIN Services s ON us.ServiceID = s.ServiceID""";

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

	public boolean updateUserServiceStatus(int userServiceID, boolean status) {
		var sql = "UPDATE UserServices SET StatusSer = ? WHERE UserServiceID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			ps.setBoolean(1, status);
			ps.setInt(2, userServiceID);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<UserServices> searchService(String keyword) {
		List<UserServices> services = new ArrayList<>();
		var sql = """
				     SELECT us.UserServiceID, us.memberID, s.ServiceID, s.ServiceName,
				           us.Daystart, us.Dayend, us.StatusSer
				    FROM UserServices us
				    JOIN Services s ON us.ServiceID = s.ServiceID
				    WHERE us.memberID LIKE ? OR s.ServiceName LIKE ?;
				""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			var rs = ps.executeQuery();

			while (rs.next()) {
				var service = new UserServices(rs.getInt("UserServiceID"), rs.getInt("MemberID"),
						rs.getInt("ServiceID"), rs.getString("ServiceName"), rs.getDate("Daystart"),
						rs.getDate("Dayend"), rs.getBoolean("StatusSer"));
				services.add(service);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return services;
	}

}
