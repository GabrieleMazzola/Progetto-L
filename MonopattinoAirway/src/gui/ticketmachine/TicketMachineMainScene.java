package gui.ticketmachine;

import com.jfoenix.controls.JFXButton;
import gui.BridgeSceneGrid;
import gui.WhiteBigButton;
import gui.WhiteSmallButton;
import static java.awt.SystemColor.text;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ticketmachine.*;

/**
 *
 * @author Zubeer
 */
public class TicketMachineMainScene extends BridgeSceneGrid {
    private Label greetings;
    private JFXButton login, signUp, goOn;
    
    /**
     *
     * @param tMachine
     */
    public TicketMachineMainScene(TicketMachine tMachine) {
        
        greetings = new Label("Hello!");
        greetings.setFont(Font.font("Tahoma", FontWeight.BOLD, 53));
        
        login = new WhiteBigButton("Login");
        login.setOnAction(e -> {
            tMachine.setOperation(Operation.LOGGING_IN);
        });
        
        signUp = new WhiteSmallButton("Sign up");
        signUp.setLayoutX(435.0);
        signUp.setLayoutY(185.0);
        signUp.setOnAction(e -> {
            tMachine.setOperation(Operation.CREATING_USER);
        });
        HBox boxSignUp = new HBox();
        boxSignUp.setAlignment(Pos.BOTTOM_RIGHT);
        boxSignUp.getChildren().add(signUp);
        
        goOn = new WhiteBigButton("Continue without login");
        goOn.setOnAction(e -> {
            tMachine.setOperation(Operation.BUYING_PHYSICAL);
        });
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        istantiateGrid();
        
        add(greetings, 0, 0);
        add(boxSignUp, 0, 1);
        add(hSeparator, 1, 0, 2, 1);
        add(goOn, 3, 0);
        add(login, 3, 1);
    }
}
