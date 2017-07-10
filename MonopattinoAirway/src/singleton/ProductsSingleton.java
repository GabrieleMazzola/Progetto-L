package singleton;

import items.MultiTicket;
import items.PhisicalSimpleSeason;
import items.PhisicalSimpleTicket;
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
        products.put("T1",new SimpleTicket("Short Ticket","T1",1.30,90));
        products.put("T2",new SimpleTicket("Medium Ticket","T2",1.60,120));
        products.put("T3",new SimpleTicket("Long Ticket","T3",1.90,150));
        products.put("T4",new SimpleTicket("Very Short Ticket","T4",1, 1));
        products.put("S1",new SimpleSeason("Monthly Season","S1",5,1));
        products.put("S2",new SimpleSeason("Semestral Season","S2",3,6));
        products.put("S3",new SimpleSeason("Annual Season","S3",2, 12));
        products.put("S4", new SimpleSeason("Trimestral Season", "S4", 3.5, 3));
        products.put("P1",new PhisicalSimpleTicket("Physical Short Ticket","P1",1.30,90));
        products.put("P2",new PhisicalSimpleTicket("Physical Medium Ticket","P2",1.60,120));
        products.put("P3",new PhisicalSimpleTicket("Physical Long Ticket","P3",1.90,150));
        products.put("P4",new PhisicalSimpleTicket("Very Short Physical Ticket","P4",1, 1));
        products.put("Q1",new PhisicalSimpleSeason("Physical Monthly Season","Q1",5,1));
        products.put("Q2",new PhisicalSimpleSeason("Physical Semestral Season","Q2",3,6));
        products.put("Q3",new PhisicalSimpleSeason("Physical Annual Season","Q3",2, 12));
        products.put("M1", new MultiTicket("Multiple Short Season (5 rides)","M1",1.30,90,5));
        
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}
