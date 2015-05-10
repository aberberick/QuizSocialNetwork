package quiz_system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import database.DBConstants;

public class MultipleChoiceAnswerQ extends Question{
	
	/*
	 * Note:
	 * Choice Table - holds all multiple choice question choices
	 * Solutions Table: Solutions Column - holds all correct choices
	 */
	
	/*-------------------------- CONSTRUCTOR -------------------------------------------*/
	protected MultipleChoiceAnswerQ(Connection connection, String questionName, String parentQuizName) {
		super(connection, questionName, QuestionType.MULTIPLE_CHOICE_ANSWER, parentQuizName);
	}
	/*-------------------------- END CONSTRUCTOR ---------------------------------------*/
	
	
	/*-------------------------- PUBLIC FUNCTIONS --------------------------------------*/
	public void addChoice(String choice){
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "INSERT INTO ";
			cmd += DBConstants.TP_QCHOICES + parentQuizName + questionName + " ";
			cmd += " VALUE (";
			cmd += "\"" + choice + "\"";
			cmd += ");";
			
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public void addChoices(HashSet<String> choices){
		for(String choice: choices){
			addChoice(choice);
		}
	}
	public HashSet<String> getChoices(){
		HashSet<String> choices = new HashSet<String>();
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT * ";
			cmd += "FROM " + DBConstants.TP_QCHOICES;
			cmd += parentQuizName + questionName + " ";
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			while(rs.next()){
				String choice = rs.getString(DBConstants.T_QCHOICES_C_CHOICES);
				choices.add(choice);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return choices;
	}
	public void addSolution(String choice){
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "INSERT INTO ";
			cmd += DBConstants.TP_QSOLUTIONS + parentQuizName + questionName + " ";
			cmd += " VALUE (";
			cmd += "\"" + DEFAULT_CHOICE + "\", ";
			cmd += "\"" + choice + "\"";
			cmd += ");";
			
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public boolean isSolution(HashSet<String> guesses){
		return(getSolutions().equals(guesses));
	}
	public void setSolutions(HashSet<String> correctChoices){
		try{
			//Clear any previous correct choice
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "TRUNCATE TABLE ";
			cmd += DBConstants.TP_QSOLUTIONS;
			cmd += parentQuizName + questionName + " ";
			cmd += ";";			
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		for(String choice: correctChoices){
			addSolution(choice);
		}
	}
	public HashSet<String> getSolutions(){
		HashSet<String> solutions = new HashSet<String>();
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT * ";
			cmd += "FROM " + DBConstants.TP_QSOLUTIONS;
			cmd += parentQuizName + questionName + " ";
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			while(rs.next()){
				String choice = rs.getString(DBConstants.T_QSOLUTIONS_C_SOLUTIONS);
				solutions.add(choice);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return solutions;
	}
	/*-------------------------- END PUBLIC FUNCTIONS ----------------------------------*/
}
