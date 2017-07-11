package org.gabriele.progettol.ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import avvio.Start;

public class ConnectionHandler {


	private Socket server;
	private PrintWriter toServer;
	private BufferedReader fromServer;

	
	public ConnectionHandler() {
		super();
		try {
			server = new Socket(Start.CSYSTEM_IP,Start.CSYSTEM_PORT);
			toServer = new PrintWriter(server.getOutputStream(),true);
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String sendAndReceive(String packet) throws IOException{
		toServer.println(packet);
		return fromServer.readLine();
	}


	public String getIP() {
		return server.getLocalAddress().getHostAddress();
	}

	public void closeConnection() throws IOException{
		toServer.close();
		fromServer.close();
		server.close();
	}
	
}
