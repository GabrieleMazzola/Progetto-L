package gui;

import JSONSingleton.JSONOperations;
import centralsystem.CSystem;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Manuele
 */
public class CSystemJSONSwingPanel extends JPanel implements Observer{
    private JSONOperations decoder;
    private CSystem cSystem;
    private DefaultListModel listModelMessages;
    private JList listMessages;
    private JScrollPane messagesPanel;
    
    public CSystemJSONSwingPanel(CSystem cSystem) {
        this.cSystem = cSystem;
        cSystem.addObserver(this);
        
        decoder = JSONOperations.getInstance();
        
        int height = this.getHeight();
        
        listModelMessages = new DefaultListModel();
        for(String message : cSystem.getLog()) listModelMessages.addElement(message);
        listMessages = new JList(listModelMessages);
        messagesPanel = new JScrollPane(listMessages);
        
        listMessages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getClickCount() >= 2) {
                    String encodedString = (String)listMessages.getSelectedValue();

                    JPanel panel = new JPanel();
                    JTextArea decodedMessage = new JTextArea();
                    decodedMessage.setEditable(false);
                    try {
                        setupDecodedMessage(encodedString, decodedMessage);
                    }
                    catch(ParseException ex) {
                        decodedMessage.setText("Could not parse this message");
                    }
                    panel.add(decodedMessage);
                    add(panel, BorderLayout.EAST);
                }
            }
        });
        
        this.setLayout(new BorderLayout());
        this.add(messagesPanel);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            listModelMessages.addElement(arg);
        }
    }
    
    private void setupDecodedMessage(String encodedString, JTextArea textArea) throws ParseException{
        String[] decodedString = decoder.decodeRead(encodedString).split("\t");
        switch(decodedString[0]) {
            case "UPDATEMACHINESTATUS":
                textArea.setText("Updating machine status:\nMachine id: " + decodedString[1] + "\nInk level: " + decodedString[2] + "\nPaper level: " + decodedString[3]);
                break;
        }
    }
}
