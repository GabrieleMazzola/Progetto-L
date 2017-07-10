package centralsystem.commands;

import centralsystem.CSystem;
import jsonenumerations.JsonFields;
import jsonenumerations.RequestCodes;
import org.json.simple.JSONObject;


public class CallRequestCodesCommand extends Command {

    public CallRequestCodesCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Requesting new codes...");
        
        long initialCodesNumber = centralSystem.requestCodes(((Long)data.get(RequestCodes.NUMBEROFCODES.toString())));
        
        data.put(JsonFields.DATA.toString(), initialCodesNumber);
        
        String notify = "New codes sent";
        
        centralSystem.addMessageToLog(notify);
        return data.toJSONString();
    }
}
