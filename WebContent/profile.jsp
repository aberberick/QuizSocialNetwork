<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Profile Page</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
  <div class="container" style="margin-top:60px">
	<div class="jumbotron">
        <div class="container">
<% 
	User signedInUser = (User)session.getAttribute("signedInUser"); 
	SystemManager sm= (SystemManager)application.getAttribute("sm");
	QuizManager qm = sm.getQuizManager();
	UserManager um =sm.getUserManager();
	
    // DISPLAY User
	String name=request.getParameter("name");
	User user = um.getExistingUser(name);
	ArrayList<Quiz> quizzesTaken = user.getTakenQuizes();
	ArrayList<Quiz> quizzesCreated = user.getCreatedQuizes();
	ArrayList<Quiz> quizzesMade = user.getCreatedQuizes();
	ArrayList<User> friendList = user.getFriendList();
    if (um.isUserNameAvailable(name)) {
        // write that the user does not exist
        out.write("User " + name + " does not exist");
    }
    else {
        out.write("<h1>" + name + "'s Profile</h1>");
    }
    if (user.isAdmin()) { 
    
    	out.write("<p>Administrator</p>");
	}
	else {  
		out.write("<p>Standard User</p>");
	}
    %>
    
    <%if((!user.isPrivate() || friendList.contains(signedInUser)) ||  user.equals(signedInUser)) { %>
    
    </div>
    </div>
    <div id="achievementsdiv" style ='background-color: #E5E4E2'>
    <h2>Achievements </h2>
    <% 
        ArrayList<Achievement> achievements = user.getAchievements();
    for (Achievement a : achievements) {
        out.write("<p>" + a.title + " (" + a.description + ")</p>");
    }
    %>
    </div>
    <hr />

    <div id="friendsdiv">
    <h2>Friends</h2>
    <% 
    out.write(User.getHTMLBullets(friendList,-1));
    %>
    </div>
        <hr />
    

    <div id="quizzesdiv" style ='background-color: #E5E4E2'>
    <h2>Quizzes</h2>
    <h3>Quizzes Taken</h3>
    <%
    ArrayList<QuizScore> quizScores = new ArrayList<QuizScore>();
    for (Quiz q : qm.getQuizzesTakenByUser(user)) {
    	quizScores.addAll(q.getScores(user));
    }
    quizScores = QuizScore.orderByDateTaken(quizScores);
    out.write(QuizScore.getHTMLTable(quizScores,5));
    %>
    <h3>Quizzes Created</h3>
    <%
    ArrayList<Quiz> created = qm.getQuizzesCreatedByUser(user);
    out.write(Quiz.getHTMLTable(created,5));
    %>
        <h3><a class="btn btn-lg btn-primary" href="userHistory.jsp?userName=<%= user.getUserName() %>&takenQuiz=default&createdQuiz=default">View Full User History</a></a></h3>    
    </div>
        <hr />
    
    <%if(!signedInUser.equals(user)){ %>
    <div id="interactdiv">
    <h2>Interact</h2>
    <h3>Send Message</h3>
    <form action="CreateMessageServlet" method="post">
    <input type="hidden" name="receiverName" value="<%= user.getUserName() %>">
    <textarea rows="1" cols="20" name = "message" >
	</textarea>
	<input type="Submit" class="btn btn-lg btn-primary" value="Send Message" />
	</form>
    
    <%if(!friendList.contains(signedInUser)){ %>
    <h3>Add Friend</h3>
    <form action="CreateFriendRequestServlet" method="post">
    <input type="Submit" class="btn btn-lg btn-primary" value="Add as friend" />
    </form>
    
    </div>    <hr />
    <%}else{ %>
    <h3>Remove Friend</h3>
    <form action="RemoveFriendServlet" method="post">
	<input name="friendUserName" type="hidden" value="<%=name %>"/> 
	<input type="Submit" class="btn btn-lg btn-danger" value="Remove Friend" />
	</form>
    </div><hr/>
    <%} %>
    <%} %>
    
    <% if(signedInUser.isAdmin()) {%>
    <div id="admindiv" style ='background-color: #E5E4E2'>
    <h2>Administrator</h2>
        <form action="RemoveUserServlet" method="post">
    <input name="userName" type="hidden" value="<%= user.getUserName() %>"/> 
    <input type="Submit" class="btn btn-lg btn-danger" value= "Delete User" />

    </form>
    </div>    
    <hr />
    <%} %>
    <%} %>


</body>
</html>