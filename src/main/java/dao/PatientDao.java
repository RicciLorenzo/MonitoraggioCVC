package dao;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.Allergy;
import model.Patient;
import model.User;

public class PatientDao {

	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/tesi";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	private final static String tableName = "Patient";
	
	
	public static boolean addPatient(String name, String surname, String fiscalCode, LocalDate dateOfBirth, LocalDate datePlacement, Allergy allergy, String Placement) {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement()) {
				Date sqlDate1 = Date.valueOf(dateOfBirth);
				Date sqlDate2 = Date.valueOf(datePlacement);
				String query;
				if(allergy.getA1()==null) {
					query = "INSERT INTO "+tableName+"(name, surname, fiscal_code, birthday, date_of_placement, allergy1, allergy2, anticoagulant_therapy, placement)"+" VALUES ('"+name+"', '"+surname+"', '"+fiscalCode+"', '"+sqlDate1+"', '"+sqlDate2+"', '"+allergy.getA0()+"', "+allergy.getA1()+", "+allergy.getAT()+", '"+Placement.toLowerCase()+"')";
				}
				else
					query = "INSERT INTO "+tableName+"(name, surname, fiscal_code, birthday, date_of_placement, allergy1, allergy2, anticoagulant_therapy, placement)"+" VALUES ('"+name+"', '"+surname+"', '"+fiscalCode+"', '"+sqlDate1+"', '"+sqlDate2+"', '"+allergy.getA0()+"', '"+allergy.getA1()+"', "+allergy.getAT()+", '"+Placement.toLowerCase()+"')";
				System.out.println(query);
				if (st.executeUpdate(query)!=0)
					return true;
				else
					return false;
				
			} catch(SQLException e) {
				System.out.println("Query error in table: "+tableName);
				}
			
		} catch(SQLException e) {
					System.out.println("Connection error to the database check credential");
			}		

		return false;
	}
	
	
	
	public static Patient getPatient(String id) {
		
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
					LocalDate birthday = rs.getDate("birthday").toLocalDate();
					LocalDate dateOfPlacement = rs.getDate("date_of_placement").toLocalDate();
					String allergy0 = rs.getString("allergy1");
					String allergy1 = rs.getString("allergy2");
					boolean aT = rs.getBoolean("anticoagulant_therapy");
					

					patient = new Patient(fiscalCode, name, surname, birthday, dateOfPlacement, allergy0, allergy1, aT, rs.getString("placement"));
				}
				
				
			} catch(SQLException e) {
				System.out.println("Query error in table: "+tableName);
				}
			
		} catch(SQLException e) {
					System.out.println("Connection error to the database check credential");
			}
		
		
		return patient;
	}
	
	public static boolean patientExist(String id) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE fiscal_code ILIKE '"+id+"'";
			
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
