package gui.ticketmachine;

import gui.BridgeSceneGrid;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ticketmachine.*;


public class PushbuttonScene extends BridgeSceneGrid implements Observer{
    private Button  twoHundred, oneHundred, fifty, twenty, ten, five,
                    two, one, fiftyCents, twentyCents, tenCents, fiveCents,
                    twoCents, oneCent, homePage;
    private Label display, toPay;
    //TODO aggiungere Label per far vedere quanto ancora va inserito
    private TicketMachine tMachine;
    
    public PushbuttonScene(TicketMachine tMachine) {
        this.tMachine = tMachine;
        tMachine.addObserver(this);
        
        twoHundred = new Button("200€");
        oneHundred = new Button("100€");
        fifty = new Button("50€");
        twenty = new Button("20€");
        ten = new Button("10€");
        five = new Button("5€");
        two = new Button("2€");
        one = new Button("1€");
        fiftyCents = new Button("0,50€");
        twentyCents = new Button("0,20€");
        tenCents = new Button("0,10€");
        fiveCents = new Button("0,05€");
        twoCents = new Button("0,02€");
        oneCent = new Button("0,01€");
        homePage = new Button("Homepage");
        homePage.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
        });
        HBox boxHomePage = new HBox();
        boxHomePage.setAlignment(Pos.CENTER_RIGHT);
        boxHomePage.getChildren().add(homePage);
        rescaleButtons();
        
        display = new Label(tMachine.getInsertedMoney() + "");
        display.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        double remaining = tMachine.getCost() - tMachine.getInsertedMoney();
        toPay = new Label("remaining: " + remaining);
        toPay.setFont(Font.font("Tahoma", FontWeight.BOLD, 10));
        
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
        twoHundred.setOnAction(e -> {tMachine.insertMoney(200);});
        oneHundred.setOnAction(e -> {tMachine.insertMoney(100);});
        fifty.setOnAction(e -> {tMachine.insertMoney(50);});
        twenty.setOnAction(e -> {tMachine.insertMoney(20);});
        ten.setOnAction(e -> {tMachine.insertMoney(10);});
        five.setOnAction(e -> {tMachine.insertMoney(5);});
        two.setOnAction(e -> {tMachine.insertMoney(2);});
        one.setOnAction(e -> {tMachine.insertMoney(1);});
        fiftyCents.setOnAction(e -> {tMachine.insertMoney(0.5f);});
        twentyCents.setOnAction(e -> { tMachine.insertMoney(0.2f);});
        tenCents.setOnAction(e -> { tMachine.insertMoney(0.1f);});
        fiveCents.setOnAction(e -> { tMachine.insertMoney(0.05f);});
        twoCents.setOnAction(e -> { tMachine.insertMoney(0.02f);});
        oneCent.setOnAction(e -> { tMachine.insertMoney(0.01f);});
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
            display.setText(tMachine.getInsertedMoney() + "");
            int buf = (int)Math.round((tMachine.getCost() - tMachine.getInsertedMoney())*100);
            double remaining = (double) buf / 100;
            toPay.setText("remaining: " + remaining);
        }
    }
}
