package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.ChoosingTicketTypeGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class ChoosingTicketCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        ChoosingTicketTypeGrid choosingScene = new ChoosingTicketTypeGrid(tMachine, controller);
        return new Scene(choosingScene.asParent());
    }
    
}