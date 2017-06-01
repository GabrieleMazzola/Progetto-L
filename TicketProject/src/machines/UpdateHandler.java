package machines;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Manuele
 */
public class UpdateHandler {
    private Timer timer;
    private TimerTask updateMachineTask;
    private final int repeatTime;
    
    private TicketMachine machine;
    private StubMachine stub;
    
    public UpdateHandler(TicketMachine machine, StubMachine stub) {
        this.machine = machine;
        this.stub = stub;
        
        timer=new Timer();
        initUpdateMachineTask();
        repeatTime = 3000;
    }
    
    public void start() {
        timer.schedule(updateMachineTask,repeatTime,repeatTime);
    }
    
    private void initUpdateMachineTask() {
        updateMachineTask = new TimerTask () {
            @Override
            public void run () {
                int cod = machine.getCod();
                double inkPercentage = machine.getInk();
                double paperPercentage = machine.getPaper();
                boolean active = machine.isActive();
                String ipAddress = stub.getIPAddress();
                stub.updateMachineStatus(cod, inkPercentage, paperPercentage, active, ipAddress);
            };
        };
    }
}
