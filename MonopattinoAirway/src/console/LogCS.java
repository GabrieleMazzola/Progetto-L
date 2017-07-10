package console;


public class LogCS{
    boolean enabled = false;
    
    private LogCS() {
    }
    
    public static LogCS getInstance() {
        return LogHolder.INSTANCE;
    }
    
    private static class LogHolder {
        private static final LogCS INSTANCE = new LogCS();
    }
    
    public boolean print(String tag, String stampa){
        if(enabled){
            switch (tag) {
                case "out":
                    System.out.println(stampa);
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
    
    public void enable() {
        enabled = true;
    }
    
    public void disable() {
        enabled = false;
    }
}
