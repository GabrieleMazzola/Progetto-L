package test;

import gui.MoneyTankFrame;
import gui.TicketMachineSwingFrame;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class TestGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicketMachine tm = new TicketMachine(0, 5000, "127.0.0.1");
        
        TicketMachineSwingFrame frame = new TicketMachineSwingFrame(tm);
        frame.setVisible(true);
        
        tm.printCoins();
        MoneyTankFrame moneyFrame = new MoneyTankFrame(tm);
        moneyFrame.setVisible(true);
    }
}
