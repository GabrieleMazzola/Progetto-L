package JSONSingleton;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Manuele
 */
public class Decoder {
    
    /**
     * Viene interpretata la stringa in ingresso. In base al suo valore viene
     * fatta un'azione diversa
     * @param inputData
     * @return 
     */
    public String decodeRead(String toDecode) throws ParseException{
        StringBuilder decoded = new StringBuilder();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(toDecode);
        
        String method = ((String) obj.get("method")).trim().toUpperCase();
        decoded.append(method).append("\t");

        switch (method) {

            case "TEST":
                decoded.append(callCentralSystemTEST((JSONObject) obj.get("data")));
                break;
            case "CREATEUSER":
                decoded.append(callCreateUser((JSONObject) obj.get("data")));
                break;
            case "MAKEFINE":
                decoded.append(callMakeFine((JSONObject) obj.get("data")));
                break;
            case "COLLECTORLOGIN":
                decoded.append(callCollectorLogin((JSONObject) obj.get("data")));
                break;
            case "USERLOGIN":
                decoded.append(callUserLogin((JSONObject) obj.get("data")));
                break;
            case "CARDPAYMENT":
                decoded.append(callCardPayment((JSONObject) obj.get("data")));
                break;
            case "EXISTSTICKET":
                decoded.append(callexistsTicket((JSONObject) obj.get("data")));
                break;
            case "REQUESTCODES":
                decoded.append(callRequestCodes());
                break;    
            case "UPDATEMACHINESTATUS":
                decoded.append(callupdateMachineStatus((JSONObject) obj.get("data")));
                break;
            default:
                throw new AssertionError();
            }
        return decoded.toString();
    }
    
    private String callCreateUser(JSONObject data) {
        StringBuilder str = new StringBuilder();
        
    	String name = ((String) data.get("name"));
    	String surname = ((String) data.get("surname"));
    	String username = ((String) data.get("username"));
    	String cf = ((String) data.get("cf"));
    	String psw = ((String) data.get("psw"));
        
        str.append(name).append("\t").append(surname).append("\t")
           .append(username).append("\t").append(cf).append("\t")
           .append(psw);
        
        return str.toString();
	}

    private String callCardPayment(JSONObject data) {
        StringBuilder str = new StringBuilder();
        
        String cardNumber = (String) data.get("cardNumber");
        double amount = (double) data.get("amount");
        
        str.append(cardNumber).append("\t").append(amount + "");
        
        return str.toString();
    }
    
    private String callexistsTicket(JSONObject data) {
        String ticketCode = (String) data.get("ticketCode");
        
        return ticketCode;

    }

    private String callRequestCodes() {
        return "request codes";
    }

    private String callCentralSystemTEST(JSONObject data) {
        String test = (String) data.get("test");

        return test;
    }

    private String callCollectorLogin(JSONObject data) {
        StringBuilder str = new StringBuilder();
        
        String username = (String) data.get("username");
        String psw = (String) data.get("psw");
        
        str.append(username).append("\t").append(psw);

        return str.toString();
    }

    private String callUserLogin(JSONObject data) {
        StringBuilder str = new StringBuilder();
        
        String username = (String) data.get("username");
        String psw = (String) data.get("psw");
        
        str.append(username).append("\t").append(psw);

        return str.toString();
    }

    private String callMakeFine(JSONObject data) {
        StringBuilder str = new StringBuilder();
        
        String cf = (String) data.get("cf");
        double amount = (double) data.get("amount");
        
        str.append(cf).append("\t").append(amount + "");

        return str.toString();
    } 
    private String callupdateMachineStatus(JSONObject data) {
        StringBuilder str = new StringBuilder();
        
        double machineCode = (double) data.get("machineCode");
        double inkLvl = (double) data.get("inkLevel");
        double paperLvl = (double) data.get("paperLevel");
        boolean active = (boolean) data.get("active");
        
        str.append(machineCode).append("\t").append(inkLvl).append("\t")
           .append(paperLvl).append("\t").append(active);

        return str.toString();
    }
}
