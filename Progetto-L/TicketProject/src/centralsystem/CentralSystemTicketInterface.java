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
    
    public boolean login(String username,String psw);
    
    public String requestCodes();
}
