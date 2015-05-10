<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Friends</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
	<div class="jumbotron">
        <div class="container">
        <br>
<% User user = (User)session.getAttribute("signedInUser"); 
 String userName = (String)session.getAttribute("signedInUserName"); 
	SystemManager sm= (SystemManager)application.getAttribute("sm");
	Mailman mailMan = sm.getMailman();
	UserManager um = sm.getUserManager();
	HashSet<String> users = um.getAllUsers();
	ArrayList<User> friendList = user.getFriendList();
	%>

Friends
<ul>
<%for(int i=0; i<friendList.size(); i++) {
	String name=friendList.get(i).getUserName();%>
	<li><a href="UserServlet?name=<%= name %>"><%= name %></a>
	<form action="RemoveFriendServlet" method="post">
	<input name="friendUserName" type="hidden" value="<%=name %>"/> 
	<input type="Submit" value= "Remove Friend" />
	</form>
<% }%>

</ul>


</div>
</div>
</body>
</html>