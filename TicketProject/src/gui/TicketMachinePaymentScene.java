package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import machines.Operation;
import machines.TicketMachine;
import paymentMethods.PaymentMethod;

/**
 *
 * @author Manuele
 */
public class TicketMachinePaymentScene extends BridgeSceneGrid{
    public Label text;
    public Button cash, cCard, homePage;
    
    public TicketMachinePaymentScene(TicketMachine tMachine) {
        istantiateGrid();
        
        text = new Label("Choose your payment method");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        cash = new Button("Cash");
        cash.setOnAction(e -> {
            tMachine.setPaymentMethod(PaymentMethod.CASH);
        });
        cCard = new Button("Credit Card");
        cCard.setOnAction(e -> {
            tMachine.setPaymentMethod(PaymentMethod.CREDITCARD);
        });
        homePage = new Button("Homepage");
        homePage.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
        });
        HBox boxHomePage = new HBox();
        boxHomePage.setAlignment(Pos.CENTER_RIGHT);
        boxHomePage.getChildren().add(homePage);
        
        grid.add(text, 0, 0, 2, 1);
        grid.add(cash, 0, 1);
        grid.add(cCard, 1, 1);
        grid.add(boxHomePage, 2, 2);
    }
}
