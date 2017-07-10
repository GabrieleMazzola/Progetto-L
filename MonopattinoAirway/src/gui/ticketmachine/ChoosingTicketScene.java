package gui.ticketmachine;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;


public class ChoosingTicketScene extends BridgeSceneGrid{
    private Label greetings;
    private Button singleTicket, multiTicket, seasonTicket, logout;
    
    public ChoosingTicketScene(TicketMachine tMachine) {
        
        greetings = new Label("Hello, " + tMachine.getLoggedUsername() + "!");
        greetings.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 30));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        singleTicket = new Button("Simple Ticket");
        singleTicket.setOnAction(e -> {
            tMachine.setOperation(Operation.BUYING_SINGLE);
        });
        multiTicket = new Button("Multi Ticket");
        multiTicket.setOnAction(e -> {
            tMachine.setOperation(Operation.BUYING_SINGLE);
            //TODO aggiungere possibilità di vendita di un biglietto multiplo
        });
        seasonTicket = new Button("Season Ticket");
        seasonTicket.setOnAction(e -> {
            tMachine.setOperation(Operation.BUYING_SEASON);
        });
        
        logout = new Button("Logout");
        logout.setOnAction(e -> {
            tMachine.logout();
        });
        
        istantiateGrid();
        add(greetings, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 3, 1);
        add(singleTicket, 2, 0);
        add(seasonTicket, 2, 1);
        add(multiTicket, 3, 0);
        add(logout, 0, 3);
    }
}
