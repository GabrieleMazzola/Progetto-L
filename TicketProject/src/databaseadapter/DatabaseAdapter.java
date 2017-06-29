package databaseadapter;

import databaseadapter.people.User;
import databaseadapter.mapper.CollectorMapper;
import databaseadapter.mapper.FineMapper;
import databaseadapter.mapper.SimCollectorMapper;
import databaseadapter.mapper.SimFineMapper;
import databaseadapter.mapper.SimTicketMapper;
import databaseadapter.mapper.SimUserMapper;
import databaseadapter.mapper.TicketMapper;
import databaseadapter.mapper.UserMapper;
import databaseadapter.people.Collector;
import java.util.*;
import ticket.Ticket;
import ticketCollector.Fine;


public class DatabaseAdapter {
    private OptionDB options;
    private SimTicketMapper ticketMapper;
    private SimUserMapper userMapper;
    private SimCollectorMapper collectorMapper;
    private SimFineMapper fineMapper;
    
    public DatabaseAdapter() {
        options = new OptionDB();
//        ticketMapper = new TicketMapper("tickets");
//        userMapper = new UserMapper("users");
//        collectorMapper = new CollectorMapper("collectors");
//        fineMapper = new FineMapper("fines");
        ticketMapper = new SimTicketMapper();
        userMapper = new SimUserMapper();
        collectorMapper = new SimCollectorMapper();
        fineMapper = new SimFineMapper();
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
    
    /**
     * Aggiunge un controllore al database. I campi del controllore da aggiungere sono
     * specificati come parametri della funzione
     * @param name
     * @param surname
     * @param cf
     * @param username
     * @param psw
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addCollector(String name, String surname, String cf, String username, String psw) {
        return collectorMapper.save(new Collector(name, surname, username, cf, psw));
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
    
    public void incrementTicketVersion(){
        options.setTicketVersionCounter(options.getTicketVersionCounter()+1);
    }
    
    /**
     * Cerca nel database se esiste un biglietto con codice indicato. Per farlo
     * viene chiamato il metodo getTicketByCode
     * @param ticketSerial
     * @return Vero se getTicketByCOde non restituisce null, falso altrimenti
     */
    public boolean existsTicket(int ticketSerial){
        //return getTicketByCode(ticketSerial + "") != null;
        return (ticketSerial <= options.getTicketVersionCounter());
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
//    public boolean activateTicket(int ticketSerial){
//        throw new UnsupportedOperationException("Cannot call 'activateTicket(int)' yet");
//    }
    
    /**
     * Aggiunge al database la multa specificata
     * @param fine
     * @return Vero se l'operazione va a buon fine
     */
    public boolean addFine(Fine fine) {
        return fineMapper.save(fine);
    }
    
    public Fine getFineById(long id) {
        return (Fine)fineMapper.get(id + "");
    }
    
    /**
     * Ricerca nel database una multa fatta alla persona con codice fiscale indicato.
     * Viene ritornato un Set di Fine dal momento che una stessa persona può
     * avere preso più multe
     * @param cfCode
     * @return Un Set di Fine che contiene tutte le multe prese dall'utente spcificato
     */
    public Set<Fine> getFinesByCFCode(String cfCode) {
        return (Set<Fine>)fineMapper.getAllFinesOf(cfCode);
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

    public List<Ticket> getTicketByUsername(String username) {
        return (List<Ticket>)ticketMapper.getAllTicketsOf(username);
    }

    public long getTicketCounter() {
        return options.getTicketCounter();
    }

    public void setTicketCounter(long l) {
        options.setTicketCounter(l);
    }
}
