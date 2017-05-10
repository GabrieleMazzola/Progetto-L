/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketCollector;

import databaseadapter.DatabaseAdapter;
import databaseadapter.TicketDB;
import java.util.Date;

/**
 *
 * @author Simone
 */
public class TicketCollector {
    private final int cod;
    private String username, psw;
    private final StubCollector stub;
    
    public TicketCollector(int cod, String username, String psw, String ipAddress){
        this.cod = cod;
        this.username = username;
        this.psw = psw;
        stub = new StubCollector(ipAddress, 5000);
    }
    
    public boolean verifyValidation(String idTicket){
         if(stub.login(username, psw)) {
             
            if(stub.existsTicket(idTicket))
                return true;
             //return stub.isValid(idTicket); //Da aggiungere allo stub
            return false;
         }
         else return false;
         //TODO
    }
      
    
    public boolean fine(String cf, double amount){
        if(stub.login(username, psw)) {
            
            Fine fine = new Fine(cf, amount);
            stub.makeFine(fine);
            return true;
        }
        return false;
    }
}
