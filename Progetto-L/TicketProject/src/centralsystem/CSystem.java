/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import ticketcollectors.Fine;
import databaseadapter.DatabaseAdapter;
import databaseadapter.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriele
 */

public class CSystem {
    private int PORTA_SERVER = 5000;
    private int NUMERO_MACCHINETTE = 1;
  
    private DatabaseAdapter database;
    private ServerSocket socketListener;
    private SocketHandler scHandler;
   
    
    public CSystem() {
        this.database = new DatabaseAdapter();
        initTickets();
        initUsers();
        initServer();
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
    private void initServer() {
        try {
            socketListener = new ServerSocket(PORTA_SERVER);
        } catch (IOException ex) {
            System.err.println("Errore apertura porta serverSocket");
        }
       
       scHandler = new SocketHandler(socketListener);
       scHandler.start();  
    }
    
}
