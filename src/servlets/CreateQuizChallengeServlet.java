package servlets;

import java.io.IOException;
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

import message.*;
import system.SystemManager;
import user.User;
import quiz_system.*;
import user.UserManager;

/**
 * Servlet implementation class CreateQuizChallengeServlet
 */
@WebServlet("/CreateQuizChallengeServlet")
public class CreateQuizChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizChallengeServlet() {
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
		SystemManager sm= (SystemManager)context.getAttribute("sm");
		String receiverName=request.getParameter("receiverName");
		String text=request.getParameter("text");
		String quizName=request.getParameter("quizName");
		QuizManager qm = sm.getQuizManager();
		UserManager um = sm.getUserManager();
		Mailman mailMan= sm.getMailman();
		Quiz quiz = qm.getQuiz(quizName);
	
		
		try {
//				mailMan.sendChallenge((User)session.getAttribute("signedInUser"), um.getExistingUser(receiver), text, quiz);
//			ArrayList<ChallengeRequest> requests =mailMan.getChallengeRequests(um.getExistingUser(receiver));
			User receiver = um.getExistingUser(receiverName);
			mailMan.sendChallenge((User)session.getAttribute("signedInUser"), receiver, text, quiz);
			ArrayList<ChallengeRequest> requests =mailMan.getChallengeRequests(receiver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
		dispatch.forward(request, response); 	
	}

}
