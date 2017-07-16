package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.LoginGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;


public class LoggingCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        LoginGrid loginGrid = new LoginGrid(controller);
        return new Scene(loginGrid.asParent());
    }
    
}