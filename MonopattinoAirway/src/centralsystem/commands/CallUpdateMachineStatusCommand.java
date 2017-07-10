package centralsystem.commands;

import centralsystem.CSystem;
import jsonenumerations.JsonFields;
import jsonenumerations.UpdateMachineStatus;
import org.json.simple.JSONObject;
import ticketmachine.MachineStatus;

public class CallUpdateMachineStatusCommand extends Command {

    
    public CallUpdateMachineStatusCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
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
