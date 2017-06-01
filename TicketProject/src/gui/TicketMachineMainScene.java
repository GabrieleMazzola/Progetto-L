package gui;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import machines.Operation;
import machines.TicketMachine;
import ticket.SingleType;

/**
 *
 * @author Manuele
 */
public class TicketMachineMainScene extends BridgeSceneGrid implements Observer{
    private Label greetings, loggedAs;
    private Button singleTicket, multiTicket, refreshSeason, login, signUp, logout;
    private HBox boxBtns;
    private GridPane upperGrid, ticketGrid;
    
    public TicketMachineMainScene(TicketMachine tMachine) {
        tMachine.addObserver(this);
        
        //Istanzio gli oggetti nella upper grid
        upperGrid = new GridPane();
        upperGrid.setHgap(15);
        
        greetings = new Label("Hello!");
        greetings.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 30));
        login = new Button("Login");
        login.setOnAction(e -> {
            tMachine.setOperation(Operation.LOGGING_IN);
        });
        //Bottone SignUp. Quando viene premuto si chiede all'utente di effettuare
        //una nuova registrazione
        signUp = new Button("Sign up");
        signUp.setOnAction(e -> {
            //TODO
        });
        boxBtns = new HBox();
        boxBtns.setSpacing(10);
        boxBtns.getChildren().addAll(signUp, login);
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.HORIZONTAL);
        
        upperGrid.add(greetings, 0, 1, 2, 1);
        upperGrid.add(hSeparator, 0, 2, 7, 1);
        upperGrid.add(boxBtns, 5, 0);
//        upperGrid.add(signUp, 6, 0);
        
        //Istanzio gli oggetti nella ticket grid
        ticketGrid = new GridPane();
        ticketGrid.setHgap(15);
        ticketGrid.setVgap(15);
        
        singleTicket = new Button("Single Ticket");
        singleTicket.setOnAction(e -> {
            tMachine.setTicketToSell(new SingleType());
            tMachine.setOperation(Operation.SELECTING_PAYMENT);
        });
        multiTicket = new Button("Multi Ticket");
        multiTicket.setOnAction(e -> {
            //tMachine.setTicketToSell(TicketType.SINGLE);
        });
        refreshSeason = new Button("Refresh Season Ticket");
        refreshSeason.setOnAction(e -> {
            //tMachine.setTicketToSell(TicketType.SINGLE);
        });
        
        ticketGrid.add(singleTicket, 0, 0);
        ticketGrid.add(multiTicket, 0, 1);
        ticketGrid.add(refreshSeason, 1, 0);
        
        istantiateGrid();
        grid.add(upperGrid, 0, 0);
        grid.add(ticketGrid, 0, 1);
        
        logout = new Button("Logout");
        logout.setOnAction(e -> {
            tMachine.logout();
        });
    }
    
    public void addToUpperGrid(Node node, int row, int column) {
        upperGrid.add(node, column, row);
    }
    
    public void addToTicketGrid(Node node, int row, int column) {
        ticketGrid.add(node, column, row);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            String username = (String) arg;
            if(username.equals("-")) {
                System.out.println("Fatto logout");
                boxBtns.getChildren().remove(1);
                boxBtns.getChildren().add(login);
                upperGrid.getChildren().remove(loggedAs);
            }
            else {
                loggedAs = new Label("Welcome, " + username);
                upperGrid.add(loggedAs, 5, 1);
                boxBtns.getChildren().remove(1);
                boxBtns.getChildren().add(logout);
            }
        }
    }
}
