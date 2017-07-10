package centralsystem.factory;

import centralsystem.CSystem;
import communication.SocketHandler;
import gui.CSystemFrame;
import java.io.IOException;
import javax.swing.JFrame;


public class CSystemFactory {
    private static CSystemFactory instance;
    private CSystem currentSystem;
    private SocketHandler socketHandler;
    
    private CSystemFactory() {
    }
    
    public static synchronized CSystemFactory getInstance() {
        if(instance == null) 
            instance = new CSystemFactory();
        return instance;
    }
    
    /**
     * Costruisce un sistema centrale e la sua GUI
     * @param databaseFactoryName indica se il database deve essere reale o simulato
     * @return Il database costruito
     */
    public CSystem buildCSystem(String databaseFactoryName) {
        try {
            currentSystem = new CSystem(databaseFactoryName);
            socketHandler = new SocketHandler(5000, currentSystem);
            socketHandler.start();
            JFrame frameGui = new CSystemFrame(currentSystem);
            frameGui.setVisible(true);
            return currentSystem;
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Costruisce un sistema centrale senza GUI
     * @param databaseFactoryName indica se il database deve essere reale o simulato
     * @return Il database costruito
     */
    public CSystem buildLightCSystem(String databaseFactoryName) {
        try {
            currentSystem = new CSystem(databaseFactoryName);
            socketHandler = new SocketHandler(5000, currentSystem);
            socketHandler.start();
            return currentSystem;
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
