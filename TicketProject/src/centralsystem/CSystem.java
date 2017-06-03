package centralsystem;

import databaseadapter.DatabaseAdapter;
import databaseadapter.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import machines.MachineStatus;
import ticket.Ticket;
import ticketCollector.Fine;

public class CSystem extends Observable implements CentralSystemCollectorInterface,CentralSystemTicketInterface {
    private final int PORTA_SERVER = 5000;
    private Map<Integer,MachineStatus> machineList;
    private final DatabaseAdapter database;
    private ServerSocket socketListener;
    private SocketHandler scHandler;
    private BankAdapter bank;
    private List<Message> log;

    public CSystem() {
        this.database = new DatabaseAdapter();
        this.bank = new BankAdapter();
        machineList = new HashMap();
        
        log = new ArrayList();
        //initUsers();
        //initCollectors();
        initServer();
    }
    
    /**
     * Chiude il servere. Se il parametro passato è vero lo riavvia anche
     * @param restart 
     */
    public void close(boolean restart) {
        notifyChange(restart);
    }
    
    public void addMessageToLog(String message) {
        Message msg = new Message(message, Calendar.getInstance());
        log.add(msg);
        notifyChange(msg);
    }
    
    public List<Message> getLog() {
        return log;
    }
    
    //__________________Metodi riguardanti l'utente_____________________________
    /**
     * 
     * Controlla nel database se esiste un utente con username. 
     * @param username
     * @return Vero se viene trovato un utente con username
     */
    public boolean checkUser(String username) {
        return database.checkUser(username);
    }
    
    /**
     * Aggiunge un utente, i cui dati sono specificati come parametri, al
     * database. Perché l'utente venga aggiunto con successo al database
     * è necessario che 
     * a) ci sia connessione al database (per il momento il database è un Set di utenti)
     * b) non sia già presente un utente con i dati indicati
     * Se una di queste due condizioni non è verificata il metodo ritorna falso.
     * Se l'operazione va a buon fine il metodo ritorna vero
     * @param name
     * @param surname
     * @param username
     * @param cf
     * @param psw
     * @return Vero se l'utente con i dati indicati viene aggiunto al database
     */
    public boolean addUser(String name, String surname, String cf, String username, String psw) {
        return database.addUser(name, surname, cf, username, psw);
    }
    
    /**
     * 
     * @param name
     * @param surname
     * @param username
     * @param cf
     * @param psw
     * @return Crea un utente e lo aggiunge al database. Se l'operazione va a buon 
     * fine ritorna vero, altrimenti ritorna falso
     */
    @Override
    public boolean createUser(String name, String surname, String username,String cf, String psw) {
        if(checkUser(username)){
            return false;
        }
        return database.addUser(name, surname, username, cf, psw);
    }
    
    /**
    * Effettua il login per gli utenti con i dati passati come parametri.
    * Esiste un login speciale per gli utenti per evitare conflitti nell'accesso al
    * proprio account
    * @param username
    * @param psw
    * @return Vero se il login va a buon fine, mentre falso se non viene effettuato
    * il login
    */
    @Override
    public boolean userLogin(String username, String psw) {
        return database.userLogin(username, psw);
    }
    
    //__________________Metodi riguardanti il controllore_______________________
    /**
     * /**
     * Aggiunge un controllore, i cui dati sono specificati come parametri, al
     * database. Perché il controllore venga aggiunto con successo al database
     * è necessario che 
     * a) ci sia connessione al database (per il momento il database è un Set di utenti)
     * b) non sia già presente un utente con i dati indicati
     * Se una di queste due condizioni non è verificata il metodo ritorna falso.
     * Se l'operazione va a buon fine il metodo ritorna vero
     * @param name
     * @param surname
     * @param cf
     * @param psw
     * @return Vero se il controllore viene aggiunto al database
     */
    public boolean addCollector(String name, String surname,String username, String cf,String psw) {
        return database.addCollector(name, surname,username, cf, psw);
    }
    
    /**
    * Effettua il login per i controllori con i dati passati come parametri.
    * Esiste un login speciale per i controllori per evitare conflitti nell'accesso al
    * proprio account
     * @param username
     * @param psw
     * @return Vero se il login va a buon fine, mentre falso se non viene effettuato
    * il login
     */
    @Override
    public boolean collectorLogin(String username, String psw) {
        return database.collectorLogin(username, psw);
    }
    
    @Override
    public boolean makeFine(Fine f) {
    	//TODO
    	return true;
    }
    
    /**
     * Aggiunge una multa, passata come parametro, al database
     * @param fine
     * @return Vero se l'operazione va a buon fine, altrimenti falso
     */
    public boolean addFine(Fine fine) {
        return database.addFine(fine);
    }
    
