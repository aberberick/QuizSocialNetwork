<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friend Requests</title>
<%@ page import="user.User"%>

<%@ page import="user.UserManager"%>
<%@ page import="message.Mailman"%>
<%@ page import="message.FriendRequest"%>
<%@ page import="system.SystemManager"%>
<%@ page import="quiz_system.QuizManager"%>
<%@ page import="quiz_system.Quiz"%>
<%@ page import="java.util.*"%>

<!--  Meta Data -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--  Bootstrap CSS -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">

<!--  Optional theme -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript  -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%@include file="refNavbar.jsp" %>
	<div class="jumbotron">
        <div class="container">
        <h1>Message Central</h1>
                <% SystemManager sm = (SystemManager)application.getAttribute("sm");
    User user = (User)session.getAttribute("signedInUser"); 
    Mailman mailMan=sm.getMailman();
    ArrayList<FriendRequest> friendRequests = mailMan.getFriendRequests(user);
    
    int numFriendRequests=friendRequests.size();
    int numChallengeRequests=mailMan.getChallengeRequests(user).size();
    int numTextMessages=mailMan.getTextMessages(user).size();
    %>


    <h2>Inbox Types</h2>
    <div class="btn-group">
      <button type="button" class="btn btn-default btn-lg"><a href="textMessages.jsp">Messages (<%=numTextMessages %>)</a></button>
      <button type="button" class="btn btn-default btn-lg active"><a href="friendRequests.jsp">Friend Requests (<%=numFriendRequests%>)</a></button>
      <button type="button" class="btn btn-default btn-lg"><a href="quizChallenges.jsp">Challenges (<%=numChallengeRequests %>)</a></button> 
    </div>
    <h3>Friend Requests</h3>
    <p>
<% 
ArrayList<FriendRequest> friends = mailMan.getFriendRequests(user); 
if(friends.size()>0) {%>

<%	for(FriendRequest req: friends) {
			%> <form action="FriendRequestResponseServlet" method="post">

Request From: <%=req.sender.getUserName() %>
<%if(!req.textMessage.equals("null")) { %>

<p>Message: <%=req.textMessage %></p>
<%} %>
<input	type="radio" name="response" value="accept"> Accept 
<input type="radio" name="response" value="deny"> Deny 
<input type="radio" name="response" value="ignore"> Ignore 
<input name="sender" type="hidden" value="<%=req.sender.getUserName() %>"/> 
	<input type="submit" class="btn btn-lg" value="Submit &raquo;">
	</form>
	</p>
	
<% 	} 
}
else { %>
	<p>Looks like you do not have any friend requests right now.</p>
<% }
	
	%>
	   <p><a href="createMessage.jsp" class="btn btn-lg btn-success">Send a Message &raquo;</a>
    <a href="createFriendRequest.jsp" class="btn btn-lg btn-primary">Send a Friend Request &raquo;</a>
    <a href="createQuizChallenge.jsp" class="btn btn-lg btn-warning">Make a Quiz Challenge &raquo;</a>
    </p>
	
	</div>
	</div>
	<%@include file="messageFooter.jsp" %>
	
</body>
</html>