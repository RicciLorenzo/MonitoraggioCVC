package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class StatisticDao {

	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/tesi";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	
	private final static String tableName = "CVC_Form";
	
	//for all dates(passed date) is the last day of the month or the current day
	
	
	//griplock, statlock, securacath, other
	public static int[] getFis(LocalDate date) {
		int[] res = {0,0,0,0};

		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(fastening) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND fastening ILIKE 'griplock'";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(fastening) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND fastening ILIKE 'statlock'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			sql = "SELECT COUNT(fastening) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND fastening ILIKE 'securacath'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[2]=rs.getInt(1);
			
			sql = "SELECT COUNT(fastening) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND (fastening IS NOT NULL OR fastening IS NOT ILIKE 'griplock' OR fastening IS NOT ILIKE 'statlock' OR fastening IS NOT ILIKE 'securacath' )";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[3]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}		
		
		return res;
	}
	
	
	//first position urgent, second programmed (modalità inserimento)
	public static int[] getInsM(LocalDate date) {
		int[] res = {0,0};
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(insertion_mode) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND insertion_mode IS TRUE";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(insertion_mode) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND insertion_mode IS FALSE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}
		
		
		return res;
	}
	
	//first position true, second false (difficoltà inserimento)
	public static int[] getDiffI(LocalDate date) {
		int[] res = {0,0};
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}

		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(difficulty_insertion) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND difficulty_insertion IS TRUE";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(difficulty_insertion) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND difficulty_insertion IS FALSE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}		
		
		return res;
	}
	
	//first position true, second false (posizionamento ecoguidato)
	public static int[] getPosE(LocalDate date) {
		int[] res = {0,0};
			
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}	
	
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(ecoguided_positioning) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND ecoguided_positioning IS TRUE";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(ecoguided_positioning) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND ecoguided_positioning IS FALSE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}		
		
		return res;
	}
	
	//first position true, second false (rx torace)
	public static int[] getRx(LocalDate date) {
		int[] res = {0,0};
			
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}	

		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(chest_rx) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND chest_rx IS TRUE";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(chest_rx) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND chest_rx IS FALSE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}			
		
		
		return res;
	}
	
	//first position aperta, second chiusa (punta)
	public static int[] getTip(LocalDate date) {
		int[] res = {0,0};
			
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}	

		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(tip) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND tip IS TRUE";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(tip) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND tip IS FALSE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}			
		
		
		return res;
	}	
	
	
	//first position 1, second 2, third 3 (n. vie)
	public static int[] getWay(LocalDate date) {
		int[] res = {0,0,0};
			
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}	
	
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(n_way) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND n_way = 1";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(n_way) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND n_way = 2";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			sql = "SELECT COUNT(n_way) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND n_way = 3";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[2]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}			
				
		
		return res;
	}	
	
	//first position true, second false (complicanze)
	public static int[] getComp(LocalDate date) {
		int[] res = {0,0};
			
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}	

		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(complication_bool) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND complication_bool IS TRUE";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(complication_bool) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND complication_bool IS FALSE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}			
		
		
		return res;
	}	
	
	
	//first position ematoma, second arteria, third pnx, fourth other (comlicanze sì)
	public static int[] getComps(LocalDate date) {
		int[] res = {0,0,0,0};
			
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}	
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(hematoma) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND hematoma IS TRUE";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(artery_puncture) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND artery_puncture IS TRUE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			sql = "SELECT COUNT(pnx) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND pnx IS TRUE";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[2]=rs.getInt(1);
			
			sql = "SELECT COUNT(complication_other) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND (complication_other IS NOT NULL OR complication_other IS NOT ILIKE '')";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[3]=rs.getInt(1);
			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}		
		
		return res;
	}	
	
	
	public static int getNumPatient(LocalDate date) {
		int res = 0;
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}

		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(fiscal_code) FROM Patient WHERE date_of_placement BETWEEN '"+first+"' AND '"+last+"'";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res=rs.getInt(1);

			
			}
			catch (SQLException e) {
					System.out.println("Query error in table: "+tableName+"  "+e.getMessage());
			}

		} catch (SQLException e) {
					System.out.println("Connection error to the database check exist"+ e.getMessage());
				}		
		
		return res;
	}
	
	
	//cicc, picc, ficc, midline, minimidline, port-a-cath, broviac, quinton, tesio (typology_structure)
	public static int[] getStruct(LocalDate date) {
		int[] res = {0,0,0,0,0,0,0,0,0};
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		
		try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)){
			
			try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			int month = date.getMonthValue();
			int year = date.getYear();
			String firstS = year+"-"+month+"-1";
			Date first = Date.valueOf(firstS);
			Date last = Date.valueOf(date);
			String sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"') AND typology_structure ILIKE 'cicc'";
			
			st.executeQuery(sql);
				
			ResultSet rs = st.getResultSet();

			res[0]=rs.getInt(1);
			
			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'picc'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[1]=rs.getInt(1);
			
			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'ficc'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[2]=rs.getInt(1);
			
			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'midline'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[3]=rs.getInt(1);

			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'minimidline'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[4]=rs.getInt(1);
			
			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'port a cath'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[5]=rs.getInt(1);
			
			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'broviac'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[6]=rs.getInt(1);
			
			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'quinton'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[7]=rs.getInt(1);
			
			sql = "SELECT COUNT(typology_structure) FROM "+tableName+" JOIN Patient P ON patient_label=fiscal_code WHERE (P.date_of_placement BETWEEN '"+first+"' AND '"+last+"' ) AND typology_structure ILIKE 'tesio'";
			
			st.executeQuery(sql);
				
			rs = st.getResultSet();

			res[8]=rs.getInt(1);
			
			
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
