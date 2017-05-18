package gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Manuele
 */
public class MachineLeafPanel extends Container{
    private JLabel machineId;
    private JProgressBar inkBar, paperBar;
    
    public MachineLeafPanel(String id, double inkLvl, double paperLvl) {
        this.setLayout(new GridLayout(1,3));
        
        //Istanzio gli oggetti da mostrare
        machineId = new JLabel(id);
        inkBar = new JProgressBar(0, 100);
        inkBar.setValue((int)Math.round(inkLvl));
        inkBar.setStringPainted(true);
        paperBar = new JProgressBar(0, 100);
        paperBar.setValue((int)Math.round(inkLvl));
        paperBar.setStringPainted(true);
        
        this.add(machineId);
        this.add(inkBar);
        this.add(paperBar);
    }
    
    public void updateInkLevel(int newInkLevel) {
        inkBar.setValue(newInkLevel);
    }
    
    public void updatePaperLevel(int newPaperLevel) {
        paperBar.setValue(newPaperLevel);
    }
}
