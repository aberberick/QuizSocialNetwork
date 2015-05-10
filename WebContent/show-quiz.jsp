<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz: <%= request.getParameter("quizName")%></title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
    <div class="container" style="margin-top:60px">
   <div class="jumbotron">
       <div class="container">
            <h1>Quiz: <%= request.getParameter("quizName")%></h1>
  
  <% 
  
  //TODO get the multipage rocking
  //TODO get the immediate correction rocking
  //TODO fix multiple answer ordered quiz grading
  //TODO put a practice mode option (on one page this is easy...on multi page just put it on
		  // the first one I guess
  
    // Get Quiz
    String quizName = request.getParameter("quizName");
    SystemManager sm = (SystemManager)application.getAttribute("sm");
    QuizManager quizManager = sm.getQuizManager();
    Quiz q = quizManager.getQuiz(quizName);
    
    long timeStart = System.currentTimeMillis();
    session.setAttribute("timeStart", timeStart);
    
    boolean multiPage = !q.isOnePage();
    boolean practiceEnabled = q.isPracticeEnabled();
    boolean randomized = q.isRandomized();
    boolean timed = q.isTimed();
    boolean immediateFeedbackEnabled = q.isCorrectedImmediatley();
    
    // Display category
    %>
    <p>Category: <%= q.getCategory() %><br />
    <%
    
    // Display Tags
    out.write("Tags: ");
    HashSet<String> tags = q.getTags();
    for (String tag : tags) {
    	out.write(tag + " ");
    }
    out.write("<br />");
    
    // display description
    out.write("Description: " + q.getDescription() + "</p>");
    
    // display date created
    out.write("<p><small>");
    Date d = new Date(q.getDateCreated());
    SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    String dText = df.format(d);
    out.write("Date created: " + dText + "</small></p>");
    
    // display rating
    double rating = q.getAverageRating();
    if (rating == Quiz.DEFAULT_RATING) {
    	out.write("<p>Average rating: quiz not yet rated</p>");
    }
    else {
        out.write("<p>Average rating: " + rating + "</p>");
    }
    
    // display options
    out.write("<p> Multi page: " + multiPage + "; " );
    out.write("Practice mode: " + practiceEnabled + "; " );
    out.write("Random order: " + randomized + "; " );
    out.write("Timed: " + timed + "<br/>");
    if (timed) {
    	out.write("<p> Time to take quiz (seconds):</p>");
    	out.write("<p id=\"quiztime\">" + q.getTimeToTake()/1000 + "</p>");
    }
    out.write("Immediate Feedback: " + immediateFeedbackEnabled + "<br/>");
    
    %>
    </div>
    </div>
