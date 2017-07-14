package gui.csystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Pannello che contiene le informazioni di una macchinetta che si connette al sistema centrale.
 */
public class MachineLeafPanel extends JPanel{
    private JLabel machineId, labelImage, ipLabel;
    private JProgressBar inkBar, paperBar;
    private BufferedImage image;
    private Graphics2D graphics;
    private final int size = 15;
    
    /**
     *
     * @param id
     * @param inkLvl
     * @param paperLvl
     * @param ip
     */
    public MachineLeafPanel(int id, double inkLvl, double paperLvl, String ip) {
        this.setLayout(new GridLayout(1,4));
        
        machineId = new JLabel(id + "");
        ipLabel = new JLabel(ip);
        inkBar = new JProgressBar(0, 100);
        inkBar.setValue((int)Math.round(inkLvl));
        inkBar.setStringPainted(true);
        paperBar = new JProgressBar(0, 100);
        paperBar.setValue((int)Math.round(inkLvl));
        paperBar.setStringPainted(true);
        image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        graphics = (Graphics2D) image.getGraphics();
        setupImageGraphics();
        labelImage = new JLabel(new ImageIcon(image));
        this.add(labelImage);
        this.add(machineId);
        this.add(ipLabel);
        this.add(inkBar);
        this.add(paperBar);
    }
    
    /**
     *
     * @param newInkLevel
     */
    public void updateInkLevel(int newInkLevel) {
        inkBar.setValue(newInkLevel);
    }
    
    /**
     *
     * @param newPaperLevel
     */
    public void updatePaperLevel(int newPaperLevel) {
        paperBar.setValue(newPaperLevel);
    }
    
    /**
     *
     * @param color
     */
    public void colorImage(Color color) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, size, size);
        labelImage.setIcon(new ImageIcon(image));
        this.revalidate();
        this.repaint();
    }
    
    private void setupImageGraphics() {
        if(inkBar.getValue() > 0 && paperBar.getValue() > 0)
            graphics.setColor(Color.GREEN);
        else
            graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, size, size);
    }
}
