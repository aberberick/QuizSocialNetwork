package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import system.SystemManager;
import user.UserManager;
import quiz_system.*;

/**
 * Servlet implementation class QuizSearchServlet
 */
@WebServlet("/QuizSearchServlet")
public class QuizSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizSearchServlet() {
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
		ServletContext context=request.getServletContext();
		HttpSession session=request.getSession();
		String quizName=request.getParameter("quizName");
		SystemManager sm = (SystemManager)context.getAttribute("sm");
		QuizManager qm =sm.getQuizManager();
		if(qm.isQuizNameTaken(quizName)) {

			RequestDispatcher dispatch = request.getRequestDispatcher("quizSummary.jsp"); 
			dispatch.forward(request, response); 
			return;//fwd to profile page
		}
		else {
			RequestDispatcher dispatch = request.getRequestDispatcher("noSuchQuiz.jsp"); 
			dispatch.forward(request, response); 
			return;
		}
	}

}
