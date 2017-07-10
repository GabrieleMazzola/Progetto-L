package database.mapper.simmapper;

import database.interfaces.MapperInterface;
import database.interfaces.mapperinterfaces.CollectorMapper;
import database.people.Collector;
import java.util.HashSet;
import java.util.Set;

public class SimCollectorMapper implements MapperInterface,CollectorMapper{
    private Set<Collector> collectors;
    
    public SimCollectorMapper() {
        collectors = new HashSet<>();
        initCollector();
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
    
    public void initCollector(){
        collectors.add(new Collector("COLLECTOR", "COLLECTOR", "COLLECTOR", "COLLECTOR", "COLLECTOR"));
    }
}
