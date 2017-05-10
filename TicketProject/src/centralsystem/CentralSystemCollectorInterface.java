package centralsystem;


import ticketCollector.Fine;



/**
 * 	@author Gabriele
 *
 *	
 *	Interfaccia usata per creare i metodi chiamati dal TicketCollector(
 */
public interface CentralSystemCollectorInterface {
    
    public boolean existsTicket(String ticketCode);
    
    public boolean makeFine(Fine f);
    
    public boolean collectorLogin(String username,String psw);

    public String centralSystemTEST(String sentTest);
}
