package Dao;

import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Feedback;
import model.FeedbackImage;
import service.ConnectDB;

public class FeedbackDAO {

	public int insertFeedback(Feedback feedback, int userID) {
		var sql = "INSERT INTO Feedback (MemberID, Namefb, FeedbackTittle, Note, Statusfb) VALUES (?, ?, ?, ?, ?)";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, userID);
			ps.setString(2, feedback.getNamefb());
			ps.setString(3, feedback.getFeedbackTittle());
			ps.setString(4, feedback.getNote());
			ps.setBoolean(5, feedback.isStatusfb());

			var affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				try (var rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						return rs.getInt(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void insertFeedbackImages(int feedbackID, List<String> imagePaths) {
		var sql = "INSERT INTO FeedbackImages (FeedbackID, ImagePath) VALUES (?, ?)";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			for (String path : imagePaths) {
				ps.setInt(1, feedbackID);
				ps.setString(2, path);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Lấy danh sách ảnh của một Feedback theo ID
	public List<FeedbackImage> getImagesByFeedbackID(int feedbackID) {
		List<FeedbackImage> imageList = new ArrayList<>();
		var sql = "SELECT ImageID, FeedbackID, ImagePath FROM FeedbackImages WHERE FeedbackID = ?";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, feedbackID);
			var rs = ps.executeQuery();

			while (rs.next()) {
				imageList.add(
						new FeedbackImage(rs.getInt("ImageID"), rs.getInt("FeedbackID"), rs.getString("ImagePath")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageList;
	}

	// lấy all feedback
	public List<Feedback> getAllFeedbacks() {
		List<Feedback> feedbackList = new ArrayList<>();
		var sql = "SELECT * FROM Feedback"; // Lấy tất cả các cột từ bảng Feedback

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {
			while (rs.next()) {
				var feedback = new Feedback(rs.getInt("FeedbackID"), rs.getInt("memberID"), rs.getString("namefb"),
						rs.getString("feedbackTittle"), rs.getString("note"), rs.getTimestamp("FeedbackDate"),
						rs.getBoolean("Statusfb"));
				feedbackList.add(feedback);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackList;
	}

	// search feed
	public List<Feedback> searchFeedback(String keyword) {
		List<Feedback> feedbackList = new ArrayList<>();
		var sql = "SELECT FeedbackID, memberID, namefb, feedbackTittle, note, FeedbackDate, Statusfb "
				+ "FROM Feedback WHERE namefb LIKE ? OR feedbackTittle LIKE ?";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + keyword + "%"); // Tìm kiếm theo tên người phản hồi
			ps.setString(2, "%" + keyword + "%"); // Tìm kiếm theo tiêu đề phản hồi

			try (var rs = ps.executeQuery()) {
				while (rs.next()) {
					feedbackList.add(new Feedback(rs.getInt("FeedbackID"), rs.getInt("memberID"),
							rs.getString("namefb"),
							rs.getString("feedbackTittle"), rs.getString("note"), rs.getTimestamp("FeedbackDate"),
							rs.getBoolean("Statusfb")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackList;
	}

//lấy ảnh
	public List<String> getFeedbackImages(int feedbackID) {
		List<String> images = new ArrayList<>();
		var sql = "SELECT imagePath FROM FeedbackImages WHERE feedbackID = ?";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, feedbackID);
			var rs = ps.executeQuery();
			while (rs.next()) {
				images.add(rs.getString("imagePath"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return images;
	}

//update feedback
	public boolean updateFeedbackStatusByID(int feedbackID, boolean newStatus) {
		var sql = "UPDATE Feedback SET Statusfb = ? WHERE feedbackID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setBoolean(1, newStatus);
			ps.setInt(2, feedbackID);
			var rowsUpdated = ps.executeUpdate();
			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Feedback> getFeedbackByDateRange(String fromDate, String toDate) {
		List<Feedback> feedbackList = new ArrayList<>();
		var sql = "SELECT * FROM Feedback WHERE feedbackDate >= ? AND feedbackDate <= ?";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setString(1, fromDate);
			ps.setString(2, toDate);
			var rs = ps.executeQuery();

			while (rs.next()) {
				feedbackList.add(new Feedback(rs.getInt("FeedbackID"), rs.getInt("memberID"), rs.getString("namefb"),
						rs.getString("feedbackTittle"), rs.getString("note"), rs.getTimestamp("FeedbackDate"),
						rs.getBoolean("Statusfb")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return feedbackList;
	}

	public List<Feedback> searchFeedbackByKeywordAndDate(String keyword, LocalDate fromDate, LocalDate toDate) {
		List<Feedback> feedbackList = new ArrayList<>();
		var sql = "SELECT * FROM Feedback WHERE FeedbackDate BETWEEN ? AND ?";

		// Nếu có từ khóa tìm kiếm, thêm điều kiện vào truy vấn SQL
		if (!keyword.isEmpty()) {
			sql += " AND (namefb LIKE ? OR feedbackTittle LIKE ? OR note LIKE ?)";
		}

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			ps.setTimestamp(1, Timestamp.valueOf(fromDate.atStartOfDay())); // Chuyển từ LocalDate sang Timestamp
			ps.setTimestamp(2, Timestamp.valueOf(toDate.atTime(23, 59, 59))); // Đặt đến cuối ngày

			// Nếu có từ khóa tìm kiếm, gán vào PreparedStatement
			if (!keyword.isEmpty()) {
				ps.setString(3, "%" + keyword + "%");
				ps.setString(4, "%" + keyword + "%");
				ps.setString(5, "%" + keyword + "%");
			}

			var rs = ps.executeQuery();
			while (rs.next()) {
				var fb = new Feedback();
				fb.setFeedbackID(rs.getInt("FeedbackID"));
				fb.setMemberID(rs.getInt("memberID"));
				fb.setNamefb(rs.getString("namefb"));
				fb.setFeedbackTittle(rs.getString("feedbackTittle"));
				fb.setNote(rs.getString("note"));
				fb.setFeedbackDate(rs.getTimestamp("FeedbackDate"));
				fb.setStatusfb(rs.getBoolean("Statusfb"));
				feedbackList.add(fb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return feedbackList;
	}


}

