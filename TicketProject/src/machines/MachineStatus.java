/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;

public class MachineStatus {
    private int machineCode;
    private String ip;
    private double inkLevel, paperLevel;
    private boolean active;

    public MachineStatus(int machineCode, String ip, double inkLevel, double paperLevel, boolean active) {
        this.machineCode = machineCode;
        this.ip = ip;
        this.inkLevel = inkLevel;
        this.paperLevel = paperLevel;
        this.active = active;
    }

    public double getInkLevel() {
        return inkLevel;
    }

    public int getMachineCode() {
        return machineCode;
    }

    public double getPaperLevel() {
        return paperLevel;
    }

    public boolean isActive() {
        return active;
    }

    public void setInkLevel(double inkLevel) {
        this.inkLevel = inkLevel;
    }

    public void setPaperLevel(double paperLevel) {
        this.paperLevel = paperLevel;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setIpAddress(String ipAddress) {
        ip = ipAddress;
    }
}