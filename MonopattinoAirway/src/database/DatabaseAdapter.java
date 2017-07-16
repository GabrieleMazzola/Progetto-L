package database;

import database.factories.MapperFactory;
import database.information.InformationUnit;
import database.interfaces.mapperinterfaces.*;
import database.people.*;
import enums.databaseenumretions.InformationUnitEnum;
import items.*;
import java.util.Set;

/**
 *
 * @author Zubeer
 */
public class DatabaseAdapter{
    
    private UserMapper userMapper;
    private CollectorMapper collectorMapper;
    private SaleMapper saleMapper;
    private FineMapper fineMapper;
    private InformationMapper infoMapper;
        
    /**
     *
     * @param className
     */
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
    
    /**
     * Controlla l'esistenza dell'user con username specificato
     * @param username
     * @return Vero se esiste l'utente. Falso altrimenti
     */
    public boolean checkUser(String username) {
        return userMapper.get(username) != null;
    }

    /**
     * Creazione di un nuovo utente
     * @param name
     * @param surname
     * @param cf
     * @param username
     * @param psw
     * @param email
     * @return Vero se viene creato l'utente. Falso altrimenti 
     */
    public boolean createUser(String name, String surname, String cf, String username, String psw, String email) {
        return userMapper.save(new User(name, surname, cf, username, psw, email));
    }
/** 
 * Login utente
 * @param username
 * @param psw
 * @return Vero se viene effettuato il login. Falso altrimenti
 */
    public boolean userLogin(String username, String psw) {
        User user = (User)userMapper.get(username);
        if(user != null){
            if(user.getPsw().equals(psw)){
                return true;
            }
        }
        return false;
    }
/**
 * Crea un nuovo collector
 * @param name
 * @param surname
 * @param cf
 * @param username
 * @param psw
 * @return Vero se viene creato il collector. Falso altrimenti
 */
    public boolean createCollector(String name, String surname, String cf, String username, String psw) {
        return collectorMapper.save(new Collector(name, surname, cf, username, psw));
    }
/**
 * Login del collector
 * @param username
 * @param psw
 * @return Vero se viene effettuato il collector. Falso altrimenti
 */
    public boolean collectorLogin(String username, String psw) {
        Collector collector = (Collector)collectorMapper.get(username);
        if(collector != null && collector.getPsw().equals(psw))
            return true;
        return false;    
    } 
/**
 * Aggiunta nuova multa
 * @param f
 * @return Vero se viene creata la multa. Falso altrimenti
 */
    public boolean addFine(Fine f) {
        return fineMapper.save(f);
    }
/**
 * Controllo esistenza del serialcode
 * @param serialCode
 * @return Vero se il check è andato a buon fine. Falso in caso contrario
 */
    public boolean serialCodeCheck(Long serialCode) {
        return serialCode <= Long.valueOf(((InformationUnit)infoMapper.get(InformationUnitEnum.PRODUCTCOUNTER.toString())).getValue());
    }
/**
 * Acquisizione delle multe organizzate per Id
 * @param id
 * @return L'unica multa con l'id ricercato
 */
    public Fine getFineById(long id) {
        return (Fine)fineMapper.get(id + "");
    }
/**
 * Acquisizione delle multe organizzate per CF
 * @param cf
 * @return Restituisce la lista delle multe di un determinato CF
 */
    public Set<Fine> getFinesByCF(String cf) {
        return (Set<Fine>)fineMapper.getAllFinesMadeTo(cf);
    }
/**
 * Aggiunta di una nuova vendita
 * @param sale
 * @return Vero se la vendita viene aggiunta. Falso in caso contrario
 */
    public boolean addSale(Sale sale) {
        return saleMapper.save(sale);
    }
/**
 * Acquisizione delle vendite organizzate per tipo
 * @param type
 * @return Restituisce le vendite di quel determinato tipo di biglietto
 */
    public Set<Sale> getSalesByType(String type) {
        return (Set<Sale>)saleMapper.get(type);
    }
/**
 * Acquisizione delle vendite organizzata per username
 * @param username
 * @return Restituisce le vendite di un determinato username
 */
    public Set<Sale> getSalesByUsername(String username) {
        return (Set<Sale>)saleMapper.getAllSalesOf(username);
    }
    /**
     * Acquisizione delle vendite valide organizzate per username
     * @param username
     * @return Restituisce le vendite valide di un determinato username
     */
    public Set<Sale> getValidSalesByUsername(String username) {
        return (Set<Sale>)saleMapper.getAllValidSalesOf(username);
    }
/**
 * Set del contatore dei prodotti
 * @param productCounter 
 */
    public void setProductCounter(Long productCounter) {
        infoMapper.save(new InformationUnit(InformationUnitEnum.PRODUCTCOUNTER.toString(), productCounter.toString()));
    }
/**
 * Acquisizione del contatore dei prodotti
 * @return Ritorna il valore del codice seriale del prodotto
 */
    public long getProductCounter() {
        InformationUnit info = (InformationUnit)infoMapper.get(InformationUnitEnum.PRODUCTCOUNTER.toString());
        return Long.valueOf(info.getValue());
    }

/**
 * Acquisizione di tutte le vendite  
 * @return Ritorna le vendite 
 */
    public Set<Sale> getAllSales() {
        return (Set<Sale>)saleMapper.getAllSales();
    }    
/**
 * Conta tutte le multe fatte da un determinato collectorUsername
 * @param collectorUsername
 * @return Ritorna le multe fatte dal collectorUsername
 */
    public Long countAllFinesMadeBy(String collectorUsername) {
        return (Long)fineMapper.countAllFinesMadeBy(collectorUsername);
    }
/**
 * Acquisizione dell'utente 
 * @param username
 * @return Ritorna l'username dell'utente
 */
    public User getUser(String username) {
        return (User)userMapper.get(username);
    }

    public Sale getSale(String serialCode) {
        return (Sale)saleMapper.get(serialCode);
    }
}
