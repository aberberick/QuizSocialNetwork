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
import user.*;

/**
 * Servlet implementation class CreateAdminServlet
 */
@WebServlet("/CreateAdminServlet")
public class CreateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdminServlet() {
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
		SystemManager sm = (SystemManager)context.getAttribute("sm");
		UserManager um = sm.getUserManager();
		HttpSession session=request.getSession();
		
//		String userName=(String)session.getAttribute("signedInUserName");
		String userName=request.getParameter("name");
		//System.out.println("");
		//System.out.println("username at create admin: " + userName);
		
		try {
			um.makeAdmin(userName);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp"); 
		 dispatch.forward(request, response); 

	}

}
