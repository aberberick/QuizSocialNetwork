package system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import message.Mailman;
import quiz_system.QuizManager;
import user.UserManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import database.DBConnection;

public class SystemManager extends Object{

	/*-------------------------- CONSTANTS ---------------------------------------------*/

	/*-------------------------- END CONSTANTS -----------------------------------------*/
	
	/*-------------------------- PRIVATE INSTANCE VARIABLES ----------------------------*/
	private DBConnection db;
	private UserManager um;
	private QuizManager qm;
	private Mailman mm;
	private Connection con;
	private Statement state;
	private ResultSet rs;
	/*-------------------------- END PRIVATE INSTANCE VARIABLES ------------------------*/
	
	
	/*-------------------------- CONSTRUCTOR -------------------------------------------*/
	public SystemManager(){
		
		try{
			//Initialization Procedure;
			db = new DBConnection();
			um = new UserManager(db, null, null);
			mm = new Mailman(db, um, null);
			qm= new QuizManager(db.getDBConnection(), um);
			um.setAttributes(mm, qm);
			mm.setAttributes(qm);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	/*-------------------------- END CONSTRUCTOR ---------------------------------------*/
	
	
	/*-------------------------- PUBLIC GETTERS/SETTERS  -------------------------------*/
	public UserManager getUserManager(){
		return um;
	}
	public QuizManager getQuizManager(){
		return qm;
	}
	public Mailman getMailman(){
		return mm;
	}
	/*-------------------------- END PUBLIC GETTERS/ SETTERS ---------------------------*/
	
	
	/*-------------------------- DELETION FUNCTIONS ------------------------------------*/
	public void deleteEverything(){
		try{
			um.deleteEverything();
			qm.deleteEverything();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void closeDatabaseConnection(){
		try{
			db.closeConection();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	private void dropAllTables(){
		
		HashSet<String> tables = new HashSet<String>();
		try{
			state = (Statement) con.createStatement();
			String cmd1 = "";
			cmd1 += "SHOW TABLES";
			cmd1 += " ;";
			
			rs = state.executeQuery(cmd1);
			while(rs.next()){
				tables.add(rs.getString(0));
			}
			
			for(String table:tables){
				System.out.println(table);
				state = (Statement) con.createStatement();
				String cmd2 = "";
				cmd2 += "DROP TABLE ";
				cmd2 += table;
				cmd2 += " ;";
				state.execute(cmd2);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	/*-------------------------- END DELETION FUNCTIONS --------------------------------*/
}
