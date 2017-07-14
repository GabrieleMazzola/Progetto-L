package gui.ticketmachine;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gui.BridgeSceneGrid;
import gui.PasswordFieldFL;
import gui.TextFieldFL;
import gui.WhiteSmallButton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;

/**
 *
 * @author Zubeer
 */
public class LoginScene extends BridgeSceneGrid{
    private Text text, fail;
    private JFXTextField textUser;
    private JFXPasswordField textPassword;
    private Button signIn, cancel;
    private HBox boxBtns, boxError;
    private VBox boxFields;
    private TicketMachine tMachine;
    
    /**
     *
     * @param tMachine
     */
    public LoginScene(TicketMachine tMachine) {
        this.tMachine = tMachine;
        istantiateGrid();
        
        text = new Text("Insert your credentials");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 40));
        grid.add(text, 0, 0, 2, 1);
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        textUser = new TextFieldFL("username");
        
        textPassword = new PasswordFieldFL("password");
        textPassword.setOnAction(e -> signIn.fire());
        
        boxFields = new VBox();
        boxFields.setAlignment(Pos.CENTER);
        boxFields.setSpacing(50);
        boxFields.setPadding(new Insets(30, 50, 30, 50));
        boxFields.getChildren().addAll(textUser, textPassword);
        
        fail = new Text();
        fail.setFill(Color.RED);
        fail.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        
        signIn = new WhiteSmallButton("Sign in");
        signIn.setOnAction(e -> {
            String name = textUser.getText();
            String psw = textPassword.getText();
            
            if (emptyFields()) {
                boxError.getChildren().clear();
                fail.setText("Fill in the fields");
                boxError.getChildren().add(fail);
            }
            
            else if(!tMachine.login(name, psw)) {
                textPassword.setText("");
                boxError.getChildren().clear();
                fail.setText("Wrong credentials");
                boxError.getChildren().add(fail);
            }
            else{
                textPassword.setText("");
                textUser.setText("");
                boxError.getChildren().clear();
                tMachine.setOperation(Operation.CHOOSING_TICKET);
            }
        });
        
        cancel = new WhiteSmallButton("Cancel");
        cancel.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
            textUser.setText("");
            textPassword.setText("");
        });
        
        boxBtns = new HBox();
        boxBtns.getChildren().addAll(signIn, cancel);
        boxBtns.setAlignment(Pos.BOTTOM_RIGHT);
        boxBtns.setSpacing(20);
        boxBtns.setPadding(new Insets(5, 20, 20, 20));
        
        boxError = new HBox();
        boxError.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(boxError, 1, 6);
        
        istantiateGrid();
        setVGap(30);
        
        add(text, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 2, 1);
        add(boxFields, 2, 1, 1, 1);
        add(boxBtns, 3, 1);
    }
    
    private boolean emptyFields() {
        String user = textUser.getText();
        String pass = textPassword.getText();
        
        return user.isEmpty() | pass.isEmpty();
    }
}
