package main;

import gui.collector.ChoosingOperationScene;
import gui.collector.LoginCollectorScene;
import gui.collector.MakeFineScene;
import gui.collector.VerifyTicketScene;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ticketcollector.CollectorOperation;
import ticketcollector.TicketCollector;


public class GUICollector extends Application implements Observer{
    private Scene loginScene, choosingOperationScene, makeFineScene, verifyingTicketScene;
    private static TicketCollector collector;
    private Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        collector.addObserver(this);
        
        LoginCollectorScene loginGrid = new LoginCollectorScene(collector);
        loginScene = new Scene(loginGrid.asParent());
        
        VerifyTicketScene verifyingGrid = new VerifyTicketScene(collector);
        verifyingTicketScene = new Scene(verifyingGrid.asParent());
        
        window.setScene(loginScene);
        
        window.setOnCloseRequest(e -> {
            collector.logOut();
        });
        
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        collector = new TicketCollector("10.87.130.83");
        
        launch(args);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof CollectorOperation) {
            CollectorOperation operation = (CollectorOperation)arg;
            switch(operation) {
                case LOGGING_IN:
                    window.setScene(loginScene);
                    break;
                case SELECTING_OPERATION:
                    ChoosingOperationScene choosingGrid = new ChoosingOperationScene(collector);
                    choosingOperationScene = new Scene(choosingGrid.asParent());
                    window.setScene(choosingOperationScene);
                    break;
                case MAKING_FINE:
                    MakeFineScene fineGrid = new MakeFineScene(collector);
                    makeFineScene = new Scene(fineGrid.asParent());
                    window.setScene(makeFineScene);
                    break;
                case VERIFYING_TICKET:
                    window.setScene(verifyingTicketScene);
                    break;
            }
        }
    }
}
