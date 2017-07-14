package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Paint;


public class WhiteBigButton extends JFXButton{
    private final double height = 100.0, width = 175.0;
    
    public WhiteBigButton(String text) {
        super(text);
        setRipplerFill(Paint.valueOf("#0040ff"));
        setStyle("-fx-background-color: #ffffff;-fx-padding: 10px;");
        setPrefHeight(height);
        setPrefWidth(width);
    }
}
