/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketcollectors;

import java.util.Date;

/**
 *
 * @author Simone
 */
public class Fine {
        
    private String cfCode;
    private double amount;
    private Date firstDeadline;
    private Date secondDeadline;
    
    public Fine(String cfCode,Date today){
        
        long first=15*24*60*60*1000; 
        long second=first*2;
        this.cfCode=cfCode;
        this.amount=999;
        this.firstDeadline=new Date(today.getTime()+first);
        this.secondDeadline=new Date(today.getTime()+second);
        
    }

    public String getCfCode() {
        return cfCode;
    }

    public double getAmount() {
        return amount;
    }

    public Date getFirstDeadline() {
        return firstDeadline;
    }

    public Date getSecondDeadline() {
        return secondDeadline;
    }

    @Override
    public String toString() {
        return "Fine{" + "cfCode=" + cfCode + ", amount=" + amount + ", firstDeadline=" + firstDeadline + ", secondDeadline=" + secondDeadline + '}';
    }
    
    
    
}
