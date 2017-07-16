package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.ChoosingPhysicalTypeGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class BuyPhysicalCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        ChoosingPhysicalTypeGrid physicalScene = new ChoosingPhysicalTypeGrid(controller);
        return new Scene(physicalScene.asParent());
    }
    
}