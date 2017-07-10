package communication;

import centralsystem.CSystem;
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
        while(true){
            try {
                Socket clientSocket = serverSocket.accept();
                connectionList.add(new Skeleton(clientSocket,csystem));
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
