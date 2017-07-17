package gui.collector.commands;

import controller.TicketCollectorSession;
import gui.collector.LoginCollectorGrid;
import javafx.scene.Scene;

/**
 *
 * @author Manuele
 */
public class LoginCommand implements CollectorSceneDispatcher{
    
    @Override
    public Scene buildScene(TicketCollectorSession controller) {
        LoginCollectorGrid loginGrid = new LoginCollectorGrid(controller);
        return new Scene(loginGrid.asParent());
    }
}
