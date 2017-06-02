package machines;

import java.util.List;
import java.util.Observable;
import ticket.Ticket;
import ticket.TicketType;

/**
 *
 * @author Andrea
 */
public class TicketMachine extends Observable{
    private int cod;
    private ResourcesHandler resources;
    private MoneyHandler moneyTank;
    private TicketMachineCodeHandler codesHandler;
    private StubMachine stub;
    private UpdateHandler updateHandler;
    private String logged;
    private Operation operation;
    
    private double insertedMoney;
    private double cost;
    private TicketType type;
    
    public TicketMachine(int cod, int PORTA_SERVER, String ipAdress) {
        this.cod =cod;
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        this.stub = new StubMachine(ipAdress, PORTA_SERVER, this);
        this.codesHandler = new TicketMachineCodeHandler(stub);
        this.updateHandler = new UpdateHandler(this, stub);
        this.updateHandler.start();
        
        operation = Operation.SELLING_TICKET;
    }
    
    //__________________Metodi getter___________________________________________
    public int getCod() {
        return cod;
    }
    
    public double getInk() {
        return resources.getInkPercentage();
    }
    
    public double getPaper() {
        return resources.getPaperPercentage();
    }
    
    public boolean canPrint() {
        return resources.hasEnoughResources();
    }
    
    public double getInsertedMoney() {
        return moneyTank.getInsertedMoney();
    }
    
    public String getLoggedUsername() {
        return logged;
    }
    
    public double getSelectedTicketCost() {
        return cost;
    }
    
    public float getMoneyInTank() {
        return moneyTank.getTotal();
    }
    
    public boolean isActive() {
        return getPaper() > 0 && getInk() > 0;
    }
    
    public int getAmountOf(double value) {
        return moneyTank.getQuantityOf(value);
    }
    
    public int getAmountByIndex(int index) {
        return moneyTank.getSingleQuantitybyIndex(index);
    }
    
    //__________________Metodi per la vendita di biglietti______________________
    /**
     * Setta il tipo di biglietto da vendere. In tal modo la macchinetta sa
     * quanto bisogna che l'utente paghi. Se la macchinetta non può stampare viene
     * mandata una notifica alla GUI
     * @param type
     */
    public void setTicketToSell(TicketType type) {
        //Se può stampare vengono settati il tipo di biglietto da vendere e il costo
        if(canPrint()) {
            this.type = type;
            this.cost = type.getCost();
        }
        //Altrimenti viene mandata una notifica alla GUI
        else {
            notifyChange(canPrint());
        }
    }
    
    /**
     * Effettua il pagamento tramite carta di credito. La carta di credito viene
     * passata come argomento della funzione. Se il pagamento va a buon fine il
     * biglietto viene stampato tramite printTicket()
     * @param cCardNumber
     * @return Vero se il pagamento va a buon fine, falso altrimenti
     */
    //TODO: Stato Pagamento
    public boolean buyTicketCreditCard(String cCardNumber) {
        if(checkCreditCard(cCardNumber)) {
            System.out.println("Pagamento effettuato. Stampa biglietto");
            printTicket();
            return true;
        }
        else {
            System.out.println("Pagamento non effettuato");
            return false;
        }
    }
    
    /**
     * Consente di inserire una moneta/banconota del valore specificato. Nel caso 
     * in cui i soldi inseriti siano sufficienti per comprare il biglietto selezionato
     * viene automaticamente effettuata la vendita restituendo eventualmente il resto.
     * @param money
     */
    public void insertMoney(double money) {
        addInsertedMoney(money);
        if (insertedEnoughMoney()) {
            printTicket();
            outputChange();
        }
    }
    
    /**
     * Aggiunge la quantità indicata al money handler, tenendo traccia di tutte
     * quelle inserite precedentemente dall'inizio della vendita
     * @param money 
     */
    private void addInsertedMoney(double money) {
        moneyTank.addMoney(money);
        notifyChange(money);
        notifyChange(moneyTank.addToInsertedMoney(money));
    }
    
    private boolean insertedEnoughMoney() {
        return moneyTank.getInsertedMoney() >= cost;
    }
    
    /**
     * Stampa il biglietto. La stampa viene effettuata mandando una notifica alla
     * GUI
     */
    private void printTicket() {
        if(codesHandler.mustRequestCodes())
            codesHandler.startUpdateSerial();
        resources.printTicket();
        Ticket ticket = createTicket();
        endSelling();
        setOperation(Operation.PRINTING_TICKET);
        notifyChange(isActive());
        notifyChange(ticket);
    }
    
    /**
     * Resetta i parametri della vendita
     */
    private void endSelling() {
        moneyTank.resetInsertedMoney();
        this.cost = 0;
        type = null;
    }
    
    /**
     * Crea un ticket con il primo ticket code disponibile e il tipo specificato
     * prima nella vendita
     * @return ticketcode
     */
    private Ticket createTicket(){
        Ticket ticket = new Ticket(codesHandler.popSerialNumber()+"", type);
        return ticket;
    }

    private void outputChange() {
        moneyTank.giveChange(cost, insertedMoney);
    }
    
    private boolean checkCreditCard(String credCardNumber) {
        return stub.cardPayment(credCardNumber, cost);
    }
    
    //__________________Metodi per la gestione dei codici_______________________
    /**
     * Aggiunge alla lista dei ticket code disponibili quelli specificati
     * @param newSerials 
     */
    public void addTicketSerials(List<Long> newSerials) {
        codesHandler.endUpdateSerial(newSerials);
    }
    
    /**
     * Setta l'operazione che sta facendo la macchinetta. Serve per mandare una
     * notifica alla GUI in modo da poter passare da una scena all'altra
     * @param operation 
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
        notifyChange(operation);
    }
    
    /**
     * Effettua il login con i dati specificati
     * @param username
     * @param password
     * @return Vero se il login ha successo, falso altrimenti
     */
    public boolean login(String username, String password) {
        if(stub.userLogin(username, password)) {
            operation = Operation.SELLING_TICKET;
            notifyChange(operation);
            logged = username;
            notifyChange(logged);
            return true;
        }
        else 
            return false;
    }
    
    /**
     * Effettua il logout settando a "-" il nome dell'utente loggato
     * @return Vero
     */
    public boolean logout() {
        logged = "-";
        notifyChange(logged);
        return true;
    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
