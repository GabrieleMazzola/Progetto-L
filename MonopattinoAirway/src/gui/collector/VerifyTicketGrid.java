package gui.collector;

import gui.BridgeSceneGrid;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
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
public class VerifyTicketGrid extends BridgeSceneGrid{
    private Text text, serialCodeText, resultText;
    private TextField serialCodeField;
    private Button verify, back;
    private HBox boxResult;
    
    /**
     *
     * @param collector
     */
    public VerifyTicketGrid(TicketCollector collector) {
        text = new Text("Which ticket shall I check?");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        serialCodeText = new Text("Serial code: ");
        
        serialCodeField = new TextField();
        serialCodeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    serialCodeField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        resultText = new Text();
        resultText.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 15));
        
        boxResult = new HBox();
        
        verify = new Button("Verify");
        verify.setOnAction(e -> {
            Long serialCode = Long.valueOf(serialCodeField.getText());
            Boolean result = collector.existsTicket(serialCode);
            
            if(result == null) {
                resultText.setFill(Color.YELLOW);
                resultText.setText("No connection");
            }
            else if(result == true) {
                resultText.setFill(Color.GREEN);
                resultText.setText("Valid ticket");
            }
            else if(result == false) {
                resultText.setFill(Color.RED);
                resultText.setText("Invalid ticket");
            }
            
            //Va rimosso e riaggiunto il text perche javafx consente di aggiungere
            //lo stesso oggetto una sola volta alla grid
            boxResult.getChildren().remove(resultText);
            boxResult.getChildren().add(resultText);
        });
        
        back = new Button("Back");
        back.setOnAction(e -> {
            collector.setOperation(CollectorOperation.SELECTING_OPERATION);
        });
        
        istantiateGrid();
        add(text, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 2, 1);
        add(serialCodeText, 2, 0);
        add(serialCodeField, 2, 1);
        add(verify, 3, 2);
        add(back, 3, 3);
        add(boxResult, 3, 0, 2, 1);
    }
}
