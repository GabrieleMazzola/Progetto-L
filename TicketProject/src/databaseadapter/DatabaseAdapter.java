package databaseadapter;

import java.util.*;
import ticketCollector.Fine;


public class DatabaseAdapter {
    private Set<UserDB> users;
    private Set<TicketDB> tickets;   //biglietti venduti
    private Set<Fine> fines;
    private Set<CollectorDB> collectors;

    public DatabaseAdapter() {
        this.tickets = new HashSet<>();
        this.users = new HashSet<>();
        addUser("ADMIN", "ADMIN", "ADMIN", "ADMIN","ADMIN");
        this.fines = new HashSet<>();
        collectors = new HashSet<>();
    }
    
    /**
     * Aggiunge un utente al database. I campi dell'utente da aggiungere sono
     * specificati come parametri della funzione. Chiamato quando un utente
     * si registra
     * @param name
     * @param surname
     * @param username
     * @param cf
     * @param psw
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addUser(String name, String surname,String username, String cf,String psw) {
        return users.add(new UserDB(name, surname, username,cf, psw));
    }
    
    /**
     * Aggiunge un biglietto al database
     * @param ticket
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addTicket(TicketDB ticket){
        return tickets.add(ticket);
    }
    public boolean addTicket(Date expiryDate, int serialCode, String username, String ticketType){
            tickets.add(new TicketDB(expiryDate,serialCode, username, ticketType));
        return true;
    }
    
    /**
     * Aggiunge un controllore al database. I campi del controllore da aggiungere sono
     * specificati come parametri della funzione
     * @param name
     * @param surname
     * @param cf
     * @param psw
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addCollector(String name, String surname,String username, String cf,String psw) {
        return collectors.add(new CollectorDB(name, surname,username, cf, psw));
    }
    
    /**
     * Cerca nel database un biglietto tramite il suo codice. Se viene trovato,
     * viene restituito il biglietto.
     * @param ticketSerial
     * @return Il TicketDB se esiste un biglietto con codice ticketCode, null
     * altrimenti
     */
    public TicketDB getTicketByCode(int ticketSerial){
        for(TicketDB t: tickets){
            if(t.getCode() == ticketSerial){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Cerca nel database se esiste un biglietto con codice indicato. Per farlo
     * viene chiamato il metodo getTicketByCode
     * @param ticketSerial
     * @return Vero se getTicketByCOde non restituisce null, falso altrimenti
     */
    public boolean existsTicket(int ticketSerial){
        return(getTicketByCode(ticketSerial) != null);
    }
    
    /**
     * Cerca nel database il biglietto con codice indicato e verifica la sua validità.
     * Per farlo viene chiamato il metodo getTicketByCode
     * @param ticketSerial
     * @return Vero se getTicketByCode non ritorna null e se il biglietto è attivo
     */
    public boolean isValid(int ticketSerial){
        TicketDB t = getTicketByCode(ticketSerial);
        if(t != null){
            return t.isActive();
        }
        return false;
    }
    
    /**
     * Ricerca nel database un utente con codice fiscale cf
     * @param username
     * @return Vero se esiste un utente con codice fiscale cf nel database
     */
    public boolean checkUser(String username){
        for(UserDB u : users){
            if(u.getUsername().trim().equalsIgnoreCase(username.trim())) return true;
        }
        return false;
    }
 
    /**
     * Rende attivo il biglietto indicato tramite codice. Per farlo viene chiamato
     * il metodo getTicketByCode
     * @param ticketSerial
     * @return Vero se l'attivazione va a buon fine (ossia se getTicketByCode non 
     * restituisce null), falso altrimenti
     */
    public boolean activateTicket(int ticketSerial){
        TicketDB t;
        if((t = getTicketByCode(ticketSerial)) != null){
            t.activate();
            return true;
        }
        return false;
    }
    
    /**
     * Aggiunge al database la multa specificata
     * @param fine
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addFine(Fine fine) {
        return fines.add(fine);
    }
    
    /**
     * Ricerca nel database una multa fatta alla persona con codice fiscale indicato.
     * Viene ritornato un Set di Fine dal momento che una stessa persona può
     * avere preso più multe
     * @param cfCode
     * @return Un Set di Fine che contiene tutte le multe prese dall'utente spcificato
     */
    public Set<Fine> getFineByCFCode(String cfCode) {
        Set<Fine> fittingFines = new HashSet<>();
        
        for(Fine fine : fines) {
            if(fine.getCfCode().equals(cfCode)) fittingFines.add(fine);
        }  
        return fittingFines;
    }
    
    /**
     * Stampa tutti gli utenti nel database.
     */
    public void printUsers(){
        System.out.println("\nUSERS:");
        for(UserDB u : users){
            System.out.println(u.toString());
        }
    }
    
    /**
     * Stampa tutti i biglietti nel database.
     */
    public void printTickets(){
        System.out.println("\nTICKETS:");
        for(TicketDB t : tickets){
            System.out.println(t.toString());
        }
    }
    
    /**
     * Effettua il login dell'utente. Prende in ingresso i dati del login e verifica
     * se esiste una coppia uguale di username/password all'interno del database
     * @param username
     * @param psw
     * @return Vero se esiste una coppia username/password uguali a quelli specificati
     */
    public boolean userLogin(String username, String psw) {
        for (UserDB user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(psw)) {
                //System.out.println(username);
                return true;
            }
        }
        System.out.println("Non trovato");
        return false;
    }
    
    /**
     * Effettua il login dell'utente. Prende in ingresso i dati del login e verifica
     * se esiste una coppia uguale di username/password all'interno del database
     * @param username
     * @param psw
     * @return Vero se esiste una coppia username/password uguali a quelli specificati
     */
    public boolean collectorLogin(String username, String psw) {
        for (CollectorDB collector : collectors) {
            if(collector.getUsername().equals(username) && collector.getPassword().equals(psw)) return true;
        }
        return false;
    }    

    public ArrayList<TicketDB> getTicketByUsername(String username) {
        ArrayList<TicketDB> listaBiglietti = new ArrayList<>();
        
        for (TicketDB ticket : listaBiglietti) {
            if(ticket.getUsername().equals(username)) listaBiglietti.add(ticket);
        }
        return listaBiglietti;
    }
    
    
    
}
