
package test;

import machines.StubMachine;
import machines.TicketMachine;
import ticketCollector.Fine;
import ticketCollector.StubCollector;

public class TesterLogin {
    
    public static void main(String[] args) {
        
        TicketMachine machine123 = new TicketMachine(5000,"10.87.130.83"); 
        
        StubMachine machine = new StubMachine("10.87.130.83", 5000,machine123);
        System.out.println(machine.userLogin("ADMIN", "ADMIN"));    //true
        System.out.println(machine.userLogin("ADMIN", "ADMIASCN")); //false
        System.out.println(machine.requestCodes());
        System.out.println(machine.cardPayment("685165316"));   //true
        
        System.out.println(machine123.getTicketCode());
        
        
        //test stubCollector
//        StubCollector collector = new StubCollector("10.87.232.53", 5000);
//        System.out.println(collector.existsTicket("5"));		//true
//        System.out.println(collector.existsTicket("51"));		//false
//        System.out.println(collector.collectorLogin("ADMIN", "ADMIN"));
//        collector.makeFine(new Fine("ashcbascjhb",100));
        

    }
}
