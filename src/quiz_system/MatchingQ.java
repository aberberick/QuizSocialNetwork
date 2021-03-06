package quiz_system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import database.DBConstants;

public class MatchingQ extends Question{
	
	/*
	 * Note:
	 * Choice Table - holds nothing
	 * Solutions Table: Choices Column - holds all first column strings
	 * Solutions Table: Solutions Column - holds all second column strings
	 */
	
	/*-------------------------- CONSTRUCTOR -------------------------------------------*/
	protected MatchingQ(Connection connection, String questionName, String parentQuizName) {
		super(connection, questionName, QuestionType.MATCHING, parentQuizName);
	}
	/*-------------------------- END CONSTRUCTOR ---------------------------------------*/
	
	
	/*-------------------------- PUBLIC FUNCTIONS --------------------------------------*/
	public void addSolution(String firstCol, String secondCol){
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "INSERT INTO ";
			cmd += DBConstants.TP_QSOLUTIONS + parentQuizName + questionName; 
			cmd += " VALUES (";
			cmd += "\"" + firstCol + "\", ";
			cmd += "\"" + secondCol + "\" ";
			cmd += ");";
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public void addSolutions(Map<String,String> matches, int key){
		if (key == 1){
			for(String firstCol:matches.keySet()){
				addSolution(firstCol, matches.get(firstCol));
			}
		}
		if(key == 2){
			for(String secondCol:matches.keySet()){
				addSolution(matches.get(secondCol),secondCol);
			}
		}
	}
	public boolean isMatch(String firstCol, String secondCol){
		String matchedSecondCol = DEFAULT_PROMPT;
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT * FROM ";
			cmd += DBConstants.TP_QSOLUTIONS + parentQuizName + questionName + " ";
			cmd += "WHERE " + DBConstants.T_QSOLUTIONS_C_CHOICES + " = ";
			cmd += "\"" + firstCol + "\" ";
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			if(rs.next()){
				matchedSecondCol = rs.getString(DBConstants.T_QSOLUTIONS_C_SOLUTIONS);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return (matchedSecondCol.equals(secondCol));
	}
	public Map<String,String> getMatches(int key){
		Map<String,String> matches = new HashMap<String,String>();
		String firstCol = DEFAULT_PROMPT;
		String secondCol = DEFAULT_PROMPT;
		
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT * FROM ";
			cmd += DBConstants.TP_QSOLUTIONS + parentQuizName + questionName + " ";
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			while(rs.next()){
				firstCol = rs.getString(DBConstants.T_QSOLUTIONS_C_CHOICES);
				secondCol = rs.getString(DBConstants.T_QSOLUTIONS_C_SOLUTIONS);
				
				if(key == 1){
					matches.put(firstCol, secondCol);
				}else if(key == 2){
					matches.put(secondCol, firstCol);
				}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return matches;
	}
	/*-------------------------- END PUBLIC FUNCTIONS ----------------------------------*/
}
