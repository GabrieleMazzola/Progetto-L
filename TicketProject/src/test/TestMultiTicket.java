package test;

import java.util.concurrent.TimeUnit;
import ticket.MultiTicket;
import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public class TestMultiTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Ticket mTicket = new MultiTicket(10, 5, 2);
        System.out.println("Valido:"+mTicket.isValid());
        mTicket.validate();
        System.out.println("Valido:"+mTicket.isValid());
        try {
            
            TimeUnit.SECONDS.sleep(6);
            System.out.println("Valido dopo 6 sec:"+ mTicket.isValid());
        }
        catch(InterruptedException exc) {
            
            System.out.println("Interrupted");
        }
        mTicket.validate();
        System.out.println("Valido la seconda volta:"+mTicket.isValid());
        try {
            
            TimeUnit.SECONDS.sleep(6);
            System.out.println("Valido dopo 6 sec:"+ mTicket.isValid());
        }
        catch(InterruptedException exc) {
            
            System.out.println("Interrupted");
        }
        mTicket.validate();
        System.out.println("Valido la terza volta:"+mTicket.isValid());
    }
}
