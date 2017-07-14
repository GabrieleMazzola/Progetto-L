package ticketmachine;

import ticketmachine.handlers.ResourcesHandler;
import communication.StubMachine;
import items.Product;
import items.Sale;
import java.io.IOException;
import java.util.*;
import ticketmachine.handlers.MoneyHandler;
import ticketmachine.handlers.TicketMachineCodeHandler;
import ticketmachine.handlers.UpdateHandler;

/**
 *
 * @author Zubeer
 */
public class TicketMachine extends Observable{
    private int cod;
    private ResourcesHandler resources;
    private MoneyHandler moneyTank;
    private TicketMachineCodeHandler codesHandler;
    private StubMachine stub;
    private UpdateHandler updateHandler;
    private String logged;
    private Operation operation;
    private Product toSell;
    
    private Map<String,Product> products;
    
    /**
     *
     * @param cod
     * @param PORTA_SERVER
     * @param ipAdress
     */
    public TicketMachine(int cod, int PORTA_SERVER, String ipAdress) {
        this.cod = cod;
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        
        try{
            this.stub = new StubMachine(ipAdress, PORTA_SERVER, this);
        }catch(IOException ex){
            ex.printStackTrace();
            stub = null;
        }
        
        this.products = stub.getProductList();
        
        this.codesHandler = new TicketMachineCodeHandler(this);
        this.updateHandler = new UpdateHandler(this);
        this.updateHandler.start();
        
        operation = Operation.SELLING_TICKET;
        
        logged = "-";
    }
    
    //__________________Metodi getter___________________________________________
    public int getCod() {
        return cod;
    }
    
    public double getInk() {
        return resources.getInkPercentage();
    }
    
    public double getPaper() {
        return resources.getPaperPercentage();
    }
    
    /**
     *
     * @return
     */
    public boolean canPrint() {
        return resources.hasEnoughResources();
    }
    
    public double getInsertedMoney() {
        return moneyTank.getInsertedMoney();
    }
    
    public String getLoggedUsername() {
        return logged;
    }

    public double getTotalMoney() {
        return moneyTank.getTotal();
    }
    
    public boolean isActive() {
        return getPaper() > 0 && getInk() > 0;
    }
    
    public int getAmountOf(double value) {
        return moneyTank.getQuantityOf(value);
    }
    
    public int getAmountByIndex(int index) {
        return moneyTank.getQuantitybyIndex(index);
    }
    
    public int getSerialsAmount() {
        return codesHandler.getSerialListLenght();
    }
    
    public Map<String, Product> getAvailableProducts() {
        return products;
    }
    
    public double getSelectedTicketCost() {
        if(toSell != null)
            return toSell.getCost();
        else return -1;
    }
    
