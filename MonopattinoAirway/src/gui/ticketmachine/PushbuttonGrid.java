package gui.ticketmachine;

import controller.TicketMachineSession;
import gui.BridgeSceneGrid;
import gui.WhiteSmallButton;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ticketmachine.*;

/**
 *
 * @author Zubeer
 */
public class PushbuttonGrid extends BridgeSceneGrid implements Observer{
    private Button  twoHundred, oneHundred, fifty, twenty, ten, five,
                    two, one, fiftyCents, twentyCents, tenCents, fiveCents,
                    twoCents, oneCent, homePage;
    private Label display, toPay;
    private TicketMachineSession controller;
    
    /**
     *
     * @param tMachine
     */
    public PushbuttonGrid(TicketMachine tMachine, TicketMachineSession controller) {
        this.controller = controller;
        tMachine.addObserver(this);
        
        twoHundred = new WhiteSmallButton("200€");
        oneHundred = new WhiteSmallButton("100€");
        fifty = new WhiteSmallButton("50€");
        twenty = new WhiteSmallButton("20€");
        ten = new WhiteSmallButton("10€");
        five = new WhiteSmallButton("5€");
        two = new WhiteSmallButton("2€");
        one = new WhiteSmallButton("1€");
        fiftyCents = new WhiteSmallButton("0,50€");
        twentyCents = new WhiteSmallButton("0,20€");
        tenCents = new WhiteSmallButton("0,10€");
        fiveCents = new WhiteSmallButton("0,05€");
        twoCents = new WhiteSmallButton("0,02€");
        oneCent = new WhiteSmallButton("0,01€");
        homePage = new WhiteSmallButton("Homepage");
        homePage.setOnAction(e -> {
            controller.back();
            controller.back();
        });
        HBox boxHomePage = new HBox();
        boxHomePage.setAlignment(Pos.CENTER_RIGHT);
        boxHomePage.getChildren().add(homePage);
        rescaleButtons();
        
        display = new Label(controller.getInsertedMoney() + "");
        display.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        double remaining = controller.getSelectedProductCost() - controller.getInsertedMoney();
        toPay = new Label("remaining: " + remaining);
        toPay.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));
        
        istantiateGrid();
        grid.add(display, 0, 0, 4, 1);
        grid.add(toPay, 3, 0);
        grid.add(twoHundred, 0, 1);
        grid.add(oneHundred, 1, 1);
        grid.add(fifty, 2, 1);
        grid.add(twenty, 3, 1);
        grid.add(ten, 0, 2);
        grid.add(five, 1, 2);
        grid.add(two, 2, 2);
        grid.add(one, 3, 2);
        grid.add(fiftyCents, 0, 3);
        grid.add(twentyCents, 1, 3);
        grid.add(tenCents, 2, 3);
        grid.add(fiveCents, 3, 3);
        grid.add(twoCents, 0, 4);
        grid.add(oneCent, 1, 4);
        grid.add(homePage, 2, 4, 2, 1);
        
        addActionToButtons();
    }
    
    private void addActionToButtons() {
        twoHundred.setOnAction(e -> {controller.insertingMoney(200);});
        oneHundred.setOnAction(e -> {controller.insertingMoney(100);});
        fifty.setOnAction(e -> {controller.insertingMoney(50);});
        twenty.setOnAction(e -> {controller.insertingMoney(20);});
        ten.setOnAction(e -> {controller.insertingMoney(10);});
        five.setOnAction(e -> {controller.insertingMoney(5);});
        two.setOnAction(e -> {controller.insertingMoney(2);});
        one.setOnAction(e -> {controller.insertingMoney(1);});
        fiftyCents.setOnAction(e -> {controller.insertingMoney(0.5f);});
        twentyCents.setOnAction(e -> {controller.insertingMoney(0.2f);});
        tenCents.setOnAction(e -> {controller.insertingMoney(0.1f);});
        fiveCents.setOnAction(e -> {controller.insertingMoney(0.05f);});
        twoCents.setOnAction(e -> {controller.insertingMoney(0.02f);});
        oneCent.setOnAction(e -> {controller.insertingMoney(0.01f);});
    }
    
    private void rescaleButtons() {
        int size = 60;
        twoHundred.setMaxWidth(size);
        twoHundred.setMaxWidth(size);
        
        oneHundred.setMaxWidth(size);
        oneHundred.setMinWidth(size);
        
        fifty.setMaxWidth(size);
        fifty.setMinWidth(size);
        
        twenty.setMaxWidth(size);
        twenty.setMinWidth(size);
        
        ten.setMaxWidth(size);
        ten.setMinWidth(size);
        
        five.setMaxWidth(size);
        five.setMinWidth(size);
        
        two.setMaxWidth(size);
        two.setMinWidth(size);
        
        one.setMaxWidth(size);
        one.setMinWidth(size);
        
        fiftyCents.setMaxWidth(size);
        fiftyCents.setMinWidth(size);
        
        twentyCents.setMaxWidth(size);
        twentyCents.setMinWidth(size);
        
        tenCents.setMaxWidth(size);
        tenCents.setMinWidth(size);
        
        fiveCents.setMaxWidth(size);
        fiveCents.setMinWidth(size);
        
        twoCents.setMaxWidth(size);
        twoCents.setMinWidth(size);
        
        oneCent.setMaxWidth(size);
        oneCent.setMinWidth(size);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Double) {
            display.setText(controller.getInsertedMoney() + "");
            int buf = (int)Math.round((controller.getSelectedProductCost() - controller.getInsertedMoney())*100);
            double remaining = (double) buf / 100;
            toPay.setText("remaining: " + remaining);
        }
    }
}
