<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Profile Page</title>
<%@include file="refBootstrap.jsp"%>
</head>

<body>
<%@include file="imports.jsp"%>
<%@include file="refNavbar.jsp"%>
<div class="container" style="margin-top: 60px">
<div class="jumbotron">
<div class="container">
<% 
	User signedInUser = (User)session.getAttribute("signedInUser"); 
	SystemManager sm= (SystemManager)application.getAttribute("sm");
	QuizManager qm = sm.getQuizManager();
	UserManager um =sm.getUserManager();
	
    // DISPLAY User
	String quizName=request.getParameter("quizName");
	Quiz q = qm.getQuiz(quizName);
	System.out.println(q);
	ArrayList<Question> questions = q.getAllQuestions();		

%>

<h1>Edit Quiz: <%= q.getHTML()%></h1>
</div>
</div>

<div id="removeQuestions" style='background-color: #E5E4E2'>
<h2>Remove A Question</h2>
<form action="RemoveQuestionServlet" method="post"><input
	type="hidden" name="quizName" value="<%= quizName %>"></input> <select
	name="questionName">
	<% for(int i=0; i<questions.size(); i++) { 
		        String questionName = questions.get(i).getName();%>
	System.out.println(questionName);
	<option value="<%= questionName %>"><%=questionName %></option>
	<%} 
		%>
</select> <input type="Submit" class="btn btn-lg btn-danger"
	value="Delete Question" /></form>

</div>
<hr />
</body>
</html>