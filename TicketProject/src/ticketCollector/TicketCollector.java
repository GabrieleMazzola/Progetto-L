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
    
    private final DatabaseAdapter mongoDb;
    
    public TicketCollector(DatabaseAdapter mongoDB){
        this.mongoDb=mongoDB;
    }
    
    public boolean verifyValidation(String idTicket){
        
         
         TicketDB t = mongoDb.getTicketByCode(idTicket);
         if(t == null){
             throw new FineException("Non esiste un biglietto con questo codice: " + idTicket);
         }     
         return t.isActive();
         
    }
      
    
    public boolean fine(String cf,Date today){
    
        if(mongoDb.addFine(new Fine(cf,today)))
            return true;
        throw new FineException("Non è stato possibile aggiungere la multa: "+ cf);
        
    }
    
    
}
