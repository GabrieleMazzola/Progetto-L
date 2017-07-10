package centralsystem.commands;

import centralsystem.CSystem;
import org.json.simple.JSONObject;

public abstract class Command {
    
    CSystem centralSystem;
    
    public Command(CSystem centralSystem){
        this.centralSystem = centralSystem;
    }
    
    public abstract String execute(JSONObject data);
    
}
