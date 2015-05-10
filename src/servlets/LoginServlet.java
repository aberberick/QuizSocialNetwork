package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

//import quiz.UserManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		RequestDispatcher dispatch = request.getRequestDispatcher("index.html"); 
		//		 dispatch.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context=request.getServletContext();
		HttpSession session=request.getSession();
		SystemManager sm= (SystemManager)context.getAttribute("sm");
		UserManager userManager = sm.getUserManager();

		String userName=request.getParameter("userName");
		String password=request.getParameter("password");



		try {
			if (!userManager.isUserNameAvailable(userName)) { // user contained
				session.setAttribute("validUser", true);
				if(userManager.isPasswordValid(userName, password)) {
					session.setAttribute("signedInUserName", userName);
					session.setAttribute("signedInUser", userManager.getExistingUser(userName));
					session.setAttribute("loginSuccess", true);
					session.setAttribute("userName", userName);
					RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
					dispatch.forward(request, response); 

				}
				else {
					session.setAttribute("loginSuccess", false);
					session.setAttribute("userName", userName);
					RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp"); 
					dispatch.forward(request, response); 
				}
			}
			else {
				session.setAttribute("loginSuccess", false);
				session.setAttribute("validUser", false);
				session.setAttribute("userName", userName);
				RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp"); 
				dispatch.forward(request, response); 
			}
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
