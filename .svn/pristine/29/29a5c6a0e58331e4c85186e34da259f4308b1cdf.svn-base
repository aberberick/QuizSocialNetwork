<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Question Creation</title>
<%@ page import="user.User"%>
<%@ page import="user.UserManager"%>
<%@ page import="message.*"%>
<%@ page import="system.SystemManager"%>
<%@ page import="quiz_system.QuizManager"%>
<%@ page import="quiz_system.Quiz"%>
<%@include file="refBootstrap.jsp" %>
</head>

<body>
<%@include file="refNavbar.jsp" %>

    <div class="jumbotron">
        <div class="container">
            <h1>Create Question for <%= (String)session.getAttribute("quizName") %></h1>
            <p>Third, <strong>enter your question information.</strong></p>
             <h2>
                <%= (String) session.getAttribute("qType") %>
            </h2>
            <% 
            String qType = (String) session.getAttribute("qType");
            int numAnswers = Integer.parseInt((String) session.getAttribute("numAnswers"));
            int numChoices = Integer.parseInt((String) session.getAttribute("numChoices"));            
       //     int numResponses = Integer.parseInt((String) session.getAttribute("numResponses"));            
            if (qType.equals("response")) {
                out.write("<p><form action=\"CreateQuizServlet2\" method=\"post\">");
                out.write("Question title: <input name=\"qtitle\" size=\"70\"></input><br />");
                out.write("Question prompt: <input name=\"qprompt\" size=\"70\"></input><br />");
                for (int i = 0; i < numAnswers; i++) {
                    out.write("Question answer " + i + ": <input name=\"qanswer" + i + "\" size=\"70\"></input><br/>");
                }
                out.write("<input name=\"qType\" type=\"hidden\" value=\"response\"></input>");
                out.write("<br />");
                out.write("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit &raquo;\">");
                out.write("</form>");
            }
           	else if (qType.equals("fillintheblank")) {
           		out.write("<p>Write your questions as \"fill in the ______\" with the underscores indicating your blank.");
                out.write("<p><form action=\"CreateQuizServlet2\" method=\"post\">");
                out.write("Question title: <input name=\"qtitle\" size=\"70\"></input><br />");
                out.write("Question prompt: <input name=\"qprompt\" size=\"70\"></input><br />");
                for (int i = 0; i < numAnswers; i++) {
                    out.write("Question answer " + i + ": <input name=\"qanswer" + i + "\" size=\"70\"></input><br/>");
                }
                out.write("<input name=\"qType\" type=\"hidden\" value=\"fillintheblank\"></input>");
                out.write("<br />");
                out.write("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit &raquo;\">");
                out.write("</form>");

           	}
           	else if (qType.equals("multiplechoice")) {
                out.write("<p><form action=\"CreateQuizServlet2\" method=\"post\">");
                out.write("Question title: <input name=\"qtitle\" size=\"70\"></input><br />");
                out.write("Question prompt: <input name=\"qprompt\" size=\"70\"></input><br />");
                for (int i = 0; i <numChoices; i++) {
                    out.write("Question choice " + i + ": <input name=\"qchoice" + i + "\" size=\"70\"></input><br/>");
                }
                out.write("Question answer: <input name=\"qanswer\" size=\"70\"></input><br/>");
                out.write("<input name=\"qType\" type=\"hidden\" value=\"multiplechoice\"></input>");
                out.write("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit &raquo;\">");
                out.write("</form>");
           	}
           	else if (qType.equals("picture")) {
                out.write("<p><form action=\"CreateQuizServlet2\" method=\"post\">");
                out.write("Question title: <input name=\"qtitle\" size=\"70\"></input><br />");
                out.write("Question prompt: <input name=\"qprompt\" size=\"70\"></input><br />");
                out.write("Select picture: <select name=\"qpicture\">");
                out.write("<option value=\"dog\">Dog</option>");
                out.write("<option value=\"cat\">Cat</option>");
                out.write("<option value=\"flag\">Flag</option>");
                out.write("</select><br/>");
                out.write("<input name=\"qType\" type=\"hidden\" value=\"picture\"></input>");
                for (int i = 0; i < numAnswers; i++) {
                    out.write("Question answer " + i + ": <input name=\"qanswer" + i + "\" size=\"70\"></input><br/>");
                }
                out.write("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit &raquo;\">");
                out.write("</form>");
           	}          
           	else if (qType.equals("multipleanswer")) {
                out.write("<p><form action=\"CreateQuizServlet2\" method=\"post\">");
           	    out.write("Question title: <input name=\"qtitle\" size=\"70\"></input><br />");
                out.write("Question prompt: <input name=\"qprompt\" size=\"70\"></input><br />");
                out.write("Order matters: <input type=\"checkbox\" name=\"ordered\" value=\"ordered\"></input>");
                out.write("</p><input name=\"qType\" type=\"hidden\" value=\"multipleanswer\"></input>");
                for (int i = 0; i < numAnswers; i++) {
                    out.write("Question answer " + i + ": <input name=\"qanswer" + i + "\" size=\"70\"></input><br/>");
                }
                out.write("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit &raquo;\">");
                out.write("</form>");

           	}
            else if (qType.equals("multiplechoicemultipleanswer")) {
                out.write("<p><form action=\"CreateQuizServlet2\" method=\"post\">");
                out.write("Question title: <input name=\"qtitle\" size=\"70\"></input><br />");
                out.write("Question prompt: <input name=\"qprompt\" size=\"70\"></input><br />");
                out.write("<input name=\"qType\" type=\"hidden\" value=\"multiplechoicemultipleanswer\"></input>");
                for (int i = 0; i <numChoices; i++) {
                    out.write("Question choice " + i + ": <input name=\"qchoice" + i + "\" size=\"70\"></input><br/>");
                }
                for (int i = 0; i < numAnswers; i++) {
                    out.write("Question answer " + i + ": <input name=\"qanswer" + i + "\" size=\"70\"></input><br/>");
                }
                out.write("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit &raquo;\">");
                out.write("</form>");
            }
            else if (qType.equals("matching")) {
                out.write("<p><form action=\"CreateQuizServlet2\" method=\"post\">");
                out.write("Question title: <input name=\"qtitle\" size=\"70\"></input><br />");
                out.write("Question prompt: <input name=\"qprompt\" size=\"70\"></input><br />");
                out.write("<input name=\"qType\" type=\"hidden\" value=\"matching\"></input>");
                for (int i = 0; i < numAnswers; i++) {
                    out.write("Question column 1 #" + i + ": <input name=\"qcol1" + i + "\" size=\"70\"></input><br/>");
                    out.write("Question column 2 #" + i + ": <input name=\"qcol2" + i + "\" size=\"70\"></input><br/>");
                }
                out.write("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit &raquo;\">");
            }
            else {
                out.write("<p>Something went wrong.</p>");
            }
            %>
        </div>
    </div>
        <%@include file="usernameHTML.jsp" %>
    
</body>
</html>