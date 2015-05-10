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

import quiz_system.Question;
import quiz_system.Quiz;
import quiz_system.QuizManager;
import system.SystemManager;
import user.User;
import user.UserManager;

/**
 * Servlet implementation class RemoveQuestionServlet
 */
@WebServlet("/RemoveQuestionServlet")
public class RemoveQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveQuestionServlet() {
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
		User user=(User)session.getAttribute("signedInUser");
		String questionName=request.getParameter("questionName");
		String quizName = request.getParameter("quizName");
		
		System.out.println("removing question: " + questionName);
		
		SystemManager sm = (SystemManager)context.getAttribute("sm");
		UserManager um = sm.getUserManager();
		QuizManager qm = sm.getQuizManager();
		
		Quiz quiz = qm.getQuiz(quizName);
		Question question = quiz.getQuestion(questionName);			
		quiz.removeQuestion(question);
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher("editQuiz.jsp"); 
		dispatch.forward(request, response); 
	}

}
