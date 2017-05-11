package centralsystem;

import ticketCollector.Fine;



/**
 * 	@author Gabriele
 *
 *	
 *	Interfaccia usata per chiamare metodi da parte del TicketCollector(
 */
public interface CentralSystemCollectorInterface {
    
    public boolean existsTicket(String ticketCode);
    
    public boolean makeFine(Fine f);
    
    public boolean collectorLogin(String username,String psw);
    
    public String centralSystemTEST(String sentTest);

}
