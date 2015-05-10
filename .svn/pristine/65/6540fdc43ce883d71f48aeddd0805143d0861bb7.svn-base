<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Quiz</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>

<%@include file="refNavbar.jsp" %>
<%@include file="topContainer.jsp" %>


    <div class="jumbotron">
        <div class="container">
            <h1>
                Create your own quiz!
            </h1>            
            <p>
            First, <strong>enter a quiz name and info</strong>.
            </p>
            <p>
            <form action="CreateQuizServlet0" method="post">
            Name: <input name="quizname"></input><br />
			Category:
                <select name="quizcategory">
                <option value="History">History</option>
                <option value="Math">Math</option>
                <option value="English">English</option>
                <option value="Literature">Literature</option>
                <option value="Geography">Geography</option>
                <option value="Religion">Religion</option>
                <option value="Technology">Technology</option>
                <option value="Engineering">Engineering</option>
                <option value="Computer Science">Computer Science</option>
                <option value="Art">Art</option>
                <option value="Language">Language</option>
                <option value="Biology">Biology</option>
                <option value="Chemistry">Chemistry</option>
                <option value="Physics">Physics</option>
                <option value="Sociology">Sociology</option>
                <option value="Psychology">Psychology</option>
                <option value="Food">Food</option>
                <option value="Economics">Economics</option>
                <option value="Ethics">Ethics</option>
                <option value="None">None</option>
                </select><br />
            Enter up to 3 tags:<br />
            <input name="tag0"></input><br />
            <input name="tag1"></input><br />
            <input name="tag2"></input><br />
			Select options:<br />
            <input type="checkbox" name="multiPage" value="multiPage">Display on multiple pages<br>
            <input type="checkbox" name="practiceEnabled" value="practiceEnabled">Allow practice<br>
            <input type="checkbox" name="randomized" value="randomized">Randomize order<br>
            <input type="checkbox" name="timed" value="timed">Timed Quiz<br>	
            Enter time in seconds: <input type="number" name="time" value="0"></input><br />
			<input type="checkbox" name="immediateFeedbackEnabled" value="immediateFeedbackEnabled">Immediate Feedback<br>         
			Enter description:<br/><input name="qdescription" size="70"></input><br />
			<p>
			
			<input type="submit" class="btn btn-primary btn-lg" value="Submit &raquo;">
			</p></form>
        </div>
    </div>
    <%@include file="usernameHTML.jsp" %>
    
  </body>
</html>