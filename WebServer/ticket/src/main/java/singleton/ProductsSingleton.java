package singleton;

import items.Product;
import items.SimpleSeason;
import items.SimpleTicket;
import java.util.HashMap;
import java.util.Map;

public class ProductsSingleton {
    
    private Map<String,Product> products;
    private static ProductsSingleton instance;
    
    private ProductsSingleton(){
        products = new HashMap<>();
        initMap();
    }
    
    public synchronized static ProductsSingleton getInstance(){
        if(instance == null)
            instance = new ProductsSingleton();
        return instance;
    }

    private void initMap() {
    	products.put("T1",new SimpleTicket("Biglietto breve","T1",1.30,90));
        products.put("T2",new SimpleTicket("Biglietto medio","T2",1.60,120));
        products.put("T3",new SimpleTicket("Biglietto lungo","T3",1.90,150));
        products.put("S1",new SimpleSeason("Season mensile","S1",5,1));
        products.put("S2",new SimpleSeason("Season semestrale","S2",3,6));
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}
