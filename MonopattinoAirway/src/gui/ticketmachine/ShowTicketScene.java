package gui.ticketmachine;

import com.google.zxing.WriterException;
import items.Sale;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import singleton.CodeHandler;
import singleton.QRCodeHandler;
import ticketmachine.TicketMachine;


public class ShowTicketScene extends BridgeSceneGrid{
    private Label date, duration,owner, type;
    private Image qrCode;
    private ImageView qrCodeView;
    private Button ok;
    
    public ShowTicketScene(TicketMachine tMachine, Sale ticket) {
        istantiateGrid();
        
        ok = new Button("Ok");
        ok.setOnAction(e -> {
            tMachine.logout();
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
        
        if(ticket.getUsername()!= null)
            owner = new Label("Owner: " + ticket.getUsername());
        else
            owner = new Label("Owner: -");
        
        type = new Label("Type: " + ticket.getType());
        
        date = new Label("Created: " + ticket.getSaleDate());
        
        if(ticket.getExpiryDate()!= null)
            duration = new Label("Deadline: "+ticket.getExpiryDate());
        
            
        grid.add(qrCodeView, 0, 0);
        grid.add(owner, 0, 1);
        grid.add(type, 0, 2);
        grid.add(date, 0, 3);
        grid.add(duration, 0, 4);
        grid.add(ok, 1, 3);
    }
}