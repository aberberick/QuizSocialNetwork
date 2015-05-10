<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Challenges</title>
<%@include file="refBootstrap.jsp" %>
<%@include file="imports.jsp" %>
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
      <button type="button" class="btn btn-default btn-lg"><a href="friendRequests.jsp">Friend Requests (<%=numFriendRequests%>)</a></button>
      <button type="button" class="btn btn-default btn-lg active"><a href="quizChallenges.jsp">Challenges (<%=numChallengeRequests %>)</a></button> 
    </div>
   <h3>Quiz Challenges</h3>
    
        
<% 
ArrayList<FriendRequest> friends = mailMan.getFriendRequests(user); 
ArrayList<ChallengeRequest> challenges=mailMan.getChallengeRequests(user);



if(challenges.size()>0) {%>
Challenges 
<ul>
	
<% for(int i=0; i<challenges.size(); i++) { 
		ChallengeRequest challenge = challenges.get(i);
		String quizName=challenge.quiz.getName();
		String challenger=challenge.sender.getUserName();
		String date = challenge.getDate();
		
		
		%>
		
				
			<li> <%=challenger %> has Challenged you to take <a href="quizSummary.jsp?quizName=<%= quizName %>"> <%= quizName %></a> 
	on <%=date %>. <%=challenger %> has scored <%=challenge.highScore.toString()%>
		<form action="DeleteMessageServlet" method="post">
		<input type="submit" class="btn btn-primary btn-lg" value="Delete &raquo;">
		<input name="message" type="hidden" value="<%=challenge.idCount %>"/> 
		</form>
		
		
		

	<%} 
%>


</ul>
<% 	

}
else { %>
	<p>I'm sorry. Looks like you do not have any Quiz Challenges right now.</p>
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