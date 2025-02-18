package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Building;
import service.ConnectDB;

public class BuildingDAO {

	// 🔍 Lấy danh sách tất cả các tòa nhà
	public List<Building> getAllBuildings() {
		List<Building> buildings = new ArrayList<>();
		var sql = "SELECT * FROM Building";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {

			while (rs.next()) {
				buildings.add(new Building(rs.getInt("BuildingID"), rs.getString("BuildingName"),
						rs.getInt("TotalFloors"), rs.getString("BuildingStatus")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buildings;
	}

	// 📌 Lấy ID của tòa nhà theo tên
	public int getBuildingID(String buildingName) {
		var sql = "SELECT BuildingID FROM Building WHERE BuildingName = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, buildingName);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("BuildingID");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // Không tìm thấy
	}

	// ✅ Thêm một tòa nhà mới vào database
	public boolean addBuilding(Building building) {
		var sql = "INSERT INTO Building (BuildingName, TotalFloors, BuildingStatus) VALUES (?, ?, ?)";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, building.getBuildingName());
			stmt.setInt(2, building.getTotalFloors());
			stmt.setString(3, building.getBuildingStatus());

			return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 🗑️ Xóa tòa nhà theo ID
	public boolean deleteBuilding(int buildingID) {
		var sql = "DELETE FROM Building WHERE BuildingID = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, buildingID);
			return stmt.executeUpdate() > 0; // Trả về true nếu xóa thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 🔄 Cập nhật thông tin tòa nhà
	public boolean updateBuilding(Building building) {
		var sql = "UPDATE Building SET BuildingName = ?, TotalFloors = ?, BuildingStatus = ? WHERE BuildingID = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, building.getBuildingName());
			stmt.setInt(2, building.getTotalFloors());
			stmt.setString(3, building.getBuildingStatus());
			stmt.setInt(4, building.getBuildingID());

			return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
