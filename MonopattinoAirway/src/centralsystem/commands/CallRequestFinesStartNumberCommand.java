package centralsystem.commands;

import centralsystem.CSystem;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.RequestFinesStartNumber;
import org.json.simple.JSONObject;


public class CallRequestFinesStartNumberCommand extends Command{

    public CallRequestFinesStartNumberCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data) {
        centralSystem.addMessageToLog("attempted request fines start number");
        System.out.println(data.toJSONString());
        String collectorUsername = (String)data.get(RequestFinesStartNumber.COLLECTORUSERNAME.toString());
        Long startNumber = centralSystem.countAllFinesMadeBy(collectorUsername);
        
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(), startNumber);
        return data.toJSONString();
    }
    
}
