/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zubeer
 */
public class SocketHandler extends Thread {
     ServerSocket socketListener = null;
     Socket newSocket  = null;
     ArrayList<Skeleton> connectionList;
     
    public SocketHandler (ServerSocket socketListener) {
       this.socketListener = socketListener;
       connectionList = new ArrayList<Skeleton>();
    }

    @Override
     public void run() {
        while(true){
            try {
                newSocket = socketListener.accept();
                connectionList.add(new Skeleton(newSocket));
                connectionList.get(connectionList.size()-1).start();
                removeDeadThread();          
            } catch (IOException ex) {
                System.err.println("Socket handler error");
            }
            
        }
    }

    private void removeDeadThread() {
        ArrayList<Skeleton> toRemove = new ArrayList<>();
        for (Skeleton stubTicket : connectionList) {
                    if(!stubTicket.isAlive()) toRemove.add(stubTicket);
        }
         connectionList.removeAll(toRemove);
    }
     
    
}
