package test;

import centralsystem.CSystem;
import centralsystem.LogCS;
import machines.TicketMachine;

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
        TicketMachine tMachine = new TicketMachine(0, 5000, "10.87.232.53");
         
         LogCS.getInstance().abilita();
         
     
    }
}
