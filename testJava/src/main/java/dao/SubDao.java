package dao;

import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import service.ConnectDB;

public class SubDao {
	public void loadIDSub(DefaultComboBoxModel<String> comboBoxModel) {
		var sql = "select IDSub from subject";
		try (var conn = ConnectDB.getCon(); var stmt = conn.createStatement(); var rs = stmt.executeQuery(sql)) {
			comboBoxModel.removeAllElements();
			while (rs.next()) {
				var nameFac = rs.getString("IDSub");
				comboBoxModel.addElement(nameFac);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}
}
