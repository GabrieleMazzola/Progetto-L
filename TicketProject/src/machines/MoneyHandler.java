/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

/**
 *
 * @author Andrea
 * Gestisce i soldi all'interno della Ticket Machine.
 */
public class MoneyHandler {
    private double insertedMoney;
    protected List <Tank> moneyTank = new ArrayList<>();
    private HashMap <Double,Integer> maxQuantityMoney = new HashMap<>();

    public MoneyHandler() {
        insertedMoney = 0;
        moneyTank.add(new Tank(200));
        moneyTank.add(new Tank(100));
        moneyTank.add(new Tank(50));
        moneyTank.add(new Tank(20));
        moneyTank.add(new Tank(10));
        moneyTank.add(new Tank(5));
        moneyTank.add(new Tank(2));
        moneyTank.add(new Tank(1));
        moneyTank.add(new Tank(0.50f));
        moneyTank.add(new Tank(0.20f));
        moneyTank.add(new Tank(0.10f));
        moneyTank.add(new Tank(0.05f));
        moneyTank.add(new Tank(0.02f));
        moneyTank.add(new Tank(0.01f));
    }
    
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
        return -1;    //DO EXCEPTION
    }
    
    /**
     * 
     * @param index
     * @return La quantità di monete/banconote del Tank dell'indice specificato
     */
    public int getSingleQuantitybyIndex(int index){
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
    public int setSingleQuantity(double value,int quantity){
        for(Tank tank: moneyTank){
            if(tank.getValue() == value){
                tank.setQuantity(quantity);
                return 1;     //DO EXCEPTION
            }
        }
        return -1;    //DO EXCEPTION
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
                return 1;     //DO EXCEPTION
            }
        }
        return -1;    //DO EXCEPTION
    }
    
    /**
     * Calcola l'ammontare totale di soldi all'interno del MoneyHandler
     * @return L'ammontare totale di soldi all'interno del MoneyHandler
     */
    public float getTotal(){
        float total = 0;
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
    public double giveChange(double cost, double insertedMoney){
        int stillToGive = (int)Math.round(insertedMoney*100 - cost*100);
        
        for(Tank tank : moneyTank) {
            int quantity =  (int) (stillToGive/(tank.getValue()*100));
            if(quantity > tank.getQuantity()) quantity = tank.getQuantity();
            tank.subtractQuantity(quantity);
            stillToGive -= quantity*tank.getValue()*100;
        }
        return stillToGive;
    }
    
    public double addToInsertedMoney(double money) {
        insertedMoney += money;
        int temp = (int)Math.floor(insertedMoney*100);
        insertedMoney = (double)temp/(double)100;
        return insertedMoney;
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
