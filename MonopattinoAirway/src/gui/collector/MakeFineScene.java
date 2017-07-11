package gui.collector;

import gui.BridgeSceneGrid;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ticketcollector.CollectorOperation;
import ticketcollector.TicketCollector;


public class MakeFineScene extends BridgeSceneGrid{
    private Text text, cfText, amountText, resultText;
    private TextField cfField, amountField;
    private Button send, back;
    private HBox boxBtns, boxFail;
    
    public MakeFineScene(TicketCollector collector) {
        
        text = new Text("It's a great day to make fines!");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        cfText = new Text("Fiscal code: ");
        amountText = new Text("Amount: ");
        
        cfField = new TextField();
        
        amountField = new TextField();
        amountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    amountField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        resultText = new Text();
        boxFail = new HBox();
        
        send = new Button("Send");
        send.setOnAction(e -> {
            //TODO controlli
            double amount = Double.valueOf(amountField.getText());
            Boolean result = collector.addFine(cfField.getText(), amount);
            if(result == null) {
                resultText.setFill(Color.YELLOW);
                resultText.setText("Cannot parse");
            }
            else if(result == false) {
                resultText.setFill(Color.RED);
                resultText.setText("Fine added offline");
            }
            else if(result == true) {
                resultText.setFill(Color.GREEN);
                resultText.setText("Fine added");
            }
            
            boxFail.getChildren().remove(resultText);
            boxFail.getChildren().add(resultText);
        });
        
        back = new Button("Back");
        back.setOnAction(e -> {
            collector.setOperation(CollectorOperation.SELECTING_OPERATION);
        });
        
        boxBtns = new HBox();
        boxBtns.getChildren().addAll(send, back);
        boxBtns.setAlignment(Pos.BOTTOM_RIGHT);
        boxBtns.setSpacing(5);
        
        istantiateGrid();
        add(text, 0, 0, 3, 1);
        add(cfText, 2, 0);
        add(cfField, 2, 1);
        add(amountText, 3, 0);
        add(amountField, 3, 1);
        add(boxBtns, 4, 1);
        add(boxFail, 4, 0, 1, 1);
    }
}
