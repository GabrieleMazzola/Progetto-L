package avvio;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import machineOnline.TicketOnline;



public class Start implements ServletContextListener {

	
	public static final String CSYSTEM_IP = "localhost";
	public static final int CSYSTEM_PORT = 5000;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		TicketOnline ticket = TicketOnline.getInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}