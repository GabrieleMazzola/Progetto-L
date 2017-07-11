package gui;

import centralsystem.factory.CSystemFactory;
import database.factories.SimMapperFactory;

/**
 *
 * @author Manuele
 */
public class LaunchCSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSystemFactory.getInstance().buildCSystem(SimMapperFactory.class.getName());
    }
}
