/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Francesco
 */
public class ErrorScene extends Stage {
    private Label errorMessage; 
    private Button closeButton;
    
    public ErrorScene(String error){
        errorMessage = new Label(error);
        closeButton = new Button("Close!");
        closeButton.setOnAction(e ->{
            this.close();
        });
        GridPane grid = new GridPane();
        grid.add(errorMessage,0,0);
        grid.add(closeButton,0,1);
        this.setScene(new Scene(grid));
    }
}
