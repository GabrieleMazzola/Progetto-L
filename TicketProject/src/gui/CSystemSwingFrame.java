package gui;

import centralsystem.CSystem;
import javax.swing.JFrame;

/**
 *
 * @author Manuele
 */
public class CSystemSwingFrame extends JFrame{
    public CSystemSwingFrame(CSystem cSystem) {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,200);
        add(new CSystemSwingPanel(cSystem));
    }
}
