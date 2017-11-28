package dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.User;
import model.UserType;


public class UserDAO {

	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/tesi";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	
	private final static String tableName = "users";
	
	
	
	public static User getUser(String username, String password) throws UnknownHostException {
		
		User user = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement()) {
				
				String query = "SELECT * FROM "+tableName+" WHERE username ILIKE '"+username+"' AND password LIKE '"+password+"'";
				System.out.println(query);
				st.executeQuery(query);
				ResultSet rs = st.getResultSet();
				
				while (rs.next()) {
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					String typeS = rs.getString("type");
					
					UserType type = UserType.valueOf(typeS);
					
					user = new User(name, surname, type);
				}
				
				
			} catch(SQLException e) {
				System.out.println("Query error in table: "+tableName);
				}
			
		} catch(SQLException e) {
					System.out.println("Connection error to the database check credential");
			}
		
		return user;
	}
	
	
	
	public static boolean userExists(String username) {
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			String sql = "SELECT * FROM "+tableName+" WHERE username ILIKE '"+username+"'";
			
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
