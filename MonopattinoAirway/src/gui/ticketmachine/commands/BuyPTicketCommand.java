package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.SelectPTicketGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class BuyPTicketCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        SelectPTicketGrid buyPTicketGrid = new SelectPTicketGrid(tMachine, controller);
        return new Scene(buyPTicketGrid.asParent());
    }
    
}
