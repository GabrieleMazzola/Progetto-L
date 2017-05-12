package creator;

import centralsystem.CSystem;

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
        
        //TODO: aggiungere una gui al sistema centrale
    }
}
