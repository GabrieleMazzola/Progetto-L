<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ page import="centralsystem.Stub" %>
    	<%@ page import="singleton.JSONOperations" %>
    	<%@ page import="org.json.simple.parser.JSONParser" %>
    	<%@ page import="org.progettol.webserver.SaleFactory" %>
    	<%@ page import="org.json.simple.JSONObject" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/ticket/css/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User profile page</title>
</head>
<body>
<%@include  file="./include/header.jsp" %>
<br>
<div class="container">
	<div class="jumbotron homepageContainer">
<h2 class="loginh2"> Lista dei biglietti e degli abbonamenti </h2>
<div class="SaleContainer">
<% 
	String result = (String) session.getAttribute("result");
	Stub CentralSystemStub = Stub.getInstance(); 
	SaleFactory saleFactory;
	//TODO controllare se user è diverso da null
	%>
	
	<% if(user != null){
		
			String json = CentralSystemStub.myTickets(user.getUsername());
			saleFactory = new SaleFactory(json);
			%>(debug) tot sale:<%=saleFactory.getNumberOfSales()%> 
	<div class="saleBox">
		<div class="container">
		<div class="row">
		<% while(saleFactory.hasNext()){ %>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				<div class="offer saleValid-<%=saleFactory.isValid()%>">
					<div class="shape">
						<div class="shape-text">
							<%if(saleFactory.isValid()){ %>Valido
							<%}else{ %> Scaduto <% }%>								
						</div>
					</div>
					<div class="offer-content">
						<h3 class="lead">
							<%=saleFactory.getDescription()%>
						</h3>						
						<p>
							Acquistato: <%=saleFactory.getBuyDay()%>:<%=saleFactory.getBuyMonth()%>:<%=saleFactory.getBuyYear()%>
							<br> Scadenza: <%=saleFactory.getExpDay()%>:<%=saleFactory.getExpMonth()%>:<%=saleFactory.getExpYear()%> 
							alle ore: <%=saleFactory.getExpHour()%>:<%=saleFactory.getExpMinute()%>
						</p>
					</div>
				</div>
			</div>
			
			<%saleFactory.nextSale();
		}%>	
			
				
		</div>
	</div>
	</div>

 <%
		}else{
			response.sendRedirect("/ticket/login.jsp");
		}
		session.removeAttribute("result");
%>
		<a href="/ticket/buy.jsp"><button type="button" class="btn btn-default">Compra nuovi biglietti</button></a>
		</div>
	</div>
</div>
</body>
</html>