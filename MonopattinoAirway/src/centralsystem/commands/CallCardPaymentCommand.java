package centralsystem.commands;

import centralsystem.CSystem;
import enums.jsonenumerations.CardPayment;
import enums.jsonenumerations.JsonFields;
import org.json.simple.JSONObject;


public class CallCardPaymentCommand extends Command {

    public CallCardPaymentCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Attempted card payment...");
        
        String cardNumber = (String) data.get(CardPayment.CARD_NUMBER.toString());
        double amount = (double) data.get(CardPayment.AMOUNT.toString());
        
        boolean result = centralSystem.cardPayment(cardNumber, amount);
        
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(),result);
        
        String notify;
        if(result) 
            notify = "Payment of " + amount + " from " + cardNumber + " was successful";
        else 
            notify = "Payment of " + amount + " from " + cardNumber + " was not accepted";
        
        centralSystem.addMessageToLog(notify);
        return data.toJSONString();
    }    
}
