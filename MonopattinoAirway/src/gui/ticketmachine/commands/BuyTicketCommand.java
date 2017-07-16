package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.SelectTicketGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class BuyTicketCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        SelectTicketGrid buySimpleScene = new SelectTicketGrid(tMachine, controller);
        return new Scene(buySimpleScene.asParent());
    }
    
}