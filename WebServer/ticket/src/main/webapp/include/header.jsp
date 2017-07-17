<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.progettol.webserver.beans.User" %>

<nav class="navbar navbar-default navbar-fixed-top headerNavbar">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand logo-container" href="/ticket/">
        <img alt="MonopattinoAirways" class="img-responsive logo" src="https://github.com/IngSW-unipv/Progetto-L/blob/master/Logo.png?raw=true"> 
      </a>
      </div>
<%User user = (User)session.getAttribute("user");%>
<% if(user!=null){ %>
	<p class="navbar-text navbar-right"> <a href="/ticket/profile.jsp" class="navbar-link">visita il profilo: <%=user.getUsername()%></a></p>
	<p class="navbar-text navbar-right"><a href="/ticket/web/?action=logout" class="navbar-link">Logout</a></p>
<%}else{%>
	<p class="navbar-text navbar-right"><a href="/ticket/login.jsp" class="navbar-link">Login</a></p>
	<p class="navbar-text navbar-right"><a href="/ticket/registration.jsp" class="navbar-link">Registrazione</a></p>
<%} %>	

  </div>
</nav>