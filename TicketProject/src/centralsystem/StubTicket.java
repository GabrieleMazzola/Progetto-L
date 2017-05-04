/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Boolean.TRUE;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zubeer
 */
public class StubTicket extends Thread{
    Socket clientSocket = null;
    BufferedReader in = null;
    PrintWriter out = null;
    
    public StubTicket(Socket clientSocket) {
       this.clientSocket = clientSocket;

    }

    @Override
    public void run() {
         try {
            in  =   new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));            
            out =   new PrintWriter(clientSocket.getOutputStream(),true);
            
            out.println("Connesso");
            decodeRead(in.readLine());
            //Esegui il metodo richiesto e rispondi
            out.println("end");
            
            in.close();
            out.close();
            
            
        } catch (IOException ex) {
            System.err.println("Error: socket opening fail");
        }
    }

    private void decodeRead(String readLine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
