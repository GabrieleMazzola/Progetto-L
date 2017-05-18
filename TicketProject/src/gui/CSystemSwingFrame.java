package gui;

import centralsystem.CSystem;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Manuele
 */
public class CSystemSwingFrame extends JFrame{
    private JTabbedPane mainPanel;
    
    public CSystemSwingFrame(CSystem cSystem) {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,300);
        
        mainPanel = new JTabbedPane();
        mainPanel.add("Machines Status", new MachineStatusPanel(cSystem));
        mainPanel.add("For the PROs", new CSystemSwingPanel(cSystem));
        
        this.add(mainPanel);
    }
}
