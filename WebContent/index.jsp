<!DOCTYPE html>
<html lang="en-US">

<head>
<title>TST Quiz Website</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="refNavbarLite.jsp" %>

    <div style="float:right;margin-top:80px;margin-right:170px;">
                <img src="http://www.animal-photos.org/_photo/3652471.jpg" class="img-thumbnail" width="360px"alt="majestic snow tiger">
    </div>
    <div class="container" style="margin-top:60px">
    
	<div class="jumbotron">
		<div class="container">
			<h1>
				Welcome to the TST<br />Quiz Growl!
			</h1>
			<p>
				We at <strong>Team Snow Tiger</strong> have put together a sick quiz website for your<br />education
				and recreation. We guarantee you'll like it.<br/><br/> Login or sign up now!
			</p>
			<p>
			<form action="LoginServlet" method="post">
			User Name:<br />
			<input type="text" name="userName">
			<br />
			Password:<br />
			<input type="password" name="password">
			<input type="submit" class="btn btn-primary btn-lg" value="Login &raquo;">
			</form><br />
			Don't have an account? <a href="createAccount.jsp">Create account here.</a>
			</p>
		</div>
	</div>
	
</body>
</html>