package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Paint;


public class WhiteSmallButton extends JFXButton{
    private final double height = 35.0, width = 95.0;
    
    public WhiteSmallButton(String text) {
        super(text);
        setRipplerFill(Paint.valueOf("#0040ff"));
        setStyle("-fx-background-color: #ffffff;-fx-padding: 10px;");
        setPrefHeight(height);
        setPrefWidth(width);
    }
}