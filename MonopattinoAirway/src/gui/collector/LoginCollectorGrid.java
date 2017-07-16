package gui.collector;

import gui.BridgeSceneGrid;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ticketcollector.CollectorOperation;
import ticketcollector.TicketCollector;

/**
 *
 * @author Zubeer
 */
public class LoginCollectorGrid extends BridgeSceneGrid{
    private Text text, username, password, fail;
    private TextField textUser;
    private PasswordField textPassword;
    private Button signIn;
    private HBox boxBtns;
    
    /**
     *
     * @param collector
     */
    public LoginCollectorGrid(TicketCollector collector) {
        istantiateGrid();
        
        fail = new Text();
        fail.setFill(Color.RED);
        
        text = new Text("Good morning!\nInsert your credentials to start working");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        username = new Text("Username: ");
        password = new Text("Password: ");
        
        textUser = new TextField();
        textPassword = new PasswordField();
        textPassword.setOnAction(e -> signIn.fire());
        
        signIn = new Button("Sign in");
        signIn.setOnAction(e -> {
            String name = textUser.getText();
            String psw = textPassword.getText();
            
            if (emptyFields()) {
                fail.setText("Fill in your informations please!");
                fail.setFont(Font.font("Tahoma", FontWeight.BLACK, 12));
                add(fail, 5, 0, 2, 1);
            }
            
            else if(!collector.loginCollector(name, psw)) {
                textPassword.setText("");
                fail.setText("Invalid credentials");
                fail.setFont(Font.font("Tahoma", FontWeight.BLACK, 12));
                add(fail, 5, 0, 2, 1);
            }
            else{
                textPassword.setText("");
                textUser.setText("");
                collector.setOperation(CollectorOperation.SELECTING_OPERATION);
            }
        });
        
        
        boxBtns = new HBox();
        boxBtns.getChildren().addAll(signIn);
        boxBtns.setAlignment(Pos.BOTTOM_RIGHT);
        boxBtns.setSpacing(5);
        
        
        add(text, 0, 0, 3, 1);
        add(hSeparator, 1, 0, 3, 1);
        add(username, 3, 1, 1, 1);
        add(password, 4, 1, 1, 1);
        add(textUser, 3, 2, 1, 1);
        add(textPassword, 4, 2, 1, 1);
        add(boxBtns, 5, 2);
    }
    
    private boolean emptyFields() {
        String user = textUser.getText();
        String pass = textPassword.getText();
        
        return user.isEmpty() | pass.isEmpty();
    }
}
