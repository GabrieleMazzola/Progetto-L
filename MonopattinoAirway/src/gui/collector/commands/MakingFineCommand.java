package gui.collector.commands;

import controller.TicketCollectorSession;
import gui.collector.MakeFineGrid;
import javafx.scene.Scene;

/**
 *
 * @author Manuele
 */
public class MakingFineCommand implements CollectorSceneDispatcher{

    @Override
    public Scene buildScene(TicketCollectorSession controller) {
        MakeFineGrid fineGrid = new MakeFineGrid(controller);
        return new Scene(fineGrid.asParent());
    }
}
