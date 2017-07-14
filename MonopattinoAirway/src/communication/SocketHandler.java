package communication;

import centralsystem.CSystem;
import console.LogCS;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*Classe che gestisce la comunicazione fra CentralSystem e client. Quando viene ricevuta una richiesta
 *dal client, viene aggiunto un nuovo thread Skeleton alla lista connectionList, e gli vengono passati
 *il riferimento al CentralSystem e il socket del client. Viene poi fatto partire il thread Skeleton.
 *Viene fatto un controllo infine dei thread inattivi, e viene pulita la lista connectionList.
*/
public class SocketHandler extends Thread{
    
    private CSystem csystem;
    private List<Skeleton> connectionList;
    private ServerSocket serverSocket;
    
    /**
     * Viene passata la porta di comunicazione e il riferimento al CentralSystem
     * viene inoltre istanziata la lista delle connessioni.
     * @param PORT
     * @param csystem
     * @throws IOException 
     */
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
                LogCS.getInstance().print("err", "\n\n---------------------");
                LogCS.getInstance().print("err", "Ricevuta richiesta di connessione, ip: "+clientSocket.getInetAddress());
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
