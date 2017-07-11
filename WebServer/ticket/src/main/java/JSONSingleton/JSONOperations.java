package JSONSingleton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DateSingleton.DateOperations;


public class JSONOperations {

    private static JSONOperations instance;

    private JSONOperations() {
    }

    /**
     *
     * Restituisce un riferimento al JSONOperator, secondo pattern singleton
     */
    public static synchronized JSONOperations getInstance() {
        if (instance == null) {
            instance = new JSONOperations();
        }
        return instance;
    }

    /**
     * Struttura JSON:
     * {"method":"USERLOGIN","data":{"username":"String","psw":"String"}}
     */
    public String userLoginPacket(String username, String psw) {
        JSONObject root = new JSONObject();
        root.put("method", "USERLOGIN");
        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("psw", psw);
        root.put("data", data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON:
     * {"method":"COLLECTORLOGIN","data":{"username":"String","psw":"String"}}
     */
    public String collectorLoginPacket(String username, String psw) {
        JSONObject root = new JSONObject();
        root.put("method", "COLLECTORLOGIN");
        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("psw", psw);
        root.put("data", data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON:
     * {"data":"boolean","msg":"String"}
     */
    public String msgPacket(boolean bool,String msg) {
        JSONObject root = new JSONObject();
        if(bool){
            root.put("data", "true");
        }else{
          root.put("data", "false");
        }
        root.put("msg",msg);
        return root.toJSONString();
    }
    
    /**
     * Struttura JSON: {"method":"EXISTSTICKET","data":{"ticketCode":"String"}}
     */
    public String existsTicketPacket(int ticketCode) {
        JSONObject root = new JSONObject();
        root.put("method", "EXISTSTICKET");
        JSONObject data = new JSONObject();
        data.put("ticketCode", ticketCode);
        root.put("data", data);
        return root.toJSONString();
    }
    
    /**
     * Struttura JSON: {"method":"REQUESTCODES"}
     */
    public String requestCodesPacket(long numberOfCodes) {
        JSONObject root = new JSONObject();
        root.put("method", "REQUESTCODES");
        JSONObject data = new JSONObject();
        data.put("numberOfCodes", numberOfCodes);
        root.put("data", data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON: {"method":"CARDPAYMENT","data":{"cardNumber":"String"}}
     */
    public String cardPaymentPacket(String cardNumber, double amount) {
        JSONObject root = new JSONObject();
        root.put("method", "CARDPAYMENT");
        JSONObject data = new JSONObject();
        data.put("cardNumber", cardNumber);
        data.put("amount", amount);
        root.put("data", data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON: {"method":"EXISTSTICKET","data":{"ticketCode":"String"}}
     */
    public String existsTicketPacket(String ticketCode) {
        JSONObject root = new JSONObject();
        root.put("method", "EXISTSTICKET");
        JSONObject data = new JSONObject();
        data.put("ticketCode", ticketCode);
        root.put("data", data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON:
     * {"method":"CREATEUSER","data":{"name":"String","surname":"String","username":"String","cf":"String","psw":"String"}}
     */
    public String createUser(String name, String surname, String username, String cf, String psw) {
        JSONObject root = new JSONObject();
        root.put("method", "CREATEUSER");
        JSONObject data = new JSONObject();
        data.put("name", name);
        data.put("surname", surname);
        data.put("username", username);
        data.put("cf", cf);
        data.put("psw", psw);

        root.put("data", data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON:
     * {"method":"MAKEFINE","data":{"cf":"String","amount":"Double"}}
     */
    public String makeFinePacket(String cf,double amount) {
        JSONObject root = new JSONObject();
        root.put("method", "MAKEFINE");
        JSONObject data = new JSONObject();
        data.put("cf", cf);
        data.put("amount", amount);
        root.put("data", data);
        return root.toJSONString();
    }
    
    /**
     * Struttura JSON:
     * {"data":"boolean"}
     */
    public String booleanPacket(boolean bool) {
        JSONObject root = new JSONObject();
        if(bool){
            root.put("data", "true");
        }else{
        	root.put("data", "false");
        }
        return root.toJSONString();
    }
    
    
    

    public String updateMachineStatus(int machineCode, double inkLevel, double paperLevel, boolean active) {
        JSONObject root = new JSONObject();
        root.put("method", "UPDATEMACHINESTATUS");
        JSONObject data = new JSONObject();
        data.put("machineCode", (double)machineCode);
        data.put("inkLevel", inkLevel);
        data.put("paperLevel", paperLevel);
        data.put("active", active);
        root.put("data", data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON:
     * {"method":"ADDTICKETSALE","data":{"username":"String","serial":"int","expiryDate":"String","ticketType":"String"}}
     */
	public String addTicketSalePacket(String username, long serial, String expiryDate,String ticketType) {
        JSONObject root = new JSONObject();
        root.put("method", "ADDTICKETSALE");
        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("serial", serial);
        data.put("expiryDate", expiryDate);
        data.put("ticketType", ticketType);
        root.put("data", data);
        return root.toJSONString();
	}

    /**
     * Struttura JSON:
     * {"method":"MYTICKETS","data":{"username":"String"}}
     */
	public String myTicketsPacket(String username) {
        JSONObject root = new JSONObject();
        root.put("method", "MYTICKETS");
        JSONObject data = new JSONObject();
        data.put("username", username);
        root.put("data", data);
		return root.toJSONString();
	}
		
	public String serverTestPacket() {
        JSONObject root = new JSONObject();
        root.put("method", "TEST");
        JSONObject data = new JSONObject();
        data.put("test", "Connection SUCCESS");
        root.put("data", data);
		return root.toJSONString();
	}
	
	
    //Struttura JSON ingresso : {"data":"boolean"}
	public boolean booleanParser(String data){
		System.out.println("CONVERSIONE: "+ data );
        JSONParser parser = new JSONParser();
        JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(data);
			return ((String)obj.get("data")).equals("true");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}
	
	public String parseTickets(JSONObject obj){
        try {
            StringBuilder sb = new StringBuilder("<br><br>TICKETS<br><br>----------<br>");
            JSONArray ticketArray = (JSONArray)obj.get("data");
            for(int i = 0; i < ticketArray.size();i++){
                JSONObject ticket = (JSONObject) ticketArray.get(i);
                sb.append("<br>       ID: ").append(ticket.get("id"));
                sb.append("<br>       Scadenza: ").append(DateOperations.getInstance().parse((String)ticket.get("expire")).toString());
                sb.append("<br>       Tipo: ").append(ticket.get("type"));
                sb.append("<br><br>----------<br>");
            }
            return sb.toString();
        } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "ERROR PARSING";
    }
   
}
