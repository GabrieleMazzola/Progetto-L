package centralsystem;

import databaseadapter.DatabaseAdapter;
import databaseadapter.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import machines.MachineStatus;
import machines.TicketMachine;
import ticketCollector.Fine;

public class CSystem implements CentralSystemCollectorInterface {

    private final int PORTA_SERVER = 5000;
    HashMap<Integer,MachineStatus> machineList;
    private final DatabaseAdapter database;
    private ServerSocket socketListener;
    private SocketHandler scHandler;

    public CSystem() {
        this.database = new DatabaseAdapter();
        machineList = new HashMap();
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
        database.addUser("Gabriele", "Mazzola", "MZZGRL95B22L872K", "pizza123");
        database.addUser("ciao", "Longhi", "ASCAKJSCAKSBCAKSJBHC", "ciao");
    }

    public boolean checkUser(String cf) {
        return database.checkUser(cf);
    }

    public boolean addUser(String name, String surname, String cf,String psw) {
        return database.addUser(name, surname, cf, psw);
    }
    
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
    public boolean login(String username, String psw) {
        return database.login(username, psw);
    }
    
    public void makeFine(Fine f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean updateMachineStatus(int machineCode, double inkLevel, double paperLevel, boolean active) {
        if(machineList.containsKey(machineCode)){
            ((MachineStatus)machineList.get(machineCode)).setActive(active);            
            ((MachineStatus)machineList.get(machineCode)).setPaperLevel(paperLevel);
            ((MachineStatus)machineList.get(machineCode)).setInkLevel(inkLevel);
        }else{
            machineList.put(machineCode, new MachineStatus(machineCode,inkLevel,paperLevel,active));
        }
        return true;
    }
    

}
