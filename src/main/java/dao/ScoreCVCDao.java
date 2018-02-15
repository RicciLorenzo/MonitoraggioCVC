package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Medication;
import model.ScoreForm;

public class ScoreCVCDao {

	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/tesi";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	private final static String tableName = "CVC_score";
	
	
	public static boolean addScoreCVC(ScoreForm score) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			String sql = "INSERT INTO "+tableName+"(id_cvc, date_valutation, score, wash, eparinizz, sost_infusive, sostitution_medication, medication_1, medication_2, glue, biopatch, "
					+ "difficulty_infusion, difficulty_aspiration, suspected_infection, obstruction, cvc_blood_culture, sign) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try (PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			
				Date sqlDate = java.sql.Date.valueOf(score.getDate());

				pst.setInt(1,score.getIdCVC() );
				pst.setDate(2, sqlDate);
				pst.setInt(3, score.getScore());
				pst.setBoolean(4, score.getWash());
				pst.setBoolean(5, score.getEparinizz());
				pst.setBoolean(6, score.getSostInfusive());
				pst.setString(7, score.getMedicationCause());
				pst.setString(8, score.getMedication().getChlOrPoly() ? "clorexidina alcolica":"poliuretano");
				pst.setString(9, score.getMedication().getIodOrGau() ? "iodio":"garza");
				pst.setBoolean(10, score.getMedication().getGlue());
				pst.setBoolean(11, score.getMedication().getBioptach());
				pst.setBoolean(12, score.getDiffInfusion());
				pst.setBoolean(13, score.getDiffAspiration());
				pst.setBoolean(14, score.getSuspInfection());
				pst.setBoolean(15, score.getObstruction());
				pst.setString(16, score.getCvcBlood());
				pst.setString(17, score.getSign());

			return pst.executeUpdate()!=0 ? true:false;	
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}		

		
		return false;
	}
	
	public static ArrayList<ScoreForm> getScoreCVC(int id) {
		
		ArrayList<ScoreForm> res = new ArrayList<ScoreForm>();
	
		for(Integer i : getIdScore(id)) {
			res.add(	getSingleScoreCVC(i.intValue()));
		}
		
		return res;
	}
	
	//get list of id of scores
	private static ArrayList<Integer> getIdScore(int id) {
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT id_score FROM "+tableName+" WHERE id_cvc = "+id+"";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			while(rs.next()) {
				res.add(new Integer(rs.getInt("id_score")));
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
	
	//get single score form
	private static ScoreForm getSingleScoreCVC(int id) {
		
		ScoreForm res = null;
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE id_score = "+id+"";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();
			rs.next();
			res = new ScoreForm(rs.getInt("id_score"), rs.getDate("date_valutation").toLocalDate(), rs.getInt("score"), rs.getBoolean("wash"),	
									rs.getBoolean("eparinizz"), rs.getBoolean("sost_infusive"), rs.getString("sostitution_medication"),
									new Medication((rs.getString("medication_1").equals("clorexidina")), (rs.getString("medication_2").equals("iodio")), rs.getBoolean("glue"), rs.getBoolean("biopatch")),
									rs.getBoolean("difficulty_infusion"), rs.getBoolean("difficulty_aspiration"), rs.getBoolean("suspected_infection"), 
									rs.getBoolean("obstruction"), rs.getString("cvc_blood_culture"), rs.getString("sign"));
			
	
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		
		return res;
	}
	
	
	
	public static boolean CVCScoreExist(int id) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE id_cvc = "+id+"";
			
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
