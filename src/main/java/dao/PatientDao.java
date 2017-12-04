package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import model.Patient;
import model.User;
import model.UserType;

public class PatientDao {

	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/tesi";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	
	private final static String tableName = "Patient";
	
	
	
	private final Patient getPatient(String id) {
		
		Patient patient = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement()) {
				
				String query = "SELECT * FROM "+tableName+" WHERE username ILIKE '"+id+"'";
				System.out.println(query);
				st.executeQuery(query);
				ResultSet rs = st.getResultSet();
				
				while (rs.next()) {
					String patientLabel = rs.getString("patient_label");
					String fiscalCode = rs.getString("fiscal_code");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					Date dateOfPlacement = rs.getDate("date_of_placement");
					boolean allergy0 = rs.getBoolean("allergy1");
					boolean allergy1 = rs.getBoolean("allergy2");
					boolean aT = rs.getBoolean("anticoagulant_therapy");

					patient = new Patient(patientLabel, fiscalCode, name, surname, dateOfPlacement, allergy0, allergy1, aT);
				}
				
				
			} catch(SQLException e) {
				System.out.println("Query error in table: "+tableName);
				}
			
		} catch(SQLException e) {
					System.out.println("Connection error to the database check credential");
			}
		
		
		return patient;
	}
	
	private final boolean patientExist(String id) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE patient_label ILIKE '"+id+"'";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			return rs.first();
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		
		return false;
	}
	
	
}
