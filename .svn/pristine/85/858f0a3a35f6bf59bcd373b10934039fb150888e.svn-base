package quiz_system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import user.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import database.DBConstants;

public class GradedQ extends Question{
	
	/*-------------------------- PRIVATE INSTANCE VARIABES -----------------------------*/
	/*-------------------------- END PRIVATE INSTANCE VARIABES -------------------------*/
	
	
	/*-------------------------- CONSTRUCTOR -------------------------------------------*/
	protected GradedQ(Connection connection, String questionName, String parentQuizName) {
		super(connection, questionName, QuestionType.GRADED, parentQuizName);
		initializeQuizGradesTable();
	}
	/*-------------------------- END CONSTRUCTOR ---------------------------------------*/
	
	
	/*-------------------------- PUBLIC FUNCTIONS --------------------------------------*/
	public ArrayList<User> getTakers(){
		/*
		 * TODO:
		 */
		return null;
	}
	public void addAnswer(User user, String solution){
		/*
		 * TODO:
		 */
	}
	public ArrayList<String> getAnswers(){
		/*
		 * TODO:
		 */
		return null;
	}
	public ArrayList<String> getAnswers(User user){
		/*
		 * TODO:
		 */
		return null;
	}
	public void scoreSolution(String solution, int score){
		/*
		 * TODO:
		 */
	}
	public int getHighestScore(User user){
		/*
		 * TODO:
		 */
		return Question.DEFAULT_GRADE;
	}
	public int getScore(User user, String solution){
		/*
		 * TODO:
		 */
		return Question.DEFAULT_GRADE;
	}
	/*-------------------------- END PUBLIC FUNCTIONS ----------------------------------*/
	
	
	/*-------------------------- PROTECTED FUNCTIONS -----------------------------------*/
	protected void removeTaker(User user){
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "DELETE FROM ";
			cmd += DBConstants.TP_QANSWERS + parentQuizName + questionName + " ";
			cmd += " WHERE " + DBConstants.T_QANSWERS_C_TAKER + " = ";
			cmd += "\"" + user.getUserName() + "\" ";
			cmd += ";";
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	@Override
	public void remove(){
		super.remove();
		removeQuizGradesTable();
	}
	/*-------------------------- END PROTECTED FUNCTIONS -------------------------------*/
	
	
	/*-------------------------- TABLE CREATOR/DESTRUCTOR FUNCTIONS --------------------*/	
	private void initializeQuizGradesTable(){
		boolean tableExists = false;
		
		//If table doesn't exist, create table
		if(!tableExists){
			try{
				state = (Statement) con.createStatement();	
				String cmd = "";
				cmd += "CREATE TABLE IF NOT EXISTS ";
				cmd += DBConstants.TP_QANSWERS + parentQuizName + questionName;
				cmd += " (";
				cmd += DBConstants.T_QANSWERS_C_TAKER + " " + DBConstants.SQL_SHORT_STRING + ", ";
				cmd += DBConstants.T_QANSWERS_C_ANSWERS + " " + DBConstants.SQL_LONG_STRING + ", ";
				cmd += DBConstants.T_QANSWERS_C_TIME + " " + DBConstants.SQL_INT + " ";
				cmd += ");";
				state.execute(cmd);				
			}catch(SQLException ex){
				ex.printStackTrace();
			}	
		}
	}
	private void removeQuizGradesTable(){
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "DROP TABLE ";
			cmd += DBConstants.TP_QANSWERS + parentQuizName + questionName;
			cmd += ";";
			
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	/*-------------------------- END TABLE CREATOR/DESTRUCTOR FUNCTIONS ----------------*/
}
