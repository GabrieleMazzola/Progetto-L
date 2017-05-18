package gui;

import centralsystem.CSystem;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Manuele
 */
public class CSystemJSONSwingPanel extends JPanel implements Observer{
    private CSystem cSystem;
    private DefaultListModel listModelMessages;
    private JList listMessages;
    private JScrollPane messagesPanel;
    
    public CSystemJSONSwingPanel(CSystem cSystem) {
        this.cSystem = cSystem;
        cSystem.addObserver(this);
        
        listModelMessages = new DefaultListModel();
        for(String message : cSystem.getLog()) listModelMessages.addElement(message);
        listMessages = new JList(listModelMessages);
        messagesPanel = new JScrollPane(listMessages);
        
        this.setLayout(new BorderLayout());
        this.add(messagesPanel);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            listModelMessages.addElement(arg);
        }
    }
}
