package database.factories;

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
    public String username = "root", password = "gigidatome3";

    @Override
    public UserMapper createUserMapper(){
        return new DBUserMapper("users", username, password);
    }

    @Override
    public CollectorMapper createCollectorMapper() {
        return new DBCollectorMapper("collectors", username, password);
    }

    @Override
    public SaleMapper createSaleMapper() {
        return new DBSaleMapper("sales", username, password); 
    }

    @Override
    public FineMapper createFineMapper() {
        return new DBFineMapper("fines", username, password);   
    }

    @Override
    public InformationMapper createInformationMapper() {
        InformationMapper infoMapper = new DBInformationMapper("informations", username, password);
        //infoMapper.save(new InformationUnit("ProductCounter", "0"));
        return infoMapper;
    }
    
    public static synchronized MapperFactory getInstance(String className, String username, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(instance == null){
            instance = (MapperFactory)Class.forName(className).newInstance();
        }
        return instance;            
    } 
    
}
