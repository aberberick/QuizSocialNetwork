<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Search Tag Results</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>

   <div class="jumbotron">
       <div class="container">
<% String tag=request.getParameter("tag");
SystemManager sm= (SystemManager)application.getAttribute("sm");
QuizManager qm = sm.getQuizManager();
ArrayList<Quiz> allQuizzes =qm.getAllQuizzes();
ArrayList<Quiz> selectedQuizzes = new ArrayList<Quiz>();
for(int i=0; i<allQuizzes.size(); i++) {
	Quiz quiz =allQuizzes.get(i);
	HashSet<String> tags =quiz.getTags();
	for(String oneTag: tags) {
		if(tag.equals(oneTag) && !selectedQuizzes.contains(quiz)) {
			selectedQuizzes.add(quiz);
		}
	}
	
		
		
}


%>	
<br>
<p> Quiz Results (<%=selectedQuizzes.size() %>) for Tag: <%=tag %> 
<br><a href="quizSearch.jsp"> Return to Search</a>
</p>
<ul>

<%for(int i=0; i<selectedQuizzes.size(); i++) { 
	String quizName=selectedQuizzes.get(i).getName();
%>
<li><a href="quizSummary.jsp?quizName=<%=quizName%>"><%=quizName %></a>


<%} %>
</ul>


        </div>
        </div>
</body>
</html>

