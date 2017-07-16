package gui.collector.commands;

import javafx.scene.Scene;
import ticketcollector.TicketCollector;


public interface CollectorSceneDispatcher {
    
    public Scene buildScene(TicketCollector collector);
}
