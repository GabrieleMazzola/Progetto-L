package test;

import machines.TicketMachine;
import ticket.SingleType;

/**
 *
 * @author Manuele
 */
public class TestCardPayment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicketMachine tMachine = new TicketMachine(0, 5000, "192.168.1.9");
        
        tMachine.setTicketToSell(new SingleType());
        
        tMachine.setTicketToSell(new SingleType());
    }
}
