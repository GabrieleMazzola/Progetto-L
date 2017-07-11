package centralsystem.commands;

import centralsystem.CSystem;
import email.EmailDispatcher;
import enums.jsonenumerations.CreateUser;
import enums.jsonenumerations.JsonFields;
import org.json.simple.JSONObject;


public class CallCreateUserCommand extends Command{
    private EmailDispatcher emailDispatcher;
    
    public CallCreateUserCommand(CSystem centralSystem) {
        super(centralSystem);
        emailDispatcher = new EmailDispatcher();
    }
           
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Attempted creating user...");
        
        String name = ((String) data.get(CreateUser.NAME.toString()));
    	String surname = ((String) data.get(CreateUser.SURNAME.toString()));
        String cf = ((String) data.get(CreateUser.CF.toString()));
    	String username = ((String) data.get(CreateUser.USERNAME.toString()));
    	String psw = ((String) data.get(CreateUser.PSW.toString()));
        String email = (String) data.get(CreateUser.EMAIL.toString());
        
        boolean result = centralSystem.createUser(name,surname,cf,username,psw, email);
        
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(),result);
        
        String notify;
        if(result) {
            notify = "User " + username + " created succesfully";
            emailDispatcher.sendEmail(buildEmailMessage(username, psw), email);
        }
        else 
            notify = "Something went wrong. Could not create user with username " + username;
        
        
        centralSystem.addMessageToLog(notify);
        return data.toJSONString();
    }

    private String buildEmailMessage(String username, String psw) {
        StringBuilder str = new StringBuilder();
        
        str.append("Welcome to MonopattinoAirway!\nYou can now start buying tickets straight from our app! Your account details are:\n")
           .append("\nUsername: ").append(username)
           .append("\nPassword: ").append(psw)
           .append("\n\nKeep this email, it contains precius informations!\nHave a nice trip!");
        
        return str.toString();
    }
    
}
