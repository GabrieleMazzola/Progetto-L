package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class InsertCreditCardScene extends BridgeSceneGrid{
    private LimitedTextField firstFour, secondFour, thirdFour, fourthFour;
    private Label label, fail;
    private Button confirm;
    
    private final String separator = "-";
    
    public InsertCreditCardScene(TicketMachine tMachine) {
        label = new Label("Insert your credit card number please");
        
        firstFour = new LimitedTextField(4);
        firstFour.setOnKeyPressed(e -> {
            String keyPressed = e.getCharacter();
            if(firstFour.hasReachedLimit() && !keyPressed.equals(KeyCode.BACK_SPACE)) secondFour.requestFocus();
        });
        //firstFour.setScaleX(0.2);
        secondFour = new LimitedTextField(4);
        secondFour.setOnKeyPressed(e -> {
            String keyPressed = e.getCharacter();
            if(secondFour.hasReachedLimit() && !keyPressed.equals(KeyCode.BACK_SPACE)) thirdFour.requestFocus();
        });
        //secondFour.setScaleX(0.2);
        thirdFour = new LimitedTextField(4);
        thirdFour.setOnKeyPressed(e -> {
            String keyPressed = e.getCharacter();
            if(thirdFour.hasReachedLimit() && !keyPressed.equals(KeyCode.BACK_SPACE)) fourthFour.requestFocus();
        });
        //thirdFour.setScaleX(0.2);
        fourthFour = new LimitedTextField(4);
        //fourthFour.setScaleX(0.2);
        
        confirm = new Button("Ok");
        confirm.setOnAction(e -> {
            String cCardNumber = firstFour.getText() + secondFour.getText() + thirdFour.getText() + fourthFour.getText();
            if(!tMachine.buyTicketCreditCard(cCardNumber)) {
                fail = new Label("Invalid credit card");
                grid.add(fail, 0, 0);
            }
        });
        
        istantiateGrid();
        grid.setHgap(0.2);
        grid.add(label, 0, 0);
        grid.add(firstFour, 0, 1);
        grid.add(new Label(separator), 1, 1);
        grid.add(secondFour, 2, 1);
        grid.add(new Label(separator), 3, 1);
        grid.add(thirdFour, 4, 1);
        grid.add(new Label(separator), 5, 1);
        grid.add(fourthFour, 6, 1);
        grid.add(confirm, 6, 2);
    }
}
