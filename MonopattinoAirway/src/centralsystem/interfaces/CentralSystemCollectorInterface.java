package centralsystem.interfaces;
import items.Fine;

public interface CentralSystemCollectorInterface {
    
    public Boolean existsTicket(long serialCode);
    
    public boolean addFine(Fine f);
    
    public boolean collectorLogin(String username,String psw);
    
}
