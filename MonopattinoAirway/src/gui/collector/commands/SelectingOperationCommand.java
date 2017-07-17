package gui.collector.commands;

import controller.TicketCollectorSession;
import gui.collector.ChoosingOperationGrid;
import javafx.scene.Scene;

/**
 *
 * @author Manuele
 */
public class SelectingOperationCommand implements CollectorSceneDispatcher{

    @Override
    public Scene buildScene(TicketCollectorSession controller) {
        ChoosingOperationGrid choosingGrid = new ChoosingOperationGrid(controller);
        return new Scene(choosingGrid.asParent());
    }
    
}
