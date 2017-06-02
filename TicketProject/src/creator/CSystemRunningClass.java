package creator;

import centralsystem.CSystem;
import gui.CSystemSwingFrame;

/**
 *
 * @author Manuele
 */
public class CSystemRunningClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSystemFactory csFactory = new CSystemFactory();
        CSystem cSystem = csFactory.buildNewCentralSystem();
        
        CSystemSwingFrame view = new CSystemSwingFrame(cSystem);
        view.setVisible(true);
    }
}
