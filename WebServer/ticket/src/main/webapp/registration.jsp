<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.progettol.webserver.beans.User" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/ticket/css/style.css">

<title>Pagina di registrazione</title>
</head>
<%
String result = (String) session.getAttribute("result");%>

<body>
<%@include  file="./include/header.jsp" %>

<%if(user == null || result ==null){%>
<div class="container">
	<div class="jumbotron homepageContainer">
	<h2 class="loginh2"> Registrazione </h2>
	
		<div class="input-group  loginForm">
			
<%if(result != null) if(result.equals("false")){ %> <h3 style="color:red">Errore registrazione</h3><%
session.removeAttribute("result");
}%>
<%if(result != null) if(result.equals("true") || user != null){ %> <h3 style="color:red">Sei già registrato</h3><%}%>
			
			<form action="web/" METHOD="POST">
			<input type="hidden" name="action" value="registration">
			<div class="form-group row">
			  <label class="col-sm-4 col-form-label col-form-label-lg">Username</label>
			  	<div class="col-sm-10">
				 	<input type="text" class="form-control" placeholder="Username" id="usern" name="username" aria-describedby="basic-addon1">
				</div>
			</div>
			
			<div class="form-group row"> 
			  <label for="usern" class="col-sm-4 col-form-label col-form-label-lg">Surname</label>
			  	<div class="col-sm-10">
				 	<input type="text" class="form-control" placeholder="Surname" id="surname" name="surname" aria-describedby="basic-addon1">
				</div>
			  </div>
			  
			  <div class="form-group row"> 
			  <label for="usern" class="col-sm-4 col-form-label col-form-label-lg">name</label>
			  	<div class="col-sm-10">
				 	<input type="text" class="form-control" placeholder="Surname" id="name" name="name" aria-describedby="basic-addon1">
				</div>
			  </div>
			  
			  <div class="form-group row">
			  <label for="usern" class="col-sm-4 col-form-label col-form-label-lg">Password</label>
				<div class="col-sm-10">
				 	<input type="text" class="form-control" placeholder="Password" id="password" name="password" aria-describedby="basic-addon1">
				 </div>
				</div>
				
			<div class="form-group row"> 			  
			  <label for="usern" class="col-sm-4 col-form-label col-form-label-lg">E-mail</label>
				<div class="col-sm-10">
				 	<input type="text" class="form-control" placeholder="Email" id="email" name="email" aria-describedby="basic-addon1">
				 </div>
			</div>
			
			<div class="form-group row">	
				<label for="usern" class="col-sm-6 col-form-label col-form-label-lg">Codice Fiscale</label>
				<div class="col-sm-10">
				 	<input type="text" class="form-control" placeholder="Codice Fiscale" id="cf" name="cf" aria-describedby="basic-addon1">
				 </div>
			 </div>
			  
			  <input type="submit"  class="btn btn-default col-sm-10" value="Invia">
			  
			</form> 
			
			</div>
	</div>
</div>

<%}%>


</body>
</html>