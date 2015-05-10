package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz_system.*;
import system.SystemManager;
import java.util.*;

/**
 * Servlet implementation class QuizSearchCategoryServlet
 */
@WebServlet("/QuizSearchCategoryServlet")
public class QuizSearchCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizSearchCategoryServlet() {
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
//		SystemManager sm = (SystemManager)context.getAttribute("sm");
//		QuizManager qm =sm.getQuizManager();
//
//		String category=request.getParameter("quizCategory");
//		ArrayList<Quiz> allQuizzes =qm.getAllQuizzes();
//		ArrayList<Quiz> selectedQuizzes = new ArrayList<Quiz>();
//		for(int i=0; i<allQuizzes.size(); i++) {
//			Quiz quiz =allQuizzes.get(i);
//			//if((quiz.getCategory().toString()).equals(category)) {
//			if(quiz.getCategory().equals(QuizCategory.fromString(category))) {
//				selectedQuizzes.add(quiz);
//			}
//		}
//
//
//		if(selectedQuizzes.size()>0) {
//
//			RequestDispatcher dispatch = request.getRequestDispatcher("quizSearchCategoryResults.jsp"); 
//			dispatch.forward(request, response); 
//			return;//fwd to profile page
//		}
//		else {
//			RequestDispatcher dispatch = request.getRequestDispatcher("noSuchQuiz.jsp"); 
//			dispatch.forward(request, response); 
//			return;
//		}
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
		dispatch.forward(request, response); 
		}

}
