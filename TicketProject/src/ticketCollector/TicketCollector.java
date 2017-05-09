package TicketCollector;

import databaseadapter.DatabaseAdapter;
import java.util.Date;

public class TicketCollector {
    
    private DatabaseAdapter mongoDb;
    
    public TicketCollector(){
        mongoDb=new DatabaseAdapter();
    }
    
    public boolean fine(String cf,Date today){
    
        if(mongoDb.addFine(new Fine(cf,today)))
            return true;
        throw new FineException("Non è stato possibile aggiungere la multa");
        
    }
    
    
}
