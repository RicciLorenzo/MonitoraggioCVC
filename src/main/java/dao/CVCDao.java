package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.CVCForm;
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
	public static boolean addCVC(CVCForm cvc) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){	
	
		String sql = "INSERT INTO "+tableName+"(id_cvc, patient_label, lumi_number, french, typology_structure, tunneled, uncuffed, insertion_mode, difficulty_insertion, ecoguided_positioning,"
				+ " chest_rx, complication_bool, hematoma, artery_puncture, pnx, complication_other, insertion_site, insertion_site_side, vein_diameter, tip, n_way, medication1, medication2, glue, biopatch, destination_of_patient, fastening, sign) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try (PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			
				pst.setInt(1, cvc.getId());
				pst.setString(2, cvc.getPatient().getFiscalCode());
				pst.setInt(3, cvc.getLumi());
				pst.setInt(4, cvc.getFrench());
				pst.setString(5, cvc.getType());
				pst.setBoolean(6, cvc.getTunneled());
				pst.setBoolean(7, cvc.getUncuffed());
				pst.setBoolean(8, cvc.getInsertion().getInsertionMode());
				pst.setBoolean(9, cvc.getInsertion().getdiffInsertion());
				pst.setBoolean(10, cvc.getEco());
				pst.setBoolean(11, cvc.getChest());
				pst.setBoolean(12, cvc.getComplication().hasComplication());
				pst.setBoolean(13, cvc.getComplication().getHematoma());
				pst.setBoolean(14, cvc.getComplication().getArtery());
				pst.setBoolean(15, cvc.getComplication().getPnx());
				pst.setString(16, cvc.getComplication().getOtherC());
				pst.setBoolean(17, cvc.getInsertion().getInsertionSide());
				pst.setString(18, cvc.getInsertion().getInsertionSite());
				pst.setFloat(19, cvc.getVeinDiameter());
				pst.setBoolean(20, cvc.getTip());
				pst.setInt(21, cvc.getWay());
				pst.setString(22, cvc.getMedication().getChlOrPoly() ? "clorexidina": "poliuretano");
				pst.setString(23, cvc.getMedication().getIodOrGau() ? "iodio" : "garza");
				pst.setBoolean(24, cvc.getMedication().getGlue());
				pst.setBoolean(25, cvc.getMedication().getBioptach());
				pst.setString(26, cvc.getDestination());
				pst.setString(27, cvc.getSign());
				
			return pst.executeUpdate(sql)!=0 ? true:false;	
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		return false;
	}
	
	
	//getCVC
	public static CVCForm getCVC(int id) {
		
		CVCForm res = null;
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE id_CVC ILIKE '"+id+"'";
			st.executeQuery(sql);
			ResultSet rs = st.getResultSet();
			//dx true, sx false
			model.Patient p = dao.PatientDao.getPatient(rs.getString("patient_label"));
			model.Insertion ins = new model.Insertion(rs.getBoolean("insertion_mode"), rs.getBoolean("difficulty_insertion"), rs.getString("insertion_site"), rs.getBoolean("insertion_site_side"));
			model.Complication comp;
			if(rs.getBoolean("complication_bool")) {
				comp = new model.Complication(true, rs.getBoolean("hematoma"), rs.getBoolean("artery_puncture"), rs.getBoolean("pnx"), rs.getString("complication_other"));
			}
			else {
				comp = new model.Complication(false); 
				}
			model.Medication med = new model.Medication(rs.getString("medication1").equalsIgnoreCase("clorexidina") ? true : false , rs.getString("medication2").equalsIgnoreCase("iodio") ? true : false, rs.getBoolean("glue"), rs.getBoolean("biopatch"));
			return new CVCForm(rs.getInt("id_cvc"), p, ins, rs.getBoolean("ecoguided_positioning"), rs.getBoolean("chest_rx"), comp, rs.getString("typology_structure"), rs.getBoolean("tunneled"), rs.getBoolean("uncuffed"),
								med, rs.getInt("lumi_number"), rs.getInt("french"), rs.getFloat("vein_diameter"), rs.getBoolean("tip"), rs.getInt("n_way"), rs.getString("fastening"), rs.getString("destination_of_patient"), rs.getString("sign"));

			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}
			
		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		return res;
	}
	
	
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
		
		ArrayList<Integer> res = new ArrayList<>();
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT id_cvc FROM "+tableName+" WHERE patient_label ILIKE '"+patient+"'";
			st.executeQuery(sql);
			ResultSet rs = st.getResultSet();
			while(rs.next()) {
				res.add(new Integer(rs.getInt("id_cvc")));
			}

			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}
			
		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		
		
		return res;
	}
	
	
}
