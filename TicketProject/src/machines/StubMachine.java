/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;
import centralsystem.CentralSystemTicketInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andrea
 */
public class StubMachine implements CentralSystemTicketInterface{
    
    String ipAdress;
    int port;
    
    Socket s;
    BufferedReader bufferIn;
    PrintWriter bw;

    public StubMachine(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
        try {
            s= new Socket(ipAdress,port);
        } catch (IOException ex) {
            Logger.getLogger(StubMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initConnection(){
       // input reader e writer per connessione a server
    }

    @Override
    public boolean login(String username, String psw) {
       loginJSONPacket();
    }

    @Override
    public String requestCodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
      
/*
    @Override
    public boolean login(String username, String psw) {
        return false;
    }

    @Override
    public String requestCodes() {
        return null;
    }
*/ 
    

