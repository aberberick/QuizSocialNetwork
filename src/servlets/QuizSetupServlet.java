package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import quiz_system.QuizManager;
import system.SystemManager;
import user.User;
import user.UserManager;

/**
 * Servlet implementation class QuizSetupServlet
 */
@WebServlet("/QuizSetupServlet")
public class QuizSetupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizSetupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		User user = (User)session.getAttribute("signedInUser");
		
		// get quiz name
		String quizName=request.getParameter("quizName");
		Quiz currentQuiz = quizManager.getQuiz(quizName);

		// SETUP STUFF
		session.setAttribute("qIndex", 0);
		session.setAttribute("currentQuiz", currentQuiz);
		session.setAttribute("score", 0);
		session.setAttribute("timeStart", 0);
		session.setAttribute("timeEnd", 0);
				
		// REDIRECT TO SHOW QUIZ (mainly for multiple page fixes)
		try {
    		RequestDispatcher dispatch = request.getRequestDispatcher("show-quiz.jsp?quizName=" + quizName); 
    		dispatch.forward(request, response); 		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
