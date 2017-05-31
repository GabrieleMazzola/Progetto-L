package test;

import centralsystem.CSystem;
import centralsystem.LogCS;
import machines.MoneyHandler;
import machines.TicketMachine;
import paymentMethods.PaymentMethod;
import ticket.TicketType;

/**
 *
 * @author Manuele
 */
public class TestMachine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         CSystem cs = new CSystem();
         
         LogCS.getInstance().abilita();
         
     
    }
}
