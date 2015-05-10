<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox</title>
<%@include file="refBootstrap.jsp" %>
<%@include file="imports.jsp" %>
</head>

<body>
<%@include file="refNavbar.jsp" %>
<div class="container" style="margin-top:60px">

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
      <button type="button" class="btn btn-default btn-lg"><a href="friendRequests.jsp">Friend Requests (<%=numFriendRequests%>)</a></button>
      <button type="button" class="btn btn-default btn-lg"><a href="quizChallenges.jsp">Challenges (<%=numChallengeRequests %>)</a></button>
    </div>
    <h3>No Inbox Selected</h3>
	<p>Any messages you have will be displayed here after you select a category above</p>
	<p><a href="createMessage.jsp" class="btn btn-lg btn-success">Send a Message &raquo;</a>
	<a href="createFriendRequest.jsp" class="btn btn-lg btn-primary">Send a Friend Request &raquo;</a>
    <a href="createQuizChallenge.jsp" class="btn btn-lg btn-warning">Make a Quiz Challenge &raquo;</a>
	</p>
	<%if(user.isAdmin()) {
		%>
		<h2> Administrator Features:</h2> <br>
		<a href="createAnnouncement.jsp" class="btn btn-lg">Make an Announcement &raquo;</a>		
	<%} %>
    </div>
</div>

<%@include file="messageFooter.jsp" %>

</body>
</html>