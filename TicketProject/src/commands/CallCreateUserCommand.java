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
public class CallCreateUserCommand extends Command{

    public CallCreateUserCommand(CSystem centralSystem) {
        super(centralSystem);
    }
           
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Attempted creating user...");
        String name = ((String) data.get("name"));
    	String surname = ((String) data.get("surname"));
    	String username = ((String) data.get("username"));
    	String cf = ((String) data.get("cf"));
    	String psw = ((String) data.get("psw"));
        boolean result = centralSystem.createUser(name,surname,username,cf,psw);
        data = new JSONObject();
        data.put("data",result);
        
        String notify;
        if(result) notify = "User " + username + " created succesfully";
        else notify = "Something went wrong";
        centralSystem.notifyChange(notify + ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }
    
}
