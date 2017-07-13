/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author Francesco
 */
public class MultiTicket implements Product {
    
    private final String description;
    private final String type;
    private double costSingleTicket;
    private int durationSingleTicket;
    private int numberOfTickets;
    
    /**
     *
     * @param description
     * @param type
     * @param costSingleTicket
     * @param durationSingleTicket
     * @param numberOfTickets
     */
    public MultiTicket(String description,String type,double costSingleTicket,int durationSingleTicket,int numberOfTickets){   
        this.description = description;
        this.type = type;
        this.costSingleTicket = costSingleTicket;
        this.durationSingleTicket = durationSingleTicket;
        this.numberOfTickets = numberOfTickets;
    }
    
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public double getCost() {
        return this.costSingleTicket*this.durationSingleTicket*this.numberOfTickets;
    }

    @Override
    public int getDuration() {
        return this.durationSingleTicket*this.numberOfTickets;
    }
    
}
