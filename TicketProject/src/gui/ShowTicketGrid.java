package gui;

import java.util.Calendar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machines.Operation;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class ShowTicketGrid extends BridgeSceneGrid{
    private Label date;
    private Image qrCode;
    private ImageView qrCodeView;
    private Button ok;
    
    public ShowTicketGrid(TicketMachine tMachine) {
        istantiateGrid();
        
        ok = new Button("Ok");
        ok.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
        });
        
        qrCode = new Image("file:images/qrbuffer.png");
        
        qrCodeView = new ImageView();
        qrCodeView.setImage(qrCode);
        
        date = new Label("Created: " + Calendar.getInstance().getTime());
        
        grid.add(qrCodeView, 0, 0);
        grid.add(date, 0, 1);
        grid.add(ok, 1, 1);
    }
}
