<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User History Page for <%= request.getParameter("userName") %></title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
<%@include file="topContainer.jsp" %>
    <div class="jumbotron">
        <div class="container">
        
        <%
             // INIT
    SystemManager sm = (SystemManager)application.getAttribute("sm");
    
    String userName = request.getParameter("userName");
	String takenQuiz = request.getParameter("takenQuiz");
	String createdQuiz = request.getParameter("createdQuiz");

    Mailman mailMan=sm.getMailman();
    QuizManager qm =sm.getQuizManager();
    UserManager um =sm.getUserManager();
    User user =um.getExistingUser(userName);
     %>
        
    <h1><%= userName %>'s History Page</h1>
	</div>
	</div>
        <div id="quizzestaken">
        <h2>Quizzes Taken</h2>
        <%
        ArrayList<Quiz> quizzesTaken = qm.getQuizzesTakenByUser(user);
        // make a selection with a submit that passes back to this page with taken quiz param
        	
            out.write("<p><form action=\"UserHistoryServlet\" method=\"post\">");
                out.write("<select name=\"takenQuiz\">");
                out.write("<option value=\"default\"> </option>");

                // for loop for these
                for (Quiz q: quizzesTaken){
                	out.write("<option value=\""+ q.getName() +"\">" + q.getName() + "</option>");
                }
                out.write("</select><br />");
                out.write("<input type=\"hidden\" name=\"userName\" value=\"" + userName + "\">");

                out.write("<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Submit &raquo;\">");

        // SCORE DISPLAY
        if (!takenQuiz.equals("default") && !takenQuiz.equals("null")) {
        	// print the quiz scores 
        	// get the quiz
        	out.write("<h2>Scores of " + userName + " on quiz: " + takenQuiz + "</h2>");
        	Quiz tquiz = qm.getQuiz(takenQuiz);
        	ArrayList<QuizScore> takenScores = tquiz.getScores(user);
        	
        	System.out.println(takenScores.size());
        	String takenScoresOut = QuizScore.getHTMLTable(takenScores,-1);
        	System.out.println(takenScoresOut);
        	out.write(takenScoresOut);
        }
        
        %>
        
        </div>
       	
       	
       	<hr />
        
        
        <div id="quizzescreated" style ='background-color: #E5E4E2'>
        <h2>Quizzes Created</h2>
        <%
        // make a selection with a submit that passes back to this page with created quiz param
        ArrayList<Quiz> quizzesCreated = qm.getQuizzesCreatedByUser(user);
            
            out.write("<p><form action=\"UserHistoryServlet\" method=\"post\">");
                out.write("<select name=\"createdQuiz\">");
                out.write("<option value=\"default\"> </option>");

                // for loop for these
                for (Quiz q: quizzesCreated){
                    out.write("<option value=\""+ q.getName() +"\">" + q.getName() + "</option>");
                }
                out.write("</select><br />");
                out.write("<input type=\"hidden\" name=\"userName\" value=\"" + userName + "\">");

                out.write("<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Submit &raquo;\">");

        // SCORE DISPLAY
        if (!createdQuiz.equals("default") && !createdQuiz.equals("null")) {
            // print the quiz scores 
            // get the quiz
            out.write("<h2>Scores of all users on quiz: " + createdQuiz + " created by user: " + userName + "</h2>");

            Quiz cquiz = qm.getQuiz(createdQuiz);
            ArrayList<QuizScore> createdScores = cquiz.getScores(TimeConstants.HUNDRED_YEARS);
            
            System.out.println(createdScores.size());
            String createdScoresOut = QuizScore.getHTMLTable(createdScores,-1);
            System.out.println(createdScoresOut);
            out.write(createdScoresOut);
        }
        %>
        
        
        
        </div>

</body>
</html>