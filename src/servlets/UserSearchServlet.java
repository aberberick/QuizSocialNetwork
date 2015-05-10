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

import message.Mailman;
import system.SystemManager;
import user.User;
import user.UserManager;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
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
		String name=request.getParameter("name");
		SystemManager sm = (SystemManager)context.getAttribute("sm");
		UserManager um = sm.getUserManager();
		try {
			if(!um.isUserNameAvailable(name)) {
			//	response.sendRedirect("/QuizWeb/UserServlet");
				request.setAttribute("name", name);
				RequestDispatcher dispatch = request.getRequestDispatcher("profile.jsp"); 
				 dispatch.forward(request, response); 
				return;//fwd to profile page
			}
			else {
				RequestDispatcher dispatch = request.getRequestDispatcher("noUser.jsp"); 
				 dispatch.forward(request, response); 
				 return;
			}
		} catch (SQLException e) {
			RequestDispatcher dispatch = request.getRequestDispatcher("noUser.jsp"); 
			 dispatch.forward(request, response); 		}
		
//		try {
//	//		request.setAttribute("searched", true);
//			request.setAttribute("searched", "true");
//			
//			if(um.isUserNameAvailable(userNameSearch)) {
//				request.setAttribute("userExists", false);
//				System.out.println("on search user exists flase");
//
//			}
//			else {
//
//				request.setAttribute("userExists", true);
//				System.out.println("on search user exists"); 
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		RequestDispatcher dispatch = request.getRequestDispatcher("allUsers.jsp"); 
//		 dispatch.forward(request, response); 

	}

}
