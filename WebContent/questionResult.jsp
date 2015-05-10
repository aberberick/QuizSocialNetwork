<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question Result</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
<%@include file="refNavbar.jsp" %>


   <div class="jumbotron">
       <div class="container">
       
       
       
            
               
  
  <% User user = (User)session.getAttribute("signedInUser");
  String quizName = request.getParameter("quizName");
    SystemManager sm = (SystemManager)application.getAttribute("sm");
    QuizManager quizManager = sm.getQuizManager();
    Quiz q = quizManager.getQuiz(quizName);
    int currentScore =(Integer)session.getAttribute("score");
    int qIndex =(Integer)session.getAttribute("qIndex") -1;
    String isCorrect=(String)session.getAttribute("isCorrect");
    
    %>
    
    <h1>Quiz: <%= request.getParameter("quizName")%></h1>
    
    Score so far: <%=currentScore %>
    <%if(isCorrect.equals("true")) { %>
    	Question <%=qIndex %>: Correct
    
    <%} else {%>
    	Question <%=qIndex %>: Incorrect
    
    <%} %>
    
    <a href="show-quiz.jsp?quizName=<%= q.getName() %>">Return to Quiz</a>
    

    </div>
    </div>

</body>
</html>