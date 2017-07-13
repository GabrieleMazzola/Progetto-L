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

/**
 *
 * @author Zubeer
 */
public class SimMapperFactory extends MapperFactory{

    /**
     *
     * @return
     */
    @Override
    public UserMapper createUserMapper() {
        return new SimUserMapper();
    }

    /**
     *
     * @return
     */
    @Override
    public CollectorMapper createCollectorMapper() {
        return new SimCollectorMapper();
    }

    /**
     *
     * @return
     */
    @Override
    public SaleMapper createSaleMapper() {
        return new SimSaleMapper();
    }

    /**
     *
     * @return
     */
    @Override
    public FineMapper createFineMapper() {
        return new SimFineMapper();
    }

    /**
     *
     * @return
     */
    @Override
    public InformationMapper createInformationMapper() {
        return new SimInformationMapper();
    }
    
}
