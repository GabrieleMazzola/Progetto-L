package gui.csystem;

import centralsystem.factory.CSystemFactory;
import database.factories.DBMapperFactory;
import database.factories.SimMapperFactory;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Zubeer
 */
public class CSystemLoaderFrame extends JFrame{
    private JPanel mainPanel;
    private JButton simButton, dbButton;
    
    /**
     *
     */
    public CSystemLoaderFrame() {
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        
        mainPanel = new JPanel(new FlowLayout());
        
        simButton = new JButton("Costruisci CSystem con db simulato");
        simButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                CSystemFactory.getInstance().buildCSystem(SimMapperFactory.class.getName());
                
            }
        });
        dbButton = new JButton("Costruisci CSystem con db reale");
        dbButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                CSystemFactory.getInstance().buildCSystem(DBMapperFactory.class.getName());
                
            }
        });
        
        mainPanel.add(simButton);
        mainPanel.add(dbButton);
        add(mainPanel);
    }
}
