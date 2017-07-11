package org.progettol.webserver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response.Status;

import org.eclipse.persistence.oxm.record.FormattedWriterRecord;
import org.progettol.webserver.beans.User;

import DateSingleton.DateOperations;
import centralSystem.Stub;
import singleton.JSONOperations;

/**
 * Servlet implementation class WebServer
 */
@Path("/web/*")
public class WebServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String)request.getParameter("action");
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
	    
	    System.out.println(" @ azione: "+action);
		switch (action) {
		case "login":
				if(user == null){
					if(Stub.getInstance().userLogin(request.getParameter("username"), request.getParameter("password"))){
						user = new User(request.getParameter("username"));
						session.setAttribute("user", user);
						session.setAttribute("result", "true");
						newRequest(request,response,"/profile.jsp");
					}else{
						session.setAttribute("result", "false");
						newRequest(request,response,"/login.jsp");
					}
				}else{
					session.setAttribute("result", "true");
					response.sendRedirect("/login.jsp");
				}		
			break;
		case "buy":
			if(user !=null){
				//TODO verificare input delle tipologie di biglietti
				String result = Stub.getInstance().buyTicket(user.getUsername(), (String)request.getParameter("cardNumber"),(String)request.getParameter("ticketType"));
				if(JSONOperations.getInstance().booleanParser(result)){
					session.setAttribute("result", "true");
					newRequest(request,response,"/buy.jsp");
				}else{
					session.setAttribute("result", "false");
					newRequest(request,response,"/buy.jsp");
				}
			}else{
				response.sendRedirect("/login.jsp");
			}
			
			break;
		case "registration":
			String result = Stub.getInstance().registration(request.getParameter("name"), request.getParameter("surname"), request.getParameter("cf"), request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
			if(JSONOperations.getInstance().booleanParser(result)){
				session.setAttribute("result", "true");
				newRequest(request,response,"/profile.jsp");
			}else{
				session.setAttribute("result", "false");
				newRequest(request,response,"/registration.jsp");
			}
			break;
		default:
			
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String)request.getParameter("action");
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

	    System.out.println(" @ azione get http webserver.java: "+action);
	    if(action == null) {
	    	response.sendRedirect("/ticket/");
	    	return;
	    }
		switch (action) {
		case "logout":
			if(session != null)session.invalidate();
			response.sendRedirect("/ticket/");
			break;
		default:
			response.setStatus(404);
		}
		
	}

	 private void newRequest(HttpServletRequest request, HttpServletResponse response, String jspPage) 
		       throws ServletException, IOException
		    {
		        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPage);
		        dispatcher.forward(request,response);
		  }
}
