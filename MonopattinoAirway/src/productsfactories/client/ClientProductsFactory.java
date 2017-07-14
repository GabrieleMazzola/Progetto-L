package productsfactories.client;

import items.PhisicalSimpleSeason;
import items.PhisicalSimpleTicket;
import items.Product;
import items.SimpleSeason;
import items.SimpleTicket;


public class ClientProductsFactory {
    private static ClientProductsFactory instance;
    
    private ClientProductsFactory(){
    }
    
    public synchronized static ClientProductsFactory getInstance(){
        if(instance == null)
            instance = new ClientProductsFactory();
        return instance;
    }
    
    public Product buildTicket(String description, String type, double cost, int duration) {
        
        switch(type.charAt(0)){
            case 'T':
                return new SimpleTicket(description, type, cost, duration);
            case 'S':
                return new SimpleSeason(description, type, cost/duration, duration);
            case 'P':
                return new PhisicalSimpleTicket(description, type, cost, duration);
            case 'Q':
                return new PhisicalSimpleSeason(description, type, cost/duration, duration);
            default:
                System.err.println("Received unknown type");
        }
        return null;
    }

}