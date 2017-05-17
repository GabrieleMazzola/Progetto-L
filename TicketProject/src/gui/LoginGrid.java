package gui;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class LoginGrid extends BridgeSceneGrid{
    private Text text, username, password, 
                        noCredentials = new Text("Please enter your credentials"),
                        fail = new Text("Wrong credentials");
    private TextField textUser;
    private PasswordField textPassword;
    private Button signIn, cancel;
    private HBox boxBtns, boxError;
    private TicketMachine tMachine;
    
    public LoginGrid(TicketMachine tMachine) {
        this.tMachine = tMachine;
        istantiateGrid();
        
        text = new Text("Please log in to your account");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        grid.add(text, 0, 0, 2, 1);
        
        username = new Text("Username: ");
        password = new Text("Password: ");
        
        grid.add(username, 0, 3);
        grid.add(password, 0, 4);
        
        textUser = new TextField();
        textPassword = new PasswordField();
        textPassword.setOnAction(e -> signIn.fire());
        
        grid.add(textUser, 1, 3);
        grid.add(textPassword, 1, 4);
        
        signIn = new Button("Sign in");
        signIn.setOnAction(e -> {
            String name = textUser.getText();
            String psw = textPassword.getText();
            
            if (emptyFields()) {
                boxError.getChildren().clear();
                noCredentials.setFill(Color.RED);
                noCredentials.setFont(Font.font("Tahoma", FontWeight.BLACK, 12));
                boxError.getChildren().add(noCredentials);
            }
            else if(!tMachine.login(name, psw)) {
                boxError.getChildren().clear();
                noCredentials.setFill(Color.RED);
                noCredentials.setFont(Font.font("Tahoma", FontWeight.BLACK, 12));
                boxError.getChildren().add(fail);
            }
        });
        
        boxBtns = new HBox();
        boxBtns.getChildren().addAll(signIn);
        boxBtns.setAlignment(Pos.BOTTOM_RIGHT);
        boxBtns.setSpacing(5);
        grid.add(boxBtns, 1, 5);
        
        boxError = new HBox();
        boxError.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(boxError, 1, 6);
    }
    
    private boolean emptyFields() {
        String user = textUser.getText();
        String pass = textPassword.getText();
        
        return user.isEmpty() | pass.isEmpty();
    }
}
