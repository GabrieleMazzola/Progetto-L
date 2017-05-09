/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;

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
    private double ink; //valore percentuale
    private int paper; //valore percentuale
    //private State s;    
    public MoneyHandler moneyTank;

    private StubMachine stub;

    public TicketMachine(int cod, String ipAdress) {
        //this.s = State.FULL;
        this.cod = cod;
        this.moneyTank = new MoneyHandler();
        ink = 5;
        paper = 4;
        stub = new StubMachine(ipAdress, cod);
    }

    public void setInk(double ink) {
        this.ink = ink;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public double getInk() {
        return ink;
    }

    public int getPaper() {
        return paper;
    }

    public void buyTicketCash(Ticket ticket) {
        insertedMoney = 0;
        cost = ticket.getCost();
    }

    public void buyTicketCreditCard(Ticket ticket) {
        if(checkCreditCard(getCredCardNumber()))
            printTicket();
        else
            System.out.println("Sei un pirla");
    }

//    //todo
    public void buyTicket(Ticket t, PaymentMethod method) {
        switch (method) {
            case CASH:
                buyTicketCash(t);
                return;
            case CREDITCARD:
                buyTicketCreditCard(t);
                return;
            default:
                throw new AssertionError(method.name());
        }
    }
//    //todo

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

    private int printTicket() {
        if (ink > 0) {
            ink -= 0.5;
        } else {
            return -1;      // do exeption
        }
        if (paper > 0) {
            paper--;
        } else {
            return -1; // do exeption
        }
        return 1;
    }

    private void outputChange() {
        //do exeption
        System.out.println("RESTO DI: " + moneyTank.giveChange(cost, insertedMoney));
    }
    
    private String getCredCardNumber() {
        return "8888 8888 8888 8888";
    }

    private boolean checkCreditCard(String credCardNumber) {
        
        //TODO
        
        return true;
    }

}
