package org.gabriele.progettol.ticket;


import centralsystem.CentralSystemWebServerInterface;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import centralsystem.Stub;
import items.Fine;
import jsonenumerations.JsonFields;
import org.json.simple.JSONObject;
import singleton.JSONOperations;



@Path("/secured/collector")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CollectorRequests {

	
	CentralSystemWebServerInterface systemStub = Stub.getInstance();
	
	
	
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
            Fine f = new Fine(id,cf,amount,collectorUsername);
	    String result = JSONOperations.getInstance().booleanPacket(systemStub.makeFine(f));
            System.out.println("Multa creata, sending : "+ result);
	    return result;
	}
	
	@GET
	@Path("/startnumber/{collectorUsername}")
	public String startNumber(@PathParam("collectorUsername") String collectorUsername){
	    
		Long result = systemStub.requestFinesStartNumber(collectorUsername);
                JSONObject data = new JSONObject();
                if(result != null){
                   
                    data.put(JsonFields.DATA.toString(), result);
                    return data.toJSONString();
                }
                data.put(JsonFields.DATA.toString(), result);
                    return data.toJSONString();
	}
	
	
	@GET
	@Path("/tickets/{id}")
	public String checkTicket(@PathParam("id") String ticketID){
		
		Boolean bool =  systemStub.existsTicket(Long.parseLong(ticketID));
		return JSONOperations.getInstance().booleanPacket(bool);
	}	
	
}
