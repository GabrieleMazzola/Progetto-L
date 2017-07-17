package centralsystem.commands;

import centralsystem.CSystem;
import items.Sale;
import java.util.Set;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.GetSalesByUsername;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import singleton.DateOperations;

public class CallGetValidSalesByUsernameCommand extends Command{
    
    public CallGetValidSalesByUsernameCommand(CSystem centralSystem) {
        super(centralSystem);
    }

    /**
     * Prende un pacchetto JSON in ingresso,e estrae l'username dell'utente che effettua la richiesta.
     * Chiama il metodo del CentralSystem per estrarre tutte le Sale associate a quell'utente,
     * e con data di scadenza non ancora passata. 
     * Viene memorizzato un JSONArray che contiene tutte le informazioni delle sale ottenute così, 
     * e quest'ultimo viene messo in un pacchetto JSON.
     * @param data
     * @return Una stringa che rappresenta il JSONArray delle Sale     
     */
    @Override
    public String execute(JSONObject data) {
        centralSystem.addMessageToLog("Request my tickets...");
        
       
        String username = (String)data.get(GetSalesByUsername.USERNAME.toString());
        data = new JSONObject();
        Set<Sale> saleList =  centralSystem.getValidSalesByUsername(username);
        JSONArray jsonList = new JSONArray();
 
        for (Sale sale : saleList) {
            JSONObject jsonSale = new JSONObject();  
            
            jsonSale.put(GetSalesByUsername.SERIALCODE.toString(),sale.getSerialCode()); 
            jsonSale.put(GetSalesByUsername.SALEDATE.toString(), DateOperations.getInstance().toString(sale.getSaleDate()));
            jsonSale.put(GetSalesByUsername.TYPE.toString(), sale.getType());
            jsonSale.put(GetSalesByUsername.SELLERMACHINEIP.toString(), sale.getSellerMachineIp());
            jsonSale.put(GetSalesByUsername.USERNAME.toString(), sale.getUsername());
            
            jsonList.add(jsonSale);
            
        }
        data.put(JsonFields.DATA.toString(), jsonList);
        return data.toString();
    }
    
}
