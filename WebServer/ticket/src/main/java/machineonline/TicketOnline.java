package machineonline;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import avvio.Start;
import centralsystem.Stub;
import items.Product;
import items.Sale;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketOnline{
	
    private TicketMachineCodeHandler codesHandler;
    private StubOnline stub;
    
    private Map<String,Product> products;
    
    private static TicketOnline instance;    
    
    //LA TICKETONLINE è UN SINGLETON, SI VUOLE UNA SOLA ISTANZA
    
    
    private TicketOnline() {
    	System.err.println("\n----------\nAVVIAMENTO MACHINEONLINE..");
    	System.err.println("Central System IP: "+Start.CSYSTEM_IP +", PORT: "+Start.CSYSTEM_PORT);
    	try {
			this.stub = new StubOnline(Start.CSYSTEM_IP, Start.CSYSTEM_PORT, this);			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERRORE AVVIAMENTO MACHINEONLINE");
			stub = null;
		}
    	
    	System.err.println("----------\n\n");
    	
    	System.out.println("Requesting product types..");
        this.products = stub.getProductList();
        System.out.println("Products initialized :\n" + printProducts());
    	
        this.codesHandler = new TicketMachineCodeHandler(this);
    }
    
    private String printProducts(){
    	StringBuilder sb = new StringBuilder();
    	
    	for(Product prod : products.values()){
    		sb.append("[").append(prod).append("]\n");
    	}
    	
    	return sb.toString();
    }
    
    
	public static synchronized TicketOnline getInstance(){
    	if(instance == null){
    		instance = new TicketOnline();
    	}
    	return instance;
    }
       
    
    public Map<String,Product> getProducts() {
		return products;
	}

    
    public boolean creditCardPayment(String cCardNumber, double amount) {
    	controlCodes();
    	return stub.cardPayment(cCardNumber, amount);
    }

    
    private void controlCodes() {
    	if(codesHandler.mustRequestCodes())
            codesHandler.startUpdateSerial();
	}



	public void printSerials(){
    	System.out.println(codesHandler.getSerialListLenght() + " seriali rimasti: " + codesHandler.getSerialList());
    }


    public void requestCodes(int numberOfCodes) {
        stub.requestCodes(numberOfCodes);
    }
    
	public Product getProduct(String ticketType) {
		if(products.containsKey(ticketType)){
			return products.get(ticketType);
		}
		return null;
	}

	public void addTicketSerials(List<Long> newSerials) {
        codesHandler.endUpdateSerial(newSerials);
	}



	public Long useOneSerial() {
	    return this.codesHandler.popSerialNumber();
	}
	
        public boolean makeSale(String username, String creditCardNumber, String ticketType){
            Product product = this.getProduct(ticketType);
            if( product != null ){
                    if(this.creditCardPayment(creditCardNumber, product.getCost())){
                        try {
                            return Stub.getInstance().addSale(new Sale(Calendar.getInstance().getTime(), TicketOnline.getInstance().useOneSerial(), username, product, InetAddress.getLocalHost().getHostAddress()));
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(TicketOnline.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            return false;
        }
	

      
}
