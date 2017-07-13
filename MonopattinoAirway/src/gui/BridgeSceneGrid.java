package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Zubeer
 */
public abstract class BridgeSceneGrid {

    /**
     *
     */
    protected GridPane grid;
    
    /**
     *
     */
    protected void istantiateGrid() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25 , 25));
    }
    
    /**
     *
     * @return
     */
    public Parent asParent() {
        return grid;
    }
    
    /**
     *
     * @param node
     * @param row
     * @param column
     */
    public void add(Node node, int row, int column) {
        grid.add(node, column, row);
    }
    
    /**
     *
     * @param node
     * @param row
     * @param column
     * @param rowSpan
     * @param columnSpan
     */
    public void add(Node node, int row, int column, int rowSpan, int columnSpan) {
        grid.add(node, column, row, rowSpan, columnSpan);
    }
}
