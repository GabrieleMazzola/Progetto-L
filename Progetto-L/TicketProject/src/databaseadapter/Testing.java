/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseadapter;

/**
 *
 * @author Gabriele
 */
public class Testing {
    
    public static void main(String[] args) {
        DatabaseAdapter mongoDB = new DatabaseAdapter();
        mongoDB.addUser("Simone", "Colosi", "Millisecondi");
        mongoDB.addTicket(new TicketDB(TicketType.SINGLE));
        mongoDB.addTicket(new TicketDB(TicketType.SINGLE));
        mongoDB.activateTicket("0");
        System.out.println(mongoDB.checkUser("Millisecondi"));  //true
        System.out.println(mongoDB.checkUser("kajbsc"));        //false
        System.out.println(mongoDB.existsTicket("0"));          //true
        System.out.println(mongoDB.existsTicket("1"));          //true
        System.out.println(mongoDB.existsTicket("2"));          //false
        System.out.println(mongoDB.isValid("0"));               //true
        System.out.println(mongoDB.isValid("1"));               //false
        
        mongoDB.printTickets();
        mongoDB.printUsers();
        
    }
    
}
