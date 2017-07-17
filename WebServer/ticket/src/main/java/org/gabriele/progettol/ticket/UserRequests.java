package org.gabriele.progettol.ticket;


import javax.ws.rs.*;
import javax.ws.rs.core.*;

import centralsystem.Stub;
import items.Sale;
import java.util.Set;
import jsonenumerations.GetSalesByUsername;
import jsonenumerations.JsonFields;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import singleton.DateOperations;
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
		Set<Sale> saleList = systemStub.getSalesByUsername(username);
                JSONArray jsonList = new JSONArray();
                JSONObject data = new JSONObject();
                for (Sale sale : saleList) {
                    JSONObject jsonSale = new JSONObject();  
                    jsonSale.put(GetSalesByUsername.SERIALCODE.toString(),sale.getSerialCode()); 
                    jsonSale.put(GetSalesByUsername.SALEDATE.toString(), DateOperations.getInstance().toString(sale.getSaleDate()));
                    jsonSale.put(GetSalesByUsername.TYPE.toString(), sale.getType());
                    jsonSale.put(GetSalesByUsername.SELLERMACHINEIP.toString(), sale.getSellerMachineIp());
                    jsonSale.put(GetSalesByUsername.USERNAME.toString(), sale.getUsername());
                    jsonList.add(jsonSale);

                }
                data.put(JsonFields.DATA.toString(), jsonList);
                return data.toString();
	}
	
	@GET
	@Path("/myvalidtickets/{username}")
	public String myValidTickets(@PathParam("username") String username){
			
		System.err.println("GETVALIDSALESBYUSERNAME");
		Set<Sale> saleList = systemStub.getValidSalesByUsername(username);
                JSONArray jsonList = new JSONArray();
                JSONObject data = new JSONObject();
                for (Sale sale : saleList) {
                    JSONObject jsonSale = new JSONObject();  
                    jsonSale.put(GetSalesByUsername.SERIALCODE.toString(),sale.getSerialCode()); 
                    jsonSale.put(GetSalesByUsername.SALEDATE.toString(), DateOperations.getInstance().toString(sale.getSaleDate()));
                    jsonSale.put(GetSalesByUsername.TYPE.toString(), sale.getType());
                    jsonSale.put(GetSalesByUsername.SELLERMACHINEIP.toString(), sale.getSellerMachineIp());
                    jsonSale.put(GetSalesByUsername.USERNAME.toString(), sale.getUsername());
                    jsonList.add(jsonSale);

                }
                data.put(JsonFields.DATA.toString(), jsonList);
                return data.toString();		
		
	}
	
	@GET
	@Path("/buy/{username}/{cardNumber}/{type}")
	public String buyTicket(	@PathParam("username") String username,
								@PathParam("cardNumber") String cardNumber,
								@PathParam("type") String type){
		
		return systemStub.buyTicket(username, cardNumber, type);
		
	}
	

}
