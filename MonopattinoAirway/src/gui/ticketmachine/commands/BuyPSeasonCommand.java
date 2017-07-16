package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.SelectPSeasonGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class BuyPSeasonCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        SelectPSeasonGrid buyPSeasonGrid = new SelectPSeasonGrid(tMachine, controller);
        return new Scene(buyPSeasonGrid.asParent());
    }
    
}