package gui.collector.commands;

import controller.TicketCollectorSession;
import javafx.scene.Scene;


public interface CollectorSceneDispatcher {
    
    public Scene buildScene(TicketCollectorSession controller);
}
