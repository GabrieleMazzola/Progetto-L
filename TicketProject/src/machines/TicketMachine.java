package machines;

import codegeneration.CodeHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import paymentMethods.PaymentMethod;
import ticket.*;
import people.User;

/**
 *
 * @author Andrea
 */
public class TicketMachine extends Observable{
    private int cod;
    private int numberOfCodes = 10;
    public ResourcesHandler resources;
    public MoneyHandler moneyTank;
    private StubMachine stub;
    private Map<TicketType,Double> ticketTemplate;
    private String ticketCodes;
    private String logged;
    private Operation operation;
 
    private Timer timer;
    private TimerTask updateMachineTask;
    
    private double insertedMoney;
    private double cost;
    private TicketType type;
    private PaymentMethod pMethod;
    private ArrayList<Integer> serialNumber;

    public TicketMachine(int PORTA_SERVER, String ipAdress) {
        this.cod =(int)( Math.random()*100);
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        ticketTemplate = new HashMap();
        setupTicketTemplate();
        stub = new StubMachine(ipAdress, PORTA_SERVER, this);
        serialNumber = new ArrayList();
        operation = Operation.SELLING_TICKET;
        timer=new Timer();
        initUpdateMachineTask();
        
        timer.schedule(updateMachineTask,3000,3000);
    }
    
    //__________________Metodi getter___________________________________________
    public double getInk() {
        return resources.getInkPercentage();
    }
    
    public double getPaper() {
        return resources.getPaperPercentage();
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
    
    public int getAmountOf(double value) {
        return moneyTank.getQuantityOf(value);
    }
    
    public int getAmountByIndex(int index) {
        return moneyTank.getSingleQuantitybyIndex(index);
    }
    
    /**
     * 
     * @return I codici che la macchinetta può usare
     */
    public String getTicketCode(){
        return this.ticketCodes;
    }
    
    //__________________Metodi per la vendita di biglietti______________________
    /**
     * Setta il tipo di biglietto da vendere. In tal modo la macchinetta sa
     * quanto bisogna che l'utente paghi
     * @param type
     */
    public void setTicketToSell(TicketType type) {
        this.type = type;
        setCostForType(type);
        operation = Operation.SELECTING_PAYMENT;
        notifyChange(operation);
    }
    
    /**
     * Setta il tipo di pagamento scelto per pagare
     * @param pMethod
     */
    public void setPaymentMethod(PaymentMethod pMethod) {
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
    public boolean buyTicket() {
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
    
    private void printTicket() {
        resources.printTicket();
        System.out.println("Ticket printed");
        operation = Operation.PRINTING_TICKET;
        notifyChange(operation);
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
    
    public void cancel() {
        operation = Operation.SELLING_TICKET;
        notifyChange(operation);
    }
    
    //__________________Metodi per la gestione dei codici_______________________
    /**
     * Setta i codici che la macchinetta può usare
     * @param ticketCodes
     */
    public void setTicketCode(String ticketCodes) {
        this.ticketCodes = ticketCodes;
    }

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
    
    private void setupTicketTemplate() {
        ticketTemplate.put(TicketType.SINGLE, 1.50);
        ticketTemplate.put(TicketType.MULTI, 5.70);
    }
    
    private void setCostForType(TicketType type) {
        if(ticketTemplate.containsKey(type)) {
            cost = ticketTemplate.get(type);
        }
        else cost = 0; //to do eccezzione
    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
    
    public void printCoins() {
        moneyTank.printCoinsInTank();
    }

    private void initUpdateMachineTask() {
        updateMachineTask = new TimerTask () {
            @Override
            public void run () {
                
               if(resources.getInkPercentage()>0 && resources.getPaperPercentage()>0) stub.updateMachineStatus(cod, resources.getInkPercentage(), resources.getPaperPercentage(), true);
               else stub.updateMachineStatus(cod, resources.getInkPercentage(), resources.getPaperPercentage(), false);
            }
        };
    }
    
    public void startUpdateSerial(){
        stub.requestCodes(numberOfCodes);
    }
    
    public void endUpdateSerial(ArrayList<Integer> serialNumbers){
        this.serialNumber.addAll(serialNumbers);
    }
    
    public void setOperation(Operation operation) {
        this.operation = operation;
        notifyChange(operation);
    }
}
