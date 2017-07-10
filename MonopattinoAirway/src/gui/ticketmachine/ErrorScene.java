package gui.ticketmachine;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;


public class ErrorScene extends BridgeSceneGrid{
    private Label messageLabel;
    private Button ok;
    
    public ErrorScene(TicketMachine tMachine, String message) {
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