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
 */
public class MoneyHandler {
    
    protected List <Tank> moneyTank = new ArrayList<>();
    HashMap <Double,Integer> maxQuantityMoney = new HashMap<>();
    
    //private Tank cent1 = new Tank(0.01f);
    

    public MoneyHandler() {moneyTank.add( new Tank(200));
    moneyTank.add( new Tank(100));
    moneyTank.add( new Tank(50));
    moneyTank.add( new Tank(20));
    moneyTank.add( new Tank(10));
    moneyTank.add( new Tank(5));
    moneyTank.add( new Tank(2));
    moneyTank.add( new Tank(1));
    moneyTank.add( new Tank(0.50f));
    moneyTank.add( new Tank(0.20f));
    moneyTank.add( new Tank(0.10f));
    moneyTank.add( new Tank(0.05f));
    moneyTank.add( new Tank(0.02f));
    moneyTank.add( new Tank(0.01f));
    }
   
    public int getSingleQuantity(double value){
        for(Tank tank: moneyTank){
            if(tank.getValue() == value){
                return tank.getQuantity();
            }
        }
        return -1;    //DO EXCEPTION
    }
    
    public int getSingleQuantitybyIndex(int index){
        return moneyTank.get(index).getQuantity();
    }
    
    public int setSingleQuantity(double value,int quantity){
        for(Tank tank: moneyTank){
            if(tank.getValue() == value){
                tank.setQuantity(quantity);
                return 1;     //DO EXCEPTION
            }
        }
        return -1;    //DO EXCEPTION
    }
    
    public int addMoney(double value){
        for(Tank tank: moneyTank){
            if(tank.getValue() == value){
                tank.addQuantity(1);
                return 1;     //DO EXCEPTION
            }
        }
        return -1;    //DO EXCEPTION
    }
         
    public float getTotal(){
        float total = 0;
        for(Tank tank: moneyTank){
            total += tank.getQuantity()*tank.getValue();
        }
        return total;
    }
    
    public double giveChange(double cost, double insertedMoney){
        double sillToGive = insertedMoney - cost;       //stillToGive tiene memoria di quanto dobbiamo ancora dare all-utente, piano piano si "svuota" e va nel resto 
        double change=0;
        int coinQuantity;
        
        for(Tank tank: moneyTank){
            coinQuantity=(int)((sillToGive - change) /tank.getValue());            
            if(coinQuantity>tank.getQuantity()){
                change+=tank.getQuantity()*tank.getValue();
                tank.setQuantity(0);
            }else{
                change+=coinQuantity*tank.getValue();
                tank.subtractQuantity(coinQuantity);
            }
        }
        return change;
    }    
}
