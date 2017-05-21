package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machines.Operation;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class ShowTicketGrid extends BridgeSceneGrid{
    private Image qrCode;
    private ImageView qrCodeView;
    private Button ok;
    
    public ShowTicketGrid(TicketMachine tMachine) {
        istantiateGrid();
        
        ok = new Button("Ok");
        ok.setOnAction(e -> {
            tMachine.setOperation(Operation.SELLING_TICKET);
        });
        
        qrCode = new Image("file:///C:/Users/user/Desktop/Progetto/Sorgente/TicketProject/TicketProject/images/qrbuffer.png");
        
        qrCodeView = new ImageView();
        qrCodeView.setImage(qrCode);
        
        grid.add(qrCodeView, 0, 0);
        grid.add(ok, 1, 1);
    }
}
