package machines;

/**
 *
 * @author Manuele
 */
public class State {
    private int machineCode;
    private double inkLevel, paperLevel;
    private boolean active;

    public State(int machineCode, double inkLevel, double paperLevel, boolean active) {
        this.machineCode = machineCode;
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
    
    
}
