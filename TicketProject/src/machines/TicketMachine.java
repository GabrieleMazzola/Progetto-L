package machines;

import java.util.List;
import java.util.Observable;
import ticket.Ticket;
import ticket.TicketType;

/**
 *
 * @author Andrea
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
    
    private double insertedMoney;
    private double cost;
    private TicketType type;
    
    public TicketMachine(int cod, int PORTA_SERVER, String ipAdress) {
        this.cod =cod;
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        this.stub = new StubMachine(ipAdress, PORTA_SERVER, this);
        this.codesHandler = new TicketMachineCodeHandler(stub);
        this.updateHandler = new UpdateHandler(this, stub);
        this.updateHandler.start();
        
        operation = Operation.SELLING_TICKET;
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
    
    public double getInsertedMoney() {
        return moneyTank.getInsertedMoney();
    }
    
    public String getLoggedUsername() {
        return logged;
    }
    
    public double getSelectedTicketCost() {
        return cost;
    }
    
    public float getMoneyInTank() {
        return moneyTank.getTotal();
    }
    
    public boolean isActive() {
        return getPaper() > 0 && getInk() > 0;
    }
    
    public int getAmountOf(double value) {
        return moneyTank.getQuantityOf(value);
    }
    
    public int getAmountByIndex(int index) {
        return moneyTank.getSingleQuantitybyIndex(index);
    }
    
    //__________________Metodi per la vendita di biglietti______________________
    /**
     * Setta il tipo di biglietto da vendere. In tal modo la macchinetta sa
     * quanto bisogna che l'utente paghi
     * @param type
     */
    public void setTicketToSell(TicketType type) {
        this.type = type;
        this.cost = type.getCost();
        notifyChange(canPrint());
    }
    
    /**
     * Consente di inserire una moneta/banconota del valore specificato. Nel caso 
     * in cui i soldi inseriti siano sufficienti per comprare il biglietto selezionato
     * viene automaticamente effettuata la vendita restituendo eventualmente il resto.
     * Se non è stato selezionato alcun biglietto o il metodo di pagamento CASH,
     * le monete inserite non vengono salvate
     * @param money
     */
    public void insertMoney(double money) {
        addInsertedMoney(money);
        if (insertedEnoughMoney()) {
            printTicket();
            outputChange();
        }
    }
    
    public boolean buyTicketCreditCard(String cCardNumber) {
        if(checkCreditCard(cCardNumber)) {
            System.out.println("Pagamento effettuato. Stampa biglietto");
            printTicket();
            return true;
        }
        else {
            System.out.println("Pagamento non effettuato");
            return false;
        }
    }
    
    private boolean insertedEnoughMoney() {
        return moneyTank.getInsertedMoney() >= cost;
    }
    
    /**
     * funzione che stampa il Ticket
     */
    private void printTicket() {
        if(codesHandler.mustRequestCodes())
            codesHandler.startUpdateSerial();
        Ticket ticket = createTicket();
        resources.printTicket();
        System.out.println("Ticket printed");
        setOperation(Operation.PRINTING_TICKET);
        notifyChange(isActive());
        notifyChange(ticket);
    }
    
    private void addInsertedMoney(double money) {
        moneyTank.addMoney(money);
        notifyChange(money);
        notifyChange(moneyTank.addToInsertedMoney(money));
    }
    
    /**
     * funzione che crea il biglietto quando lo si compra 
     * @return ticketcode
     */
    private Ticket createTicket(){
        Ticket ticket = new Ticket(codesHandler.popSerialNumber()+"", type);
        return ticket;
    }
    
    public void addTicketSerials(List<Long> newSerials) {
        codesHandler.endUpdateSerial(newSerials);
    }

    private void outputChange() {
        moneyTank.giveChange(cost, insertedMoney);
    }
    
    private boolean checkCreditCard(String credCardNumber) {
        return stub.cardPayment(credCardNumber, cost);
    }
    
    //__________________Metodi per la gestione dei codici_______________________

    public boolean login(String username, String password) {
        if(stub.userLogin(username, password)) {
            operation = Operation.SELLING_TICKET;
            notifyChange(operation);
            logged = username;
            notifyChange(logged);
            return true;
        }
        else 
            return false;
    }
    
    public boolean logout() {
        logged = "-";
        notifyChange(logged);
        return true;
    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
    
    public void setOperation(Operation operation) {
        this.operation = operation;
        notifyChange(operation);
    }
}
