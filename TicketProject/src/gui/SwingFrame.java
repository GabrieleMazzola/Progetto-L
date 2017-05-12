package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class SwingFrame extends JFrame{
    private JPanel mainPanel;
    private TicketMachine ticketMachine;
    
    public SwingFrame(TicketMachine tMachine) {
        super();
        this.setSize(400,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ticketMachine = tMachine;
        
        MainMachineSwingPanel mainPanel = new MainMachineSwingPanel(tMachine);
        this.add(mainPanel);
    }
}
