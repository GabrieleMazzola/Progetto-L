package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class GUITicketMachine extends Application {
    private Scene chooseLoginScene, chooseTicketScene, moneyScene;
    private static TicketMachine tMachine;
    private Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        ChooseLoginGrid loginGrid = new ChooseLoginGrid();
        PushbuttonMachineGrid moneyGrid = new PushbuttonMachineGrid(tMachine);
        
        Button login = new Button("Login");
        login.setOnAction(e -> {System.out.println("You currently cannot log in");});
        Button goOn = new Button("Continue");
        goOn.setOnAction(e -> {window.setScene(moneyScene);});
        Button homePage = new Button("Homepage");
        homePage.setOnAction(e -> {window.setScene(chooseLoginScene);});
        homePage.setMinWidth(130);
        homePage.setMaxWidth(130);
        
        loginGrid.add(login, 0, 1);
        loginGrid.add(goOn, 1, 1);
        moneyGrid.add(homePage, 4, 2, 2, 1);
        
        chooseLoginScene = new Scene(loginGrid.asParent());
        moneyScene = new Scene(moneyGrid.asParent());
        
        //moneyScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        
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
    
//    private void buildChooseLoginScene() {
//        Button login = new Button("Login"), goOn = new Button("Continue without login");
//        
//        login.setOnAction(e -> {
//            
//        });
//        goOn.setOnAction(e -> {
//            window.setScene(chooseTicketScene);
//        });
//        
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//        grid.add(login, 0, 0);
//        grid.add(goOn, 0, 1);
//        
//        chooseLoginScene = new Scene(grid);
//    }
//    
//    private void buildChooseTicketScene() {
//        Button buy = new Button("Single ticket");
//        
//        buy.setOnAction(e -> {
//            tMachine.setTicketToSell(TicketType.SINGLE);
//            window.setScene(moneyScene);
//        });
//        
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//        grid.add(buy, 0, 0);
//        chooseTicketScene = new Scene(grid);
//    }
//    
//    private void buildMoneyScene() {
//        Button  oneHundred = new Button("100"),
//                fifty = new Button("50"),
//                twenty = new Button("20"),
//                ten = new Button("10"),
//                five = new Button("5"),
//                two = new Button("2"),
//                one = new Button("1"),
//                fiftyCents = new Button("0,50"),
//                twentyCents = new Button("0,20"),
//                tenCents = new Button("0,10"),
//                fiveCents = new Button("0,05"),
//                twoCents = new Button("0,02"),
//                oneCent = new Button("0,01");
//        
//        double size = fiveCents.getScaleX();
//        oneHundred.setMinWidth(size);
//        fifty.setMinWidth(size);
//        twenty.setMinWidth(size);
//        ten.setMinWidth(size);
//        five.setMinWidth(size);
//        two.setMinWidth(size);
//        one.setMinWidth(size);
//        fiftyCents.setMinWidth(size);
//        twentyCents.setMinWidth(size);
//        tenCents.setMinWidth(size);
//        fiveCents.setMinWidth(size);
//        twoCents.setMinWidth(size);
//        oneCent.setMinWidth(size);
//        
//        oneHundred.setOnAction(e -> {tMachine.insertMoney(100);});
//        fifty.setOnAction(e -> {tMachine.insertMoney(50);});
//        twenty.setOnAction(e -> {tMachine.insertMoney(20);});
//        ten.setOnAction(e -> {tMachine.insertMoney(10);});
//        five.setOnAction(e -> {tMachine.insertMoney(5);});
//        two.setOnAction(e -> {tMachine.insertMoney(2);});
//        one.setOnAction(e -> {tMachine.insertMoney(1);});
//        fiftyCents.setOnAction(e -> {tMachine.insertMoney(0.5f);});
//        twentyCents.setOnAction(e -> { tMachine.insertMoney(0.2f);});
//        tenCents.setOnAction(e -> { tMachine.insertMoney(0.1f);});
//        fiveCents.setOnAction(e -> { tMachine.insertMoney(0.05f);});
//        twoCents.setOnAction(e -> { tMachine.insertMoney(0.02f);});
//        oneCent.setOnAction(e -> { tMachine.insertMoney(0.01f);});
//        
//        
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setVgap(10);
//        grid.setHgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//        
//        grid.add(oneHundred, 0, 0);
//        grid.add(fifty, 1, 0);
//        grid.add(twenty, 2, 0);
//        grid.add(ten, 3, 0);
//        grid.add(five, 0, 1);
//        grid.add(two, 1, 1);
//        grid.add(one, 2, 1);
//        grid.add(fiftyCents, 3, 1);
//        grid.add(twentyCents, 0, 2);
//        grid.add(tenCents, 1, 2);
//        grid.add(fiveCents, 2, 2);
//        grid.add(twoCents, 3, 2);
//        grid.add(oneCent, 0, 3);
//        
//        moneyScene = new Scene(grid);
//    }
}
