package databaseadapter.mapper;

import java.util.HashSet;
import java.util.Set;
import ticketCollector.Fine;

/**
 *
 * @author Manuele
 */
public class SimFineMapper implements MapperInterface{
    private Set<Fine> fines;
    
    public SimFineMapper() {
        fines = new HashSet<>();
    }
    
    @Override
    public Object get(String id) {
        for(Fine f : fines) {
            if(f.getId() == (Long.parseLong(id))) return f;
        }
        return null;
    }

    @Override
    public boolean save(Object arg) {
        if(arg instanceof Fine)
            return fines.add((Fine)arg);
        else return false;
    }

    @Override
    public int getCacheSize() {
        return fines.size();
    }
}
