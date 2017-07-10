package gui;

import centralsystem.factory.CSystemFactory;
import database.factories.DBMapperFactory;
import database.factories.SimMapperFactory;
import gui.ticketmachine.*;
import items.Sale;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ticketmachine.Operation;
import ticketmachine.*;


public class GUITicketMachine extends Application implements Observer{
    private Scene mainScene, buySimpleTicketScene, buySeasonScene, buyPhysicalScene, paymentMethodScene, choosingTicketScene, 
                  createUserScene, moneyScene, loginScene, showTicketScene, insertCardNumberScene, errorScene;
    private static TicketMachine tMachine;
    private Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        tMachine.addObserver(this);
        
        //Costruisco la scena principale
        TicketMachineMainScene mainSceneGrid = new TicketMachineMainScene(tMachine);
        mainScene = new Scene(mainSceneGrid.asParent());
        
        //Costruisco la scena di vendita dei biglietti fisici
        BuyPhysicalScene physicalScene = new BuyPhysicalScene(tMachine);
        buyPhysicalScene = new Scene(physicalScene.asParent());
        
        BuySingleTicketScene buySimpleScene = new BuySingleTicketScene(tMachine);
        buySimpleTicketScene = new Scene(buySimpleScene.asParent());
        
        //Costruisco la scena della scelta del metodo di pagamento
        TicketMachinePaymentScene paymentGrid = new TicketMachinePaymentScene(tMachine);
        paymentMethodScene = new Scene(paymentGrid.asParent());
        
        //Costruisco la scena della tastiera
        
        
        //Costruisco la scena di login
        LoginScene loginGrid = new LoginScene(tMachine);
        loginScene = new Scene(loginGrid.asParent());
        
        //Costruisco la scena di registrazione utente
        CreateUserScene userGrid = new CreateUserScene(tMachine);
        createUserScene = new Scene(userGrid.asParent());
        
        //Costruisco la scena del numero della carta di credito
        InsertCreditCardScene cCardScene = new InsertCreditCardScene(tMachine);
        insertCardNumberScene = new Scene(cCardScene.asParent());
        
        //Costruisco la scena d'errore
        ErrorScene errorGrid = new ErrorScene(tMachine, "Ticket machine unable to operate. Waiting for assistance");
        errorScene = new Scene(errorGrid.asParent());
        
        window.setScene(mainScene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSystemFactory.getInstance().buildCSystem(DBMapperFactory.class.getName());
        
        tMachine = new TicketMachine(0, 5000, "localhost");

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
                    TicketMachinePaymentScene paymentGrid = new TicketMachinePaymentScene(tMachine);
                    paymentMethodScene = new Scene(paymentGrid.asParent());
                    window.setScene(paymentMethodScene);
                    break;
                case INSERTING_COINS:
                    PushbuttonScene moneyGrid = new PushbuttonScene(tMachine);
                    moneyScene = new Scene(moneyGrid.asParent());
                    window.setScene(moneyScene);
                    break;
                case LOGGING_IN:
                    window.setScene(loginScene);
                    break;
                case CREATING_USER:
                    window.setScene(createUserScene);
                    break;
                case PRINTING_TICKET:
                    break;
                case INSERTING_CCARD:
                    window.setScene(insertCardNumberScene);
                    break;
                case BUYING_PHYSICAL:
                    window.setScene(buyPhysicalScene);
                    break;
                case CHOOSING_TICKET:
                    ChoosingTicketScene choosingScene = new ChoosingTicketScene(tMachine);
                    choosingTicketScene = new Scene(choosingScene.asParent());
                    window.setScene(choosingTicketScene);
                    break;
                case BUYING_SINGLE:
                    window.setScene(buySimpleTicketScene);
                    break;
                case BUYING_SEASON:
                    if(tMachine.hasLogged()){
                        BuySeasonScene seasonGrid = new BuySeasonScene(tMachine);
                        buySeasonScene = new Scene(seasonGrid.asParent());
                        window.setScene(buySeasonScene);
                    }  
                    break;    
            }
        }
        
        else if (arg instanceof Boolean) {
            if(!(boolean)arg) window.setScene(errorScene);
        }
        
        else if(arg instanceof Sale) {
            Sale ticket = (Sale) arg;
            ShowTicketScene showTicketGrid = new ShowTicketScene(tMachine, ticket);
            showTicketScene = new Scene(showTicketGrid.asParent());
            window.setScene(showTicketScene);
        }
    }
}
