/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketCollector;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Simone
 */
public class Fine {
        
    private String cfCode;
    private double amount;
    private LocalDate today, firstDeadline, secondDeadline;
    
    public Fine(String cfCode, double amount){
        
//        long first=15*24*60*60*1000; 
//        long second=first*2;
        this.cfCode=cfCode;
//        this.amount=999;
//        this.firstDeadline=new Date(today.getTime()+first);
//        this.secondDeadline=new Date(today.getTime()+second);
        today = LocalDate.now();
        firstDeadline = today.plus(15, ChronoUnit.DAYS);
        secondDeadline = today.plus(1, ChronoUnit.MONTHS);
        this.amount = amount;
    }

    public String getCfCode() {
        return cfCode;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Fine{" + "cfCode=" + cfCode + ", amount=" + amount + ", firstDeadline=" + firstDeadline + ", secondDeadline=" + secondDeadline + '}';
    }
    
}
