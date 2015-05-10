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

import quiz_system.FillInBlankQ;
import quiz_system.MatchingQ;
import quiz_system.MultipleAnswerQ;
import quiz_system.MultipleChoiceAnswerQ;
import quiz_system.MultipleChoiceQ;
import quiz_system.PictureResponseQ;
import quiz_system.Question;
import quiz_system.QuestionType;
import quiz_system.Quiz;
import quiz_system.QuizManager;
import quiz_system.ResponseQ;

import system.SystemManager;
import user.UserManager;

/**
 * Servlet implementation class CreateQuizServlet2
 */
@WebServlet("/CreateQuizServlet2")
public class CreateQuizServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet2() {
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
		// standard
		ServletContext context=request.getServletContext();
		HttpSession session=request.getSession();
		SystemManager sm= (SystemManager)context.getAttribute("sm");
		UserManager userManager = sm.getUserManager();
		QuizManager qm = (QuizManager)sm.getQuizManager();
		
		// print writer
		PrintWriter out = response.getWriter();
		
		// question processing
		String qType = request.getParameter("qType");
		String inProgressQuiz = (String) session.getAttribute("inProgressQuiz");
		String qtitle = request.getParameter("qtitle");
		String qprompt = request.getParameter("qprompt");
		String qanswer = request.getParameter("qanswer");
		int numAnswers = Integer.parseInt((String) session.getAttribute("numAnswers"));
		int numChoices = Integer.parseInt((String) session.getAttribute("numChoices"));
//		int numResponses = Integer.parseInt((String) session.getAttribute("numResponses"));
		Quiz q = qm.getQuiz(inProgressQuiz);
		session.setAttribute("questionTaken", false);

		if (q.isQuestionNameTaken(qtitle)) {
			// TODO redirect back with a fail message
			try {
				session.setAttribute("questionTaken", true);
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz1.jsp"); 
				dispatch.forward(request, response); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		else {
		    if (qType.equals("response")) {
				ResponseQ que = (ResponseQ) q.createAndAddQuestion(QuestionType.RESPONSE, qtitle, qprompt);
				for (int i = 0; i < numAnswers; i++) {
					String soln = request.getParameter("qanswer" +i);
					System.out.println("soln");
					que.addSolution(soln);
				}
			}
	       	else if (qType.equals("fillintheblank")) {
	       		FillInBlankQ que = (FillInBlankQ) q.createAndAddQuestion(QuestionType.FILL_IN_THE_BLANK, qtitle, qprompt);
				for (int i = 0; i < numAnswers; i++) {
					String soln = request.getParameter("qanswer" +i);
					que.addSolution(soln);
				}

	       	}
	       	else if (qType.equals("multiplechoice")) {
	       		MultipleChoiceQ que = (MultipleChoiceQ) q.createAndAddQuestion(QuestionType.MULTIPLE_CHOICE, qtitle, qprompt);
				for (int i =0; i < numChoices; i++) {
					String choice = request.getParameter("qchoice" +i);
					que.addChoice(choice);
				}
	       		que.setSolution(qanswer);
	       	}
	       	else if (qType.equals("picture")) {
	       		PictureResponseQ que = (PictureResponseQ) q.createAndAddQuestion(QuestionType.PICTURE_RESPONSE, qtitle, qprompt);
	       		for (int i =0; i < numAnswers; i++) {
	       			String soln = request.getParameter("qanswer" + i);
	       			que.addSolution(soln);
	       		}
	       		String qpicture = request.getParameter("qpicture");
	       		if (qpicture.equals("dog")) {
		       		que.setPicture("http://abcnews.go.com/images/Lifestyle/GTY_yawning_dog_dm_130807.jpg");
	       		}
	       		else if (qpicture.equals("cat")) {
		       		que.setPicture("http://www.petfinder.com/wp-content/uploads/2012/11/99059361-choose-cat-litter-632x475.jpg");
	       		}
	       		else if (qpicture.equals("flag")) {
		       		que.setPicture("http://1.bp.blogspot.com/-ZJYNERNT8z0/UD2AQhcueII/AAAAAAAAAfk/uxL6NpFY4I0/s1600/wave_usa_flagMiddletown+Insider.jpg");
	       		}
	       		else {
		       		que.setPicture("http://www.wwubap.org/wp-content/uploads/2012/03/no-available-image.png");
	       		}
	       	}
	       	else if (qType.equals("matching")) {
	       		MatchingQ que = (MatchingQ) q.createAndAddQuestion(QuestionType.MATCHING, qtitle, qprompt);
	       		for (int i =0; i < numAnswers; i++) {
	       			que.addSolution(request.getParameter("qcol1" + i), request.getParameter("qcol2" + i));
	       		}
	       	}
	        
	       	else if (qType.equals("multiplechoicemultipleanswer")) {
	       		MultipleChoiceAnswerQ que = (MultipleChoiceAnswerQ) q.createAndAddQuestion(QuestionType.MULTIPLE_CHOICE_ANSWER, qtitle, qprompt);
				for (int i =0; i < numChoices; i++) {
					String choice = request.getParameter("qchoice" +i);
					que.addChoice(choice);
				}
	       		for (int i =0; i < numAnswers; i++) {
	       			String soln = request.getParameter("qanswer" + i);
	       			que.addSolution(soln);
	       		}
	       	}
	       	else if (qType.equals("multipleanswer")) {
	       		MultipleAnswerQ que = (MultipleAnswerQ) q.createAndAddQuestion(QuestionType.MULTIPLE_ANSWER, qtitle, qprompt);
	       		boolean isOrdered = (request.getParameter("ordered") != null);
				que.setOrdered(isOrdered);
				for (int i = 0; i < numAnswers; i++) {
					String soln = request.getParameter("qanswer" +i);
					que.addSolution(i, soln);
				}
	       	}
	
	        else {
	            out.write("<p>Something went wrong. Try clicking home button.</p>");
	        }
		    
			try {
				RequestDispatcher dispatch = request.getRequestDispatcher("createQuiz1.jsp"); 
				dispatch.forward(request, response); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

		}
	}
}
