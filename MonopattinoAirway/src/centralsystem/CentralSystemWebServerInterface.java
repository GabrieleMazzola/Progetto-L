package centralsystem;

import items.Fine;
import items.Sale;
import java.util.Set;

/**
 *
 * @author Zubeer
 */
interface CentralSystemWebServerInterface {

    Boolean existsTicket(long ticketID);

    boolean collectorLogin(String username, String password);

    Sale getSale(String serialCode);

    Boolean makeFine(Fine f);

    Set<Sale> getSalesByUsername(String username);

    public Set<Sale> getValidSalesByUsername(String username);

    
    Long countAllFinesMadeBy(String collectorUsername);

    boolean userLogin(String username, String password);
}
