package gui.ticketmachine;

import gui.BridgeSceneGrid;
import items.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;

/**
 *
 * @author Zubeer
 */
public class BuySeasonScene extends BridgeSceneGrid{

    private final Text text;
    private Button back;
    
    /**
     *
     * @param tMachine
     */
    public BuySeasonScene(TicketMachine tMachine){
    
        text = new Text("Choose your season, " + tMachine.getLoggedUsername());
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        back = new Button("Back");
        back.setOnAction(e->{
            tMachine.setOperation(Operation.CHOOSING_TICKET);
        });
       
        istantiateGrid();
        add(text, 0, 0, 3, 1);
        add(hSeparator, 1, 0, 3, 1);
        addAllButtons(tMachine);
    }
    
    private void addAllButtons(TicketMachine tMachine) {
        List<Product> simpleTickets = getAllSeasonTickets(tMachine);
        int row = 0;
        int column = 0;
        
        for(Product product : simpleTickets) {
            Button button = new Button(product.getDescription() + "\n-\n" + product.getDuration() + " months");
            button.setOnAction(e -> {
                try {
                    tMachine.setTicketToSell(product.getType());
                    tMachine.setOperation(Operation.SELECTING_PAYMENT);
                }
                catch(ClassNotFoundException|IllegalAccessException|InstantiationException ex) {
                    ex.printStackTrace();
                }
            });
            add(button, row%3 + 2, column%3);
            column++;
            if(column%3 == 0)
                row++;
        }
        
        this.add(back, row%3 + 2, 3);
    }
    
    private List<Product> getAllSeasonTickets(TicketMachine tMachine) {
        List<Product> seasonTickets = new ArrayList();
        
        Map<String, Product> products = tMachine.getAvailableProducts();
        for(Map.Entry<String, Product> product : products.entrySet()) {
            if(isSeasonType(product.getValue()))
                seasonTickets.add(product.getValue());
        }
        
        return seasonTickets;
    }
    
    private boolean isSeasonType(Product p) {
        String type = p.getType();
        return type.charAt(0) == 'S';
    }
}
