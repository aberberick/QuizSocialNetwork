22<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Quiz</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
<%@include file="topContainer.jsp" %>

    <div class="jumbotron">
        <div class="container">
             <h1>
                <%= (String) session.getAttribute("quizName") %>
            </h1>
            
            <% 
            // get quizTaken, quizName, and Username.
            boolean quizTaken = (Boolean) session.getAttribute("quizTaken"); 
            boolean questionTaken = (Boolean) session.getAttribute("questionTaken");
            String quizName = (String) session.getAttribute("inProgressQuiz");
            String userName = (String) session.getAttribute("userName");
            if (quizTaken) {
                out.write("<p style=\"color: #aa0f0b\">Hey "
                        + userName
                        + ", it looks like the quiz name " 
                        + quizName 
                        + " is already taken. Click <a href=\"createQuiz0.jsp\">here</a> to try a different name.</p>");
            }
            else {
            	if (questionTaken) {
            		out.write("<p style=\"color: #aa0f0b\">Question name taken. Need to try again!</p>");
            	}
            	// PROMPT FOR QUESTIONS
            	out.write("<p>Second, <strong>select a question type</strong>.</p>");
            	out.write("<p><form action=\"CreateQuizServlet1\" method=\"post\">");
            	out.write("<select name=\"qType\">");
            	out.write("<option value=\"response\">Response</option>");
                out.write("<option value=\"fillintheblank\">Fill in the Blank</option>");
                out.write("<option value=\"multiplechoice\">Multiple Choice</option>");
                out.write("<option value=\"picture\">Picture Response</option>");
                out.write("<option value=\"multipleanswer\">Multiple Answer</option>");
                out.write("<option value=\"multiplechoicemultipleanswer\">Multiple Choice Multiple Answer</option>");
                out.write("<option value=\"matching\">Matching</option>");
            	out.write("</select><br />");
            				
            	// PROMPT FOR NUMBER OF ANSWERS
            	out.write("How many answers will your question have? Enter an integer.<br />");
            	out.write("<input type=\"number\" name=\"numAnswers\" value=\"1\"></input><br />");
                out.write("If you're selecting multiple choice, enter number of multiple choice options. Enter an integer.<br />");
                out.write("<input type=\"number\" name=\"numChoices\" value=\"1\"></input><br />");
           
            //    out.write("DELETE ME If you're selecting multiple answer, enter number of responses user can make. Enter an integer.<br />");
            //    out.write("<input type=\"number\" name=\"numResponses\" value=\"1\"></input><br />");
            	out.write("If you want the question to be timed, enter time in milliseconds.<br />");
                out.write("<input type=\"number\" name=\"time\" value=\"0\"></input><br />");
                // TODO pass this time along, have a check for if == 0, no time. if > 0, set time. 
                		// setTimeToTake... if its 0, no countdown, if it isn't 0, countdown.
                out.write("<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Submit &raquo;\">");
            	out.write("</form>");
            }
            %>
        </div>
    </div>
    
    <%if (!quizTaken) { %>
	    <div class="container" width="400px" style="text-align:left">
	    <h2><%= quizName %> (so far)</h2>
	   
	    <% 
	    // DISPLAY QUIZ SO FAR
	    // check quiz name
	    // get context, get session
	    
	    SystemManager sm = (SystemManager)application.getAttribute("sm");
	    QuizManager quizManager = sm.getQuizManager();
	    Quiz q = quizManager.getQuiz(quizName);
	            				
	 // Display Questions
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
	    <p><a href="quizSummary.jsp?quizName=<%=quizName%>" class="btn btn-lg btn-success">Complete Quiz</a></p>
    <% } %>    
    </div>
    
</body>
</html>