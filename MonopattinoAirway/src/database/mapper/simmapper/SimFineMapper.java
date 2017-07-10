package database.mapper.simmapper;

import database.interfaces.MapperInterface;
import database.interfaces.mapperinterfaces.FineMapper;
import items.Fine;
import java.util.HashSet;
import java.util.Set;

public class SimFineMapper implements MapperInterface,FineMapper{
    private Set<Fine> fines;
    
    public SimFineMapper() {
        fines = new HashSet<>();
    }
    
    public Set<Fine> getAllFinesMadeTo(String personCF) {
        Set<Fine> finesOf = new HashSet();
        for(Fine f : fines) {
            if(f.getCfCode().equals(personCF)) finesOf.add(f);
        }
        return finesOf;
    }
    
    @Override
    public Long countAllFinesMadeBy(String collectorUsername) {

        Long count = new Long(0);
        for(Fine f : fines) {
            if(f.getCollectorUsername().equals(collectorUsername)) 
                count++;
        }
        return count;
    }
    
    @Override
    public Object get(String id) {
        for(Fine f : fines) {
            if(f.getId().equals(id)) 
                return f;
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
