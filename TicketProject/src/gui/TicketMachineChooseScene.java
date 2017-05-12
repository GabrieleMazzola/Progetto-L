package gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 *
 * @author Manuele
 */
public class TicketMachineChooseScene{
    private Button login, goOn;
    private boolean answer;
    
    public TicketMachineChooseScene() {
        login = new Button("Login");
        goOn = new Button("Contue without login");
        
        login.setOnAction(e -> {answer = true;});
        goOn.setOnAction(e -> {answer = false;});
    }
}
