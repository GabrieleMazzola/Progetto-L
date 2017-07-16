package testing;

import centralsystem.CSystem;
import centralsystem.factory.CSystemFactory;
import database.factories.DBMapperFactory;
import database.factories.SimMapperFactory;
import main.GUITicketMachine;

public class TestSystem {
    
    public static void main(String[] args) {
        CSystemFactory.getInstance().buildCSystem(SimMapperFactory.class.getCanonicalName());
       
    }
    
}
