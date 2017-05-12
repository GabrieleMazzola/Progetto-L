package centralsystem;

import databaseadapter.DatabaseAdapter;
import databaseadapter.*;
import java.io.IOException;
import java.net.ServerSocket;
import ticketCollector.Fine;

public class CSystem implements CentralSystemCollectorInterface,CentralSystemTicketInterface {

    private final int PORTA_SERVER = 5000;
    private final int NUMERO_MACCHINETTE = 3;

    private final DatabaseAdapter database;
    private ServerSocket socketListener;
    private SocketHandler scHandler;
    private BankAdapter bank;

    public CSystem() {
        this.database = new DatabaseAdapter();
        this.bank = new BankAdapter();
        initTickets();
        initUsers();
        initCollectors();
        initServer();
    }
    
    //__________________Metodi riguardanti l'utente_____________________________
    /**
     * 
     * @param cf
     * @return Controlla nel database se esiste un utente con codice fiscale cf.
     * Ritorna vero se l'utente viene trovato
     */
    public boolean checkUser(String cf) {
        return database.checkUser(cf);
    }
    
    /**
     * 
     * @param name
     * @param surname
     * @param cf
     * @param psw
     * @return Aggiunge un utente, i cui dati sono specificati come parametri, al
     * database. Se l'operazione va a buon fine il metodo ritorna vero, altrimenti 
     * ritorna falso
     */
    public boolean addUser(String name, String surname, String username,String cf,String psw) {
        return database.addUser(name, surname, username,cf, psw);
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
        return database.addUser(name, surname, username, cf, psw);
    }
    
    /**
    * 
    * @param username
    * @param psw
    * @return Effettua il login per gli utenti con i dati passati come parametri.
    * Ritorna vero se il login va a buon fine, mentre falso se non viene effettuato
    * il login. Esistono login differenti per evitare conflitti nell'accesso al
    * proprio account
    */
    @Override
    public boolean userLogin(String username, String psw) {
        return database.userLogin(username, psw);
    }
    
    //__________________Metodi riguardanti il controllore_______________________
    /**
     * 
     * @param name
     * @param surname
     * @param cf
     * @param psw
     * @return Aggiunge un controllore, i cui dati sono specificati come parametri,
     * al database. Se l'operazione va a buon fine il metodo ritorna vero, altrimenti
     * ritorna falso
     */
    public boolean addCollector(String name, String surname, String cf,String psw) {
        return database.addCollector(name, surname, cf, psw);
    }
    
    /**
     * 
     * @param username
     * @param psw
     * @return Effettua il login per i constrollori con i dati passati come parametri.
     * Ritorna vero se il login va a buon fine, mentre falso se non viene effettuato
     * il login. Esistono login differenti per evitare conflitti nell'accesso al
     * proprio account
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
     * 
     * @param fine
     * @return Aggiunge una multa, passata come parametro, al database.Se l'operazione 
     * va a buon fine il metodo ritorna vero, altrimenti ritorna falso
     */
    public boolean addFine(Fine fine) {
        return database.addFine(fine);
    }
    
    /**
     * 
     * @param ticketCode
     * @return Ricerca nel database un biglietto con il codice uguale a quello
     * indicato come parametro. Se tale ricerca non produce alcun risultato il metodo
     * ritorna falso, altrimenti ritorna vero
     */
    @Override
    public boolean existsTicket(String ticketCode) {
        return database.existsTicket(ticketCode);
    }
    
    /**
     * 
     * @param ticketCode
     * @return Prende dal database il biglietto con il codice uguale a quello
     * indicato come parametro. Se tale biglietto è valido il metodo ritorna vero,
     * altrimenti ritorna falso. Usato in congiunzione con existsTicket per verificare
     * l'esistenza del biglietto prima della sua validità
     */
    public boolean isValid(String ticketCode) {
        return database.isValid(ticketCode);
    }
    
    //__________________Metodi riguardanti la macchinetta_______________________
    /**
     * 
     * @return Richiede dei codici nuovi
     */
    @Override
    public String requestCodes() {        
        String result = "MAZZOLAINARRIVO";
        return result;
    }
    
    /**
     * 
     * @param cardNumber
     * @return Effettua il pagamento via carta di credito con la carta di credito
     * specificata. Ritorna vero se il pagamento va a buon fine, mentre falso altrimenti
     */
    @Override
    public boolean cardPayment(String cardNumber) {
        return bank.checkCreditCard(cardNumber);
    }
    
    //__________________Metodi per il debugging_________________________________
    /**
     * Stampa tutti i biglietti all'interno del database. Usato per debuggare
     */
    public void printTickets() {
        database.printTickets();
    }
    
    /**
     * Stampa tutti gli utenti all'interno del database. Usato per debuggare
     */
    public void printUsers() {
        database.printUsers();
    }
    
    /**
     * 
     * @param sentTest
     * @return Fa un test di connessione. Ritorna al client la stringa che arriva
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
    
    private void initTickets() {
        for (int i = 0; i < 10; i++) {
            database.addTicket(new TicketDB(TicketType.SINGLE));
        }

        for (int i = 0; i < 10; i++) {
            database.addTicket(new TicketDB(TicketType.SEASON));
        }
    }

    private void initUsers() {
        database.addUser("Gabriele", "Mazzola", "MZZGRL95B22L872K","gabriele.m1995@gmail.com", "pizza123");
        database.addUser("Manuele", "Longhi", "ASCAKJSCAKSBCAKSJBHC","manumanu@gmail.com", "manumanu");
    }
    
    private void initCollectors() {
        database.addCollector("Andrea", "Rossi", "RSSNDR95A13G388U", "IOBOHIOBOH");
        database.addCollector("Mario", "bioanchi", "mrreaosdnvaoen", "manumanu");
    }
}
