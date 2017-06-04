package gui;

import codegeneration.CodeHandler;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.util.Calendar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machines.Operation;
import machines.QRCodeHandler;
import machines.TicketMachine;
import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public class ShowTicketGrid extends BridgeSceneGrid{
    private Label date, owner, type;
    private Image qrCode;
    private ImageView qrCodeView;
    private Button ok;
    
    public ShowTicketGrid(TicketMachine tMachine, Ticket ticket) {
        istantiateGrid();
        
        ok = new Button("Ok");
        ok.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
        });
        
        QRCodeHandler qrCodeHandler = QRCodeHandler.getInstance();
        try {
            long code = Long.parseLong(ticket.getCode());
            String criptedCode = CodeHandler.getInstance().encoder(code, "B");
            String qrCodePath = qrCodeHandler.buildQRCodeFromString(criptedCode, "TicketCode" + ticket.getCode());
            qrCode = new Image("file:"+qrCodePath);
        }
        catch(WriterException |IOException exc) {
            System.out.println(exc);
        }
        
        qrCodeView = new ImageView();
        qrCodeView.setImage(qrCode);
        
        if(ticket.getOwner() != null)
            owner = new Label("Owner: " + ticket.getOwner());
        else
            owner = new Label("Owner: -");
        
        type = new Label("Type: " + ticket.getType());
        
        if(ticket.getExpiryDate() != null)
            date = new Label("Created: " + Calendar.getInstance().getTime());
        else
            date = new Label("Created: -");
        
        grid.add(qrCodeView, 0, 0);
        grid.add(owner, 0, 1);
        grid.add(type, 0, 2);
        grid.add(date, 0, 3);
        grid.add(ok, 1, 3);
    }
}
