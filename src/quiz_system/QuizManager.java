package quiz_system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeMap;

import user.User;
import user.UserManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import database.DBConstants;

public class QuizManager extends Object{

	/*-------------------------- CONSTANTS ---------------------------------------------*/
	public static final long DEFAULT_DATE_CREATED = -1;
	/*-------------------------- END CONSTANTS -----------------------------------------*/
	
	/*-------------------------- PRIVATE INSTANCE VARIABLES ----------------------------*/
	private Connection con;
	private Statement state;
	private Date date;
	private UserManager userManager;
	/*-------------------------- END PRIVATE INSTANCE VARIABLES ------------------------*/
	
	
	/*-------------------------- CONSTRUCTOR -------------------------------------------*/
	public QuizManager(Connection connection, UserManager userManager){
		con = connection;
		this.userManager = userManager;
		initializeQuizTable();
	}
	public String quizzesToString(){
		String str = "";
		str += "\n";
		str += "Quizzes Table: Start \n";
		
		ArrayList<Quiz> quizzes = getAllQuizzes();
		for(Quiz q: quizzes){
			str += q.toString();
			str += "\n";
		}
		str += "End Quizzes Table";
		str += "\n";
		return str;
	}
	/*-------------------------- END CONSTRUCTOR ---------------------------------------*/
	
	
	/*-------------------------- PUBLIC FUNCTIONS --------------------------------------*/
	public Quiz createAndAddQuiz(QuizCategory category, String name, User user){
		
		//Remove quiz already occupying the same name
		if(isQuizNameTaken(name)){
			removeQuiz(getQuiz(name));
		}
		
		date = new Date();
		//Add a new quiz with the name
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "INSERT INTO " + DBConstants.T_QUIZZES + " VALUES (";
			cmd += "\"" + user.getUserName() + "\", ";
			cmd += "\"" + name + "\", ";
			cmd += "\"" + category.toString() + "\", ";
			cmd +=  Long.toString(date.getTime()) + ", ";
			cmd += Boolean.toString(Quiz.DEFAULT_IS_ONEPAGE).toUpperCase() + ", ";
			cmd += Boolean.toString(Quiz.DEFAULT_IS_PRACTICE).toUpperCase() + ", ";
			cmd += "\"" + Quiz.DEFAULT_DESCRIPTION + "\", ";
			cmd += Boolean.toString(Quiz.DEFAULT_IS_TIMED).toUpperCase() + ", ";
			cmd += Boolean.toString(Quiz.DEFAULT_IS_RANDOMIZED).toUpperCase() + ", ";
			cmd += Boolean.toString(Quiz.DEFAULT_IS_CORRECTED_IMMEDIATLEY).toUpperCase() + ", "; 
			cmd += Long.toString(Quiz.DEFAULT_TIME_TO_TAKE) + " ";
			cmd += ");";
			
			
			state.execute(cmd);
				
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		//All other quiz tables are created by quiz object
		return new Quiz(con,name,userManager);
	}
	public void removeQuiz(Quiz quiz){
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "DELETE FROM " + DBConstants.T_QUIZZES + " WHERE ";
			cmd += DBConstants.T_QUIZZES_C_QUIZ + " = ";
			cmd += "\"" + quiz.getName() + "\"";
			cmd += ";";
			
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		//Quiz object itself will remove all other tables it ownes
		quiz.remove();
	}
	public void removeAllQuizes(){
		for(Quiz quiz:getAllQuizzes()){
			removeQuiz(quiz);
		}
	}
	public boolean isQuizNameTaken(String quizName){
		for(Quiz quiz:getAllQuizzes()){
			if(quiz != null){
				if(quiz.getName().equals(quizName)) return true;				
			}
		}
		return false;
	}
	public Quiz getQuiz(String quizName){
		boolean quizExists = false;
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT * ";
			cmd += "FROM " + DBConstants.T_QUIZZES + " WHERE ";
			cmd += DBConstants.T_QUIZZES_C_QUIZ + " = ";
			cmd += "\"" + quizName + "\"";
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			if(rs.next()){
				quizExists = true;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		if(quizExists){
			return new Quiz(con, quizName, userManager);
		}
		return null;
	}
	public ArrayList<Quiz> getQuizzesCreatedByUser(User user){
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT " + DBConstants.T_QUIZZES_C_QUIZ;
			cmd += " FROM " + DBConstants.T_QUIZZES + " WHERE ";
			cmd += DBConstants.T_QUIZZES_C_CREATOR + " = ";
			cmd += "\"" + user.getUserName() + "\"";
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			while(rs.next()){
				Quiz quiz = getQuiz(rs.getString(DBConstants.T_QUIZZES_C_QUIZ));
				quizzes.add(quiz);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return quizzes;
	}
	public ArrayList<Quiz> getQuizzesTakenByUser(User user){
		ArrayList<Quiz> quizzes = getAllQuizzes();
		
		TreeMap<Long, Quiz> dateMap = new TreeMap<Long,Quiz>();
		for(Quiz q:quizzes){
			long dateTaken = q.getDateTaken(user);
			if(dateTaken != Quiz.DEFAULT_DATE_TAKEN){
				dateMap.put(dateTaken, q);				
			}
		}
		quizzes.clear();
		
		if(!dateMap.isEmpty()){
			Long mostRecentLeft = dateMap.lastKey();
			while(true){
				quizzes.add(dateMap.get(mostRecentLeft));
				mostRecentLeft = dateMap.lowerKey(mostRecentLeft);
				if(mostRecentLeft == null) break;
			}	
		}
		return quizzes;	
	}

	
	public ArrayList<Quiz> getAllQuizzes(){
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT * ";
			cmd += " FROM " + DBConstants.T_QUIZZES;
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			
			while(rs.next()){
				Quiz quiz = getQuiz(rs.getString(DBConstants.T_QUIZZES_C_QUIZ));
				if(quiz != null){
					quizzes.add(quiz);						
				}
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return quizzes;
	}
	public int getNumQuizzes(){
		int numQuizzes = 0;
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT COUNT(*) ";
			cmd += " FROM " + DBConstants.T_QUIZZES;
			cmd += ";";
			
			ResultSet rs = state.executeQuery(cmd);
			
			while(rs.next()){
				numQuizzes = rs.getInt(1);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return numQuizzes;
	}
	public int getNumQuizzesTaken(){
		int numQuizzesTaken = 0;
		for(Quiz q:getAllQuizzes()){
			if (q.hasBeenTaken()){
				numQuizzesTaken++;
			}
		}
		return numQuizzesTaken;
	}
	public ArrayList<Quiz> getRecentlyCreatedQuizzes(long time){
		date = new Date();
		long currentTime = date.getTime();
		if (time < 0) time *= -1;
		long lastTimeAccepted = currentTime - time;
		
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "SELECT " + DBConstants.T_QUIZZES_C_QUIZ;
			cmd += " FROM " + DBConstants.T_QUIZZES + " WHERE ";
			cmd += DBConstants.T_QUIZZES_C_DATE_CREATED + " >= ";
			cmd += Long.toString(lastTimeAccepted) + " ";
			cmd += "ORDER BY ";
			cmd += DBConstants.T_QUIZZES_C_DATE_CREATED + " ";
			cmd += "DESC";
			cmd += " ;";
			
			ResultSet rs = state.executeQuery(cmd);
			while(rs.next()){
				Quiz quiz = getQuiz(rs.getString(DBConstants.T_QUIZZES_C_QUIZ));
				quizzes.add(quiz);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return quizzes;
	}
	public ArrayList<Quiz> getMostPopularQuizzes(long time){
		date = new Date();
		long currentTime = date.getTime();
		if (time < 0) time *= -1;
		long lastTimeAccepted = currentTime - time;
		
		ArrayList<Quiz> quizzes = getAllQuizzes();
		ArrayList<Quiz> sortedQuizzes = new ArrayList<Quiz>();
		if (quizzes.equals(null)) return null;
		
		//System.out.println("Number of quizzes = " + quizzes.size());
		
		if(!quizzes.isEmpty()){
			TreeMap<Integer, HashSet<Quiz>> popularMap = new TreeMap<Integer,HashSet<Quiz>>();
			for(Quiz q:quizzes){
				int numTakers = q.getNumTakers(time);
				
				if(popularMap.containsKey(numTakers)){
					popularMap.get(numTakers).add(q);
				}else{
					HashSet<Quiz> qSet = new HashSet<Quiz>();
					qSet.add(q);
					popularMap.put(numTakers, qSet);
				}			
			}
			
			Integer leastPopularLeft = popularMap.firstKey();
			while(true){
				sortedQuizzes.addAll(popularMap.get(leastPopularLeft));
				leastPopularLeft = popularMap.higherKey(leastPopularLeft);
				if(leastPopularLeft == null) break;
			}			
		}
		for(Quiz q: quizzes){
			//System.out.println("Quiz Num Takers = " + q.getNumTakers(time));
		}
		
		//System.out.println("Number of quizzes after sorting = " + quizzes.size());
		
		Collections.reverse(sortedQuizzes);
		return sortedQuizzes;
	}
	public void removeUser(User user){	
		//Remove all quizzes that the user created
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "DELETE FROM " + DBConstants.T_QUIZZES + " WHERE ";
			cmd += DBConstants.T_QUIZZES_C_CREATOR + " = ";
			cmd += "\"" + user.getUserName() + "\"";
			cmd += ";";
			
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		//Remove all user records from all quizzes
		ArrayList<Quiz> quizzes = getAllQuizzes();
		for(Quiz q:quizzes){
			q.removeTaker(user);
		}
	}
	/*-------------------------- END PUBLIC FUNCTIONS ----------------------------------*/
	
	
	/*-------------------------- INITIALIZATION FUNCTIONS ------------------------------*/
	/**
	 * Initializes the Quizzes table
	 */
	public void initializeQuizTable(){
		try{
			state = (Statement) con.createStatement();
			
			String cmd = "";
			cmd += "CREATE TABLE IF NOT EXISTS " + DBConstants.T_QUIZZES + " (";
			cmd += DBConstants.T_QUIZZES_C_CREATOR + " " + DBConstants.SQL_SHORT_STRING + ", ";
			cmd += DBConstants.T_QUIZZES_C_QUIZ + " " + DBConstants.SQL_SHORT_STRING + ", ";
			cmd += DBConstants.T_QUIZZES_C_CATEGORY + " " + DBConstants.SQL_SHORT_STRING + ", ";
			cmd += DBConstants.T_QUIZZES_C_DATE_CREATED + " " + DBConstants.SQL_INT + ", ";
			cmd += DBConstants.T_QUIZZES_C_ONEPAGE + " " + DBConstants.SQL_BOOLEAN + ", ";
			cmd += DBConstants.T_QUIZZES_C_PRACTICE + " " + DBConstants.SQL_BOOLEAN + ", ";
			cmd += DBConstants.T_QUIZZES_C_DESCRIPTION + " " + DBConstants.SQL_LONG_STRING + ", ";
			cmd += DBConstants.T_QUIZZES_C_TIMED + " " + DBConstants.SQL_BOOLEAN + ", ";
			cmd += DBConstants.T_QUIZZES_C_RANDOMIZED + " " + DBConstants.SQL_BOOLEAN + ", ";
			cmd += DBConstants.T_QUIZZES_C_CORRECTED_IMMEDIATLEY + " " + DBConstants.SQL_BOOLEAN + ", ";
			cmd += DBConstants.T_QUIZZES_C_TIME_TO_TAKE + " " + DBConstants.SQL_INT + " ";
			cmd += ");";

			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}	
	}
	/**
	 * Deletes the Quizzes table
	 */
	public void removeQuizTable(){
		try{
			state = (Statement) con.createStatement();
			String cmd = "";
			cmd += "DROP TABLE " + DBConstants.T_QUIZZES;
			cmd += ";";
			
			state.execute(cmd);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
	public void deleteEverything(){
		removeAllQuizes();
		removeQuizTable();
	}
	/*-------------------------- END INITIALIZATION FUNCTIONS --------------------------*/
}
