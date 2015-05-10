<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Search</title>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="imports.jsp" %>
<%@include file="refNavbar.jsp" %>

   <div class="jumbotron">
       <div class="container">
            <h1>Quiz Search</h1>
            Search by Name
    <form action="QuizSearchServlet" method="post">
	<input type="text" name="quizName">
	<input type="submit" class="btn btn-primary btn-lg"
	value="Search &raquo;">
	</form>
	
	Search by Category
	    <form action="quizSearchCategoryResults.jsp" method="post">
	   <!--<form action="QuizSearchCategoryServlet" method="post">
	            --><select name="quizCategory">
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
                </select>
	<input type="submit" class="btn btn-primary btn-lg"
	value="Search &raquo;">
	</form>
	Search by Tag
		    <form action="quizSearchTagResults.jsp" method="post">
		    <input type="text" name="tag">
	    
	<input type="submit" class="btn btn-primary btn-lg"
	value="Search &raquo;">
	</form>
<a href="allQuizzes.jsp"> View all Quizzes</a>
        </div>
        </div>
</body>
</html>

