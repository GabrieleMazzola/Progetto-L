<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/ticket/css/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include  file="./include/header.jsp" %>
<br><br>
<div class="container">
	<div class="jumbotron homepageContainer">
	
	  <h1>Benvenuto su MonopattinoAirway!</h1>
	  <p>Il sito è in versione limitata (monopattino), entra nella community dei viaggiatori.</p>
	  <p><a class="btn btn-success btn-lg" href="registration.jsp" role="button">Registrati</a>
	  <a class="btn btn-default btn-lg" href="login.jsp" role="button">Login</a>
	  </p>
	  
	  
  </div>
</div>

<%session.removeAttribute("result"); %>

<%@include  file="./include/footer.html" %>

</body>
</html>