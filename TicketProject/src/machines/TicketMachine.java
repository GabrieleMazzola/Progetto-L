package machines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import paymentMethods.PaymentMethod;
import ticket.*;

/**
 *
 * @author Andrea
 */
public class TicketMachine extends Observable{
    private int cod;
    private final int numberOfCodes = 30;
    private ResourcesHandler resources;
    private MoneyHandler moneyTank;
    private StubMachine stub;
    private Map<TicketType,Double> ticketTemplate;
   
    private String logged;
    private Operation operation;
    private String path;
 
    private Timer timer;
    private TimerTask updateMachineTask;
    
    private double insertedMoney;
    private double cost;
    private TicketType type;
    private PaymentMethod pMethod;
    private List<Integer> serialNumber;

    private boolean requestCodesThread;/*per controllare se il thread è ancora vivo*/
    
    public TicketMachine(int PORTA_SERVER, String ipAdress) {
        this.cod =(int)( Math.random()*100);
        this.moneyTank = new MoneyHandler();
        this.resources = new ResourcesHandler();
        ticketTemplate = new HashMap();
        setupTicketTemplate();
        stub = new StubMachine(ipAdress, PORTA_SERVER, this);
        operation = Operation.SELLING_TICKET;
        timer=new Timer();
        initSerialNumber();
        initUpdateMachineTask();
      
        timer.schedule(updateMachineTask,3000,3000);
    }
    
