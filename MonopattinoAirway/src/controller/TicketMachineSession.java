package controller;

import items.Product;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;


public class TicketMachineSession {
    private double insertedMoney, change;
    private Product toSell;
    private TicketMachine tMachine;
    private Operation current, previous;
    
    public TicketMachineSession(TicketMachine tMachine) {
        this.tMachine = tMachine;
        insertedMoney = 0;
        current = previous = Operation.SELLING_TICKET;
    }
    
    public double getSelectedProductCost() {
        return toSell.getCost();
    }
    
    public double getInsertedMoney() {
        return insertedMoney;
    }
    
    public double getChange() {
        return change;
    }
    
    public void selectBuyPhysicalTicket() {
        setNewOperation(Operation.BUYING_PHYSICAL);
    }
    
    public void selectBuyPhysicalSeason() {
        setNewOperation(Operation.BUYING_PSEASON);
    }
    
    public void selectBuyPhysicalSimple() {
        setNewOperation(Operation.BUYING_PTICKET);
    }
    
    public void selectBuySeason() {
        setNewOperation(Operation.BUYING_SEASON);
    }
    
    public void selectBuyTicket() {
        setNewOperation(Operation.BUYING_SINGLE);
    }
    
    public void startSale(String typeToSell) {
        toSell = tMachine.getProduct(typeToSell);
        setNewOperation(Operation.SELECTING_PAYMENT);
    }
    
    public void selectCashPayment() {
        setNewOperation(Operation.INSERTING_COINS);
    }
    
    public void selectCardPayment() {
        tMachine.setOperation(Operation.INSERTING_CCARD);
    }
    
    public void insertingMoney(double value) {
        insertedMoney += value;
        tMachine.insertMoney(value);
        if(insertedMoney >= toSell.getCost()) {
            change = tMachine.sellTicket(toSell, insertedMoney);
            tMachine.createSale(toSell);
            setNewOperation(Operation.PRINTING_TICKET);
        }
    }
    
    public boolean insertingCardNumber(String cardNumber) {
        if(tMachine.sellTicket(toSell, cardNumber)) {
            tMachine.createSale(toSell);
            setNewOperation(Operation.PRINTING_TICKET);
            return true;
        }
        else
            return false;
    }
    
    public void selectLogin() {
        setNewOperation(Operation.LOGGING_IN);
    }
    
    public boolean login(String username, String password) {
        if(tMachine.login(username, password)) {
            setNewOperation(Operation.CHOOSING_TICKET);
            return true;
        }
        else
            return false;
    }
    
    public void selectSignup() {
        setNewOperation(Operation.CREATING_USER);
    }
    
    public boolean signup(String name, String surname, String cf, String username, String psw, String email) {
        return tMachine.createUser(name, surname, cf, username, psw, email);
    }
    
    public boolean logout() {
        setNewOperation(Operation.SELLING_TICKET);
        return tMachine.logout();
    }
    
    public void back() {
        current = previous;
        previous = Operation.SELLING_TICKET;
        tMachine.setOperation(current);
    }
    
    private void setNewOperation(Operation newOperation) {
        previous = current;
        current = newOperation;
        tMachine.setOperation(current);
        insertedMoney = 0;
    }
}
