package gui.ticketmachine.commands;

import controller.TicketMachineSession;
import gui.ticketmachine.InsertCreditCardGrid;
import javafx.scene.Scene;
import ticketmachine.TicketMachine;

/**
 *
 * @author Manuele
 */
public class InsertingCardCommand implements SceneDispatcherCommand{

    @Override
    public Scene selectScene(TicketMachine tMachine, TicketMachineSession controller) {
        InsertCreditCardGrid cCardScene = new InsertCreditCardGrid(controller);
        return new Scene(cCardScene.asParent());
    }
    
}