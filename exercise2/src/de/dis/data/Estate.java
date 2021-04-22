package de.dis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Makler-Bean
 * 
 * Beispiel-Tabelle:
 * CREATE TABLE makler (
 * name varchar(255), 
 * address varchar(255), 
 * login varchar(40) UNIQUE, 
 * password varchar(40), 
 * id serial primary key);
 */
public class Estate {
	private int id = 1;
	private String city;
	private String postal_code;
	private String street;
	private String street_number;
    private float square_area;
    private int agent;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPostal_Code() {
		return postal_code;
	}
	
	public void setPostal_Code(String postal_code) {
		this.postal_code = postal_code;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

    public String getStreet_Number() {
		return street_number;
	}
	
	public void setStreet_Number(String street_number) {
		this.street_number = street_number;
	}
	
	public Float getSquare_Area() {
		return square_area;
	}
	
	public void setSquare_Area(Float square_area) {
		this.square_area = square_area;
	}
	
    public Integer getAgent() {
        return agent;
    }

	public void setAgent(Integer agent) {
		this.agent = agent;
	}

    
	/**
	 * Lädt einen Makler aus der Datenbank
	 * @param id ID des zu ladenden Maklers
	 * @return Makler-Instanz
	 */
	public static Makler load(int id) {
		try {
			// Hole Verbindung
			Connection con = DbConnectionManager.getInstance().getConnection();

			// Erzeuge Anfrage
			String selectSQL = "SELECT * FROM estate WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, id);

	

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Estate ts = new Estate();
				ts.setId(id);
				ts.setCity(rs.getCity("city"));
				ts.setPostal_Code(rs.getPostal_Code("postal_code"));
				ts.setStreet(rs.getStreet("street"));
				ts.setStreet_Number(rs.getStreet_Number("street_number"));
				ts.setSquare_Area(rs.getSquare_Area("square_area"));
				ts.setAgent(rs.getAgent("agent"));

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
			private int id = 1;
			private String city;
			private String postal_code;
			private String street;
			private String street_number;
			private float square_area;
			private int agent;
	 * Speichert den Makler in der Datenbank. Ist noch keine ID vergeben
	 * worden, wird die generierte Id von der DB geholt und dem Model übergeben.
	 */
	public void save() {
		// Hole Verbindung
		Connection con = DbConnectionManager.getInstance().getConnection();

		try {
			// FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
			if (getId() == -1) {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit spC$ter generierte IDs zurC<ckgeliefert werden!
				String insertSQL = "INSERT INTO estate(id, city, postal_code, street, street_number, square_area, agent) VALUES (?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und fC<hre Anfrage aus
				pstmt.setString(1, getId());
				pstmt.setString(2, getCity());
				pstmt.setString(3, getPostal_Code());
				pstmt.setString(4, getStreet());
				pstmt.setString(5, getStreet_Number());
				pstmt.setString(6, getSquare_Area());
				pstmt.setString(7, getAgent());
				pstmt.executeUpdate();

				// Hole die Id des engefC<gten Datensatzes
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					setId(rs.getInt(1));
				}

				rs.close();
				pstmt.close();
			} else {
				// Falls schon eine ID vorhanden ist, mache ein Update...
				String updateSQL = "UPDATE estate SET id = ?, city = ?, postal_code = ?, street = ?, street_number = ?, square_area = ?, agent = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				// Setze Anfrage Parameter
				pstmt.setString(1, getId());
				pstmt.setString(2, getCity());
				pstmt.setString(3, getPostal_Code());
				pstmt.setString(4, getStreet());
				pstmt.setString(5, getStreet_Number());
				pstmt.setString(6, getSquare_Area());
				pstmt.setString(7, getAgent());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
