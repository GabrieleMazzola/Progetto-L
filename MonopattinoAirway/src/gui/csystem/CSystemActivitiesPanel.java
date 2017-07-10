package gui.csystem;

import centralsystem.CSystem;
import centralsystem.Message;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 * Classe che mostra in chiaro le operazioni che svolge il sistema centrale.
 * Vengono passati dei messaggi il cui testo (del tipo "Attempting payment") viene
 * mostrato
 */
public class CSystemActivitiesPanel extends JPanel implements Observer{
    private DefaultListModel listModelActivities;
    private JList listActivities;
    private JScrollPane activitiesPane;

    public CSystemActivitiesPanel(CSystem cSystem) {
        super(new BorderLayout());
        
        cSystem.addObserver(this);
        
        listModelActivities = new DefaultListModel();
        listActivities = new JList(listModelActivities);
        activitiesPane = new JScrollPane(listActivities);
        
        this.add(activitiesPane);
        
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof CSystem) {
            if(arg instanceof Message) {
                listModelActivities.addElement((Message) arg);
            }
        }
    }
}
