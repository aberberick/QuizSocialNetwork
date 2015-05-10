<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Summary</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
<%@include file="topContainer.jsp" %>
    <div class="jumbotron">
        <div class="container">

	   <% 
	   User user = (User)session.getAttribute("signedInUser");
	    // Get Quiz
	    String quizName = request.getParameter("quizName");
	    
	    System.out.println(quizName);
	    
	    
	    SystemManager sm = (SystemManager)application.getAttribute("sm");
	    QuizManager quizManager = sm.getQuizManager();
	    Quiz q = quizManager.getQuiz(quizName);
	    User creator =q.getCreator();
	    ArrayList <QuizScore> quizScores=q.getScores(); //ordered worst to best.
	    QuizScore lowestScore=q.getLowestScore();
	    QuizScore highestScore =q.getHighScore();
	    long currentTime=System.currentTimeMillis();
	    int numTakers=q.getNumTakers();
	    HashSet<User> offendedUsers=q.getTakersWhoDislikeQuiz();
	    
	    long timeStart = System.currentTimeMillis();
	    session.setAttribute("timeStart", timeStart);
	    
	    boolean onePage = q.isOnePage();
	    boolean practiceEnabled = q.isPracticeEnabled();
	    boolean randomized = q.isRandomized();
	    boolean timed = q.isTimed();
	    int numToPrint = 10;
	    %>  
       <h1>Quiz Summary for : <%= request.getParameter("quizName")%></h1>
     </div> 
    </div>
         
       <div id="description" style ='background-color: #E5E4E2'>
       <h2>Description</h2>
       <p>Name: <%= q.getName() %></p>
       <p>Num Questions: <%= q.getNumQuestions() %></p>
       <p>Quiz Category: <%= q.getCategory().toString() %></p>
      <p>Tags: 
      <% 
       for (String tag : q.getTags()) {
    	    out.write(tag + " " );
       }
      %>
      </p>
      <p>Creator: <%= q.getCreator().getHTML() %></p>
      <p>Date created: <%= TimeConstants.getDate(q.getDateCreated()) %></p>
      
      <%  if (user.equals(q.getCreator())) { %>
   		 <form action="editQuiz.jsp" method="post">
   		 <input name="quizName" type="hidden" value="<%=q.getName()%>">
         <input type="Submit" class="btn btn-lg btn-warning" value="Edit Quiz" />
    	</form>    
      <% }%>
      
       </div>
       <hr/>
       
       <div id="takequiz">
       <h2>Take Quiz</h2>
       <p><a class="btn btn-lg btn-primary" href="QuizSetupServlet?quizName=<%= q.getName() %>">Take Quiz</a></p>
       </div>
       <hr/>
       
       

       <div id="scores" style ='background-color: #E5E4E2'>
 	   <% 
       String myOrder = request.getParameter("myOrder");
       String timePeriod = request.getParameter("timePeriod");
       
       if (myOrder == null) {
    	   myOrder = "date";
       }
       if (timePeriod == null) {
    	   timePeriod = "day";
       }
       %>
       <h2>Scores</h2>
       <h3>My Scores</h3>
       <!-- write a select for type. -->
       <!--  show scores based on type -->
       <!--  have a submit button for type select which redirects to quizsummary servlet -->
       <%
       out.write("<p><form action=\"QuizSummaryServlet\" method=\"post\">");
       out.write("<select name=\"myOrder\">");
       out.write("<option value=\"date\">Date</option>");
       out.write("<option value=\"score\">Score</option>");
       out.write("<option value=\"timeToTake\">Time to take</option>");
       out.write("</select><br />");
       out.write("<input name=\"quizName\" type=\"hidden\" value=\"" + q.getName() + "\">");
       out.write("<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Submit &raquo;\">");
 
       // display the scores:
    	   ArrayList<QuizScore> myScores = q.getScores(user);
       if (myOrder.equals("date")) {
    	   out.write("<h4>Ordered by Date</h4>");
    	   myScores = QuizScore.orderByDateTaken(myScores);
       }
       else if (myOrder.equals("score")) {
           out.write("<h4>Ordered by Score</h4>");
           myScores = QuizScore.orderByScore(myScores);
       }
       else{
           out.write("<h4>Ordered by Time to Take</h4>");
    	   myScores = QuizScore.orderByTimeToTake(myScores);
       }
       String scoreHTMLTableScore = QuizScore.getHTMLTable(myScores,numToPrint);
	   //System.out.println(scoreHTMLTableScore);
       out.write(scoreHTMLTableScore);
 		%>
 		
       <h3>Top Performers in Time Period</h3>
       <!-- write a select for type. -->
       <!--  show scores based on type -->
       <!--  have a submit button for type select which redirects to quizsummary servlet -->
		<%     	
	   out.write("<p><form action=\"QuizSummaryServlet\" method=\"post\">");
       out.write("<select name=\"timePeriod\">");
       out.write("<option value=\"day\">Day</option>");
       out.write("<option value=\"week\">Week</option>");
       out.write("<option value=\"month\">Month</option>");
       out.write("<option value=\"year\">Year</option>");
       out.write("</select><br />");
       out.write("<input name=\"quizName\" type=\"hidden\" value=\"" + q.getName() + "\">");
       out.write("<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Submit &raquo;\">");
       
       // display scores
       ArrayList<QuizScore> timeScores = new ArrayList<QuizScore>();
       if (timePeriod.equals("day")) {
           out.write("<h4>Scores from last Day</h4>");
           timeScores = q.getScores(TimeConstants.DAY);
       }
       else if (timePeriod.equals("week")) {
           out.write("<h4>Scores from last Week</h4>");
           timeScores = q.getScores(TimeConstants.WEEK);
       }
       else if (timePeriod.equals("month")) {
           out.write("<h4>Scores from last Month</h4>");
           timeScores = q.getScores(TimeConstants.MONTH);
       }
       else{
           out.write("<h4>Scores from last Year</h4>");
           timeScores = q.getScores(TimeConstants.YEAR);
       }
       String scoreHTMLTableDate = QuizScore.getHTMLTable(timeScores,numToPrint);
	   System.out.println(scoreHTMLTableDate);
       out.write(scoreHTMLTableDate);
       
       %>
       <h3>Top Performers All Time</h3>
       <% 
       ArrayList<QuizScore> allTimeHighScores = q.getScores(TimeConstants.HUNDRED_YEARS);
       out.write(QuizScore.getHTMLTable(allTimeHighScores,numToPrint));
       %>
       
       <h3>Most Recent Scores</h3>
       <%
       ArrayList<QuizScore> mostRecentScores = q.getScores(TimeConstants.HUNDRED_YEARS);
       mostRecentScores = QuizScore.orderByDateTaken(mostRecentScores);
       out.write(QuizScore.getHTMLTable(mostRecentScores,numToPrint));
       %>
       </div>
       <hr/>
       
       
       <div id="stats">
       <h2>Quiz Statistics</h2>
       <p>Number of takers:  <%= q.getNumTakers() %></p>
       <p>Average score: <%= q.getAverageScore() %></p>
       <p>Average rating: <%= q.getAverageRating() %></p>
       <p>Number of users who find quiz inappropriate: <%= q.getNumTakersWhoDislikeQuiz() %></p>
       </div>
	   <hr/>
	   
	   <div id="usersWhoDislike" style ='background-color: #E5E4E2'>
	   <%
		if(user.isAdmin()) { 
		out.write("<h2>Offended Users</h2>");
 		out.write(User.getHTMLBullets(new ArrayList<User>(offendedUsers),-1));
 		} 
		 %>
	   </div>
	   <hr/>
       


</body>
</html>