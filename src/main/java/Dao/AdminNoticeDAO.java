package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Notification;
import service.ConnectDB;

public class AdminNoticeDAO {
	public void addNotification(String title, String content) {
		var sql = "INSERT INTO Notifications (Title, Content, SentDate) VALUES (?, ?, GETDATE())";
		try (var con = ConnectDB.getCon(); var stmt = con.prepareStatement(sql)) {

			stmt.setString(1, title);
			stmt.setString(2, content);

			stmt.executeUpdate();
			System.out.println("Thông báo đã được thêm thành công!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Notification> getAllNotifications() {
		List<Notification> notifications = new ArrayList<>();
		var sql = "SELECT NotificationID, Title, Content, COALESCE(SentDate, GETDATE()) AS SentDate FROM Notifications ORDER BY SentDate DESC";

		try (var conn = ConnectDB.getCon();
				var stmt = conn.createStatement();
				var rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				var notification = new Notification(rs.getInt("NotificationID"), rs.getString("Title"),
						rs.getString("Content"), rs.getDate("SentDate") // Không còn NULL nữa
				);
				notifications.add(notification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notifications;
	}

}
