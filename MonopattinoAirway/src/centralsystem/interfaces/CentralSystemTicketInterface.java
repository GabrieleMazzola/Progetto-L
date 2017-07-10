 package centralsystem.interfaces;

import items.Sale;
import ticketmachine.MachineStatus;

public interface CentralSystemTicketInterface {
    
    public boolean userLogin(String username,String psw);
    
    public long requestCodes(long numberOfCodes);
    
    public boolean cardPayment(String cardNumber, double amount);
    
    public boolean createUser(String name,String surname,String cf,String username,String psw, String email);
    
    public boolean updateMachineStatus(MachineStatus status);
    
    public boolean addSale(Sale sale);
}
