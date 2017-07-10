
package database.mapper.simmapper;

import database.information.InformationUnit;
import database.interfaces.MapperInterface;
import database.interfaces.mapperinterfaces.InformationMapper;
import java.util.HashSet;
import java.util.Set;

public class SimInformationMapper implements MapperInterface,InformationMapper{
    private Set<InformationUnit> informations;
    
    public SimInformationMapper() {
        informations = new HashSet();
        initInformation();
    }
    
    @Override
    public Object get(String id) {
        for(InformationUnit unit : informations) {
            if(unit.getKey().equals(id))
                return unit;
        }
        return null;
    }

    @Override
    public boolean save(Object arg) {
        InformationUnit info = (InformationUnit) arg;
        
        for(InformationUnit unit : informations) {
            if(unit.getKey().equals(info.getKey())){
                unit.setValue(info.getValue());
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCacheSize() {
        return informations.size();
    }

    private void initInformation() {
        informations.add(new InformationUnit("ProductCounter", "0"));
    }
}
