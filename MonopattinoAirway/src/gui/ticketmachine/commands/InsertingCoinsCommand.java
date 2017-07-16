package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.PushbuttonGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class InsertingCoinsCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        PushbuttonGrid moneyGrid = new PushbuttonGrid(tMachine, controller);
        return new Scene(moneyGrid.asParent());
    }
    
}
