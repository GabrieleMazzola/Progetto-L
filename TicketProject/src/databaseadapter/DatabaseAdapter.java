
package databaseadapter;

import java.util.*;

/**
 *
 * @author Gabriele
 */
public class DatabaseAdapter {
    
    private Set<UserDB> users;
    private Set<TicketDB> tickets;
    private Set<FineDB> fines;      //Verrà eliminata una volta che Simo invierà la sua parte del codice

    public DatabaseAdapter() {
        this.tickets = new HashSet<>();
        this.users = new HashSet<>();
        addUser("ADMIN", "ADMIN", "ADMIN");
        this.fines = new HashSet<>();
    }

    public boolean addUser(String name, String surname, String cf) {
        return users.add(new UserDB(name, surname, cf));
    }
    
    public boolean addTicket(TicketDB ticket){
        return tickets.add(ticket);
    }
    
    private TicketDB getTicketByCode(String ticketCode){
        for(TicketDB t: tickets){
            if(t.getCode().trim().equalsIgnoreCase(ticketCode.trim())){
                return t;
            }
        }
        return null;
    }
    
    public boolean existsTicket(String ticketCode){
        return(getTicketByCode(ticketCode) != null);
    }
    
    
    public boolean isValid(String ticketCode){
        TicketDB t = getTicketByCode(ticketCode);
        if(t != null){
            return t.isActive();
        }
        return false;
    }
    
    public boolean checkUser(String cf){
        for(UserDB u : users){
            if(u.getCF().trim().equalsIgnoreCase(cf.trim())) return true;
        }
        return false;
    }
 
    public boolean activateTicket(String ticketCode){
        TicketDB t;
        if((t = getTicketByCode(ticketCode)) != null){
            t.activate();
            return true;
        }
        return false;
    }
    
    public boolean addFine(FineDB fine) {
        return fines.add(fine);
    }
    
    public Set<FineDB> getFineByCFCode(String cfCode) {
        Set<FineDB> fittingFines = new HashSet<>();
        
        for(FineDB fine : fines) {
            if(fine.getCfCode().equals(cfCode)) fittingFines.add(fine);
        }
        
        return fittingFines;
    }
    
    public void printUsers(){
        System.out.println("\nUSERS:");
        for(UserDB u : users){
            System.out.println(u.toString());
        }
    }
    
    public void printTickets(){
        System.out.println("\nTICKETS:");
        for(TicketDB t : tickets){
            System.out.println(t.toString());
        }
    }
    
    
}
