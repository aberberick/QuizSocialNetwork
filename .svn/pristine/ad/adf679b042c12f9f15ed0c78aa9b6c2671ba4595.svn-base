package quiz_system.test;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz_system.Question;
import quiz_system.QuestionType;
import quiz_system.Quiz;
import quiz_system.QuizCategory;
import quiz_system.QuizManager;
import quiz_system.QuizScore;
import system.SystemManager;
import user.User;
import user.UserManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class QuestionTest {
	/*-------------------------- CONSTANTS ---------------------------------------------*/
	public static final int NUM_CREATORS = 1;
	public static final int NUM_QUIZZES = 2;
	public static final int NUM_QUESTIONS = 10;
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
		System.out.println("Start Question Test");
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
		System.out.println("End Question Test");
	}	
	private void addUsers(){
		try{
			for(int index = 0; index<NUM_CREATORS; index++){
				um.createUser(TConst.TEST_USER_NAME.get(index),
						TConst.TEST_USER_PASSWORD.get(index));
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
			boolean onePage = TConst.TEST_QUIZ_ONEPAGE.get(quiz);
			boolean practice = TConst.TEST_QUIZ_PRACTICE.get(quiz);
			String description = TConst.TEST_QUIZ_DESCRIPTION.get(quiz);
			boolean timed = TConst.TEST_QUIZ_TIMED.get(quiz);
			boolean randomized = TConst.TEST_QUIZ_RANDOMIZED.get(quiz);
			boolean ordered = TConst.TEST_QUIZ_ORDERED.get(quiz);
			
			Quiz q = qm.createAndAddQuiz(category, quizName, um.getExistingUser(creator));
		
			q.setCategory(category);
			q.setOnePage(onePage);
			q.setPracticeEnabled(practice);
			q.setDescription(description);
			q.setTimed(timed);
			q.setRandomized(randomized);
			
			addQuestionsToQuiz(q);
		}	
	}
	private void addQuestionsToQuiz(Quiz quiz) throws SQLException{
		for(int question=0; question<NUM_QUESTIONS; question++){
			String name = TConst.TEST_QUEST_NAME.get(question);
			String prompt = TConst.TEST_QUEST_PROMPT.get(question);
			QuestionType type = TConst.TEST_QUEST_TYPE.get(question);
			
			quiz.createAndAddQuestion(type, name, prompt);
		}
	}
	/*-------------------------- INITIALIZATION/DESTRUCTION FUNCTIONS ------------------*/
	
	
	/*-------------------------- TEST FUNCTIONS ----------------------------------------*/
	@Test 
	public void testQuestion() throws SQLException{
		ArrayList<Quiz> quizzes;
		ArrayList<Question> questions;
		
		quizzes = qm.getAllQuizzes();
		for(Quiz quiz:quizzes){
			
			questions = quiz.getAllQuestions();
			
			Question quest = questions.get(0);
			
			quest.setPrompt("New Prompt1");
			assert(quest.getPrompt().equals("New Prompt1"));
			quest.setPrompt("New Prompt2");
			assert(quest.getPrompt().equals("New Prompt2"));
			
			quest.setTimeToTake(10000);
			assert(quest.getTimeToTake() == 10000);
			quest.setTimeToTake(1000);
			assert(quest.getTimeToTake() == 1000);
			
			System.out.println(quiz.questionsToString());
		}
		
		System.out.println(qm.quizzesToString());
	}
	/*-------------------------- END TEST FUNCTIONS ------------------------------------*/
	
	
	/*-------------------------- PRIVATE HELPER FUNCTIONS ------------------------------*/
	/*-------------------------- END PRIVATE HELPER FUNCTIONS --------------------------*/
}
