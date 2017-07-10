package database.mapper.simmapper;

import database.interfaces.MapperInterface;
import database.interfaces.mapperinterfaces.SaleMapper;
import items.Sale;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class SimSaleMapper implements MapperInterface,SaleMapper{
    private Set<Sale> sales;
    
    public SimSaleMapper() {
        sales = new HashSet<>();
    }
    
    public Set<Sale> getSalesByType(String type) {
        Set<Sale> salesType = new HashSet();
        for(Sale s : sales) {
            if (s.getType().equals(type))
                salesType.add(s);
        }
        return salesType;
    } 
    
    public Set<Sale> getAllSalesOf(String username) {
        Set<Sale> salesOf = new HashSet();
        for(Sale s : sales) {
            if(s.getUsername().equals(username))
                salesOf.add(s);
        }
        return salesOf;
    }
    
    @Override
    public Set<Sale> getAllValidSalesOf(String username) {
        Set<Sale> validSalesOf = new HashSet<>();
        for(Sale sale : getAllSalesOf(username)){
            if(checkSaleValidity(sale)){
                validSalesOf.add(sale);
            }
        }
        
        return validSalesOf;
    }
    
    @Override
    public Object get(String code) {
        long serialCode = Long.valueOf(code);
        for(Sale s : sales) {
            if(s.getSerialCode() == serialCode)
                return s;
        }
        return null;
    }

    @Override
    public boolean save(Object arg) {
        if(arg instanceof Sale){
            return sales.add((Sale)arg);
            
        }    
        else return false;
    }

    @Override
    public int getCacheSize() {
        return sales.size();
    }

    @Override
    public Set<Sale> getAllSales() {
        return sales;
    }

    private boolean checkSaleValidity(Sale sale) {
        Date expiryDate = sale.getExpiryDate();
        return ((new Date()).before(expiryDate));
    }
}
