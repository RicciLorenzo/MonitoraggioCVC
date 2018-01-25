package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import model.CVCPreview;

public class CVCDao {
	
	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/tesi";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	private final static String tableName = "CVC_form";
	

	//cvc exists
	
	public static boolean CVCExist(String id) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE id_cvc ILIKE "+Integer.parseInt(id)+"";
			
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
	
	//addcvc
	
	//getCVC
	
	//getPreviewCVC
		public static CVCPreview CVCPreview(String id) {
		CVCPreview res=null;
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT id_cvc, patient_label, insertion_site, insertion_site_side FROM "+tableName+" WHERE patient_label ILIKE '"+id+"'";
			st.executeQuery(sql);
			ResultSet rs = st.getResultSet();
			int idCVC = rs.getInt("id_cvc");
			String patientLabel=rs.getString("patient_label");
			boolean side= rs.getBoolean("insertion_site_site");
			String insertion=rs.getString("insertion_site")+(side?" dx":" sx");
			//dx true, sx false
			model.Patient p = dao.PatientDao.getPatient(patientLabel);
			res = new CVCPreview(idCVC, p.getName(), p.getSurname(), LocalDate.parse(p.getBirthday()), patientLabel, insertion, dao.RemovalCVCDao.CVCRemovalExist(idCVC) );
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}
			
		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		
		return res;
	}
	
	
	
	//get cvc id from patient
	public static ArrayList<Integer> getCVCId(String patient) {
		return null;
	}
	
	
}
