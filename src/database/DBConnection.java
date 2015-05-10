package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import database.DBInfo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBConnection {

	
	static String account = DBInfo.MYSQL_USERNAME; 
	static String password = DBInfo.MYSQL_PASSWORD; 
	static String server = DBInfo.MYSQL_DATABASE_SERVER; 
	static String database = DBInfo.MYSQL_DATABASE_NAME; 
	private Connection con;
	
	
	 
	public DBConnection() {
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			 
			con  = (Connection) DriverManager.getConnection( "jdbc:mysql://" + server, account ,password); 
			Statement stmt = (Statement) con.createStatement(); 
			stmt.executeQuery("USE " + database); 
	
		} catch (SQLException e) { 
			 // TODO Auto-generated catch block 
			 e.printStackTrace(); 
			 } 
			 catch (ClassNotFoundException e) { 
			 // TODO Auto-generated catch block 
			 e.printStackTrace(); 
		}
	}

	public Connection getDBConnection() {
		return con;
	}
		
	public void closeConection() throws SQLException {
		con.close();	
	}
	
				
}
