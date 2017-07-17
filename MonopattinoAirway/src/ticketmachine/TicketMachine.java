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

public class TicketMachine extends Observable{
    private int cod;
    private ResourcesHandler resources;
    private MoneyHandler moneyTank;
    private TicketMachineCodeHandler codesHandler;
    private StubMachine stub;
    private UpdateHandler updateHandler;
    private Operation operation;
    
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
        
        printProducts();
        
        this.codesHandler = new TicketMachineCodeHandler(this);
        this.updateHandler = new UpdateHandler(this);
        this.updateHandler.start();
        
        operation = Operation.SELLING_TICKET;
        
        //logged = "-";
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
    
    public boolean canPrint() {
        return resources.hasEnoughResources();
    }
    
//    public String getLoggedUsername() {
//        return logged;
//    }

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
    
    //__________________Metodi per la vendita di biglietti______________________
    
    public Product getProduct(String type) {
        return products.get(type);
    }
    
    /**
     * Vende un biglietto con pagamento tramite carta di credito
     * @param toSell
     * @param cardNumber
     * @return Vero se il pagamento viene effettuato
     */
    public boolean sellTicket(Product toSell, String cardNumber) {
        return checkCreditCard(cardNumber, toSell.getCost());
    }
    
    /**
     * Vende un biglietto con pagamento tramite contanti
     * @param toSell
     * @param amountPaid
     * @return Il resto che è possibile erogare
     */
    public double sellTicket(Product toSell, double amountPaid) {
        printTicket();
        return moneyTank.giveChange(toSell.getCost(), amountPaid);
    }
    
    
    public void insertMoney(double money) {
        moneyTank.addMoney(money);
        notifyChange(money);
    }
    
    /**
     * Stampa il biglietto riducendo le risorse disponibili per la macchinetta
     */
    private void printTicket() {
        if(codesHandler.mustRequestCodes())
            codesHandler.startUpdateSerial();
        resources.printTicket();
                
        notifyChange(isActive());
    }

    
    /**
     * Crea un ticket con il primo ticket code disponibile e il tipo specificato
     * prima nella vendita
     * @return ticketcode
     */
    public Sale createSale(Product toSell, String toWho){
        Sale sale = new Sale(new Date(), codesHandler.popSerialNumber(), toWho, toSell, getClientIPAddress());
        if(toWho.equals("-"))
            resources.printTicket();
        stub.addSale(sale);
        notifyChange(sale);
        return sale;
    }
    
    private boolean checkCreditCard(String credCardNumber, double cost) {
        return stub.cardPayment(credCardNumber, cost);
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
     * Effettua il verifyLogin con i dati specificati
     * @param username
     * @param password
     * @return Vero se il verifyLogin ha successo, falso altrimenti
     */
    public boolean verifyLogin(String username, String password) {
        return stub.userLogin(username, password);
    }
    
    /**
     *
     * @return
     */
//    public boolean hasLogged(){
//        return logged != "-" && logged != null;
//    
//    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
//
//    public double getCost() {
//        return toSell.getCost();
//    }

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
    
    
    private void printProducts(){
    	StringBuilder sb = new StringBuilder();
    	
    	for(Product prod : products.values()){
    		sb.append("[").append(prod).append("]\n");
    	}
    	System.err.println("\n\nProducts initialized: ");
    	System.err.println(sb.toString());
    }
    
    public int getOfflineSaleSize(){
        return stub.getOfflineSaleSize();
    }
      
}
