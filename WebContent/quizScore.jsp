<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>View Quiz Score</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
<%@include file="topContainer.jsp" %>
	<div class="jumbotron">
        <div class="container"> 
	<% 
	User signedInUser = (User)session.getAttribute("signedInUser"); 
	
	SystemManager sm= (SystemManager)application.getAttribute("sm");
	QuizManager qm = sm.getQuizManager();
	UserManager um =sm.getUserManager();
	String userName = request.getParameter("userName");
    String quizName = request.getParameter("quizName");
    User user = um.getExistingUser(userName);
    //added
    Quiz quiz =qm.getQuiz(quizName);
    HashSet <User> offendedUsers =quiz.getTakersWhoDislikeQuiz();
    
    boolean userWantsPractice = (Boolean) session.getAttribute("userWantsPractice");
    int numToPrint = -1;
    %>
    

    <h1>Quiz Score on <%= quizName %> for <%= userName %></h1>
    </div>
    </div>
    <hr/>
    
    <div id="currentquizscore" style ='background-color: #E5E4E2'>
    <h2>My Last Quiz Score</h2>
        <%
    if (userWantsPractice) {
        // get score stuff from sessoin
        int score = (Integer) session.getAttribute("score");
        long timeElapsed = (Long) session.getAttribute("timeElapsed");
        out.write("<p>NOTE: Quiz taken in practice mode</p>");
        out.write("<p>Score: " + score);
        out.write("<p>Time elapsed (ms): " + timeElapsed);          
    }
    else {
        // get score stuff from quizScore object
        QuizScore qs = (QuizScore) session.getAttribute("quizScore");
        out.write("<p>NOTE: Quiz taken for score</p>");
        out.write("<p>Score: " + qs.getScore());
        out.write("<p>Time elapsed (ms): " + qs.getTimeToComplete());       
    }%>    
    
        <%if(!offendedUsers.contains(signedInUser)) { %>

		<form action="MarkOffensiveServlet" method="post">
		
		<input name="quizName" type="hidden" value="<%=quizName %>"/> 
		
		<input type="submit" class="btn btn-primary btn-lg"
		
		value="Mark as Offensive &raquo;">
		
		</form>
		
		<%} %>
    </div>
    <hr/>
        
    <div id="allscores">
    <h2>My Past Scores on Quiz: <%=quizName%></h2>
    <%
    Quiz q = qm.getQuiz(quizName);
    ArrayList<QuizScore> allScores = q.getScores(user);
    out.write(QuizScore.getHTMLTable(allScores,numToPrint));
    %>
    
    </div>
    <hr/>
    
    <div id="correctanswers" style ='background-color: #E5E4E2'>
    <h2>All Questions and Answers to Quiz: <%=quizName %></h2>
    <% 
    ArrayList<Question> questions = q.getAllQuestions();
    if (questions.size() == 0) {
        out.write("<p>No questions in " + quizName + " at this time.</p>");
    }
         
     for (int i = 0; i < questions.size(); i++) {
         Question que = questions.get(i);
         QuestionType qt = que.getQuestionType();
         out.write("<h3>Question #" + i + " [" + qt + "] </h3>");
         out.write("<p>Name: " + que.getName() + "</p>");
         out.write("<p>Prompt: " + que.getPrompt() + "</p>");
         
         // Response Type
         if (qt.equals(QuestionType.RESPONSE)) { 
           ResponseQ rq = (ResponseQ) que;
           out.write("Solutions: ");
           HashSet<String> solns = rq.getSolutions();
           for (String soln : solns) {
               out.write("<p>" + soln + "</p>");
           }
         }    
         // Fill in the blank
         else if (qt.equals(QuestionType.FILL_IN_THE_BLANK)) {
             FillInBlankQ rq = (FillInBlankQ) que;
             out.write("Solutions: ");
             HashSet<String> solns = rq.getSolutions();
             for (String soln : solns) {
                 out.write("<p>" + soln + "</p>");
             }
         }
         // Multiple choice
           else if (qt.equals(QuestionType.MULTIPLE_CHOICE)) {
                 MultipleChoiceQ rq = (MultipleChoiceQ) que;
                 out.write("Choices: ");
                 HashSet<String> choices = rq.getChoices();
                 for (String choice : choices) {
                     out.write("<p>" + choice + "</p>");
                 }
                 out.write("<p>Solution:" + "</p>");
                 out.write("<p>" + rq.getSolution() + "</p>");
             }
           else if (qt.equals(QuestionType.PICTURE_RESPONSE)) {
               PictureResponseQ rq = (PictureResponseQ) que;
               out.write("<img src=\"" + rq.getPicture() + "\" class=\"img-thumbnail\" width=\"200px\"alt=\"" + rq.getName() + "\">");
               out.write("<br />Solutions:" + "<br />");
               HashSet<String> solns = rq.getSolutions();
                 for (String soln : solns) {
                     out.write("<p>" + soln + "</p>");
                 }
           }
           else if (qt.equals(QuestionType.MULTIPLE_ANSWER)) {
                MultipleAnswerQ rq = (MultipleAnswerQ) que;
                out.write("<p>Order matters: " + rq.isOrdered() + "</p>");
                out.write("Solutions: ");
                 HashSet<String> solns = rq.getSolutions();
                 for (String soln : solns) {
                    out.write("<p>" + soln + "</p>");
                 }
           }
           else if (qt.equals(QuestionType.MULTIPLE_CHOICE_ANSWER)) { //multi choice multi answer
               MultipleChoiceAnswerQ rq = (MultipleChoiceAnswerQ) que;
               out.write("Choices: ");
               HashSet<String> choices = rq.getChoices();
               for (String choice : choices) {
                   out.write("<p>" + choice + "</p>");
               }
               out.write("Solutions: ");
                HashSet<String> solns = rq.getSolutions();
                for (String soln : solns) {
                   out.write("<p>" + soln + "</p>");
                }
           }
           else if (qt.equals(QuestionType.MATCHING)) { //multi choice multi answer
               MatchingQ rq = (MatchingQ) que;
               HashMap<String, String> matches = (HashMap<String,String>)rq.getMatches(1);
               for (String key : matches.keySet()) {
                   out.write("<p>" + key + " : " + matches.get(key) + "</p>");
               }
           }
     }
    %>
    </div>
    <hr/>
    
</div>
</div>

</body>
</html>