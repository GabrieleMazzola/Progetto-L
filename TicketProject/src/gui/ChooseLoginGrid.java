package gui;

import javafx.scene.control.Label;

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
        
        grid.setStyle("-fx-background-image: url(\"LeafBackground.jpg\");");
    }
}
