package org.gabriele.progettol.ticket;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import centralsystem.Stub;
import singleton.JSONOperations;



@Path("/secured/collector")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CollectorRequests {

	
	Stub systemStub = Stub.getInstance();
	
	
	
	@GET
	@Path("/login")
	public String collectorLogin(){
		
		String result = JSONOperations.getInstance().msgPacket(true, "Login controllore avvenuto con successo");
		System.out.println("Sending to app : "+result);
		return result;
		
	}
	
	@GET
	@Path("/fine/{id}/{cf}/{amount}/{collectorUsername}")
	public String makeFine(		@PathParam("id") String id,
				              	@PathParam("cf") String cf,
				              	@PathParam("amount") double amount,
				              	@PathParam("collectorUsername") String collectorUsername){
	    
		return systemStub.makeFine(id,cf,amount,collectorUsername);
	    
	}
	
	@GET
	@Path("/startnumber/{collectorUsername}")
	public String startNumber(@PathParam("collectorUsername") String collectorUsername){
	    
		return systemStub.requestFinesStartNumber(collectorUsername);
	    
	}
	
	
	@GET
	@Path("/tickets/{id}")
	public String checkTicket(@PathParam("id") String ticketID){
		
		return systemStub.checkTicket(ticketID);
		
	}	
	
}
