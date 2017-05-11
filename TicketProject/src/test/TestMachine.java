package test;

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
        TicketMachine tMachine = new TicketMachine(0, "ipAddress");
        
        tMachine.setTicketToSell(TicketType.SINGLE);
//        tMachine.setPaymentMethod(PaymentMethod.CASH);
//        tMachine.buyTicket();
//        tMachine.insertMoney(1);
//        tMachine.insertMoney(2);
//        
//        tMachine.printCoins();
//        MoneyHandler mh = new MoneyHandler();
//        System.out.println(mh.giveChange(4.05, 5));
//        mh.printCoinsInTank();
//        System.out.println(5*100-4.90*100);
    }
}
