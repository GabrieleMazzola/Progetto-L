package centralsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zubeer
 */
public class LogCS{
        boolean enable = false;
    private LogCS() {
    }
    
    public static LogCS getInstance() {
        return LogHolder.INSTANCE;
    }
    
    private static class LogHolder {
        private static final LogCS INSTANCE = new LogCS();
    }
    
    public boolean stampa(String tag, String stampa){
        if(enable){
            switch (tag) {
                case "out":
                    //System.out.println(stampa);
                    break;
                case "err":
                    System.err.println(stampa);
                break;
                default:
                    System.err.println("Il tag è errato, usare out o err.");
            }
        }
        return false;
    }
    
    public void abilita() {
        enable = true;
    }
    public void disabilita() {
        enable = false;
    }
    
    
}
