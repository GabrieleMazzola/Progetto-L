/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import centralsystem.CSystem;
import java.util.Calendar;
import org.json.simple.JSONObject;

/**
 *
 * @author Francesco
 */
public class CallCardPayementCommand extends Command {

    public CallCardPayementCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Attempted card payment...");
        String cardNumber = (String) data.get("cardNumber");
        double amount = (double) data.get("amount");
        boolean result = centralSystem.cardPayment(cardNumber, amount);
        data = new JSONObject();
        data.put("data",result);
        
        String notify;
        if(result) notify = "Payment of " + amount + " from " + cardNumber + " was successful";
        else notify = "Something went wrong";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }    
}
