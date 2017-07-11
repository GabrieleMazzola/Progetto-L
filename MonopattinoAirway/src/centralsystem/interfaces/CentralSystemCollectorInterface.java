package centralsystem.interfaces;
import items.Fine;

public interface CentralSystemCollectorInterface {
    
    public Boolean existsTicket(long serialCode);
    
    public Boolean addFine(Fine f);
    
    public boolean collectorLogin(String username,String psw);
    
}
