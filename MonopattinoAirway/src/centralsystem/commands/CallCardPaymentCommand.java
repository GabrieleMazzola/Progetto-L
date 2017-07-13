package centralsystem.commands;

import centralsystem.CSystem;
import enums.jsonenumerations.CardPayment;
import enums.jsonenumerations.JsonFields;
import org.json.simple.JSONObject;


public class CallCardPaymentCommand extends Command {

    public CallCardPaymentCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    /**
     * Prende un pacchetto JSON in ingresso, estrae numero carta di credito, e quantità da pagare. 
     * Chiama il metodo del CentralSystem per effettuare il pagamento da carta di credito. 
     * Viene memorizzato il valore di ritorno del metodo del CentralSystem, e 
     * viene messo in un pacchetto JSON.
     * @param data
     * @return Una stringa che rappresenta il valore di ritorno del metodo cardPayment del CentralSystem
     */
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
