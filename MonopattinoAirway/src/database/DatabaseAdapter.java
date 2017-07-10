package database;

import database.factories.MapperFactory;
import database.information.InformationUnit;
import database.interfaces.mapperinterfaces.*;
import database.people.*;
import items.*;
import java.util.Set;

//TODO aggiungere gli enum al database
public class DatabaseAdapter{
    
    private UserMapper userMapper;
    private CollectorMapper collectorMapper;
    private SaleMapper saleMapper;
    private FineMapper fineMapper;
    private InformationMapper infoMapper;
        
    public DatabaseAdapter(String className){
        try{
            userMapper = MapperFactory.getInstance(className).createUserMapper();
            collectorMapper = MapperFactory.getInstance(className).createCollectorMapper();
            saleMapper = MapperFactory.getInstance(className).createSaleMapper();
            fineMapper = MapperFactory.getInstance(className).createFineMapper();
            infoMapper = MapperFactory.getInstance(className).createInformationMapper();
        } catch (ClassNotFoundException|IllegalAccessException|InstantiationException ex) {
            ex.printStackTrace();
        }

    }
    
    
    public boolean checkUser(String username) {
        return userMapper.get(username) != null;
    }

    
    public boolean createUser(String name, String surname, String cf, String username, String psw, String email) {
        return userMapper.save(new User(name, surname, cf, username, psw, email));
    }

    public boolean userLogin(String username, String psw) {
        User user = (User)userMapper.get(username);
        if(user != null){
            if(user.getPsw().equals(psw)){
                return true;
            }
        }
        return false;
    }

    public boolean createCollector(String name, String surname, String cf, String username, String psw) {
        return collectorMapper.save(new Collector(name, surname, cf, username, psw));
    }

    public boolean collectorLogin(String username, String psw) {
        Collector collector = (Collector)collectorMapper.get(username);
        if(collector != null && collector.getPsw().equals(psw))
            return true;
        return false;    
    } 

    public boolean addFine(Fine f) {
        return fineMapper.save(f);
    }

    public boolean serialCodeCheck(Long serialCode) {
        return serialCode <= Long.valueOf(((InformationUnit)infoMapper.get("ProductCounter")).getValue());
    }

    public Fine getFineById(long id) {
        return (Fine)fineMapper.get(id + "");
    }

    public Set<Fine> getFinesByCF(String cf) {
        return (Set<Fine>)fineMapper.getAllFinesMadeTo(cf);
    }

    public boolean addSale(Sale sale) {
        return saleMapper.save(sale);
    }

    public Set<Sale> getSalesByType(String type) {
        return (Set<Sale>)saleMapper.get(type);
    }

    public Set<Sale> getSalesByUsername(String username) {
        return (Set<Sale>)saleMapper.getAllSalesOf(username);
    }
    
    public Set<Sale> getValidSalesByUsername(String username) {
        return (Set<Sale>)saleMapper.getAllValidSalesOf(username);
    }

    public void setProductCounter(Long productCounter) {
        infoMapper.save(new InformationUnit("ProductCounter", productCounter.toString()));
    }

    public long getProductCounter() {
        InformationUnit info = (InformationUnit)infoMapper.get("ProductCounter");
        return Long.valueOf(info.getValue());
    }

    public void setProductVersionCounter(Long productVersionCounter) {
        infoMapper.save(new InformationUnit("ProductVersionCounter", productVersionCounter.toString()));
    }

    public long getProductVersionCounter() {
        return (long)infoMapper.get("ProductVersionCounter");
    }

    public Set<Sale> getAllSales() {
        return (Set<Sale>)saleMapper.getAllSales();
    }    

    public Long countAllFinesMadeBy(String collectorUsername) {
        return (Long)fineMapper.countAllFinesMadeBy(collectorUsername);
    }

    public User getUser(String username) {
        return (User)userMapper.get(username);
    }
}
