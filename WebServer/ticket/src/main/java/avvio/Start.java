package avvio;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import machineonline.TicketOnline;



public class Start implements ServletContextListener {

	
	public static final String CSYSTEM_IP = "10.87.156.248";
	public static final int CSYSTEM_PORT = 5000;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		TicketOnline ticket = TicketOnline.getInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}