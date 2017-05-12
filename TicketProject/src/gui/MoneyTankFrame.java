package gui;

import javax.swing.JFrame;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class MoneyTankFrame extends JFrame{
    public MoneyTankFrame(TicketMachine tm) {
        super();
        
        setSize(200,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(new MoneyTankSwingPanel(tm));
    }
}
