<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="user.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="message.*"%>
    <%@ page import="system.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Message!</title>
<%@include file="refBootstrap.jsp" %>
</head>
<body>
<%@include file="refNavbar.jsp" %>
	<div class="jumbotron">
        <div class="container">
<h1> Create a Message</h1>

<% User user = (User)session.getAttribute("signedInUser"); 
SystemManager sm= (SystemManager)application.getAttribute("sm");
	UserManager um =sm.getUserManager();
	//ArrayList<User> friendList=user.getFriendList();
	HashSet<String> allUserNames =um.getAllUsers();
	
%>
<form action="CreateMessageServlet" method="post">
<select name="receiverName">

<% for(String friendUserName: allUserNames) {
	if(!friendUserName.equals(user.getUserName())) {%>
		<option value= "<%= friendUserName %>"> <%=friendUserName %> </option><%} 
%>
<% 	}


%>
</select>


<textarea rows="5" cols="20" name = "message" >
</textarea>
<center><input type="Submit" value="Send Message" /></center>
</form>

</div>
</div>
<%@include file="messageFooter.jsp" %>


</body>
</html>