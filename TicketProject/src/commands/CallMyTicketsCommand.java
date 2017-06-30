/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import centralsystem.CSystem;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ticket.Ticket;

/**
 *
 * @author Francesco
 */
public class CallMyTicketsCommand extends Command {

    public CallMyTicketsCommand(CSystem centralSystem) {
        super(centralSystem);
    }
    
    @Override
    public String execute(JSONObject data){
        centralSystem.notifyChange("Requesting user codes...");
        String username = (String)data.get("username");
        data = new JSONObject();
        Set<Ticket> listaBiglietti =  centralSystem.getTicketsByUsername(username);
        JSONArray JList = new JSONArray();
 
        for (Ticket ticket : listaBiglietti) {
            JSONObject jTicket = new JSONObject();    
            jTicket.put("id",ticket.getCode());
            //jTicket.put("expire",operator.toString(ticket.getExpireTime()));
            jTicket.put("type", ticket.getType());
            JList.add(jTicket);
            
        }
        data.put("data", JList);
        return data.toString();
    }
}
