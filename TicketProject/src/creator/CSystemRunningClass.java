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
        CSystemFactory csFactory = CSystemFactory.getInstance();
        CSystem cSystem = csFactory.getCentralSystemInstance();
        
        CSystemSwingFrame view = new CSystemSwingFrame(cSystem);
        view.setVisible(true);
        //TODO: aggiungere una gui al sistema centrale
    }
}
