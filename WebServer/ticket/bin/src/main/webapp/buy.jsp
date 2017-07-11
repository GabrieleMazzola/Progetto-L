<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="items.Product" %>
        <%@ page import="machineOnline.TicketOnline" %>        
        <%@ page import="java.util.Map" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include  file="./include/header.jsp" %>

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

  Credit card number:<br>
  <input type="text" name="cardNumber" value="2222222222222222">
  <br>
  Tipologia biglietto:<br>
   <select name="ticketType">
   <% for (Product item : products.values()){  %>
    <option value="<%=item.getType()%>"><%=item.getDescription()%></option>
    <%}%>
  </select>
  
  <br><br>
  <input type="submit" value="Submit">
</form> 
 <% }else{
		response.sendRedirect("login.jsp");
 }
	%>
	
	<%if(result != null) if(result.equals("true") || user != null){ %> <h3 style="color:green">Comprato!</h3><%
		session.removeAttribute("result");
	}%>
	
	
	


</body>
</html>