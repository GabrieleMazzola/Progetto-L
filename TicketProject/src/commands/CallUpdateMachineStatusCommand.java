/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import centralsystem.CSystem;
import java.net.Socket;
import org.json.simple.JSONObject;

/**
 *
 * @author Francesco
 */
public class CallUpdateMachineStatusCommand extends Command {

    private Socket clientSocket;
    
    public CallUpdateMachineStatusCommand(CSystem centralSystem,Socket clientSocket) {
        super(centralSystem);
        this.clientSocket = clientSocket;
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.updateMachineStatus(((Double)data.get("machineCode")).intValue(), (double) data.get("inkLevel"), (double) data.get("paperLevel"), (boolean) data.get("active"), clientSocket.getRemoteSocketAddress().toString());
        data = new JSONObject();
        data.put("data", true);
        return data.toString();
    }
}
