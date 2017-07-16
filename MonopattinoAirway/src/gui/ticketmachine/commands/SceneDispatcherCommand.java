package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ticketmachine.TicketMachine;


public interface SceneDispatcherCommand {
    
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller);
}
