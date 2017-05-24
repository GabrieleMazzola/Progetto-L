package test;

import exceptions.TicketTypeNotFoundException;
import machines.TicketMachine;
import paymentMethods.PaymentMethod;
import ticket.TicketType;

/**
 *
 * @author Manuele
 */
public class TestCardPayment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicketMachine tMachine = new TicketMachine(5000, "192.168.1.9");
        
        tMachine.setTicketToSell(TicketType.SINGLE);
        tMachine.setPaymentMethod(PaymentMethod.CREDITCARD);
        
        try{
            tMachine.buyTicket();

            tMachine.setTicketToSell(TicketType.SINGLE);
            tMachine.setPaymentMethod(PaymentMethod.CREDITCARD);

            tMachine.buyTicket();
        }
        catch(TicketTypeNotFoundException exc){
            System.err.println(exc);
        }
    }
}
