package main;

import gui.collector.CollectorStage;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import ticketcollector.TicketCollector;


public class TicketCollectorLoader extends Application {
    private static TicketCollector collector;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage = new CollectorStage(collector);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        collector = new TicketCollector("localhost");
        
        launch(args);
    }
}
