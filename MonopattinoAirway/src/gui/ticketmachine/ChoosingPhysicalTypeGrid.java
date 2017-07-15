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


public class ChoosingPhysicalTypeGrid extends BridgeSceneGrid{
    private Text text;
    private Button singleTicket, seasonTicket, back;
    
    public ChoosingPhysicalTypeGrid(TicketMachineSession controller) {
        
        text = new Text("Choose your ticket");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 40));
        
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        singleTicket = new WhiteWideButton("Simple Ticket");
        singleTicket.setOnAction(e -> {
            controller.selectBuyPhysicalSimple();
        });
        seasonTicket = new WhiteWideButton("Season Ticket");
        seasonTicket.setOnAction(e -> {
            controller.selectBuyPhysicalSeason();
        });
        
        back = new WhiteSmallButton("Back");
        back.setOnAction(e -> {
            controller.back();
        });
        
        istantiateGrid();
        add(text, 0, 0, 2, 1);
        add(hSeparator, 1, 0, 4, 1);
        add(singleTicket, 2, 0);
        add(seasonTicket, 2, 1);
        add(back, 0, 3);
    }
}
