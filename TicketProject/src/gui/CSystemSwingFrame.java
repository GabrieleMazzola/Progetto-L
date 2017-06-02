package gui;

import centralsystem.CSystem;
import creator.CSystemFactory;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 *
 * @author Manuele
 */
public class CSystemSwingFrame extends JFrame implements Observer{
    private JPanel mainPanel, buttonPanel;
    private JTabbedPane tabbedPane;
    
    public CSystemSwingFrame(CSystem cSystem) {
        super();
        cSystem.addObserver(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,400);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Machines Status", new MachineStatusPanel(cSystem));
        tabbedPane.add("Activities", new CSystemActivitiesSwingPanel(cSystem));
        tabbedPane.add("Recieved JSON packages [PROs only]", new CSystemJSONSwingPanel(cSystem));
        
        buttonPanel = new CSystemButtonSwingPanel(cSystem);
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        
        this.add(mainPanel);
        
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Boolean) {
            if((boolean)arg) {
                this.dispose();
                CSystem newSystem = CSystemFactory.getInstance().getCentralSystemInstance();
                JFrame newFrame = new CSystemSwingFrame(newSystem);
                newFrame.setVisible(true);
            }
            else {
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}
