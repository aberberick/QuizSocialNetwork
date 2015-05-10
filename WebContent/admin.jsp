<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Admin Page</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>
  <div class="container" style="margin-top:60px">
    <div class="jumbotron">
        <div class="container">
        <h1>Administrator Controls</h1>
        </div></div>
        
        <% String userName = (String)session.getAttribute("signedInUserName"); 
        SystemManager sm= (SystemManager)application.getAttribute("sm");
        Mailman mailMan = sm.getMailman();
        UserManager um = sm.getUserManager();
        QuizManager qm = sm.getQuizManager();
        HashSet<String> users = um.getAllUsers();
        
        ArrayList<Quiz> quizzes = qm.getAllQuizzes();
        %> 
        
        
        <div id="userdiv" style ='background-color: #E5E4E2'>
        <h2>Users</h2>
        <h3>Delete a User</h3>
	    <form action="RemoveUserServlet" method="post">
	    <select name="userName">
		<%  for(String name: users) {%>
		    <option value= "<%= name %>"> <%=name %> </option><%} 
		%>
	   </select>
		<input type="Submit" class="btn btn-lg btn-primary" value= "Delete User" />
		
		</form>
	   
	   
	   <h3>Promote a User</h3>
	    <form action="CreateAdminServlet" method="post">
        <select name="name">
        <%  ArrayList<String> nonAdmins = um.getNonAdmins();
        	for(String name: nonAdmins) {%>
            <option value= "<%= name %>"> <%=name %> </option><%} 
        %>
       </select>
        <input type="Submit" class="btn btn-lg btn-primary" value= "Promote to Administrator" />
       </div>
       <hr />
       
        <div id="quizdiv">
        <h2>Quizzes</h2>
        <h3>Delete a Quiz</h3>
		<form action="RemoveQuizServlet" method="post">
		    <select name="quizName">
		<% for(int i=0; i<quizzes.size(); i++) { 
		        String quizName = quizzes.get(i).getName();%>
		        <option value= "<%= quizName %>"> <%=quizName %> </option><%} 
		%>
		</select>
		    <input type="Submit" class="btn btn-lg btn-primary" value= "Delete Quiz" />
		</form>
		        
		 <h3>Clear History</h3>
		      <form action="ClearQuizHistoryServlet" method="post">
            <select name="quizName">
	        <% for(int i=0; i<quizzes.size(); i++) { 
	                String quizName = quizzes.get(i).getName();%>
	                <option value= "<%= quizName %>"> <%=quizName %> </option><%} 
	        %>
	        </select>
            <input type="Submit" class="btn btn-lg btn-primary" value= "Clear Quiz Scores" />
        
            </form>
        </div>
        <hr />
        
        <div id="messagediv" style ='background-color: #E5E4E2'>
        <h2>Messages</h2>
            <form action="CreateAnnouncementServlet" method="post">
			<textarea  name="message" >
			</textarea> <br> 
			 <input type="Submit" class="btn btn-lg btn-primary" value="Send Announcement" />
			</form>
        </div>
        <hr />
       
        <div id="statsdiv">
        <h2>Site Statistics</h2>
        <p>Number of users on site: <%= um.getAllUsers().size() %></p>
        <p>Number of quizzes taken: <%= qm.getNumQuizzesTaken() %></p>
        <p>Number of quizzes created: <%= qm.getNumQuizzes() %></p>
        </div>
        <hr />


</body>
</html>