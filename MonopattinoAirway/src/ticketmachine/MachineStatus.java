package ticketmachine;

/**
 *
 * @author Zubeer
 */
public class MachineStatus {
    private int machineCode;
    private String sellerIp;
    private double inkLevel, paperLevel;
    private boolean active;

    /**
     *
     * @param machineCode
     * @param sellerIp
     * @param inkLevel
     * @param paperLevel
     * @param active
     */
    public MachineStatus(int machineCode, String sellerIp, double inkLevel, double paperLevel, boolean active) {
        this.machineCode = machineCode;
        this.sellerIp = sellerIp;
        this.inkLevel = inkLevel;
        this.paperLevel = paperLevel;
        this.active = active;
    }

    public String getSellerIp() {
        return sellerIp;
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

}