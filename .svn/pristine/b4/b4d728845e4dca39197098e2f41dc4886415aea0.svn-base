<!DOCTYPE html>
<html lang="en-US">

<head>
<title>TST Quiz Website</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>

<%@include file="refNavbarLite.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1>Login Page</h1>
		 	<%
				boolean loginSuccess = (Boolean) session.getAttribute("loginSuccess"); 
		 	    boolean validUser = (Boolean) session.getAttribute("validUser");
				String userName = (String)session.getAttribute("userName");
								
 				if (!validUser) {
                    out.write("<p>Hey "
                            + userName
                            + ", it looks like we don't have your name in our system. Try creating an account.</p>");
                    out.write("<a href=\"createAccount.jsp\">Create account here.</a>");
 				}
 				else {
                    out.write("<p>Hey "
                            + userName
                            + ", it looks like you entered the wrong password. Let's try again.</p>");
 				    out.write("<p><form action=\"LoginServlet\" method=\"post\">"
 				           + "User Name:<br />" 
 				           + "<input type=\"text\" name=\"userName\">"
 				           + "<br />Password:<br />" 
 				           + "<input type=\"password\" name=\"password\">"
 				           + "<br /><br />"
 				           + "<input type=\"submit\" class=\"btn btn-primary btn-lg\" value=\"Login &raquo;\">"
 				           + "</form></p>");
 				}
 			%> 
		</div>
	</div>
</body>
</html>