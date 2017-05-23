package JSONSingleton;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ticketCollector.Fine;

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
     * Struttura JSON: {"method":"REQUESTCODES"}
     */
    public String requestCodesPacket(int numberOfCodes) {
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
    public String makeFinePacket(Fine f) {
        JSONObject root = new JSONObject();
        root.put("method", "MAKEFINE");
        JSONObject data = new JSONObject();
        data.put("cf", f.getCfCode());
        data.put("amount", f.getAmount());
        root.put("data", data);
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
