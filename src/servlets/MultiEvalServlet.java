package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz_system.FillInBlankQ;
import quiz_system.MatchingQ;
import quiz_system.MultipleAnswerQ;
import quiz_system.MultipleChoiceAnswerQ;
import quiz_system.MultipleChoiceQ;
import quiz_system.PictureResponseQ;
import quiz_system.Question;
import quiz_system.QuestionType;
import quiz_system.Quiz;
import quiz_system.QuizManager;
import quiz_system.ResponseQ;
import system.SystemManager;
import user.User;
import user.UserManager;

/**
 * Servlet implementation class MultiEvalServlet
 */
@WebServlet("/MultiEvalServlet")
public class MultiEvalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MultiEvalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* SETUP REFERENCE */
		// get context, get session
		ServletContext context = request.getServletContext();
		HttpSession session=request.getSession();

		// get system manager.
		SystemManager sm = (SystemManager)context.getAttribute("sm");

		// get quiz
		QuizManager quizManager = sm.getQuizManager();
		ArrayList<Quiz> quizList = quizManager.getAllQuizzes();


		//  get user
		UserManager userManager = sm.getUserManager();
		User user = (User)session.getAttribute("signedInUser");

		// get quiz name
		String quizName=request.getParameter("quizName");
		Quiz q = quizManager.getQuiz(quizName);

		/*
		    session.setAttribute("qIndex", 0);
	        session.setAttribute("currentQuiz", currentQuiz);
	        session.setAttribute("score", 0);
	        session.setAttribute("timeStart", 0);
	        session.setAttribute("timeEnd", 0);
		 */



		// get questions list (randomized or non)
		// get questions
		ArrayList<Question> questions = q.getAllQuestions();
		if (q.isRandomized()) {
			questions = (ArrayList<Question>) session.getAttribute("randomizedArray"); 
		}

		// current question index to display.
		int qIndex = (Integer) session.getAttribute("qIndex");

		// display the individual code for questions.
		QuestionType qt = questions.get(qIndex).getQuestionType();
		int score = (Integer) session.getAttribute("score");
		int  originalScore = (Integer) session.getAttribute("score");
		// get guesses for current index
		QuestionType qType = questions.get(qIndex).getQuestionType();
		if (qType.equals(QuestionType.RESPONSE)) {
			ResponseQ que = (ResponseQ) questions.get(qIndex);
			// one answer
			String guess = request.getParameter("guess" + qIndex + "0");
			if (que.isSolution(guess)) {
				score++;
			}
		}
		else if (qType.equals(QuestionType.FILL_IN_THE_BLANK)) {
			FillInBlankQ que = (FillInBlankQ) questions.get(qIndex);
			// one answer
			String guess = request.getParameter("guess" + qIndex + "0");
			if (que.isSolution(guess)) {
				score++;
			}
		}
		else if (qType.equals(QuestionType.PICTURE_RESPONSE)) {
			PictureResponseQ que = (PictureResponseQ) questions.get(qIndex);
			// one answer
			String guess = request.getParameter("guess" + qIndex + "0");
			if (que.isSolution(guess)) {
				score++;
			}
		}
		else if (qType.equals(QuestionType.MULTIPLE_CHOICE)) {
			MultipleChoiceQ que = (MultipleChoiceQ) questions.get(qIndex);
			// one answer
			String guess = request.getParameter("guess" + qIndex + "0");
			if (que.isCorrectSolution(guess)) {
				score++;
			}
		}
		else if (qType.equals(QuestionType.MULTIPLE_ANSWER)) {
			MultipleAnswerQ que = (MultipleAnswerQ) questions.get(qIndex);
			// multi answer
			HashSet<String> solns = que.getSolutions();
			boolean ordered = que.isOrdered();
			for (int j = 0; j < solns.size(); j++) {
				String guess = request.getParameter("guess" + qIndex + j);
				if (ordered) {
					// order number is j. (0 -> numSolns-1)
					if (que.isSolution(j, guess)) {
						score++;
					}
				}
				else {
					if (que.isSolution(guess)) {
						score++;
					}
				}
			}
		}
		else if (qType.equals(QuestionType.MULTIPLE_CHOICE_ANSWER)) {
			MultipleChoiceAnswerQ que = (MultipleChoiceAnswerQ) questions.get(qIndex);
			HashSet<String> choices = que.getChoices();
			HashSet<String> solns = que.getSolutions();
			HashSet<String> guesses = new HashSet<String>();
			for (int j = 0; j < choices.size(); j++) {
				String guess = request.getParameter("guess" + qIndex + j);
				if (guess != null)
					guesses.add(guess);
			}
			int countRight = 0;
			for (String guess : guesses) {
				if (solns.contains(guess)) {
					countRight++;
				}
				else
					countRight--;
			}
			score += (countRight > 0) ? countRight : 0;
		}
		else if (qType.equals(QuestionType.MATCHING)) {
			MatchingQ que = (MatchingQ) questions.get(qIndex);
			// get the matches they enter
			HashMap<String, String> matches = (HashMap<String, String>)que.getMatches(2);
			for (int j = 0; j < matches.size(); j++ ) {
				String guess = request.getParameter("guess" + qIndex + j);
				String pairing = matches.get(guess);
				if (que.isMatch(pairing, guess)) {
					score++;
				}
			}
		}


		// compare to solution for equivalent question
		// do grading here
		qIndex++;
		session.setAttribute("qIndex", qIndex);
		//this means this question was correct
		if(score > originalScore) {
			session.setAttribute("isCorrect", "true");
		}
		else {
			session.setAttribute("isCorrect", "false");
		}
		
		session.setAttribute("score", score);
		boolean userWantsPractice = (request.getParameter("userWantsPractice") != null);
		session.setAttribute("userWantsPractice", userWantsPractice);
		// REDIRECT TO SHOW QUIZ (mainly for multiple page fixes)
		try {
			if(q.isCorrectedImmediatley()) {
				RequestDispatcher dispatch = request.getRequestDispatcher("questionResult.jsp"); 
				dispatch.forward(request, response); 
			}

			else {
				RequestDispatcher dispatch = request.getRequestDispatcher("show-quiz.jsp?quizName=" + quizName); 
				dispatch.forward(request, response); 		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		


	}

	// TODO add immediate correction page direct. Not too bad.
	// if (immediateCorrection...) redirect elsewhere



}
