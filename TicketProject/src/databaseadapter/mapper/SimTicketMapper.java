package databaseadapter.mapper;

import java.util.HashSet;
import java.util.Set;
import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public class SimTicketMapper implements MapperInterface{
    private Set<Ticket> tickets;
    
    public SimTicketMapper() {
        tickets = new HashSet<>();
    }

    @Override
    public Object get(String code) {
        for(Ticket t : tickets) {
            if(t.getCode().equals(code)) return t;
        }
        return null;
    }

    @Override
    public boolean save(Object arg) {
        if(arg instanceof Ticket)
            return tickets.add((Ticket)arg);
        else return false;
    }

    @Override
    public int getCacheSize() {
        return tickets.size();
    }
    
}
