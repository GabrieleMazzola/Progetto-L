package gui.ticketmachine;

import gui.BridgeSceneGrid;
import com.google.zxing.WriterException;
import controller.TicketMachineSession;
import gui.WhiteSmallButton;
import items.Sale;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import singleton.CodeHandler;
import singleton.QRCodeHandler;

public class ShowTicketGrid extends BridgeSceneGrid{
    private Label serialCode, date, duration, owner, type, changeLabel;
    private Image qrCode;
    private ImageView qrCodeView;
    private Button ok;
    
    /**
     *
     * @param controller
     * @param ticket
     */
    public ShowTicketGrid(TicketMachineSession controller, Sale ticket) {
        istantiateGrid();
        
        ok = new WhiteSmallButton("Ok");
        ok.setOnAction(e -> {
            controller.logout();
        });
        
        QRCodeHandler qrCodeHandler = QRCodeHandler.getInstance();
        try {
            long serial = ticket.getSerialCode();
            String criptedCode = CodeHandler.getInstance().encoder(serial, "B");
            String qrCodePath = qrCodeHandler.buildQRCodeFromString(criptedCode, "TicketCode" + ticket.getSerialCode());
            qrCode = new Image("file:"+qrCodePath);
        }
        catch(WriterException |IOException exc) {
            System.err.println(exc);
        }
        
        qrCodeView = new ImageView();
        qrCodeView.setImage(qrCode);
        
        serialCode = new Label("Serial code: " + ticket.getSerialCode());
        
        if(ticket.getUsername()!= null)
            owner = new Label("Owner: " + ticket.getUsername());
        else
            owner = new Label("Owner: -");
        
        type = new Label("Type: " + ticket.getType());
        
        date = new Label("Created: " + ticket.getSaleDate());
        
        if(ticket.getExpiryDate()!= null)
            duration = new Label("Deadline: "+ticket.getExpiryDate());
        
        int buffer = (int)Math.round(controller.getChange()*100);
        double change = (double)buffer/100;
        if(change > 0) {
            changeLabel = new Label("Your change: " + change + "€");
            add(changeLabel, 6, 0);
        }
        
        add(qrCodeView, 0, 0);
        add(serialCode, 1, 0);
        add(owner, 2, 0);
        add(type, 3, 0);
        add(date, 4, 0);
        add(duration, 5, 0);
        add(ok, 7, 1);
    }
}