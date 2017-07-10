package centralsystem;


import bank.BankAdapter;
import centralsystem.interfaces.CentralSystemCollectorInterface;
import centralsystem.interfaces.CentralSystemTicketInterface;
import console.LogCS;
import database.DatabaseAdapter;
import database.people.User;
import items.*;;
import java.util.*;
import singleton.JSONOperations;
import singleton.ProductsSingleton;
import ticketmachine.MachineStatus;



public class CSystem extends Observable implements CentralSystemCollectorInterface,CentralSystemTicketInterface{
    
    private final DatabaseAdapter database;
    private Map<Integer,MachineStatus> statusList;
    private Map<String,Product> itemList;
    private BankAdapter bank;
    private List<Message> log; 

    public CSystem(String className) {
        this.database = new DatabaseAdapter(className); 
        this.bank = new BankAdapter();
        statusList = new HashMap();
        itemList = ProductsSingleton.getInstance().getProducts();

        
        LogCS.getInstance().enable();
        log = new ArrayList();
        initUsers();
        initCollectors();
    }
    
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


    public boolean checkUser(String username) {
        return database.checkUser(username);
    }

    
    @Override
    public boolean createUser(String name, String surname, String cf,String username, String psw, String email) {
        if(checkUser(username)){
            return false;
        }
        return database.createUser(name, surname, cf, username, psw, email);
    }
    
    
    @Override
    public boolean userLogin(String username, String psw) {
        return database.userLogin(username, psw);
    }
    
    public User getUser(String username) {
        return database.getUser(username);
    }
    
    
    //__________________Metodi riguardanti il controllore_______________________
   
    public boolean createCollector(String name, String surname,String cf, String username,String psw) {
        return database.createCollector(name, surname, cf,username, psw);
    }
    

    @Override
    public boolean collectorLogin(String username, String psw) {
        return database.collectorLogin(username, psw);
    }
    
    @Override
    public boolean addFine(Fine f) {
    	return database.addFine(f);
    }
    
    @Override
    public Boolean existsTicket(long serialCode) {
        return database.serialCodeCheck(serialCode);
    }

    public Fine getFineById(long id) {
        return database.getFineById(id);
    }
    
    public Set<Fine> getFinesOf(String cf) {
        return database.getFinesByCF(cf);
    }
    
    //__________________Metodi riguardanti i biglietti__________________________
    @Override
    public boolean addSale(Sale sale) {
        return database.addSale(sale);
    }
    
    public Set<Sale> getSalesByType(String type) {
        return database.getSalesByType(type);
    }
    
    public Set<Sale> getSalesByUsername(String username) {
        return database.getSalesByUsername(username);
    }
    
    public Set<Sale> getValidSalesByUsername(String username) {
        return database.getValidSalesByUsername(username);
    }
    
    //__________________Metodi riguardanti la macchinetta_______________________
   
    @Override
    public boolean cardPayment(String cardNumber, double amount) {
        return bank.paymentAttempt(cardNumber, amount);
    }
    
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
    
    public synchronized void notifyChange(Object arg) {
        if(arg != null) {
            setChanged();
            notifyObservers(arg);
        }
    }

    public String ticketTypes(){
        return JSONOperations.getInstance().ticketTypesPacket(itemList);
    }

    public Set<Sale> getAllSales() {
        return database.getAllSales();
    }
    
    public long getProductsCounter() {
        return database.getProductCounter();
    }

    public Long countAllFinesMadeBy(String collectorUsername) {
        return database.countAllFinesMadeBy(collectorUsername);
    }
}
