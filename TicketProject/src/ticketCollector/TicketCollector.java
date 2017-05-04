/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicketCollector;

import databaseadapter.DatabaseAdapter;
import java.util.Date;
import ticket.Ticket;

/**
 *
 * @author Simone
 */
public class TicketCollector {
    
    private DatabaseAdapter mongoDb;
    
    public TicketCollector(){
        mongoDb=new DatabaseAdapter();
    }
    
    /*
    public boolean verifyValidation(String idTicket){
        
         Ticket ticket = mongoDb.getTicketByCode(idTicket);
         return ticket.isValid();
         
    }
    */
    
    
    public boolean fine(String cf,Date today){
    
        if(mongoDb.addFine(new Fine(cf,today)))
            return true;
        throw new FineException("Non è stato possibile aggiungere la multa");
        
    }
    
    
}
