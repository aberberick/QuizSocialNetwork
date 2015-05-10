package quiz_system.test;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz_system.FillInBlankQ;
import quiz_system.Question;
import quiz_system.QuestionType;
import quiz_system.Quiz;
import quiz_system.QuizCategory;
import quiz_system.QuizManager;
import system.SystemManager;
import user.UserManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class FillInBlankQTest {
	/*-------------------------- CONSTANTS ---------------------------------------------*/
	public static final int NUM_CREATORS = 1;
	public static final int NUM_QUIZZES = 1;
	public static final int NUM_QUESTIONS = 1;
	
	public static final String QUESTION_NAME = "TestQuestion";
	public static final QuestionType QUESTION_TYPE = QuestionType.FILL_IN_THE_BLANK;
	public static final String QUESTION_PROMPT = "Default Prompt";
	
	public static final HashSet<String> SOLUTIONS = 
		new HashSet(Arrays.asList(new String[] {"Sol1","Sol2","Sol3","Sol4"}));
	/*-------------------------- END CONSTANTS -----------------------------------------*/
	
	
	/*-------------------------- PRIVATE INSTANCE VARIABLES ----------------------------*/
	private SystemManager sm;
	private UserManager um;
	private QuizManager qm;
	
	private Connection con;
	private ResultSet rs;
	private Statement state;	
	/*-------------------------- END PRIVATE INSTANCE VARIABLES ------------------------*/
	
	
	/*-------------------------- INITIALIZATION/DESTRUCTION FUNCTIONS ------------------*/
	@Before
	public void initializeTests() throws SQLException{
		System.out.println("Start Question Fill In the Blank Test");
		System.out.println("Initialization Sequence");
	
		sm = new SystemManager();
		um = sm.getUserManager();
		qm = sm.getQuizManager();
		
		addUsers();
		addQuizzes();
		System.out.println("End Initialization Sequence\n");
	}
	@After
	public void destroyTests() throws SQLException{
		System.out.println("\nEnding Sequence");
		sm.deleteEverything();
		sm.closeDatabaseConnection();
		System.out.println("End Question Fill In the Blank Test");
	}	
	private void addUsers(){
		try{
			for(int index = 0; index<NUM_CREATORS; index++){
				um.createUser(TConst.TEST_USER_NAME.get(index),TConst.TEST_USER_PASSWORD.get(index));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	private void addQuizzes() throws SQLException{
		for(int user = 0; user<NUM_CREATORS; user++){
			String username = TConst.TEST_USER_NAME.get(user);
			addQuizzes(username);
		}
	}
	private void addQuizzes(String creator) throws SQLException{
		for(int quiz = 0; quiz<NUM_QUIZZES; quiz++){
			String quizName = creator + TConst.TEST_QUIZ_NAME.get(quiz);
			QuizCategory category = TConst.TEST_QUIZ_CATEGORY.get(quiz);
			
			Quiz q = qm.createAndAddQuiz(category, quizName, um.getExistingUser(creator));
			addQuestionToQuiz(q);
		}	
	}
	private void addQuestionToQuiz(Quiz quiz) throws SQLException{
		String name = QUESTION_NAME;
		String prompt = QUESTION_PROMPT;
		QuestionType type = QUESTION_TYPE;
		quiz.createAndAddQuestion(type, name, prompt);
	}
	/*-------------------------- INITIALIZATION/DESTRUCTION FUNCTIONS ------------------*/
	
	
	/*-------------------------- TEST FUNCTIONS ----------------------------------------*/
	@Test 
	public void testResponseQ() throws SQLException{
		ArrayList<Quiz> quizzes;
		ArrayList<Question> questions;
		Question quest;
		FillInBlankQ fibq;
		
		quizzes = qm.getAllQuizzes();
		for(Quiz quiz:quizzes){
			
			questions = quiz.getAllQuestions();
			quest = questions.get(0);
			fibq = (FillInBlankQ) quest;
			
			fibq.addSolutions(SOLUTIONS);
			HashSet<String> sols = fibq.getSolutions();
			assert(fibq.getSolutions().equals(SOLUTIONS));
			
			fibq.addSolution("Sol5");
			assert(fibq.isSolution("Sol5"));
			
			fibq.removeSolution("Sol5");
			assert(fibq.getSolutions().equals(SOLUTIONS));
		}
	}
	/*-------------------------- END TEST FUNCTIONS ------------------------------------*/
}
