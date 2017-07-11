package centralsystem.commands;

import centralsystem.CSystem;
import enums.jsonenumerations.ExistsTicket;
import enums.jsonenumerations.JsonFields;
import org.json.simple.JSONObject;


public class CallExistsTicketCommand extends Command {

    public CallExistsTicketCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Request exists ticket...");
        
        Long serialCode = (Long) data.get(ExistsTicket.SERIALCODE.toString());
        
        boolean result = centralSystem.existsTicket(serialCode);
        
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(), result);
        
        String notify;
        if(result) 
            notify = "Ticket " + serialCode + " found";
        else 
            notify = "Ticket " + serialCode + " not found";
        
        centralSystem.addMessageToLog(notify);
        return data.toJSONString();
    }
}
