package communication;

import centralsystem.CSystem;
import console.LogCS;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketHandler extends Thread{
    
    private CSystem csystem;
    private List<Skeleton> connectionList;
    private ServerSocket serverSocket;
    
    public SocketHandler(int PORT, CSystem csystem) throws IOException{
        this.csystem = csystem;
        connectionList = new ArrayList<>();
        serverSocket = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        LogCS.getInstance().print("err", "\n\n---------------------");
        LogCS.getInstance().print("err", "\nSocketHandler avviata, in attesa di connessioni..");
        while(true){
            try {
                Socket clientSocket = serverSocket.accept();
                LogCS.getInstance().print("out", "\n\n---------------------");
                LogCS.getInstance().print("out", "Ricevuta richiesta di connessione, ip: "+clientSocket.getInetAddress());
                connectionList.add(new Skeleton(clientSocket,csystem));
                LogCS.getInstance().print("err", "Avviato Skeleton relativo. Numero di skeleton attivi: "+ connectionList.size());
                connectionList.get(connectionList.size()-1).start();
                removeDeadThread();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void removeDeadThread() {
        List<Skeleton> toRemove = new ArrayList<>();
        for(Skeleton skeleton : connectionList){
            if(!skeleton.isAlive())
                toRemove.add(skeleton);
        }
        connectionList.removeAll(toRemove);
    }
    
}
