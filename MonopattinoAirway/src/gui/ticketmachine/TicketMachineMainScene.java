package gui.ticketmachine;

import gui.BridgeSceneGrid;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ticketmachine.*;


public class TicketMachineMainScene extends BridgeSceneGrid {
    private Label greetings;
    private Button login, signUp, goOn;
    
    public TicketMachineMainScene(TicketMachine tMachine) {
        
        greetings = new Label("Hello!");
        greetings.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 30));
        
        login = new Button("Login");
        login.setOnAction(e -> {
            tMachine.setOperation(Operation.LOGGING_IN);
        });
        signUp = new Button("Sign up");
        signUp.setOnAction(e -> {
            tMachine.setOperation(Operation.CREATING_USER);
        });
        goOn = new Button("Continue without login");
        goOn.setOnAction(e -> {
            tMachine.setOperation(Operation.BUYING_PHYSICAL);
        });
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        istantiateGrid();
        
        add(greetings, 0, 0, 3, 1);
        add(hSeparator, 1, 0, 3, 1);
        add(signUp, 0, 2);
        add(goOn, 3, 1);
        add(login, 3, 2);
    }
}
