/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import centralsystem.CSystem;
import java.util.Calendar;
import org.json.simple.JSONObject;
import ticketCollector.Fine;

/**
 *
 * @author Francesco
 */
public class CallMakeFineCommand extends Command {

    public CallMakeFineCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Attempted making new fine...");
        long id = (Long)data.get("id");
       String cf = (String)data.get("cf");
       double amount = (Double)data.get("amount");
       Fine fine = new Fine(id, cf, amount);
       boolean result = centralSystem.makeFine(fine);
       data = new JSONObject();
       data.put("data", result);

       String notify;
       if(result) notify = "Fine of " + amount + "to " + cf + " was successfully added";
       else notify = "Could not add the new fine";
       centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());

       return data.toJSONString();
    }
}
