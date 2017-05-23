package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import machines.Operation;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class ErrorGrid extends BridgeSceneGrid{
    private Label messageLabel;
    private Button ok;
    
    public ErrorGrid(TicketMachine tMachine, String message) {
        messageLabel = new Label(message);
        ok = new Button("Ok");
        ok.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
        });
        istantiateGrid();
        grid.add(messageLabel, 1, 1);
        grid.add(ok, 2, 1);
    }
}
