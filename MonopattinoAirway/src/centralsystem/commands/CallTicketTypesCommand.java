package centralsystem.commands;

import centralsystem.CSystem;
import org.json.simple.JSONObject;
import singleton.JSONOperations;

public class CallTicketTypesCommand extends Command{

    
    public CallTicketTypesCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data) {
        centralSystem.notifyChange("Attempted ticket types request...");
        
        return JSONOperations.getInstance().ticketTypesPacket(centralSystem.ticketTypes());
    }
    
}
