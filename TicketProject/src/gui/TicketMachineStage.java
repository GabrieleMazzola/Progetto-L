package gui;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class TicketMachineStage extends Stage implements Observer{
    private Scene chooseLoginScene, chooseTicketScene, moneyScene, loginScene;
    private TicketMachine tMachine;
    
    public TicketMachineStage(TicketMachine tMachine) {
        this.tMachine = tMachine;
        tMachine.addObserver(this);
        
        ChooseLoginGrid chooseLoginGrid = new ChooseLoginGrid();
        PushbuttonMachineGrid moneyGrid = new PushbuttonMachineGrid(tMachine);
        LoginGrid loginGrid = new LoginGrid(tMachine);
        
        //Bottone Login. Quando viene premuto l'utente viene portato alla pagina
        //in cui si chiede di inserire i propri dati per effettura il login
        Button login = new Button("Login");
        login.setOnAction(e -> {
            this.setScene(loginScene);
        });
        //Bottone Continua. Quando viene premuto l'utente viene portato alla pagina
        //di acquisto del biglietto senza effettuare login
        Button goOn = new Button("Continue");
        goOn.setOnAction(e -> {
            this.setScene(moneyScene);
        });
        //Bottone HomePage. Tramite questo bottone l'utente è in grado di ritornare
        //alla schermata iniziale
        Button homePage = new Button("Homepage");
        homePage.setOnAction(e -> {
            this.setScene(chooseLoginScene);
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
        
        this.setScene(chooseLoginScene);
        this.show();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String)
            this.setScene(moneyScene);
    }
}
