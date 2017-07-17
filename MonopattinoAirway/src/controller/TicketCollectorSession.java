package controller;

import items.Fine;
import ticketcollector.CollectorOperation;
import ticketcollector.TicketCollector;


public class TicketCollectorSession {
    private TicketCollector collector;
    private Long fineNumber;
    private String username;
    private boolean connected;
    
    private CollectorOperation current, previous;
    
    public TicketCollectorSession(TicketCollector collector) {
        this.collector = collector;
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean login(String username, String password) {
        if(connected)
            return true;
        
        else if(collector.loginCollector(username, password)){
            this.username = username;
            connected = true;
            fineNumber = collector.requestFinesStartNumber(username);
            setNewOperation(CollectorOperation.SELECTING_OPERATION);
            return true;
        }
        
        else
            return false;
    }
    
    public void logout() {
        username = null;
        connected = false;
        setNewOperation(CollectorOperation.LOGGING_OUT);
    }
    
    public void selectedMakingFine() {
        setNewOperation(CollectorOperation.MAKING_FINE);
    }
    
    public void selectedCheckingTicket() {
        setNewOperation(CollectorOperation.VERIFYING_TICKET);
    }
    
    public Boolean makingFine(String cf, double amount) {
        if(connected){
            System.out.println("Ok");
            Fine fine = new Fine(username+fineNumber, cf, amount, username);
            fineNumber++;
            
            return collector.addFine(fine);
        }
        return null;
    }
    
    public Boolean checkingTicket(long code) {
        return collector.existsTicket(code);
    }
    
    public void back() {
        current = previous;
        previous = CollectorOperation.SELECTING_OPERATION;
        collector.setOperation(current);
    }
    
    private void setNewOperation(CollectorOperation newOperation) {
        previous = current;
        current = newOperation;
        collector.setOperation(current);
    }
}
