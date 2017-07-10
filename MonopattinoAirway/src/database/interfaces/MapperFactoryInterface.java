package database.interfaces;

import database.interfaces.mapperinterfaces.CollectorMapper;
import database.interfaces.mapperinterfaces.FineMapper;
import database.interfaces.mapperinterfaces.InformationMapper;
import database.interfaces.mapperinterfaces.SaleMapper;
import database.interfaces.mapperinterfaces.UserMapper;

public interface MapperFactoryInterface {
    
    public UserMapper createUserMapper();
    public CollectorMapper createCollectorMapper();
    public SaleMapper createSaleMapper();
    public FineMapper createFineMapper();
    public InformationMapper createInformationMapper();
    
}
