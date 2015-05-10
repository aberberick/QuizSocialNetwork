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

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccountServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			if(userManager.isUserNameAvailable(userName)) {
				request.setAttribute("loginSuccess", true);
				userManager.createUser(userName, password);
//				context.setAttribute("signedInUserName", userName);
//				context.setAttribute("signedInUser", userManager.getExistingUser(userName));
		
				session.setAttribute("signedInUserName", userName);
				session.setAttribute("signedInUser", userManager.getExistingUser(userName));
				
				
				RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
				dispatch.forward(request, response); 
			}
			else {
				request.setAttribute("loginSuccess", false);
				request.setAttribute("accountTaken", true);
				request.setAttribute("userName", userName);
				RequestDispatcher dispatch = request.getRequestDispatcher("createAccount.jsp"); 
				dispatch.forward(request, response); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		

	}

}
