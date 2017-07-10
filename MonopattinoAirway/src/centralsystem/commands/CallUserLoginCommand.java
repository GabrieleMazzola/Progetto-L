package centralsystem.commands;

import centralsystem.CSystem;
import jsonenumerations.JsonFields;
import jsonenumerations.UserLogin;
import org.json.simple.JSONObject;


public class CallUserLoginCommand extends Command{

    public CallUserLoginCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Attempted user login...");
        
        String username = (String) data.get(UserLogin.USERNAME.toString());
        String psw = (String) data.get(UserLogin.PSW.toString());
        
        boolean result = centralSystem.userLogin(username, psw);
        
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
