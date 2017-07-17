package gui.collector;

import controller.TicketCollectorSession;
import gui.BridgeSceneGrid;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Zubeer
 */
public class ChoosingOperationGrid extends BridgeSceneGrid{
    private Text text;
    private Button makeFineButton, verifyTicket, logout;
    
    /**
     *
     * @param controller
     */
    public ChoosingOperationGrid(TicketCollectorSession controller) {
        text = new Text("Beware people! " + controller.getUsername() + " is here!");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        makeFineButton = new Button("Make fine");
        makeFineButton.setOnAction(e -> {
            controller.selectedMakingFine();
        });
        
        verifyTicket = new Button("Verify");
        verifyTicket.setOnAction(e -> {
            controller.selectedCheckingTicket();
        });
        
        logout = new Button("Logout");
        logout.setOnAction(e -> {
            controller.logout();
        });
        
        istantiateGrid();
        add(text, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 2, 1);
        add(makeFineButton, 2, 0);
        add(verifyTicket, 2, 1);
        add(logout, 3, 0);
    }
}
