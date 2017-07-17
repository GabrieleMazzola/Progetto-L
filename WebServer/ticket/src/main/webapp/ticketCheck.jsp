<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="singleton.DateOperations"%>
<%@page import="items.Sale"%>
<%@page import="org.progettol.webserver.TicketChecker"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/ticket/css/style.css">
        <title>Verifica online del biglietto</title>
    </head>
    <body>
        
    <%@include  file="./include/header.jsp" %>
    <br><br>
    <% 
        TicketChecker ticketChecker;
        Sale sale = null;
        if(request.getParameter("value") != null){
            ticketChecker = new TicketChecker(request.getParameter("value"));
            sale = ticketChecker.getTicket();
        }
    %>
    <div class="container">
            <div class="jumbotron homepageContainer">

              <h2 class="loginh2">Verifica online</h2>
            <% if(sale != null){ %> 
            <h3><%=sale.getProduct().getDescription()%></h3>
            <h4>Acquistato da: <%=sale.getUsername()%><br>
                <% Date data = sale.getExpiryDate();%>
                Il titolo di viaggio è valido fino al <%=DateOperations.getInstance().getDay(data)%>/<%=DateOperations.getInstance().getMonth(data)%>/<%=DateOperations.getInstance().getYear(data)%>  <%=DateOperations.getInstance().getHour(data)%>:<%=DateOperations.getInstance().getMinute(data)%></h4><br>
                <%if(sale.getExpiryDate().after(Calendar.getInstance().getTime())){%>
                <div class="alert alert-success">
                    <strong>Valido!</strong> Il tuo titolo di viaggio è valido.
                </div>
                <%}else{%>
                <div class="alert alert-warning">
                    <strong>Attenzione!</strong> Il tuo titolo di viaggio è scaduto.
                </div>
            
                <%}
            }else{%>  
                <div class="alert alert-danger">
                    <strong>Errore!</strong> Il tuo tiolo di viaggio non è valido.
                </div>  
            <%}%>
             

              <a class="btn btn-default btn-lg" href="login.jsp">Login</a>
              
      </div>
    </div>

    <%@include  file="./include/footer.html" %>

    </body>
</html>
