<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="user.User"%>
    <%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create an Announcement</title>
<%@include file="refBootstrap.jsp" %>
</head>
<body>
<%@include file="refNavbar.jsp" %>
	<div class="jumbotron">
        <div class="container">
        
 <% User user = (User)session.getAttribute("signedInUser"); 
	ArrayList<User> friendList=user.getFriendList();
	if(!user.isAdmin()) {%>
	<p> I'm sorry. Looks like you are not an administrator, so you cannot create an announcement.
	<a href="createMessage.jsp"> Create a Private Message</a>
	<a href="home.jsp"> Return home</a>
		</p>	
<% 	} else {
%>  
        
        
Create an Announcement to be sent to all users.


<form action="CreateAnnouncementServlet" method="post">
<textarea rows="5" cols="20" name = "message" >
</textarea>
<center><input type="Submit" value="Send Announcement" /></center>
</form>
<%} %>
</div>
</div>

</body>
</html>