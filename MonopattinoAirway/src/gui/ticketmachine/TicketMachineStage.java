package gui.ticketmachine;

import controller.TicketMachineSession;
import gui.ticketmachine.commands.*;
import items.Sale;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;


public class TicketMachineStage extends Stage implements Observer{
    private TicketMachineSession controller;
    private TicketMachine tMachine;
    private Map<Operation, SceneDispatcherCommand> scenes;
    
    private final int width = 750, height = 600;
    
    public TicketMachineStage(TicketMachine tMachine) {
        this.tMachine = tMachine;
        tMachine.addObserver(this);
        controller = new TicketMachineSession(tMachine);
        
        initSceneMap();
        
        Scene mainScene = new Scene(new TicketMachineInitialGrid(controller).asParent());
        setScene(mainScene);
        setSize();
        
        setOnCloseRequest(e -> {
            close();
        });
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Operation) {
            Operation op = (Operation)arg;
            Scene scene = scenes.get(op).selectScene(tMachine, controller);
            setScene(scene);
            setSize();
        }
        
        else if (arg instanceof Boolean) {
            if(!(boolean)arg) {
                ErrorScene errorGrid = new ErrorScene(tMachine, "Ticket Machine " + tMachine.getCod() + " can't operate :(");
                setScene(new Scene(errorGrid.asParent()));
                setSize();
            }
        }
        
        else if(arg instanceof Sale) {
            Sale ticket = (Sale) arg;
            ShowTicketGrid showTicketGrid = new ShowTicketGrid(controller, ticket);
            Scene showTicketScene = new Scene(showTicketGrid.asParent());
            setScene(showTicketScene);
            setSize();
            controller = new TicketMachineSession(tMachine);
        }
    }
    
    private void setSize() {
        setHeight(height);
        setWidth(width);
    }

    private void initSceneMap() {
        scenes = new HashMap();
        scenes.put(Operation.SELLING_TICKET, new MainSceneCommand());
        scenes.put(Operation.SELECTING_PAYMENT, new SelectingPaymentCommand());
        scenes.put(Operation.INSERTING_COINS, new InsertingCoinsCommand());
        scenes.put(Operation.LOGGING_IN, new LoggingCommand());
        scenes.put(Operation.CREATING_USER, new CreatingUserCommand());
        scenes.put(Operation.CHOOSING_TICKET, new ChoosingTicketCommand());
        scenes.put(Operation.INSERTING_CCARD, new InsertingCardCommand());
        scenes.put(Operation.BUYING_PHYSICAL, new BuyPhysicalCommand());
        scenes.put(Operation.BUYING_PSEASON, new BuyPSeasonCommand());
        scenes.put(Operation.BUYING_PTICKET, new BuyPTicketCommand());
        scenes.put(Operation.BUYING_SINGLE, new BuyTicketCommand());
        scenes.put(Operation.BUYING_SEASON, new BuySeasonCommand());
    }
}
