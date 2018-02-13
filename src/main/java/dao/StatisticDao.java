package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class StatisticDao {

	private final static String jdbcUrl = "jdbc:postgresql://localhost:5432/tesi";
	private final static String jdbcUsername = "postgres";
	private final static String jdbcPassword = "ciao";
	
	private final static String jdbcTable = "CVC_Form";
	
	//first position urgent, second programmed (modalità inserimento)
	public static int[] getInsM(LocalDate date) {
		int[] res = {0,0};
		
		System.out.println("Try Database Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			
		return res;
	}	
	
		
}
