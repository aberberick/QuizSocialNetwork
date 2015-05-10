<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>All Users</title>
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
	Search for a User 
	<form action="UserSearchServlet" method="post">
	<input type="text" name="name">
	<input type="submit" class="btn btn-primary btn-lg"
	value="Search &raquo;">
	</form>


	
    <h1>All Users [<%= users.size() %>]</h1>
	<ul>
	<%
	for(String name: users) {
		User friend =um.getExistingUser(name);

			%><li><a href="UserServlet?name=<%= name %>">
			<%= name %></a>
			
			<%if((!friendList.contains(friend)) &&  (!user.equals(friend))) { %>

<form action="CreateFriendRequestServlet" method="post">
<input name="friendUserName" type="hidden" value="<%=name %>"/> 
<input type="Submit" value= "Add as a Friend" />

</form>

<%} %>

	<%	
		
	}

%>
</ul>

</div>
</div>
</body>
</html>