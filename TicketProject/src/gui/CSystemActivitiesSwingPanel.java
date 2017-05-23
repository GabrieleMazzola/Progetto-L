package gui;

import centralsystem.CSystem;
import java.awt.BorderLayout;
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
public class CSystemActivitiesSwingPanel extends JPanel implements Observer{
    private DefaultListModel listModelActivities;
    private JList listActivities;
    private JScrollPane activitiesPane;

    public CSystemActivitiesSwingPanel(CSystem cSystem) {
        super(new BorderLayout());
        
        cSystem.addObserver(this);
        
        listModelActivities = new DefaultListModel();
        listActivities = new JList(listModelActivities);
        activitiesPane = new JScrollPane(listActivities);
        
        this.add(activitiesPane);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            listModelActivities.addElement((String) arg);
        }
    }
}
