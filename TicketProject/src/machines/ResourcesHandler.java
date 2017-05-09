package machines;

/**
 *
 * @author Manuele
 */
public class ResourcesHandler {
    private final int maxPaper = 1000;
    private int paper;
    private final double maxInk = 500;
    private double ink;
    
    public ResourcesHandler() {
        paper = maxPaper;
        ink = maxInk;
    }
    
    public void printTicket() {
        if(paper > 0 && ink > 5) {
            paper -= 1;
            ink -= 5;
        }
        //Valori a caso
    }
    
    public double getPaperPercentage() {
        return (double)paper*100/(double)maxPaper;
    }
    
    public double getInkPercentage() {
        return (double)ink*100/(double)maxInk;
    }
}
