package databaseadapter;

import databaseadapter.people.User;
import databaseadapter.mapper.CollectorMapper;
import databaseadapter.mapper.TicketMapper;
import databaseadapter.mapper.UserMapper;
import databaseadapter.people.Collector;
import java.util.*;
import ticket.Ticket;
import ticketCollector.Fine;


public class DatabaseAdapter {
    private OptionDB options;
    private TicketMapper ticketMapper;
    private UserMapper userMapper;
    private CollectorMapper collectorMapper;
    
    public DatabaseAdapter() {
        options = new OptionDB();
        ticketMapper = new TicketMapper("tickets");
        userMapper = new UserMapper("users");
        collectorMapper = new CollectorMapper("collectors");
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
    public boolean addUser(String name, String surname, String cf, String username, String psw) {
        return userMapper.save(new User(name, surname, cf, username, psw));
    }
    
    /**
     * Aggiunge un biglietto al database
     * @param ticket
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addTicket(Ticket ticket){
        return ticketMapper.save(ticket);
    }
    
    public boolean addTicketSale(Date expiryDate, long serialCode, String username, String ticketType){
        return true;
//        throw new UnsupportedOperationException("Cannot call 'addTicket(Dat, long, String, String)' yet");
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
    public boolean addCollector(String name, String surname, String cf, String username, String psw) {
        return collectorMapper.save(new Collector(name, surname, cf, username, psw));
    }
    
    /**
     * Cerca nel database un biglietto tramite il suo codice. Se viene trovato,
     * viene restituito il biglietto.
     * @param ticketSerial
     * @return Il TicketDB se esiste un biglietto con codice ticketCode, null
     * altrimenti
     */
    public Ticket getTicketByCode(String ticketSerial){
        return (Ticket)ticketMapper.get(ticketSerial);
    }
    
    /**
     * Cerca nel database se esiste un biglietto con codice indicato. Per farlo
     * viene chiamato il metodo getTicketByCode
     * @param ticketSerial
     * @return Vero se getTicketByCOde non restituisce null, falso altrimenti
     */
    public boolean existsTicket(int ticketSerial){
        return getTicketByCode(ticketSerial + "") != null;
    }
    
    /**
     * Cerca nel database il biglietto con codice indicato e verifica la sua validità.
     * Per farlo viene chiamato il metodo getTicketByCode
     * @param ticketSerial
     * @return Vero se getTicketByCode non ritorna null e se il biglietto è attivo
     */
    public boolean isValid(int ticketSerial){
        throw new UnsupportedOperationException("Cannot call 'isValid(int)' yet");
    }
    
    /**
     * Ricerca nel database un utente con codice fiscale cf
     * @param username
     * @return Vero se esiste un utente con codice fiscale cf nel database
     */
    public boolean checkUser(String username){
        return userMapper.get(username) != null;
    }
 
    /**
     * Rende attivo il biglietto indicato tramite codice. Per farlo viene chiamato
     * il metodo getTicketByCode
     * @param ticketSerial
     * @return Vero se l'attivazione va a buon fine (ossia se getTicketByCode non 
     * restituisce null), falso altrimenti
     */
    public boolean activateTicket(int ticketSerial){
        throw new UnsupportedOperationException("Cannot call 'activateTicket(int)' yet");
    }
    
    /**
     * Aggiunge al database la multa specificata
     * @param fine
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addFine(Fine fine) {
        throw new UnsupportedOperationException("Cannot call 'addFine(Fine)' yet");
    }
    
    /**
     * Ricerca nel database una multa fatta alla persona con codice fiscale indicato.
     * Viene ritornato un Set di Fine dal momento che una stessa persona può
     * avere preso più multe
     * @param cfCode
     * @return Un Set di Fine che contiene tutte le multe prese dall'utente spcificato
     */
    public Set<Fine> getFineByCFCode(String cfCode) {
        throw new UnsupportedOperationException("Cannot call 'getFineByCFCode(String)' yet");
    }
    
    /**
     * Effettua il login dell'utente. Prende in ingresso i dati del login e verifica
     * se esiste una coppia uguale di username/password all'interno del database
     * @param username
     * @param psw
     * @return Vero se esiste una coppia username/password uguali a quelli specificati
     */
    public boolean userLogin(String username, String psw) {
        User u = (User)userMapper.get(username);
        if(u != null)
            return u.getUsername().equals(username) && u.getPsw().equals(psw);
        else return false;
    }
    
    /**
     * Effettua il login dell'utente. Prende in ingresso i dati del login e verifica
     * se esiste una coppia uguale di username/password all'interno del database
     * @param username
     * @param psw
     * @return Vero se esiste una coppia username/password uguali a quelli specificati
     */
    public boolean collectorLogin(String username, String psw) {
        Collector c = (Collector)collectorMapper.get(username);
        if(c != null)
            return c.getUsername().equals(username) && c.getPsw().equals(psw);
        else return false;
    }    

    public ArrayList<Ticket> getTicketByUsername(String username) {
        throw new UnsupportedOperationException("Cannot call 'getTicketByUsername(String)' yet");
    }

    public long getTicketCounter() {
        return options.getTicketCounter();
    }

    public void setTicketCounter(long l) {
        options.setTicketCounter(l);
    }
}
