package Dao;

import model.Member;
import service.ConnectDB;

public class MemberDAO {
	public void insertMember(Member t, int memberID) {
		var sql = "INSERT INTO members (memberID, memberName, avatar, country, dob, StartDate, EndDate, quantity, Phone, cccd, verifyCode, gender, apartmentID, memberStatus, identityImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, memberID);
			ps.setString(2, t.getMemberName());
			ps.setString(3, t.getAvatar());
			ps.setString(4, t.getCountry());
			ps.setDate(5, java.sql.Date.valueOf(t.getDob()));
			ps.setDate(6, java.sql.Date.valueOf(t.getStartDate()));
			ps.setDate(7, java.sql.Date.valueOf(t.getEndDate()));
			ps.setInt(8, t.getQuantity());
			ps.setString(9, t.getPhone());
			ps.setString(10, t.getCccd());
			ps.setInt(11, t.getVerifyCode());
			ps.setBoolean(12, t.isGender());
			ps.setInt(13, t.getApartmentID());
			ps.setBoolean(14, t.isMemberStatus());
			ps.setString(15, t.getIdentityImage());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member getMemberByID(int memberID) {
		var sql = "SELECT * FROM members WHERE memberID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setInt(1, memberID);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Member(rs.getInt("memberID"), rs.getString("memberName"), rs.getString("avatar"),
							rs.getString("country"), rs.getDate("dob").toLocalDate(),
							rs.getDate("StartDate").toLocalDate(), rs.getDate("EndDate").toLocalDate(),
							rs.getInt("quantity"), rs.getString("Phone"), rs.getString("cccd"), rs.getInt("verifyCode"),
							rs.getBoolean("gender"), rs.getInt("apartmentID"), rs.getBoolean("memberStatus"),
							rs.getString("identityImage"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // Không tìm thấy thành viên
	}

	public boolean updateMemberByID(Member member) {
		var sql = "UPDATE members SET memberName = ?, phone = ?, cccd = ?, country = ?, gender = ?, dob = ?,avatar = ? WHERE memberID = ?";
		try (var con = ConnectDB.getCon(); var stmt = con.prepareStatement(sql)) {
			stmt.setString(1, member.getMemberName());
			stmt.setString(2, member.getPhone());
			stmt.setString(3, member.getCccd());
			stmt.setString(4, member.getCountry());
			stmt.setBoolean(5, member.isGender());
			stmt.setDate(6, java.sql.Date.valueOf(member.getDob()));
			stmt.setString(7, member.getAvatar()); // Lưu đường dẫn ảnh vào DB
			stmt.setInt(8, member.getMemberID()); // WHERE memberID = ?
			var rowsUpdated = stmt.executeUpdate();
			System.out.println("Rows affected: " + rowsUpdated);
			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; // Trả về false nếu có lỗi xảy ra
	}


}
