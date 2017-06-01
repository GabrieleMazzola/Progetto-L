package machines;

import exceptions.TicketTypeNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import paymentMethods.PaymentMethod;

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
    private String type;
    private PaymentMethod pMethod;
    
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
        return insertedMoney;
    }
    
    public String getLoggedUsername() {
        return logged;
    }
    
    public double getSelectedTicketCost() {
        return cost;
    }
    
    public PaymentMethod getPaymentMethod() {
        return pMethod;
    }
    
    public float getMoneyInTank() {
        return moneyTank.getTotal();
    }
    
    public boolean hasTicketSelected() {
        return type != null;
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
    public void setTicketToSell(String type) {
        this.type = type;
        operation = Operation.SELECTING_PAYMENT;
        notifyChange(operation);
        notifyChange(canPrint());
    }
    
    /**
     * Setta il tipo di pagamento scelto per pagare
     * @param pMethod
     */
    public void setPaymentMethod(PaymentMethod pMethod){
        this.pMethod = pMethod;
        switch (pMethod) {
            case CASH:
                operation = Operation.INSERTING_COINS;
                notifyChange(operation);
                break;
            case CREDITCARD:
                operation = Operation.INSERTING_CCARD;
                notifyChange(operation);
                break;
        }
    }
    
    /**
     * Effettua la vendita del biglietto in base al tipo di biglietto e al metodo
     * di pagamento scelto.
     */
    public boolean buyTicket() throws TicketTypeNotFoundException {
        setCostForType(type);
        switch (pMethod) {
            case CASH:
                insertedMoney = 0;
                break;
            case CREDITCARD:
                return true;
            default:
                return false;
        }
        return true;
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
        moneyTank.addMoney(money);
        notifyChange(money);
        insertedMoney += money;
        int temp = (int)Math.floor(insertedMoney*100);
        insertedMoney = (double)temp/(double)100;
        notifyChange(insertedMoney);
        if (isEnoughMoney(insertedMoney)) {
            sellTicket(PaymentMethod.CASH);
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
    
    private boolean isEnoughMoney(double money) {
        return money >= cost;
    }

    private void sellTicket(PaymentMethod method) {
        switch (method) {
            case CASH:
                sellTicketCash();
                break;
            case CREDITCARD:
                sellTicketNotCash();
                break;
            default:
                throw new AssertionError(method.name());
        }
        type = null;
        cost = 0;
        insertedMoney = 0;
        notifyChange(insertedMoney);
    }
    
    private void sellTicketCash() {
        printTicket();
        outputChange();
    }

    private void sellTicketNotCash() {   //serve sia per bancomat e per carta di credito
        printTicket();
    }
    
    /**
     * funzione che stampa il Ticket
     */
    private void printTicket() {
        if(codesHandler.mustRequestCodes())
            codesHandler.startUpdateSerial();
        System.out.println("numero ticket:"+createTicket());
        resources.printTicket();
        notifyChange(isActive());
        System.out.println("Ticket printed");
        operation = Operation.PRINTING_TICKET;
        notifyChange(operation);
    }
    
    /**
     * funzione che crea il biglietto quando lo si compra 
     * @return ticketcode
     */
    private long createTicket(){
        return codesHandler.popSerialNumber();
    }
    
    public void addTicketSerials(List<Long> newSerials) {
        codesHandler.endUpdateSerial(newSerials);
    }

    private void outputChange() {
        //do exeption
        moneyTank.giveChange(cost, insertedMoney);
    }
    
    private String getCredCardNumber() {
        return "0000000000000000";
        //return "8888 8888 8888 8888";
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
    
    private void setCostForType(String type) {
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
