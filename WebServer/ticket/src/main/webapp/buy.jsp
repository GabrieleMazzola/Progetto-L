<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="items.Product" %>
        <%@ page import="machineonline.TicketOnline" %>        
        <%@ page import="java.util.Map" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/ticket/css/style.css">
<title>Buy Tickets</title>
</head>
<body>
<%@include  file="./include/header.jsp" %>
<div class="container">
<div class="jumbotron homepageContainer">

	<h2 class="loginh2"> But Ticket or Season </h2>
		<div class="input-group  loginForm">
<% 
	String result = (String) session.getAttribute("result");
	Map<String,Product> products = TicketOnline.getInstance().getProducts();
	if(result != null){
		if(result.equals("false")){
			%><h3 style="color:red">Errore acquisto del biglietto</h3><% 
		}
	}%>
	
<% if(user != null){ %>	
<form action="/ticket/web/" METHOD="POST">
<input type="hidden" name="action" value="buy">



 
		<div class="form-group row">
				 <label for="cardNumber" class=" col-form-label col-form-label-lg">Card Number</label>
				 <div class="">
				 	<input type="text" class="form-control col-sm-12" name="cardNumber" id="cardNumber" maxlength="16" value="2222222222222222"  aria-describedby="basic-addon1">
				 </div>
		</div>
		

  
	<div class="form-group row">
			<label for="ticketType" class=" col-form-label col-form-label-lg">Tipologia biglietto</label>
			<div class="">
				<select  class="selectpicker form-control col-sm-12" name="ticketType" id="ticketType">
					<% for (Product item : products.values()){  %>
					 <option value="<%=item.getType()%>"><%=item.getDescription()%> - <%=item.getCost()%> euro</option>
				 		<%}%>
					</select>
		</div>
	</div>
  <br>
  <input type="submit" class="btn btn-default col-sm-12" value="Submit">
</form> 
 <% }else{
		response.sendRedirect("login.jsp");
 }
	%>
	
	<%if(result != null) if(result.equals("true") && user != null){ %> <h3 style="color:green">Comprato!</h3><%
		session.removeAttribute("result");
	}%>
	</div>
	</div>
</div>
<%@include  file="./include/footer.html" %>

</body>
</html>