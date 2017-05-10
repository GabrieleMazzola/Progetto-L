package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import machines.TicketMachine;
import paymentMethods.PaymentMethod;
import ticket.SingleTicket;
import ticket.TicketType;

/**
 *
 * @author Manuele
 */
public class TicketMachineScene {
    private Button fiftyCents, oneEuro, twoEuro, fiveEuro, tenEuro, twentyEuro,
                   buyTicket, renewSeason, login;
    private TicketMachine machine;
    private GridPane grid, moneyGrid, buttonsGrid;
    
    public TicketMachineScene(TicketMachine machine) {
        this.machine = machine;
        
        //Istanzio la griglia principale (grid)
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        
        //Istanzio gli oggetti
        fiftyCents = new Button("0,50€");
        oneEuro = new Button("1,00€");
        twoEuro = new Button("2,00€");
        fiveEuro = new Button("5,00€");
        tenEuro = new Button("10,00€");
        twentyEuro = new Button("20,00€");
        
        //Aggiungo le azioni dei bottoni chiamando il metodo insertMoney della ticketMachine
        fiftyCents.setOnAction(e -> {machine.insertMoney(0.50);});
        oneEuro.setOnAction(e-> {machine.insertMoney(1);});
        twoEuro.setOnAction(e-> {machine.insertMoney(2);});
        fiveEuro.setOnAction(e-> {machine.insertMoney(5);});
        tenEuro.setOnAction(e-> {machine.insertMoney(10);});
        twentyEuro.setOnAction(e-> {machine.insertMoney(20);});
        
        //Aggiungo i bottoni alla griglia dei bottoni
        moneyGrid = new GridPane();
        moneyGrid.setAlignment(Pos.CENTER);
        moneyGrid.add(fiftyCents,1,1);
        moneyGrid.add(oneEuro,1,2);
        moneyGrid.add(twoEuro,1,3);
        moneyGrid.add(fiveEuro,2,1);
        moneyGrid.add(tenEuro,2,2);
        moneyGrid.add(twentyEuro,2,3);
        
        //Aggiungo la griglia dei bottoni alla griglia principale
        grid.add(moneyGrid, 0, 1);
        
        //Istanzio i bottoni di funzionalità
        buyTicket = new Button("Buy single ticket");
        renewSeason = new Button("Renew season ticket");
        login = new Button("Login");
        
        //Aggiungo le azioni dei bottoni
        buyTicket.setOnAction(e -> {
            machine.buyTicket(TicketType.SINGLE, PaymentMethod.CASH);
        });
    }
}
