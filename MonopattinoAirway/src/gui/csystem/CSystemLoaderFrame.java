package gui.csystem;

import centralsystem.factory.CSystemFactory;
import database.factories.DBMapperFactory;
import database.factories.SimMapperFactory;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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
                closeFrame();
            }
        });
        dbButton = new JButton("Costruisci CSystem con db reale");
        dbButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                CSystemFactory.getInstance().buildCSystem(DBMapperFactory.class.getName());
                closeFrame();
            }
        });
        
        mainPanel.add(simButton);
        mainPanel.add(dbButton);
        add(mainPanel);
    }
    
    private void closeFrame() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
