<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ page import="centralsystem.Stub" %>
    	<%@ page import="singleton.JSONOperations" %>
    	<%@ page import="org.json.simple.parser.JSONParser" %>
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
<a href="/ticket/buy.jsp">Compra biglietto</a>

<% 
	String result = (String) session.getAttribute("result");
	Stub CentralSystemStub = Stub.getInstance(); 
	
	//TODO controllare se user è diverso da null
	String json = CentralSystemStub.myTickets(user.getUsername());
	JSONParser jsp = new JSONParser();%>
	
	<% if(user != null){ %>	
	
	<%=JSONOperations.getInstance().parseTickets((JSONObject)jsp.parse(json)) %>
	
	
	
<%}else{response.sendRedirect("/login.jsp");}
session.removeAttribute("result");
%>
</body>
</html>