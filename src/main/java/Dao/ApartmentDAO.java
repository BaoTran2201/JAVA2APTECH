package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Apartment;
import service.ConnectDB;

public class ApartmentDAO {
	// Lấy danh sách tất cả tòa nhà (chỉ lấy tên)
	public List<String> getAllBuildings() {
		List<String> buildings = new ArrayList<>();
		var sql = "SELECT BuildingName FROM Building";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {
			while (rs.next()) {
				buildings.add(rs.getString("BuildingName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buildings;
	}

	// Lấy danh sách tất cả tầng của tòa nhà theo BuildingID
	public List<String> getFloorsByBuildingID(int buildingID) {
		List<String> floors = new ArrayList<>();
		var sql = "SELECT FloorNumber FROM floor WHERE BuildingID = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, buildingID);
			try (var rs = stmt.executeQuery()) {
				while (rs.next()) {
					floors.add(rs.getString("FloorNumber"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return floors;
	}

	// Lấy BuildingID từ tên tòa nhà
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
		return -1;
	}

	// Kiểm tra xem tầng đã tồn tại chưa
	public int getFloorID(int buildingID, int floorNumber) {
		var sql = "SELECT FloorID FROM floor WHERE BuildingID = ? AND FloorNumber = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, buildingID);
			stmt.setInt(2, floorNumber);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("FloorID"); // 🔹 Trả về FloorID nếu tầng đã tồn tại
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 🔸 Trả về -1 nếu tầng chưa tồn tại
	}

	// Thêm tầng mới vào database nếu chưa có
	public int addNewFloor(int buildingID, int floorNumber) {
		var sql = "INSERT INTO floor (BuildingID, FloorNumber, FloorName) OUTPUT INSERTED.FloorID VALUES (?, ?, ?)";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, buildingID);
			stmt.setInt(2, floorNumber);
			stmt.setString(3, "Tầng " + floorNumber);
			try (var rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1); // 🔹 Trả về FloorID của tầng vừa tạo
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 🔸 Trả về -1 nếu thêm tầng thất bại
	}

	// Lấy danh sách tất cả các căn hộ
	public List<Apartment> getAllApartments() {
		List<Apartment> apartments = new ArrayList<>();
		var sql = """
				SELECT a.ApartmentID, a.BuildingID, a.FloorID,
				       a.ApartmentNumber, a.ApartmentType, a.Area, a.Apartments_Status
				FROM Apartments a
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {

			while (rs.next()) {
				apartments.add(new Apartment(rs.getInt("ApartmentID"), rs.getInt("BuildingID"), rs.getInt("FloorID"),
						rs.getString("ApartmentNumber"), rs.getString("ApartmentType"), rs.getDouble("Area"),
						rs.getInt("Apartments_Status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apartments;
	}

	// Thêm phòng mới vào database
	public boolean addApartment(Apartment apartment) {
		var sql = "INSERT INTO Apartments (BuildingID, FloorID, ApartmentNumber, ApartmentType, Area, Apartments_Status) VALUES (?, ?, ?, ?, ?, ?)";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, apartment.getBuildingID());
			stmt.setInt(2, apartment.getFloorID());
			stmt.setString(3, apartment.getApartmentNumber());
			stmt.setString(4, apartment.getApartmentType());
			stmt.setDouble(5, apartment.getArea());
			stmt.setInt(6, 1); // ⚡ Luôn đặt trạng thái mặc định là "Căn hộ trống"

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Apartment> getApartmentsByFloor() {
		List<Apartment> apartments = new ArrayList<>();
		var sql = """
				    SELECT a.ApartmentID, f.FloorNumber, a.ApartmentNumber, a.Apartments_Status
				    FROM Apartments a
				    JOIN Floor f ON a.FloorID = f.FloorID
				    ORDER BY f.FloorNumber, a.ApartmentNumber
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {
			while (rs.next()) {
				apartments.add(new Apartment(rs.getInt("ApartmentID"), -1, // Không cần BuildingID
						rs.getInt("FloorNumber"), rs.getString("ApartmentNumber"), "", // Không cần ApartmentType
						0, // Không cần diện tích
						rs.getInt("Apartments_Status") // Lấy trạng thái căn hộ
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apartments;
	}

	public List<Apartment> getApartmentsByStatus(int status) {
		List<Apartment> apartments = new ArrayList<>();
		var sql = "SELECT * FROM Apartments WHERE Apartments_Status = ?";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, status);
			try (var rs = stmt.executeQuery()) {
				while (rs.next()) {
					apartments.add(new Apartment(rs.getInt("ApartmentID"), rs.getInt("BuildingID"),
							rs.getInt("FloorID"), rs.getString("ApartmentNumber"), rs.getString("ApartmentType"),
							rs.getDouble("Area"), rs.getInt("Apartments_Status")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apartments;
	}

	public boolean updateApartmentStatus(int apartmentID, int status) {
		var sql = "UPDATE Apartments SET Apartments_Status = ? WHERE ApartmentID = ?"; // Đảm bảo tên cột đúng

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, status);
			stmt.setInt(2, apartmentID);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<String> getAvailableApartments() {
		List<String> apartments = new ArrayList<>();
		var sql = """
				    SELECT a.ApartmentID, a.ApartmentNumber
				    FROM Apartments a
				    LEFT JOIN contracts c ON a.ApartmentID = c.ApartmentID
				    WHERE c.ApartmentID IS NULL
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {
			while (rs.next()) {
				apartments.add(rs.getInt("ApartmentID") + " - " + rs.getString("ApartmentNumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apartments;
	}

	public boolean updateApartment(Apartment apartment) {
		var sql = """
				    UPDATE Apartments
				    SET BuildingID = ?, FloorID = ?, ApartmentNumber = ?, ApartmentType = ?, Area = ?, Apartments_Status = ?
				    WHERE ApartmentID = ?
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, apartment.getBuildingID());
			stmt.setInt(2, apartment.getFloorID());
			stmt.setString(3, apartment.getApartmentNumber());
			stmt.setString(4, apartment.getApartmentType());
			stmt.setDouble(5, apartment.getArea());
			stmt.setInt(6, apartment.getApartmentsStatus());
			stmt.setInt(7, apartment.getApartmentID());

			return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean restoreApartment(int apartmentID) {
		var sql = "UPDATE Apartments SET Apartments_Status = 2 WHERE ApartmentID = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, apartmentID);
			return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean moveApartmentToTrash(int apartmentID) {
		var sql = "UPDATE Apartments SET Apartments_Status = 0 WHERE ApartmentID = ?";
		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, apartmentID);
			return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Apartment> getDeletedApartments() {
		List<Apartment> deletedApartments = new ArrayList<>();
		var sql = """
				  SELECT ApartmentID, BuildingID, FloorID, ApartmentNumber, ApartmentType, Area
				  FROM Apartments
				  WHERE Apartments_Status = 0
				""";

		try (var conn = ConnectDB.getCon(); var stmt = conn.prepareStatement(sql); var rs = stmt.executeQuery()) {
			while (rs.next()) {
				deletedApartments.add(new Apartment(rs.getInt("ApartmentID"), rs.getInt("BuildingID"),
						rs.getInt("FloorID"), rs.getString("ApartmentNumber"), rs.getString("ApartmentType"),
						rs.getDouble("Area"), 0)); // Trạng thái là 0
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletedApartments;
	}

}
