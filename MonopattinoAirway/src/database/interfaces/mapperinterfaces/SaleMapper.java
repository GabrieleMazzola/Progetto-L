package database.interfaces.mapperinterfaces;

import database.interfaces.MapperInterface;
import items.Sale;
import java.util.Set;


public interface SaleMapper extends MapperInterface{
    
    public Set<Sale> getAllSalesOf(String username);
        
    public Set<Sale> getAllSales();
    
    public Set<Sale> getAllValidSalesOf(String username);
}
