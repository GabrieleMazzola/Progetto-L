package ticketmachine.handlers;

import java.util.Timer;
import java.util.TimerTask;
import ticketmachine.MachineStatus;
import ticketmachine.TicketMachine;

/**
 *
 * @author Zubeer
 */
public class UpdateHandler {
    private Timer timer;
    private TimerTask updateMachineTask;

    /**
     *
     */
    public static final int UPDATE_REPEAT_TIME = 5*1000;
    
    private TicketMachine machine;
    
    /**
     *
     * @param machine
     */
    public UpdateHandler(TicketMachine machine) {
        this.machine = machine;
        
        timer=new Timer();
        initUpdateMachineTask();
    }
    
    public void start() {
        timer.schedule(updateMachineTask,2000,UPDATE_REPEAT_TIME);
    }
    
    private void initUpdateMachineTask() {
        updateMachineTask = new TimerTask () {
            @Override
            public void run () {
                int cod = machine.getCod();
                double inkPercentage = machine.getInk();
                double paperPercentage = machine.getPaper();
                boolean active = machine.isActive();
                String ipAddress = machine.getClientIPAddress();
                machine.updateMachineStatus(new MachineStatus(cod, ipAddress, inkPercentage, paperPercentage, active));
            };
        };
    }
}
