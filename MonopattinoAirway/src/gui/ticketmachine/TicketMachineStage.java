package gui.ticketmachine;

import controller.TicketMachineSession;
import items.Sale;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ticketmachine.Operation;
import ticketmachine.TicketMachine;


public class TicketMachineStage extends Stage implements Observer{
    private Scene mainScene, buySimpleTicketScene, buySeasonScene, choosePhysical, paymentMethodScene, choosingTicketScene, 
                  createUserScene, moneyScene, loginScene, showTicketScene, insertCardNumberScene, errorScene, buyPSeason, buyPTicket;
    private TicketMachineSession controller;
    private TicketMachine tMachine;
    private final int width = 750, height = 600;
    
    public TicketMachineStage(TicketMachine tMachine) {
        this.tMachine = tMachine;
        tMachine.addObserver(this);
        
        controller = new TicketMachineSession(tMachine);
        
        //Costruisco la scena principale
        TicketMachineInitialGrid mainSceneGrid = new TicketMachineInitialGrid(controller);
        mainScene = new Scene(mainSceneGrid.asParent());
        
        //Costruisco la scena di vendita dei biglietti fisici
        ChoosingPhysicalTypeGrid physicalScene = new ChoosingPhysicalTypeGrid(controller);
        choosePhysical = new Scene(physicalScene.asParent());
        
        SelectTicketGrid buySimpleScene = new SelectTicketGrid(tMachine, controller);
        buySimpleTicketScene = new Scene(buySimpleScene.asParent());
        
        SelectPTicketGrid buyPTicketGrid = new SelectPTicketGrid(tMachine, controller);
        buyPTicket = new Scene(buyPTicketGrid.asParent());
        
        //Costruisco la scena della scelta del metodo di pagamento
//        ChoosingPaymentGrid paymentGrid = new ChoosingPaymentGrid(controller);
//        paymentMethodScene = new Scene(paymentGrid.asParent());
        
        SelectPSeasonGrid buyPSeasonGrid = new SelectPSeasonGrid(tMachine, controller);
        buyPSeason = new Scene(buyPSeasonGrid.asParent());
        
        //Costruisco la scena di login
        LoginGrid loginGrid = new LoginGrid(controller);
        loginScene = new Scene(loginGrid.asParent());
        
        //Costruisco la scena di registrazione utente
        CreateUserGrid userGrid = new CreateUserGrid(controller);
        createUserScene = new Scene(userGrid.asParent());
        
        //Costruisco la scena del numero della carta di credito
        InsertCreditCardGrid cCardScene = new InsertCreditCardGrid(controller);
        insertCardNumberScene = new Scene(cCardScene.asParent());
        
        //Costruisco la scena d'errore
        ErrorScene errorGrid = new ErrorScene(tMachine, "Ticket machine unable to operate. Waiting for assistance");
        errorScene = new Scene(errorGrid.asParent());
        
        setScene(mainScene);
        setSize();
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            
        }
        else if(arg instanceof Operation) {
            switch((Operation)arg) {
                case SELLING_TICKET:
                    setScene(mainScene);
                    setSize();
                    break;
                case SELECTING_PAYMENT:
                    ChoosingPaymentGrid paymentGrid = new ChoosingPaymentGrid(controller);
                    paymentMethodScene = new Scene(paymentGrid.asParent());
                    setScene(paymentMethodScene);
                    setSize();
                    break;
                case INSERTING_COINS:
                    PushbuttonGrid moneyGrid = new PushbuttonGrid(tMachine, controller);
                    moneyScene = new Scene(moneyGrid.asParent());
                    setScene(moneyScene);
                    setSize();
                    break;
                case LOGGING_IN:
                    setScene(loginScene);
                    setSize();
                    break;
                case CREATING_USER:
                    setScene(createUserScene);
                    setSize();
                    break;
                case PRINTING_TICKET:
                    break;
                case INSERTING_CCARD:
                    setScene(insertCardNumberScene);
                    setSize();
                    break;
                case BUYING_PHYSICAL:
                    setScene(choosePhysical);
                    setSize();
                    break;
                case BUYING_PSEASON:
                    setScene(buyPSeason);
                    setSize();
                    break;
                case BUYING_PTICKET:
                    setScene(buyPTicket);
                    setSize();
                    break;
                case CHOOSING_TICKET:
                    ChoosingTicketTypeGrid choosingScene = new ChoosingTicketTypeGrid(tMachine, controller);
                    choosingTicketScene = new Scene(choosingScene.asParent());
                    setScene(choosingTicketScene);
                    setSize();
                    break;
                case BUYING_SINGLE:
                    setScene(buySimpleTicketScene);
                    setSize();
                    break;
                case BUYING_SEASON:
                    if(tMachine.hasLogged()){
                        SelectSeasonGrid seasonGrid = new SelectSeasonGrid(tMachine, controller);
                        buySeasonScene = new Scene(seasonGrid.asParent());
                        setScene(buySeasonScene);
                        setSize();
                    }  
                    break;    
            }
        }
        
        else if (arg instanceof Boolean) {
            if(!(boolean)arg) {
                setScene(errorScene);
                setSize();
            }
        }
        
        else if(arg instanceof Sale) {
            Sale ticket = (Sale) arg;
            ShowTicketGrid showTicketGrid = new ShowTicketGrid(controller, ticket);
            showTicketScene = new Scene(showTicketGrid.asParent());
            setScene(showTicketScene);
            setSize();
        }
    }
    
    private void setSize() {
        setHeight(height);
        setWidth(width);
    }
}
