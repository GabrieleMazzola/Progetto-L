package centralsystem.commands;

import centralsystem.CSystem;
import org.json.simple.JSONObject;

public class CallTicketTypesCommand extends Command{

    
    public CallTicketTypesCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data) {
        centralSystem.notifyChange("Attempted ticket types request...");
        
        return centralSystem.ticketTypes();
    }
    
}
