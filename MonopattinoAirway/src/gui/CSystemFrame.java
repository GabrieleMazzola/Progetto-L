package gui;

import centralsystem.CSystem;
import centralsystem.factory.CSystemFactory;
import database.factories.SimMapperFactory;
import gui.csystem.*;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;


public class CSystemFrame extends JFrame implements Observer{
    private JPanel mainPanel, buttonPanel;
    private JTabbedPane mainPane;
    
    public CSystemFrame(CSystem cSystem) {
        super();
        cSystem.addObserver(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,400);
        
        mainPane = new JTabbedPane();
        mainPane.add("Machines Status", new MachineStatusPanel(cSystem));
        mainPane.add("Activities", new CSystemActivitiesPanel(cSystem));
        
        buttonPanel = new CSystemButtonPanel(cSystem);
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(mainPane);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        
        this.add(mainPanel);
        
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    //TODO aggiungere possibilità di restart
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Boolean) {
            boolean restart = (Boolean) arg;
            if(restart) {
                this.dispose();
                CSystemFactory.getInstance().buildCSystem(SimMapperFactory.class.getCanonicalName());
            }
            else
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
