package centralSystem;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.PathParam;

import org.gabriele.progettol.ticket.ConnectionHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DateSingleton.DateOperations;
import items.Fine;
import items.Product;
import jsonenumerations.JsonFields;
import machineOnline.TicketOnline;
import singleton.JSONOperations;

public class Stub {

	private static Stub instance;
	
	private Stub(){
	}
	
    public static synchronized Stub getInstance() {
        if (instance == null) {
            instance = new Stub();
        }
        return instance;
    }
    
	public String myTickets(String username){
		try{
			String packet = JSONOperations.getInstance().myTicketsPacket(username);
			ConnectionHandler conn = new ConnectionHandler();
			System.out.println("Checking my tickets.. sending to system: "+ packet);
			
			String line = conn.sendAndReceive(packet);
			conn.closeConnection();
			JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            System.out.println("sending to app the result: " + obj.toJSONString());
            return obj.toJSONString();
            
		}catch(IOException|ParseException e){
			e.printStackTrace();
		}
		System.err.println("ERRORE MYTICKETS");
		return null;
	}
	
	public String myValidTickets(String username) {
		try{
			String packet = JSONOperations.getInstance().myValidTicketsPacket(username);
			ConnectionHandler conn = new ConnectionHandler();
			System.out.println("Checking my tickets.. sending to system: "+ packet);
			
			String line = conn.sendAndReceive(packet);
			conn.closeConnection();
			JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            System.out.println("Sending to app the result: " + obj.toJSONString());
            return obj.toJSONString();
            
		}catch(IOException|ParseException e){
			e.printStackTrace();
		}
		System.err.println("ERRORE MYVALIDTICKETS");
		return null;
	}	

	public String buyTicket(String username, String cardNumber,String type){
		
		
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

	//COLLECTOR REQUESTS

	
	
	public String requestFinesStartNumber(String collectorUsername) {
	    try{
	      String request = JSONOperations.getInstance().requestFinesStartNumberPacket(collectorUsername);
	      
	      ConnectionHandler conn = new ConnectionHandler();
	      System.out.println("--Requesting start number for collector: " + collectorUsername + " —\nSending : " + request);
	      String answer = conn.sendAndReceive(request);
	      conn.closeConnection();
	      System.out.println("Sending to app: " + answer);
	      
	          return answer;
	    }catch(IOException ex){
	      return JSONOperations.getInstance().booleanPacket(false);
	    }
	  }

	
	
	
	
	public String makeFine(String id,String cf,double amount,String collectorUsername){
	    
	    String message = "Errore Connessione";
	    
	    try{
	      String packet = JSONOperations.getInstance().makeFinePacket(new Fine(id,cf,amount, collectorUsername));
	      
	      
	      ConnectionHandler conn = new ConnectionHandler();
	      
	      System.out.println("Sending : " + packet);
	      
	      //INVIO MULTA
	      String line = conn.sendAndReceive(packet);
	      conn.closeConnection();
	      JSONParser parser = new JSONParser();               
	          JSONObject obj = (JSONObject)parser.parse(line);
	          
	          //Struttura JSON di risposta : {"data":"boolean"}
	          if((Boolean)obj.get(JsonFields.DATA.toString())){
	            message = "Multa creata con successo";
	            String result = JSONOperations.getInstance().msgPacket(true, message);
	            System.out.println("Multa creata, sending : "+ result);
	            //return JSONOperations.getInstance().booleanPacket(true);
	            return result;
	          }
	          
	          message = "Creazione multa fallita";
	          
	    } catch (IOException e) {
	      e.printStackTrace();
	      message = "Eccezione di I/O";
	    } catch (ParseException e){
	      e.printStackTrace();
	      message = "Eccezione di Parsing";
	    }
	    
	    String result = JSONOperations.getInstance().msgPacket(false, message);
	    System.out.println("Operazione fallita, sending : " + result);
	    return  result;
	  }
	
	
	
	
	
	
	public String checkTicket(String ticketID){

		try{
			String packet = JSONOperations.getInstance().existsTicketPacket(Long.valueOf(ticketID));
			
			ConnectionHandler conn = new ConnectionHandler();
			
			System.out.println("Checking in system .. Sending : " + packet);
			String line = conn.sendAndReceive(packet);
			conn.closeConnection();
			JSONParser parser = new JSONParser();               
	        JSONObject obj = (JSONObject)parser.parse(line);
	        
	        //Struttura JSON di risposta : {"data":"boolean"}
	        if( (Boolean)obj.get(JsonFields.DATA.toString())){
	        	System.out.println("Biglietto esistente.");
	        	return JSONOperations.getInstance().booleanPacket(true);
	        }
		} catch (IOException|ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Biglietto inesistente.");
		return  JSONOperations.getInstance().booleanPacket(false);
	}
	
	//FILTRO LOGIN
	
	
	
	
	public boolean userLogin(String username,String password){
		try{			
			ConnectionHandler conn = new ConnectionHandler();
			String packet = JSONOperations.getInstance().userLoginPacket(username, password);
			//System.out.println("Checking in server..");
			String line = conn.sendAndReceive(packet);
			
			JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            conn.closeConnection();
            
            //Struttura JSON di risposta : {"data":"boolean"}
            if((Boolean)obj.get(JsonFields.DATA.toString())){
            	System.out.println("result from system: AUTHORIZED");
            	return true;
            }
		} catch (IOException|ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	public boolean collectorLogin(String username,String password){
		try{			
			ConnectionHandler conn = new ConnectionHandler();
			String packet = JSONOperations.getInstance().collectorLoginPacket(username, password);
			//System.out.println("Checking in server..");
			String line = conn.sendAndReceive(packet);
			
			JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            conn.closeConnection();
            
            //Struttura JSON di risposta : {"data":"boolean"}
            if((Boolean)obj.get(JsonFields.DATA.toString())){
            	System.out.println("result from system: AUTHORIZED");
            	return true;
            }

            
		} catch (IOException|ParseException e) {
			e.printStackTrace();
		}
		return false;
	}


	
	
	
	public String registration(String name,String surname,String cf, String username, String password, String email){
		try{
			String request = JSONOperations.getInstance().createUser(name, surname, cf, username, password, email);
			
			ConnectionHandler conn = new ConnectionHandler();
			System.out.println("--Registration--\nSending : " + request);
			String answer = conn.sendAndReceive(request);
			conn.closeConnection();
			
			System.out.println("Received : " + answer);
			JSONParser parser = new JSONParser();               
	        JSONObject obj = (JSONObject)parser.parse(answer);
	        
	        //Struttura JSON di risposta : {"data":"boolean"}
	        if((Boolean)obj.get(JsonFields.DATA.toString())){
	        	return JSONOperations.getInstance().booleanPacket(true);
	        }
		} catch (IOException|ParseException e) {
			e.printStackTrace();
		}
			return JSONOperations.getInstance().booleanPacket(false);
	}


}
