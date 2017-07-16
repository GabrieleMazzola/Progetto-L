package database.factories;

import database.interfaces.mapperinterfaces.CollectorMapper;
import database.interfaces.mapperinterfaces.FineMapper;
import database.interfaces.mapperinterfaces.InformationMapper;
import database.interfaces.mapperinterfaces.SaleMapper;
import database.interfaces.mapperinterfaces.UserMapper;
import database.mapper.simmapper.SimCollectorMapper;
import database.mapper.simmapper.SimFineMapper;
import database.mapper.simmapper.SimInformationMapper;
import database.mapper.simmapper.SimSaleMapper;
import database.mapper.simmapper.SimUserMapper;


public class SimMapperFactory extends MapperFactory{

    @Override
    public UserMapper createUserMapper() {
        return new SimUserMapper();
    }

    @Override
    public CollectorMapper createCollectorMapper() {
        return new SimCollectorMapper();
    }
    
    @Override
    public SaleMapper createSaleMapper() {
        return new SimSaleMapper();
    }

    @Override
    public FineMapper createFineMapper() {
        return new SimFineMapper();
    }

    @Override
    public InformationMapper createInformationMapper() {
        return new SimInformationMapper();
    }
    
}
