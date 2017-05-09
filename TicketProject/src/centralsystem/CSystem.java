package centralsystem;

import TicketCollector.Fine;
import databaseadapter.DatabaseAdapter;
import databaseadapter.*;
import java.io.IOException;
import java.net.ServerSocket;

public class CSystem implements CentralSystemCollectorInterface {

    private final int PORTA_SERVER = 5000;
    private final int NUMERO_MACCHINETTE = 1;

    private final DatabaseAdapter database;
    private ServerSocket socketListener;
    private SocketHandler scHandler;

    public CSystem() {
        this.database = new DatabaseAdapter();
        initTickets();
        initUsers();
        initServer();
    }

    private void initTickets() {
        for (int i = 0; i < 10; i++) {
            database.addTicket(new TicketDB(TicketType.SINGLE));
        }

        for (int i = 0; i < 10; i++) {
            database.addTicket(new TicketDB(TicketType.SEASON));
        }
    }

    private void initUsers() {
        for (int i = 0; i < 10; i++) {
            database.addUser(i + "", i + "", i + "",i + "");
        }
    }

    public boolean checkUser(String cf) {
        return database.checkUser(cf);
    }

    public boolean addUser(String name, String surname, String cf,String psw) {
        return database.addUser(name, surname, cf, psw);
    }

    /*
    public boolean addUser(User user) {
        return database.addUser(user.getName, user.getSurname, user.getCf);
    
    }
     */
    public boolean addFine(Fine fine) {
        return database.addFine(fine);
    }

    public boolean existsTicket(String ticketCode) {
        return database.existsTicket(ticketCode);
    }

    public boolean isValid(String ticketCode) {
        return database.isValid(ticketCode);
    }

    public void printTickets() {
        database.printTickets();
    }

    public void printUsers() {
        database.printUsers();
    }

    private void initServer() {
        try {
            socketListener = new ServerSocket(PORTA_SERVER);
        } catch (IOException ex) {
            System.err.println("Errore apertura porta serverSocket");
        }

        scHandler = new SocketHandler(socketListener, this);
        scHandler.start();
    }

    @Override
    public void makeFine(Fine f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(String username, String psw) {
        return database.login(username, psw);
    }

}
