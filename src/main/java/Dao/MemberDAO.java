package Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		return null;
	}

	public boolean updateMemberByID(Member t) {
		var sql = "UPDATE members SET memberName = ?, phone = ?, cccd = ?, country = ?, gender = ?, dob = ?,avatar = ? WHERE memberID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setString(1, t.getMemberName());
			ps.setString(2, t.getPhone());
			ps.setString(3, t.getCccd());
			ps.setString(4, t.getCountry());
			ps.setBoolean(5, t.isGender());
			ps.setDate(6, java.sql.Date.valueOf(t.getDob()));
			ps.setString(7, t.getAvatar());
			ps.setInt(8, t.getMemberID());
			var rowsUpdated = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsUpdated);
			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Member> getAllMembers() {
		List<Member> members = new ArrayList<>();
		var sql = """
				SELECT *FROM members m
				JOIN Login l ON m.memberID = l.memberID
				WHERE l.loginStatus = 1;""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {
			while (rs.next()) {
				members.add(new Member(rs.getInt("memberID"), rs.getString("memberName"), rs.getString("avatar"),
						rs.getString("country"), rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null,
						rs.getDate("StartDate") != null ? rs.getDate("StartDate").toLocalDate() : null,
						rs.getDate("EndDate") != null ? rs.getDate("EndDate").toLocalDate() : null,
						rs.getInt("quantity"), rs.getString("Phone"), rs.getString("cccd"), rs.getInt("verifyCode"),
						rs.getBoolean("gender"), rs.getInt("apartmentID"), rs.getBoolean("memberStatus"),
						rs.getString("identityImage")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}

	public boolean updateMember(Member member) {
		var sql = """
				UPDATE members
				SET memberName = ?, gender = ?, dob = ?, phone = ?, cccd = ?,
				    country = ?, quantity = ?, apartmentID = ?, startDate = ?, endDate = ?
				WHERE memberID = ?
				""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			// Gán giá trị cho câu lệnh UPDATE
			ps.setString(1, member.getMemberName());
			ps.setBoolean(2, member.isGender());
			ps.setDate(3, java.sql.Date.valueOf(member.getDob()));
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getCccd());
			ps.setString(6, member.getCountry());
			ps.setInt(7, member.getQuantity());
			ps.setInt(8, member.getApartmentID());
			ps.setDate(9, java.sql.Date.valueOf(member.getStartDate()));
			ps.setDate(10, java.sql.Date.valueOf(member.getEndDate()));
			ps.setInt(11, member.getMemberID());

			var rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateMember(int memberID, String memberName, boolean gender, LocalDate dob, String phone,
			String cccd, String country, int quantity, Integer apartmentID, LocalDate startDate, LocalDate endDate) {
		var sql = """
				UPDATE members
				SET memberName = ?, gender = ?, dob = ?, phone = ?, cccd = ?,
				country = ?, quantity = ?, apartmentID = ?, startDate = ?, endDate = ?
				WHERE memberID = ?
				""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

// Gán giá trị vào câu lệnh UPDATE
			ps.setString(1, memberName);
			ps.setBoolean(2, gender);
			ps.setDate(3, dob != null ? java.sql.Date.valueOf(dob) : null);
			ps.setString(4, phone);
			ps.setString(5, cccd);
			ps.setString(6, country);
			ps.setInt(7, quantity);
			if (apartmentID == null) {
				ps.setNull(8, java.sql.Types.INTEGER);
			} else {
				ps.setInt(8, apartmentID);
			}
			ps.setDate(9, startDate != null ? java.sql.Date.valueOf(startDate) : null);
			ps.setDate(10, endDate != null ? java.sql.Date.valueOf(endDate) : null);
			ps.setInt(11, memberID);

			var rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean updateLoginStatus(int userID, boolean status) {
		var sql = "UPDATE Login SET loginStatus = ? WHERE memberID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setBoolean(1, status);
			ps.setInt(2, userID);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Member getUserByApartmentID(int apartmentID) {
		var sql = """
				SELECT memberID, memberName, avatar, country, dob, StartDate, EndDate, \
				quantity, Phone, cccd, verifyCode, gender, apartmentID, memberStatus, identityImage \
				FROM members WHERE apartmentID = ?""";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {

			ps.setInt(1, apartmentID);
			var rs = ps.executeQuery();

			if (rs.next()) {
				return new Member(rs.getInt("memberID"), rs.getString("memberName"), rs.getString("avatar"),
						rs.getString("country"), rs.getDate("dob").toLocalDate(), rs.getDate("StartDate").toLocalDate(),
						rs.getDate("EndDate").toLocalDate(), rs.getInt("quantity"), rs.getString("Phone"),
						rs.getString("cccd"), rs.getInt("verifyCode"), rs.getBoolean("gender"),
						rs.getInt("apartmentID"), rs.getBoolean("memberStatus"), 
						rs.getString("identityImage"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
