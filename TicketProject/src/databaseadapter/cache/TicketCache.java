package databaseadapter.cache;

import java.util.HashSet;
import java.util.Set;
import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public class TicketCache implements CacheInterface{
    private Set<Ticket> cache;
    
    public TicketCache() {
        cache = new HashSet<>();
    }
    
    @Override
    public void add(Object arg) {
        if(arg instanceof Ticket)
            cache.add((Ticket)arg);
    }
    
    @Override
    public Ticket get(String code) {
        for(Ticket t : cache) {
            if(t.getCode().equals(code)) return t;
        }
        return null;
    }
}
