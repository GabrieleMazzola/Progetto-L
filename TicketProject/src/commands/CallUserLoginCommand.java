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
public class CallUserLoginCommand extends Command{

    public CallUserLoginCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Attempted user login...");
        String username = (String) data.get("username");
        String psw = (String) data.get("psw");
        boolean result = centralSystem.userLogin(username, psw);
        data = new JSONObject();
        data.put("data", result);
        
        String notify;
        if(result) notify = "Login as " + username + " was successful";
        else notify = "Login as " + username + " was not successful";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());

        return data.toJSONString();
    }
}
