package centralsystem;

import TicketCollector.Fine;


public interface CentralSystemCollectorInterface {
    
    public boolean existsTicket(String ticketCode);
    
    public void makeFine(Fine f);
    
    public boolean login(String username,String psw);

}
