package gui.csystem;

import centralsystem.CSystem;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ticketmachine.MachineStatus;

/**
 * Pannello che contiene le informazioni di tutte le macchinette connesse.
 * Le informazioni sono mostrate graficamente per ogni macchinetta
 */
public class MachineStatusPanel extends JPanel implements Observer{
    private Map<String, MachineLeafPanel> contents;
    private Box box;
    private JLabel activeLabel, idLabel, ipLabel, inkLvlLabel, paperLvlLabel;
    private JPanel labelPanel;
    
    /**
     *
     * @param cSystem
     */
    public MachineStatusPanel(CSystem cSystem) {
        contents = new HashMap();
        box = Box.createVerticalBox();
        
        setupBox();
        
        this.add(box);
        cSystem.addObserver(this);
    }
    
    private void setupBox() {
        activeLabel = new JLabel("Active");
        idLabel = new JLabel("Id");
        ipLabel = new JLabel("Ip");
        inkLvlLabel = new JLabel("Ink Level");
        paperLvlLabel = new JLabel("Paper Level");
        
        labelPanel = new JPanel(new GridLayout(1,4));
        labelPanel.add(activeLabel);
        labelPanel.add(idLabel);
        labelPanel.add(ipLabel);
        labelPanel.add(inkLvlLabel);
        labelPanel.add(paperLvlLabel);
        
        box.add(labelPanel);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof MachineStatus) {
            repaintContent((MachineStatus)arg);
        }
    }
    
    private boolean alredyHas(String ip) {
        return contents.containsKey(ip);
    }
    
    private void repaintContent(MachineStatus status) {
        int id = status.getMachineCode();
        double inkLvl = status.getInkLevel();
        double paperLvl = status.getPaperLevel();
        boolean active = status.isActive();
        String sellerIp = status.getSellerIp();
                
        if(alredyHas(sellerIp)) {
            contents.get(sellerIp).updateInkLevel((int)Math.round(inkLvl));
            contents.get(sellerIp).updatePaperLevel((int)Math.round(paperLvl));
        }
        else {
            MachineLeafPanel newPanel = new MachineLeafPanel(id, inkLvl, paperLvl, sellerIp);
            box.add(newPanel);
            contents.put(sellerIp, newPanel);
        }
        
        if(!active) contents.get(id).colorImage(Color.RED);
        this.revalidate();
        this.repaint();
    }
} 
        