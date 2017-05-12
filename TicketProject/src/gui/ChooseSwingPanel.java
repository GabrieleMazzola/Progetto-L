package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class ChooseSwingPanel extends JPanel{
    private JButton buyButton;
    
    public ChooseSwingPanel(TicketMachine tm) {
        buyButton = new JButton("Buy single ticket");
        buyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });
        this.add(buyButton);
    }
}
