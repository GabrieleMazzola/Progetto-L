package centralsystem.commands;

import centralsystem.CSystem;
import jsonenumerations.CollectorLogin;
import jsonenumerations.JsonFields;
import org.json.simple.JSONObject;


public class CallCollectorLoginCommand extends Command {

    public CallCollectorLoginCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Attempted collector login...");
        
        String username = (String) data.get(CollectorLogin.USERNAME.toString());
        String psw = (String) data.get(CollectorLogin.PSW.toString());
        
        boolean result = centralSystem.collectorLogin(username, psw);
        
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(), result);
        
        String notify;
        if(result) 
            notify = "Login as " + username + " was successful";
        else 
            notify = "Login as " + username + " was not successful";
        
        centralSystem.addMessageToLog(notify);
        return data.toJSONString();
    }
}
