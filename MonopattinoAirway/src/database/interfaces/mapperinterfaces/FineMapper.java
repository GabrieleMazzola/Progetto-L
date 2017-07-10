package database.interfaces.mapperinterfaces;

import database.interfaces.MapperInterface;
import items.Fine;
import java.util.Set;

public interface FineMapper extends MapperInterface{
    
    public Set<Fine> getAllFinesMadeTo(String CF);
    
    public Long countAllFinesMadeBy(String collectorUsername);
    
}
