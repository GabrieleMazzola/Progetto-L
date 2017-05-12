package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class TicketMachineSwingFrame extends JFrame{
    private JPanel mainPanel;
    private TicketMachine ticketMachine;
    
    public TicketMachineSwingFrame(TicketMachine tMachine) {
        super();
        this.setSize(400,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ticketMachine = tMachine;
        
        MainMachineSwingPanel mainPanel = new MainMachineSwingPanel(tMachine);
        this.add(mainPanel);
        
        //Setto al centro la posizione del frame
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        setLocation(x, y);
    }
}
