package main;

import gui.MoneyTankFrame;
import gui.ticketmachine.*;
import javafx.application.Application;
import javafx.stage.Stage;
import ticketmachine.*;


public class TicketMachineLoader extends Application {
    private static TicketMachine tMachine;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage = new TicketMachineStage(tMachine);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        tMachine = new TicketMachine(0, 5000, "localhost");

         MoneyTankFrame debug = new MoneyTankFrame(tMachine);
         debug.setVisible(true);

         launch(args);
    }
}
