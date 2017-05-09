package JSONSingleton;

import org.json.simple.JSONObject;

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

    public String loginPacket(String username, String psw) {
        //{"method":"LOGIN","data":{"username":"String","psw":"String"}}

        JSONObject root = new JSONObject();
        root.put("method", "LOGIN");
        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("psw", psw);
        root.put("data", data);

        return root.toJSONString();
    }

    public String requestCodesPacket() {
        //{"method":"REQUESTCODES"}

        JSONObject root = new JSONObject();
        root.put("method", "REQUESTCODES");
        return root.toJSONString();
    }

    public String cardPaymentPacket(String cardNumber) {
        //{"method":"CARDPAYMENT","data":{"cardNumber":"String"}}

        JSONObject root = new JSONObject();
        root.put("method", "CARDPAYMENT");
        JSONObject data = new JSONObject();
        data.put("cardNumber", cardNumber);
        root.put("data", data);
        return root.toJSONString();
    }

    public String existsTicketPacket(String ticketCode) {
        //{"method":"EXISTSTICKET","data":{"ticketCode":"String"}}
        
        JSONObject root = new JSONObject();
        root.put("method", "EXISTSTICKET");
        JSONObject data = new JSONObject();
        data.put("ticketCode", ticketCode);
        root.put("data", data);

        return root.toJSONString();
    }

}
