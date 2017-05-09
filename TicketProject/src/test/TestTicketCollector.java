/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import databaseadapter.DatabaseAdapter;
import databaseadapter.TicketDB;
import databaseadapter.TicketType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import ticketCollector.FineException;
import ticketCollector.TicketCollector;


/**
 *
 * @author Simone
 */
public class TestTicketCollector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DatabaseAdapter mongoDB = new DatabaseAdapter();
        mongoDB.addUser("Simone", "Colosi", "Millisecondi","qwert");
        mongoDB.addTicket(new TicketDB(TicketType.SINGLE));
        mongoDB.addTicket(new TicketDB(TicketType.SINGLE));
        mongoDB.activateTicket("0");
        mongoDB.printTickets();
        
        TicketCollector prova = new TicketCollector(mongoDB);
        try{
        if(prova.verifyValidation("0")){   
            System.out.println("co");
            BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("CF utente");
            prova.fine(c.readLine(), GregorianCalendar.getInstance().getTime());
                    }             
        prova.verifyValidation("3");  //errore
        
        
        }catch(FineException | IOException  e){
            System.out.println(e.getMessage());
        }
    }
    
}
