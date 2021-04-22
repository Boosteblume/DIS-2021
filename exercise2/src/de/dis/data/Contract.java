package de.dis.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Vertrags-Bean
 * 
 * Beispiel-Tabelle:
 * CREATE TABLE contract (
 * contractno;
 * contact_date;
 * place;
 */

/public class Contract {
	private int contractno;
	private String contract_date;
	private String place;

			
	public int getcontractno() {
		return contractno;
	}
	
	public void setcontractno( int contractno) {
		this.contractno = contractno;
	}
	
	public String getcontract_date() {
		return contract_date;
	}
	
	public void setcontract_date(String contract_date) {
		this.contract_date = contract_date;
	}
	
	public String getplace() {
		return place;
	}
	
	public void setplace(String place) {
		this.place = place;
	}
	
	/**
	 * Lädt einen Vertrag aus der Datenbank
	 * @param contractno ID des zu ladenden Vertrags
	 * @return Makler-Instanz
	 */
	public static Contract load(int contractno) {
		try {
			// Hole Verbindung
			Connection con = DbConnectionManager.getInstance().getConnection();

			// Erzeuge Anfrage
			String selectSQL = "SELECT * FROM contract WHERE contractno = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, contractno);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Contract ts = new Contract();
				ts.setcontractno(contractno);
				ts.setcontract_date(rs.getDate("contract_date"));
				ts.setplace(rs.getString("place"));

				rs.close();
				pstmt.close();
				return ts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Speichert den Vertrag in der Datenbank. Ist noch keine Vertragsnummer vergeben
	 * worden, wird die generierte Vertragsnummer von der DB geholt und dem Model übergeben.
	 */
	public void save() {
		// Hole Verbindung
		Connection con = DbConnectionManager.getInstance().getConnection();

		try {
			// FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
			if (getcontractno() == -1) {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit spC$ter generierte IDs zurC<ckgeliefert werden!
				String insertSQL = "INSERT INTO contract(contract_date, place) VALUES (?, ?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und fC<hre Anfrage aus
				pstmt.setDate(1, getcontract_date());
				pstmt.setString(2, getplace());
				pstmt.executeUpdate();

				// Hole die Id des engefC<gten Datensatzes
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					setcontractno(rs.getInt(1));
				}

				rs.close();
				pstmt.close();
			} else {
				// Falls schon eine ID vorhanden ist, mache ein Update...
				String updateSQL = "UPDATE contract SET contract_date = ?, date = ?, WHERE conmt = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				// Setze Anfrage Parameter
				pstmt.setDate(1, getcontract_date());
				pstmt.setString(2, getplace());
				pstmt.setInt(3, getcontractno());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
