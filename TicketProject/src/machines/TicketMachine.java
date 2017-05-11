package machines;

import java.util.HashMap;
import java.util.Map;

import centralsystem.CentralSystemTicketInterface;
import paymentMethods.PaymentMethod;
import ticket.*;
import people.User;


public class TicketMachine {
    private int cod;
    public ResourcesHandler resources;
    public MoneyHandler moneyTank;
    private CentralSystemTicketInterface stub;
    private Map<String,Double> ticketTemplate;
    private String ticketCodes;

    public TicketMachine(int cod, String ipAdress) {
        this.cod = cod;
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        ticketTemplate = new HashMap();
        setupTicketTemplate();
        stub = new StubMachine(ipAdress, cod,this);
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
    
    public void setTicketToSell(TicketType type) {
        this.type = type;
        setCostForType(type);
        System.out.println(cost);
    }
    
    public void setPaymentMethod(PaymentMethod pMethod) {
        this.pMethod = pMethod;
    }

    public void buyTicket() {
        switch (pMethod) {
            case CASH:
                insertedMoney = 0;
                System.out.println("Selected: Single Ticket. Payment: Cash. Cost: " + cost);
                return;
            case CREDITCARD:
                return;
            default:
                throw new AssertionError(pMethod.name());
        }
    }
    
    public void buyTicketCreditCard(Ticket ticket) {
        if(checkCreditCard(getCredCardNumber()))
            printTicket();
        else
            System.out.println("Sei un pirla");
    }

    void logIn(User u) {
    }

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
        return true;
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

    public void setTicketCode(String ticketCodes) {
        this.ticketCodes = ticketCodes;
    }
    
    public String getTicketCode(){
        return this.ticketCodes;
    }
    
}
