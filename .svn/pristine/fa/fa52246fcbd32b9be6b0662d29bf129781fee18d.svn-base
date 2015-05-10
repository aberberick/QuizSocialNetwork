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

import message.*;
import system.SystemManager;
import user.*;
import quiz_system.*;


/**
 * Servlet implementation class CreateMessageServlet
 */
@WebServlet("/CreateMessageServlet")
public class CreateMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMessageServlet() {
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
		User sender=(User)session.getAttribute("signedInUser");
		String receiverName=request.getParameter("receiverName");
		String text=request.getParameter("message");
		SystemManager sm = (SystemManager)context.getAttribute("sm");
		Mailman mn = sm.getMailman();
		UserManager um = sm.getUserManager();
		try {
			User receiver = um.getExistingUser(receiverName);
			mn.sendTextMessage(sender, receiver, text);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
	//send back to the home screen
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
		 dispatch.forward(request, response); 

		
	}

}
