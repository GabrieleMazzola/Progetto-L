package centralsystem.commands;

import centralsystem.CSystem;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.RequestFinesStartNumber;
import org.json.simple.JSONObject;


public class CallRequestFinesStartNumberCommand extends Command{

    public CallRequestFinesStartNumberCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    /**
     * Prende un pacchetto JSON in ingresso,e estrae l'username del Collector.
     * Chiama il metodo del CentralSystem per estrarre dal database l'indice di multe a cui è arrivato
     * il controllore con l'username estratto dal JSON. 
     * Viene memorizzato un JSONArray che contiene il valore di ritorno del metodo del CentralSystem, 
     * e quest'ultimo viene messo in un pacchetto JSON.
     * @param data
     * @return Una stringa che rappresenta il valore di ritorno del metodo countAllFinesMadeBy del CSystem
     */
    @Override
    public String execute(JSONObject data) {
        centralSystem.addMessageToLog("attempted request fines start number");
        System.out.println(data.toJSONString());
        String collectorUsername = (String)data.get(RequestFinesStartNumber.COLLECTORUSERNAME.toString());
        Long startNumber = centralSystem.countAllFinesMadeBy(collectorUsername);
        
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(), startNumber);
        return data.toJSONString();
    }
    
}
