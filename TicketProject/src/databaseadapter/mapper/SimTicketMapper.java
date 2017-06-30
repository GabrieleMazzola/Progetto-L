package databaseadapter.mapper;

import java.util.ArrayList;
import java.util.List;
import ticket.Sale;

/**
 *
 * @author Manuele
 */
public class SimTicketMapper implements MapperInterface{
    private List<Sale> sales;
    
    public SimTicketMapper() {
        sales = new ArrayList<>();
    }
    
    public List<Sale> getAllSalesOf(String username) {
        
        List<Sale> salesOf = new ArrayList<>();
        for(Sale s : sales) {
            if(s.getUsername().equals(username))
                salesOf.add(s);  
        }
        return salesOf;
    }
    
    @Override
    public Object get(String code) {
        long serialCode = Long.valueOf(code);
        
        for(Sale s : sales) {
            if(s.getSerialCode() == serialCode) return s;
        }
        return null;
    }

    @Override
    public boolean save(Object arg) {
        if(arg instanceof Sale)
            return sales.add((Sale)arg);
        else return false;
    }

    @Override
    public int getCacheSize() {
        return sales.size();
    }
    
}
