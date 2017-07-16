package gui.collector.commands;

import gui.collector.LoginCollectorGrid;
import javafx.scene.Scene;
import ticketcollector.TicketCollector;

/**
 *
 * @author Manuele
 */
public class LoginCommand implements CollectorSceneDispatcher{
    
    @Override
    public Scene buildScene(TicketCollector collector) {
        LoginCollectorGrid loginGrid = new LoginCollectorGrid(collector);
        return new Scene(loginGrid.asParent());
    }
}
