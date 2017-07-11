package gui.ticketmachine;

import gui.BridgeSceneGrid;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;


public class CreateUserScene extends BridgeSceneGrid{
    
    private Text text, nameText, surnameText, cfText, emailText, usernameText, pswText, checkPswText,
                 fail;
    private TextField nameField, surnameField, emailField, cfField, usernameField;
    private PasswordField pswField, checkPswField;
    private Button ok, homepage;
    
    public CreateUserScene(TicketMachine tMachine) {
        initTexts();
        
        ok = new Button("Ok");
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
            
                if(psw.equals(checkPsw)) {
                    tMachine.createUser(name, surname, cf, username, psw, email);
                    tMachine.setOperation(Operation.LOGGING_IN);
                    resetFields();
                }
                else {
                    fail.setText("Password mismatch");
                    fail.setFill(Color.RED);
                    add(fail, 10, 0, 2, 1);
                }
            }
            else {
                fail.setText("Do not leave any field blank please");
                fail.setFill(Color.RED);
                add(fail, 10, 0, 2, 1);
            }
        });
        
        homepage = new Button("Homepage");
        homepage.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
            grid.getChildren().remove(fail);
            resetFields();
        });
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        istantiateGrid();
        
        add(text, 0, 0, 3, 1);
        add(hSeparator, 1, 0, 3, 1);
        add(nameText, 2, 0);
        add(nameField, 2, 1);
        add(surnameText, 3, 0);
        add(surnameField, 3, 1);
        add(cfText, 4, 0);
        add(cfField, 4, 1);
        add(usernameText, 5, 0);
        add(usernameField, 5, 1);
        add(emailText, 7, 0);
        add(emailField, 7, 1);
        add(pswText, 8, 0);
        add(pswField, 8, 1);
        add(checkPswText, 9, 0);
        add(checkPswField, 9, 1);
        add(homepage, 10, 2);
        add(ok, 10, 3);
    }
    
    private void initTexts() {
        text = new Text("Insert your data");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        fail = new Text();
        fail.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 13));
        nameText = new Text("Name: ");
        nameText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        surnameText = new Text("Surname: ");
        surnameText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        cfText = new Text("CF: ");
        cfText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        emailText = new Text("Email: ");
        emailText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        usernameText = new Text("Username: ");
        usernameText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        pswText = new Text("Password: ");
        pswText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        checkPswText = new Text("Reimmit password: ");
        checkPswText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        
        nameField = new TextField();
        surnameField = new TextField();
        cfField = new TextField();
        usernameField = new TextField();
        emailField = new TextField();
        pswField = new PasswordField();
        checkPswField = new PasswordField();
    }
    
    private void resetFields() {
        nameField.setText("");
        surnameField.setText("");
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
