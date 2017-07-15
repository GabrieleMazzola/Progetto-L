package gui.ticketmachine;

import controller.TicketMachineSession;
import gui.BridgeSceneGrid;
import gui.WhiteSmallButton;
import gui.WhiteWideButton;
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
public class ChoosingTicketScene extends BridgeSceneGrid{
    private Text greetings;
    private Button singleTicket, multiTicket, seasonTicket, logout;
    
    /**
     *
     * @param tMachine
     */
    public ChoosingTicketScene(TicketMachine tMachine, TicketMachineSession controller) {
        
        greetings = new Text("Hello, " + tMachine.getLoggedUsername() + "!");
        greetings.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 40));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        singleTicket = new WhiteWideButton("Simple Ticket");
        singleTicket.setOnAction(e -> {
            controller.selectBuyTicket();
        });
        multiTicket = new WhiteWideButton("Multi Ticket");
        multiTicket.setOnAction(e -> {
            controller.selectBuyTicket();
            //TODO aggiungere possibilità di vendita di un biglietto multiplo
        });
        seasonTicket = new WhiteWideButton("Season Ticket");
        seasonTicket.setOnAction(e -> {
            controller.selectBuySeason();
        });
        
        logout = new WhiteSmallButton("Logout");
        logout.setOnAction(e -> {
            controller.logout();
        });
        
        istantiateGrid();
        add(greetings, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 4, 1);
        add(singleTicket, 2, 0);
        add(seasonTicket, 2, 1);
        add(multiTicket, 3, 0);
        add(logout, 0, 3);
    }
}
