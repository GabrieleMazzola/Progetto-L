package databaseadapter.mapper;

import databaseadapter.people.Collector;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Manuele
 */
public class SimCollectorMapper implements MapperInterface{
    private Set<Collector> collectors;
    
    public SimCollectorMapper() {
        collectors = new HashSet<>();
    }
    
    @Override
    public Object get(String username) {
        for(Collector c : collectors) {
            if(c.getUsername().equals(username)) return c;
        }
        return null;
    }

    @Override
    public boolean save(Object arg) {
        if(arg instanceof Collector)
            return collectors.add((Collector)arg);
        else return false;
    }

    @Override
    public int getCacheSize() {
        return collectors.size();
    }
}
