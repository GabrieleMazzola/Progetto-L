package gui.ticketmachine;

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


public class ChoosingPhysicalScene extends BridgeSceneGrid{
    private Text text;
    private Button singleTicket, seasonTicket, logout;
    
    public ChoosingPhysicalScene(TicketMachine tMachine) {
        
        text = new Text("Choose your ticket");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 40));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        singleTicket = new WhiteWideButton("Simple Ticket");
        singleTicket.setOnAction(e -> {
            tMachine.setOperation(Operation.BUYING_PTICKET);
        });
        seasonTicket = new WhiteWideButton("Season Ticket");
        seasonTicket.setOnAction(e -> {
            tMachine.setOperation(Operation.BUYING_PSEASON);
        });
        
        logout = new WhiteSmallButton("Logout");
        logout.setOnAction(e -> {
            tMachine.logout();
        });
        
        istantiateGrid();
        add(text, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 4, 1);
        add(singleTicket, 2, 0);
        add(seasonTicket, 2, 1);
        add(logout, 0, 3);
    }
}
