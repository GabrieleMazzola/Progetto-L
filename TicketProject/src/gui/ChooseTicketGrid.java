package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class ChooseTicketGrid extends BridgeSceneGrid{
    private Label note;
    private Button buySingle, buyMulti;
    private GridPane grid;
    
    public ChooseTicketGrid(TicketMachine tMachine) {
        note = new Label("Choose the ticket you want to buy");
        
        buySingle = new Button("Single \n 1,50");
        
        buySingle.setOnAction(e -> {
            tMachine.setTicketToSell("Single");
        });
        
        istantiateGrid();
        grid.add(note, 0, 0);
        grid.add(buySingle, 0, 1);
    }
}
