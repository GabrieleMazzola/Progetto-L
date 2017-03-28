package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Manuele
 */
public class Login {
    private static User user;
    private static GridPane grid;
    private static Text text, username, password, 
                        noCredentials = new Text("Please enter your credentials"),
                        fail = new Text("Wrong credentials");
    private static TextField textUser;
    private static PasswordField textPassword;
    private static Button signIn, cancel;
    private static HBox boxBtns, boxError;
    
    public static User signIn() {
        Stage window = new Stage();
        
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        text = new Text("Please log in to your account");
        text.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        grid.add(text, 0, 0, 2, 1);
        
        username = new Text("Username: ");
        password = new Text("Password: ");
        
        grid.add(username, 0, 3);
        grid.add(password, 0, 4);
        
        textUser = new TextField();
        textPassword = new PasswordField();
        textPassword.setOnAction(e -> signIn.fire());
        
        grid.add(textUser, 1, 3);
        grid.add(textPassword, 1, 4);
        
        signIn = new Button("Sign in");
        signIn.setOnAction(e -> {
            
            String name = textUser.getText();
            String psw = textPassword.getText();
            
            if (emptyFields()) {
                
                boxError.getChildren().clear();
                noCredentials.setFill(Color.RED);
                noCredentials.setFont(Font.font("Tahoma", FontWeight.BLACK, 12));
                boxError.getChildren().add(noCredentials);
            }
            else if(!connect(name, psw)) {
                
                boxError.getChildren().clear();
                fail.setFill(Color.RED);
                fail.setFont(Font.font("Tahoma", FontWeight.BLACK, 12));
                boxError.getChildren().add(fail);
            }
            else window.close();
        });
        cancel = new Button("Cancel");
        cancel.setOnAction(e -> window.close());
        
        boxBtns = new HBox();
        boxBtns.getChildren().addAll(cancel, signIn);
        boxBtns.setAlignment(Pos.BOTTOM_RIGHT);
        boxBtns.setSpacing(5);
        grid.add(boxBtns, 1, 5);
        
        boxError = new HBox();
        boxError.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(boxError, 1, 6);
        
        window.setScene(new Scene(grid, 500, 200));
        window.showAndWait();
        
        return user;
    }
    
    private static boolean connect(String username, String password) {
        String hostname = "localhost";
        String port = "3306";   //DEFAULT
        String databaseName = "prova";//Z08532_TANK_semi2
        boolean exists;
        
        String databaseURL = "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;

        try {
            Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
            exists = fetchData(con,username,password);
            con.close();
            
            //qui vorrei cambiare il mainstage e far apparire la pagina di acquisto utente 
            //con i dati della persona
//            GridPane shopPage = (GridPane)new ShopPage(utente).asParent();
//            
//            Scene shopScene = new Scene(shopPage, 400, 400);
//            mainWindow.setScene(shopScene);
//            
//            mainWindow.setTitle("Benvenuto, " + utente.getUsername());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("CONNESSIONE DATABASE FALLITA.");
            return false;
        }
        
        return exists;
    }
    
    private static boolean fetchData(Connection con, String username, String password) throws SQLException{
        ResultSet ris = con.createStatement().executeQuery("SELECT * FROM utenti where (USERNAME = '"+username+"' and PSW = '"+password+"')");
        
        while(ris.next()) {
            
            String name = ris.getString("NOME");
            String surname = ris.getString("COGNOME");
            String cf = ris.getString("CF");
            String usrname = ris.getString("USERNAME");
            String psw = ris.getString("PSW");
            user = new User(name, surname, cf, usrname, psw);
            return true;
        }
        
        return false;
    }
    
    private static boolean emptyFields() {
        
        String user = textUser.getText();
        String pass = textPassword.getText();
        
        return user.isEmpty() | pass.isEmpty();
    }
}
