/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

/**
 *
 * @author Gabriele
 */
public interface CentralSystemTicketInterface {
    
    public boolean userLogin(String username,String psw);
    
    public long requestCodes(long numberOfCodes);
    
    public boolean cardPayment(String cardNumber, double amount);
    
    public boolean createUser(String name,String surname,String cf,String username,String psw);
    
    public boolean updateMachineStatus(int machineCode, double inkLevel, double paperLevel, boolean active, String ipAddress);
}
