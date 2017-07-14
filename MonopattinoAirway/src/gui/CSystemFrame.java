package gui;

import centralsystem.CSystem;
import gui.csystem.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 *
 * @author Zubeer
 */
public class CSystemFrame extends JFrame implements Observer{
    private JPanel mainPanel, buttonPanel;
    private JTabbedPane mainPane;
    private JButton close;
    
    /**
     *
     * @param cSystem
     */
    public CSystemFrame(CSystem cSystem) {
        super();
        cSystem.addObserver(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,400);
        
        mainPane = new JTabbedPane();
        mainPane.add("Machines Status", new MachineStatusPanel(cSystem));
        mainPane.add("Activities", new CSystemActivitiesPanel(cSystem));
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(mainPane);
        
        this.add(mainPanel);
        
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        close = new JButton("Close server");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                closeWindow();
            }
        });
        
        buttonPanel = new JPanel();
        buttonPanel.add(close);
        
        JButton show = new JButton("Show products");
        show.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                cSystem.printProducts();
            }
        });
        buttonPanel.add(show);
        
        this.add(buttonPanel, BorderLayout.PAGE_END);
        
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    //TODO aggiungere possibilità di restart
    @Override
    public void update(Observable o, Object arg) {
        
    }
    
    public void closeWindow() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
