package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.RemovalCVC;

public class RemovalCVCDao {

	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	private final static String tableName = "CVC_remove";
	
	
	public static boolean addRemovalCVC(RemovalCVC removalCVC) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			Date sqlDate = java.sql.Date.valueOf(removalCVC.getRemovalDate());
			
			String sql = "INSERT INTO "+tableName+"(id_remove, removal_date, motivation, cvc_bacteremia, cvc_tip_culture, closed) VALUES "
					+ "			("+removalCVC.getId()+", '"+sqlDate+"', '"+removalCVC.getMotivation()+"', "+removalCVC.getCVCBact()+", "+removalCVC.getCVCTip()+", true"+")";
			System.out.println(sql);
			return st.executeUpdate(sql)!=0 ? true:false;
			
			
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		
		
		return false;
	}
	
	public static RemovalCVC getRemovalCVC(int id) {
		
		RemovalCVC res=null;
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE id_remove = "+id+"";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();
			rs.next();
			LocalDate removalDate = rs.getDate("removal_date").toLocalDate();
			String mot = rs.getString("motivation");
			boolean tipCulture = rs.getBoolean("cvc_tip_culture");
			boolean bact = rs.getBoolean("cvc_bacteremia");
			boolean closed = rs.getBoolean("closed");
			res= new RemovalCVC(removalDate, mot, tipCulture, bact, closed);
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		
		return res;
	}
	
	public static boolean CVCRemovalExist(int id) {
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE id_remove = "+id+"";
			
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
