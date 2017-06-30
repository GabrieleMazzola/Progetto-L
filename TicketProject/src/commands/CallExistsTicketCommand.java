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
public class CallExistsTicketCommand extends Command {

    public CallExistsTicketCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Request exists ticket...");
        String ticketCode = (String) data.get("ticketCode");
        boolean result = centralSystem.existsTicket(Integer.parseInt(ticketCode));
        data = new JSONObject();
        data.put("data", result);
        
        String notify;
        if(result) notify = "Ticket " + ticketCode + " found";
        else notify = "Ticket " + ticketCode + " not found";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }
}
