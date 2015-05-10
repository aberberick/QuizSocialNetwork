<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@ page import="user.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="message.*"%>
<%@ page import="system.SystemManager"%>
<%@ page import="quiz_system.QuizManager"%>
<%@ page import="quiz_system.Quiz"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Send a Friend Request</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
    <div class="jumbotron">
        <div class="container">
        <h1>Create Friend Request</h1>
<%User user = (User)session.getAttribute("signedInUser"); 
ArrayList<User> friendList=user.getFriendList();
SystemManager sm= (SystemManager)application.getAttribute("sm");
Mailman mailMan = sm.getMailman();
UserManager um = sm.getUserManager();
HashSet<String> users = um.getAllUsers();
HashSet<String> dontRequestNames=new HashSet<String>();
HashSet<String> potentialFriends=new HashSet<String>();

//make nameFriendList
for(int i=0; i<friendList.size(); i++) {
	dontRequestNames.add(friendList.get(i).getUserName());
}
dontRequestNames.add(user.getUserName());
for(String potentialFriend: users) {
	if(!(dontRequestNames.contains(potentialFriend))) {
		potentialFriends.add(potentialFriend);
	}
}

%>



<form action="CreateFriendRequestServlet" method="post">



Send a Friend Request <br />

<select name="friendUserName">

<% for(String friendUserName: potentialFriends) {%>
	<option value= "<%= friendUserName %>"> <%=friendUserName %> </option><%
}
%>

</select>
Message

<input type="text" name="text"> <br /> 

<br />
<input type="submit" class="btn btn-primary btn-lg"
	value="Send &raquo;"></form>
</div>
</div>
<%@include file="messageFooter.jsp" %>

</body>
</html>