    /**
     * Ricerca nel database un biglietto con codice uguale a quello passato
     * come parametro. Se nel database viene trovato un biglietto con tale codice
     * il metodo ritorna vero
     * @param ticketSerial
     * @return ero se esiste un biglietto nel database con il codice indicato
     */
    
    @Override
    public boolean existsTicket(int ticketSerial) {
        return database.existsTicket(ticketSerial);
    }
    
    /**
     * Prende dal database il biglietto con codice uguale a quello passato
     * come parametro, e verifica la sua validità. Viene usato in congiunzione
     * con il metodo existsTicket per garantire l'esistenza del biglietto con 
     * codice indicato (per evitare NullPointerException)
     * @param ticketSerial
     * @return Vero se il biglietto è valido
     */
    public boolean isValid(int ticketSerial) {
        return database.isValid(ticketSerial);
    }
    
    //__________________Metodi riguardanti i biglietti__________________________
    public boolean addTicket(Ticket t) {
        return database.addTicket(t);
    }
    
    public Ticket getTicketById(String id) {
        return database.getTicketByCode(id);
    }
    
    //__________________Metodi riguardanti la macchinetta_______________________
    /**
     * Chiamato quando la macchinetta richiede dei nuovi codici. La generazione
     * dei codici avviene tramite [...]
     * @return I nuovi codici generati
     */
   
    
    /**
     * Permette il pagamento via carta di credito usando l'adapter della
     * banca. Per effettuare il pagamento viene controllata la validità del numero
     * di carta passato come argomento. Se il pagamento va a buon fine, ossia se
     * il numero di carta è valido e ci sono dei soldi sul conto associato a tale
     * carta, il metodo ritorna vero
     * @param cardNumber
     * @param amount
     * @return Vero se il pagamento va a buon fine
     */
    @Override
    public boolean cardPayment(String cardNumber, double amount) {
        return bank.paymentAttempt(cardNumber, amount);
    }
    
    //__________________Metodi per il debugging_________________________________
    /**
     * Stampa tutti i biglietti all'interno del database. Usato per debuggare
     */
    
    /**
     * Effettua un test di connessione. Ritorna al client la stringa che questo
     * gli ha mandato. Usato per debuggare
     * @param sentTest
     * @return La stringa in ingresso
     */
    @Override
    public String centralSystemTEST(String sentTest) {
        return sentTest;
    }
    
    //__________________Metodi privati per l'inizializzazione___________________
    /**
     * Inizializza il server. Viene creata la ServerSocket e questa si mette
     * in ascolto. 
     */
    private void initServer() {
        try {
            socketListener = new ServerSocket(PORTA_SERVER);
        } catch (IOException ex) {
            System.err.println("Errore apertura porta serverSocket");
        }

        scHandler = new SocketHandler(socketListener, this);
        scHandler.start();
    }
    
   

    private void initUsers() {
        database.addUser("Gabriele", "Mazzola", "MZZGRL95B22L872K","gabriele.m1995@gmail.com", "pizza123");
        database.addUser("Manuele", "Longhi", "ASCAKJSCAKSBCAKSJBHC","manumanu@gmail.com", "manumanu");
    }
    
    private void initCollectors() {
        database.addCollector("Andrea", "Rossi","areds", "RSSNDR95A13G388U", "ioboh");
        database.addCollector("ADMIN","ADMIN","ADMIN","ADMIN","ADMIN");
    }
   
    public boolean userLoginogin(String username, String psw) {
        return database.userLogin(username, psw);
    }    
   
    @Override
    public boolean updateMachineStatus(int machineCode, double inkLevel, double paperLevel, boolean active, String ipAddress) {
        if(machineList.containsKey(machineCode)){
            ((MachineStatus)machineList.get(machineCode)).setActive(active);            
            ((MachineStatus)machineList.get(machineCode)).setPaperLevel(paperLevel);
            ((MachineStatus)machineList.get(machineCode)).setInkLevel(inkLevel);
            ((MachineStatus)machineList.get(machineCode)).setIpAddress(ipAddress);
        }else{
            machineList.put(machineCode, new MachineStatus(machineCode,ipAddress,inkLevel,paperLevel,active));
        }
        return true;
    }

    @Override
    public synchronized long requestCodes(long numberOfCodes) {
        //codesCounter += numberOfCodes;
        database.setTicketCounter(database.getTicketCounter() + numberOfCodes);
        
        return database.getTicketCounter() - numberOfCodes;
        //return codesCounter - numberOfCodes;
    }
    
    public synchronized void notifyChange(Object arg) {
        if(arg != null) {
            setChanged();
            notifyObservers(arg);
        }
    }

    public ArrayList<Ticket> MyTicket(String username) {
        return database.getTicketByUsername(username); 
    }

    void addTicketSale(Date expiryDate, long serialCode, String username, String ticketType) {
        database.addTicketSale(expiryDate, serialCode, username, ticketType);
    }
}
