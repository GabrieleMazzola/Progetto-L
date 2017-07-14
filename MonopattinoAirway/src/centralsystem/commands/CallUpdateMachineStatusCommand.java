package centralsystem.commands;

import centralsystem.CSystem;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.UpdateMachineStatus;
import org.json.simple.JSONObject;
import ticketmachine.MachineStatus;

public class CallUpdateMachineStatusCommand extends Command {

    
    public CallUpdateMachineStatusCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    /**
     * Prende un pacchetto JSON in ingresso, estrae lil codice della TicketMachine, il livello di inchiostro,
     * il livello di carta, se è attiva la TicketMachine, e l'ip. Crea un nuovo MachineStatus con le
     * informazioni estratte.
     * Chiama il metodo del CentralSystem per caricare il MachineStatus nella mappa dei MachineStatus. 
     * Viene creato un JsonObject che contiene true.
     * @param data
     * @return Una stringa che contiene "true".
     */
    @Override
    public String execute(JSONObject data){
        
        int machineCode = ((Double)data.get(UpdateMachineStatus.MACHINECODE.toString())).intValue();
        double inkLevel = (double) data.get(UpdateMachineStatus.INKLEVEL.toString());
        double paperLevel = (double) data.get(UpdateMachineStatus.PAPERLEVEL.toString());
        boolean active = (boolean) data.get(UpdateMachineStatus.ACTIVE.toString());
        String sellerMachineIp = (String)data.get(UpdateMachineStatus.SELLER_IP.toString());
        
        MachineStatus status = new MachineStatus(machineCode, sellerMachineIp, inkLevel, paperLevel, active);
        centralSystem.updateMachineStatus(status);
        data = new JSONObject();
        data.put(JsonFields.DATA.toString(), true);
                
        return data.toString();
    }
}
