package org.gabriele.progettol.ticket;


import centralsystem.CentralSystemWebServerInterface;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import centralsystem.Stub;
import items.Product;
import items.Sale;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import jsonenumerations.GetSalesByUsername;
import jsonenumerations.JsonFields;
import machineonline.TicketOnline;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import singleton.DateOperations;
import singleton.JSONOperations;



@Path("/secured/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRequests {

	
	CentralSystemWebServerInterface systemStub = Stub.getInstance();
	
	
	
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
		
                TicketOnline onlineMachine = TicketOnline.getInstance();	
		
		//STAMPE LOG
		System.err.print("BUY TICKET: ");
		
		//RILEVAMENTO COSTO
		Product item = TicketOnline.getInstance().getProduct(type);
		
		//STAMPE LOG
		System.out.print("Description: "+ item.getDescription());
		System.out.print(",  Type : " + item.getType());
		System.out.print(",  Cost : " + item.getCost());
		System.out.print(".  Paying...");
		
		//PAGAMENTO
		boolean result = onlineMachine.creditCardPayment(cardNumber,item.getCost());
		
		System.out.println("Risultato pagamento :" + result);
		
		//AGGIUNTA DELLA VENDITA
		if(result){
			return addSale(onlineMachine,username,item.getType());
		}
		
		//STAMPA SERIALI
		onlineMachine.printSerials();
		
		System.out.println("Sending to app : "+ result);
		return JSONOperations.getInstance().booleanPacket(result);		
	}
	
        private String addSale(TicketOnline onlineMachine,String username,String type) {		
		
		Long serialCode = onlineMachine.useOneSerial();
		System.err.println("ADDSALE: Seriale comprato: " + serialCode);
		String sellDate = DateOperations.getInstance().toString(new Date());

		
		ConnectionHandler conn = new ConnectionHandler();
		String packet = JSONOperations.getInstance().addSalePacket(username,serialCode,sellDate,type,conn.getIP());

		try{
			System.out.println("addSale to system :" + packet);
			
			//INVIO INFORMAZIONI VENDITA
			String line = conn.sendAndReceive(packet);
			conn.closeConnection();
			JSONParser parser = new JSONParser();               
                        JSONObject obj = (JSONObject)parser.parse(line);
                        //STAMPA RISULTATO
                        System.out.println("Result: " + obj.toJSONString());
                        if((Boolean)obj.get(JsonFields.DATA.toString())){

                            //STAMPA SERIALI
                            onlineMachine.printSerials();
                            return JSONOperations.getInstance().booleanPacket(true);
                        }
            
		}catch(IOException|ParseException e){
			e.printStackTrace();
		}
		//STAMPA SERIALI
		onlineMachine.printSerials();
		System.err.println("ADDSALE FALLITA");
		
		return JSONOperations.getInstance().booleanPacket(false);		
	}
        

}
