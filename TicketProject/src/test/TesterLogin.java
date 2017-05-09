
package test;

import machines.StubMachine;
import ticketCollector.StubCollector;

public class TesterLogin {
    
    public static void main(String[] args) {
        
        StubMachine machine = new StubMachine("10.87.232.53", 5000);
        System.out.println(machine.login("ADMIN", "ADMIN"));
        System.out.println(machine.login("ADMIN", "ADMIASCN"));
        
        
        StubCollector collector = new StubCollector("10.87.232.53", 5000);
        System.out.println(collector.existsTicket("10"));
        System.out.println(collector.existsTicket("5"));

    }
}
