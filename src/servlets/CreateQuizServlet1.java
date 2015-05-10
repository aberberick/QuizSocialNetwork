package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateQuizServlet1
 */
@WebServlet("/CreateQuizServlet1")
public class CreateQuizServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet1() {
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
		/* THIS SERVLET RECEIVES A QUESTION TYPE AND PASSES ON TO RECEIVE INFO */
		// standard
		ServletContext context=request.getServletContext();
		HttpSession session=request.getSession();
		SystemManager sm= (SystemManager)context.getAttribute("sm");
		UserManager userManager = sm.getUserManager();

		// q type processing
		String qType = request.getParameter("qType");
		String numAnswers = request.getParameter("numAnswers");
		String numChoices = request.getParameter("numChoices");
//		String numResponses = request.getParameter("numResponses");

		session.setAttribute("qType", "no question");
		
		try {
			session.setAttribute("qType", qType);
        	session.setAttribute("numAnswers", numAnswers);
        	session.setAttribute("numChoices", numChoices);
//        	session.setAttribute("numResponses", numResponses);
			RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz2.jsp"); 
			dispatch.forward(request, response); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

}
