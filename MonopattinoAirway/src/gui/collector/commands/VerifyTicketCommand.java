package gui.collector.commands;

import controller.TicketCollectorSession;
import gui.collector.VerifyTicketGrid;
import javafx.scene.Scene;


public class VerifyTicketCommand implements CollectorSceneDispatcher{

    @Override
    public Scene buildScene(TicketCollectorSession controller) {
        VerifyTicketGrid verifyingGrid = new VerifyTicketGrid(controller);
        return new Scene(verifyingGrid.asParent());
    }
    
}
