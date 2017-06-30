/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import DateSingleton.DateOperations;
import centralsystem.CSystem;
import centralsystem.LogCS;
import java.util.Date;
import org.json.simple.JSONObject;

/**
 *
 * @author Francesco
 */
public class CallAddTicketSaleCommand extends Command{

    DateOperations operator;
    
    public CallAddTicketSaleCommand(CSystem centralSystem) {
        super(centralSystem);
        this.operator = DateOperations.getInstance();
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Attempted selling ticket...");
        Date expiryDate = new Date();
        try {
            expiryDate = operator.parse((String)data.get("expiryDate"));
        } catch (java.text.ParseException ex) {
            LogCS.getInstance().print("err", ex.toString());
            data = new JSONObject();
            data.put("data", false);
            return data.toString();
        }
        long serialCode = ((Long)data.get("serial"));
        String username =(String) data.get("username");
        String ticketType =(String) data.get("ticketType");
        
        centralSystem.addTicketSale(expiryDate,  serialCode,  username, ticketType);
        data = new JSONObject();
        data.put("data", true);
        return data.toString();
    }
}
