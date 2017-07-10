package database.factories;

import database.information.InformationUnit;
import database.interfaces.mapperinterfaces.CollectorMapper;
import database.interfaces.mapperinterfaces.FineMapper;
import database.interfaces.mapperinterfaces.InformationMapper;
import database.interfaces.mapperinterfaces.SaleMapper;
import database.interfaces.mapperinterfaces.UserMapper;
import database.mapper.realmapper.DBCollectorMapper;
import database.mapper.realmapper.DBFineMapper;
import database.mapper.realmapper.DBInformationMapper;
import database.mapper.realmapper.DBSaleMapper;
import database.mapper.realmapper.DBUserMapper;

public class DBMapperFactory extends MapperFactory{

    @Override
    public UserMapper createUserMapper(){
        return new DBUserMapper("users");
    }

    @Override
    public CollectorMapper createCollectorMapper() {
        return new DBCollectorMapper("collectors");   
    }

    @Override
    public SaleMapper createSaleMapper() {
        return new DBSaleMapper("sales"); 
    }

    @Override
    public FineMapper createFineMapper() {
        return new DBFineMapper("fines");   
    }

    @Override
    public InformationMapper createInformationMapper() {
        InformationMapper infoMapper = new DBInformationMapper("informations");
        //infoMapper.save(new InformationUnit("ProductCounter", "0"));
        return infoMapper;
    }
    
}
