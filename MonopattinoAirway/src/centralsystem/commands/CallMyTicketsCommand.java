package centralsystem.commands;

import centralsystem.CSystem;
import items.Sale;
import java.util.Set;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.MyTickets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import singleton.DateOperations;


public class CallMyTicketsCommand extends Command {

    public CallMyTicketsCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    /**
     * Prende un pacchetto JSON in ingresso,e estrae l'username dell'utente che effettua la richiesta.
     * Chiama il metodo del CentralSystem per estrarre tutte le Sale associate a quell'utente. 
     * Viene memorizzato un JSONArray che contiene tutte le informazioni delle sale ottenute così, 
     * e quest'ultimo viene messo in un pacchetto JSON.
     * @param data
     * @return Una stringa che rappresenta il JSONArray delle Sale     
     */
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Request my tickets...");
        
       
        String username = (String)data.get(MyTickets.USERNAME.toString());
        data = new JSONObject();
        Set<Sale> saleList =  centralSystem.getSalesByUsername(username);
        JSONArray jsonList = new JSONArray();
 
        for (Sale sale : saleList) {
            JSONObject jsonSale = new JSONObject();  
            
            jsonSale.put(MyTickets.SERIALCODE.toString(),sale.getSerialCode()); 
            jsonSale.put(MyTickets.SALEDATE.toString(), DateOperations.getInstance().toString(sale.getSaleDate()));
            jsonSale.put(MyTickets.TYPE.toString(), sale.getType());
            jsonSale.put(MyTickets.SELLERMACHINEIP.toString(), sale.getSellerMachineIp());
            jsonSale.put(MyTickets.USERNAME.toString(), sale.getUsername());
            
            jsonList.add(jsonSale);
            
        }
        data.put(JsonFields.DATA.toString(), jsonList);
        return data.toString();

    }
}