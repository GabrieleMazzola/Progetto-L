/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import TicketCollector.Fine;
import databaseadapter.DatabaseAdapter;
import databaseadapter.*;

/**
 *
 * @author Gabriele
 */
public class CSystem {
    private DatabaseAdapter database;

    public CSystem() {
        this.database = new DatabaseAdapter();
        
        initTickets();
        initUsers();
    }
    
    private void initTickets() {
        for(int i = 0; i < 10; i++) {
            database.addTicket(new TicketDB(TicketType.SINGLE));
        }
        
        for(int i = 0; i < 10; i++) {
            database.addTicket(new TicketDB(TicketType.SEASON));
        }
    }
    
    private void initUsers() {
        for(int i = 0; i < 10; i++) {
            database.addUser(i+"", i+"", i+"");
        }
    }
    
    
    public boolean checkUser(String cf){
        return database.checkUser(cf);
    }
    
    public boolean addUser(String name, String surname, String cf) {
        return database.addUser(name, surname, cf);
    }
    
    /*
    public boolean addUser(User user) {
        return database.addUser(user.getName, user.getSurname, user.getCf);
    
    }
    */

    public boolean addFine(Fine fine) {
        return database.addFine(fine);
    }
    
    public boolean existsTicket(String ticketCode) {
        return database.existsTicket(ticketCode);
    }
    
    public boolean isValid(String ticketCode) {
        return database.isValid(ticketCode);
    }
    
    public void printTickets() {
        database.printTickets();
    }
    
    public void printUsers() {
        database.printUsers();
    }
}
