package creator;

import gui.MoneyTankFrame;
import gui.TicketMachineSwingFrame;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class TicketMachineRunningClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicketMachineFactory tmFactory = TicketMachineFactory.getInstance();
        TicketMachine tMachine = tmFactory.createTicketMachine();
        
        TicketMachineSwingFrame view = new TicketMachineSwingFrame(tMachine);
        view.setVisible(true);
        
        MoneyTankFrame debug = new MoneyTankFrame(tMachine);
        debug.setVisible(true);
    }
}
