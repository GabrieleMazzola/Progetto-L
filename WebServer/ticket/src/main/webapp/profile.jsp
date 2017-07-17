<%@page import="centralsystem.CentralSystemWebServerInterface"%>
<%@page import="java.net.InetAddress"%>
<%@page import="avvio.Start"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ page import="centralsystem.Stub" %>
    	<%@ page import="singleton.JSONOperations" %>
    	<%@ page import="org.json.simple.parser.JSONParser" %>
    	<%@ page import="org.progettol.webserver.SaleHandler" %>
    	<%@ page import="org.json.simple.JSONObject" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/ticket/css/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista dei biglietti</title>
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
	CentralSystemWebServerInterface CentralSystemStub = Stub.getInstance(); 
	SaleHandler saleFactory;
	//TODO controllare se user è diverso da null
	%>
	
	<% if(user != null){
		
			
			saleFactory = new SaleHandler( CentralSystemStub.getSalesByUsername(user.getUsername()));
			%>
	<div class="saleBox">
		<div class="container">
		<div class="row">
		<% while(saleFactory.hasNext()){ %>
			<div class="SingleSale col-xs-12 col-sm-6 col-md-4 col-lg-3">
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
						<p class="SaleDescription">
							Acquistato: <%=saleFactory.getBuyDay()%>:<%=saleFactory.getBuyMonth()%>:<%=saleFactory.getBuyYear()%>
                                                        <br>
                                                        <br>
                                                        
                                                        <img src="https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=http%3A%2F%2F<%=InetAddress.getLocalHost().getHostAddress()%>%3A8080%2Fticket%2Fweb%2F%3Faction%3Dcheck%26value%3D<%=saleFactory.getCode()%>" alt="HTML5 Icon" style="width:128px;height:128px;">

							<br> <br> 
                                                        Scadenza: <%=saleFactory.getExpDay()%>:<%=saleFactory.getExpMonth()%>:<%=saleFactory.getExpYear()%> 
                                                        <br>
							alle ore: <%=saleFactory.getExpHour()%>:<%=saleFactory.getExpMinute()%>
                                                        <br>
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
		<a href="/ticket/buy.jsp"><button type="button" class="btn btn-primary">Compra nuovi biglietti</button></a>
		</div>
	</div>
</div>
</body>
</html>