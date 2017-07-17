package gui.collector.commands;

import controller.TicketCollectorSession;
import gui.collector.LoginCollectorGrid;
import javafx.scene.Scene;

/**
 *
 * @author Manuele
 */
public class LogoutCommand implements CollectorSceneDispatcher{

    @Override
    public Scene buildScene(TicketCollectorSession controller) {
        LoginCollectorGrid operationGrid = new LoginCollectorGrid(controller);
        return new Scene(operationGrid.asParent());
    }
}
