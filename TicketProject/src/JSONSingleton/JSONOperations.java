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
    public String existsTicketPacket(int ticketCode) {
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

    public String updateMachineStatusPacket(int machineCode, double inkLevel, double paperLevel, boolean active, String ipAddress) {

        JSONObject root = new JSONObject();
        root.put("method", "UPDATEMACHINESTATUS");
        JSONObject data = new JSONObject();
        data.put("machineCode", (double)machineCode);
        data.put("inkLevel", inkLevel);
        data.put("paperLevel", paperLevel);
        data.put("active", active);
        data.put("ipAddress", ipAddress);
        root.put("data", data);
        return root.toJSONString();
    }
}
