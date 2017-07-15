package gui.ticketmachine;

import controller.TicketMachineSession;
import gui.BridgeSceneGrid;
import gui.WhiteSmallButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
;


public class PaymentScene extends BridgeSceneGrid{
    public Label text,textCost;
    public Button cash, cCard, homePage;
    
    public PaymentScene(TicketMachineSession controller) {
        istantiateGrid();
        
        textCost = new Label("Cost: "+controller.getSelectedProductCost());
        text = new Label("Choose your payment method");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 40));
        
        cash = new WhiteSmallButton("Cash");
        cash.setOnAction(e -> {
            controller.selectCashPayment();
        });
        cCard = new WhiteSmallButton("Credit Card");
        cCard.setOnAction(e -> {
            controller.selectCardPayment();
        });
        homePage = new WhiteSmallButton("Homepage");
        homePage.setOnAction(e -> {
            controller.back();
        });
        HBox boxHomePage = new HBox();
        boxHomePage.setAlignment(Pos.CENTER_RIGHT);
        boxHomePage.getChildren().add(homePage);
        
        grid.add(textCost, 0, 3);
        grid.add(text, 0, 0, 2, 1);
        grid.add(cash, 0, 1);
        grid.add(cCard, 1, 1);
        grid.add(boxHomePage, 2, 2);
    }
}