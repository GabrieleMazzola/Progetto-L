package gui;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class GUITicketMachine extends Application implements Observer{
    private Scene chooseLoginScene, chooseTicketScene, moneyScene, loginScene;
    private static TicketMachine tMachine;
    private Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        tMachine.addObserver(this);
        ChooseLoginGrid chooseLoginGrid = new ChooseLoginGrid();
        PushbuttonMachineGrid moneyGrid = new PushbuttonMachineGrid(tMachine);
        LoginGrid loginGrid = new LoginGrid(tMachine);
        
        //Bottone Login. Quando viene premuto l'utente viene portato alla pagina
        //in cui si chiede di inserire i propri dati per effettura il login
        Button login = new Button("Login");
        login.setOnAction(e -> {
            window.setScene(loginScene);
        });
        //Bottone Continua. Quando viene premuto l'utente viene portato alla pagina
        //di acquisto del biglietto senza effettuare login
        Button goOn = new Button("Continue");
        goOn.setOnAction(e -> {
            window.setScene(moneyScene);
        });
        //Bottone HomePage. Tramite questo bottone l'utente è in grado di ritornare
        //alla schermata iniziale
        Button homePage = new Button("Homepage");
        homePage.setOnAction(e -> {
            window.setScene(chooseLoginScene);
        });
        homePage.setMinWidth(130);
        homePage.setMaxWidth(130);
        
        chooseLoginGrid.add(login, 0, 1);
        chooseLoginGrid.add(goOn, 1, 1);
        moneyGrid.add(homePage, 4, 2, 2, 1);
        
        chooseLoginScene = new Scene(chooseLoginGrid.asParent());
        moneyScene = new Scene(moneyGrid.asParent());
        loginScene = new Scene(loginGrid.asParent());
        
        //moneyScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        
        window.setScene(chooseLoginScene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicketMachine tm = new TicketMachine(5000, "192.168.1.7");
        tMachine = tm;
        launch(args);
    }
    
    public void setTicketMachine(TicketMachine tMachine) {
        this.tMachine = tMachine;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String)
            window.setScene(moneyScene);
    }
}
