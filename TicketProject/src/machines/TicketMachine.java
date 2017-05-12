package machines;

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
    public ResourcesHandler resources;
    public MoneyHandler moneyTank;
    private StubMachine stub;
    private Map<TicketType,Double> ticketTemplate;
    private String ticketCodes;
 
    Timer timer;
    TimerTask updateMachineTask;
    
    private double insertedMoney;
    private double cost;
    private TicketType type;
    private PaymentMethod pMethod;

    public TicketMachine(int PORTA_SERVER, String ipAdress) {
        this.cod =(int)( Math.random()*10);
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        ticketTemplate = new HashMap();
        setupTicketTemplate();
        stub = new StubMachine(ipAdress, PORTA_SERVER, this);
        
        timer=new Timer();
        initUpdateMachineTask();
        
        timer.schedule(updateMachineTask,3000,3000);
       
        
    }
    
    public double getInk() {
        return resources.getInkPercentage();
    }
    
    public double getPaper() {
        return resources.getPaperPercentage();
    }
    
    public double getInsertedMoney() {
        return insertedMoney;
    }
    
    /**
     * 
     * @param type 
     * Setta il tipo di biglietto da vendere. In tal modo la macchinetta sa
     * quanto bisogna che l'utente paghi
     */
    public void setTicketToSell(TicketType type) {
        this.type = type;
        setCostForType(type);
        System.out.println(cost);
    }
    
    /**
     * 
     * @param pMethod
     * Setta il tipo di pagamento scelto per pagare
     */
    public void setPaymentMethod(PaymentMethod pMethod) {
        this.pMethod = pMethod;
    }
    
    /**
     * Effettua la vendita del biglietto in base al tipo di biglietto e al metodo
     * di pagamento scelto
     */
    public void buyTicket() {
        switch (pMethod) {
            case CASH:
                insertedMoney = 0;
                System.out.println("Selected: Single Ticket. Payment: Cash. Cost: " + cost);
                return;
            case CREDITCARD:
                buyTicketCreditCard();
                return;
            default:
                throw new AssertionError(pMethod.name());
        }
    }
    
    /**
     * 
     * @param money 
     * Consente di inserire una moneta/banconota del valore specificato. Nel caso 
     * in cui i soldi inseriti siano sufficienti per comprare il biglietto selezionato
     * viene automaticamente effettuata la vendita restituendo eventualmente il resto.
     * Se non è stato selezionato alcun biglietto o il metodo di pagamento CASH,
     * le monete inserite non vengono salvate
     */
    public void insertMoney(double money) {
        moneyTank.addMoney(money);
        notifyChange(money);
        insertedMoney += money;
        int temp = (int)Math.floor(insertedMoney*100);
        insertedMoney = (double)temp/(double)100;
        System.out.println(insertedMoney);
        notifyChange(insertedMoney);
        if (isEnoughMoney(insertedMoney)) {
            sellTicket(PaymentMethod.CASH);
        }
    }
    
    /**
     * 
     * @param ticketCodes
     * Setta i codici che la macchinetta può usare
     */
    public void setTicketCode(String ticketCodes) {
        this.ticketCodes = ticketCodes;
    }
    
    /**
     * 
     * @return Ritorna i codici che la macchinetta può usare
     */
    public String getTicketCode(){
        return this.ticketCodes;
    }
    
    private void buyTicketCreditCard() {
        if(checkCreditCard(getCredCardNumber()))
            printTicket();
        else
            System.out.println("Carta di credito non riconosciuta");
    }

    void logIn(User u) {
    }

    private boolean isEnoughMoney(double money) {
        return money >= cost;
    }

    private void sellTicket(PaymentMethod method) {
        switch (method) {
            case CASH:
                sellTicketCash();
                return;
            case CREDITCARD:
                sellTicketNotCash();
                return;
            default:
                throw new AssertionError(method.name());
        }
    }
    
    private void sellTicketCash() {
        printTicket();
        outputChange();
        cost = 0;
        insertedMoney = 0;
        notifyChange(insertedMoney);
    }

    private void sellTicketNotCash() {   //serve sia per bancomat e per carta di credito
        printTicket();
    }

    private void printTicket() {
        resources.printTicket();
    }

    private void outputChange() {
        //do exeption
        moneyTank.giveChange(cost, insertedMoney);
    }
    
    private String getCredCardNumber() {
        return "8888 8888 8888 8888";
    }
    
   

    private boolean checkCreditCard(String credCardNumber) {
        return stub.cardPayment(credCardNumber);
    }
    
    private void setupTicketTemplate() {
        ticketTemplate.put(TicketType.SINGLE, 1.50);
        ticketTemplate.put(TicketType.MULTI, 5.70);
    }
    
    private void setCostForType(TicketType type) {
        if(ticketTemplate.containsKey(type)) {
            System.out.println("Found");
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
    
    public int getAmountOf(double value) {
        return moneyTank.getQuantityOf(value);
    }
    
    public int getAmountByIndex(int index) {
        return moneyTank.getSingleQuantitybyIndex(index);
    }

    private void initUpdateMachineTask() {

        updateMachineTask = new TimerTask () {
            @Override
            public void run () {
                
               if(resources.getInkPercentage()>0 || resources.getPaperPercentage()>0) stub.updateMachineStatus(cod, resources.getInkPercentage(), resources.getPaperPercentage(), true);
               else stub.updateMachineStatus(cod, resources.getInkPercentage(), resources.getPaperPercentage(), false);
            }
    
        };
    }
    
    
}
