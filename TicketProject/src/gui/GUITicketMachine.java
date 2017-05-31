package gui;

import centralsystem.CSystem;
import creator.CSystemFactory;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import machines.Operation;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class GUITicketMachine extends Application implements Observer{
    private Scene mainScene, paymentMethodScene, moneyScene, loginScene, 
                  showTicketScene, insertCardNumberScene, errorScene;
    private static TicketMachine tMachine;
    private Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        tMachine.addObserver(this);
        
        //Costruisco la scena principale
        TicketMachineMainScene mainSceneGrid = new TicketMachineMainScene(tMachine);
        mainScene = new Scene(mainSceneGrid.asParent());
        
        //Costruisco la scena della scelta del metodo di pagamento
        TicketMachinePaymentScene paymentGrid = new TicketMachinePaymentScene(tMachine);
        paymentMethodScene = new Scene(paymentGrid.asParent());
        
        //Costruisco la scena della tastiera
        PushbuttonMachineGrid moneyGrid = new PushbuttonMachineGrid(tMachine);
        moneyScene = new Scene(moneyGrid.asParent());
        
        //Costruisco la scena di login
        LoginGrid loginGrid = new LoginGrid(tMachine);
        loginScene = new Scene(loginGrid.asParent());
        
        //Costruisco la scena del numero della carta di credito
        InsertCreditCardScene cCardScene = new InsertCreditCardScene(tMachine);
        insertCardNumberScene = new Scene(cCardScene.asParent());
        
        //Costruisco la scena d'errore
        ErrorGrid errorGrid = new ErrorGrid(tMachine, "Ticket machine unable to operate. Waiting for assistance");
        errorScene = new Scene(errorGrid.asParent());
        
        window.setScene(mainScene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        CSystemFactory csFactory = CSystemFactory.getInstance();
        CSystem cSystem = csFactory.getCentralSystemInstance();
        
        CSystemSwingFrame viewCSystem = new CSystemSwingFrame(cSystem);
        viewCSystem.setVisible(true);
        */
        TicketMachine tm = new TicketMachine(5000, "10.87.156.248");
        tMachine = tm;
        
        MoneyTankFrame debug = new MoneyTankFrame(tMachine);
        debug.setVisible(true);
        
        launch(args);
    }
    
    public void setTicketMachine(TicketMachine tMachine) {
        this.tMachine = tMachine;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            
        }
        else if(arg instanceof Operation) {
            switch((Operation)arg) {
                case SELLING_TICKET:
                    window.setScene(mainScene);
                    break;
                case SELECTING_PAYMENT:
                    window.setScene(paymentMethodScene);
                    break;
                case INSERTING_COINS:
                    window.setScene(moneyScene);
                    break;
                case LOGGING_IN:
                    window.setScene(loginScene);
                    break;
                case PRINTING_TICKET:
                    ShowTicketGrid showTicketGrid = new ShowTicketGrid(tMachine);
                    showTicketScene = new Scene(showTicketGrid.asParent());
                    window.setScene(showTicketScene);
                    break;
                case INSERTING_CCARD:
                    window.setScene(insertCardNumberScene);
                    break;
            }
        }
        
        else if (arg instanceof Boolean) {
            if(!(boolean)arg) window.setScene(errorScene);
        }
    }
}
