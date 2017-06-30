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
public class CallRequestCodesCommand extends Command {

    public CallRequestCodesCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Requesting new codes...");
        long initialCodesNumber = centralSystem.requestCodes(((Long)data.get("numberOfCodes")));
        data.put("data", initialCodesNumber);
        
        String notify = "Codes requested to the Central System";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }
}
