package gui.ticketmachine;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.TicketMachineSession;
import gui.BridgeSceneGrid;
import gui.PasswordFieldFL;
import gui.TextFieldFL;
import gui.WhiteSmallButton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class CreateUserScene extends BridgeSceneGrid{
    
    private Text text, fail;
    private JFXTextField nameField, surnameField, emailField, cfField, usernameField;
    private JFXPasswordField pswField, checkPswField;
    private VBox boxFields;
    private Button ok, homepage;
    
    
    public CreateUserScene(TicketMachineSession controller) {
        text = new Text("Insert your data");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 40));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        nameField = new TextFieldFL("name");
        surnameField = new TextFieldFL("surname");
        emailField = new TextFieldFL("email");
        cfField = new TextFieldFL("cf");
        usernameField = new TextFieldFL("username");
        pswField = new PasswordFieldFL("password");
        checkPswField = new PasswordFieldFL("reimmit password");
        
        boxFields = new VBox();
        boxFields.setAlignment(Pos.CENTER);
        boxFields.setSpacing(20);
        boxFields.setPadding(new Insets(20, 50, 10, 50));
        boxFields.getChildren().addAll(text, hSeparator, nameField, surnameField, cfField, usernameField, emailField, pswField, checkPswField);
        
        ok = new WhiteSmallButton("Ok");
        ok.setOnAction(e -> {
            grid.getChildren().remove(fail);
            if(allFieldsFilled()) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String cf = cfField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String psw = pswField.getText();
                String checkPsw = checkPswField.getText();
                
                if(!psw.equals(checkPsw)) {
                    fail.setText("Password mismatch");
                    fail.setFill(Color.RED);
                    add(fail, 10, 0, 2, 1);
                    pswField.setText("");
                    checkPswField.setText("");
                }
                else if(!controller.signup(name, surname, cf, username, psw, email)) {
                    fail.setText("Username not available");
                    fail.setFill(Color.RED);
                    add(fail, 10, 0, 2, 1);
                    resetFields();
                }
                else {
                    fail.setText("Password mismatch");
                    fail.setFill(Color.RED);
                    add(fail, 10, 0, 2, 1);
                    resetFields();
                }
            }
            else {
                fail.setText("Do not leave any field blank please");
                fail.setFill(Color.RED);
                add(fail, 10, 0, 2, 1);
            }
        });
        
        homepage = new WhiteSmallButton("Homepage");
        homepage.setOnAction(e -> {
            controller.back();
            grid.getChildren().remove(fail);
            resetFields();
        });
        
        istantiateGrid();
        
        add(boxFields, 1, 1, 1, 1);
        add(homepage, 2, 2);
        add(ok, 2, 3);
    }
    
    private void resetFields() {
        nameField.setText("");
        surnameField.setText("");
        cfField.setText("");
        emailField.setText("");
        usernameField.setText("");
        pswField.setText("");
        checkPswField.setText("");
    }

    private boolean allFieldsFilled() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String cf = cfField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String psw = pswField.getText();
        String checkPsw = checkPswField.getText();
        
        return (!name.equals("")) && (!surname.equals("")) && (!cf.equals("")) && (!username.equals(""))
                && (!email.equals("")) && (!psw.equals("")) && (!checkPsw.equals(""));
    }
}
