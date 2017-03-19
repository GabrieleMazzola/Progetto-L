package test;

import exceptions.TicketExausted;
import java.util.concurrent.TimeUnit;
import ticket.SingleTicket;
import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public class TestSingleTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Ticket sTicket = new SingleTicket(1.30, 5);
        System.out.println("Valido:"+sTicket.isValid());
        try {sTicket.validate();}
        catch(TicketExausted exc) {System.out.println(exc);}
        System.out.println("Valido dopo convalida:"+sTicket.isValid());
        try {
            
            TimeUnit.SECONDS.sleep(4);
            System.out.println("Valido dopo 4 sec:"+ sTicket.isValid());
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Valido dopo 6 sec:"+sTicket.isValid());
        }
        catch(InterruptedException exc) {
            
            System.out.println("Interrupted");
        }
    }
}
