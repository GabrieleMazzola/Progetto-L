package main;

import controller.TicketMachineSession;
import gui.MoneyTankFrame;
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
    private Scene mainScene, buySimpleTicketScene, buySeasonScene, choosePhysical, paymentMethodScene, choosingTicketScene, 
                  createUserScene, moneyScene, loginScene, showTicketScene, insertCardNumberScene, errorScene, buyPSeason, buyPTicket;
    private static TicketMachine tMachine;
    private TicketMachineSession controller;
    private Stage window;
    private final int width = 750, height = 600;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        tMachine.addObserver(this);
        controller = new TicketMachineSession(tMachine);
        
        //Costruisco la scena principale
        TicketMachineMainScene mainSceneGrid = new TicketMachineMainScene(controller);
        mainScene = new Scene(mainSceneGrid.asParent());
        
        //Costruisco la scena di vendita dei biglietti fisici
        ChoosingPhysicalScene physicalScene = new ChoosingPhysicalScene(controller);
        choosePhysical = new Scene(physicalScene.asParent());
        
        BuySingleTicketScene buySimpleScene = new BuySingleTicketScene(tMachine, controller);
        buySimpleTicketScene = new Scene(buySimpleScene.asParent());
        
        BuyPhysicalTicketScene buyPTicketGrid = new BuyPhysicalTicketScene(tMachine, controller);
        buyPTicket = new Scene(buyPTicketGrid.asParent());
        
        //Costruisco la scena della scelta del metodo di pagamento
//        PaymentScene paymentGrid = new PaymentScene(controller);
//        paymentMethodScene = new Scene(paymentGrid.asParent());
        
        BuyPhysicalSeasonScene buyPSeasonGrid = new BuyPhysicalSeasonScene(tMachine, controller);
        buyPSeason = new Scene(buyPSeasonGrid.asParent());
        
        //Costruisco la scena di login
        LoginScene loginGrid = new LoginScene(controller);
        loginScene = new Scene(loginGrid.asParent());
        
        //Costruisco la scena di registrazione utente
        CreateUserScene userGrid = new CreateUserScene(controller);
        createUserScene = new Scene(userGrid.asParent());
        
        //Costruisco la scena del numero della carta di credito
        InsertCreditCardScene cCardScene = new InsertCreditCardScene(controller);
        insertCardNumberScene = new Scene(cCardScene.asParent());
        
        //Costruisco la scena d'errore
        ErrorScene errorGrid = new ErrorScene(tMachine, "Ticket machine unable to operate. Waiting for assistance");
        errorScene = new Scene(errorGrid.asParent());
        
        window.setScene(mainScene);
        setSize();
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        tMachine = new TicketMachine(0, 5000, "localhost");

         MoneyTankFrame debug = new MoneyTankFrame(tMachine);
         debug.setVisible(true);

         launch(args);
    }
    
    /**
     *
     * @param tMachine
     */
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
                    setSize();
                    break;
                case SELECTING_PAYMENT:
                    PaymentScene paymentGrid = new PaymentScene(controller);
                    paymentMethodScene = new Scene(paymentGrid.asParent());
                    window.setScene(paymentMethodScene);
                    setSize();
                    break;
                case INSERTING_COINS:
                    PushbuttonScene moneyGrid = new PushbuttonScene(tMachine, controller);
                    moneyScene = new Scene(moneyGrid.asParent());
                    window.setScene(moneyScene);
                    setSize();
                    break;
                case LOGGING_IN:
                    window.setScene(loginScene);
                    setSize();
                    break;
                case CREATING_USER:
                    window.setScene(createUserScene);
                    setSize();
                    break;
                case PRINTING_TICKET:
                    break;
                case INSERTING_CCARD:
                    window.setScene(insertCardNumberScene);
                    setSize();
                    break;
                case BUYING_PHYSICAL:
                    window.setScene(choosePhysical);
                    setSize();
                    break;
                case BUYING_PSEASON:
                    window.setScene(buyPSeason);
                    setSize();
                    break;
                case BUYING_PTICKET:
                    window.setScene(buyPTicket);
                    setSize();
                    break;
                case CHOOSING_TICKET:
                    ChoosingTicketScene choosingScene = new ChoosingTicketScene(tMachine, controller);
                    choosingTicketScene = new Scene(choosingScene.asParent());
                    window.setScene(choosingTicketScene);
                    setSize();
                    break;
                case BUYING_SINGLE:
                    window.setScene(buySimpleTicketScene);
                    setSize();
                    break;
                case BUYING_SEASON:
                    if(tMachine.hasLogged()){
                        BuySeasonScene seasonGrid = new BuySeasonScene(tMachine, controller);
                        buySeasonScene = new Scene(seasonGrid.asParent());
                        window.setScene(buySeasonScene);
                        setSize();
                    }  
                    break;    
            }
        }
        
        else if (arg instanceof Boolean) {
            if(!(boolean)arg) {
                window.setScene(errorScene);
                setSize();
            }
        }
        
        else if(arg instanceof Sale) {
            Sale ticket = (Sale) arg;
            ShowTicketScene showTicketGrid = new ShowTicketScene(controller, ticket);
            showTicketScene = new Scene(showTicketGrid.asParent());
            window.setScene(showTicketScene);
            //controller = new TicketMachineSession(tMachine);
            setSize();
        }
    }
    
    private void setSize() {
        window.setHeight(height);
        window.setWidth(width);
    }
}
