package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.SelectSeasonGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class BuySeasonCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        SelectSeasonGrid seasonGrid = new SelectSeasonGrid(tMachine, controller);
        return new Scene(seasonGrid.asParent());
    }
    
}
