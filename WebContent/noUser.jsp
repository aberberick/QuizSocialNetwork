<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>No Such User</title>
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
        <br>
<p> <%String name=request.getParameter("name"); %> 
I'm sorry <%=name %> does not have an account.
<a href="home.jsp" class="btn btn-lg btn-success">Return home &raquo;</a>
<a href="allUsers.jsp" class="btn btn-lg btn-success">Return to All Users Page &raquo;</a>

</p>

</div>
</div>

</body>
</html>