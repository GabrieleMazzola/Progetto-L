package machines;

import java.util.HashMap;
import java.util.Map;
import paymentMethods.PaymentMethod;
import ticket.*;
import people.User;

/**
 *
 * @author Andrea
 */
public class TicketMachine {
    private int cod;
    private double insertedMoney;
    private double cost;
    public ResourcesHandler resources;
    public MoneyHandler moneyTank;
    private StubMachine stub;
    private Map<String,Double> ticketTemplate;

    public TicketMachine(int cod, String ipAdress) {
        this.cod = cod;
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        ticketTemplate = new HashMap();
        setupTicketTemplate();
        stub = new StubMachine(ipAdress, cod);
    }
    
    public double getInk() {
        return resources.getInkPercentage();
    }
    
    public double getPaper() {
        return resources.getPaperPercentage();
    }

    public void buyTicket(TicketType type, PaymentMethod method) {
        getCost(type);
        switch (method) {
            case CASH:
                insertedMoney = 0;
                return;
            case CREDITCARD:
                return;
            default:
                throw new AssertionError(method.name());
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
        insertedMoney += money;
        if (isEnoughMoney(insertedMoney)) {
            sellTicket(PaymentMethod.CASH);
        }
    }

    private boolean isEnoughMoney(double money) {
        if (money >= cost) {
            return true;
        }
        return false;
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
    }

    private void sellTicketNotCash() {   //serve sia per bancomat e per carta di credito
        printTicket();
    }

    private void printTicket() {
        resources.printTicket();
    }

    private void outputChange() {
        //do exeption
        System.out.println("RESTO DI: " + moneyTank.giveChange(cost, insertedMoney));
    }
    
    private String getCredCardNumber() {
        return "8888 8888 8888 8888";
    }

    private boolean checkCreditCard(String credCardNumber) {
        return true;
    }
    
    private void setupTicketTemplate() {
        ticketTemplate.put("Single", 1.50);
        ticketTemplate.put("Multi", 5.70);
    }
    
    private void getCost(TicketType type) {
        if(ticketTemplate.containsKey(type.name()))
            cost = ticketTemplate.get(type.name());
        else cost = 0; //to do eccezzione
    }
}
