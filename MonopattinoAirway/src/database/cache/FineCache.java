package database.cache;

import database.interfaces.CacheInterface;
import items.Fine;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Zubeer
 */
public class FineCache implements CacheInterface{
    private Set<Fine> cache;
    
    /**
     *
     */
    public FineCache() {
        cache = new HashSet<>();
    }
    
    /**
     *
     * @param arg
     */
    @Override
    public void add(Object arg) {
        if(arg instanceof Fine)
            cache.add((Fine)arg);
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Fine get(String id) {
        for(Fine f : cache) {
            String idString = f.getId() + "";
            if(idString.equals(id)) return f;
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return cache.size();
    }
}
