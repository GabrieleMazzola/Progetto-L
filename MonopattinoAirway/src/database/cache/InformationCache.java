package database.cache;

import database.interfaces.CacheInterface;

/**
 *
 * @author Zubeer
 */
public class InformationCache implements CacheInterface{
    
    /**
     *
     * @param arg
     */
    @Override
    public void add(Object arg) {
        
    }

    /**
     *
     * @param code
     * @return
     */
    @Override
    public Object get(String code) {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return 0;
    }
    
}
