package servlets;

import java.io.IOException;
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
 * Servlet implementation class ClearQuizHistoryServlet
 */
@WebServlet("/ClearQuizHistoryServlet")
public class ClearQuizHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearQuizHistoryServlet() {
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
		// TODO Auto-generated method stub
		
		ServletContext context=request.getServletContext();
		SystemManager sm = (SystemManager)context.getAttribute("sm");
		UserManager um = sm.getUserManager();
		HttpSession session=request.getSession();
		
		QuizManager qm = sm.getQuizManager();

		String quizName = request.getParameter("quizName");
		
		// get quiz
		// clear quiz scores
		Quiz q = qm.getQuiz(quizName);
		ArrayList<User> takers = q.getTakers();
		for (User taker : takers) {
			q.removeTaker(taker);
			
		}
		// redirect to home
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
		 dispatch.forward(request, response); 

	}

}
