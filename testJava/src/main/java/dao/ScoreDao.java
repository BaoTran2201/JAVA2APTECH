package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Score;
import service.ConnectDB;

public class ScoreDao implements DAOInterface<Score> {
	public static ScoreDao getInstance() {
		// TODO Auto-generated method stub
		return new ScoreDao();
	}
	@Override
	public void insert(Score t) {
		var sql = "{call InsertScore(?, ?, ?, ?, ?)}";

		try (var con = ConnectDB.getCon(); PreparedStatement ps = con.prepareCall(sql)) {

			ps.setString(1, t.getIdStu());
			ps.setString(2, t.getIdSub());
			ps.setBigDecimal(3, t.getSc1());
			ps.setBigDecimal(4, t.getSc2());
			ps.setBigDecimal(5, t.getSc3());

			var rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(null, "Insert Success");
			} else {
				JOptionPane.showMessageDialog(null, "Subject score already!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Score selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Score> selectall() {
		List<Score> list = new ArrayList<>();
		try (var con = ConnectDB.getCon();
				PreparedStatement cs = con.prepareCall("{call AllScores()}");
				var rs = cs.executeQuery()) {

			while (rs.next()) {
				var score = new Score();
				score.setIdStu(rs.getString("IDStu"));
				score.setIdSub(rs.getString("IDSub"));
				score.setSc1(rs.getBigDecimal("Sc1"));
				score.setSc2(rs.getBigDecimal("Sc2"));
				score.setSc3(rs.getBigDecimal("Sc3"));
				score.setScTotal(rs.getBigDecimal("Sctotal"));
				score.setRate(rs.getString("Rate"));
				list.add(score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exception properly
		}
		return list;
	}

	@Override
	public List<Score> selectid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Score t) {
		var sql = "{call UpdateScore(?, ?, ?, ?, ?)}";

		try (var con = ConnectDB.getCon(); PreparedStatement cs = con.prepareCall(sql)) {

			cs.setBigDecimal(1, t.getSc1());
			cs.setBigDecimal(2, t.getSc2());
			cs.setBigDecimal(3, t.getSc3());
			cs.setString(4, t.getIdStu());
			cs.setString(5, t.getIdSub());

			cs.executeUpdate();
			JOptionPane.showMessageDialog(null, "Update Success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String idStu, String idSub) {
		var sql = "{call DeleteScore(?, ?)}";
		try (var con = ConnectDB.getCon(); PreparedStatement ps = con.prepareCall(sql)) {
			ps.setString(1, idStu);
			ps.setString(2, idSub);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Delete Success");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
