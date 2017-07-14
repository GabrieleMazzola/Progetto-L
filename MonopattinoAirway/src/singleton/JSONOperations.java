package singleton;

import enums.jsonenumerations.UserLogin;
import enums.jsonenumerations.TicketTypes;
import enums.jsonenumerations.MakeFine;
import enums.jsonenumerations.RequestFinesStartNumber;
import enums.jsonenumerations.ExistsTicket;
import enums.jsonenumerations.AddSale;
import enums.jsonenumerations.UpdateMachineStatus;
import enums.jsonenumerations.RequestCodes;
import enums.jsonenumerations.CreateUser;
import enums.jsonenumerations.CardPayment;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.CollectorLogin;
import enums.jsonenumerations.Email;
import items.Fine;
import items.Product;
import items.Sale;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ticketmachine.MachineStatus;

/**
 *
 * @author Zubeer
 */
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
     * {"method":"USERLOGIN","data":{"username":"String","psw":"String"}}
     * @param username
     * @param psw
     * @return 
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

      //{"data": [{ "type": "String","description": "String","cost": "double","duration": "double"}]}

    /**
     *
     * @param products
     * @return
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
     *
     * @return
     */
    public String requestTicketTypesPacket() {
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), TicketTypes.TICKETTYPES.toString());
        
        return root.toJSONString();
    }
    
    /**
     * Struttura JSON:
     * {"method":"COLLECTORLOGIN","data":{"username":"String","psw":"String"}}
     * @param username
     * @param psw
     * @return 
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
     * @param numberOfCodes
     * @return 
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
     * Struttura JSON: {"method":"CARDPAYMENT","data":{"cardNumber":"String","amount":"double"}}
     * @param cardNumber
     * @param amount
     * @return 
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
     * @param serialCode
     * @return 
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
     * {"method":"CREATEUSER","data":{"name":"String","surname":"String","cf":"String","username":"String","psw":"String", "email":"String"}}
     * @param name
     * @param surname
     * @param cf
     * @param username
     * @param email
     * @param psw
     * @return 
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
        data.put(CreateUser.EMAIL, email);

        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }

    /**
     * Struttura JSON:
     * {"method":"MAKEFINE","data":{"id":"long", "cf":"String", "amount":"Double"}}
     * @param f
     * @return 
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
     * Struttura JSON:
     * {"method":"UPDATEMACHINESTATUS","data":{"machineCode":"double", "inkLevel":"double", "paperLevel":"double", "active":"boolean", "ipAddress":"String"}}
     * @param status
     * @return 
     */
    public String updateMachineStatusPacket(MachineStatus status) {
        
        int machineCode = status.getMachineCode();
        double inkLevel = status.getInkLevel();
        double paperLevel = status.getPaperLevel();
        boolean active = status.isActive();
        String sellerIP = status.getSellerIp();
        
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), UpdateMachineStatus.UPDATEMACHINESTATUS.toString());
        JSONObject data = new JSONObject();
        data.put(UpdateMachineStatus.MACHINECODE.toString(), (double)machineCode);
        data.put(UpdateMachineStatus.INKLEVEL.toString(), inkLevel);
        data.put(UpdateMachineStatus.PAPERLEVEL.toString(), paperLevel);
        data.put(UpdateMachineStatus.ACTIVE.toString(), active);
        data.put(UpdateMachineStatus.SELLER_IP.toString(), sellerIP);
        
        root.put(JsonFields.DATA.toString(), data);
        return root.toJSONString();
    }
    
    /**
     * Strutture JSON:
     * {"method":"ADDSALE","data":{"saleDate":"Data", "serialCode":"long", "username":"String", "type":"String", "sellerMachineIp":"String"}}
     * @param sale
     * @return 
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

    /**
     *
     * @param collectorUsername
     * @return
     */
    public String requestFinesStartNumberPacket(String collectorUsername) {
        
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), RequestFinesStartNumber.REQUESTFINESSTARTNUMBER.toString());
        JSONObject data = new JSONObject();
        data.put(RequestFinesStartNumber.COLLECTORUSERNAME.toString(), collectorUsername);
        
        root.put(JsonFields.DATA.toString(), data);
        
        return root.toJSONString();
    }
    
    public String requestEmailTo(String email) {
        
        JSONObject root = new JSONObject();
        
        root.put(JsonFields.METHOD.toString(), Email.SENDEMAIL);
        JSONObject data = new JSONObject();
        data.put(Email.RECIEVER, email);
        
        root.put(JsonFields.DATA.toString(), data);
        
        return root.toJSONString();
    }
}
