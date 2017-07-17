package centralsystem.interfaces;
import items.Fine;

public interface CentralSystemCollectorInterface {
    
    public Boolean existsTicket(long serialCode);
    
    public Boolean makeFine(Fine f);
    
    public boolean collectorLogin(String username,String psw);
    
    public Long requestFinesStartNumber(String collectorUsername);
    
}
