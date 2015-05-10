 <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.jsp">Team Snow Tiger</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="home.jsp">Quiz Growl Home</a></li>
            <li><a href="inbox.jsp">Message Central</a></li>
            <li><a href="profile.jsp?name=<%= session.getAttribute("signedInUserName")%>"><strong>Current user: <%= session.getAttribute("signedInUserName")%></strong></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>