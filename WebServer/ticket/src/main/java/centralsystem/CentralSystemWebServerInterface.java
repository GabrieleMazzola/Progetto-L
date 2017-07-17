/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import items.Fine;
import items.Sale;
import java.util.Set;

/**
 *
 * @author Zubeer
 */
public interface CentralSystemWebServerInterface {


    boolean existsTicket(Long ticketID);

    boolean collectorLogin(String username, String password);

    Sale getSale(String serialCode);

    Boolean makeFine(Fine f);

    Set<Sale> getSalesByUsername(String username);
    public Set<Sale> getValidSalesByUsername(String username);
    

    String createUser(String name, String surname, String cf, String username, String password, String email);

    Long requestFinesStartNumber(String collectorUsername);
    boolean userLogin(String username, String password);

    public boolean addSale(Sale sale);
    
}
