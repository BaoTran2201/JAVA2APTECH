package Dao;

import java.util.ArrayList;
import java.util.List;

import model.Service;
import service.ConnectDB;

public class ServiesDAO {
//get all
	public List<Service> getAllServices() {
		List<Service> services = new ArrayList<>();
		var sql = "SELECT * FROM Services";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {

			while (rs.next()) {
				var s = new Service(rs.getInt("ServiceID"), rs.getString("ServiceName"),
						rs.getString("Description"), rs.getDouble("Price"), rs.getInt("DurationDays"),
						rs.getBoolean("ServiceStatus"));
				services.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return services;
	}

//add
	public boolean addService(Service s) {
		var sql = "INSERT INTO Services (ServiceName, Description, Price, DurationDays,ServiceStatus) VALUES (?, ?, ?, ?, ?)";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setString(1, s.getServiceName());
			ps.setString(2, s.getDescription());
			ps.setDouble(3, s.getPrice());
			ps.setInt(4, s.getDurationDays());
			ps.setBoolean(5, s.isServiceStatus());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//update
	public boolean updateService(Service s) {
		var sql = "UPDATE services SET ServiceName = ?, Description = ?, Price = ?, DurationDays = ?,ServiceStatus = ? WHERE ServiceID = ?";
		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql)) {
			ps.setString(1, s.getServiceName());
			ps.setString(2, s.getDescription());
			ps.setDouble(3, s.getPrice());
			ps.setInt(4, s.getDurationDays());
			ps.setBoolean(5, s.isServiceStatus());
			ps.setInt(6, s.getServiceID());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Service> getActiveServices() {
		List<Service> services = new ArrayList<>();
		var sql = "SELECT ServiceID, ServiceName, Description, Price , DurationDays FROM Services WHERE ServiceStatus = 1";

		try (var con = ConnectDB.getCon(); var ps = con.prepareStatement(sql); var rs = ps.executeQuery()) {

			while (rs.next()) {
				var s = new Service();
				s.setServiceID(rs.getInt("ServiceID"));
				s.setServiceName(rs.getString("ServiceName"));
				s.setDescription(rs.getString("Description"));
				s.setPrice(rs.getDouble("Price"));
				s.setDurationDays(rs.getInt("DurationDays"));
				services.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return services;
	}
}
