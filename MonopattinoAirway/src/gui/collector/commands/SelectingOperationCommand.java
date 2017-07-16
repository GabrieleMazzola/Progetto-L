package gui.collector.commands;

import gui.collector.ChoosingOperationGrid;
import javafx.scene.Scene;
import ticketcollector.TicketCollector;

/**
 *
 * @author Manuele
 */
public class SelectingOperationCommand implements CollectorSceneDispatcher{

    @Override
    public Scene buildScene(TicketCollector collector) {
        ChoosingOperationGrid choosingGrid = new ChoosingOperationGrid(collector);
        return new Scene(choosingGrid.asParent());
    }
    
}
