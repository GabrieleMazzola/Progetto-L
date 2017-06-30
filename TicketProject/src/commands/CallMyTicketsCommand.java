/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import DateSingleton.DateOperations;
import centralsystem.CSystem;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ticket.Sale;
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
        Date expiryDate = new Date();
        try {
            expiryDate = DateOperations.getInstance().parse((String)data.get("expiryDate"));
        } catch (java.text.ParseException ex) {
            data = new JSONObject();
            data.put("data", false);
            return data.toString();
        }
        long serialCode = ((Long)data.get("serial"));
        String username =(String) data.get("username");
        String ticketType =(String) data.get("ticketType");
        
        centralSystem.addTicketSale(expiryDate,  serialCode,  username, ticketType);
        data = new JSONObject();
        data.put("data", true);
        return data.toString();
    }
}
