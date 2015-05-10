<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="user.*"%>
	<%@ page import="system.*"%>
	<%@ page import="message.*"%>
	<%@ page import="quiz_system.*"%>
    <%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Create a Quiz Challenge</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
<div class="container" style="margin-top:60px">
    <div class="jumbotron">
        <div class="container">
        <h1>Create Quiz Challenge</h1>

<% SystemManager sm= (SystemManager)application.getAttribute("sm");
QuizManager qm = sm.getQuizManager(); 
ArrayList<Quiz> allQuizzes = qm.getAllQuizzes();
User user = (User)session.getAttribute("signedInUser"); 
ArrayList<User> friendList=user.getFriendList();
if(friendList.size()==0) {
	%><p>I'm sorry. It looks like you do not have any friends right now.</p><p>
    <a href="createFriendRequest.jsp" class="btn btn-lg btn-primary">Send a Friend Request &raquo;</a>
    </p>
	<% 
}


else if(allQuizzes.size()>0) {
%>


Friend's User Name:
<form action="CreateQuizChallengeServlet" method="post">
<select name="receiverName">


<% for(int i=0; i<friendList.size(); i++) { 
		String friendUserName=friendList.get(i).getUserName();%>
		<option value= "<%= friendUserName %>"> <%=friendUserName %> </option><%} 
%>
</select>

 Message <input type="text" name="text"> <br />
 Quiz <select name="quizName">
<% for(int i=0; i<allQuizzes.size(); i++) { 
		String quizName=allQuizzes.get(i).getName();%>
		
		<option value= "<%= quizName %>"> <%=quizName %> </option>
		<%} 
%>
</select>
 

<br />
<input type="submit" class="btn btn-primary btn-lg"
	value="Send &raquo;"></form>
	<% } else {
		
		%> I'm sorry, there are no quizzes at this time.
		  <p><a href="createMessage.jsp" class="btn btn-lg btn-success">Send a Message &raquo;</a>

    <a href="createQuiz0.jsp" class="btn btn-lg">Create a Quiz &raquo;</a>
    </p>
				
		
		
		
<% 	}
		%>
	
</div>
</div>

<%@include file="messageFooter.jsp" %>

</body>
</html>