    //__________________Metodi getter___________________________________________
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
        return insertedMoney;
    }
    
    public String getLoggedUsername() {
        return logged;
    }
    
    public double getSelectedTicketCost() {
        return cost;
    }
    
    public PaymentMethod getPaymentMethod() {
        return pMethod;
    }
    
    public float getMoneyInTank() {
        return moneyTank.getTotal();
    }
    
    public boolean hasTicketSelected() {
        return type != null;
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
    
    public String getPath() {
        return path;
    }
        
    //__________________Metodi per la vendita di biglietti______________________
    /**
     * Setta il tipo di biglietto da vendere. In tal modo la macchinetta sa
     * quanto bisogna che l'utente paghi
     * @param type
     */
    public void setTicketToSell(TicketType type) {
        this.type = type;
        setCostForType(type);
        operation = Operation.SELECTING_PAYMENT;
        notifyChange(operation);
        notifyChange(canPrint());
    }
    
    /**
     * Setta il tipo di pagamento scelto per pagare
     * @param pMethod
     */
    public void setPaymentMethod(PaymentMethod pMethod) {
        this.pMethod = pMethod;
        switch (pMethod) {
            case CASH:
                operation = Operation.INSERTING_COINS;
                notifyChange(operation);
                break;
            case CREDITCARD:
                operation = Operation.INSERTING_CCARD;
                notifyChange(operation);
                break;
        }
    }
    
    /**
     * Effettua la vendita del biglietto in base al tipo di biglietto e al metodo
     * di pagamento scelto.
     */
    public boolean buyTicket() {
        switch (pMethod) {
            case CASH:
                insertedMoney = 0;
                break;
            case CREDITCARD:
                return true;
            default:
                return false;
        }
        return true;
    }
    
    /**
     * Consente di inserire una moneta/banconota del valore specificato. Nel caso 
     * in cui i soldi inseriti siano sufficienti per comprare il biglietto selezionato
     * viene automaticamente effettuata la vendita restituendo eventualmente il resto.
     * Se non è stato selezionato alcun biglietto o il metodo di pagamento CASH,
     * le monete inserite non vengono salvate
     * @param money
     */
    public void insertMoney(double money) {
        moneyTank.addMoney(money);
        notifyChange(money);
        insertedMoney += money;
        int temp = (int)Math.floor(insertedMoney*100);
        insertedMoney = (double)temp/(double)100;
        notifyChange(insertedMoney);
        if (isEnoughMoney(insertedMoney)) {
            sellTicket(PaymentMethod.CASH);
        }
    }
    
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
    
    private boolean isEnoughMoney(double money) {
        return money >= cost;
    }

    private void sellTicket(PaymentMethod method) {
        switch (method) {
            case CASH:
                sellTicketCash();
                break;
            case CREDITCARD:
                sellTicketNotCash();
                break;
            default:
                throw new AssertionError(method.name());
        }
        type = null;
        cost = 0;
        insertedMoney = 0;
        notifyChange(insertedMoney);
    }
    
    private void sellTicketCash() {
        printTicket();
        outputChange();
    }

    private void sellTicketNotCash() {   //serve sia per bancomat e per carta di credito
        printTicket();
    }
    
    /**
     * funzione che stampa il Ticket
     */
    private void printTicket() {
        controlRemainedCode();
        System.out.println("numero ticket:"+createTicket());
        resources.printTicket();
        notifyChange(isActive());
        System.out.println("Ticket printed");
        operation = Operation.PRINTING_TICKET;
        notifyChange(operation);
    }
    
    /**
     * funzione che crea il biglietto quando lo si compra 
     * @return ticketcode
     */
    private int createTicket(){
        return this.serialNumber.remove(0);
    }
    
    /**
     * Controlla che che i codici siano sopra un certo valore.
     * Se il numero è sotto, e non ci sono attivi thread per la richiesta, 
     * ne manda una. Se i bilglietti sono zero attende. 
     */
    private void controlRemainedCode(){
        if(serialNumber.size()<=20 && this.requestCodesThread){ //il venti al momento è aliatorio
                this.startUpdateSerial();
            if(serialNumber.size()==0)
                  while(!this.requestCodesThread){}   //entra in ciclo infinito se non ci sono biblietti e attende la ricezione di biglietti
        }    
    }

    private void outputChange() {
        //do exeption
        moneyTank.giveChange(cost, insertedMoney);
    }
    
    private String getCredCardNumber() {
        return "0000000000000000";
        //return "8888 8888 8888 8888";
    }
    
    private boolean checkCreditCard(String credCardNumber) {
        return stub.cardPayment(credCardNumber, cost);
    }
    
    public void cancel() {
        operation = Operation.SELLING_TICKET;
        notifyChange(operation);
    }
    
    //__________________Metodi per la gestione dei codici_______________________

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
    
    public boolean logout() {
        logged = "-";
        notifyChange(logged);
        return true;
    }
    
    private void setupTicketTemplate() {
        ticketTemplate.put(TicketType.SINGLE, 1.50);
        ticketTemplate.put(TicketType.MULTI, 5.70);
    }
    
    private void setCostForType(TicketType type) {
        if(ticketTemplate.containsKey(type)) {
            cost = ticketTemplate.get(type);
        }
        else cost = 0; //to do eccezzione
    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
    
    public void printCoins() {
        moneyTank.printCoinsInTank();
    }

    //__________________Metodi per thread______________________
        /**
     *funzione che imposta un attr. boolean a false se requestCodesThread è in esecuzione
     */
    public void requestCodesThreadisAlive(){
        this.requestCodesThread=false;
    }
    
    /**
     *funzione che imposta un attr. boolean a true se requestCodesThread è morto
     */
    public void requestCodesThreadisDead(){
        this.requestCodesThread=true;
    }
    
    //__________________Metodi per l'inizializazione______________________
    /**
     * Inizializa il vettore che conterrà i codici validi per questa macchinetta.
     * Richiede al CS i primi biglietti e la macchinetta non inizierà a lavorare
     * finche non ha completato la richiesta
    */
    private synchronized void initSerialNumber() {
        try{
            serialNumber = new ArrayList();
            this.startUpdateSerial();
            //System.out.println("Ok");
            while(!requestCodesThread){this.wait(1000);} //metodo da sistemare
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void initUpdateMachineTask() {
        updateMachineTask = new TimerTask () {
            @Override
            public void run () {
                stub.updateMachineStatus(cod, resources.getInkPercentage(), resources.getPaperPercentage(), isActive(), "a");
            };
        };
    }
    
    public void startUpdateSerial(){
        stub.requestCodes(numberOfCodes);
    }
    
    public void endUpdateSerial(ArrayList<Integer> serialNumbers){
        this.serialNumber.addAll(serialNumbers);
    }
    
    public void setOperation(Operation operation) {
        this.operation = operation;
        notifyChange(operation);
    }
    
    /**
     * questo metodo serve solo per fare dei test
     * E' una funzione che ritorna il quantitativo di codici disponibili nella macchinetta
     * @return serialNumber.size()
     */
    public int getSerialNumberSize(){
        return serialNumber.size();
    }
    
    /**
     * stampa i biglietti della macchineatta
     */
    public void printSerialNumber(){
        for(Integer c: serialNumber){
            System.out.println(c);
        }
    }
      
}
