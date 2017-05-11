package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import machines.TicketMachine;
import ticket.TicketType;

/**
 *
 * @author Manuele
 */
public class GUITicketMachine extends Application {
    private Scene chooseLoginScene, chooseTicketScene, moneyScene;
    private static TicketMachine tMachine;
    private static Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        buildChooseLoginScene();
        buildChooseTicketScene();
        buildMoneyScene();
        window.setScene(chooseLoginScene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicketMachine tm = new TicketMachine(1, "ipAddress");
        tMachine = tm;
        launch(args);
    }
    
    private void buildChooseLoginScene() {
        Button login = new Button("Login"), goOn = new Button("Continue without login");
        
        login.setOnAction(e -> {
            
        });
        goOn.setOnAction(e -> {
            window.setScene(chooseTicketScene);
        });
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(login, 0, 0);
        grid.add(goOn, 0, 1);
        
        chooseLoginScene = new Scene(grid);
    }
    
    private void buildChooseTicketScene() {
        Button buy = new Button("Single ticket");
        
        buy.setOnAction(e -> {
            tMachine.setTicketToSell(TicketType.SINGLE);
            window.setScene(moneyScene);
        });
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(buy, 0, 0);
        chooseTicketScene = new Scene(grid);
    }
    
    private void buildMoneyScene() {
        Button oneEuro = new Button("1"), fiftyCents = new Button("0,50"), twentyCents = new Button("0,20");
        
        oneEuro.setOnAction(e -> {
            tMachine.insertMoney(1);
            System.out.println(tMachine.getInsertedMoney());
        });
        fiftyCents.setOnAction(e -> {
            tMachine.insertMoney(0.5f);
            System.out.println(tMachine.getInsertedMoney());
        });
        twentyCents.setOnAction(e -> {
            tMachine.insertMoney(0.2f);
            System.out.println(tMachine.getInsertedMoney());
        });
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(oneEuro, 0, 0);
        grid.add(fiftyCents, 0, 1);
        grid.add(twentyCents, 0, 2);
        
        moneyScene = new Scene(grid);
    }
}
