<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.progettol.webserver.beans.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/ticket/css/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body> 

<%@include  file="./include/header.jsp" %>
<div class="container">
<div class="jumbotron homepageContainer">
	<h2 class="loginh2"> Login </h2>
<% 
	String result = (String) session.getAttribute("result");
	
	if(result != null){
		if(result.equals("false")){
			%><h3 style="color:red">Errore dati</h3><% 
		}
	}%>
	
<% if(user == null){ %>	

	<div class="input-group  loginForm">
	
	<form action="/ticket/web/" METHOD="POST">
			<input type="hidden" name="action" value="login">
			 <div class="form-group row">
				 <label for="usern" class=" col-form-label col-form-label-lg">Username</label>
				 <div class="">
				 	<input type="text" class="form-control col-sm-12" placeholder="Username" id="usern" name="username" aria-describedby="basic-addon1">
				 </div>
			</div>
			<div class="form-group row">
				<label for="passw" class=" col-form-label col-form-label-lg">Password</label>
				<div class="">
					<input type="password" class="form-control col-sm-12" placeholder="password" id="passw" name="password" aria-describedby="basic-addon1">
				</div>
			</div>
	 		  <input type="submit"  class="btn btn-primary col-sm-12" value="Invia">
		  
		</form> 


 <% }else{%> 
		<div class="alert alert-danger" role="alert">
		  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  <span class="sr-only">Errore:</span>
		  Hai già effettuato il login con <%=(user.getUsername())%>
		</div>
<% }%>


	</div>
		</div>
</div>
<%@include  file="./include/footer.html" %>
	
</body>
</html>