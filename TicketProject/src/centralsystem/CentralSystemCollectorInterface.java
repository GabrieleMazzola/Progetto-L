/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import TicketCollector.Fine;

/**
 *
 * @author Gabriele
 */
public interface CentralSystemCollectorInterface {
    
      public boolean existsTicket(String ticketCode);
    
    public void makeFine(Fine f);
    
    public boolean login(String username,String psw);

}
