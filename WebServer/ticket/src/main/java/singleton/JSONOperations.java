package singleton;

import items.Fine;
import items.Product;
import items.Sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import jsonenumerations.*;
import machineonline.TicketOnline;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONOperations {

    private static JSONOperations instance;

    private JSONOperations() {
    }

    public static synchronized JSONOperations getInstance() {
        if (instance == null) {
            instance = new JSONOperations();
        }
        return instance;
    }
    
    /**
     * Struttura JSON:
 {"METHOD":"GETSALESBYUSERNAME","DATA":{"USERNAME":"String"}}
     */
	  public String getSalesByUsernamePacket(String username) {
	        JSONObject root = new JSONObject();
	        root.put(JsonFields.METHOD.toString(), GetSalesByUsername.GETSALESBYUSERNAME.toString());
	        JSONObject data = new JSONObject();
	        data.put(GetSalesByUsername.USERNAME.toString(), username);
	        root.put(JsonFields.DATA.toString(), data);
	    return root.toJSONString();
	  }
	  
	  /**
	     * Struttura JSON:
	     * {"METHOD":"ADDSALE","DATA":{"USERNAME":"String","SERIALCODE":"long","SALEDATE":"String","TYPE":"String","SELLERMACHINEIP":"String"}}
	     */
	  public String addSalePacket(String username, long serialCode, String saleDate,String type,String sellerMachineIp) {
	        JSONObject root = new JSONObject();
	        root.put(JsonFields.METHOD.toString(), AddSale.ADDSALE.toString());
	        JSONObject data = new JSONObject();
	        
	        data.put(AddSale.USERNAME.toString(), username);
	        data.put(AddSale.SERIALCODE.toString(), serialCode);
	        data.put(AddSale.SALEDATE.toString(), saleDate);
	        data.put(AddSale.TYPE.toString(), type);
	        data.put(AddSale.SELLERMACHINEIP.toString(), sellerMachineIp);
	        
	        root.put(JsonFields.DATA.toString(), data);
	        return root.toJSONString();
	  }
	  
	//Struttura JSON ingresso : {"data":"boolean"}
	  public boolean booleanParser(String data){
	    System.out.println("CONVERSIONE: "+ data );
	        JSONParser parser = new JSONParser();
	        JSONObject obj;
	    try {
	      obj = (JSONObject) parser.parse(data);
	      return ((String)obj.get(JsonFields.DATA.toString())).equals("true");
	    } catch (ParseException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	        return false;
	  }
	  
	  /**
	     * Struttura JSON:
	     * {"data":"boolean"}
	     */
	    public String booleanPacket(boolean bool) {
	        JSONObject root = new JSONObject();
	        if(bool){
	            root.put(JsonFields.DATA.toString(), "true");
	        }else{
	          root.put(JsonFields.DATA.toString(), "false");
	        }
	        return root.toJSONString();
	    }
	  
	  
    /**
     * Struttura JSON:
     * {"method":"USERLOGIN","data":{"username":"String","psw":"String"}}
     */
    public String userLoginPacket(String username, String psw) {
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), UserLogin.USERLOGIN.toString());
        JSONObject data = new JSONObject();
        data.put(UserLogin.USERNAME.toString(), username);
        data.put(UserLogin.PSW.toString(), psw);
        
        root.put(JsonFields.DATA.toString(), data);        
        return root.toJSONString();
    }