    //__________________Metodi per la vendita di biglietti______________________
    /**
     * Setta il tipo di biglietto da vendere. In tal modo la macchinetta sa
     * quanto bisogna che l'utente paghi. Se la macchinetta non può stampare viene
     * mandata una notifica alla GUI
     * @param type
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public void setTicketToSell(String type) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //Se può stampare viene settato il costo
        if(canPrint() && codesHandler.hasSerials()) {
             toSell = products.get(type);  
        } else {
            notifyChange(false);
        }
    }

    /**
     * Effettua il pagamento tramite carta di credito. La carta di credito viene
     * passata come argomento della funzione. Se il pagamento va a buon fine il
     * biglietto viene stampato tramite printTicket()
     * @param cCardNumber
     * @return Vero se il pagamento va a buon fine, falso altrimenti
     */
    public boolean buyTicketCreditCard(String cCardNumber) {
        if(checkCreditCard(cCardNumber)) {
            printTicket();
            endSale();
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Consente di inserire una moneta/banconota del valore specificato. Nel caso 
     * in cui i soldi inseriti siano sufficienti per comprare il biglietto selezionato
     * viene automaticamente effettuata la vendita restituendo eventualmente il resto.
     * @param money
     */
    public void insertMoney(double money) {
        addInsertedMoney(money); 
        if (insertedEnoughMoney()) {
            printTicket();
            outputChange(); 
            endSale();
        }
    }
    
    /**
     * Aggiunge la quantità indicata al money handler, tenendo traccia di tutte
     * quelle inserite precedentemente dall'inizio della vendita
     * @param money 
     */
    private void addInsertedMoney(double money) {
        moneyTank.addMoney(money);
        notifyChange(money);
        notifyChange(moneyTank.addToInsertedMoney(money));
    }
    
    private boolean insertedEnoughMoney() {
        return moneyTank.getInsertedMoney() >= toSell.getCost();
    }
    
    /**
     * Stampa il biglietto. La stampa viene effettuata mandando una notifica alla
     * GUI
     */
    private void printTicket() {
        if(codesHandler.mustRequestCodes())
            codesHandler.startUpdateSerial();

        Sale sale = createSale();
        
        stub.addSale(sale);
        
        setOperation(Operation.PRINTING_TICKET);
                
        notifyChange(isActive());
        notifyChange(sale);
    }

    
    /**
     * Crea un ticket con il primo ticket code disponibile e il tipo specificato
     * prima nella vendita
     * @return ticketcode
     */
    private Sale createSale(){
        Sale sale = new Sale(new Date(), codesHandler.popSerialNumber(), logged, toSell, getClientIPAddress());
        System.out.println("logged: "+logged);
        if(logged=="-")
            resources.printTicket();
        return sale;
    }

    private void outputChange() {
        moneyTank.giveChange(toSell.getCost());
    }
    
    private boolean checkCreditCard(String credCardNumber) {
        return stub.cardPayment(credCardNumber, toSell.getCost());
    }
    
    private void endSale(){
        moneyTank.resetInsertedMoney();
        toSell=null;
    }
    
    //__________________Metodi per la gestione dei codici_______________________

    /**
     *
     * @param numberOfCodes
     */
    
    public void requestCodes(int numberOfCodes) {
        stub.requestCodes(numberOfCodes);
    }
    /**
     * Aggiunge alla lista dei ticket code disponibili quelli specificati
     * @param newSerials 
     */
    public void addTicketSerials(List<Long> newSerials) {
        codesHandler.endUpdateSerial(newSerials);
    }
    
    /**
     * Setta l'operazione che sta facendo la macchinetta. Serve per mandare una
     * notifica alla GUI in modo da poter passare da una scena all'altra
     * @param operation 
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
        notifyChange(operation);
    }
    
    //__________________Metodi per l'utente_____________________________________

    /**
     *
     * @param name
     * @param surname
     * @param cf
     * @param username
     * @param psw
     * @param email
     * @return
     */
    
    public boolean createUser(String name, String surname,String cf, String username, String psw, String email){
        return stub.createUser(name, surname, cf, username, psw, email);
    }
    
    /**
     * Effettua il login con i dati specificati
     * @param username
     * @param password
     * @return Vero se il login ha successo, falso altrimenti
     */
    public boolean login(String username, String password) {
        if(stub.userLogin(username, password)) {
            notifyChange(operation);
            logged = username;
            notifyChange(logged);
            
            return true;
        }
        else 
            return false;
    }
    
    /**
     * Effettua il logout settando a "-" il nome dell'utente loggato
     * @return Vero
     */
    public boolean logout() {
        logged = "-";
        notifyChange(logged);
        setOperation(Operation.SELLING_TICKET);
        return true;
    }
    
    /**
     *
     * @return
     */
    public boolean hasLogged(){
        return logged != "-" && logged != null;
    
    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    public double getCost() {
        return toSell.getCost();
    }

    public String getClientIPAddress() {
        return stub.getClientIPAddress();
    }

    /**
     *
     * @param machineStatus
     */
    public void updateMachineStatus(MachineStatus machineStatus) {
        stub.updateMachineStatus(machineStatus);
    }

    public void getUserFromEmail(String email) {
        stub.userEmailRequest(email);
    }
    
}
