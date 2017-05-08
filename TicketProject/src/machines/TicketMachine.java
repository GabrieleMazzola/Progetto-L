/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;
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
    private double ink;
    private int paper;
    //private State s;    
    public MoneyHandler moneyTank;
    
    
    
    public TicketMachine(int cod){
        //this.s = State.FULL;
        this.cod = cod;
        this.moneyTank = new MoneyHandler();
        ink=5;
        paper =4;
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
    
    public void buyTicket(Ticket ticket){
        insertedMoney=0;
        cost = ticket.getCost();
    }
    
//    //todo
void buyTicket(Ticket t, User u){        
}
//    //todo
void logIn(User u){        
}
    public void insertMoney(double money){
        moneyTank.addMoney(money);
        insertedMoney += money;
        if(isEnoughMoney(insertedMoney)){
            sellTicket();
        }
    }
    
    private boolean isEnoughMoney(double money){
        if(money >= cost)
            return true;
        return false;
    }
    
    private void sellTicket() {
        printTicket();
        outputChange();
        cost=0;
        insertedMoney=0;
        System.out.println("BIGLIETTO VENDUTO\n");
    }

    private int printTicket() {
        if(ink>0){
            ink-=0.5;
        }else{
            return -1;      // do exeption
        }
        if(paper>0){
            paper--;
        }else{
            return -1; // do exeption
        }
        return 1;
    }

    private void outputChange() {
         //do exeption
        System.out.println("RESTO DI: " + moneyTank.giveChange(cost, insertedMoney));
    }
  
}
