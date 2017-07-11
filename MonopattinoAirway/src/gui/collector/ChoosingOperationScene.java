package gui.collector;

import gui.BridgeSceneGrid;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ticketcollector.CollectorOperation;
import ticketcollector.TicketCollector;


public class ChoosingOperationScene extends BridgeSceneGrid{
    private Text text;
    private Button makeFineButton, verifyTicket, logout;
    
    public ChoosingOperationScene(TicketCollector collector) {
        text = new Text("Beware people! " + collector.getUsername() + " is here!");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        makeFineButton = new Button("Make fine");
        makeFineButton.setOnAction(e -> {
            collector.setOperation(CollectorOperation.MAKING_FINE);
        });
        
        verifyTicket = new Button("Verify");
        verifyTicket.setOnAction(e -> {
            collector.setOperation(CollectorOperation.VERIFYING_TICKET);
        });
        
        logout = new Button("Logout");
        logout.setOnAction(e -> {
            collector.logOut();
            collector.setOperation(CollectorOperation.LOGGING_IN);
        });
        
        istantiateGrid();
        add(text, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 2, 1);
        add(makeFineButton, 2, 0);
        add(verifyTicket, 2, 1);
        add(logout, 3, 0);
    }
}
