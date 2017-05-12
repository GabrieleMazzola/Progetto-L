package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Manuele
 */
public class ChooseLoginGrid extends BridgeSceneGrid{
    private Label greetings;
    
    public ChooseLoginGrid() {
        greetings = new Label("Hi! Do you want to log in?");
        
        istantiateGrid();
        grid.add(greetings, 0, 0);
    }
}
