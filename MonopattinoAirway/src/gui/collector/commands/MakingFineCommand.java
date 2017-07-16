package gui.collector.commands;

import gui.collector.MakeFineGrid;
import javafx.scene.Scene;
import ticketcollector.TicketCollector;

/**
 *
 * @author Manuele
 */
public class MakingFineCommand implements CollectorSceneDispatcher{

    @Override
    public Scene buildScene(TicketCollector collector) {
        MakeFineGrid fineGrid = new MakeFineGrid(collector);
        return new Scene(fineGrid.asParent());
    }
}
