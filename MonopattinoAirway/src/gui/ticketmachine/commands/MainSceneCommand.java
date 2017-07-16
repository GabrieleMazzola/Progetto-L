package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.TicketMachineInitialGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;


public class MainSceneCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        TicketMachineInitialGrid mainSceneGrid = new TicketMachineInitialGrid(controller);
        return new Scene(mainSceneGrid.asParent());
    }
    
}
