package gui.csystem;

import centralsystem.CSystem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Pannello che contiene i bottoni di chiusura e restar del server.
 */
public class CSystemButtonPanel extends JPanel{
    private JButton close, restart;
    
    public CSystemButtonPanel(CSystem cSystem) {
        super();
        
        close = new JButton("Close server");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                cSystem.close(false);
            }
        });
        restart = new JButton("Restart server");
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                cSystem.close(true);
            }
        });
        
        this.add(close);
        this.add(restart);
        
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
