package gui.collector;

import gui.collector.commands.*;
import static java.awt.SystemColor.window;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ticketcollector.CollectorOperation;
import ticketcollector.TicketCollector;

/**
 *
 * @author Manuele
 */
public class CollectorStage extends Stage implements Observer{
    private Map<CollectorOperation, CollectorSceneDispatcher> scenes;
    private TicketCollector collector;
    
    private final int width = 750, height = 600;
    
    public CollectorStage(TicketCollector collector) {
        this.collector = collector;
        collector.addObserver(this);
        
        initSceneMap();
        
        Scene loginScene = scenes.get(CollectorOperation.LOGGING_IN).buildScene(collector);
        setScene(loginScene);
        setSize();
        
        setOnCloseRequest(e -> {
            collector.logOut();
        });
    }
    
    private void initSceneMap() {
        scenes = new HashMap();
        scenes.put(CollectorOperation.LOGGING_IN, new LoginCommand());
        scenes.put(CollectorOperation.MAKING_FINE, new MakingFineCommand());
        scenes.put(CollectorOperation.SELECTING_OPERATION, new SelectingOperationCommand());
        scenes.put(CollectorOperation.VERIFYING_TICKET, new VerifyTicketCommand());
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof CollectorOperation) {
            CollectorOperation operation = (CollectorOperation)arg;
            Scene scene = scenes.get(operation).buildScene(collector);
            setScene(scene);
            setSize();
        }
    }
    
    private void setSize() {
        setHeight(height);
        setWidth(width);
    }
}