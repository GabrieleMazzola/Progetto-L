package gui.ticketmachine;

import gui.BridgeSceneGrid;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
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
public class InsertCreditCardScene extends BridgeSceneGrid{
    private LimitedTextField firstFour, secondFour, thirdFour, fourthFour;
    private HBox boxField;
    private Label label;
    private Text fail;
    private Button confirm, homepage;
    private HBox boxBtns;
    
    private final String separator = "-";
    
    /**
     *
     * @param tMachine
     */
    public InsertCreditCardScene(TicketMachine tMachine) {
        label = new Label("Insert your credit card number please");
        label.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        firstFour = new LimitedTextField(4);
        firstFour.setOnKeyPressed(e -> {
            String keyPressed = e.getCharacter();
            if(firstFour.hasReachedLimit() && !keyPressed.equals(KeyCode.BACK_SPACE)) secondFour.requestFocus();
        });
        secondFour = new LimitedTextField(4);
        secondFour.setOnKeyPressed(e -> {
            String keyPressed = e.getCharacter();
            if(secondFour.hasReachedLimit() && !keyPressed.equals(KeyCode.BACK_SPACE)) thirdFour.requestFocus();
        });
        thirdFour = new LimitedTextField(4);
        thirdFour.setOnKeyPressed(e -> {
            String keyPressed = e.getCharacter();
            if(thirdFour.hasReachedLimit() && !keyPressed.equals(KeyCode.BACK_SPACE)) fourthFour.requestFocus();
        });
        fourthFour = new LimitedTextField(4);
        
        boxField = new HBox();
        boxField.getChildren().addAll(firstFour, new Label(separator), secondFour, new Label(separator), thirdFour, new Label(separator), fourthFour);
        boxField.setAlignment(Pos.CENTER);
        boxField.setSpacing(5);
        
        confirm = new Button("Ok");
        confirm.setOnAction(e -> {
            if(!completlyFilled()) {
                fail = new Text("You must fill in all the spaces");
                fail.setFill(Color.RED);
                add(fail, 2, 1);
            }
            else {
                String cCardNumber = firstFour.getText() + secondFour.getText() + thirdFour.getText() + fourthFour.getText();
                if(!tMachine.buyTicketCreditCard(cCardNumber)) {
                    fail = new Text("Invalid credit card");
                    fail.setFill(Color.RED);
                    add(fail, 2, 1);
                }
            }
        });
        
        homepage = new Button("Back");
        homepage.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
        });
        
        boxBtns = new HBox();
        boxBtns.getChildren().addAll(homepage, confirm);
        boxBtns.setAlignment(Pos.BOTTOM_RIGHT);
        boxBtns.setSpacing(5);
        
        istantiateGrid();
        grid.setHgap(0.2);
        add(label, 0, 0, 7, 1);
        add(boxField, 1, 0, 7, 1);
        add(boxBtns, 2, 6);
    }
    
    private boolean completlyFilled() {
        return firstFour.getText() != "" && secondFour.getText() != "" && thirdFour.getText() != "" && fourthFour.getText() != "";
    }
}