<% if (!multiPage) {
	
	%>    
	    <div class="container" width="400px" style="text-align:left;font-size:20px;">
	    <h2>Questions</h2>
	<% 
	//Display Questions
	ArrayList<Question> questions = q.getAllQuestions();
	
	// randomized
	if (randomized) {
		long seed = System.nanoTime();
		Collections.shuffle(questions, new Random(seed));
		session.setAttribute("randomizedArray", questions); 		
	}
	                        
	if (questions.size() == 0) {
	    out.write("<p>No questions in " + quizName + " at this time.</p>");
	}
	
	out.write("<p><form action=\"QuizEvalServlet\" method=\"post\" id=\"jsform\">");
	out.write("<input type=\"hidden\" name=\"quizName\" value=\"" + quizName + "\">");
    if (practiceEnabled) {
        out.write("<input type=\"checkbox\" name=\"userWantsPractice\" value=\"userWantsPractice\">Take for Practice<br>");

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
	    	   out.write("Enter your guess here: <input name=\"guess" + i + "0\"></input><br />");  
	     }    
	     // Fill in the blank
	     else if (qt.equals(QuestionType.FILL_IN_THE_BLANK)) {
	         FillInBlankQ rq = (FillInBlankQ) que;
	         // one guess
	         out.write("Enter your guess here: <input name=\"guess" + i + "0\"></input><br />");  
	     }
	     // Multiple choice
	       else if (qt.equals(QuestionType.MULTIPLE_CHOICE)) {
	             MultipleChoiceQ rq = (MultipleChoiceQ) que;
	             out.write("Select: <br />");             
	             HashSet<String> choices = rq.getChoices();
	             // one guess
	             for (String choice : choices) {
	            	 out.write("<input type=\"radio\" name=\"guess" + i + "0\" value=\"" + choice + "\">" + choice + "<br/>");
	             }
	         }
	     // Picture response
	       else if (qt.equals(QuestionType.PICTURE_RESPONSE)) {
	           PictureResponseQ rq = (PictureResponseQ) que;
	           out.write("<img src=\"" + rq.getPicture() + "\" class=\"img-thumbnail\" width=\"200px\"alt=\"" + rq.getName() + "\"><br />");
	           out.write("Enter your guess here: <input name=\"guess" + i + "0\"></input><br />");  
	       }
	       else if (qt.equals(QuestionType.MULTIPLE_ANSWER)) {
	            MultipleAnswerQ rq = (MultipleAnswerQ) que;
	            HashSet<String> solns = rq.getSolutions();
	            int numSolns = solns.size();
	            for (int j = 0; j < numSolns; j++) {
	                out.write("Enter your guess #" + j + " here: <input name=\"guess" + i + j + "\"></input><br />");  
	            }
	       }
	       else if (qt.equals(QuestionType.MULTIPLE_CHOICE_ANSWER)) { //multi choice multi answer
	           MultipleChoiceAnswerQ rq = (MultipleChoiceAnswerQ) que;
	           out.write("Choices: <br/>");
	           HashSet<String> choices = rq.getChoices();
	           int j = 0;
	           for (String choice : choices) {
	               out.write("<input type=\"checkbox\" name=\"guess" + i + j + "\" value=\"" + choice + "\">" + choice + "<br/>");
	               j++;
	           }
	       }
	       else if (qt.equals(QuestionType.MATCHING)) { //multi choice multi answer
	           MatchingQ rq = (MatchingQ) que;
               HashMap<String, String> matches = (HashMap<String,String>)rq.getMatches(1);
               int j = 0;
               for (String key : matches.keySet()) {
            	   out.write("Match #" + j + ":<br /> " + key + " &raquo; ");
	            	out.write("<select name=\"guess" + i + j + "\">");
	            	for (String key2 : matches.keySet() ) {
	                    out.write("<option value=\"" + matches.get(key2) + "\">" + matches.get(key2) + "</option>");
	            	}
	            	out.write("</select><br />");
            	   j++;
               }
	       }
	 }
	 // rating selection
	    out.write("<h3>Select your rating!</h3><p><select name=\"qrating\">");
	    for (double i = Quiz.MIN_RATING; i <= Quiz.MAX_RATING; i++) {
	    	out.write("<option value=\"" + i + "\">"+i+"</option>");
	    }
	    out.write("</p>");
	 out.write("<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Submit &raquo;\">");
	 out.write("</form>");
	     %>
	     
	     <!-- TIMED JAVASCRIPT  -->
	     <% if (timed) { %>
		    <script type="text/javascript">
		    window.onload=countdown();
		    
		    function submitAnswers() { // submits answers once time expires
		        document.getElementById("jsform").submit();
		    }
		    
		    function countdown()
		    {
		        if (document.getElementById("jsform")) {
		        	var quiztime = document.getElementById("quiztime").innerHTML;
		        	var seconds = parseInt(quiztime);
		        	var ms = seconds * 1000;
		        	alert("You have " + seconds + " seconds to complete this quiz.\n"
		        			+ "If you aren't done by then, your answers so far will be submitted.\nGood luck!");
		            setTimeout("submitAnswers()", ms); // set timeout to quiz seconds 
		       }
		    }
		    </script>

       <% } //end if (timed) %>    
	     
 <% } // end of if (!multiPage) 
 else { // MULTI PAGE
	 /* SETUP REFERENCE */
	 /*
	    session.setAttribute("qIndex", 0);
        session.setAttribute("currentQuiz", currentQuiz);
        session.setAttribute("score", 0);
        session.setAttribute("timeStart", 0);
        session.setAttribute("timeEnd", 0);
      */
      // get questions
      ArrayList<Question> questions = q.getAllQuestions();
      if (q.isRandomized()) {
          questions = (ArrayList<Question>) session.getAttribute("randomizedArray"); 
      }
      
      int maxIndex = questions.size()-1;
      // current question index to display.
      int qIndex = (Integer) session.getAttribute("qIndex");
      
      // display the individual code for questions.
      Question que = questions.get(qIndex);
      QuestionType qt = que.getQuestionType();
      
      out.write("<h3>Question #" + qIndex + " [" + qt + "] </h3>");
      out.write("<p>Name: " + que.getName() + "</p>");
      out.write("<p>Prompt: " + que.getPrompt() + "</p>");
      
      // TODO put this in front...based on index
      if (maxIndex == qIndex) {
          out.write("<p><form action=\"QuizEvalServlet\" method=\"post\" id=\"jsform\">");
          out.write("<input type=\"hidden\" name=\"quizName\" value=\"" + quizName + "\">");
      }
      else {
          out.write("<p><form action=\"MultiEvalServlet\" method=\"post\" id=\"jsform\">");
          out.write("<input type=\"hidden\" name=\"quizName\" value=\"" + quizName + "\">");
          // redirect to multi eval servlet to compute score so far and add to session
          // this servlet should compute a score, add update session attribute for score.
          // then it should update qIndex, then return back here.
      }
      if (qIndex == 0) {
          if (practiceEnabled) {
              out.write("<input type=\"checkbox\" name=\"userWantsPractice\" value=\"userWantsPractice\">Take for Practice<br>");
          }
      }

      
      
      // Response Type
      if (qt.equals(QuestionType.RESPONSE)) { 
        ResponseQ rq = (ResponseQ) que;
        out.write("Enter your guess here: <input name=\"guess" + qIndex + "0\"></input><br />");  
      }    
      // Fill in the blank
      else if (qt.equals(QuestionType.FILL_IN_THE_BLANK)) {
          FillInBlankQ rq = (FillInBlankQ) que;
          // one guess
          out.write("Enter your guess here: <input name=\"guess" + qIndex + "0\"></input><br />");  
      }
      // Multiple choice
        else if (qt.equals(QuestionType.MULTIPLE_CHOICE)) {
              MultipleChoiceQ rq = (MultipleChoiceQ) que;
              out.write("Select: <br />");             
              HashSet<String> choices = rq.getChoices();
              // one guess
              for (String choice : choices) {
                  out.write("<input type=\"radio\" name=\"guess" + qIndex + "0\" value=\"" + choice + "\">" + choice + "<br/>");
              }
          }
      // Picture response
        else if (qt.equals(QuestionType.PICTURE_RESPONSE)) {
            PictureResponseQ rq = (PictureResponseQ) que;
            out.write("<img src=\"" + rq.getPicture() + "\" class=\"img-thumbnail\" width=\"200px\"alt=\"" + rq.getName() + "\"><br />");
            out.write("Enter your guess here: <input name=\"guess" + qIndex + "0\"></input><br />");  
        }
        else if (qt.equals(QuestionType.MULTIPLE_ANSWER)) {
             MultipleAnswerQ rq = (MultipleAnswerQ) que;
             HashSet<String> solns = rq.getSolutions();
             int numSolns = solns.size();
             for (int j = 0; j < numSolns; j++) {
                 out.write("Enter your guess #" + j + " here: <input name=\"guess" + qIndex + j + "\"></input><br />");  
             }
        }
        else if (qt.equals(QuestionType.MULTIPLE_CHOICE_ANSWER)) { //multi choice multi answer
            MultipleChoiceAnswerQ rq = (MultipleChoiceAnswerQ) que;
            out.write("Choices: <br/>");
            HashSet<String> choices = rq.getChoices();
            int j = 0;
            for (String choice : choices) {
                out.write("<input type=\"checkbox\" name=\"guess" + qIndex + j + "\" value=\"" + choice + "\">" + choice + "<br/>");
                j++;
            }
        }
        else if (qt.equals(QuestionType.MATCHING)) { //multi choice multi answer
            MatchingQ rq = (MatchingQ) que;
            HashMap<String, String> matches = (HashMap<String,String>)rq.getMatches(1);
            int j = 0;
            for (String key : matches.keySet()) {
                out.write("Match #" + j + ":<br /> " + key + " &raquo; ");
                 out.write("<select name=\"guess" + qIndex + j + "\">");
                 for (String key2 : matches.keySet() ) {
                     out.write("<option value=\"" + matches.get(key2) + "\">" + matches.get(key2) + "</option>");
                 }
                 out.write("</select><br />");
                j++;
            }
        }

      
      if (qIndex == maxIndex) {
    	  // rating selection
    	     out.write("<h3>Select your rating!</h3><p><select name=\"qrating\">");
    	     for (double i = Quiz.MIN_RATING; i <= Quiz.MAX_RATING; i++) {
    	         out.write("<option value=\"" + i + "\">"+i+"</option>");
    	     }
    	     out.write("</p>");
  }
      out.write("<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Submit &raquo;\">");
      out.write("</form>");     
      
      session.setAttribute("timeStart", System.currentTimeMillis());
 %>
<%
 }
 %>
	     
     </div>
</body>
</html>