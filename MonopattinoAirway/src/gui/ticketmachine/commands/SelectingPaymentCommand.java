package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.ChoosingPaymentGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class SelectingPaymentCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        ChoosingPaymentGrid paymentGrid = new ChoosingPaymentGrid(controller);
        return new Scene(paymentGrid.asParent());
    }
}
