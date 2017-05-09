package gui;


import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


/**
 *
 * @author Manuele
 */
public class ShopScene {
    private final Text text = new Text("This is the shope scene");
    private GridPane grid;
    
    public ShopScene() {
        
        grid = new GridPane();
        grid.add(text, 0, 0);
    }
    
    public Parent asParent() {
        
        return grid;
    }
}
