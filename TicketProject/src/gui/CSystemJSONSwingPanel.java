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
import org.json.simple.JSONObject;
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
    private JTextArea decodedMessage;
    
    private JScrollPane messagesPanel;
    private JPanel clearMessagePanel;
    
    public CSystemJSONSwingPanel(CSystem cSystem) {
        this.cSystem = cSystem;
        cSystem.addObserver(this);
        
        decoder = JSONOperations.getInstance();
        
        listModelMessages = new DefaultListModel();
        for(String message : cSystem.getLog()) listModelMessages.addElement(message);
        listMessages = new JList(listModelMessages);
        messagesPanel = new JScrollPane(listMessages);
        
        listMessages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getClickCount() >= 2) {
                    String encodedString = (String)listMessages.getSelectedValue();

                    try {
                        setupDecodedMessage(encodedString);
                    }
                    catch(ParseException exc) {
                        decodedMessage.setText("Could not decode");
                    }
                }
            }
        });
        
        clearMessagePanel = new JPanel();
        
        decodedMessage = new JTextArea();
        decodedMessage.setEditable(false);
        clearMessagePanel.add(decodedMessage);
        
        this.setLayout(new BorderLayout());
        this.add(messagesPanel);
        this.add(clearMessagePanel, BorderLayout.EAST);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            listModelMessages.addElement(arg);
        }
    }
    
    private void setupDecodedMessage(String encodedString) throws ParseException{
        String[] decodedString = decoder.decodeRead(encodedString).split("\t");
        switch(decodedString[0]) {
            case "UPDATEMACHINESTATUS":
                decodedMessage.setText("Updating machine status:\nMachine id: " + decodedString[1] + "\nInk level: " + decodedString[2] + "\nPaper level: " + decodedString[3]);
                break;
            case "TEST":
                decodedMessage.setText("This is just a test");
                break;
            case "CREATEUSER":
                decodedMessage.setText("Creating new user:\nName:" + decodedString[1] + "\nSurname: " + decodedString[2] + "\nUsername: " + decodedString[3]);
                break;
            case "MAKEFINE":
                decodedMessage.setText("Creating new fine:\nReciever: " + decodedString[1] + "\nAmount: " + decodedString[2]);
                break;
            case "COLLECTORLOGIN":
                decodedMessage.setText("Collector login with these credentials:\nUsename: " + decodedString[1] + "\nPassword: " + decodedString[2]);
                break;
            case "USERLOGIN":
                decodedMessage.setText("User login with these credentials:\nUsename: " + decodedString[1] + "\nPassword: " + decodedString[2]);
                break;
            case "CARDPAYMENT":
                decodedMessage.setText("Paying with credit card:\nCredit card number: " + decodedString[1] + "\nAmount paid: " + decodedString[2]);
                break;
            case "EXISTSTICKET":
                decodedMessage.setText("Verifying ticket existance:\nTicket code: " + decodedString[1]);
                break;
            case "REQUESTCODES":
                decodedMessage.setText("Request new codes");
                break;
        }
    }
}
