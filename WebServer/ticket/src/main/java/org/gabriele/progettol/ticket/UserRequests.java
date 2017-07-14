package org.gabriele.progettol.ticket;


import javax.ws.rs.*;
import javax.ws.rs.core.*;

import centralsystem.Stub;
import singleton.JSONOperations;



@Path("/secured/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRequests {

	
	Stub systemStub = Stub.getInstance();
	
	
	
	@GET
	@Path("/login")
	public String userLogin(){
		
		String result = JSONOperations.getInstance().msgPacket(true,"Login utente avvenuto con successo");
		System.out.println("Sending to app : "+result);
		return result;
	}
	
	@GET
	@Path("/mytickets/{username}")
	public String myTickets(@PathParam("username") String username){
			
		System.err.println("MYTICKETS");
		return systemStub.myTickets(username);		
		
	}
	
	@GET
	@Path("/myvalidtickets/{username}")
	public String myValidTickets(@PathParam("username") String username){
			
		System.err.println("MYVALIDTICKETS");
		return systemStub.myValidTickets(username);		
		
	}
	
	@GET
	@Path("/buy/{username}/{cardNumber}/{type}")
	public String buyTicket(	@PathParam("username") String username,
								@PathParam("cardNumber") String cardNumber,
								@PathParam("type") String type){
		
		return systemStub.buyTicket(username, cardNumber, type);
		
	}
	

}
