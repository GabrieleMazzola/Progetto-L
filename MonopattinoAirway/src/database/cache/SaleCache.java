package database.cache;

import database.interfaces.CacheInterface;
import items.Sale;
import java.util.HashSet;
import java.util.Set;


public class SaleCache implements CacheInterface{
    private Set<Sale> cache;

    public SaleCache() {
        cache = new HashSet<>();
    }

    @Override
    public void add(Object arg) {
        if(arg instanceof Sale)
            cache.add((Sale)arg);
    }

    @Override
    public Sale get(String code) {
        
        long serialCode = Long.valueOf(code);
        
        for(Sale s : cache) {
            if(s.getSerialCode() == serialCode) 
                return s;
        }
        return null;
    }
    
    @Override
    public int getSize() {
        return cache.size();
    }
}
