package centralsystem;


import bank.BankAdapter;
import centralsystem.interfaces.CentralSystemCollectorInterface;
import centralsystem.interfaces.CentralSystemTicketInterface;
import console.LogCS;
import database.DatabaseFacade;
import database.people.User;
import productsfactories.central.CentralProductsFactory;
import items.*;;
import java.util.*;
import singleton.JSONOperations;
import ticketmachine.MachineStatus;import java.util.*;
import singleton.JSONOperations;
import ticketmachine.MachineStatus;import java.util.*;
import singleton.JSONOperations;
import ticketmachine.MachineStatus;import java.util.*;
import singleton.JSONOperations;
import ticketmachine.MachineStatus;

/**
 *
 * @author Zubeer
 */
public class CSystem extends Observable implements CentralSystemCollectorInterface,CentralSystemTicketInterface,CentralSystemWebServerInterface{
    
    private final DatabaseFacade database;
    private Map<Integer,MachineStatus> statusList;
    private Map<String,Product> products;
    private BankAdapter bank;
    private List<Message> log; 
    
    /**
     * Inizializza tutti i coponenti relativi a connessione e comunicazione
     * @param className 
     */
    public CSystem(String className) {
        this.database = new DatabaseFacade(className); 
        this.bank = new BankAdapter();
        statusList = new HashMap();
        products = CentralProductsFactory.getInstance().getProducts();
        
        LogCS.getInstance().enable();
        log = new ArrayList();
        initUsers();
        initCollectors();
        //initProducts();
    }
    
    /**
     *
     * @param message
     */
    public void addMessageToLog(String message) {
        Message msg = new Message(message, Calendar.getInstance());
        log.add(msg);
        notifyChange(msg);
    }
    
    public List<Message> getLog() {
        return log;
    }
    
    public Map<String, Product> getAvailableProducts() {
        return products;
    }
    
    public void printProducts() {
        for(Map.Entry<String,Product> entry:products.entrySet())
            System.out.println(entry.getKey() + "N: " + entry.getValue().getDescription() + "Cost: " + entry.getValue().getCost() + " Dur: " + entry.getValue().getDuration());
    }
    
    //__________________Metodi riguardanti l'utente_____________________________


    /**
     * Controlla che esista l'username nel database
     * @param username
     * @return Vero se l'username è presente, falso altrimenti
     */
    public boolean checkUser(String username) {
        return database.checkUser(username);
    }

    /**
     * Aggiunge un nuovo utente al database, memorizzando le informazioni
     * passate nei parametri.
     * @param name
     * @param surname
     * @param cf Codice fiscale
     * @param username
     * @param psw Password
     * @param email
     * @return Vero se l'operazione va a buon fine, falso altrimenti o se 
     * l'utente esiste già
     */
    @Override
    public boolean createUser(String name, String surname, String cf,String username, String psw, String email) {
        if(checkUser(username)){
            return false;
        }
        return database.createUser(name, surname, cf, username, psw, email);
    }
    
    /**
     * Permette di effettuare il login dell'utente, comunicando al DatabaseFacade le credenziali
     * @param username
     * @param psw
     * @return Vero se le credenziali sono giuste, falso altrimenti
     */
    @Override
    public boolean userLogin(String username, String psw) {
        return database.userLogin(username, psw);
    }
    
    public User getUser(String username) {
        return database.getUser(username);
    }
    
    
    //__________________Metodi riguardanti il controllore_______________________
   
    /**
     * Aggiunge un nuovo Controllore al database, memorizzando le informazioni
     * passate nei parametri.
     * @param name
     * @param surname
     * @param cf
     * @param username
     * @param psw
     * @return Vero se l'operazione va a buon fine, falso altrimenti
     */
    public boolean createCollector(String name, String surname,String cf, String username,String psw) {
        return database.createCollector(name, surname, cf,username, psw);
    }
    
    /**
     * Permette di effettuare il login del Collector, comunicando al DatabaseFacade le credenziali
     * @param username
     * @param psw
     * @return Vero se le credenziali sono giuste, falso altrimenti
     */
    @Override
    public boolean collectorLogin(String username, String psw) {
        return database.collectorLogin(username, psw);
    }
    
    @Override
    public Boolean makeFine(Fine f) {
    	return database.addFine(f);
    }
    
    @Override
    public Boolean existsTicket(long serialCode) {
        return database.serialCodeCheck(serialCode);
    }

    public Fine getFineById(long id) {
        return database.getFineById(id);
    }
    
    /**
     * Permette di ottenere le multe legate ad un codice fiscale
     * @param cf
     * @return Ritorna un set di Fine legate al codice fiscale inserito
     */
    public Set<Fine> getFinesOf(String cf) {
        return database.getFinesByCF(cf);
    }
    
    //__________________Metodi riguardanti i biglietti__________________________
    @Override
    public Boolean addSale(Sale sale) {
        return database.addSale(sale);
    }
    
    public Set<Sale> getSalesByType(String type) {
        return database.getSalesByType(type);
    }
    public Sale getSale(String serialCode){
        return database.getSale(serialCode);
    }
    public Set<Sale> getSalesByUsername(String username) {
        return database.getSalesByUsername(username);
    }
    /**
     * Permette di ottenere dal database le Sale ancora valide di un username
     * @param username
     * @return Un set di Sale la cui expiryDate non è ancora passata
     */
    public Set<Sale> getValidSalesByUsername(String username) {
        return database.getValidSalesByUsername(username);
    }
    
    //__________________Metodi riguardanti la macchinetta_______________________

    /**
     *
     * @param cardNumber
     * @param amount
     * @return
     */
   
    @Override
    public boolean cardPayment(String cardNumber, double amount) {
        return bank.paymentAttempt(cardNumber, amount);
    }
    
    /**
     * Riceve un machine status dal SocketHandler, e lo mette nella mappa
     * statusList, con chiave il codice della macchina
     * @param status
     * @return Vero
     */
    @Override
    public boolean updateMachineStatus(MachineStatus status) {
        statusList.put(status.getMachineCode(), status);
        notifyChange(status);
        return true;
    }
    
    /**
     * Incrementa il contatore dei prodotti e ritorna il valore dal quale devono
     * cominciare i nuovi seriali della macchinetta che ha richiesto i codici
     * 
     * @param numberOfCodes
     * @return 
     */
    @Override
    public synchronized long requestCodes(long numberOfCodes) {
        
        long counter = getProductsCounter();
        database.setProductCounter(counter + numberOfCodes);
        return counter;
    }

    
    //__________________Metodi privati per l'inizializzazione___________________   
    private void initUsers() {
        database.createUser("ADMIN", "ADMIN", "ADMIN", "ADMIN", "ADMIN", "");
    }
    
    private void initCollectors() {
        database.createCollector("Andrea", "Rossi","RSSNDR95A13G388U","areds", "ioboh");
    }
        

    
    //__________________Metodi pubblici per la gui______________________________

    /**
     *
     * @param arg
     */
    
    public synchronized void notifyChange(Object arg) {
        if(arg != null) {
            setChanged();
            notifyObservers(arg);
        }
    }

    /**
     *
     * @return
     */
    public String ticketTypes(){
        return JSONOperations.getInstance().ticketTypesPacket(products);
    }

    public Set<Sale> getAllSales() {
        return database.getAllSales();
    }
    
    public long getProductsCounter() {
        return database.getProductCounter();
    }

    /**
     *
     * @param collectorUsername
     * @return
     */
    public Long countAllFinesMadeBy(String collectorUsername) {
        return database.countAllFinesMadeBy(collectorUsername);
    }

    
    
    
   

    

    

   

    

    

    

    
}
