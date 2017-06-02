package gui;

import centralsystem.CSystem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Manuele
 */
public class CSystemButtonSwingPanel extends JPanel{
    private JButton close, restart;
    
    public CSystemButtonSwingPanel(CSystem cSystem) {
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
        //this.add(restart);
    }
}
