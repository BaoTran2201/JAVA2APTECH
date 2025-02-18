package Dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import service.ConnectDB;

public class MemberDAO {

	// Lấy thông tin chủ sở hữu căn hộ theo ApartmentID
	public Member getOwnerByApartmentID(int apartmentID) {
		var sql = """
				SELECT m.memberID, m.memberName, m.avatar, m.country, m.dob,
				       m.StartDate, m.EndDate, m.quantity, m.Phone, m.Email,
				       m.verifyCode, m.gender, m.apartmentID, m.memberStatus
				FROM members m
				JOIN contracts c ON m.memberID = c.memberID
				WHERE c.ApartmentID = ?
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, apartmentID);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Member(rs.getInt("memberID"), rs.getString("memberName"), rs.getString("avatar"),
							rs.getString("country"), rs.getDate("dob"), rs.getDate("StartDate"), rs.getDate("EndDate"),
							rs.getInt("quantity"), rs.getString("Phone"), rs.getString("Email"),
							rs.getInt("verifyCode"), rs.getBoolean("gender"), rs.getInt("apartmentID"),
							rs.getBoolean("memberStatus"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Trả về null nếu không tìm thấy chủ sở hữu căn hộ
	}

	// Lấy danh sách tất cả thành viên
	public List<Member> getAllMembers() {
		List<Member> members = new ArrayList<>();
		var sql = """
				SELECT memberID, memberName, avatar, country, dob, StartDate, EndDate,
				       quantity, Phone, Email, verifyCode, gender, apartmentID, memberStatus
				FROM members
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {

			while (rs.next()) {
				members.add(new Member(rs.getInt("memberID"), rs.getString("memberName"), rs.getString("avatar"),
						rs.getString("country"), rs.getDate("dob"), rs.getDate("StartDate"), rs.getDate("EndDate"),
						rs.getInt("quantity"), rs.getString("Phone"), rs.getString("Email"), rs.getInt("verifyCode"),
						rs.getBoolean("gender"), rs.getInt("apartmentID"), rs.getBoolean("memberStatus")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	// Thêm thành viên mới
	public boolean addMember(Member member) {
		var sql = """
				INSERT INTO members (memberName, avatar, country, dob, StartDate, EndDate,
				                     quantity, Phone, Email, verifyCode, gender, apartmentID, memberStatus)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, member.getMemberName());
			stmt.setString(2, member.getAvatar());
			stmt.setString(3, member.getCountry());
			stmt.setDate(4, member.getDob());
			stmt.setDate(5, member.getStartDate());
			stmt.setDate(6, member.getEndDate());
			stmt.setInt(7, member.getQuantity());
			stmt.setString(8, member.getPhone());
			stmt.setString(9, member.getEmail());
			stmt.setInt(10, member.getVerifyCode());
			stmt.setBoolean(11, member.isGender());
			stmt.setObject(12, member.getApartmentID(), Types.INTEGER);
			stmt.setBoolean(13, member.isMemberStatus());

			return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Cập nhật thông tin thành viên
	public boolean updateMember(Member member) {
		var sql = """
				UPDATE members
				SET memberName = ?, avatar = ?, country = ?, dob = ?, StartDate = ?, EndDate = ?,
				    quantity = ?, Phone = ?, Email = ?, verifyCode = ?, gender = ?, apartmentID = ?, memberStatus = ?
				WHERE memberID = ?
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, member.getMemberName());
			stmt.setString(2, member.getAvatar());
			stmt.setString(3, member.getCountry());
			stmt.setDate(4, member.getDob());
			stmt.setDate(5, member.getStartDate());
			stmt.setDate(6, member.getEndDate());
			stmt.setInt(7, member.getQuantity());
			stmt.setString(8, member.getPhone());
			stmt.setString(9, member.getEmail());
			stmt.setInt(10, member.getVerifyCode());
			stmt.setBoolean(11, member.isGender());
			stmt.setObject(12, member.getApartmentID(), Types.INTEGER);
			stmt.setBoolean(13, member.isMemberStatus());
			stmt.setInt(14, member.getMemberID());

			return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Xóa thành viên theo ID
	public boolean deleteMember(int memberID) {
		var sql = "DELETE FROM members WHERE memberID = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, memberID);
			return stmt.executeUpdate() > 0; // Trả về true nếu xóa thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
