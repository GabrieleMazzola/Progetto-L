package gui.collector.commands;

import gui.collector.VerifyTicketGrid;
import gui.collector.commands.CollectorSceneDispatcher;
import javafx.scene.Scene;
import ticketcollector.TicketCollector;


public class VerifyTicketCommand implements CollectorSceneDispatcher{

    @Override
    public Scene buildScene(TicketCollector collector) {
        VerifyTicketGrid verifyingGrid = new VerifyTicketGrid(collector);
        return new Scene(verifyingGrid.asParent());
    }
    
}
