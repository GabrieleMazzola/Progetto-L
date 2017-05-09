package centralsystem;

import ticketCollector.Fine;



public interface CentralSystemCollectorInterface {
    
    public boolean existsTicket(String ticketCode);
    
    public void makeFine(Fine f);
    
    public boolean login(String username,String psw);

}
