package centralsystem.commands;

import centralsystem.CSystem;
import items.Fine;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.MakeFine;
import org.json.simple.JSONObject;


public class CallMakeFineCommand extends Command {

    public CallMakeFineCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    /**
     * Prende un pacchetto JSON in ingresso, estrae id (TODO), codice fiscale del passeggero,
     * username del controllore e quantità da pagare. Crea una Fine e chiama il metodo del
     * CentralSystem per memorizzarla nel database. Viene memorizzato il valore di ritorno del
     * metodo del CentralSystem, e viene messo in un pacchetto JSON
     * @param data
     * @return Una stringa che rappresenta il valore di ritorno del metodo addSale del CentralSystem 
     */
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Attempted making new fine...");
        
        String id = (String)data.get(MakeFine.ID.toString());
        String cf = (String)data.get(MakeFine.CF.toString());
        String collectorUsername = (String)data.get(MakeFine.COLLECTORUSERNAME.toString());
        double amount = (Double)data.get(MakeFine.AMOUNT.toString());
        
        //TODO cambiare la creazione di FINE nei json e in ogni dove
        Fine fine = new Fine(id, cf, amount,collectorUsername);
        boolean result = centralSystem.makeFine(fine);
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(), result);

        String notify;
        if(result) 
            notify = "Fine of " + amount + "to " + cf + " was successfully added";
        else 
            notify = "Could not add the new fine";
        
        centralSystem.addMessageToLog(notify);
        return data.toJSONString();
    }
}
