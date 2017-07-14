package machineonline;

import items.Product;
import items.SimpleSeason;
import items.SimpleTicket;


public class ClientOnlineProductsFactory {
    private static ClientOnlineProductsFactory instance;
    
    private ClientOnlineProductsFactory(){
    }
    
    public synchronized static ClientOnlineProductsFactory getInstance(){
        if(instance == null)
            instance = new ClientOnlineProductsFactory();
        return instance;
    }
    
    public Product buildTicket(String description, String type, double cost, int duration) {
        
        switch(type.charAt(0)){
            case 'T':
                return new SimpleTicket(description, type, cost, duration);
            case 'S':
                return new SimpleSeason(description, type, cost/duration, duration);
            default:
                System.err.println("Received not valid type");
        }
        return null;
    }

}