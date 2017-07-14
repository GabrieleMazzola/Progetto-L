package ticketmachine.handlers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zubeer
 */
public class MoneyHandler {
    private double insertedMoney;

    /**
     *
     */
    protected List<Tank> moneyTank;

    /**
     *
     */
    public MoneyHandler() {
        moneyTank  = new ArrayList<>();
        insertedMoney = 0;
        moneyTank.add(new Tank(200));
        moneyTank.add(new Tank(100));
        moneyTank.add(new Tank(50));
        moneyTank.add(new Tank(20));
        moneyTank.add(new Tank(10));
        moneyTank.add(new Tank(5));
        moneyTank.add(new Tank(2));
        moneyTank.add(new Tank(1));
        moneyTank.add(new Tank(0.50));
        moneyTank.add(new Tank(0.20));
        moneyTank.add(new Tank(0.10));
        moneyTank.add(new Tank(0.05));
        moneyTank.add(new Tank(0.02));
        moneyTank.add(new Tank(0.01));
    }
    
    /**
     *
     * @return
     */
    public double getInsertedMoney() {
        return insertedMoney;
    }
   
    /**
     * 
     * @param value
     * @return La quantità di monete/banconote del valore specificato
     * attualmente presenti nel MoneyHandler
     */
    public int getQuantityOf(double value){
        for(Tank tank: moneyTank){
            if(tank.getValue() == value){
                return tank.getQuantity();
            }
        }
        return -1;    //TODO EXCEPTION
    }
    
    /**
     * 
     * @param index
     * @return La quantità di monete/banconote del Tank dell'indice specificato
     */
    public int getQuantitybyIndex(int index){
        return moneyTank.get(index).getQuantity();
    }
    
    /**
     * Setta la quantità di monete/banconote del valore specificato alla 
     * quantità specificata.
     * @param value
     * @param quantity
     * @return 1 se la modifica viene correttamente eseguita, 
     * altrimenti ritorna -1
     */
    public int setQuantityOf(double value,int quantity){
        for(Tank tank: moneyTank){
            if(tank.getValue() == value){
                tank.setQuantity(quantity);
                return 1;     //TODODO mi piaci tu EXCEPTION
            }
        }
        return -1;    //TODO EXCEPTION
    }
    
    /**
     * Aggiunge una moneta/banconota del valore indicato al MoneyHandler.
     * @param value
     * @return 1 se viene correttamente aggiornato il contenuto del MoneyHandler,
     */
    public int addMoney(double value){
        for(Tank tank: moneyTank){
            if(tank.getValue() == value){
                tank.addQuantity(1);
                return 1;     //TODO EXCEPTION
            }
        }
        return -1;    //TODO EXCEPTION
    }
    
    /**
     * Calcola l'ammontare totale di soldi all'interno del MoneyHandler
     * @return L'ammontare totale di soldi all'interno del MoneyHandler
     */
    public double getTotal(){
        double total = 0;
        for(Tank tank: moneyTank){
            total += tank.getQuantity()*tank.getValue();
        }
        return total;
    }
    
    /**
     * Fornisce il resto, calcolato come insertedMoney - cost. Il resto viene
     * sempre fornito con il minor numero di monete possibili, se è possibile
     * fornire il resto
     * @param cost
     * @param insertedMoney
     * @return 0 se il resto viene dato correttamente. Il resto viene fornito con
     * il minor numero di monete possibili
     */
    public double giveChange(double cost){
        int stillToGive = (int)Math.round(insertedMoney*100 - cost*100);
        for(Tank tank : moneyTank) {
            int quantity =  (int) (stillToGive/(tank.getValue()*100));
            if(quantity > tank.getQuantity())
                quantity = tank.getQuantity();
            tank.subtractQuantity(quantity);
            stillToGive -= quantity*tank.getValue()*100;
        }
        return stillToGive;
    }
    
    /**
     * Aggiunge la quantità specificata alle monete inserite
     * @param money
     * @return 
     */
    public double addToInsertedMoney(double money) {
        insertedMoney += money;
        //Serve per fare in modo che inserted money abbia 2 cifre decimali
        int temp = (int)Math.round(insertedMoney*100);
        insertedMoney = (double)temp/(double)100;
        return insertedMoney;
    }
    
    /**
     * Setta a 0 le monete inserite. Chiamato quando viene effettuata la vendita
     */
    public void resetInsertedMoney() {
        insertedMoney = 0;
    }
    
    /**
     * Stampa a video per ogni Tank la quantità di monete/banconote che ha all'interno.
     * Usato per il debugging
     */
    public void printCoinsInTank() {
        for(Tank tank : moneyTank)
            System.out.println(tank.getValue()+ " euro: " + tank.getQuantity());
    }
}
