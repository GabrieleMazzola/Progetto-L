package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Manuele
 */
public class HomePage extends Application {
    private User user;
    private Button login, showUser, buy;
    private Text greetings;
    private GridPane grid;
    
    @Override
    public void start(Stage primaryStage) {
        
        login = new Button("Login");
        login.setOnAction(e -> {
            
            user = Login.signIn();
            greetings = new Text("Welcome, " + user.getUsername());
            grid.add(greetings, 0, 2);
//            if(user != null) buildShopScene(primaryStage);
        });
        showUser = new Button("Show user");
        showUser.setOnAction(e -> {
            
            if (user != null) System.out.println("Utente: " + user.getUsername());
            else System.out.println("Non hai fatto il login");
        });
        buy = new Button("Buy");
        buy.setOnAction(e -> {
            
            System.out.println("Work in progress. Please stand by the yellow line");
        });
        
        VBox boxBtns = new VBox();
        boxBtns.setSpacing(15);
        boxBtns.getChildren().addAll(login, showUser, buy);
        
        grid = new GridPane();
        grid.getChildren().add(boxBtns);
        
        Scene s = new Scene(grid, 400, 400);
        primaryStage.setScene(s);
        primaryStage.show();
    }
    
    private void buildShopScene(Stage stage) {
        
        Scene shop = new Scene(new ShopScene().asParent(), 400, 400);
        stage.setScene(shop);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
