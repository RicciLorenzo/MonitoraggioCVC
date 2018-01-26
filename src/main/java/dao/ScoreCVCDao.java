package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		
		
		
		
		
		
		return false;
	}
	
	public static ArrayList<ScoreForm> getScoreCVC(int id) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		
		return null;
	}
	
	//get list of id of scores
	private static ArrayList<Integer> getIdScore(int id) {
		return null;
	}
	
	//get single score form
	private static ScoreForm getSingleScoreCVC(int id) {
		return null;
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
