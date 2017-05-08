package test;

import exceptions.TicketExausted;
import java.util.concurrent.TimeUnit;
import ticket.MultiTicket;
import ticket.Ticket;
import machines.*;
import ticket.SingleTicket;
/**
 *
 * @author Manuele
 */
public class TestMultiTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // test macchinetta
        TicketMachine tm = new TicketMachine(0);
        tm.moneyTank.setSingleQuantity(1, 6);
        tm.moneyTank.setSingleQuantity(2, 5);
        tm.moneyTank.setSingleQuantity(5, 1);
        
        System.out.println("totale è " + tm.moneyTank.getTotal());
        
        tm.buyTicket(new SingleTicket(3,3));
        
        tm.insertMoney(1);       
        tm.insertMoney(1);
        tm.insertMoney(5);
        System.out.println("totale è " + tm.moneyTank.getTotal());
        
          /* test money holder
          MoneyHandler mh =  new MoneyHandler();
          
          mh.setSingleQuantity(2, 0);
          mh.setSingleQuantity(5, 2);
          mh.setSingleQuantity(1, 0);
          
          System.out.println("totale è " + mh.getTotal());          
          System.out.println("Resto " + mh.returnMoney(5, 10));
          System.out.println("totale è " + mh.getTotal()); 
          */
//        Ticket mTicket = new MultiTicket(10, 5, 2);
//        System.out.println("Valido:"+mTicket.isValid());
//        try {mTicket.validate();}
//        catch(TicketExausted exc) {
//            
//            System.out.println(exc);
//        }
//        System.out.println("Valido:"+mTicket.isValid());
//        try {
//            
//            TimeUnit.SECONDS.sleep(6);
//            System.out.println("Valido dopo 6 sec:"+ mTicket.isValid());
//        }
//        catch(InterruptedException exc) {
//            
//            System.out.println("Interrupted");
//        }
//        try {mTicket.validate();}
//        catch(TicketExausted exc) {
//            
//            System.out.println(exc);
//        }
//        System.out.println("Valido la seconda volta:"+mTicket.isValid());
//        try {
//            
//            TimeUnit.SECONDS.sleep(6);
//            System.out.println("Valido dopo 6 sec:"+ mTicket.isValid());
//        }
//        catch(InterruptedException exc) {
//            
//            System.out.println("Interrupted");
//        }
//        try {mTicket.validate();}
//        catch(TicketExausted exc) {
//            
//            System.out.println(exc);
//        }
//        System.out.println("Valido la terza volta:"+mTicket.isValid());
    }
}
