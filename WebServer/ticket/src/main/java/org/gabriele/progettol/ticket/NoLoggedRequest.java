package org.gabriele.progettol.ticket;


import centralsystem.CentralSystemWebServerInterface;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import centralsystem.Stub;
import items.Product;
import javax.ws.rs.POST;
import jsonenumerations.JsonFields;
import machineonline.TicketOnline;
import singleton.JSONOperations;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NoLoggedRequest {

	
	
	CentralSystemWebServerInterface systemStub = Stub.getInstance();
    
    
    @GET
	@Path("/registration/{name}/{surname}/{cf}/{username}/{password}/{email}")
	public String registration(	@PathParam("name") String name,
								@PathParam("surname") String surname,
								@PathParam("cf") String cf,
								@PathParam("username") String username,
								@PathParam("password") String password,
								@PathParam("email") String email){
		
		return systemStub.createUser(name, surname, cf, username, password, email);
    }
    
    @GET
	@Path("/types")
	public String types(){
		
		TicketOnline onlineMachine = TicketOnline.getInstance();		
		
		Map<String,Product> products = onlineMachine.getProducts();
		
		String packet = JSONOperations.getInstance().ticketTypesPacket(products);

		System.out.println("Richiesta ticketTypes, sending to app : " + packet);
		return packet;
    }
        
    @GET
        @Path("/ping")
        public String ping(){
            return JSONOperations.getInstance().booleanPacket(true);
        }
}