//TODO zuzzu help
    public String parseTickets(JSONObject obj){
        try {
            StringBuilder sb = new StringBuilder("<br><br>TICKETS<br><br>----------<br>");
            JSONArray ticketArray = (JSONArray)obj.get(JsonFields.DATA.toString());
            for(int i = 0; i < ticketArray.size();i++){
                JSONObject ticket = (JSONObject) ticketArray.get(i);
                sb.append("<br>       ID: ").append(ticket.get("id"));
                sb.append("<br>       Scadenza: ").append(DateOperations.getInstance().parse((String)ticket.get("expire")).toString());
                sb.append("<br>       Tipo: ").append(ticket.get("type"));
                sb.append("<br><br>----------<br>");
            }
            return sb.toString();
        } catch (java.text.ParseException e) {
      e.printStackTrace();
    }
        return "ERROR PARSING";
    }
    
    /**
     * Struttura JSON:
     * {"method":"COLLECTORLOGIN","data":{"username":"String","psw":"String"}}
     */
    public String collectorLoginPacket(String username, String psw) {
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), CollectorLogin.COLLECTORLOGIN.toString());
        JSONObject data = new JSONObject();
        data.put(CollectorLogin.USERNAME.toString(), username);
        data.put(CollectorLogin.PSW.toString(), psw);
        
        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON: {"method":"REQUESTCODES","data":{"numberOfCodes":"long"}}
     */
    public String requestCodesPacket(long numberOfCodes) {
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), RequestCodes.NUMBEROFCODES.toString());
        JSONObject data = new JSONObject();
        data.put(RequestCodes.NUMBEROFCODES.toString(), numberOfCodes);
        
        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON: {"METHOD":"CARDPAYMENT","DATA":{"CARD_NUMBER":"String","AMOUNT":"double"}}
     */
    public String cardPaymentPacket(String cardNumber, double amount) {
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), CardPayment.CARDPAYMENT.toString());
        JSONObject data = new JSONObject();
        data.put(CardPayment.CARD_NUMBER.toString(), cardNumber);
        data.put(CardPayment.AMOUNT.toString(), amount);
        
        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON: {"method":"EXISTSTICKET","data":{"serialCode":"long"}}
     */
    public String existsTicketPacket(long serialCode) {
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), ExistsTicket.EXISTSTICKET.toString());
        JSONObject data = new JSONObject();
        data.put(ExistsTicket.SERIALCODE.toString(), serialCode);
        
        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON:
     * {"method":"CREATEUSER","data":{"name":"String","surname":"String","cf":"String","username":"String","psw":"String", "EMAIL":"String"}}
     */
    public String createUser(String name, String surname, String cf, String username, String psw, String email) {
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), CreateUser.CREATEUSER.toString());
        JSONObject data = new JSONObject();
        data.put(CreateUser.NAME.toString(), name);
        data.put(CreateUser.SURNAME.toString(), surname);
        data.put(CreateUser.CF.toString(), cf);
        data.put(CreateUser.USERNAME.toString(), username);
        data.put(CreateUser.PSW.toString(), psw);
        data.put(CreateUser.EMAIL.toString(), email);

        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }
    
    
    /**
     * Struttura JSON:
     * {"DATA": [{ "TYPE": "String","DESCRIPTION": "String","COST": "double","DURATION": "int"}]}
     */
    public String ticketTypesPacket(Map<String, Product> products) {

      JSONObject root = new JSONObject();
      JSONArray data = new JSONArray();

      for(Product p : products.values()){
	        JSONObject type = new JSONObject();
	      
	        type.put(TicketTypes.TYPE.toString(), p.getType());
	        type.put(TicketTypes.DESCRIPTION.toString(), p.getDescription());
	        type.put(TicketTypes.COST.toString(), p.getCost());
	        type.put(TicketTypes.DURATION.toString(), p.getDuration());
	        
	        data.add(type);
      }
      
      root.put(JsonFields.DATA.toString(), data);
      
      return root.toJSONString();  
    }
    
    /**
     * Struttura JSON:
     * {"DATA":"boolean","MSG":"String"}
     */
    public String msgPacket(boolean bool,String msg) {
        JSONObject root = new JSONObject();
        if(bool){
            root.put(JsonFields.DATA.toString(), "true");
        }else{
          root.put(JsonFields.DATA.toString(), "false");
        }
        root.put(Msg.MSG.toString(),msg);
        return root.toJSONString();
    }


    /**
     * Struttura JSON:
     * {"METHOD":"MAKEFINE","DATA":{"ID":"String", "CF":"String", "AMOUNT":Double, "COLLECTORUSERNAME":"String"}}
     */
    public String makeFinePacket(Fine f) {
      JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), MakeFine.MAKEFINE.toString());
        JSONObject data = new JSONObject();
        data.put(MakeFine.ID.toString(), f.getId());
        data.put(MakeFine.CF.toString(), f.getCfCode());
        data.put(MakeFine.AMOUNT.toString(), f.getAmount());
        data.put(MakeFine.COLLECTORUSERNAME.toString(), f.getCollectorUsername());

                
        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }

    /**
     * Strutture JSON:
     * {"method":"ADDSALE","data":{"saleDate":"Data", "serialCode":"long", "username":"String", "type":"String", "sellerMachineIp":"String"}}
     */
    public String addSale(Sale sale){
        
        String saleDate = DateOperations.getInstance().toString(sale.getSaleDate());
        long serialCode = sale.getSerialCode();
        String username = sale.getUsername();
        String type = sale.getType();
        String sellerMachineIp = sale.getSellerMachineIp();
        
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), AddSale.ADDSALE.toString());
        JSONObject data = new JSONObject();
        data.put(AddSale.SALEDATE.toString(), saleDate);
        data.put(AddSale.SERIALCODE.toString(), serialCode);
        data.put(AddSale.USERNAME.toString(), username);
        data.put(AddSale.TYPE.toString(), type);
        data.put(AddSale.SELLERMACHINEIP.toString(), sellerMachineIp);
        
        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }

    public String requestTicketTypesPacket() {
        JSONObject root = new JSONObject(); 
        
        root.put(JsonFields.METHOD.toString(), TicketTypes.TICKETTYPES.toString());
        
        return root.toJSONString();
    }
    
    public ArrayList<Sale> decodeSales(String JsonMyTickets) {
		JSONParser parser = new JSONParser();
		JSONObject obj;
		
		try { 
			obj = (JSONObject) parser.parse(JsonMyTickets);
			JSONArray jArr = (JSONArray)obj.get(JsonFields.DATA.toString());
			ArrayList<Sale> sales = new ArrayList<>();
			
			for (Object JSONsale : jArr) {
				Product product = TicketOnline.getInstance().getProduct((String)((JSONObject)JSONsale).get(AddSale.TYPE.toString())); 
				
					sales.add(new Sale(DateOperations.getInstance().parse((String)((JSONObject)JSONsale).get(AddSale.SALEDATE.toString())), 
										((Long)((JSONObject)JSONsale).get(AddSale.SERIALCODE.toString())),
										(String)((JSONObject)JSONsale).get(AddSale.USERNAME.toString()),
										product,
										(String)((JSONObject)JSONsale).get(AddSale.SELLERMACHINEIP.toString())));
			}
			
			return sales;
		} catch (ParseException e) {
			// TODO Gestire eccezzione dell'errore di parsing
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
    public String getValidSalesByUsername(String username) {
        JSONObject root = new JSONObject();
          root.put(JsonFields.METHOD.toString(), GetValidSalesByUsername.GETVALIDSALESBYUSERNAME.toString());
          JSONObject data = new JSONObject();
          data.put(GetSalesByUsername.USERNAME.toString(), username);
          root.put(JsonFields.DATA.toString(), data);
      return root.toJSONString();     
      }

    public String requestFinesStartNumberPacket(String collectorUsername) {
      
      JSONObject root = new JSONObject();
          
          root.put(JsonFields.METHOD.toString(), RequestFinesStartNumber.REQUESTFINESSTARTNUMBER.toString());
          JSONObject data = new JSONObject();
          data.put(RequestFinesStartNumber.COLLECTORUSERNAME.toString(), collectorUsername);
          
          root.put(JsonFields.DATA.toString(), data);
          
          return root.toJSONString();
      
    }

    public String getSalePacket(Long valueOf) {
          JSONObject root = new JSONObject();
          root.put(JsonFields.METHOD.toString(), getSale.GETSALE.toString());
          JSONObject data = new JSONObject();
          data.put(getSale.SERIALCODE.toString(), valueOf);
          root.put(JsonFields.DATA.toString(), data);
          
          return root.toJSONString();

    }
    
    
}
