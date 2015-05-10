package servlets;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import message.Mailman;

import quiz_system.QuizManager;
import database.DBConnection;
import user.UserManager;
import system.SystemManager;
//import mail.Mailman;

/**
 * Application Lifecycle Listener implementation class QuizListener
 *
 */
@WebListener
public class QuizListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public QuizListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	ServletContext context = arg0.getServletContext();
    	
    	//SystemManager intitialized a database, usermanager, quizmanager, and mailman.
   // 	try {
			SystemManager sm = new SystemManager();
			context.setAttribute("sm", sm);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

//    	DBConnection db = new DBConnection();
//    	context.setAttribute("db", db);
//    	UserManager userManager = new UserManager(db, null, null);
//    	Mailman mailMan;
//		try {
//			mailMan = new Mailman(db, userManager, null);
//			Connection con = db.getDBConnection();
//	    	QuizManager quizManager= new QuizManager((com.mysql.jdbc.Connection) con, userManager);
//			userManager.setAttributes(mailMan, quizManager);
//	    	mailMan.setAttributes(quizManager);
//	 		context.setAttribute("userManager", userManager);
//	   		context.setAttribute("mailMan", mailMan);
//	   		context.setAttribute("quizManager", quizManager);
//		
//		
//		
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	
    	

 
    	

    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
   // 	ServletContext context = arg0.getServletContext();
 //   	SystemManager sm= (SystemManager)context.getAttribute("sm");
//    	try {
////			sm.getUserManager().deleteEverything();
////			System.out.println("deleting everything");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//       DBConnection db = sm.get
//       db.closeConnection();
//        // TODO Auto-generated method stub
    }
	
}
