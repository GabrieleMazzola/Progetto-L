package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Paint;

/**
 *
 * @author Manuele
 */
public class WhiteWideButton extends JFXButton{
    private final double height = 45.0, width = 250.0;
    
    public WhiteWideButton(String text) {
        super(text);
        setRipplerFill(Paint.valueOf("#0040ff"));
        setStyle("-fx-background-color: #ffffff;-fx-padding: 10px;");
        setPrefHeight(height);
        setPrefWidth(width);
    }
}
