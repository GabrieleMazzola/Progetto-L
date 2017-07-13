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
public class PhisicalSimpleSeason implements Product {
    private String description;
    private String type;
    private double monthlyCost;
    private int duration;

    /**
     *
     * @param description
     * @param type
     * @param monthlyCost
     * @param duration
     */
    public PhisicalSimpleSeason(String description, String type, double monthlyCost,int duration){
        this.description=description;
        this.type=type;
        this.monthlyCost=monthlyCost;
        this.duration=duration;
    }
    
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public double getCost() {
        return monthlyCost*duration;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
