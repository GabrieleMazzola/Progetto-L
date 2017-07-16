package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.CreateUserGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;


public class CreatingUserCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        CreateUserGrid userGrid = new CreateUserGrid(controller);
        return new Scene(userGrid.asParent());
    }
    
}