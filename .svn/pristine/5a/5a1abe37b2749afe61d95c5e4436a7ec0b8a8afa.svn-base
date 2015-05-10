package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import message.FriendRequest;
import message.Mailman;

import system.SystemManager;
import user.User;
import user.UserManager;

/**
 * Servlet implementation class CreateFriendRequestServlet
 */
@WebServlet("/CreateFriendRequestServlet")
public class CreateFriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateFriendRequestServlet() {
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
		String friendUserName=request.getParameter("friendUserName");
		UserManager um = sm.getUserManager();
		Mailman mailMan= sm.getMailman();
		User signedInUser=(User)session.getAttribute("signedInUser");
		
		try {
			User friend = um.getExistingUser(friendUserName);
			ArrayList <User> friends = signedInUser.getFriendList();
//			ArrayList <FriendRequest> requestsList1 = mailMan.getFriendRequests(friend);
//			ArrayList <FriendRequest> requestsList2 = mailMan.getFriendRequests(signedInUser);
			if(friend!=null && !friends.contains(friend)) {
				mailMan.sendFriendRequest(signedInUser, friend, request.getParameter("text"));
			}
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
		dispatch.forward(request, response); 		
	}

}
