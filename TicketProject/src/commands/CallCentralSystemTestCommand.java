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
public class CallCentralSystemTestCommand extends Command{

    public CallCentralSystemTestCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
       centralSystem.notifyChange("Attempted test...");
       String result = centralSystem.centralSystemTEST((String) data.get("test"));
       data = new JSONObject();
       data.put("data", result);

       String notify;
       if(result.equals("test")) notify = "Test successful";
       else notify = "Something went wrong";
       centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());

       return data.toJSONString();
    }
}
