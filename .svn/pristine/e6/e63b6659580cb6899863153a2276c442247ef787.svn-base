package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz_system.Quiz;
import quiz_system.QuizCategory;
import quiz_system.QuizManager;

import system.SystemManager;
import user.Achievement;
import user.User;
import user.UserManager;

/**
 * Servlet implementation class CreateQuizServlet0
 */
@WebServlet("/CreateQuizServlet0")
public class CreateQuizServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet0() {
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
		/* THIS SERVLET RECEIVES QUIZ INFO AND PROCESSES IT TO MAKE A NEW QUIZ */
		
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
		String userName = (String)session.getAttribute("signedInUserName");
		User user = null;
		try {
			user = userManager.getExistingUser(userName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// check quiz name
		String quizname=request.getParameter("quizname");
		session.setAttribute("quizName", quizname);
		
		String quizcategory=request.getParameter("quizcategory");	
		
		boolean quizTaken = false;
		request.setAttribute("quizTaken", false);
		for (int i = 0; i < quizList.size(); i++) {
			String quizI = quizList.get(i).getName();
			if (quizI.equals(quizname)) {
				request.setAttribute("quizTaken", true);
				quizTaken = true;
			}
		}
		
		// if available
		if (!quizTaken) {
			// set quiz name and maybe init a new quiz object with that name
			Quiz q = quizManager.createAndAddQuiz(QuizCategory.fromString(quizcategory), quizname, user);
			// then store it and process to question select.
			
			q.addTag(request.getParameter("tag0"));
			q.addTag(request.getParameter("tag1"));
			q.addTag(request.getParameter("tag2"));
			
			q.setDescription(request.getParameter("qdescription"));
			
			q.setOnePage(request.getParameter("multiPage") == null);
			q.setRandomized(request.getParameter("randomized") != null);
			q.setPracticeEnabled(request.getParameter("practiceEnabled") != null);
			q.setTimed(request.getParameter("timed") != null);
			// IMMEDIATE setup
			q.setCorrectedImmediatley(request.getParameter("immediateFeedbackEnabled") != null);
			if (q.isTimed()) {
				long sec = Long.parseLong(request.getParameter("time"));
				long ms = sec*1000;
				q.setTimeToTake(ms);				
			}

			// Achievements
			try {
				if (user != null){
					ArrayList<Achievement> as = user.getAchievements();
					ArrayList<Quiz> quizzesCreated = user.getCreatedQuizes();
					int numCreated = quizzesCreated.size();
					if (numCreated == 1 && !as.contains(Achievement.AMATEUR_AUTHOR)) {
						user.addAchievement(Achievement.AMATEUR_AUTHOR);
					}
					if (numCreated == 5) {
						user.addAchievement(Achievement.PROLIFIC_AUTHOR);
					}
					if (numCreated == 10) {
						user.addAchievement(Achievement.PRODIGIOUS_AUTHOR);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			session.setAttribute("questionTaken", false);
			session.setAttribute("quizTaken", quizTaken);
			session.setAttribute("inProgressQuiz", quizname);
			RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz1.jsp"); 
			dispatch.forward(request, response); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
