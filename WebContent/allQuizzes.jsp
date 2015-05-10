<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Quizzes</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>

   <div class="jumbotron">
       <div class="container">
            <h1>All Quizzes</h1>
     <% 
    SystemManager sm = (SystemManager)application.getAttribute("sm");
    QuizManager quizManager = sm.getQuizManager();
    ArrayList<Quiz> quizzes = quizManager.getAllQuizzes();
    
    System.out.println(quizzes.size());
    out.write(Quiz.getHTMLTable(quizzes,-1));
    %>
    </div>
    </div>
</body>
</html>

